package com.weikbest.pro.saas.advice;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ArrayUtil;
import com.weikbest.pro.saas.common.annotation.log.LogExecute;
import com.weikbest.pro.saas.common.util.context.SpringContext;
import com.weikbest.pro.saas.common.util.web.IpUtil;
import com.weikbest.pro.saas.common.util.web.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author wisdomelon
 * @date 2019/6/12 0012
 * @project saas
 * @jdk 1.8
 * 日志AOP
 */
@Aspect
@Slf4j
@Component
public class LogAdvice {

    @Around("@annotation(com.weikbest.pro.saas.common.annotation.log.SaveLog)")
    public Object aroundPostMappingAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        return executeAround(joinPoint);
    }

    @Around("@annotation(com.weikbest.pro.saas.common.annotation.log.SaveOrUpdateLog)")
    public Object aroundPostPutMappingAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        return executeAround(joinPoint);
    }

    @Around("@annotation(com.weikbest.pro.saas.common.annotation.log.UpdateLog)")
    public Object aroundPutMappingAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        return executeAround(joinPoint);
    }

    @Around("@annotation(com.weikbest.pro.saas.common.annotation.log.RemoveLog)")
    public Object aroundDeleteMappingAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        return executeAround(joinPoint);
    }

    @Around("@annotation(com.weikbest.pro.saas.common.annotation.log.QueryLog)")
    public Object aroundGetMappingAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        return executeAround(joinPoint);
    }

    @Around("execution(* com.weikbest.pro.saas.*.service.*.impl.*.*(..))")
    public Object aroundServiceAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        return executeAround(joinPoint);
    }

    private Object executeAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        org.slf4j.Logger logger;
        String className = joinPoint.getTarget().getClass().getSimpleName();
        try {
            Class<?> aClass = joinPoint.getTarget().getClass();
            Field field = aClass.getDeclaredField("log");
            field.setAccessible(true);
            logger = (Logger) field.get(null);
        } catch (Exception e) {
            log.warn("this class: {}, is not configuration Slf4j Logger!, use default Logger...", className);
            logger = log;
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        logBegin(logger, className, method.getName(), args);

        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        logEnd(logger, className, method, result, end - start);
        return result;
    }

    private void logBegin(Logger logger, String className, String methodName, Object[] args) {
        logger.info("********** Class.Method is : {}.{}() method invoke start, Args is: [{}] **********", className, methodName, StringUtils.join(args, " ; "));
    }

    private void logEnd(Logger logger, String className, Method method, Object result, long time) {

        logger.info("********** Class.Method is : {}.{}() method invoke finish, Result is: [{}] ********** \nuse time: {} ms\n", className, method.getName(), result, time);

//        try {
//            // 异步线程记录日志
//            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            if (ObjectUtils.isEmpty(servletRequestAttributes)) {
//                return;
//            }
//            HttpServletRequest request = servletRequestAttributes.getRequest();
//            String ipAddress = IpUtil.getIpAddress(request);
//            Long userId = RequestUtil.getUserId(method, request);
//            boolean mobileDevice = RequestUtil.isMobileDevice(request);
//            String traceid = RequestUtil.getTraceId(request);
//
//            ThreadUtil.execute(() -> {
//                String[] logExecuteStrArr = SpringContext.getInstance().getApplicationContext().getBeanNamesForType(LogExecute.class);
//                if (ArrayUtil.isNotEmpty(logExecuteStrArr)) {
//                    for (String logExecuteClassName : logExecuteStrArr) {
//                        LogExecute logExecute = (LogExecute) SpringContext.getInstance().getBean(logExecuteClassName);
//                        logExecute.execute(logger, className, method, ipAddress, userId, mobileDevice, traceid);
//                    }
//                }
//
//            });
//        } catch (Exception e) {
//            logger.warn("日志记录失败！", e);
//        }
    }


}
