package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.param.entity.PayConfig;
import com.weikbest.pro.saas.sys.param.module.dto.PayConfigDTO;
import com.weikbest.pro.saas.sys.param.module.vo.PayConfigVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统支付商户号配置表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-14
 */
@Mapper
public interface PayConfigMapStruct extends BaseMapStruct {
    PayConfigMapStruct INSTANCE = Mappers.getMapper(PayConfigMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param payConfig entity
     * @return dto
     */
    PayConfigDTO converToDTO(PayConfig payConfig);

    /**
     * DTO转换为entity
     *
     * @param payConfigDTO dto
     * @return entity
     */
    PayConfig converToEntity(PayConfigDTO payConfigDTO);

    /**
     * entity转换为VO
     *
     * @param payConfig entity
     * @return vo
     */
    PayConfigVO converToVO(PayConfig payConfig);

    /**
     * VO转换为entity
     *
     * @param payConfigVO vo
     * @return entity
     */
    PayConfig converToEntity(PayConfigVO payConfigVO);

    /**
     * entity转微信支付配置
     *
     * @param payConfig
     * @return 微信支付配置
     */
    @Mapping(target = "mchId", source = "wxPayMchId")
    @Mapping(target = "mchKey", source = "wxPayMchKey")
    @Mapping(target = "keyPath", source = "wxPayKeyPath")
    @Mapping(target = "privateKeyPath", source = "wxPayPrivateKeyPath")
    @Mapping(target = "privateCertPath", source = "wxPayPrivateCertPath")
    @Mapping(target = "apiV3Key", source = "wxPayApiV3Key")
    @Mapping(target = "certSerialNo", source = "wxPayApiCertSerialNo")
    WxPayConfig converToWxPayConfig(PayConfig payConfig);
}