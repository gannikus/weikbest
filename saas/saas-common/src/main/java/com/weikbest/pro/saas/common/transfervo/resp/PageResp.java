package com.weikbest.pro.saas.common.transfervo.resp;

import com.weikbest.pro.saas.common.util.web.RequestUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author wisdomelon
 * @date 2019/6/11 0011
 * @project saas
 * @jdk 1.8
 * 返回到前台数据统一格式
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "PageResp对象", description = "标准分页返回对象")
public class PageResp<T> extends DataResp<T> {

    @ApiModelProperty(value = "数据总量")
    private Long count;

    public static PageResp<Object> ok() {
        PageResp<Object> resp = new PageResp<>();
        resp.setCode(ResultConstant.SUCCESS.getCode());
        resp.setMsg(ResultConstant.SUCCESS.getMsg());
        resp.setTraceid(RequestUtil.getTraceId());
        return resp;
    }

    public static PageResp<Object> error() {
        PageResp<Object> resp = new PageResp<>();
        resp.setCode(ResultConstant.EXCEPTION_FAIL.getCode());
        resp.setMsg(ResultConstant.EXCEPTION_FAIL.getMsg());
        resp.setTraceid(RequestUtil.getTraceId());
        return resp;
    }

    public static <T> PageResp<T> ok(Long count, T data) {
        PageResp<T> resp = new PageResp<>();
        resp.setCode(ResultConstant.SUCCESS.getCode());
        resp.setMsg(ResultConstant.SUCCESS.getMsg());
        resp.setTraceid(RequestUtil.getTraceId());
        resp.setCount(count);
        resp.setData(data);
        return resp;
    }

    public static <T> PageResp<T> error(Long count, T data) {
        PageResp<T> resp = new PageResp<>();
        resp.setCode(ResultConstant.EXCEPTION_FAIL.getCode());
        resp.setMsg(ResultConstant.EXCEPTION_FAIL.getMsg());
        resp.setTraceid(RequestUtil.getTraceId());
        resp.setCount(count);
        resp.setData(data);
        return resp;
    }

    public PageResp<T> count(long count) {
        this.setCount(count);
        return this;
    }

}
