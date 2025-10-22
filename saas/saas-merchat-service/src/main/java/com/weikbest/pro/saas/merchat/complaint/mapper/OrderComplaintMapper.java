package com.weikbest.pro.saas.merchat.complaint.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaint;
import com.weikbest.pro.saas.merchat.complaint.module.qo.OrderComplaintQO;
import com.weikbest.pro.saas.merchat.complaint.module.vo.ComplaintBusiStatusGroupVO;
import com.weikbest.pro.saas.merchat.complaint.module.vo.OrderComplaintPageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单投诉表 Mapper 接口
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-19
 */
public interface OrderComplaintMapper extends BaseMapper<OrderComplaint> {

    /**
     * 订单投诉后台分页查询
     *
     * @param page
     * @param orderComplaintQO
     * @return
     */
    IPage<OrderComplaintPageVO> queryPage(Page<OrderComplaintPageVO> page, @Param("orderComplaint") OrderComplaintQO orderComplaintQO);

    /**
     * 订单投诉商户处理状态汇总
     *
     * @param paramMap
     * @return
     */
    List<ComplaintBusiStatusGroupVO> queryGroupOrderComplaintBusiStatus(@Param("param") Map<String, Object> paramMap);
}
