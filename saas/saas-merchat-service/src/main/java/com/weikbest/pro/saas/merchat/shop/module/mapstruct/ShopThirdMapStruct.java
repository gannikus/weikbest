package com.weikbest.pro.saas.merchat.shop.module.mapstruct;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.shop.entity.ShopThird;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopSetupDTO;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopThirdDTO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopThirdVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 店铺第三方平台拆分表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-17
 */
@Mapper
public interface ShopThirdMapStruct extends BaseMapStruct {
    ShopThirdMapStruct INSTANCE = Mappers.getMapper(ShopThirdMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param shopThird entity
     * @return dto
     */
    ShopThirdDTO converToDTO(ShopThird shopThird);

    /**
     * DTO转换为entity
     *
     * @param shopThirdDTO dto
     * @return entity
     */
    ShopThird converToEntity(ShopThirdDTO shopThirdDTO);

    /**
     * DTO转换为entity
     *
     * @param shopSetupDTO dto
     * @return entity
     */
    ShopThird converToEntity(ShopSetupDTO shopSetupDTO);

    /**
     * entity转换为VO
     *
     * @param shopThird entity
     * @return vo
     */
    ShopThirdVO converToVO(ShopThird shopThird);

    /**
     * VO转换为entity
     *
     * @param shopThirdVO vo
     * @return entity
     */
    ShopThird converToEntity(ShopThirdVO shopThirdVO);

    /**
     * entity转换为WxPayConfig
     *
     * @param shopThird entity
     * @return WxPayConfig
     */
    @Mapping(target = "mchId", source = "wxPayMchId")
    @Mapping(target = "mchKey", source = "wxPayMchKey")
    @Mapping(target = "keyPath", source = "wxPayKeyPath")
    @Mapping(target = "privateKeyPath", source = "wxPayPrivateKeyPath")
    @Mapping(target = "privateCertPath", source = "wxPayPrivateCertPath")
    @Mapping(target = "apiV3Key", source = "wxPayApiV3Key")
    @Mapping(target = "certSerialNo", source = "wxPayApiCertSerialNo")
    WxPayConfig converToWxPayConfig(ShopThird shopThird);
}