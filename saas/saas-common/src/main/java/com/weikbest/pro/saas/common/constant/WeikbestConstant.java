package com.weikbest.pro.saas.common.constant;

/**
 * @author wisdomelon
 * @date 2020/6/24
 * @project saas
 * @jdk 1.8
 * 常量类
 */
public interface WeikbestConstant {

    /**
     * map默认大小
     */
    int HASHMAP_INITIALCAPACITY = 16;

    /**
     * 商户端日志类型
     */
    String BUSINESS_RECORD = "1";

    /**
     * 分账接收方类型:商户ID
     */
    String MERCHANT_ID = "MERCHANT_ID";
    /**
     * 与分账方的关系类型:合作伙伴
     */
    String PARTNER = "PARTNER";

    /**
     * 分隔符
     */
    String COMM_SPLIT = ",";

    /**
     * 权限项的分隔符
     */
    String PURVIEW_SPLIT = ":";

    /**
     * 中国-行政区划
     */
    Integer CHINA = 100000;

    Integer MAX_SAVE_BATCH_SIZE = 1000;

    /**
     * 顺丰快递编码
     */
    String SFEXPRESS = "SFEXPRESS";

    /**
     * 微信优惠券时间格式
     */
    String WX_COUPON_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";

    /******************** 字典 ***************************/
    String UNCHEKCED_0 = "0";
    String CHEKCED_1 = "1";

    String PURVIEW_1 = "1";
    // 默认排序
    Integer DEFAULT_ORD = 1;

    // 根用户
    Long ROOT_CUST_PROMOTION_ID = 0L;

    /******************** 超级管理员ID ***************************/
    long SUPERID = 0L;

    /******************** 树的默认节点ID ***************************/
    /**
     * 根节点ID
     */
    String ROOT_ID = "0";
    /**
     * 根节点的父ID
     */
    String ROOT_PARENT_ID = "-1";
    /**
     * 默认父节点ID
     */
    String DEFAULT_PARENT_ID = "0";


    /**
     * 请求来源是手机端
     */
    String MOBLE_SOURCE = "2";
    /**
     * 请求来源是后台
     */
    String MANAGE_SOURCE = "0";

    String FLOW = "flowsheet";

    String TRACEID = "traceid";

    /**
     * 临时前缀
     */
    String TEMP = "TEMP_";

    long ZERO_LONG = 0L;
    long ONE_LONG = 1L;
    int ZERO_INT = 0;
    int ONE_INT = 1;
    int TWO_INT = 2;
    int THREE_INT = 3;
    String ZERO_STR = "0";
    char ZERO_CHAR = '0';

}
