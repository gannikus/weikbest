package com.weikbest.pro.saas.merchat.comment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.comment.entity.CustCommentImg;
import com.weikbest.pro.saas.merchat.comment.module.dto.CustCommentImgDTO;
import com.weikbest.pro.saas.merchat.comment.module.qo.CustCommentImgQO;

/**
 * <p>
 * 用户评论图片表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface CustCommentImgService extends IService<CustCommentImg> {

    /**
     * 新增数据
     *
     * @param custCommentImgDTO custCommentImgDTO
     * @return 新增结果
     */
    boolean insert(CustCommentImgDTO custCommentImgDTO);

    /**
     * 更新数据
     *
     * @param id                ID
     * @param custCommentImgDTO custCommentImgDTO
     * @return 更新结果
     */
    boolean updateById(Long id, CustCommentImgDTO custCommentImgDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    CustCommentImg findById(Long id);

    /**
     * 分页查询
     *
     * @param custCommentImgQO
     * @param pageReq
     * @return
     */
    IPage<CustCommentImg> queryPage(CustCommentImgQO custCommentImgQO, PageReq pageReq);
}
