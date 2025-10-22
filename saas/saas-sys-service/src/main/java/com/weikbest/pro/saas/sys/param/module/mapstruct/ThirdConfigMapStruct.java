package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.common.third.wx.miniapp.config.WxMaProperties;
import com.weikbest.pro.saas.common.third.wx.pay.config.WxPayProperties;
import com.weikbest.pro.saas.sys.param.entity.ThirdConfig;
import com.weikbest.pro.saas.sys.param.module.dto.ThirdConfigDTO;
import com.weikbest.pro.saas.sys.param.module.vo.ThirdConfigVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 第三方平台配置表  实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-17
 */
@Mapper
public interface ThirdConfigMapStruct extends BaseMapStruct {
    ThirdConfigMapStruct INSTANCE = Mappers.getMapper(ThirdConfigMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param thirdConfig entity
     * @return dto
     */
    ThirdConfigDTO converToDTO(ThirdConfig thirdConfig);

    /**
     * DTO转换为entity
     *
     * @param thirdConfigDTO dto
     * @return entity
     */
    ThirdConfig converToEntity(ThirdConfigDTO thirdConfigDTO);

    /**
     * entity转换为VO
     *
     * @param thirdConfig entity
     * @return vo
     */
    ThirdConfigVO converToVO(ThirdConfig thirdConfig);

    /**
     * VO转换为entity
     *
     * @param thirdConfigVO vo
     * @return entity
     */
    ThirdConfig converToEntity(ThirdConfigVO thirdConfigVO);

    /**
     * entity转换为WxPayProperties
     *
     * @param thirdConfig entity
     * @return dto
     */
    @Mapping(target = "appId", source = "wxPayAppId")
    @Mapping(target = "subAppId", source = "wxPaySubAppId")
    @Mapping(target = "mchId", source = "wxPayMchId")
    @Mapping(target = "mchKey", source = "wxPayMchKey")
    @Mapping(target = "entPayKey", source = "wxPayEntPayKey")
    @Mapping(target = "subMchId", source = "wxPaySubMchId")
    @Mapping(target = "notifyUrl", source = "wxPayNotifyUrl")
    @Mapping(target = "keyPath", source = "wxPayKeyPath")
    @Mapping(target = "privateKeyPath", source = "wxPayPrivateKeyPath")
    @Mapping(target = "privateCertPath", source = "wxPayPrivateCertPath")
    @Mapping(target = "apiV3Key", source = "wxPayApiV3Key")
    @Mapping(target = "certSerialNo", source = "wxPayApiCertSerialNo")
    @Mapping(target = "serviceId", source = "wxPayApiServiceId")
    @Mapping(target = "payScoreNotifyUrl", source = "wxPayPayScoreNotifyUrl")
    @Mapping(target = "payScorePermissionNotifyUrl", source = "wxPaypayScorePermissionNotifyUrl")
    WxPayProperties converToWxPayProperties(ThirdConfig thirdConfig);

    /**
     * entity转换为WxMaProperties
     *
     * @param thirdConfig entity
     * @return dto
     */
    @Mapping(target = "appId", source = "wxMiniappAppId")
    @Mapping(target = "secret", source = "wxMiniappAppSecret")
    @Mapping(target = "token", source = "wxMiniappToken")
    @Mapping(target = "aesKey", source = "wxMiniappAesKey")
    @Mapping(target = "originalId", source = "wxMiniappOriginalId")
    @Mapping(target = "msgDataFormat", source = "wxMiniappMsgDataFormat", defaultValue = "JSON")
    @Mapping(target = "cloudEnv", source = "wxMiniappColudEnv")
    WxMaProperties converToWxMaProperties(ThirdConfig thirdConfig);
}