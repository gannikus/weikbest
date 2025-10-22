package com.weikbest.pro.saas.sys.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.common.util.SecurestUtil;
import com.weikbest.pro.saas.sys.common.constant.CodeRuleConstant;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.common.ext.CreateAvatarService;
import com.weikbest.pro.saas.sys.common.service.CurrentUserService;
import com.weikbest.pro.saas.sys.param.service.CodeRuleService;
import com.weikbest.pro.saas.sys.system.entity.*;
import com.weikbest.pro.saas.sys.system.mapper.UserMapper;
import com.weikbest.pro.saas.sys.system.module.dto.UserDTO;
import com.weikbest.pro.saas.sys.system.module.dto.UserRegisterDTO;
import com.weikbest.pro.saas.sys.system.module.mapstruct.RoleMapStruct;
import com.weikbest.pro.saas.sys.system.module.mapstruct.UserMapStruct;
import com.weikbest.pro.saas.sys.system.module.mapstruct.UserRelateMapStruct;
import com.weikbest.pro.saas.sys.system.module.qo.UserQO;
import com.weikbest.pro.saas.sys.system.module.vo.UserRelateVO;
import com.weikbest.pro.saas.sys.system.module.vo.UserRoleVO;
import com.weikbest.pro.saas.sys.system.module.vo.UserVO;
import com.weikbest.pro.saas.sys.system.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private CurrentUserService currentUserService;

    @Resource
    private UserLoginService userLoginService;

    @Resource
    private UserRelateService userRelateService;

    @Resource
    private CodeRuleService codeRuleService;

    @Resource
    private UserActivexService userActivexService;

    @Resource
    private RoleService roleService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private CreateAvatarService createAvatarService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(UserDTO userDTO) {
        Long userId = GenerateIDUtil.nextId();
        String number = codeRuleService.nextNum(CodeRuleConstant.T_SYS_USER);
        User user = UserMapStruct.INSTANCE.converToEntity(userDTO);
        user.setId(userId);
        user.setNumber(number);
        user.setIsSysuser(DictConstant.Whether.yes.getCode());
        if (StrUtil.isBlank(userDTO.getAvatar())) {
            user.setAvatar(createAvatarService.generateImageAndUploadOss(userDTO.getName()));
        }
        boolean save = this.save(user);
        // 创建用户关联数据
        this.newRegisterUserRelate(user.getPhone(), DictConstant.UserRelateType.sys.getCode(), userId, userId, number, userDTO.getPassword(), user);
        return save;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(Long id, UserDTO userDTO) {
        User user = this.findById(id);
        UserMapStruct.INSTANCE.copyProperties(userDTO, user);
        user.setId(id);
        boolean update = this.updateById(user);

        // 更新控件关联表
        userActivexService.updateByUser(id, user);

        // 更新系统用户登录表
        UserLogin userLogin = userLoginService.getOne(new QueryWrapper<UserLogin>().eq(UserLogin.USER_ID, id).eq(UserLogin.LOGIN_TYPE, DictConstant.LoginType.phone.getCode()));
        userLogin.setLoginName(user.getPhone());
        userLoginService.updateById(userLogin);

        // 更新密码
        if(StrUtil.isNotBlank(userDTO.getPassword())) {
            userLoginService.updatePassword(id, userDTO.getPassword());
        }

//        String password = userDTO.getPassword();
//        String securestPassword = SecurestUtil.securestPassword(password);
//        List<UserLogin> userLoginList = userLoginService.list(new QueryWrapper<UserLogin>().eq(UserLogin.USER_ID, user.getId()));
//        userLoginList.forEach(userLogin -> userLogin.setPassword(securestPassword));
//        userLoginService.updateBatchById(userLoginList);

        return update;
    }


    @Override
    public User findById(Long id) {
        User user = this.getById(id);
        if (ObjectUtil.isNull(user)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return user;
    }

    @Override
    public IPage<UserVO> queryPage(UserQO userQO, PageReq pageReq) {
        IPage<UserVO> queryPage = this.baseMapper.queryPage(new Page<>(pageReq.getPage(), pageReq.getLimit()), userQO);
        if(CollectionUtil.isNotEmpty(userQO.getRelateTypeList())) {
            // 查一遍关联类型，组装起来
            List<Long> userIdList = queryPage.getRecords().stream().map(UserVO::getId).collect(Collectors.toList());
            List<UserRelate> userRelateList = userRelateService.list(new QueryWrapper<UserRelate>().in(UserRelate.USER_ID, userIdList).in(UserRelate.RELATE_TYPE, userQO.getRelateTypeList()));
            Map<Long, List<UserRelate>> userRelateListMap = userRelateList.stream().collect(Collectors.groupingBy(UserRelate::getUserId));
            return queryPage.convert(userVO -> {
                Long userId = userVO.getId();
                List<UserRelate> userRelates = userRelateListMap.get(userId);
                if (CollectionUtil.isNotEmpty(userRelates)) {
                    List<UserRelateVO> userRelateVOList = userRelates.stream().map(UserRelateMapStruct.INSTANCE::converToVO).collect(Collectors.toList());
                    userVO.setUserRelateVOList(userRelateVOList);
                }
                return userVO;
            });
        }
        return queryPage;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void registUser(UserRegisterDTO userRegisterDTO, String relateType) {
        // 判断手机号是否已存在（手机号在用户表全局唯一）
        String phone = userRegisterDTO.getPhone();
        User user = this.getOne(new QueryWrapper<User>().eq(User.PHONE, phone));

        if (ObjectUtil.isNotEmpty(user)) {
            this.updateRegistUser(user, relateType, user.getId());
        } else {
            this.newRegistUser(userRegisterDTO, phone, relateType, WeikbestConstant.ZERO_LONG);
        }
    }

    private Long updateRegistUser(User user, String relateType, Long relateId) {
        // 查询手机号是否已经绑定该用户类型
        UserRelate userRelate = userRelateService.getOne(new QueryWrapper<UserRelate>()
                .eq(UserRelate.RELATE_ID, relateId)
                .eq(UserRelate.RELATE_TYPE, relateType)
                .eq(UserRelate.USER_ID, user.getId()));
        if (ObjectUtil.isNotNull(userRelate) && !StrUtil.equals(relateType, DictConstant.UserRelateType.applet.getCode())) {
            // 非小程序端的关联账号不能重复
            throw new WeikbestException(ResultConstant.PHONE_REGISTER_FAIL);
        }

        // 该手机号未绑定关联用户，或者是小程序端来的手机号，则建一个关联
        userRelate = new UserRelate();
        userRelate.setUserId(user.getId())
                .setRelateId(relateId)
                .setRelateType(relateType);
        userRelateService.save(userRelate);

        // 更新用户信息
        if (StrUtil.equals(user.getIsSysuser(), DictConstant.Whether.no.getCode())) {
            user.setIsSysuser(StrUtil.equals(DictConstant.UserRelateType.sys.getCode(), relateType) ? DictConstant.Whether.yes.getCode() : DictConstant.Whether.no.getCode());
        }
        user.setModifier(user.getId());
        this.updateById(user);

        // 更新用户控件信息
        userActivexService.updateByUser(user.getId(), user);

        return user.getId();
    }

    private Long newRegistUser(UserRegisterDTO userRegisterDTO, String phone, String relateType, Long relateId) {
        // 手机号不存在，注册用户
        Long userId = GenerateIDUtil.nextId();
        String number = codeRuleService.nextNum(CodeRuleConstant.T_SYS_USER);
        User user = new User();
        user.setId(userId);
        user.setNumber(number);
        user.setName(userRegisterDTO.getName());
        user.setPhone(userRegisterDTO.getPhone());
        user.setEmail(userRegisterDTO.getEmail());
        user.setAvatar(StrUtil.isNotBlank(userRegisterDTO.getAvatar()) ? userRegisterDTO.getAvatar() : createAvatarService.generateImageAndUploadOss(userRegisterDTO.getName()));
        user.setIsSysuser(StrUtil.equals(DictConstant.UserRelateType.sys.getCode(), relateType) ? DictConstant.Whether.yes.getCode() : DictConstant.Whether.no.getCode());
        user.setCreator(userId);
        user.setModifier(userId);
        this.save(user);

        newRegisterUserRelate(phone, relateType, relateId, userId, number, "", user);

        return userId;
    }

    private void newRegisterUserRelate(String phone, String relateType, Long relateId, Long userId, String number, String password, User user) {
        // 新建用户关联表
        UserRelate userRelate = new UserRelate();
        userRelate.setUserId(user.getId())
                .setRelateId(relateId == 0L ? user.getId() : relateId)
                .setRelateType(relateType);
        userRelateService.save(userRelate);

        // 新建用户登录表-手机号登录
        // 新建用户登录表-工号登录
        userLoginService.saveBatch(Lists.newArrayList(
                new UserLogin().setUserId(userId).setLoginName(phone).setPassword(StrUtil.isNotBlank(password) ? SecurestUtil.securestPassword(password) : "").setLoginType(DictConstant.LoginType.phone.getCode()),
                new UserLogin().setUserId(userId).setLoginName(number).setPassword(StrUtil.isNotBlank(password) ? SecurestUtil.securestPassword(password) : "").setLoginType(DictConstant.LoginType.number.getCode())
        ));

        // 新建系统用户控件表
        userActivexService.saveByUser(UserMapStruct.INSTANCE.converToUserActivexEntity(user));
    }

    @Override
    public boolean checkLoginPassword(Long relateId, String phone, String password, String relateType) {
        // 根据手机号查询
        User user = findByPhoneAndRelateType(phone, relateType);
        // 根据手机号 + 用户ID + 密码查询
        UserLogin userLogin = userLoginService.getOne(new QueryWrapper<UserLogin>()
                .eq(UserLogin.USER_ID, user.getId()).eq(UserLogin.LOGIN_NAME, phone)
                .eq(UserLogin.PASSWORD, SecurestUtil.securestPassword(password))
                .eq(UserLogin.LOGIN_TYPE, DictConstant.LoginType.phone.getCode()));

        return ObjectUtil.isNotEmpty(userLogin);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateLoginPassword(String phone, String password, String relateType) {
        // 根据手机号查询
        User user = findByPhoneAndRelateType(phone, relateType);

        // 更新用户登录密码
        return userLoginService.updatePassword(user.getId(), password);
    }

    private User findByPhoneAndRelateType(String phone, String relateType) {
        User user = this.getOne(new QueryWrapper<User>().eq(User.PHONE, phone)
                .eq(User.IS_SYSUSER, StrUtil.equals(DictConstant.UserRelateType.sys.getCode(), relateType) ? DictConstant.Whether.yes.getCode() : DictConstant.Whether.no.getCode()));
        if (ObjectUtil.isEmpty(user)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBatchByIds(List<Long> idList) {
        userRelateService.deleteBatchByIds(idList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long registerRelateUser(String phone, String name, String email, String avatar, String relateType, Long relateId) {
        User user = this.getOne(new QueryWrapper<User>().eq(User.PHONE, phone));
        if (ObjectUtil.isNotEmpty(user)) {
            // 平台用户已经存在，更新平台用户关联信息
            return this.updateRegistUser(user, relateType, relateId);
        }

        // 平台用户不存在，创建平台用户和用户关联信息
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setPhone(phone);
        userRegisterDTO.setName(name);
        userRegisterDTO.setEmail(email);
        userRegisterDTO.setAvatar(avatar);
        return this.newRegistUser(userRegisterDTO, phone, relateType, relateId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long updateByRelateUser(String phone, String name, String email, String avatar, String relateType, Long relateId) {
        UserRelate userRelate = userRelateService.findByRelateId(relateId, relateType);
        User user;
        Long userId;
        if (ObjectUtil.isNull(userRelate)) {
            user = this.getOne(new QueryWrapper<User>().eq(User.PHONE, phone));
            userId = user.getId();
        }else {
            user = this.findById(userRelate.getUserId());
            userId=user.getId();
        }
        // 更新系统用户表
        // 判断是否修改了手机号
        if (StrUtil.equals(user.getPhone(), phone)) {
            // 未修改手机号，则更新其他字段
            if (StrUtil.isBlank(user.getName()) && StrUtil.isNotBlank(name)) {
                user.setName(name);
            }
            if (StrUtil.isBlank(user.getEmail()) && StrUtil.isNotBlank(email)) {
                user.setEmail(email);
            }
            if (StrUtil.isBlank(user.getAvatar()) && StrUtil.isNotBlank(avatar)) {
                user.setAvatar(avatar);
            }
            this.updateById(user);

            // 更新系统用户控件关联表
            userActivexService.updateByUser(userId, user);
            // app用户登出，因为更新了数据，在APP端会重新调用登录，生成新的token
            if (StrUtil.equals(relateType, DictConstant.UserRelateType.applet.getCode())) {
                userLoginService.appLogout();
            }
            return userId;
        }

        log.info("updateByRelateUser update phone. {}", phone);
        // 用户修改了手机号，如果原始手机号是TEMP_开头的，说明是小程序更新的手机号，要做更新
        if (StrUtil.contains(user.getPhone(), WeikbestConstant.TEMP)) {
            User findUser = this.getOne(new QueryWrapper<User>().eq(User.PHONE, phone));
            if (ObjectUtil.isNotNull(findUser)) {
                // 删除原来的系统关联数据
                userRelateService.deleteBatchByRelateIds(Collections.singletonList(relateId));
                // 平台用户已经存在，更新原来的平台用户关联信息
                userId = this.updateRegistUser(findUser, relateType, relateId);
                //更新userLogin的userId
                UserLogin userLogin = userLoginService.getOne(new QueryWrapper<UserLogin>().eq(UserLogin.LOGIN_NAME, phone).eq(UserLogin.LOGIN_TYPE, DictConstant.LoginType.phone.getCode()));
                if (ObjectUtil.isNotNull(userLogin)){
                    userLogin.setUserId(userId);
                    userLoginService.updateById(userLogin);
                }
                // 用户登出
                if (StrUtil.equals(relateType, DictConstant.UserRelateType.applet.getCode())) {
                    userLoginService.appLogout();
                }
                return userId;
            }
        }
        // 用户修改了手机号，如果原始手机号不是TEMP_开头的，平台用户使用手机号为唯一标识，则走注册用户那一套
        // 删除原来的系统关联数据
        userRelateService.deleteBatchByRelateIds(Collections.singletonList(relateId));
        userId = registerRelateUser(phone, name, email, avatar, relateType, relateId);
        // 用户登出
        if (StrUtil.equals(relateType, DictConstant.UserRelateType.applet.getCode())) {
            userLoginService.appLogout();
        } else {
            Long currRelateId = currentUserService.getTokenUser().getId();
            if (ObjectUtil.equal(currRelateId, relateId)) {
                // 改了自己账号的手机号码，用户登出要重新登录
                userLoginService.logout();
            }
        }

        return userId;
    }

    @Override
    public boolean associateRoleList(Long id, List<Long> roleIdList) {
        // 用户关联角色
        return userRoleService.associateRoleList(id, roleIdList);
    }

    @Override
    public List<UserRoleVO> queryUserRole(Long userId) {
        // 查询全部角色
        List<Role> roleList = roleService.list();

        // 查询用户关联的角色
        List<UserRole> userRoleList = userRoleService.list(new QueryWrapper<UserRole>().eq(UserRole.USER_ID, userId));
        Set<Long> userRoleIdSet = new HashSet<>();
        if (CollectionUtil.isNotEmpty(userRoleList)) {
            userRoleIdSet.addAll(userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toSet()));
        }

        // 返回用户关联角色
        return roleList.stream().map(role -> {
            UserRoleVO userRoleVO = RoleMapStruct.INSTANCE.converToUserRoleVO(role, userId);
            userRoleVO.setChecked(userRoleIdSet.contains(role.getId()));
            return userRoleVO;
        }).collect(Collectors.toList());
    }
}
