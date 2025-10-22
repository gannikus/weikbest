package com.weikbest.pro.saas.merchat.category.util;

import cn.hutool.core.collection.CollectionUtil;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletDecConfigDTO;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletDecConfigEntryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;
import javax.validation.Validator;
import java.util.List;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/2
 */
@Component
public class AppletDecConfigJsrCheckUtil {

    @Resource
    protected Validator validator;

    /**
     * 小程序装修页新增或更新校验
     *
     * @param bindingResult
     */
    public void validAppletDecConfigDTO(BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        AppletDecConfigDTO appletDecConfigDTO = (AppletDecConfigDTO) bindingResult.getModel().get(bindingResult.getObjectName());

        List<AppletDecConfigEntryDTO> appletDecConfigEntryDTOList = appletDecConfigDTO.getAppletDecConfigEntryDTOList();
        if (CollectionUtil.isNotEmpty(appletDecConfigEntryDTOList)) {
            for (AppletDecConfigEntryDTO appletDecConfigEntryDTO : appletDecConfigEntryDTOList) {
                JsrCheckUtil.validateWithException(validator, appletDecConfigEntryDTO);
            }
        }

    }
}
