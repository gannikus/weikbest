package com.weikbest.pro.saas.common.util;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean拷贝工具类
 * @author Mr.Wang
 */
public class BeanUtil {

    /**
     * 浅拷贝
     * @param source:来源数据
     * @param clazz:目标数据
     */
    public static <T> T copyProperties(Object source, Class<T> clazz){
        if (source == null) {
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BeanUtils.copyProperties(source, obj);
        return obj;
    }


    /**
     * 集合拷贝到对象
     * @param source:来源数据
     * @param clazz:目标数据
     */
    public static <T> List<T> copyProperties(List source, Class<T> clazz){
        List<T> target = new ArrayList<>();
        if (!CollectionUtils.isEmpty(source)){
            for (Object c: source) {
                T obj = copyProperties(c, clazz);
                target.add(obj);
            }
        }
        return target;
    }


}
