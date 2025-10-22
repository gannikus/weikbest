package com.weikbest.pro.saas.common.util;

import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wisdomelon
 * @date 2020/6/22 0022
 * @project saas
 * @jdk 1.8
 */
public class JsrCheckUtil {

    /**
     * JSR303验证
     *
     * @param bindingResult
     */
    public static void valid(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errorList = bindingResult.getAllErrors();
            List<String> errorMessage = errorList.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

            throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), errorMessage.toString());
        }
    }

    /**
     * 服务端参数有效性验证
     *
     * @param object 验证的实体对象
     * @param groups 验证组
     * @return 验证成功：返回true；验证失败：将错误信息添加到message中
     */
    @SuppressWarnings("rawtypes")
    public static void validateWithException(Validator validator, Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation constraintViolation : constraintViolations) {
                errorMessage.append(constraintViolation.getMessage());
            }
            throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), errorMessage.toString());
        }
    }
}
