package com.weikbest.pro.saas.merchat.aftersale.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSale;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.AppOrderAfterSaleApplyDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.AppOrderAfterSaleListDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.excel.OrderAfterSaleDetailExcel;
import com.weikbest.pro.saas.merchat.aftersale.module.qo.OrderAfterSaleQO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleKeyGroupVO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单售后表 Mapper 接口
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
public interface OrderAfterSaleMapper extends BaseMapper<OrderAfterSale> {

    /**
     * 获取订单售后申请列表
     *
     * @param paramMap
     * @return
     */
    List<AppOrderAfterSaleApplyDTO> queryPageAppOrderAfterApply(Map<String, Object> paramMap);

    /**
     * 获取我的申请列表
     *
     * @param paramMap
     * @return
     */
    List<AppOrderAfterSaleListDTO> queryPageAppOrderAfterList(Map<String, Object> paramMap);

    /**
     * 查询商户店铺订单售后关键节点汇总数据
     *
     * @param paramMap
     * @return
     */
    List<OrderAfterSaleKeyGroupVO> queryGroupOrderAfterSaleKey(@Param("param") Map<String, Object> paramMap);

    /**
     * 订单售后后台分页查询
     *
     * @param page
     * @param orderAfterSaleQO
     * @return
     */
    IPage<OrderAfterSaleListVO> queryPage(IPage<OrderAfterSaleListVO> page, @Param("orderAfterSale") OrderAfterSaleQO orderAfterSaleQO);

    /**
     * excel导出
     *
     * @param orderAfterSaleQO
     * @return
     */
    List<OrderAfterSaleDetailExcel> downloadDetail(@Param("orderAfterSale") OrderAfterSaleQO orderAfterSaleQO);
}
