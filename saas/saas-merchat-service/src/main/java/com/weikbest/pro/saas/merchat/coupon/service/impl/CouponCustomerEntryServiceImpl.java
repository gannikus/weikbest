package com.weikbest.pro.saas.merchat.coupon.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.coupon.entity.CouponCustomerEntry;
import com.weikbest.pro.saas.merchat.coupon.mapper.CouponCustomerEntryMapper;
import com.weikbest.pro.saas.merchat.coupon.module.dto.CouponCustomerEntryDTO;
import com.weikbest.pro.saas.merchat.coupon.module.mapstruct.CouponCustomerEntryMapStruct;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CouponCustomerEntryQO;
import com.weikbest.pro.saas.merchat.coupon.service.CouponCustomerEntryService;
import com.weikbest.pro.saas.merchat.cust.entity.Customer;
import com.weikbest.pro.saas.merchat.cust.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 优惠券与适用客户拆分表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Service
public class CouponCustomerEntryServiceImpl extends ServiceImpl<CouponCustomerEntryMapper, CouponCustomerEntry> implements CouponCustomerEntryService {

    @Resource
    private CustomerService customerService;

    @Override
    public boolean insert(CouponCustomerEntryDTO couponCustomerEntryDTO) {
        CouponCustomerEntry couponCustomerEntry = CouponCustomerEntryMapStruct.INSTANCE.converToEntity(couponCustomerEntryDTO);
        return this.save(couponCustomerEntry);
    }

    @Override
    public boolean updateById(Long id, CouponCustomerEntryDTO couponCustomerEntryDTO) {
        CouponCustomerEntry couponCustomerEntry = this.findById(id);
        CouponCustomerEntryMapStruct.INSTANCE.copyProperties(couponCustomerEntryDTO, couponCustomerEntry);
        couponCustomerEntry.setId(id);
        return this.updateById(couponCustomerEntry);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public CouponCustomerEntry findById(Long id) {
        CouponCustomerEntry couponCustomerEntry = this.getById(id);
        if (ObjectUtil.isNull(couponCustomerEntry)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return couponCustomerEntry;
    }

    @Override
    public IPage<CouponCustomerEntry> queryPage(CouponCustomerEntryQO couponCustomerEntryQO, PageReq pageReq) {
        QueryWrapper<CouponCustomerEntry> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(couponCustomerEntryQO.getCouponType())) {
            wrapper.eq(CouponCustomerEntry.COUPON_TYPE, couponCustomerEntryQO.getCouponType());
        }
        if (StrUtil.isNotBlank(couponCustomerEntryQO.getRestrictUserPhone())) {
            wrapper.eq(CouponCustomerEntry.RESTRICT_USER_PHONE, couponCustomerEntryQO.getRestrictUserPhone());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveBatchWithCouponId(Long id, String shopCouponType, List<String> customerPhoneList) {
        // 先删除
        this.remove(new QueryWrapper<CouponCustomerEntry>().eq(CouponCustomerEntry.ID, id));

        // 在新增
        List<Customer> customerList = customerService.list(new QueryWrapper<Customer>().in(Customer.PHONE, customerPhoneList));
        List<CouponCustomerEntry> couponCustomerEntryList = customerList.stream().map(customer -> {
            CouponCustomerEntry couponCustomerEntry = new CouponCustomerEntry();
            couponCustomerEntry.setCustomerId(customer.getId())
                    .setRestrictUserPhone(customer.getPhone())
                    .setCouponType(shopCouponType)
                    .setId(id);
            return couponCustomerEntry;
        }).collect(Collectors.toList());

        this.saveBatch(couponCustomerEntryList);
    }

    @Override
    public List<CouponCustomerEntry> queryByCouponId(Long id) {
        return this.list(new QueryWrapper<CouponCustomerEntry>().eq(CouponCustomerEntry.ID, id));
    }
}
