package com.weikbest.pro.saas.sysmerchat.coupon.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.coupon.module.dto.ShopRefluxCouponDTO;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sysmerchat.coupon.module.dto.PlatformCouponDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/11/12
 * 平台优惠券的JSR校验
 */
@Component
public class PlatformCouponJsrCheckUtil {

    @Resource
    protected Validator validator;

    /**
     * 平台优惠券新增或更新校验
     *
     * @param bindingResult
     */
    public void validPlatformCouponDTO(BindingResult bindingResult) {
        JsrCheckUtil.valid(bindingResult);

        PlatformCouponDTO platformCouponDTO = (PlatformCouponDTO) bindingResult.getModel().get(bindingResult.getObjectName());

        // 优惠金额不能为0
        BigDecimal discountAmount = platformCouponDTO.getDiscountAmount();
        if(discountAmount.compareTo(BigDecimal.ZERO) == WeikbestConstant.ZERO_INT) {
            throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), "优惠金额不能为0");
        }

        // 商品校验
        if(StrUtil.equals(platformCouponDTO.getCouponProdType(), DictConstant.CouponProdType.special_prod.getCode())) {
            if(CollectionUtil.isEmpty(platformCouponDTO.getProdIdList())) {
                throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), "指定商品ID集合不为空！");
            }
        }

    }
}
