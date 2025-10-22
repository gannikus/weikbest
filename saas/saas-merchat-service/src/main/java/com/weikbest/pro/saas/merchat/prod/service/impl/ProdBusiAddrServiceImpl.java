package com.weikbest.pro.saas.merchat.prod.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.busi.entity.BusiAddress;
import com.weikbest.pro.saas.merchat.busi.service.BusiAddressService;
import com.weikbest.pro.saas.merchat.prod.entity.ProdBusiAddr;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdBusiAddrMapper;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdBusiAddrDTO;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdBusiAddrMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdBusiAddrQO;
import com.weikbest.pro.saas.merchat.prod.service.ProdBusiAddrService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品与商家详细地址管理表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ProdBusiAddrServiceImpl extends ServiceImpl<ProdBusiAddrMapper, ProdBusiAddr> implements ProdBusiAddrService {

    @Resource
    private BusiAddressService busiAddressService;

    @Override
    public boolean insert(ProdBusiAddrDTO prodBusiAddrDTO) {
        ProdBusiAddr prodBusiAddr = ProdBusiAddrMapStruct.INSTANCE.converToEntity(prodBusiAddrDTO);
        return this.save(prodBusiAddr);
    }

    @Override
    public boolean updateById(Long id, ProdBusiAddrDTO prodBusiAddrDTO) {
        ProdBusiAddr prodBusiAddr = this.findById(id);
        ProdBusiAddrMapStruct.INSTANCE.copyProperties(prodBusiAddrDTO, prodBusiAddr);
        prodBusiAddr.setId(id);
        return this.updateById(prodBusiAddr);
    }

    @Override
    public ProdBusiAddr findById(Long id) {
        ProdBusiAddr prodBusiAddr = this.getById(id);
        if (ObjectUtil.isNull(prodBusiAddr)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return prodBusiAddr;
    }

    @Override
    public IPage<ProdBusiAddr> queryPage(ProdBusiAddrQO prodBusiAddrQO, PageReq pageReq) {
        QueryWrapper<ProdBusiAddr> wrapper = new QueryWrapper<>();
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public BusiAddress findOneBusiAddressByProdIdOrBusinessId(Long prodId, Long businessId) {
        List<ProdBusiAddr> prodBusiAddrList = this.list(new QueryWrapper<ProdBusiAddr>().eq(ProdBusiAddr.PROD_ID, prodId));
        if (CollectionUtil.isEmpty(prodBusiAddrList)) {
            // 没有关联关系，查询当时商家默认的一条地址信息
            return busiAddressService.getDefaultByBusinessId(businessId);
        }
        // 拿第一条数据
        return busiAddressService.findById(prodBusiAddrList.get(0).getBusiAddrId());
    }
}
