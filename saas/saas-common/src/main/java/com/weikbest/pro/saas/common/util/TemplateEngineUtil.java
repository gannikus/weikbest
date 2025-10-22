package com.weikbest.pro.saas.common.util;

import cn.hutool.core.lang.Dict;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 模板引擎
 *
 * @author wisdomelon
 * @date 2021/5/22
 * @project saas
 * @jdk 1.8
 */
@Slf4j
public class TemplateEngineUtil {

    public static final TemplateEngine ENGINE = TemplateUtil.createEngine(new TemplateConfig());

    /**
     * 解析模板
     *
     * @param templateContent
     * @param templateParamMap
     * @return
     */
    public static String renderTemplate(String templateContent, Map<String, Object> templateParamMap) {
        Template template = ENGINE.getTemplate(templateContent);
        return template.render(templateParamMap);
    }

    /**
     * 解析模板
     *
     * @param templateContent
     * @param dict
     * @return
     */
    public static String renderTemplate(String templateContent, Dict dict) {
        Template template = ENGINE.getTemplate(templateContent);
        return template.render(dict);
    }


    /**
     * 解析模板
     *
     * @param templateContent
     * @param param
     * @return
     */
    public static String renderTemplate(String templateContent, String... param) {
        Template template = ENGINE.getTemplate(templateContent);
        Dict dict = Dict.create();
        for (int i = 0; i < param.length; i++) {
            log.debug(param[i]);
            dict.set("p" + i, param[i]);
        }
        return template.render(dict);
    }

    public static void main(String[] args) {
        String templateContent = "{\"code\":\"${p0}\"}";
        String template = TemplateEngineUtil.renderTemplate(templateContent, "6379");
        System.out.println(template);
    }

}
