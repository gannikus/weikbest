package com.weikbest.pro.saas.merchat.comment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.comment.entity.CustComment;
import com.weikbest.pro.saas.merchat.comment.module.dto.CustCommentDTO;
import com.weikbest.pro.saas.merchat.comment.module.qo.CustCommentQO;

/**
 * <p>
 * 客户评论表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-24
 */
public interface CustCommentService extends IService<CustComment> {

    /**
     * 新增数据
     *
     * @param custCommentDTO custCommentDTO
     * @return 新增结果
     */
    boolean insert(CustCommentDTO custCommentDTO);

    /**
     * 更新数据
     *
     * @param id             ID
     * @param custCommentDTO custCommentDTO
     * @return 更新结果
     */
    boolean updateById(Long id, CustCommentDTO custCommentDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    CustComment findById(Long id);

    /**
     * 分页查询
     *
     * @param custCommentQO
     * @param pageReq
     * @return
     */
    IPage<CustComment> queryPage(CustCommentQO custCommentQO, PageReq pageReq);
}
