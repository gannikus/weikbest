package com.weikbest.pro.saas.common.annotation.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wisdomelon
 * @date 2019/6/23 0023
 * @project saas
 * @jdk 1.8
 * 标记为添加或修改服务
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SaveOrUpdateLog {

    String type() default "SU";

    String value() default "";

    String recordSource() default "0";

}
