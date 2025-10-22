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
import com.weikbest.pro.saas.merchat.order.entity.OrderLogisticsImg;
import com.weikbest.pro.saas.merchat.order.mapper.OrderLogisticsImgMapper;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderLogisticsDTO;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderLogisticsImgDTO;
import com.weikbest.pro.saas.merchat.order.module.mapstruct.OrderLogisticsImgMapStruct;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderLogisticsImgQO;
import com.weikbest.pro.saas.merchat.order.service.OrderLogisticsImgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单物流图片表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class OrderLogisticsImgServiceImpl extends ServiceImpl<OrderLogisticsImgMapper, OrderLogisticsImg> implements OrderLogisticsImgService {

    @Override
    public boolean insert(OrderLogisticsImgDTO orderLogisticsImgDTO) {
        OrderLogisticsImg orderLogisticsImg = OrderLogisticsImgMapStruct.INSTANCE.converToEntity(orderLogisticsImgDTO);
        return this.save(orderLogisticsImg);
    }

    @Override
    public boolean updateById(Long id, OrderLogisticsImgDTO orderLogisticsImgDTO) {
        OrderLogisticsImg orderLogisticsImg = this.findById(id);
        OrderLogisticsImgMapStruct.INSTANCE.copyProperties(orderLogisticsImgDTO, orderLogisticsImg);
        orderLogisticsImg.setId(id);
        return this.updateById(orderLogisticsImg);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveBatchWithOrderLogistics(Long orderLogisticsId, OrderLogisticsDTO orderLogisticsDTO) {
        this.remove(new QueryWrapper<OrderLogisticsImg>().eq(OrderLogisticsImg.ID, orderLogisticsId));
        List<OrderLogisticsImgDTO> orderLogisticsImgDTOList = orderLogisticsDTO.getOrderLogisticsImgDTOList();
        if (CollectionUtil.isNotEmpty(orderLogisticsImgDTOList)) {
            List<OrderLogisticsImg> orderLogisticsImgList = orderLogisticsImgDTOList.stream().map(orderLogisticsImgDTO -> {
                OrderLogisticsImg orderLogisticsImg = OrderLogisticsImgMapStruct.INSTANCE.converToEntity(orderLogisticsImgDTO);
                orderLogisticsImg.setId(orderLogisticsId);
                return orderLogisticsImg;
            }).collect(Collectors.toList());
            this.saveBatch(orderLogisticsImgList);
        }
    }

    @Override
    public OrderLogisticsImg findById(Long id) {
        OrderLogisticsImg orderLogisticsImg = this.getById(id);
        if (ObjectUtil.isNull(orderLogisticsImg)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderLogisticsImg;
    }

    @Override
    public IPage<OrderLogisticsImg> queryPage(OrderLogisticsImgQO orderLogisticsImgQO, PageReq pageReq) {
        QueryWrapper<OrderLogisticsImg> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(orderLogisticsImgQO.getCourierImgPath())) {
            wrapper.eq(OrderLogisticsImg.COURIER_IMG_PATH, orderLogisticsImgQO.getCourierImgPath());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
