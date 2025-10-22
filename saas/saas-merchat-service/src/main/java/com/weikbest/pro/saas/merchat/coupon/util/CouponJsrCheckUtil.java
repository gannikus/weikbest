package com.weikbest.pro.saas.merchat.coupon.util;

import cn.hutool.core.date.DateUtil;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.coupon.module.dto.ShopPromptlyCouponDTO;
import com.weikbest.pro.saas.merchat.coupon.module.dto.ShopRefluxCouponDTO;
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
 * 优惠券的JSR校验
 */
@Component
public class CouponJsrCheckUtil {

    @Resource
    protected Validator validator;

    /**
     * 回流优惠券新增或更新校验
     *
     * @param bindingResult
     */
    public void validShopRefluxCouponDTO(BindingResult bindingResult) {
        JsrCheckUtil.valid(bindingResult);

        ShopRefluxCouponDTO shopRefluxCouponDTO = (ShopRefluxCouponDTO) bindingResult.getModel().get(bindingResult.getObjectName());

        // 优惠金额不能为0
        BigDecimal discountAmount = shopRefluxCouponDTO.getDiscountAmount();
        if (discountAmount.compareTo(BigDecimal.ZERO) == WeikbestConstant.ZERO_INT) {
            throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), "优惠金额不能为0");
        }

        // 活动开始时间
        Date eventStartTime = shopRefluxCouponDTO.getEventStartTime();
        // 活动结束时间
        Date eventEndTime = shopRefluxCouponDTO.getEventEndTime();
        // 领券开始时间
        Date getStartTime = shopRefluxCouponDTO.getGetStartTime();
        // 领券结束时间
        Date getEndTime = shopRefluxCouponDTO.getGetEndTime();
        //1. 活动开始时间必须早于领券开始时间和用券开始时间
        if (DateUtil.compare(eventStartTime, getStartTime) > WeikbestConstant.ZERO_INT) {
            throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), "活动开始时间必须早于领券开始时间");
        }
        //2. 领券开始时间必须早于使用开始时间
        //3. 活动结束时间必须晚于领券结束时间和用券结束时间
        if (DateUtil.compare(eventEndTime, getEndTime) < WeikbestConstant.ZERO_INT) {
            throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), "活动结束时间必须晚于领券结束时间");
        }
        //4.  用券结束时间必须晚于领券结束时间

    }

    /**
     * 立减优惠券新增或更新校验
     *
     * @param bindingResult
     */
    public void validShopPromptlyCouponDTO(BindingResult bindingResult) {
        JsrCheckUtil.valid(bindingResult);

        ShopPromptlyCouponDTO shopPromptlyCouponDTO = (ShopPromptlyCouponDTO) bindingResult.getModel().get(bindingResult.getObjectName());

        // 优惠金额不能为0
        BigDecimal discountAmount = shopPromptlyCouponDTO.getDiscountAmount();
        if (discountAmount.compareTo(BigDecimal.ZERO) == WeikbestConstant.ZERO_INT) {
            throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), "优惠金额不能为0");
        }

        // 活动开始时间
        Date eventStartTime = shopPromptlyCouponDTO.getEventStartTime();
        // 活动结束时间
        Date eventEndTime = shopPromptlyCouponDTO.getEventEndTime();
        // 领券开始时间
        Date getStartTime = shopPromptlyCouponDTO.getGetStartTime();
        // 领券结束时间
        Date getEndTime = shopPromptlyCouponDTO.getGetEndTime();
        //1. 活动开始时间必须早于领券开始时间和用券开始时间
        if (DateUtil.compare(eventStartTime, getStartTime) > WeikbestConstant.ZERO_INT) {
            throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), "活动开始时间必须早于领券开始时间");
        }
        //2. 领券开始时间必须早于使用开始时间
        //3. 活动结束时间必须晚于领券结束时间和用券结束时间
        if (DateUtil.compare(eventEndTime, getEndTime) < WeikbestConstant.ZERO_INT) {
            throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), "活动结束时间必须晚于领券结束时间");
        }
        //4.  用券结束时间必须晚于领券结束时间
    }
}
