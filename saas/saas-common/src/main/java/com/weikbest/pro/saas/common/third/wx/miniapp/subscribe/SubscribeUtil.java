package com.weikbest.pro.saas.common.third.wx.miniapp.subscribe;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaSubscribeService;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.util.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/19
 * <p>
 * 微信订阅消息发送
 */
@Slf4j
public class SubscribeUtil {

    /**
     * 微信要求 thing关键字的长度不能大于20
     *
     * @param thingVal 值
     * @return 截取后的值
     */
    public static String subThing(String thingVal) {
        if (thingVal.length() > 20) {
            thingVal = StrUtil.subPre(thingVal, 17) + "...";
        }
        return thingVal;
    }

    /**
     * 发送单条订阅消息
     *
     * @param templateId       消息模板ID
     * @param openid           用户openid
     * @param appletPage       小程序页面
     * @param miniProgramState 订阅消息跳转小程序类型 {@link WxMaConstants.MiniProgramState}
     * @param templateParam    参数，t_sys_applet_subscribe_config的param转换后的参数
     * @return 请求参数
     */
    public static WxMaSubscribeMessage buildSendSubscribeMsg(String templateId, String openid, String appletPage, String miniProgramState, String templateParam) {
        Map paramMap = JsonUtils.json2Bean(templateParam, Map.class);

        WxMaSubscribeMessage wxMaSubscribeMessage = new WxMaSubscribeMessage();
        wxMaSubscribeMessage.setTemplateId(templateId);
        wxMaSubscribeMessage.setToUser(openid);
        if (StrUtil.isNotBlank(appletPage)) {
            wxMaSubscribeMessage.setPage(appletPage);
        }

        List<WxMaSubscribeMessage.MsgData> msgDataList = new ArrayList<>();
        paramMap.forEach((name, value) -> {
            msgDataList.add(new WxMaSubscribeMessage.MsgData(String.valueOf(name), String.valueOf(value)));
        });
        wxMaSubscribeMessage.setData(msgDataList);

        if (StrUtil.isNotBlank(miniProgramState)) {
            wxMaSubscribeMessage.setMiniprogramState(miniProgramState);
        }

        return wxMaSubscribeMessage;
    }

    /**
     * 发送单条订阅消息
     *
     * @param wxMaService          微信小程序服务
     * @param wxMaSubscribeMessage 消息入参
     * @return 请求参数
     */
    public static String doSendOneSubscribe(WxMaService wxMaService, WxMaSubscribeMessage wxMaSubscribeMessage) {
        WxMaSubscribeService subscribeService = wxMaService.getSubscribeService();

        try {
            subscribeService.sendSubscribeMsg(wxMaSubscribeMessage);
        } catch (WxErrorException e) {
            log.error("发送微信订阅消息失败！ ", e);
            return e.getMessage();
        }

        return "";
    }
}
