package com.weikbest.pro.saas.merchat.aftersale.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSaleImg;
import com.weikbest.pro.saas.merchat.aftersale.mapper.OrderAfterSaleImgMapper;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleImgDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.mapstruct.OrderAfterSaleImgMapStruct;
import com.weikbest.pro.saas.merchat.aftersale.module.qo.OrderAfterSaleImgQO;
import com.weikbest.pro.saas.merchat.aftersale.service.OrderAfterSaleImgService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单售后图片拆分表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Service
public class OrderAfterSaleImgServiceImpl extends ServiceImpl<OrderAfterSaleImgMapper, OrderAfterSaleImg> implements OrderAfterSaleImgService {

    @Override
    public boolean insert(OrderAfterSaleImgDTO orderAfterSaleImgDTO) {
        OrderAfterSaleImg orderAfterSaleImg = OrderAfterSaleImgMapStruct.INSTANCE.converToEntity(orderAfterSaleImgDTO);
        return this.save(orderAfterSaleImg);
    }

    @Override
    public boolean updateById(Long id, OrderAfterSaleImgDTO orderAfterSaleImgDTO) {
        OrderAfterSaleImg orderAfterSaleImg = this.findById(id);
        OrderAfterSaleImgMapStruct.INSTANCE.copyProperties(orderAfterSaleImgDTO, orderAfterSaleImg);
        orderAfterSaleImg.setId(id);
        return this.updateById(orderAfterSaleImg);
    }

    @Override
    public OrderAfterSaleImg findById(Long id) {
        OrderAfterSaleImg orderAfterSaleImg = this.getById(id);
        if (ObjectUtil.isNull(orderAfterSaleImg)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderAfterSaleImg;
    }

    @Override
    public IPage<OrderAfterSaleImg> queryPage(OrderAfterSaleImgQO orderAfterSaleImgQO, PageReq pageReq) {
        QueryWrapper<OrderAfterSaleImg> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(orderAfterSaleImgQO.getCourierImgPath())) {
            wrapper.eq(OrderAfterSaleImg.COURIER_IMG_PATH, orderAfterSaleImgQO.getCourierImgPath());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
