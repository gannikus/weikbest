package com.weikbest.pro.saas.merchat.coupon.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.coupon.delaytaskprocess.AppCouponRestrictTypeExpiredDelayTaskProcess;
import com.weikbest.pro.saas.merchat.coupon.entity.Coupon;
import com.weikbest.pro.saas.merchat.coupon.entity.CustCouponRestrict;
import com.weikbest.pro.saas.merchat.coupon.mapper.CustCouponRestrictMapper;
import com.weikbest.pro.saas.merchat.coupon.module.dto.AppCustCouponRestrictDTO;
import com.weikbest.pro.saas.merchat.coupon.module.dto.CustCouponRestrictDTO;
import com.weikbest.pro.saas.merchat.coupon.module.mapstruct.CustCouponRestrictMapStruct;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CustCouponRestrictQO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.CustCouponRestrictPageVO;
import com.weikbest.pro.saas.merchat.coupon.service.CouponService;
import com.weikbest.pro.saas.merchat.coupon.service.CustCouponRestrictService;
import com.weikbest.pro.saas.merchat.cust.module.vo.CustomerVO;
import com.weikbest.pro.saas.merchat.cust.service.CustomerService;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderInfoCustCouponRestrictVO;
import com.weikbest.pro.saas.merchat.order.service.OrderInfoService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 客户领用优惠券表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Slf4j
@Service
public class CustCouponRestrictServiceImpl extends ServiceImpl<CustCouponRestrictMapper, CustCouponRestrict> implements CustCouponRestrictService {

    @Resource
    private CustomerService customerService;

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private CouponService couponService;

    @Resource
    private AppCouponRestrictTypeExpiredDelayTaskProcess appCouponRestrictTypeExpiredDelayTaskProcess;


    @Override
    public boolean insert(CustCouponRestrictDTO custCouponRestrictDTO) {
        CustCouponRestrict custCouponRestrict = CustCouponRestrictMapStruct.INSTANCE.converToEntity(custCouponRestrictDTO);
        return this.save(custCouponRestrict);
    }

    @Override
    public boolean updateById(Long id, CustCouponRestrictDTO custCouponRestrictDTO) {
        CustCouponRestrict custCouponRestrict = this.findById(id);
        CustCouponRestrictMapStruct.INSTANCE.copyProperties(custCouponRestrictDTO, custCouponRestrict);
        custCouponRestrict.setId(id);
        return this.updateById(custCouponRestrict);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public CustCouponRestrict findById(Long id) {
        CustCouponRestrict custCouponRestrict = this.getById(id);
//        if (ObjectUtil.isNull(custCouponRestrict)) {
//            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
//        }
        return custCouponRestrict;
    }

    @Override
    public IPage<CustCouponRestrict> queryPage(CustCouponRestrictQO custCouponRestrictQO, PageReq pageReq) {
        QueryWrapper<CustCouponRestrict> wrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(custCouponRestrictQO.getCouponId())) {
            wrapper.eq(CustCouponRestrict.COUPON_ID, custCouponRestrictQO.getCouponId());
        }
        if (StrUtil.isNotBlank(custCouponRestrictQO.getCouponType())) {
            wrapper.eq(CustCouponRestrict.COUPON_TYPE, custCouponRestrictQO.getCouponType());
        }
        if (StrUtil.isNotBlank(custCouponRestrictQO.getRestrictType())) {
            wrapper.eq(CustCouponRestrict.RESTRICT_TYPE, custCouponRestrictQO.getRestrictType());
        }
        if (StrUtil.isNotBlank(custCouponRestrictQO.getRestrictUserPhone())) {
            wrapper.eq(CustCouponRestrict.RESTRICT_USER_PHONE, custCouponRestrictQO.getRestrictUserPhone());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public Map<Long, List<CustCouponRestrict>> queryGroupByCouponId(List<Long> couponIdList) {
        List<CustCouponRestrict> custCouponRestrictList = this.list(new QueryWrapper<CustCouponRestrict>().in(CustCouponRestrict.COUPON_ID, couponIdList).ne(CustCouponRestrict.RESTRICT_TYPE, ""));
        if (CollectionUtil.isEmpty(custCouponRestrictList)) {
            return new HashMap<>(1);
        }

        return custCouponRestrictList.stream().collect(Collectors.groupingBy(CustCouponRestrict::getCouponId));
    }

    @Override
    public Map<Long, List<CustCouponRestrict>> queryUseCouponGroupByCouponId(List<Long> couponIdList) {
        List<CustCouponRestrict> custCouponRestrictList = this.list(new QueryWrapper<CustCouponRestrict>()
                .in(CustCouponRestrict.COUPON_ID, couponIdList)
                .in(CustCouponRestrict.RESTRICT_TYPE, Lists.newArrayList(DictConstant.RestrictType.TYPE_20.getCode(), DictConstant.RestrictType.TYPE_25.getCode())));
        if (CollectionUtil.isEmpty(custCouponRestrictList)) {
            return new HashMap<>(1);
        }

        return custCouponRestrictList.stream().collect(Collectors.groupingBy(CustCouponRestrict::getCouponId));
    }

    @Override
    public IPage<CustCouponRestrictPageVO> queryVOPage(CustCouponRestrictQO custCouponRestrictQO, PageReq pageReq) {
        QueryWrapper<CustCouponRestrict> wrapper = new QueryWrapper<>();
        wrapper.eq(CustCouponRestrict.COUPON_ID, custCouponRestrictQO.getCouponId());
        wrapper.eq(CustCouponRestrict.COUPON_TYPE, custCouponRestrictQO.getCouponType());
        wrapper.ne(CustCouponRestrict.RESTRICT_TYPE, "");
        Page<CustCouponRestrict> page = this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);

        List<CustCouponRestrict> records = page.getRecords();

        // 查询客户信息
        List<Long> customerIdList = records.stream().map(CustCouponRestrict::getCustomerId).collect(Collectors.toList());
        Map<Long, CustomerVO> customerVOMap = customerService.queryVOMapByIdList(customerIdList);

        // 查询客户关联的订单信息
        Map<Long, OrderInfoCustCouponRestrictVO> orderInfoCustCouponRestrictVOMap = orderInfoService.queryCustCouponRestrictVOCustomerIdMapByCouponIdAndCustomerIdList(custCouponRestrictQO.getCouponId(), customerIdList);

        return page.convert(custCouponRestrict -> {
            CustCouponRestrictPageVO custCouponRestrictPageVO = CustCouponRestrictMapStruct.INSTANCE.converToPageVO(custCouponRestrict);
            Long customerId = custCouponRestrictPageVO.getCustomerId();
            custCouponRestrictPageVO.setCustomerVO(customerVOMap.get(customerId));
            custCouponRestrictPageVO.setOrderInfoCustCouponRestrictVO(orderInfoCustCouponRestrictVOMap.get(customerId));
            return custCouponRestrictPageVO;
        });
    }

    @Override
    public int queryCountUseableCustCouponRestricts(Long customerId) {
        return this.baseMapper.queryCountUseableCustCouponRestricts(customerId);
    }

    @Override
    public int queryCountExpiredCustCouponRestricts(Long customerId) {
        return this.baseMapper.queryCountExpiredCustCouponRestricts(customerId);
    }

    @Override
    public int queryCountUsedCustCouponRestricts(Long customerId) {
        return this.baseMapper.queryCountUsedCustCouponRestricts(customerId);
    }

    @Override
    public int queryCountUseableCustCouponProds(Map<String, Object> paramMap) {
        return this.baseMapper.queryCountUseableCustCouponProds(paramMap);
    }

    @Override
    public int queryCountUnuseableCustCouponProds(Map<String, Object> paramMap) {
        return this.baseMapper.queryCountUnuseableCustCouponProds(paramMap);
    }

    @Override
    public List<AppCustCouponRestrictDTO> queryUseableCustCouponRestricts(Map<String, Object> paramMap) {
        return this.baseMapper.queryUseableCustCouponRestricts(paramMap);
    }

    @Override
    public List<AppCustCouponRestrictDTO> queryExpiredCustCouponRestricts(Map<String, Object> paramMap) {
        return this.baseMapper.queryExpiredCustCouponRestricts(paramMap);
    }

    @Override
    public List<AppCustCouponRestrictDTO> queryUsedCustCouponRestricts(Map<String, Object> paramMap) {
        return this.baseMapper.queryUsedCustCouponRestricts(paramMap);
    }

    @Override
    public List<AppCustCouponRestrictDTO> queryUseableCustCouponProds(Map<String, Object> paramMap) {
        return this.baseMapper.queryUseableCustCouponProds(paramMap);
    }

    @Override
    public List<AppCustCouponRestrictDTO> listUseableCustCouponProds(Map<String, Object> paramMap) {
        return this.baseMapper.listUseableCustCouponProds(paramMap);
    }

    @Override
    public List<AppCustCouponRestrictDTO> queryUnuseableCustCouponProds(Map<String, Object> paramMap) {
        return this.baseMapper.queryUnuseableCustCouponProds(paramMap);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveCustCouponRestrictType(Long id){

        CustCouponRestrict custCouponRestrict = this.findById(id);
        Coupon coupon = couponService.queryCouponById(custCouponRestrict.getCouponId());

        if (DateUtil.compare(coupon.getUseStartTime(), DateUtil.date()) > WeikbestConstant.ZERO_INT) {
            //用券开始时间   大于  当前时间   领券状态设置为 1-未生效
            custCouponRestrict.setRestrictType(DictConstant.RestrictType.TYPE_1.getCode());
        }
        if (DateUtil.compare(DateUtil.date(), coupon.getUseStartTime()) >= WeikbestConstant.ZERO_INT
                && DateUtil.compare(coupon.getUseEndTime(), DateUtil.date()) >= WeikbestConstant.ZERO_INT) {
            //当前时间   大于  用券开始时间  并且  用券结束时间  大于  当前时间   领券状态设置为 5-未使用
            custCouponRestrict.setRestrictType(DictConstant.RestrictType.TYPE_5.getCode());
        }
        if (DateUtil.compare(DateUtil.date(), coupon.getUseEndTime()) > WeikbestConstant.ZERO_INT) {
            //当前时间   大于  用券结束时间   领券状态设置为 10-已过期
            custCouponRestrict.setRestrictType(DictConstant.RestrictType.TYPE_10.getCode());
        }

        boolean bool = this.updateById(custCouponRestrict);

        return bool;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void couponRestrictTypeNotUsedExecute(Long id){
        CustCouponRestrict custCouponRestrict = this.findById(id);
        Coupon coupon = couponService.queryCouponById(custCouponRestrict.getCouponId());
        if (StrUtil.equals(custCouponRestrict.getRestrictType(), DictConstant.RestrictType.TYPE_1.getCode())) {
            //状态变更为未使用
            custCouponRestrict.setRestrictType(DictConstant.RestrictType.TYPE_5.getCode());
            this.updateById(custCouponRestrict);
            //设置延时队列状态变更为已过期
            appCouponRestrictTypeExpiredDelayTaskProcess.putTask(id,coupon.getUseEndTime().getTime());
            return;
        }
        log.warn("优惠券领券状态：{}，状态不是未生效，跳过未使用处理任务...", id);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void couponRestrictTypeExpiredExecute(Long id){
        CustCouponRestrict custCouponRestrict = this.findById(id);
        if (StrUtil.equals(custCouponRestrict.getRestrictType(), DictConstant.RestrictType.TYPE_5.getCode())) {
            //状态变更为已过期
            custCouponRestrict.setRestrictType(DictConstant.RestrictType.TYPE_10.getCode());
            this.updateById(custCouponRestrict);
            return;
        }
        log.warn("优惠券领券状态：{}，状态不是未使用，跳过已过期处理任务...", id);
    }

}
