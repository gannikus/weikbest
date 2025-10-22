package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.common.third.wx.miniapp.config.WxMaProperties;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.sys.param.entity.AppletConfig;
import com.weikbest.pro.saas.sys.param.module.dto.AppletConfigDTO;
import com.weikbest.pro.saas.sys.param.module.vo.AppletConfigVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 小程序配置表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-18
 */
@Mapper
public interface AppletConfigMapStruct extends BaseMapStruct {
    AppletConfigMapStruct INSTANCE = Mappers.getMapper(AppletConfigMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param appletConfig entity
     * @return dto
     */
    AppletConfigDTO converToDTO(AppletConfig appletConfig);

    /**
     * DTO转换为entity
     *
     * @param appletConfigDTO dto
     * @return entity
     */
    AppletConfig converToEntity(AppletConfigDTO appletConfigDTO);

    /**
     * entity转换为VO
     *
     * @param appletConfig entity
     * @return vo
     */
    AppletConfigVO converToVO(AppletConfig appletConfig);

    /**
     * VO转换为entity
     *
     * @param appletConfigVO vo
     * @return entity
     */
    AppletConfig converToEntity(AppletConfigVO appletConfigVO);

    /**
     * entity转换为WxMaConfig
     *
     * @param appletConfig entity
     * @return WxMaConfig
     */
    @Mapping(target = "appId", source = "wxMiniappAppId")
    @Mapping(target = "secret", source = "wxMiniappAppSecret")
    @Mapping(target = "token", source = "wxMiniappToken")
    @Mapping(target = "aesKey", source = "wxMiniappAesKey")
    @Mapping(target = "originalId", source = "wxMiniappOriginalId")
    @Mapping(target = "msgDataFormat", source = "wxMiniappMsgDataFormat", defaultValue = "JSON")
    @Mapping(target = "cloudEnv", source = "wxMiniappColudEnv")
    WxMaProperties converToWxMaProperties(AppletConfig appletConfig);

    /**
     * entity转换为DictEntry
     *
     * @param appletConfig entity
     * @return DictEntry
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "key", source = "wxMiniappAppId")
    @Mapping(target = "value", source = "appletName")
    @Mapping(target = "icon", ignore = true)
    @Mapping(target = "ord", ignore = true)
    @Mapping(target = "pid", ignore = true)
    DictEntry converToDictEntry(AppletConfig appletConfig);
}