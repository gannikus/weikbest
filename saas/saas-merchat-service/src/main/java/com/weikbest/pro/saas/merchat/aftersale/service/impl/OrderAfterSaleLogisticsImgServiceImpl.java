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
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSaleLogisticsImg;
import com.weikbest.pro.saas.merchat.aftersale.mapper.OrderAfterSaleLogisticsImgMapper;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleLogisticsImgDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.mapstruct.OrderAfterSaleLogisticsImgMapStruct;
import com.weikbest.pro.saas.merchat.aftersale.module.qo.OrderAfterSaleLogisticsImgQO;
import com.weikbest.pro.saas.merchat.aftersale.service.OrderAfterSaleLogisticsImgService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单售后物流图片拆分表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Service
public class OrderAfterSaleLogisticsImgServiceImpl extends ServiceImpl<OrderAfterSaleLogisticsImgMapper, OrderAfterSaleLogisticsImg> implements OrderAfterSaleLogisticsImgService {

    @Override
    public boolean insert(OrderAfterSaleLogisticsImgDTO orderAfterSaleLogisticsImgDTO) {
        OrderAfterSaleLogisticsImg orderAfterSaleLogisticsImg = OrderAfterSaleLogisticsImgMapStruct.INSTANCE.converToEntity(orderAfterSaleLogisticsImgDTO);
        return this.save(orderAfterSaleLogisticsImg);
    }

    @Override
    public boolean updateById(Long id, OrderAfterSaleLogisticsImgDTO orderAfterSaleLogisticsImgDTO) {
        OrderAfterSaleLogisticsImg orderAfterSaleLogisticsImg = this.findById(id);
        OrderAfterSaleLogisticsImgMapStruct.INSTANCE.copyProperties(orderAfterSaleLogisticsImgDTO, orderAfterSaleLogisticsImg);
        orderAfterSaleLogisticsImg.setId(id);
        return this.updateById(orderAfterSaleLogisticsImg);
    }

    @Override
    public OrderAfterSaleLogisticsImg findById(Long id) {
        OrderAfterSaleLogisticsImg orderAfterSaleLogisticsImg = this.getById(id);
        if (ObjectUtil.isNull(orderAfterSaleLogisticsImg)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderAfterSaleLogisticsImg;
    }

    @Override
    public IPage<OrderAfterSaleLogisticsImg> queryPage(OrderAfterSaleLogisticsImgQO orderAfterSaleLogisticsImgQO, PageReq pageReq) {
        QueryWrapper<OrderAfterSaleLogisticsImg> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(orderAfterSaleLogisticsImgQO.getCourierImgPath())) {
            wrapper.eq(OrderAfterSaleLogisticsImg.COURIER_IMG_PATH, orderAfterSaleLogisticsImgQO.getCourierImgPath());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
