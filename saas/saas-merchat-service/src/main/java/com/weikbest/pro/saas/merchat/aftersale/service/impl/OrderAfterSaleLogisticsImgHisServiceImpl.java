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
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSaleLogisticsImgHis;
import com.weikbest.pro.saas.merchat.aftersale.mapper.OrderAfterSaleLogisticsImgHisMapper;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleLogisticsImgHisDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.mapstruct.OrderAfterSaleLogisticsImgHisMapStruct;
import com.weikbest.pro.saas.merchat.aftersale.module.qo.OrderAfterSaleLogisticsImgHisQO;
import com.weikbest.pro.saas.merchat.aftersale.service.OrderAfterSaleLogisticsImgHisService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单售后物流图片拆分历史表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Service
public class OrderAfterSaleLogisticsImgHisServiceImpl extends ServiceImpl<OrderAfterSaleLogisticsImgHisMapper, OrderAfterSaleLogisticsImgHis> implements OrderAfterSaleLogisticsImgHisService {

    @Override
    public boolean insert(OrderAfterSaleLogisticsImgHisDTO orderAfterSaleLogisticsImgHisDTO) {
        OrderAfterSaleLogisticsImgHis orderAfterSaleLogisticsImgHis = OrderAfterSaleLogisticsImgHisMapStruct.INSTANCE.converToEntity(orderAfterSaleLogisticsImgHisDTO);
        return this.save(orderAfterSaleLogisticsImgHis);
    }

    @Override
    public boolean updateById(Long id, OrderAfterSaleLogisticsImgHisDTO orderAfterSaleLogisticsImgHisDTO) {
        OrderAfterSaleLogisticsImgHis orderAfterSaleLogisticsImgHis = this.findById(id);
        OrderAfterSaleLogisticsImgHisMapStruct.INSTANCE.copyProperties(orderAfterSaleLogisticsImgHisDTO, orderAfterSaleLogisticsImgHis);
        orderAfterSaleLogisticsImgHis.setId(id);
        return this.updateById(orderAfterSaleLogisticsImgHis);
    }

    @Override
    public OrderAfterSaleLogisticsImgHis findById(Long id) {
        OrderAfterSaleLogisticsImgHis orderAfterSaleLogisticsImgHis = this.getById(id);
        if (ObjectUtil.isNull(orderAfterSaleLogisticsImgHis)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderAfterSaleLogisticsImgHis;
    }

    @Override
    public IPage<OrderAfterSaleLogisticsImgHis> queryPage(OrderAfterSaleLogisticsImgHisQO orderAfterSaleLogisticsImgHisQO, PageReq pageReq) {
        QueryWrapper<OrderAfterSaleLogisticsImgHis> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(orderAfterSaleLogisticsImgHisQO.getCourierImgPath())) {
            wrapper.eq(OrderAfterSaleLogisticsImgHis.COURIER_IMG_PATH, orderAfterSaleLogisticsImgHisQO.getCourierImgPath());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public List<String> queryImgByHistoryIdAndCourierImgType(Long historyId, String courierImgType) {
        List<OrderAfterSaleLogisticsImgHis> orderAfterSaleLogisticsImgHisList = this.list(new QueryWrapper<OrderAfterSaleLogisticsImgHis>().eq(OrderAfterSaleLogisticsImgHis.HISTORY_ID, historyId).eq(OrderAfterSaleLogisticsImgHis.COURIER_IMG_TYPE, courierImgType));
        return orderAfterSaleLogisticsImgHisList.stream().map(OrderAfterSaleLogisticsImgHis::getCourierImgPath).collect(Collectors.toList());
    }
}
