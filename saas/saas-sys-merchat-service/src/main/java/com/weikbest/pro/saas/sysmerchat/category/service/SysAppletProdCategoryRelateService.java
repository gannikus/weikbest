package com.weikbest.pro.saas.sysmerchat.category.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletProdCategoryRelateDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdListVO;
import com.weikbest.pro.saas.sysmerchat.category.module.qo.SysAppletProdCategoryRelateQO;

import java.util.List;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/2
 */
public interface SysAppletProdCategoryRelateService {

    /**
     * 分页查询
     *
     * @param sysAppletProdCategoryRelateQO
     * @param pageReq
     * @return
     */
    IPage<ProdListVO> queryPage(SysAppletProdCategoryRelateQO sysAppletProdCategoryRelateQO, PageReq pageReq);

    /**
     * 保存商品与小程序分类关系
     *
     * @param appletProdCategoryRelateDTOList
     * @return
     */
    boolean associateProdList(List<AppletProdCategoryRelateDTO> appletProdCategoryRelateDTOList);

    /**
     * 根据关联商品小程序类目ID和商品ID删除
     * @param appletProdCategotyId
     * @param prodIdList
     */
    void deleteByAppletProdCategotyIdAndProdId(Long appletProdCategotyId, List<Long> prodIdList);
}
