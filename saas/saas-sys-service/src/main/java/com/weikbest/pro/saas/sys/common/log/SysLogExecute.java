package com.weikbest.pro.saas.sys.common.log;

import cn.hutool.core.date.DateUtil;
import com.weikbest.pro.saas.common.annotation.log.*;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.sys.param.entity.Log;
import com.weikbest.pro.saas.sys.param.service.LogService;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author 西瓜瓜
 * @project weikbest
 * @jdk 1.8
 * @since :2022/9/3
 * <p>
 * 系统日志记录
 */
@Component
public class SysLogExecute implements LogExecute {

    @Resource
    private LogService logService;

    @Override
    public void execute(Logger logger, String className, Method method, String ipAddress, Long userId, boolean mobileDevice, String traceid) {
        try {
            if (method.isAnnotationPresent(SaveLog.class) || method.isAnnotationPresent(UpdateLog.class)
                    || method.isAnnotationPresent(RemoveLog.class) || method.isAnnotationPresent(QueryLog.class)) {

                Log log = new Log();
                log.setOperateTime(DateUtil.date());
                log.setOperateIp(ipAddress);
                log.setClassName(className);
                log.setMethodName(method.getName());
                log.setUserId(userId);
                log.setTraceId(traceid);
                log.setCreator(userId);
                log.setModifier(userId);

                if (method.isAnnotationPresent(SaveLog.class)) {
                    SaveLog saveLog = method.getAnnotation(SaveLog.class);
                    log.setLogType(saveLog.type());
                    log.setDetails(saveLog.value());
                    log.setRecordSource(mobileDevice ? WeikbestConstant.MOBLE_SOURCE : saveLog.recordSource());
                }
                if (method.isAnnotationPresent(SaveOrUpdateLog.class)) {
                    SaveOrUpdateLog saveOrUpdateLog = method.getAnnotation(SaveOrUpdateLog.class);
                    log.setLogType(saveOrUpdateLog.type());
                    log.setDetails(saveOrUpdateLog.value());
                    log.setRecordSource(mobileDevice ? WeikbestConstant.MOBLE_SOURCE : saveOrUpdateLog.recordSource());
                }
                if (method.isAnnotationPresent(UpdateLog.class)) {
                    UpdateLog updateLog = method.getAnnotation(UpdateLog.class);
                    log.setLogType(updateLog.type());
                    log.setDetails(updateLog.value());
                    log.setRecordSource(mobileDevice ? WeikbestConstant.MOBLE_SOURCE : updateLog.recordSource());
                }
                if (method.isAnnotationPresent(RemoveLog.class)) {
                    RemoveLog removeLog = method.getAnnotation(RemoveLog.class);
                    log.setLogType(removeLog.type());
                    log.setDetails(removeLog.value());
                    log.setRecordSource(mobileDevice ? WeikbestConstant.MOBLE_SOURCE : removeLog.recordSource());
                }
                if (method.isAnnotationPresent(QueryLog.class)) {
                    QueryLog queryLog = method.getAnnotation(QueryLog.class);
                    log.setLogType(queryLog.type());
                    log.setDetails(queryLog.value());
                    log.setRecordSource(mobileDevice ? WeikbestConstant.MOBLE_SOURCE : queryLog.recordSource());
                }
                if (method.isAnnotationPresent(AuthLog.class)) {
                    AuthLog authLog = method.getAnnotation(AuthLog.class);
                    log.setLogType(authLog.type());
                    log.setDetails(authLog.value());
                    log.setRecordSource(mobileDevice ? WeikbestConstant.MOBLE_SOURCE : authLog.recordSource());
                }
                logService.save(log);
            }
        } catch (Exception e) {
            logger.warn("日志记录失败！", e);
        }
    }
}
