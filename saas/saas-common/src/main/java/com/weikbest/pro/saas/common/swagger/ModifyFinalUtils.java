package com.weikbest.pro.saas.common.swagger;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author wisdomelon
 * @date 2021/5/24
 * @project saas
 * @jdk 1.8
 */
public class ModifyFinalUtils {
    public static void modify(Object object, String fieldName, Object newFieldValue) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        //Field 的 modifiers 是私有的
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        if (!field.isAccessible()) {
            field.setAccessible(true);
        }

        field.set(object, newFieldValue);
    }
}