package com.weikbest.pro.saas.merchat.order.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.order.entity.OrderProdInfo;
import com.weikbest.pro.saas.merchat.order.mapper.OrderProdInfoMapper;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderProdInfoDTO;
import com.weikbest.pro.saas.merchat.order.module.mapstruct.OrderProdInfoMapStruct;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderProdInfoQO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderProdInfoVO;
import com.weikbest.pro.saas.merchat.order.service.OrderProdInfoService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单商品参数详细表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class OrderProdInfoServiceImpl extends ServiceImpl<OrderProdInfoMapper, OrderProdInfo> implements OrderProdInfoService {

    @Override
    public boolean insert(OrderProdInfoDTO orderProdInfoDTO) {
        OrderProdInfo orderProdInfo = OrderProdInfoMapStruct.INSTANCE.converToEntity(orderProdInfoDTO);
        return this.save(orderProdInfo);
    }

    @Override
    public boolean updateById(Long id, OrderProdInfoDTO orderProdInfoDTO) {
        OrderProdInfo orderProdInfo = this.findById(id);
        OrderProdInfoMapStruct.INSTANCE.copyProperties(orderProdInfoDTO, orderProdInfo);
        orderProdInfo.setId(id);
        return this.updateById(orderProdInfo);
    }

    @Override
    public OrderProdInfo findById(Long id) {
        OrderProdInfo orderProdInfo = this.getById(id);
        if (ObjectUtil.isNull(orderProdInfo)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderProdInfo;
    }

    @Override
    public OrderProdInfoVO findVOById(Long id) {
        OrderProdInfo orderProdInfo = this.findById(id);
        return OrderProdInfoMapStruct.INSTANCE.converToVO(orderProdInfo);
    }

    @Override
    public IPage<OrderProdInfo> queryPage(OrderProdInfoQO orderProdInfoQO, PageReq pageReq) {
        QueryWrapper<OrderProdInfo> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(orderProdInfoQO.getProdName())) {
            wrapper.eq(OrderProdInfo.PROD_NAME, orderProdInfoQO.getProdName());
        }
        if (StrUtil.isNotBlank(orderProdInfoQO.getProdSkuName())) {
            wrapper.eq(OrderProdInfo.PROD_SKU_NAME, orderProdInfoQO.getProdSkuName());
        }
        if (StrUtil.isNotBlank(orderProdInfoQO.getProdSkuAttrValues())) {
            wrapper.eq(OrderProdInfo.PROD_SKU_ATTR_VALUES, orderProdInfoQO.getProdSkuAttrValues());
        }
        if (StrUtil.isNotBlank(orderProdInfoQO.getProdImg())) {
            wrapper.eq(OrderProdInfo.PROD_IMG, orderProdInfoQO.getProdImg());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public Map<Long, OrderProdInfo> queryMapByIdList(List<Long> idList) {
        if (CollectionUtil.isEmpty(idList)) {
            return new HashMap<>(1);
        }
        List<OrderProdInfo> orderProdInfoList = this.listByIds(idList);
        return orderProdInfoList.stream().collect(Collectors.toMap(OrderProdInfo::getId, orderProdInfo -> orderProdInfo));
    }
}
