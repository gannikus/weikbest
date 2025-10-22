package com.weikbest.pro.saas.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.google.common.base.Predicates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 默认访问地址  http://ip:host/swagger-ui.html
 * layui皮肤访问地址  http://ip:host/docs.html
 * bootstrap-ui皮肤访问地址  http://ip:host/doc.html
 *
 * @author wisdomelon
 * @date 2020/4/4 0004
 * @project saas
 * @jdk 1.8
 */
@Slf4j
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig implements WebMvcConfigurer {

    @Value("${swagger.enable}")
    private boolean enableSwagger;

    /**
     * 显示swagger-ui.html文档展示页，还必须注入swagger资源：
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket webApiSysConfig() {
        return getDocket("webApi-sys", "com.weikbest.pro.saas.sys", webApiInfo());
    }

    @Bean
    public Docket webApiSysMerchatConfig() {
        return getDocket("webApi-sysmerchat", "com.weikbest.pro.saas.sysmerchat", webApiInfo());
    }

    @Bean
    public Docket webApiMeachatConfig() {
        return getDocket("webApi-merchat", "com.weikbest.pro.saas.merchat", webApiInfo());
    }

    @Bean
    public Docket webApiAppletConfig() {
        return getDocket("webApi-applet", "com.weikbest.pro.saas.applet", webApiInfo());
    }

    private Docket getDocket(String groupName, String basePackage, ApiInfo apiInfo) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .apiInfo(apiInfo)
                .enable(enableSwagger)
                .select()
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .build();
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("saas商城-API文档")
                .description("本文档描述了saas商城服务接口定义")
                .version("1.0")
                .contact(new Contact("Wisdomelon", "http://www.wisdomelon.com/", "wisdomelon@vip.qq.com"))
                .build();
    }
}