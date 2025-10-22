package com.weikbest.pro.saas.handler;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.util.web.RequestUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wisdomelon
 * @date 2020/4/4 0004
 * @project saas
 * @jdk 1.8
 * 用于mybatis-plus的值的自动装配
 */
@Component
public class AutoValueMataObjectHandler implements MetaObjectHandler {

    private static final String CREATOR = "creator";
    private static final String MODIFIER = "modifier";
    private static final String GMT_CREATE = "gmtCreate";
    private static final String GMT_MODIFIED = "gmtModified";
    private static final String GMT_UPDATE = "gmtUpdate";
    private static final String NUMBER = "number";

    @Override
    public void insertFill(MetaObject metaObject) {
        // 设置日期
        DateTime date = DateUtil.date();
        this.setFieldValByName(GMT_CREATE, date, metaObject);
        this.setFieldValByName(GMT_MODIFIED, date, metaObject);
        this.setFieldValByName(GMT_UPDATE, date, metaObject);

        // 设置创建人更新人
        String creator = metaObject.findProperty(CREATOR, true);
        if (StrUtil.isNotEmpty(creator) && ObjectUtil.isNull(metaObject.getValue(CREATOR))) {
            // 获取创建更新人
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (ObjectUtils.isEmpty(servletRequestAttributes)) {
                this.setFieldValByName(CREATOR, WeikbestConstant.ZERO_LONG, metaObject);
                this.setFieldValByName(MODIFIER, WeikbestConstant.ZERO_LONG, metaObject);
            } else {
                HttpServletRequest request = servletRequestAttributes.getRequest();
                Long userId = RequestUtil.getUserId(request);
                this.setFieldValByName(CREATOR, userId, metaObject);
                this.setFieldValByName(MODIFIER, userId, metaObject);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(GMT_MODIFIED, DateUtil.date(), metaObject);
        this.setFieldValByName(GMT_UPDATE, DateUtil.date(), metaObject);

        String modifier = metaObject.findProperty(MODIFIER, true);
        if (StrUtil.isNotEmpty(modifier) && ObjectUtil.isNull(metaObject.getValue(MODIFIER))) {
            // 获取更新人
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (ObjectUtils.isEmpty(servletRequestAttributes)) {
                this.setFieldValByName(MODIFIER, WeikbestConstant.ZERO_LONG, metaObject);
            } else {
                HttpServletRequest request = servletRequestAttributes.getRequest();
                Long userId = RequestUtil.getUserId(request);
                this.setFieldValByName(MODIFIER, userId, metaObject);
            }
        }
    }
}

