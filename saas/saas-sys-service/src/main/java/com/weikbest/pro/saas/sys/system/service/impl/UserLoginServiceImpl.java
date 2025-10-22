package com.weikbest.pro.saas.sys.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.SecurestUtil;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.common.util.token.TokenUtil;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.common.delaytaskprocess.LogoutDelayTaskProcess;
import com.weikbest.pro.saas.sys.common.service.CurrentUserService;
import com.weikbest.pro.saas.sys.system.entity.User;
import com.weikbest.pro.saas.sys.system.entity.UserLogin;
import com.weikbest.pro.saas.sys.system.entity.UserLoginRecord;
import com.weikbest.pro.saas.sys.system.entity.UserRelate;
import com.weikbest.pro.saas.sys.system.mapper.UserLoginMapper;
import com.weikbest.pro.saas.sys.system.module.dto.UserLoginDTO;
import com.weikbest.pro.saas.sys.system.module.mapstruct.UserLoginMapStruct;
import com.weikbest.pro.saas.sys.system.module.qo.UserLoginQO;
import com.weikbest.pro.saas.sys.system.service.UserLoginRecordService;
import com.weikbest.pro.saas.sys.system.service.UserLoginService;
import com.weikbest.pro.saas.sys.system.service.UserRelateService;
import com.weikbest.pro.saas.sys.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 系统用户登录表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Slf4j
@Service
public class UserLoginServiceImpl extends ServiceImpl<UserLoginMapper, UserLogin> implements UserLoginService {

    @Resource
    private CurrentUserService currentUserService;

    @Resource
    private UserService userService;

    @Resource
    private UserLoginService userLoginService;

    @Resource
    private UserRelateService userRelateService;

    @Resource
    private UserLoginRecordService userLoginRecordService;

    @Resource
    private RedisContext redisContext;

    @Resource
    private LogoutDelayTaskProcess logoutDelayTaskProcess;

    @Override
    public boolean insert(UserLoginDTO userLoginDTO) {
        UserLogin userLogin = UserLoginMapStruct.INSTANCE.converToEntity(userLoginDTO);
        return this.save(userLogin);
    }

    @Override
    public boolean updateById(Long id, UserLoginDTO userLoginDTO) {
        UserLogin userLogin = this.findById(id);
        UserLoginMapStruct.INSTANCE.copyProperties(userLoginDTO, userLogin);
        userLogin.setId(id);
        return this.updateById(userLogin);
    }

    @Override
    public UserLogin findById(Long id) {
        UserLogin userLogin = this.getById(id);
        if (ObjectUtil.isNull(userLogin)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return userLogin;
    }

    @Override
    public IPage<UserLogin> queryPage(UserLoginQO userLoginQO, PageReq pageReq) {
        QueryWrapper<UserLogin> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(userLoginQO.getLoginName())) {
            wrapper.eq(UserLogin.LOGIN_NAME, userLoginQO.getLoginName());
        }
        if (StrUtil.isNotBlank(userLoginQO.getPassword())) {
            wrapper.eq(UserLogin.PASSWORD, userLoginQO.getPassword());
        }
        if (StrUtil.isNotBlank(userLoginQO.getLoginType())) {
            wrapper.eq(UserLogin.LOGIN_TYPE, userLoginQO.getLoginType());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public TokenUser loginUser(UserLogin userLogin, String relateType, String loginIp, String loginName) {
        // 获取系统用户关联ID
        Long userId = userLogin.getUserId();
        UserRelate userRelate = userRelateService.getOne(new QueryWrapper<UserRelate>().eq(UserRelate.USER_ID, userId)
                .eq(UserRelate.RELATE_TYPE, relateType));
        if (ObjectUtil.isEmpty(userRelate)) {
            throw new WeikbestException("该用户不存在，请核实您的信息！");
        }
        Long relateId = userRelate.getRelateId();
        // 返回数据
        return loginUser(userLogin, relateType, loginIp, loginName, relateId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TokenUser loginUser(UserLogin userLogin, String relateType, String loginIp, String loginName, Long relateId) {
        // 获取系统用户关联ID
        Long userId = userLogin.getUserId();
        // 获取系统用户名称
        User user = userService.getById(userId);
        String name =  StrUtil.isBlank(user.getName())? user.getPhone() : user.getName() ;

        // 记录用户登录信息
        UserLoginRecord userLoginRecord = new UserLoginRecord();
        userLoginRecord.setUserId(userId);
        userLoginRecord.setRelateId(relateId);
        userLoginRecord.setUserLoginId(userLogin.getId());
        userLoginRecord.setLoginTime(DateUtil.date());
        userLoginRecord.setLoginIp(loginIp);
        userLoginRecord.setOnline(DictConstant.Online.code_1.getCode());
        userLoginRecordService.save(userLoginRecord);

        // Token必须要的用户信息，token设置不过期，redis中设置过期即可
        TokenUser tokenUser = new TokenUser().setLoginIp(loginIp).setUserId(userId).setLoginNameOrPhone(loginName).setUserLoginRecordId(userLoginRecord.getId())
                .setId(relateId).setRelateType(relateType).setUserLoginId(userLogin.getId()).setUserName(name);

        // 保存用户信息到redis
        String redisUserDataKey = TokenUtil.redisUserDataKey(tokenUser);
        redisContext.hset(redisUserDataKey, "id", tokenUser.getId());
        redisContext.hset(redisUserDataKey, "userId", tokenUser.getUserId());
        redisContext.hset(redisUserDataKey, "userLoginId", tokenUser.getUserLoginId());
        redisContext.hset(redisUserDataKey, "loginName", tokenUser.getLoginNameOrPhone());
        redisContext.hset(redisUserDataKey, "loginIp", tokenUser.getLoginIp());
        redisContext.hset(redisUserDataKey, "userLoginRecordId", userLoginRecord.getId());

        // 返回数据
        return tokenUser;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void repass(String phone, String password) {
        // 根据手机号查找用户信息
        User user = userService.getOne(new QueryWrapper<User>().eq(User.PHONE, phone));
        if (ObjectUtil.isEmpty(user)) {
            throw new WeikbestException("该手机号尚未绑定用户，请核实您的信息！");
        }

        // 更新系统用户登录表，这个人的所有登录密码都更新
        userLoginService.updatePassword(user.getId(), password);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void logout() {
        // 获取当前登录token
        TokenUser tokenUser = currentUserService.getTokenUser();
        logout(tokenUser);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void appLogout() {
        // 获取当前登录token
        TokenUser tokenUser = currentUserService.getAppTokenUser();
        logout(tokenUser);
    }

    private void logout(TokenUser tokenUser) {
        String redisUserTokenKey = TokenUtil.redisUserTokenKey(tokenUser);
        String redisUserDataKey = TokenUtil.redisUserDataKey(tokenUser);

        //更新用户登出
        Long userLoginRecordId = Convert.toLong(redisContext.hget(redisUserDataKey, "userLoginRecordId"));
        UserLoginRecord userLoginRecord = userLoginRecordService.getById(userLoginRecordId);
        if (ObjectUtil.isNotNull(userLoginRecord)) {
            userLoginRecord.setLogoutTime(DateUtil.date());
            userLoginRecord.setOnline(DictConstant.Online.code_0.getCode());
            userLoginRecordService.updateById(userLoginRecord);
        }

        //清除缓存
        redisContext.del(redisUserDataKey, redisUserTokenKey);
        logoutDelayTaskProcess.removeTask(tokenUser);
    }

    @Override
    public void logoutWithTimeout(Long relateId, String loginName, String userType, Long userLoginRecordId) {
        log.info("logout {}，{}，{}，{}", relateId, loginName, userType, userLoginRecordId);

        UserLoginRecord userLoginRecord = userLoginRecordService.getById(userLoginRecordId);
        if (ObjectUtil.isNotNull(userLoginRecord)) {
            userLoginRecord.setLogoutTime(DateUtil.date());
            userLoginRecord.setOnline(DictConstant.Online.code_0.getCode());
            userLoginRecordService.updateById(userLoginRecord);
        }

        //清除缓存
        String redisUserTokenKey = TokenUtil.redisUserTokenKey(relateId, loginName, userType, userLoginRecordId);
        String redisUserDataKey = TokenUtil.redisUserDataKey(relateId, loginName, userType, userLoginRecordId);
        redisContext.del(redisUserDataKey, redisUserTokenKey);
    }

    @Override
    public UserLogin findByUserIdAndLoginType(Long userId, String loginType) {
        UserLogin userLogin = userLoginService.getOne(new QueryWrapper<UserLogin>().eq(UserLogin.USER_ID, userId).eq(UserLogin.LOGIN_TYPE, DictConstant.LoginType.phone.getCode()));
//        if (ObjectUtil.isNull(userLogin)) {
//            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
//        }
        return userLogin;

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updatePassword(Long userId, String password) {
        String securestPassword = SecurestUtil.securestPassword(password);
        List<UserLogin> userLoginList = this.list(new QueryWrapper<UserLogin>().eq(UserLogin.USER_ID, userId));
        if (CollectionUtil.isNotEmpty(userLoginList)) {
            userLoginList.forEach(userLogin -> userLogin.setPassword(securestPassword));
            return this.updateBatchById(userLoginList);
        }

        return false;
    }
}
