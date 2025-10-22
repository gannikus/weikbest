package com.weikbest.pro.saas.common.annotation.token;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wisdomelon
 * @date 2019/8/9 0009
 * @project saas
 * @jdk 1.8
 * APP端使用Token
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AppToken {
    boolean required() default true;
}
