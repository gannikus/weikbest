package com.weikbest.pro.saas.merchat.cust.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.WeikbestObjectUtil;
import com.weikbest.pro.saas.merchat.cust.entity.CustShopAttent;
import com.weikbest.pro.saas.merchat.cust.mapper.CustShopAttentMapper;
import com.weikbest.pro.saas.merchat.cust.module.dto.CustShopAttentDTO;
import com.weikbest.pro.saas.merchat.cust.module.mapstruct.CustShopAttentMapStruct;
import com.weikbest.pro.saas.merchat.cust.module.qo.CustShopAttentQO;
import com.weikbest.pro.saas.merchat.cust.service.CustShopAttentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户关注店铺表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-18
 */
@Service
public class CustShopAttentServiceImpl extends ServiceImpl<CustShopAttentMapper, CustShopAttent> implements CustShopAttentService {

    @Override
    public boolean insert(CustShopAttentDTO custShopAttentDTO) {
        CustShopAttent custShopAttent = CustShopAttentMapStruct.INSTANCE.converToEntity(custShopAttentDTO);
        return this.save(custShopAttent);
    }

    @Override
    public boolean updateById(Long id, CustShopAttentDTO custShopAttentDTO) {
        CustShopAttent custShopAttent = this.findById(id);
        CustShopAttentMapStruct.INSTANCE.copyProperties(custShopAttentDTO, custShopAttent);
        custShopAttent.setId(id);
        return this.updateById(custShopAttent);
    }

    @Override
    public CustShopAttent findById(Long id) {
        CustShopAttent custShopAttent = this.getById(id);
        if (ObjectUtil.isNull(custShopAttent)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return custShopAttent;
    }

    @Override
    public IPage<CustShopAttent> queryPage(CustShopAttentQO custShopAttentQO, PageReq pageReq) {
        QueryWrapper<CustShopAttent> wrapper = new QueryWrapper<>();
        if (WeikbestObjectUtil.isNotEmpty(custShopAttentQO.getCustomerId())) {
            wrapper.eq(CustShopAttent.CUSTOMER_ID, custShopAttentQO.getCustomerId());
        }
        wrapper.orderByDesc(CustShopAttent.GMT_CREATE);
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
