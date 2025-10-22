package com.weikbest.pro.saas.common.transfervo.resp;

import cn.hutool.core.map.MapUtil;
import com.weikbest.pro.saas.common.util.web.RequestUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * @author wisdomelon
 * @date 2019/6/11 0011
 * @project saas
 * @jdk 1.8
 * 返回到前台数据统一格式
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "DataResp对象", description = "标准返回对象")
public class DataResp<T> implements Serializable {

    private static final long serialVersionUID = -4349970333991138613L;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String msg;

    @ApiModelProperty(value = "返回数据头")
    private Object head;

    @ApiModelProperty(value = "返回数据跟踪链")
    private String traceid;

    @ApiModelProperty(value = "返回数据")
    private T data;

    protected DataResp() {
    }

    public static DataResp<Object> ok() {
        DataResp<Object> resp = new DataResp<>();
        resp.setCode(ResultConstant.SUCCESS.getCode());
        resp.setMsg(ResultConstant.SUCCESS.getMsg());
        resp.setTraceid(RequestUtil.getTraceId());
        return resp;
    }

    public static DataResp<Object> error() {
        DataResp<Object> resp = new DataResp<>();
        resp.setCode(ResultConstant.EXCEPTION_FAIL.getCode());
        resp.setMsg(ResultConstant.EXCEPTION_FAIL.getMsg());
        resp.setTraceid(RequestUtil.getTraceId());
        return resp;
    }

    public static <T> DataResp<T> ok(T data) {
        DataResp<T> resp = new DataResp<>();
        resp.setCode(ResultConstant.SUCCESS.getCode());
        resp.setMsg(ResultConstant.SUCCESS.getMsg());
        resp.setTraceid(RequestUtil.getTraceId());
        resp.setData(data);
        return resp;
    }

    public static <T> DataResp<T> error(T data) {
        DataResp<T> resp = new DataResp<>();
        resp.setCode(ResultConstant.EXCEPTION_FAIL.getCode());
        resp.setMsg(ResultConstant.EXCEPTION_FAIL.getMsg());
        resp.setTraceid(RequestUtil.getTraceId());
        resp.setData(data);
        return resp;
    }

    public DataResp<T> msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    public DataResp<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

    public DataResp<T> infomation(ResultConstant resultConstant) {
        this.setCode(resultConstant.getCode());
        this.setMsg(resultConstant.getMsg());
        return this;
    }

    public static class BuilderMap {

        private final DataResp<Map<String, Object>> map = new DataResp<>();

        public static BuilderMap create() {
            return new BuilderMap();
        }

        public BuilderMap ok() {
            map.setCode(ResultConstant.SUCCESS.getCode());
            map.setMsg(ResultConstant.SUCCESS.getMsg());
            map.setTraceid(RequestUtil.getTraceId());
            map.setHead(MapUtil.newHashMap());
            map.setData(MapUtil.newHashMap());
            return this;
        }

        public BuilderMap error() {
            map.setCode(ResultConstant.EXCEPTION_FAIL.getCode());
            map.setMsg(ResultConstant.EXCEPTION_FAIL.getMsg());
            map.setTraceid(RequestUtil.getTraceId());
            map.setHead(MapUtil.newHashMap());
            map.setData(MapUtil.newHashMap());
            return this;
        }

        public BuilderMap msg(String msg) {
            map.setMsg(msg);
            return this;
        }

        public BuilderMap code(Integer code) {
            map.setCode(code);
            return this;
        }

        public BuilderMap put(String key, Object value) {
            map.getData().put(key, value);
            return this;
        }

        public BuilderMap put(Map<String, Object> dataMap) {
            map.getData().putAll(dataMap);
            return this;
        }

        public BuilderMap putHead(String key, Object value) {
            ((Map<String, Object>) map.getHead()).put(key, value);
            return this;
        }

        public BuilderMap putHead(Map<String, Object> headMap) {
            ((Map<String, Object>) map.getHead()).putAll(headMap);
            return this;
        }

        public DataResp<Map<String, Object>> build() {
            return map;
        }
    }
}
