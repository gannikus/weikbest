package com.weikbest.pro.saas.merchat.complaint.module.resp;

import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.web.RequestUtil;
import com.weikbest.pro.saas.merchat.complaint.module.vo.WxOrderComplaintVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/12/4
 * 微信订单查询返回到前台数据统一格式
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "WxOrderComplaintRespp对象", description = "微信订单查询返回到前台数据统一格式")
public class WxOrderComplaintResp extends PageResp<WxOrderComplaintVO> {

    @ApiModelProperty(value = "微信返回的原始内容")
    private Map<String, Object> originalData;

    public static WxOrderComplaintResp ok(Long count, WxOrderComplaintVO data, Map<String, Object> originalData) {
        WxOrderComplaintResp resp = new WxOrderComplaintResp();
        resp.setCode(ResultConstant.SUCCESS.getCode());
        resp.setMsg(ResultConstant.SUCCESS.getMsg());
        resp.setTraceid(RequestUtil.getTraceId());
        resp.setCount(count);
        resp.setData(data);
        resp.setOriginalData(originalData);
        return resp;
    }

    public static WxOrderComplaintResp error(Long count, WxOrderComplaintVO data, Map<String, Object> originalData) {
        WxOrderComplaintResp resp = new WxOrderComplaintResp();
        resp.setCode(ResultConstant.EXCEPTION_FAIL.getCode());
        resp.setMsg(ResultConstant.EXCEPTION_FAIL.getMsg());
        resp.setTraceid(RequestUtil.getTraceId());
        resp.setCount(count);
        resp.setData(data);
        resp.setOriginalData(originalData);
        return resp;
    }
}
