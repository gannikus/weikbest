package com.weikbest.pro.saas.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.stdout.StdOutImpl;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/9/19
 */
@Slf4j
public class MPStdOutImpl extends StdOutImpl {

    public MPStdOutImpl(String clazz) {
        super(clazz);
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void error(String s, Throwable e) {
        log.error(s, e);
    }

    @Override
    public void error(String s) {
        log.error(s);
    }

    @Override
    public void debug(String s) {
        log.debug(s);
    }

    @Override
    public void trace(String s) {
        log.trace(s);
    }

    @Override
    public void warn(String s) {
        log.warn(s);
    }
}
