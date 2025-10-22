package com.weikbest.pro.saas.sys.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.system.entity.User;
import com.weikbest.pro.saas.sys.system.entity.UserActivex;
import com.weikbest.pro.saas.sys.system.module.dto.UserActivexDTO;
import com.weikbest.pro.saas.sys.system.module.qo.UserActivexQO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统用户控件关联表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-20
 */
public interface UserActivexService extends IService<UserActivex> {

    /**
     * 新增数据
     *
     * @param userActivexDTO userActivexDTO
     * @return 新增结果
     */
    boolean insert(UserActivexDTO userActivexDTO);

    /**
     * 更新数据
     *
     * @param id             ID
     * @param userActivexDTO userActivexDTO
     * @return 更新结果
     */
    boolean updateById(Long id, UserActivexDTO userActivexDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    UserActivex findById(Long id);

    /**
     * 分页查询
     *
     * @param userActivexQO
     * @param pageReq
     * @return
     */
    IPage<UserActivex> queryPage(UserActivexQO userActivexQO, PageReq pageReq);

    /**
     * 更新用户控件关联表
     *
     * @param idList
     */
    void disabledByIds(List<Long> idList);

    /**
     * 保存用户数据
     *
     * @param userActivex
     */
    void saveByUser(UserActivex userActivex);

    /**
     * 更新用户数据
     *
     * @param id
     * @param user
     */
    void updateByUser(Long id, User user);

    /**
     * 刷新控件缓存数据
     *
     * @return
     */
    Map<String, Object> refreshCache();

    /**
     * 查询数据后放入缓存
     *
     * @param userId
     * @return
     */
    UserActivex getByIdWithCache(Long userId);

    /**
     * 从缓存中查询数据
     *
     * @param id
     * @return
     */
    UserActivex getByCache(Long id);
}
