package com.weikbest.pro.saas.sys.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.system.entity.User;
import com.weikbest.pro.saas.sys.system.module.dto.UserDTO;
import com.weikbest.pro.saas.sys.system.module.dto.UserRegisterDTO;
import com.weikbest.pro.saas.sys.system.module.qo.UserQO;
import com.weikbest.pro.saas.sys.system.module.vo.UserRoleVO;
import com.weikbest.pro.saas.sys.system.module.vo.UserVO;

import java.util.List;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
public interface UserService extends IService<User> {

    /**
     * 新增数据
     *
     * @param userDTO userDTO
     * @return 新增结果
     */
    boolean insert(UserDTO userDTO);

    /**
     * 更新数据
     *
     * @param id      ID
     * @param userDTO userDTO
     * @return 更新结果
     */
    boolean updateById(Long id, UserDTO userDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    User findById(Long id);

    /**
     * 分页查询
     *
     * @param userQO
     * @param pageReq
     * @return
     */
    IPage<UserVO> queryPage(UserQO userQO, PageReq pageReq);

    /**
     * 平台用户注册
     *
     * @param userRegisterDTO
     * @param relateType
     */
    void registUser(UserRegisterDTO userRegisterDTO, String relateType);

    /**
     * 非平台用户注册
     *
     * @param phone
     * @param name
     * @param email
     * @param avatar
     * @param relateType
     * @param relateId
     * @return 返回平台用户ID
     */
    Long registerRelateUser(String phone, String name, String email, String avatar, String relateType, Long relateId);

    /**
     * 非平台用户更新
     *
     * @param phone
     * @param name
     * @param email
     * @param avatar
     * @param relateType
     * @param relateId
     * @return 返回平台用户ID
     */
    Long updateByRelateUser(String phone, String name, String email, String avatar, String relateType, Long relateId);

    /**
     * 根据ID列表删除系统用户
     *
     * @param idList
     */
    void deleteBatchByIds(List<Long> idList);

    /**
     * 用户关联角色
     *
     * @param id
     * @param roleIdList
     * @return
     */
    boolean associateRoleList(Long id, List<Long> roleIdList);

    /**
     * 查询所有角色和用户ID关联的角色
     *
     * @param userId
     * @return
     */
    List<UserRoleVO> queryUserRole(Long userId);

    /**
     * 查询用户密码是否正确
     *
     * @param relateId
     * @param phone
     * @param password
     * @param relateType
     * @return true-匹配成功， false-匹配失败
     */
    boolean checkLoginPassword(Long relateId, String phone, String password, String relateType);

    /**
     * 更新用户登录密码
     *
     * @param phone
     * @param password
     * @param relateType
     * @return
     */
    boolean updateLoginPassword(String phone, String password, String relateType);
}
