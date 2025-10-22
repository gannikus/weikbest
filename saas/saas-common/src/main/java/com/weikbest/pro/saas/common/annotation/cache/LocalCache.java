package com.weikbest.pro.saas.common.annotation.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wisdomelon
 * @date 2019/6/23 0023
 * @project saas
 * @jdk 1.8
 * 本地缓存注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalCache {

    String name() default "";
}
