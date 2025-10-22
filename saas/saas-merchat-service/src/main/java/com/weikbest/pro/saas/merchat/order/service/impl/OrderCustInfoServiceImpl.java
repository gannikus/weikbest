package com.weikbest.pro.saas.merchat.order.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.order.entity.OrderCustInfo;
import com.weikbest.pro.saas.merchat.order.mapper.OrderCustInfoMapper;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderCustInfoDTO;
import com.weikbest.pro.saas.merchat.order.module.mapstruct.OrderCustInfoMapStruct;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderCustInfoQO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderCustInfoVO;
import com.weikbest.pro.saas.merchat.order.service.OrderCustInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户订单与客户关联拆分表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class OrderCustInfoServiceImpl extends ServiceImpl<OrderCustInfoMapper, OrderCustInfo> implements OrderCustInfoService {

    @Override
    public boolean insert(OrderCustInfoDTO orderCustInfoDTO) {
        OrderCustInfo orderCustInfo = OrderCustInfoMapStruct.INSTANCE.converToEntity(orderCustInfoDTO);
        return this.save(orderCustInfo);
    }

    @Override
    public boolean updateById(Long id, OrderCustInfoDTO orderCustInfoDTO) {
        OrderCustInfo orderCustInfo = this.findById(id);
        OrderCustInfoMapStruct.INSTANCE.copyProperties(orderCustInfoDTO, orderCustInfo);
        orderCustInfo.setId(id);
        return this.updateById(orderCustInfo);
    }

    @Override
    public OrderCustInfo findById(Long id) {
        OrderCustInfo orderCustInfo = this.getById(id);
        if (ObjectUtil.isNull(orderCustInfo)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderCustInfo;
    }

    @Override
    public OrderCustInfoVO findVOById(Long id) {
        OrderCustInfo orderCustInfo = this.findById(id);
        return OrderCustInfoMapStruct.INSTANCE.converToVO(orderCustInfo);
    }

    @Override
    public IPage<OrderCustInfo> queryPage(OrderCustInfoQO orderCustInfoQO, PageReq pageReq) {
        QueryWrapper<OrderCustInfo> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(orderCustInfoQO.getCustomerName())) {
            wrapper.eq(OrderCustInfo.CUSTOMER_NAME, orderCustInfoQO.getCustomerName());
        }
        if (StrUtil.isNotBlank(orderCustInfoQO.getCustomerProvince())) {
            wrapper.eq(OrderCustInfo.CUSTOMER_PROVINCE, orderCustInfoQO.getCustomerProvince());
        }
        if (StrUtil.isNotBlank(orderCustInfoQO.getCustomerCity())) {
            wrapper.eq(OrderCustInfo.CUSTOMER_CITY, orderCustInfoQO.getCustomerCity());
        }
        if (StrUtil.isNotBlank(orderCustInfoQO.getCustomerDistrict())) {
            wrapper.eq(OrderCustInfo.CUSTOMER_DISTRICT, orderCustInfoQO.getCustomerDistrict());
        }
        if (StrUtil.isNotBlank(orderCustInfoQO.getCustomerAddr())) {
            wrapper.eq(OrderCustInfo.CUSTOMER_ADDR, orderCustInfoQO.getCustomerAddr());
        }
        if (StrUtil.isNotBlank(orderCustInfoQO.getCustomerPhone())) {
            wrapper.eq(OrderCustInfo.CUSTOMER_PHONE, orderCustInfoQO.getCustomerPhone());
        }
        if (StrUtil.isNotBlank(orderCustInfoQO.getTpType())) {
            wrapper.eq(OrderCustInfo.TP_TYPE, orderCustInfoQO.getTpType());
        }
        if (StrUtil.isNotBlank(orderCustInfoQO.getTpName())) {
            wrapper.eq(OrderCustInfo.TP_NAME, orderCustInfoQO.getTpName());
        }
        if (StrUtil.isNotBlank(orderCustInfoQO.getTpPhoto())) {
            wrapper.eq(OrderCustInfo.TP_PHOTO, orderCustInfoQO.getTpPhoto());
        }
        if (StrUtil.isNotBlank(orderCustInfoQO.getTpOpenid())) {
            wrapper.eq(OrderCustInfo.TP_OPENID, orderCustInfoQO.getTpOpenid());
        }
        if (StrUtil.isNotBlank(orderCustInfoQO.getTpQrcode())) {
            wrapper.eq(OrderCustInfo.TP_QRCODE, orderCustInfoQO.getTpQrcode());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
