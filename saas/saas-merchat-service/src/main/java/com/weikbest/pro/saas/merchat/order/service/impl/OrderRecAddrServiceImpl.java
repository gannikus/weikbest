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
import com.weikbest.pro.saas.merchat.order.entity.OrderInfo;
import com.weikbest.pro.saas.merchat.order.entity.OrderRecAddr;
import com.weikbest.pro.saas.merchat.order.mapper.OrderRecAddrMapper;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderRecAddrDTO;
import com.weikbest.pro.saas.merchat.order.module.mapstruct.OrderRecAddrMapStruct;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderRecAddrQO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderRecAddrVO;
import com.weikbest.pro.saas.merchat.order.service.OrderInfoService;
import com.weikbest.pro.saas.merchat.order.service.OrderRecAddrService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 订单收货地址拆分表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class OrderRecAddrServiceImpl extends ServiceImpl<OrderRecAddrMapper, OrderRecAddr> implements OrderRecAddrService {

    @Resource
    private OrderInfoService orderInfoService;

    @Override
    public boolean insert(OrderRecAddrDTO orderRecAddrDTO) {
        OrderRecAddr orderRecAddr = OrderRecAddrMapStruct.INSTANCE.converToEntity(orderRecAddrDTO);
        return this.save(orderRecAddr);
    }

    @Override
    public boolean updateById(Long id, OrderRecAddrDTO orderRecAddrDTO) {
        OrderInfo orderInfo = orderInfoService.findById(id);
        if (!StrUtil.equalsAny(orderInfo.getOrderStatus(), DictConstant.OrderStatus.orderStatus_1.getCode(), DictConstant.OrderStatus.orderStatus_10.getCode())) {
            // 订单已经发货
            throw new WeikbestException("已发货订单不支持修改收货信息");
        }

        OrderRecAddr orderRecAddr = this.findById(id);
        OrderRecAddrMapStruct.INSTANCE.copyProperties(orderRecAddrDTO, orderRecAddr);
        orderRecAddr.setId(id);
        return this.updateById(orderRecAddr);
    }

    @Override
    public OrderRecAddr findById(Long id) {
        OrderRecAddr orderRecAddr = this.getById(id);
        if (ObjectUtil.isNull(orderRecAddr)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderRecAddr;
    }

    @Override
    public OrderRecAddrVO findVOById(Long id) {
        OrderRecAddr orderRecAddr = this.findById(id);
        return OrderRecAddrMapStruct.INSTANCE.converToVO(orderRecAddr);
    }

    @Override
    public IPage<OrderRecAddr> queryPage(OrderRecAddrQO orderRecAddrQO, PageReq pageReq) {
        QueryWrapper<OrderRecAddr> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(orderRecAddrQO.getConsignee())) {
            wrapper.eq(OrderRecAddr.CONSIGNEE, orderRecAddrQO.getConsignee());
        }
        if (StrUtil.isNotBlank(orderRecAddrQO.getPhone())) {
            wrapper.eq(OrderRecAddr.PHONE, orderRecAddrQO.getPhone());
        }
        if (StrUtil.isNotBlank(orderRecAddrQO.getAddrProvince())) {
            wrapper.eq(OrderRecAddr.ADDR_PROVINCE, orderRecAddrQO.getAddrProvince());
        }
        if (StrUtil.isNotBlank(orderRecAddrQO.getAddrCity())) {
            wrapper.eq(OrderRecAddr.ADDR_CITY, orderRecAddrQO.getAddrCity());
        }
        if (StrUtil.isNotBlank(orderRecAddrQO.getAddrDistrict())) {
            wrapper.eq(OrderRecAddr.ADDR_DISTRICT, orderRecAddrQO.getAddrDistrict());
        }
        if (StrUtil.isNotBlank(orderRecAddrQO.getAddr())) {
            wrapper.eq(OrderRecAddr.ADDR, orderRecAddrQO.getAddr());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
