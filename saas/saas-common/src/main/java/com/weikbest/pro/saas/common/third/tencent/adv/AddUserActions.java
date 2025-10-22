package com.weikbest.pro.saas.common.third.tencent.adv;


import com.google.gson.JsonObject;
import com.tencent.ads.ApiContextConfig;
import com.tencent.ads.TencentAds;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.exception.TencentAdsSDKException;
import com.tencent.ads.model.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 上传用户行为数据
 * @author 53653
 *
 */
@Slf4j
public class AddUserActions {
  /** YOUR ACCESS TOKEN */
  public static String ACCESS_TOKEN = "c2f56d1f5aad2177bf40a06d9b482ef7";

  /** TencentAds */
  public TencentAds tencentAds;
  public UserActionsAddRequest data = new UserActionsAddRequest();
  /** 授权的推广帐号 id **/
//  public static Long accountId = 23526052L;
//  public ActionType actionType = ActionType.CUSTOM;
//  public static Long userActionSetId = 1200911683L;//数据源
//  public static String url = "/pages/goods/details?id=1600311272010813440";
//  public static String clickId = "xx";
//  public static Integer value = 4000;

  public void init(ActionType actionType,Long accountId,Long userActionSetId,String clickId,Integer value,String accessToken,String appid,String openid,String unionid) {
    this.tencentAds = TencentAds.getInstance();
    this.tencentAds.init(
        new ApiContextConfig().accessToken(accessToken==null?ACCESS_TOKEN:accessToken).isDebug(true)); // debug==true 会打印请求详细信息
    this.tencentAds.useProduction(); //线上环境
    this.buildParams(actionType,accountId,userActionSetId,clickId,value,appid,openid,unionid);
  }

  public void buildParams(ActionType actionType, Long accountId,Long userActionSetId,String clickId,Integer value,String appid,String openid,String unionid) {
    data.setAccountId(accountId);
    UserAction userAction = new UserAction();
    userAction.setActionType(actionType);
    ActionsUserId user = new ActionsUserId();
    user.setWechatAppId(appid);
    user.setWechatOpenid(openid);
    user.setWechatUnionid(unionid);
    userAction.setUserId(user);
    Trace trace = new Trace();
    trace.setClickId(clickId);
    userAction.setTrace(trace);//目前仅支持click_id 落地页URL中的click_id
    JsonObject param = new JsonObject();
    param.addProperty("value", value);
    userAction.setActionParam(param);//行为所带的参数，转化行为价值（例如金额）
    List<UserAction> actions = new ArrayList<>();
    actions.add(userAction);
    data.setActions(actions);
    data.setUserActionSetId(userActionSetId);
  }

  public UserActionsAddResponse addUserActions() throws Exception {
    UserActionsAddResponse response = tencentAds.userActions().userActionsAdd(data);
    log.info("上传用户行为数据结果--------" + response.toString());
    //{"code":0,"message":""}
    return response;
  }

  public static void main(String[] args) {
	try {
      AddUserActions addUserActions = new AddUserActions();
      //  ActionType actionType = ActionType.COMPLETE_ORDER;//下单
        ActionType actionType = ActionType.PURCHASE;//购买
      addUserActions.init(actionType,26261070L,1200916100L,"wx0gcciho4c6jwma",200,"896fd53f24e6be780c97f4c20373dd97","wx72f7e3e4016bcef1","oVa-54sgd9Mq3wUjGDB4iFgQepc0","oTDyp6LtMFrX6qeixwJWXf8GJ6sM");
      UserActionsAddResponse response = addUserActions.addUserActions();
      System.out.println(response.toString());
    } catch (TencentAdsResponseException e) {
      e.printStackTrace();
    } catch (TencentAdsSDKException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}