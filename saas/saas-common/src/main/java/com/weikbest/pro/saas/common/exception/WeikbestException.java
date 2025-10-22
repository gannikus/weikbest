package com.weikbest.pro.saas.common.exception;

import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wisdomelon
 * @date 2020/4/5 0005
 * @jdk 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WeikbestException extends RuntimeException {

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "错误说明")
    private String msg;

    public WeikbestException() {
        super(ResultConstant.EXCEPTION_FAIL.getMsg());
        this.code = ResultConstant.EXCEPTION_FAIL.getCode();
        this.msg = ResultConstant.EXCEPTION_FAIL.getMsg();
    }

    public WeikbestException(Throwable e) {
        super(e);
        this.code = ResultConstant.EXCEPTION_FAIL.getCode();
        this.msg = StrUtil.isNotBlank(e.getMessage()) ? e.getMessage() : ResultConstant.EXCEPTION_FAIL.getMsg();
    }

    public WeikbestException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public WeikbestException(ResultConstant resultConstant) {
        super(resultConstant.getMsg());
        this.code = resultConstant.getCode();
        this.msg = resultConstant.getMsg();
    }

    public WeikbestException(ResultConstant resultConstant, Throwable e) {
        super(e);
        this.code = resultConstant.getCode();
        this.msg = resultConstant.getMsg();
    }

    public WeikbestException(Integer code, String msg, Throwable e) {
        super(e);
        this.code = code;
        this.msg = msg;
    }

    public WeikbestException(String msg) {
        super(msg);
        this.code = ResultConstant.EXCEPTION_FAIL.getCode();
        this.msg = msg;
    }

    public WeikbestException(String msg, Throwable e) {
        super(e);
        this.code = ResultConstant.EXCEPTION_FAIL.getCode();
        this.msg = msg;
    }

    public WeikbestException(Integer code) {
        super(ResultConstant.EXCEPTION_FAIL.getMsg());
        this.code = code;
        this.msg = ResultConstant.EXCEPTION_FAIL.getMsg();
    }

    public WeikbestException(Integer code, Throwable e) {
        super(e);
        this.code = code;
        this.msg = ResultConstant.EXCEPTION_FAIL.getMsg();
    }

    @Override
    public String toString() {
        return "MysteryBoxesException{" +
                "msg=" + this.getMsg() +
                ", code=" + code +
                '}';
    }
}
