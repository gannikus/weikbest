package com.weikbest.pro.saas.merchat.cust.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.cust.entity.CustAddress;
import com.weikbest.pro.saas.merchat.cust.mapper.CustAddressMapper;
import com.weikbest.pro.saas.merchat.cust.module.dto.CustAddressDTO;
import com.weikbest.pro.saas.merchat.cust.module.mapstruct.CustAddressMapStruct;
import com.weikbest.pro.saas.merchat.cust.module.qo.CustAddressQO;
import com.weikbest.pro.saas.merchat.cust.service.CustAddressService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户收货地址表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class CustAddressServiceImpl extends ServiceImpl<CustAddressMapper, CustAddress> implements CustAddressService {

    @Override
    public boolean insert(CustAddressDTO custAddressDTO) {
        CustAddress custAddress = CustAddressMapStruct.INSTANCE.converToEntity(custAddressDTO);
        return this.save(custAddress);
    }

    @Override
    public boolean updateById(Long id, CustAddressDTO custAddressDTO) {
        CustAddress custAddress = this.findById(id);
        CustAddressMapStruct.INSTANCE.copyProperties(custAddressDTO, custAddress);
        custAddress.setId(id);
        return this.updateById(custAddress);
    }

    @Override
    public CustAddress findById(Long id) {
        CustAddress custAddress = this.getById(id);
        if (ObjectUtil.isNull(custAddress)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return custAddress;
    }

    @Override
    public IPage<CustAddress> queryPage(CustAddressQO custAddressQO, PageReq pageReq) {
        QueryWrapper<CustAddress> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(custAddressQO.getConsignee())) {
            wrapper.eq(CustAddress.CONSIGNEE, custAddressQO.getConsignee());
        }
        if (StrUtil.isNotBlank(custAddressQO.getConsPhone())) {
            wrapper.eq(CustAddress.CONS_PHONE, custAddressQO.getConsPhone());
        }
        if (StrUtil.isNotBlank(custAddressQO.getAddrProvince())) {
            wrapper.eq(CustAddress.ADDR_PROVINCE, custAddressQO.getAddrProvince());
        }
        if (StrUtil.isNotBlank(custAddressQO.getAddrCity())) {
            wrapper.eq(CustAddress.ADDR_CITY, custAddressQO.getAddrCity());
        }
        if (StrUtil.isNotBlank(custAddressQO.getAddrDistrict())) {
            wrapper.eq(CustAddress.ADDR_DISTRICT, custAddressQO.getAddrDistrict());
        }
        if (StrUtil.isNotBlank(custAddressQO.getAddr())) {
            wrapper.eq(CustAddress.ADDR, custAddressQO.getAddr());
        }
        if (StrUtil.isNotBlank(custAddressQO.getDef())) {
            wrapper.eq(CustAddress.DEF, custAddressQO.getDef());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
