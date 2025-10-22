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
import com.weikbest.pro.saas.merchat.prod.entity.ProdCoupon;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdCouponMapper;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdCouponDTO;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdCouponMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdCouponQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdCouponVO;
import com.weikbest.pro.saas.merchat.prod.service.ProdCouponService;
import com.weikbest.pro.saas.merchat.prod.service.ProdCouponRelateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 广告商品优惠劵拆分表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Service
public class ProdCouponServiceImpl extends ServiceImpl<ProdCouponMapper, ProdCoupon> implements ProdCouponService {

    @Resource
    private ProdCouponRelateService prodCouponRelateService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(Long prodId, ProdCouponDTO prodCouponDTO) {
        ProdCoupon prodCoupon = ProdCouponMapStruct.INSTANCE.converToEntity(prodCouponDTO);
        prodCoupon.setId(prodId);
        prodCoupon.setCallbackProportion(ObjectUtil.isEmpty(prodCouponDTO.getCallbackProportion())? BigDecimal.valueOf(100):prodCouponDTO.getCallbackProportion());
        boolean save = this.save(prodCoupon);
        // 更新优惠券关联表
        prodCouponRelateService.updateProdCouponRelate(prodId, prodCoupon.getEntryId(), prodCouponDTO);
        return save;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(Long prodId, ProdCouponDTO prodCouponDTO) {
        ProdCoupon prodCoupon = this.findByProdIdAndType(prodId, prodCouponDTO.getShopCouponType());
        ProdCouponMapStruct.INSTANCE.copyProperties(prodCouponDTO, prodCoupon);
        prodCoupon.setId(prodId);

        boolean update = this.updateById(prodCoupon);
        // 更新优惠券关联表
        prodCouponRelateService.updateProdCouponRelate(prodId, prodCoupon.getEntryId(), prodCouponDTO);
        return update;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrUpdateProdCouponDTO(Long prodId, ProdCouponDTO prodCouponDTO) {
        ProdCoupon prodCoupon = this.getOne(new QueryWrapper<ProdCoupon>().eq(ProdCoupon.ID, prodId).eq(ProdCoupon.SHOP_COUPON_TYPE, prodCouponDTO.getShopCouponType()));
        if (ObjectUtil.isEmpty(prodCoupon)) {
            // 新增
            return this.insert(prodId, prodCouponDTO);
        }
        // 更新
        return this.updateById(prodId, prodCouponDTO);
    }

    @Override
    public ProdCoupon findByProdIdAndType(Long prodId, String type) {
        return Optional.ofNullable(this.getOne(new QueryWrapper<ProdCoupon>().eq(ProdCoupon.ID, prodId).eq(ProdCoupon.SHOP_COUPON_TYPE, type))).orElseGet(ProdCoupon::new);
    }

    @Override
    public ProdCoupon findById(Long id) {
        ProdCoupon prodCoupon = this.getById(id);
        if (ObjectUtil.isNull(prodCoupon)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return prodCoupon;
    }

    @Override
    public IPage<ProdCoupon> queryPage(ProdCouponQO prodCouponQO, PageReq pageReq) {
        QueryWrapper<ProdCoupon> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(prodCouponQO.getShopCouponType())) {
            wrapper.eq(ProdCoupon.SHOP_COUPON_TYPE, prodCouponQO.getShopCouponType());
        }
        if (StrUtil.isNotBlank(prodCouponQO.getIsOpenCoupon())) {
            wrapper.eq(ProdCoupon.IS_OPEN_COUPON, prodCouponQO.getIsOpenCoupon());
        }
        if (StrUtil.isNotBlank(prodCouponQO.getChargeOff())) {
            wrapper.eq(ProdCoupon.CHARGE_OFF, prodCouponQO.getChargeOff());
        }
        if (StrUtil.isNotBlank(prodCouponQO.getCouponOpenImg())) {
            wrapper.eq(ProdCoupon.COUPON_OPEN_IMG, prodCouponQO.getCouponOpenImg());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public List<ProdCouponVO> queryProdCouponVOById(Long prodId) {
        List<ProdCoupon> prodCouponList = this.list(new QueryWrapper<ProdCoupon>().eq(ProdCoupon.ID, prodId));
        if (CollectionUtil.isEmpty(prodCouponList)) {
            return new ArrayList<>();
        }

        return prodCouponList.stream().map(prodCoupon -> {
            ProdCouponVO prodCouponVO = ProdCouponMapStruct.INSTANCE.converToVO(prodCoupon);
            // 查询商品关联优惠券数据字典数据
            List<DictEntry> prodCouponRelateVOList = prodCouponRelateService.queryDictEntry(prodId, prodCoupon.getShopCouponType());
            prodCouponVO.setDictEntryList(prodCouponRelateVOList);
            return prodCouponVO;
        }).collect(Collectors.toList());
    }
}
