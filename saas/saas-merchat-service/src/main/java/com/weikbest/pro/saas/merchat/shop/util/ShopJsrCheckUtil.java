package com.weikbest.pro.saas.merchat.shop.util;

import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;
import javax.validation.Validator;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/1
 * <p>
 * 店铺的JSR校验
 */
@Component
public class ShopJsrCheckUtil {

    @Resource
    protected Validator validator;

    /**
     * 店铺新增或更新校验
     *
     * @param bindingResult
     */
    public void validShopDTO(BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        ShopDTO shopDTO = (ShopDTO) bindingResult.getModel().get(bindingResult.getObjectName());

        JsrCheckUtil.validateWithException(validator, shopDTO.getShopThirdDTO());
    }
}
