package com.weikbest.pro.saas.merchat.comment.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.comment.entity.CustComment;
import com.weikbest.pro.saas.merchat.comment.mapper.CustCommentMapper;
import com.weikbest.pro.saas.merchat.comment.module.dto.CustCommentDTO;
import com.weikbest.pro.saas.merchat.comment.module.mapstruct.CustCommentMapStruct;
import com.weikbest.pro.saas.merchat.comment.module.qo.CustCommentQO;
import com.weikbest.pro.saas.merchat.comment.service.CustCommentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户评论表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-24
 */
@Service
public class CustCommentServiceImpl extends ServiceImpl<CustCommentMapper, CustComment> implements CustCommentService {

    @Override
    public boolean insert(CustCommentDTO custCommentDTO) {
        CustComment custComment = CustCommentMapStruct.INSTANCE.converToEntity(custCommentDTO);
        return this.save(custComment);
    }

    @Override
    public boolean updateById(Long id, CustCommentDTO custCommentDTO) {
        CustComment custComment = this.findById(id);
        CustCommentMapStruct.INSTANCE.copyProperties(custCommentDTO, custComment);
        custComment.setId(id);
        return this.updateById(custComment);
    }

    @Override
    public CustComment findById(Long id) {
        CustComment custComment = this.getById(id);
        if (ObjectUtil.isNull(custComment)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return custComment;
    }

    @Override
    public IPage<CustComment> queryPage(CustCommentQO custCommentQO, PageReq pageReq) {
        QueryWrapper<CustComment> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(custCommentQO.getIsAnonymous())) {
            wrapper.eq(CustComment.IS_ANONYMOUS, custCommentQO.getIsAnonymous());
        }
        if (StrUtil.isNotBlank(custCommentQO.getCommentDetail())) {
            wrapper.eq(CustComment.COMMENT_DETAIL, custCommentQO.getCommentDetail());
        }
        if (StrUtil.isNotBlank(custCommentQO.getCommentType())) {
            wrapper.eq(CustComment.COMMENT_TYPE, custCommentQO.getCommentType());
        }
        if (StrUtil.isNotBlank(custCommentQO.getCommentFlag())) {
            wrapper.eq(CustComment.COMMENT_FLAG, custCommentQO.getCommentFlag());
        }
        if (StrUtil.isNotBlank(custCommentQO.getIsBusinessComm())) {
            wrapper.eq(CustComment.IS_BUSINESS_COMM, custCommentQO.getIsBusinessComm());
        }
        if (StrUtil.isNotBlank(custCommentQO.getAuditType())) {
            wrapper.eq(CustComment.AUDIT_TYPE, custCommentQO.getAuditType());
        }
        if (StrUtil.isNotBlank(custCommentQO.getDataStatus())) {
            wrapper.eq(CustComment.DATA_STATUS, custCommentQO.getDataStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
