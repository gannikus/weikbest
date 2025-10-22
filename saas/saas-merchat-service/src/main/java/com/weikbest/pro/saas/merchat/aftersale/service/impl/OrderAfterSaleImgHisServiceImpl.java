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
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSaleImgHis;
import com.weikbest.pro.saas.merchat.aftersale.mapper.OrderAfterSaleImgHisMapper;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleImgHisDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.mapstruct.OrderAfterSaleImgHisMapStruct;
import com.weikbest.pro.saas.merchat.aftersale.module.qo.OrderAfterSaleImgHisQO;
import com.weikbest.pro.saas.merchat.aftersale.service.OrderAfterSaleImgHisService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单售后图片拆分历史表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Service
public class OrderAfterSaleImgHisServiceImpl extends ServiceImpl<OrderAfterSaleImgHisMapper, OrderAfterSaleImgHis> implements OrderAfterSaleImgHisService {

    @Override
    public boolean insert(OrderAfterSaleImgHisDTO orderAfterSaleImgHisDTO) {
        OrderAfterSaleImgHis orderAfterSaleImgHis = OrderAfterSaleImgHisMapStruct.INSTANCE.converToEntity(orderAfterSaleImgHisDTO);
        return this.save(orderAfterSaleImgHis);
    }

    @Override
    public boolean updateById(Long id, OrderAfterSaleImgHisDTO orderAfterSaleImgHisDTO) {
        OrderAfterSaleImgHis orderAfterSaleImgHis = this.findById(id);
        OrderAfterSaleImgHisMapStruct.INSTANCE.copyProperties(orderAfterSaleImgHisDTO, orderAfterSaleImgHis);
        orderAfterSaleImgHis.setId(id);
        return this.updateById(orderAfterSaleImgHis);
    }

    @Override
    public OrderAfterSaleImgHis findById(Long id) {
        OrderAfterSaleImgHis orderAfterSaleImgHis = this.getById(id);
        if (ObjectUtil.isNull(orderAfterSaleImgHis)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderAfterSaleImgHis;
    }

    @Override
    public IPage<OrderAfterSaleImgHis> queryPage(OrderAfterSaleImgHisQO orderAfterSaleImgHisQO, PageReq pageReq) {
        QueryWrapper<OrderAfterSaleImgHis> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(orderAfterSaleImgHisQO.getCourierImgPath())) {
            wrapper.eq(OrderAfterSaleImgHis.COURIER_IMG_PATH, orderAfterSaleImgHisQO.getCourierImgPath());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public List<String> queryImgByHistoryId(Long historyId) {
        List<OrderAfterSaleImgHis> afterSaleImgHisList = this.list(new QueryWrapper<OrderAfterSaleImgHis>().eq(OrderAfterSaleImgHis.HISTORY_ID, historyId));
        return afterSaleImgHisList.stream().map(OrderAfterSaleImgHis::getCourierImgPath).collect(Collectors.toList());
    }
}
