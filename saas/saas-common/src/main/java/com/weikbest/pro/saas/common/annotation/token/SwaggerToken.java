package com.weikbest.pro.saas.common.annotation.token;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wisdomelon
 * @date 2019/6/13
 * @project saas
 * @jdk 1.8
 * 不使用token
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SwaggerToken {
    boolean required() default true;
}
