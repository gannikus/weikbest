package com.weikbest.pro.saas.merchat.feight.util;

import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.feight.module.dto.FeightTemplateDTO;
import com.weikbest.pro.saas.merchat.feight.module.dto.FeightTemplateDetailDTO;
import com.weikbest.pro.saas.merchat.feight.module.dto.FeightTemplateRegionDTO;
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
 * @since 2022/10/4
 * <p>
 * 运费模板的JSR校验
 */
@Component
public class FeightTemplateJsrCheckUtil {

    @Resource
    protected Validator validator;

    /**
     * 新增校验
     *
     * @param bindingResult
     */
    public void validFeightTemplateDTO(BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        FeightTemplateDTO feightTemplateDTO = (FeightTemplateDTO) bindingResult.getModel().get(bindingResult.getObjectName());

        List<FeightTemplateDetailDTO> feightTemplateDetailDTOList = feightTemplateDTO.getFeightTemplateDetailDTOList();
        for (FeightTemplateDetailDTO feightTemplateDetailDTO : feightTemplateDetailDTOList) {
            JsrCheckUtil.validateWithException(validator, feightTemplateDetailDTO);

            List<FeightTemplateRegionDTO> feightTemplateRegionDTOList = feightTemplateDetailDTO.getFeightTemplateRegionDTOList();
            for (FeightTemplateRegionDTO feightTemplateRegionDTO : feightTemplateRegionDTOList) {
                JsrCheckUtil.validateWithException(validator, feightTemplateRegionDTO);
            }
        }
    }
}
