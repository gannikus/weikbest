package com.weikbest.pro.saas.common.db.table;

/**
 * @author wisdomelon
 * @date 2021/4/30
 * @project saas
 * @jdk 1.8
 */
public interface TableColumnInfo {

    /**
     * 返回列名称
     *
     * @return 列名称
     */
    default String column() {
        return this.toString();
    }
}
