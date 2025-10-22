package com.weikbest.pro.saas.sys.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.system.entity.UserRelate;
import com.weikbest.pro.saas.sys.system.module.dto.UserRelateDTO;
import com.weikbest.pro.saas.sys.system.module.qo.UserRelateQO;

import java.util.List;

/**
 * <p>
 * 系统用户关联表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
public interface UserRelateService extends IService<UserRelate> {

    /**
     * 新增数据
     *
     * @param userRelateDTO userRelateDTO
     * @return 新增结果
     */
    boolean insert(UserRelateDTO userRelateDTO);

    /**
     * 更新数据
     *
     * @param id            ID
     * @param userRelateDTO userRelateDTO
     * @return 更新结果
     */
    boolean updateById(Long id, UserRelateDTO userRelateDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    UserRelate findById(Long id);

    /**
     * 分页查询
     *
     * @param userRelateQO
     * @param pageReq
     * @return
     */
    IPage<UserRelate> queryPage(UserRelateQO userRelateQO, PageReq pageReq);

    /**
     * 根据系统用户ID集合删除所有系统用户数据
     *
     * @param userIdList
     */
    void deleteBatchByIds(List<Long> userIdList);

    /**
     * 根据关联ID查询
     *
     * @param relateId
     * @param relateType
     * @return
     */
    UserRelate findByRelateId(Long relateId, String relateType);

    /**
     * 根据关联用户ID删除数据
     *
     * @param relateIdList
     */
    void deleteBatchByRelateIds(List<Long> relateIdList);
}
