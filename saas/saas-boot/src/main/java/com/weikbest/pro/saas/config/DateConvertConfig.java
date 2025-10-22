package com.weikbest.pro.saas.config;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 西瓜瓜
 * @project weikbest
 * @jdk 1.8
 * @since :2022/9/3
 * <p>
 * 全局handler前日期统一处理
 */
@Component
public class DateConvertConfig implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        if (StrUtil.isBlank(source)) {
            return null;
        }
        return DateUtil.parse(source);
    }
}
