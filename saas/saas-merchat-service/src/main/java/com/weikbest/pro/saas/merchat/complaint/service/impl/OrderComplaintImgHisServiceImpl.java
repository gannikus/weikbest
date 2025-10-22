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
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaintImgHis;
import com.weikbest.pro.saas.merchat.complaint.mapper.OrderComplaintImgHisMapper;
import com.weikbest.pro.saas.merchat.complaint.module.dto.OrderComplaintImgHisDTO;
import com.weikbest.pro.saas.merchat.complaint.module.mapstruct.OrderComplaintImgHisMapStruct;
import com.weikbest.pro.saas.merchat.complaint.module.qo.OrderComplaintImgHisQO;
import com.weikbest.pro.saas.merchat.complaint.service.OrderComplaintImgHisService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单投诉图片拆分历史表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-05
 */
@Service
public class OrderComplaintImgHisServiceImpl extends ServiceImpl<OrderComplaintImgHisMapper, OrderComplaintImgHis> implements OrderComplaintImgHisService {

    @Override
    public boolean insert(OrderComplaintImgHisDTO orderComplaintImgHisDTO) {
        OrderComplaintImgHis orderComplaintImgHis = OrderComplaintImgHisMapStruct.INSTANCE.converToEntity(orderComplaintImgHisDTO);
        return this.save(orderComplaintImgHis);
    }

    @Override
    public boolean updateById(Long id, OrderComplaintImgHisDTO orderComplaintImgHisDTO) {
        OrderComplaintImgHis orderComplaintImgHis = this.findById(id);
        OrderComplaintImgHisMapStruct.INSTANCE.copyProperties(orderComplaintImgHisDTO, orderComplaintImgHis);
        orderComplaintImgHis.setId(id);
        return this.updateById(orderComplaintImgHis);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public OrderComplaintImgHis findById(Long id) {
        OrderComplaintImgHis orderComplaintImgHis = this.getById(id);
        if (ObjectUtil.isNull(orderComplaintImgHis)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderComplaintImgHis;
    }

    @Override
    public IPage<OrderComplaintImgHis> queryPage(OrderComplaintImgHisQO orderComplaintImgHisQO, PageReq pageReq) {
        QueryWrapper<OrderComplaintImgHis> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(orderComplaintImgHisQO.getImgPath())) {
            wrapper.eq(OrderComplaintImgHis.IMG_PATH, orderComplaintImgHisQO.getImgPath());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public List<String> queryImgByHistoryIdAndImgType(Long historyId, String imgType) {
        List<OrderComplaintImgHis> orderComplaintImgHis = this.list(new QueryWrapper<OrderComplaintImgHis>().eq(OrderComplaintImgHis.HISTORY_ID, historyId).eq(OrderComplaintImgHis.IMG_TYPE, imgType));
        return orderComplaintImgHis.stream().map(OrderComplaintImgHis::getImgPath).collect(Collectors.toList());
    }
}
