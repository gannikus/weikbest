package com.weikbest.pro.saas.applet.comm.controller;

import com.alibaba.fastjson.JSONObject;
import com.weikbest.pro.saas.common.annotation.token.PassToken;
import com.weikbest.pro.saas.merchat.order.service.OrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 点击监测控制层
 *
 * @author 甘之萌  2023/04/04 15:08
 */
@Slf4j
@Api(tags = {"click::点击监测处理类"})
@RestController
public class ClickMonitoringController {

    @Autowired
    private OrderInfoService orderInfoService;

    @PassToken
    @ApiOperation(value = "点击监测", notes = "广告链接格式：\n" +
            "https://saas.baoliangmall.com/click/?adgroup_id=__ADGROUP_ID__&ad_id=__AD_ID__&callback=__CALLBACK__&account_id=__ACCOUNT_ID__&click_id=__CLICK_ID__&click_time=__CLICK_TIME__&wechat_openid=__WECHAT_OPENID__&request_id=__REQUEST_ID__\n" +
            "这里的 “__ADGROUP_ID__ ”，就是一个宏，代表将来真实的ADGROUP_ID 字段会在这里填写。\n" +
            " 小程序必填字段：account_id / callback / click_id / click_time")
    @GetMapping(value = "/click/")
    public JSONObject clickMonitoring(@ApiParam(value = "广告组id（实际为广告id）",required = true) @RequestParam(value = "adgroup_id") String adgroupId,
                                      @ApiParam(value = "广告id（实际为创意id）",required = true) @RequestParam(value = "ad_id") String adId,
                                      @ApiParam(value = "直接提供上报信息回传接口的 url，示例为url encode编码原值，广告主需要decode作为post请求url回传至AMS",required = true) @RequestParam(value = "callback") String callback,
                                      @ApiParam(value = "广告主id" ,required = true) @RequestParam(value = "account_id") String accountId,
                                      @ApiParam(value = "点击id",required = true) @RequestParam(value = "click_id") String clickId,
                                      @ApiParam(value = "点击时间",required = true) @RequestParam(value = "click_time") String clickTime,
                                      @ApiParam(value = "用户针对小程序应用会产生一个安全的OpenID",required = true) @RequestParam(value = "wechat_openid") String wechatOpenid,
                                      @ApiParam(value = "请求id",required = true) @RequestParam(value = "request_id") String requestId) {
        JSONObject result = new JSONObject();
        //1.日志打印
        log.info("--------点击监测参数：adgroup_id:{},adId:{},account_id:{},click_id:{},click_time:{},callback:{},wechat_openid:{},request_id:{}",adgroupId,adId,accountId,clickId,clickTime,callback,wechatOpenid,requestId);

        //3.返回结果
       try {
           //2.调用订单广告监测
           orderInfoService.clickMonitoring(clickId,adgroupId,adId,callback,accountId,clickTime,wechatOpenid,requestId);
           result.put("code","0");
           result.put("msg","success");
       }catch (Exception e){
           log.error("出现异常：",e);
           result.put("code","999");
           result.put("msg","error");
           result.put("data",e.getMessage());
       }

        log.info("--------返回结果：" + result);
        return result;
    }


    @PassToken
    @ApiOperation(value = "点击监测-old", notes = "广告链接格式：\n" +
            "https://saas.baoliangmall.com/gdt.cgi?adgroup_id=__ADGROUP_ID__&ad_id=__AD_ID__&callback=__CALLBACK__&account_id=__ACCOUNT_ID__&click_id=__CLICK_ID__&click_time=__CLICK_TIME__&wechat_openid=__WECHAT_OPENID__&request_id=__REQUEST_ID__\n" +
            "这里的 “__ADGROUP_ID__ ”，就是一个宏，代表将来真实的ADGROUP_ID 字段会在这里填写。\n")
    @GetMapping(value = "/gdt.cgi")
    public JSONObject clickMonitoringOld(@ApiParam(value = "广告组id（实际为广告id）",required = true) @RequestParam(value = "adgroup_id") String adgroupId,
                                      @ApiParam(value = "点击id",required = true) @RequestParam(value = "click_id") Integer clickId,
                                      @ApiParam(value = "点击时间,取值为标准时间戳，秒级别",required = true) @RequestParam(value = "click_time") Integer clickTime,
                                      @ApiParam(value = "请求id",required = true) @RequestParam(value = "request_id") String requestId) {
        JSONObject result = new JSONObject();
        //1.日志打印
        log.info("--------点击监测参数：adgroup_id:{},click_id:{},click_time:{},request_id:{}",adgroupId,clickId,clickTime,requestId);

        //3.返回结果
        try {
            //2.调用订单广告监测
            orderInfoService.clickMonitoringOld(clickId,adgroupId,clickTime,requestId);
            result.put("ret",0);
            result.put("msg","success");
        }catch (Exception e){
            log.error("出现异常：",e);
            result.put("code",999);
            result.put("msg","error");
            result.put("data",e.getMessage());
        }

        log.info("--------返回结果：" + result);
        return result;
    }
}
