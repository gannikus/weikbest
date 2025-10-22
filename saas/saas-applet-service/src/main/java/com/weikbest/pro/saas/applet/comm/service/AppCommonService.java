package com.weikbest.pro.saas.applet.comm.service;


import com.weikbest.pro.saas.applet.comm.module.qo.AppProdSemPositionQO;
import com.weikbest.pro.saas.applet.comm.module.vo.*;
import com.weikbest.pro.saas.sys.param.module.vo.LogisticsCompanyVO;

import java.util.List;

public interface AppCommonService {

    /**
     * 获取首页轮播广告位
     *
     * @return
     */
    List<AppProdCarouselVO> queryProdCarousels();

    /**
     * 获取营销位对应广告分类信息
     *
     * @return
     */
    List<AppProdSemPositionVO> queryProdSemPositions(AppProdSemPositionQO appProdSemPositionQO);

    /**
     * 根据分类编码获取字典列表
     *
     * @param number
     * @return
     */
    List<AppDictVO> queryDictsByNumber(String number);

    /**
     * 根据小程序装修配置ID查询区域信息
     *
     * @param appletDecConfigId
     * @return
     */
    List<AppAppletDecConfigEntryVO> queryIndexInfo(Long appletDecConfigId);

    /**
     * 获取小程序类目集
     *
     * @param number 类目编码
     * @return
     */
    List<AppAppletProdCategoryVO> queryAppletProdCategorys(String number);


    /**
     * 获取物流公司信息
     *
     * @return
     */
    List<LogisticsCompanyVO> queryLogisticsCompany();
}
