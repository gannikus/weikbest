package com.weikbest.pro.saas.merchat.busi.service.impl;

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
import com.weikbest.pro.saas.merchat.busi.entity.BusiAddress;
import com.weikbest.pro.saas.merchat.busi.mapper.BusiAddressMapper;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiAddressDTO;
import com.weikbest.pro.saas.merchat.busi.module.mapstruct.BusiAddressMapStruct;
import com.weikbest.pro.saas.merchat.busi.module.qo.BusiAddressQO;
import com.weikbest.pro.saas.merchat.busi.module.vo.BusiAddressVO;
import com.weikbest.pro.saas.merchat.busi.service.BusiAddressService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商家详细地址信息表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-06
 */
@Service
public class BusiAddressServiceImpl extends ServiceImpl<BusiAddressMapper, BusiAddress> implements BusiAddressService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(BusiAddressDTO busiAddressDTO) {
        BusiAddress busiAddress = BusiAddressMapStruct.INSTANCE.converToEntity(busiAddressDTO);

        if (StrUtil.equals(busiAddress.getDef(), DictConstant.Whether.yes.getCode())) {
            // 当前新增的地址是默认地址,将当前商户的其他地址设为非默认地址
            Long businessId = busiAddressDTO.getBusinessId();
            List<BusiAddress> busiAddressList = this.list(new QueryWrapper<BusiAddress>().eq(BusiAddress.BUSINESS_ID, businessId));
            busiAddressList.forEach(updateBusinessAddress -> updateBusinessAddress.setDef(DictConstant.Whether.no.getCode()));
            this.updateBatchById(busiAddressList);
        }

        return this.save(busiAddress);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(Long id, BusiAddressDTO busiAddressDTO) {
        BusiAddress busiAddress = this.findById(id);
        BusiAddressMapStruct.INSTANCE.copyProperties(busiAddressDTO, busiAddress);
        busiAddress.setId(id);

        if (StrUtil.equals(busiAddress.getDef(), DictConstant.Whether.yes.getCode())) {
            // 当前新增的地址是默认地址,将当前商户的其他地址设为非默认地址
            Long businessId = busiAddressDTO.getBusinessId();
            List<BusiAddress> busiAddressList = this.list(new QueryWrapper<BusiAddress>().eq(BusiAddress.BUSINESS_ID, businessId).ne(BusiAddress.ID, id));
            busiAddressList.forEach(updateBusinessAddress -> updateBusinessAddress.setDef(DictConstant.Whether.no.getCode()));
            this.updateBatchById(busiAddressList);
        }

        return this.updateById(busiAddress);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public BusiAddress findById(Long id) {
        BusiAddress busiAddress = this.getById(id);
        if (ObjectUtil.isNull(busiAddress)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return busiAddress;
    }

    @Override
    public BusiAddress getDefaultByBusinessId(Long businessId) {
        List<BusiAddress> busiAddressList = this.list(new QueryWrapper<BusiAddress>().eq(BusiAddress.BUSINESS_ID, businessId).eq(BusiAddress.DEF, DictConstant.Whether.yes.getCode()));
        if(CollectionUtil.isNotEmpty(busiAddressList)) {
            return busiAddressList.get(0);
        }
        // 如果没配默认地址，就随便给他取最先创建的那个地址
        busiAddressList = this.list(new QueryWrapper<BusiAddress>().eq(BusiAddress.BUSINESS_ID, businessId).orderByAsc(BusiAddress.GMT_CREATE));
        if(CollectionUtil.isNotEmpty(busiAddressList)) {
            return busiAddressList.get(0);
        }
        throw new WeikbestException(StrUtil.format("商户:{}，未配置任何地址信息！", businessId));
    }

    @Override
    public List<BusiAddressVO> queryByBusinessId(Long businessId) {
        List<BusiAddress> busiAddressList = this.list(new QueryWrapper<BusiAddress>().eq(BusiAddress.BUSINESS_ID, businessId));
        if (CollectionUtil.isEmpty(busiAddressList)) {
            return new ArrayList<>();
        }
        return busiAddressList.stream().map(BusiAddressMapStruct.INSTANCE::converToVO).collect(Collectors.toList());
    }

    @Override
    public IPage<BusiAddress> queryPage(BusiAddressQO busiAddressQO, PageReq pageReq) {
        QueryWrapper<BusiAddress> wrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(busiAddressQO.getBusinessId())) {
            wrapper.eq(BusiAddress.BUSINESS_ID, busiAddressQO.getBusinessId());
        }
        if (StrUtil.isNotBlank(busiAddressQO.getName())) {
            wrapper.eq(BusiAddress.NAME, busiAddressQO.getName());
        }
        if (StrUtil.isNotBlank(busiAddressQO.getBusiPhone())) {
            wrapper.eq(BusiAddress.BUSI_PHONE, busiAddressQO.getBusiPhone());
        }
        if (StrUtil.isNotBlank(busiAddressQO.getBusiAreaCode())) {
            wrapper.eq(BusiAddress.BUSI_AREA_CODE, busiAddressQO.getBusiAreaCode());
        }
        if (StrUtil.isNotBlank(busiAddressQO.getBusiLandlineNumber())) {
            wrapper.eq(BusiAddress.BUSI_LANDLINE_NUMBER, busiAddressQO.getBusiLandlineNumber());
        }
        if (StrUtil.isNotBlank(busiAddressQO.getBusiExtensionNumber())) {
            wrapper.eq(BusiAddress.BUSI_EXTENSION_NUMBER, busiAddressQO.getBusiExtensionNumber());
        }
        if (StrUtil.isNotBlank(busiAddressQO.getBusiProvince())) {
            wrapper.eq(BusiAddress.BUSI_PROVINCE, busiAddressQO.getBusiProvince());
        }
        if (StrUtil.isNotBlank(busiAddressQO.getBusiCity())) {
            wrapper.eq(BusiAddress.BUSI_CITY, busiAddressQO.getBusiCity());
        }
        if (StrUtil.isNotBlank(busiAddressQO.getBusiDistrict())) {
            wrapper.eq(BusiAddress.BUSI_DISTRICT, busiAddressQO.getBusiDistrict());
        }
        if (StrUtil.isNotBlank(busiAddressQO.getAddr())) {
            wrapper.eq(BusiAddress.ADDR, busiAddressQO.getAddr());
        }
        if (StrUtil.isNotBlank(busiAddressQO.getDef())) {
            wrapper.eq(BusiAddress.DEF, busiAddressQO.getDef());
        }
        if (StrUtil.isNotBlank(busiAddressQO.getDescription())) {
            wrapper.eq(BusiAddress.DESCRIPTION, busiAddressQO.getDescription());
        }
        if (StrUtil.isNotBlank(busiAddressQO.getDataStatus())) {
            wrapper.eq(BusiAddress.DATA_STATUS, busiAddressQO.getDataStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
