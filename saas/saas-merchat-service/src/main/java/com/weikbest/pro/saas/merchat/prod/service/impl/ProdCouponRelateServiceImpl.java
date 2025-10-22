package com.weikbest.pro.saas.merchat.prod.service.impl;

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
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.merchat.coupon.entity.Coupon;
import com.weikbest.pro.saas.merchat.coupon.module.mapstruct.CouponMapStruct;
import com.weikbest.pro.saas.merchat.coupon.service.CouponService;
import com.weikbest.pro.saas.merchat.prod.entity.ProdCouponRelate;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdCouponRelateMapper;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdCouponDTO;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdCouponRelateDTO;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdCouponRelateMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdCouponRelateQO;
import com.weikbest.pro.saas.merchat.prod.service.ProdCouponRelateService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品与优惠券绑定拆分表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ProdCouponRelateServiceImpl extends ServiceImpl<ProdCouponRelateMapper, ProdCouponRelate> implements ProdCouponRelateService {

    @Resource
    private CouponService couponService;

    @Override
    public boolean insert(ProdCouponRelateDTO prodCouponRelateDTO) {
        ProdCouponRelate prodCouponRelate = ProdCouponRelateMapStruct.INSTANCE.converToEntity(prodCouponRelateDTO);
        return this.save(prodCouponRelate);
    }

    @Override
    public boolean updateById(Long entryId, ProdCouponRelateDTO prodCouponRelateDTO) {
        ProdCouponRelate prodCouponRelate = this.findById(entryId);
        ProdCouponRelateMapStruct.INSTANCE.copyProperties(prodCouponRelateDTO, prodCouponRelate);
        prodCouponRelate.setEntryId(entryId);
        return this.updateById(prodCouponRelate);
    }

    @Override
    public ProdCouponRelate findById(Long entryId) {
        ProdCouponRelate prodCouponRelate = this.getById(entryId);
        if (ObjectUtil.isNull(prodCouponRelate)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return prodCouponRelate;
    }

    @Override
    public IPage<ProdCouponRelate> queryPage(ProdCouponRelateQO prodCouponRelateQO, PageReq pageReq) {
        QueryWrapper<ProdCouponRelate> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(prodCouponRelateQO.getCouponType())) {
            wrapper.eq(ProdCouponRelate.COUPON_TYPE, prodCouponRelateQO.getCouponType());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

//    @Override
//    public void insertBatchWithProd(Long prodId, List<ProdCouponRelateDTO> prodCouponRelateDTOList) {
//        if (CollectionUtil.isEmpty(prodCouponRelateDTOList)) {
//            throw new WeikbestException("商品关联优惠券信息为空，请检查数据！");
//        }
//
//        // 转换实体
//        List<ProdCouponRelate> prodCouponRelateList = prodCouponRelateDTOList.stream()
//                .map(prodCouponRelateDTO -> ProdCouponRelateMapStruct.INSTANCE.converToEntity(prodCouponRelateDTO, prodId))
//                .collect(Collectors.toList());
//        this.saveBatch(prodCouponRelateList);
//    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<DictEntry> queryDictEntry(Long prodId, String couponType) {
        // 查询未过期的优惠券
        List<ProdCouponRelate> prodCouponRelateList = this.list(new QueryWrapper<ProdCouponRelate>().eq(ProdCouponRelate.ID, prodId)
                .eq(ProdCouponRelate.COUPON_TYPE, couponType)
                .eq(ProdCouponRelate.IS_EXPIRED, DictConstant.Whether.no.getCode()));
        if (CollectionUtil.isEmpty(prodCouponRelateList)) {
            return new ArrayList<>();
        }

        // 查询优惠券信息，区分已过期的和未过期的
        Map<String, List<Coupon>> couponListMap = couponService.queryHasExpiredCoupon(prodCouponRelateList.stream().map(ProdCouponRelate::getCouponId).collect(Collectors.toList()));

        // 存在已过期的优惠券？
        List<Coupon> expiredCouponList = couponListMap.get(DictConstant.Whether.yes.getCode());
        if (CollectionUtil.isNotEmpty(expiredCouponList)) {
            // 更新数据集
            List<ProdCouponRelate> updateProdCouponList = this.list(new QueryWrapper<ProdCouponRelate>().eq(ProdCouponRelate.ID, prodId)
                    .eq(ProdCouponRelate.COUPON_TYPE, couponType)
                    .in(ProdCouponRelate.COUPON_ID, expiredCouponList.stream().map(Coupon::getId).collect(Collectors.toList())));
            updateProdCouponList.forEach(prodCouponRelate -> prodCouponRelate.setIsExpired(DictConstant.Whether.yes.getCode()));
            this.updateBatchById(updateProdCouponList);
        }

        // 只返回未过期的
        List<Coupon> couponList = couponListMap.get(DictConstant.Whether.no.getCode());
        if (CollectionUtil.isEmpty(couponList)) {
            return new ArrayList<>();
        }

        return couponList.stream().map(CouponMapStruct.INSTANCE::converToDictEntry).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateProdCouponRelate(Long prodId, Long entryId, ProdCouponDTO prodCouponDTO) {
        this.remove(new QueryWrapper<ProdCouponRelate>().eq(ProdCouponRelate.ID, prodId).eq(ProdCouponRelate.COUPON_TYPE, prodCouponDTO.getShopCouponType()));
        List<ProdCouponRelateDTO> prodCouponRelateDTOList = prodCouponDTO.getProdCouponRelateDTOList();
        if (CollectionUtil.isNotEmpty(prodCouponRelateDTOList)) {
            List<ProdCouponRelate> prodCouponRelateList = prodCouponRelateDTOList.stream().map(prodCouponRelateDTO -> {
                ProdCouponRelate prodCouponRelate = ProdCouponRelateMapStruct.INSTANCE.converToEntity(prodCouponRelateDTO);
                prodCouponRelate.setId(prodId);
                prodCouponRelate.setEntryId(entryId);
                return prodCouponRelate;
            }).collect(Collectors.toList());
            this.saveBatch(prodCouponRelateList);
        }
    }
}
