package com.weikbest.pro.saas.merchat.complaint.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaintImg;
import com.weikbest.pro.saas.merchat.complaint.mapper.OrderComplaintImgMapper;
import com.weikbest.pro.saas.merchat.complaint.module.dto.OrderComplaintImgDTO;
import com.weikbest.pro.saas.merchat.complaint.module.mapstruct.OrderComplaintImgMapStruct;
import com.weikbest.pro.saas.merchat.complaint.module.qo.OrderComplaintImgQO;
import com.weikbest.pro.saas.merchat.complaint.service.OrderComplaintImgService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单投诉图片拆分表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-19
 */
@Service
public class OrderComplaintImgServiceImpl extends ServiceImpl<OrderComplaintImgMapper, OrderComplaintImg> implements OrderComplaintImgService {

    @Override
    public boolean insert(OrderComplaintImgDTO orderComplaintImgDTO) {
        OrderComplaintImg orderComplaintImg = OrderComplaintImgMapStruct.INSTANCE.converToEntity(orderComplaintImgDTO);
        return this.save(orderComplaintImg);
    }

    @Override
    public boolean updateById(Long id, OrderComplaintImgDTO orderComplaintImgDTO) {
        OrderComplaintImg orderComplaintImg = this.findById(id);
        OrderComplaintImgMapStruct.INSTANCE.copyProperties(orderComplaintImgDTO, orderComplaintImg);
        orderComplaintImg.setId(id);
        return this.updateById(orderComplaintImg);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public OrderComplaintImg findById(Long id) {
        OrderComplaintImg orderComplaintImg = this.getById(id);
        if (ObjectUtil.isNull(orderComplaintImg)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderComplaintImg;
    }

    @Override
    public IPage<OrderComplaintImg> queryPage(OrderComplaintImgQO orderComplaintImgQO, PageReq pageReq) {
        QueryWrapper<OrderComplaintImg> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(orderComplaintImgQO.getImgPath())) {
            wrapper.eq(OrderComplaintImg.IMG_PATH, orderComplaintImgQO.getImgPath());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
