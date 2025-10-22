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
import com.weikbest.pro.saas.merchat.comment.entity.CustCommentImg;
import com.weikbest.pro.saas.merchat.comment.mapper.CustCommentImgMapper;
import com.weikbest.pro.saas.merchat.comment.module.dto.CustCommentImgDTO;
import com.weikbest.pro.saas.merchat.comment.module.mapstruct.CustCommentImgMapStruct;
import com.weikbest.pro.saas.merchat.comment.module.qo.CustCommentImgQO;
import com.weikbest.pro.saas.merchat.comment.service.CustCommentImgService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户评论图片表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class CustCommentImgServiceImpl extends ServiceImpl<CustCommentImgMapper, CustCommentImg> implements CustCommentImgService {

    @Override
    public boolean insert(CustCommentImgDTO custCommentImgDTO) {
        CustCommentImg custCommentImg = CustCommentImgMapStruct.INSTANCE.converToEntity(custCommentImgDTO);
        return this.save(custCommentImg);
    }

    @Override
    public boolean updateById(Long id, CustCommentImgDTO custCommentImgDTO) {
        CustCommentImg custCommentImg = this.findById(id);
        CustCommentImgMapStruct.INSTANCE.copyProperties(custCommentImgDTO, custCommentImg);
        custCommentImg.setId(id);
        return this.updateById(custCommentImg);
    }

    @Override
    public CustCommentImg findById(Long id) {
        CustCommentImg custCommentImg = this.getById(id);
        if (ObjectUtil.isNull(custCommentImg)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return custCommentImg;
    }

    @Override
    public IPage<CustCommentImg> queryPage(CustCommentImgQO custCommentImgQO, PageReq pageReq) {
        QueryWrapper<CustCommentImg> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(custCommentImgQO.getImgUrl())) {
            wrapper.eq(CustCommentImg.IMG_URL, custCommentImgQO.getImgUrl());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
