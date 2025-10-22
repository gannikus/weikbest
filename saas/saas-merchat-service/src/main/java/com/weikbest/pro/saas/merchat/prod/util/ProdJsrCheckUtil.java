package com.weikbest.pro.saas.merchat.prod.util;

import cn.hutool.core.collection.CollectionUtil;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.prod.module.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;
import javax.validation.Validator;
import java.util.List;

/**
 * @author 西瓜瓜
 * @project weikbest
 * @jdk 1.8
 * @since :2022/9/12
 * <p>
 * 商品的JSR校验
 */
@Component
public class ProdJsrCheckUtil {

    @Resource
    protected Validator validator;

    /**
     * 商品新增或更新校验
     *
     * @param bindingResult
     */
    public void validProdDTO(BindingResult bindingResult) {
        JsrCheckUtil.valid(bindingResult);

        ProdDTO prodDTO = (ProdDTO) bindingResult.getModel().get(bindingResult.getObjectName());

        // 解析对象后校验
        for (ProdComboDTO prodComboDTO : prodDTO.getProdComboDTOList()) {
            JsrCheckUtil.validateWithException(validator, prodComboDTO);

            List<ProdComboAttrRelateDTO> prodComboAttrRelateDTOList = prodComboDTO.getProdComboAttrRelateDTOList();
            if (CollectionUtil.isNotEmpty(prodComboAttrRelateDTOList)){
                for (ProdComboAttrRelateDTO prodComboAttrRelateDTO : prodComboAttrRelateDTOList) {
                    JsrCheckUtil.validateWithException(validator, prodComboAttrRelateDTO);
                }
            }
        }

        JsrCheckUtil.validateWithException(validator, prodDTO.getProdPriceDTO());
        JsrCheckUtil.validateWithException(validator, prodDTO.getProdFeightDTO());
        JsrCheckUtil.validateWithException(validator, prodDTO.getProdThemeDTO());

        for (ProdDetailDTO prodDetailDTO : prodDTO.getProdDetailDTOList()) {
            JsrCheckUtil.validateWithException(validator, prodDetailDTO);
        }
        for (ProdMainimgDTO prodMainimgDTO : prodDTO.getProdMainimgDTOList()) {
            JsrCheckUtil.validateWithException(validator, prodMainimgDTO);
        }

//        JsrCheckUtil.validateWithException(validator, prodDTO.getProdAdvBackDTO());
//        for (ProdAdvBackAccountDTO prodAdvBackAccountDTO : prodDTO.getProdAdvBackAccountDTOList()) {
//            JsrCheckUtil.validateWithException(validator, prodAdvBackAccountDTO);
//        }
//        for (ProdJumpLinkDTO prodJumpLinkDTO : prodDTO.getProdJumpLinkDTOList()) {
//            JsrCheckUtil.validateWithException(validator, prodJumpLinkDTO);
//        }
//        for (ProdAppletRelateDTO prodAppletRelateDTO : prodDTO.getProdAppletRelateDTOList()) {
//            JsrCheckUtil.validateWithException(validator, prodAppletRelateDTO);
//        }
//        for (ProdCouponRelateDTO prodCouponRelateDTO : prodDTO.getProdCouponRelateDTOList()) {
//            JsrCheckUtil.validateWithException(validator, prodCouponRelateDTO);
//        }
    }


    /***
     * 商品装修页信息新增或更新校验
     *
     * @param bindingResult
     */
    public void validProdDecFloorDTO(BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        ProdDecFloorDTO prodDecFloorDTO = (ProdDecFloorDTO) bindingResult.getModel().get(bindingResult.getObjectName());

        // 解析对象后校验
        for (ProdAdvBackAccountDTO prodAdvBackAccountDTO : prodDecFloorDTO.getProdAdvBackAccountDTOList()) {
            JsrCheckUtil.validateWithException(validator, prodAdvBackAccountDTO);
        }
    }

    /**
     * 商品左滑信息新增或更新校验
     *
     * @param bindingResult
     */
    public void validProdLeftSlideDTO(BindingResult bindingResult) {
        JsrCheckUtil.valid(bindingResult);

        ProdLeftSlideDTO prodLeftSlideDTO = (ProdLeftSlideDTO) bindingResult.getModel().get(bindingResult.getObjectName());

        // 解析对象后校验
        for (ProdJumpLinkDTO prodJumpLinkDTO : prodLeftSlideDTO.getProdJumpLinkDTOList()) {
            JsrCheckUtil.validateWithException(validator, prodJumpLinkDTO);
        }
    }

    /**
     * 商品优惠券信息更新校验
     *
     * @param bindingResult
     */
    public void validProdCouponDTO(BindingResult bindingResult) {
        JsrCheckUtil.valid(bindingResult);

        ProdCouponDTO prodCouponDTO = (ProdCouponDTO) bindingResult.getModel().get(bindingResult.getObjectName());

        // 解析对象后校验
        for (ProdCouponRelateDTO prodCouponRelateDTO : prodCouponDTO.getProdCouponRelateDTOList()) {
            JsrCheckUtil.validateWithException(validator, prodCouponRelateDTO);
        }
    }
}
