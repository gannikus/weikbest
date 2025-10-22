package com.weikbest.pro.saas.common.cache;

/**
 * @author wisdomelon
 * @project saas
 * @jdk 1.8
 * @since 2022/08/31
 */
public interface ILocalCache {

    void loadCache();

    void refreshCache();
}
