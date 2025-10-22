package com.weikbest.pro.saas.sys.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.sys.system.entity.UserLogin;
import com.weikbest.pro.saas.sys.system.module.dto.UserLoginDTO;
import com.weikbest.pro.saas.sys.system.module.qo.UserLoginQO;

/**
 * <p>
 * 系统用户登录表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
public interface UserLoginService extends IService<UserLogin> {

    /**
     * 新增数据
     *
     * @param userLoginDTO userLoginDTO
     * @return 新增结果
     */
    boolean insert(UserLoginDTO userLoginDTO);

    /**
     * 更新数据
     *
     * @param id           ID
     * @param userLoginDTO userLoginDTO
     * @return 更新结果
     */
    boolean updateById(Long id, UserLoginDTO userLoginDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    UserLogin findById(Long id);

    /**
     * 分页查询
     *
     * @param userLoginQO
     * @param pageReq
     * @return
     */
    IPage<UserLogin> queryPage(UserLoginQO userLoginQO, PageReq pageReq);

    /**
     * 用户登录（后台+商户登录）
     *
     * @param userLogin
     * @param relateType
     * @param loginIp
     * @param loginName
     * @return
     */
    TokenUser loginUser(UserLogin userLogin, String relateType, String loginIp, String loginName);

    /**
     * 用户登录（小程序客户登录）
     *
     * @param userLogin
     * @param relateType
     * @param loginIp
     * @param loginName
     * @param relateId
     * @return
     */
    TokenUser loginUser(UserLogin userLogin, String relateType, String loginIp, String loginName, Long relateId);

    /**
     * 根据手机号修改密码
     *
     * @param phone
     * @param password
     */
    void repass(String phone, String password);

    /**
     * 登出
     */
    void logout();

    /**
     * 登出
     */
    void appLogout();

    /**
     * 登出
     *
     * @param relateId
     * @param loginName
     * @param userType
     * @param userLoginRecordId
     */
    void logoutWithTimeout(Long relateId, String loginName, String userType, Long userLoginRecordId);

    /**
     * 根据系统用户ID和登录类型查询
     *
     * @param userId
     * @param loginType
     * @return
     */
    UserLogin findByUserIdAndLoginType(Long userId, String loginType);

    /**
     * 更新登录密码
     *
     * @param userId
     * @param password
     * @return
     */
    boolean updatePassword(Long userId, String password);

}
