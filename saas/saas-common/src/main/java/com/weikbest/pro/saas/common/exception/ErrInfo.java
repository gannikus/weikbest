package com.weikbest.pro.saas.common.exception;

/**
 * @author wisdomelon
 * @date 2021/5/15
 * @project saas
 * @jdk 1.8
 */
public enum ErrInfo {
    /**
     * 错误信息枚举
     */
    WITHDRAWAL_FAIL("提现操作失败，提现单号：");


    private String errMsg;

    private ErrInfo(String errMsg) {
        this.errMsg = errMsg;
    }

    public String errMsg() {
        return this.errMsg;
    }
}
