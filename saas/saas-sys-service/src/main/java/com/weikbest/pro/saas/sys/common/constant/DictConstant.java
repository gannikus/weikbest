package com.weikbest.pro.saas.sys.common.constant;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import com.weikbest.pro.saas.common.constant.ConstantCode;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.sys.param.entity.Dict;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author wisdomelon
 * @date 2020/6/24
 * @project saas
 * @jdk 1.8
 * <p>
 * Kv键值对常量类 系统字典表常量
 */
public class DictConstant {
    /**
     * 批量上传状态
     */
    public enum  OrderBatchDeliverStatus implements ConstantCode<String>{
        /**
         * 上传中
         */
        uploading("1"),
        /**
         * 已上传
         */
        uploaded("2"),
        ;
        private final String code;

        OrderBatchDeliverStatus(String code){
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return Status.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }
        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 状态
     */
    public enum Status implements ConstantCode<String> {
        /**
         * 禁用
         */
        disable("0"),
        /**
         * 有效
         */
        enable("1"),
        ;

        private final String code;

        Status(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return Status.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }


    /**
     * 是否
     */
    public enum Whether implements ConstantCode<String> {
        /**
         * 否
         */
        no("0"),
        /**
         * 是
         */
        yes("1"),
        ;

        private final String code;

        Whether(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return Whether.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 角色类型
     */
    public enum RoleType implements ConstantCode<String> {
        /**
         * 平台角色
         */
        sys("0"),
        /**
         * 商家端角色
         */
        merchat("1"),
        /**
         * APP端角色
         */
        applet("2"),
        ;

        private final String code;

        RoleType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return RoleType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 模块类型
     */
    public enum ModuleType implements ConstantCode<String> {
        /**
         * 平台模块
         */
        sys("0"),
        /**
         * 商家端模块
         */
        merchat("1"),
        /**
         * APP端模块
         */
        applet("2"),
        ;

        private final String code;

        ModuleType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return ModuleType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 菜单类型
     */
    public enum MenuType implements ConstantCode<String> {
        /**
         * 平台菜单
         */
        sys("0"),
        /**
         * 商家端菜单
         */
        merchat("1"),
        /**
         * APP端菜单
         */
        applet("2"),
        ;

        private final String code;

        MenuType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return MenuType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }


    /**
     * 系统用户关联类型
     */
    public enum UserRelateType implements ConstantCode<String> {
        /**
         * 系统平台用户
         */
        sys("0"),
        /**
         * 商家端用户
         */
        merchat("1"),
        /**
         * 小程序端用户
         */
        applet("2"),
        ;

        private final String code;

        UserRelateType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return UserRelateType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 系统用户登录类型
     */
    public enum LoginType implements ConstantCode<String> {
        /**
         * 工号登录
         */
        number("0"),
        /**
         * 手机号登录
         */
        phone("1"),
        /**
         * 邮箱登录
         */
        email("2"),
        ;

        private final String code;

        LoginType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return LoginType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 用户在线状态
     */
    public enum Online implements ConstantCode<String> {
        /**
         * 登出
         */
        code_0("0"),
        /**
         * 在线
         */
        code_1("1");

        private final String code;

        Online(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return Online.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 行政区划类型
     */
    public enum RegionLevelType implements ConstantCode<String> {
        /**
         * 省、自治区、直辖市
         */
        PROVINCE("province"),
        /**
         * 地级市、地区、自治州、盟
         */
        CITY("city"),
        /**
         * 市辖区、县级市、县
         */
        DISTRICT("district"),
        ;

        private final String code;

        RegionLevelType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return RegionLevelType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 编码规则类型
     */
    public enum CodeRuleType implements ConstantCode<String> {
        /**
         * 流水号
         */
        SERIAL("1"),
        /**
         * 日期时间+流水号
         */
        DATETIME_SERIAL("2"),
        ;

        private final String code;

        CodeRuleType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return CodeRuleType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }


    /**
     * 延时任务状态
     */
    public enum DelayTaskStatus implements ConstantCode<String> {
        /**
         * 等待执行
         */
        waiting("0"),
        /**
         * 执行成功
         */
        success("1"),
        /**
         * 执行异常
         */
        error("2"),
        /**
         * 无法执行
         */
        cannot("9"),
        ;

        private final String code;

        DelayTaskStatus(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return DelayTaskStatus.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 支付方式
     */
    public enum PayType implements ConstantCode<String> {
        /**
         * 1-微信支付
         */
        TYPE_1("1"),
        /**
         * 1-货到付款
         */
        TYPE_2("2"),
        ;

        private final String code;

        PayType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return PayType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 平台商户号对应的类型
     */
    public enum PayConfigType implements ConstantCode<String> {
        /**
         * 1-普通商户
         */
        TYPE_1("1"),
        /**
         * 3-特约商户
         */
        TYPE_3("3"),
        ;

        private final String code;

        PayConfigType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return PayConfigType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 广告渠道类型
     */
    public enum AdvChannelType implements ConstantCode<String> {
        /**
         * 1-腾讯广告
         */
        TYPE_1("1"),
        /**
         * 2-快手广告
         */
        TYPE_2("2"),
        /**
         * 3-序言泽联盟广告
         */
        TYPE_3("3"),
        /**
         * 4-腾讯视频号广告
         */
        TYPE_4("4"),
        /**
         * 5-美柚广告
         */
        TYPE_5("5"),
        ;

        private final String code;

        AdvChannelType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return AdvChannelType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 广告回传类型
     */
    public enum AdvBackType implements ConstantCode<String> {
        /**
         * 1-全部回传
         */
        TYPE_1("1"),
        /**
         * 2-按投放账号回传
         */
        TYPE_2("2"),
        ;

        private final String code;

        AdvBackType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return AdvBackType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 商品优惠券类型
     */
    public enum CouponType implements ConstantCode<String> {
        /**
         * 1-商品立减劵
         */
        TYPE_1("1"),
        /**
         * 2-回流优惠券
         */
        TYPE_2("2"),
        /**
         * 3-平台优惠券
         */
        TYPE_3("3"),
        ;

        private final String code;

        CouponType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return CouponType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }


    /**
     * 商品优惠券状态
     */
    public enum CouponStatus implements ConstantCode<String> {
        /**
         * 1-待发布
         */
        TYPE_1("1"),
        /**
         * 5-活动未开始
         */
        TYPE_5("5"),
        /**
         * 10-进行中
         */
        TYPE_10("10"),
        /**
         * 15-已结束
         */
        TYPE_15("15"),
        /**
         * 20-已取消
         */
        TYPE_20("20"),
        ;

        private final String code;

        CouponStatus(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return CouponStatus.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }


    /**
     * 优惠券领用状态
     */
    public enum RestrictType implements ConstantCode<String> {
        /**
         * 1-未生效
         */
        TYPE_1("1"),
        /**
         * 5-未使用
         */
        TYPE_5("5"),
        /**
         * 10-已过期
         */
        TYPE_10("10"),
        /**
         * 15-冻结中
         */
        TYPE_15("15"),
        /**
         * 20-用券核销
         */
        TYPE_20("20"),
        /**
         * 25-主动核销
         */
        TYPE_25("25"),
        ;

        private final String code;

        RestrictType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return RestrictType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 商品优惠券场景类型
     */
    public enum CouponSceneType implements ConstantCode<String> {
        /**
         * 1-广告进入绑定回流优惠券领取
         */
        TYPE_1("1"),
        /**
         * 2-新用户进入小程序点击领取
         */
        TYPE_2("2"),
        ;

        private final String code;

        CouponSceneType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return CouponSceneType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 小程序装修配置区域类型
     */
    public enum AppletConfigType implements ConstantCode<String> {
        /**
         * 1-轮播区
         */
        TYPE_1("1"),
        /**
         * 2-金刚区
         */
        TYPE_2("2"),
        /**
         * 3-魔方区
         */
        TYPE_3("3"),
        /**
         * 4-广告营销区
         */
        TYPE_4("4"),
        ;

        private final String code;

        AppletConfigType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return AppletConfigType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 订单来源
     */
    public enum OrderSource implements ConstantCode<String> {
        /**
         * 1-自然流量
         */
        TYPE_1("1"),
        /**
         * 5-广告引流
         */
        TYPE_5("5"),
        /**
         * 10-回流流量-未回传
         */
        TYPE_10("10"),
        /**
         * 15-回流流量-已回传
         */
        TYPE_15("15"),
        /**
         * 20-营销流量
         */
        TYPE_20("20"),
        ;

        private final String code;

        OrderSource(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return OrderSource.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 用户行为
     */
    public enum UserBehavior implements ConstantCode<String> {
        /**
         * 1-主动打开微信小程序
         */
        TYPE_1("1"),
        /**
         * 5-点击所投放的广告进入小程序
         */
        TYPE_5("5"),
        /**
         * 10-卡包回流优惠券
         */
        TYPE_10("10"),
        /**
         * 15-卡包立减券、平台券
         */
        TYPE_15("15"),
        ;

        private final String code;

        UserBehavior(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return UserBehavior.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 下单路径
     */
    public enum OrderPath implements ConstantCode<String> {
        /**
         * 小程序直接成交
         */
        TYPE_1("1"),
        /**
         * 小程序支付失败成交
         */
        TYPE_5("5"),
        /**
         * 小程序直接成交（绑定商家）
         */
        TYPE_10("10"),
        /**
         * 小程序支付失败成交（绑定商家）
         */
        TYPE_15("15"),
        /**
         * 广告直接提交
         */
        TYPE_20("20"),
        /**
         * 广告支付失败成交
         */
        TYPE_25("25"),
        /**
         * 左滑操作直接成交
         */
        TYPE_30("30"),
        /**
         * 左滑操作支付失败成交
         */
        TYPE_35("35"),
        /**
         * 聚合页首产品直接成交
         */
        TYPE_40("40"),
        /**
         * 聚合页首产品支付失败成交
         */
        TYPE_45("45"),
        /**
         * 聚合页非首产品直接成交
         */
        TYPE_50("50"),
        /**
         * 聚合页非首产品支付失败成交
         */
        TYPE_55("55"),
        /**
         * 聚合页商户主页直接成交
         */
        TYPE_60("60"),
        /**
         * 聚合页商户主页支付失败成交
         */
        TYPE_65("65"),
        /**
         * 回流优惠券页直接成交
         */
        TYPE_70("70"),
        /**
         * 回流优惠券页支付失败成交
         */
        TYPE_75("75"),
        /**
         * 限时优惠直接成交
         */
        TYPE_80("80"),
        /**
         * 限时优惠支付失败成交
         */
        TYPE_85("85"),
        ;

        private final String code;

        OrderPath(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return OrderPath.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }


    /**
     * 订单状态
     */
    public enum OrderStatus implements ConstantCode<String> {
        /**
         * 待付款
         */
        orderStatus_1("1"),
        /**
         * 待发货
         */
        orderStatus_10("10"),
        /**
         * 已发货
         */
        orderStatus_20("20"),
        /**
         * 已完成
         */
        orderStatus_30("30"),
        /**
         * 已关闭
         */
        orderStatus_99("99"),
        ;

        /**
         * 订单完成状态集合
         */
        private static final Set<OrderStatus> ORDER_STATUS_FINISH = Sets.newHashSet(OrderStatus.orderStatus_30, OrderStatus.orderStatus_99);
        /**
         * 订单发货状态集合
         */
        private static final Set<OrderStatus> ORDER_STATUS_SEND = Sets.newHashSet(OrderStatus.orderStatus_20, OrderStatus.orderStatus_30);
        private final String code;

        OrderStatus(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return OrderStatus.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        /**
         * 订单状态是否完成
         *
         * @param code
         * @return
         */
        public static boolean isFinish(String code) {
            for (OrderStatus orderStatus : ORDER_STATUS_FINISH) {
                if (StrUtil.equals(orderStatus.getCode(), code)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 订单是否发货
         *
         * @param code
         * @return
         */
        public static boolean isSend(String code) {
            for (OrderStatus orderStatus : ORDER_STATUS_SEND) {
                if (StrUtil.equals(orderStatus.getCode(), code)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }


    /**
     * 后台页面显示订单状态
     */
    public enum OrderGroupStatus implements ConstantCode<String> {
        /**
         * 待付款
         */
        orderStatus_1(OrderStatus.orderStatus_1.getCode()),
        /**
         * 待发货
         */
        orderStatus_10(OrderStatus.orderStatus_10.getCode()),
        /**
         * 已发货
         */
        orderStatus_20(OrderStatus.orderStatus_20.getCode()),
        /**
         * 已完成
         */
        orderStatus_30(OrderStatus.orderStatus_30.getCode()),
        /**
         * 已关闭
         */
        orderStatus_99(OrderStatus.orderStatus_99.getCode()),
        /**
         * 24小时待发货状态
         */
        orderStatus_15("15"),
        /**
         * 发货已超时
         */
        orderStatus_40("40"),
        /**
         * 售后中
         */
        orderStatus_60("60"),
        ;

        /**
         * 订单状态
         */
        private static final Set<OrderGroupStatus> ORDER_STATUS_SET = Sets.newHashSet(OrderGroupStatus.orderStatus_1, OrderGroupStatus.orderStatus_10, OrderGroupStatus.orderStatus_20, OrderGroupStatus.orderStatus_30, OrderGroupStatus.orderStatus_99);
        private final String code;

        OrderGroupStatus(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return OrderGroupStatus.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        /**
         * 订单分组状态是否是订单状态
         *
         * @param code
         * @return
         */
        public static boolean isOrderStatus(String code) {
            for (OrderGroupStatus orderGroupStatus : ORDER_STATUS_SET) {
                if (StrUtil.equals(orderGroupStatus.getCode(), code)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 售后申请类型
     */
    public enum AfterSaleApplyType implements ConstantCode<String> {
        /**
         * 1-仅退款
         */
        only_refund("1"),
        /**
         * 2-退货退款
         */
        return_and_refund("2"),
        /**
         * 3-换货
         */
        exchange("3"),
        ;

        private final String code;

        AfterSaleApplyType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return AfterSaleApplyType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        public static AfterSaleKey getAfterSaleKey(String code) {
            if (StrUtil.equals(code, AfterSaleApplyType.only_refund.getCode())) {
                return AfterSaleKey.afterSaleKey_10;
            }
            if (StrUtil.equals(code, AfterSaleApplyType.return_and_refund.getCode())) {
                return AfterSaleKey.afterSaleKey_20;
            }
            if (StrUtil.equals(code, AfterSaleApplyType.exchange.getCode())) {
                return AfterSaleKey.afterSaleKey_30;
            }
            throw new WeikbestException("无法根据售后申请类型匹配到具体的售后关键节点!");
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 售后关键节点
     */
    public enum AfterSaleKey implements ConstantCode<String> {
        /**
         * 售后关闭
         */
        afterSaleKey_0("0"),
        /**
         * 售后成功
         */
        afterSaleKey_1("1"),
        /**
         * 仅退款待处理
         */
        afterSaleKey_10("10"),
        /**
         * 仅退款处理中
         */
        afterSaleKey_11("11"),
        /**
         * 退货退款待处理
         */
        afterSaleKey_20("20"),
        /**
         * 退货退款待商家收货
         */
        afterSaleKey_21("21"),
        /**
         * 退货退款处理中
         */
        afterSaleKey_22("22"),
        /**
         * 换货待处理
         */
        afterSaleKey_30("30"),
        /**
         * 换货待商家收货
         */
        afterSaleKey_31("31"),
        /**
         * 换货处理中
         */
        afterSaleKey_32("32"),
        /**
         * 待买家退货
         */
        afterSaleKey_7("7"),
        /**
         * 客户处理中
         */
        afterSaleKey_8("8"),
        /**
         * 平台处理中
         */
        afterSaleKey_9("9"),
        ;

        /**
         * 关键节点=待处理
         */
        private static final Set<AfterSaleKey> AFTER_SALE_KEY_PENDIND = Sets.newHashSet(AfterSaleKey.afterSaleKey_10, AfterSaleKey.afterSaleKey_20, AfterSaleKey.afterSaleKey_30);
        /**
         * 关键节点=处理中
         */
        private static final Set<AfterSaleKey> AFTER_SALE_KEY_PROCESSIND = Sets.newHashSet(AfterSaleKey.afterSaleKey_7, AfterSaleKey.afterSaleKey_8, AfterSaleKey.afterSaleKey_9, AfterSaleKey.afterSaleKey_11, AfterSaleKey.afterSaleKey_21, AfterSaleKey.afterSaleKey_22, AfterSaleKey.afterSaleKey_31, AfterSaleKey.afterSaleKey_32);
        /**
         * 关键节点=已处理
         */
        private static final Set<AfterSaleKey> AFTER_SALE_KEY_FINISH = Sets.newHashSet(AfterSaleKey.afterSaleKey_0, AfterSaleKey.afterSaleKey_1);
        private final String code;

        AfterSaleKey(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return AfterSaleKey.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        /**
         * 关键节点是否为待处理状态
         *
         * @param code
         * @return
         */
        public static boolean isPending(String code) {
            for (AfterSaleKey afterSaleKey : AFTER_SALE_KEY_PENDIND) {
                if (StrUtil.equals(afterSaleKey.getCode(), code)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 关键节点是否为处理中状态
         *
         * @param code
         * @return
         */
        public static boolean isProcessing(String code) {
            for (AfterSaleKey afterSaleKey : AFTER_SALE_KEY_PROCESSIND) {
                if (StrUtil.equals(afterSaleKey.getCode(), code)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 关键节点是否为已处理状态
         *
         * @param code
         * @return
         */
        public static boolean isFinish(String code) {
            for (AfterSaleKey afterSaleKey : AFTER_SALE_KEY_FINISH) {
                if (StrUtil.equals(afterSaleKey.getCode(), code)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 售后状态
     */
    public enum AfterSaleStatus implements ConstantCode<String> {
        /**
         * 售后关闭
         */
        afterSaleStatus_0("0"),
        /**
         * 售后成功
         */
        afterSaleStatus_1("1"),
        /**
         * 客户申请售后，待商家处理
         */
        afterSaleStatus_2("2"),
        /**
         * 客户未处理，平台自动关闭售后
         */
        afterSaleStatus_3("3"),
        /**
         * 客户撤销申请
         */
        afterSaleStatus_4("4"),
        /**
         * 客户修改申请信息，待商家处理
         */
        afterSaleStatus_5("5"),
        /**
         * 客户已寄回商品，待商家收货
         */
        afterSaleStatus_6("6"),
        /**
         * 极速退款中
         */
        afterSaleStatus_8("8"),
        /**
         * 极速退款成功
         */
        afterSaleStatus_9("9"),
        /**
         * 商家不同意申请，待客户处理
         */
        afterSaleStatus_10("10"),
        /**
         * 商家未处理，平台同意申请
         */
        afterSaleStatus_11("11"),
        /**
         * 退款处理中
         */
        afterSaleStatus_12("12"),
        /**
         * 商家同意仅退款
         */
        afterSaleStatus_101("101"),
        /**
         * 商家同意退货退款，待客户寄回商品
         */
        afterSaleStatus_201("201"),
        /**
         * 商家已收货，待商家确认签收
         */
        afterSaleStatus_203("203"),
        /**
         * 商家已签收，待商家确认
         */
        afterSaleStatus_204("204"),
        /**
         * 商家已同意退款
         */
        afterSaleStatus_205("205"),
        /**
         * 商家拒绝退款，待客户处理
         */
        afterSaleStatus_206("206"),
        /**
         * 商家同意换货，待客户寄回商品
         */
        afterSaleStatus_301("301"),
        /**
         * 商家已收货，待商家确认
         */
        afterSaleStatus_303("303"),
        /**
         * 商家已重新发货，待客户确认
         */
        afterSaleStatus_304("304"),
        /**
         * 客户确认收货
         */
        afterSaleStatus_305("305"),
        /**
         * 商家拒绝换货，待客户处理
         */
        afterSaleStatus_306("306"),
        /**
         * 客户未收到货，待重新发起售后
         */
        afterSaleStatus_307("307"),
        ;

        /**
         * 售后完成状态集合
         */
        private static final Set<AfterSaleStatus> AFTER_SALE_STATUS_FINISH = Sets.newHashSet(AfterSaleStatus.afterSaleStatus_0, AfterSaleStatus.afterSaleStatus_1, AfterSaleStatus.afterSaleStatus_4, AfterSaleStatus.afterSaleStatus_9);
        /**
         * 售后关键节点与售后状态的关系
         */
        private static final Set<AfterSaleStatus> AFTER_SALE_KEY_SET_0 = Sets.newHashSet(AfterSaleStatus.afterSaleStatus_0, AfterSaleStatus.afterSaleStatus_4);
        private static final Set<AfterSaleStatus> AFTER_SALE_KEY_SET_1 = Sets.newHashSet(AfterSaleStatus.afterSaleStatus_1, AfterSaleStatus.afterSaleStatus_9);
        private static final Set<AfterSaleStatus> AFTER_SALE_KEY_SET_10 = Sets.newHashSet(AfterSaleStatus.afterSaleStatus_2, AfterSaleStatus.afterSaleStatus_5);
        private static final Set<AfterSaleStatus> AFTER_SALE_KEY_SET_11 = Sets.newHashSet(AfterSaleStatus.afterSaleStatus_101);
        private static final Set<AfterSaleStatus> AFTER_SALE_KEY_SET_20 = Sets.newHashSet(AfterSaleStatus.afterSaleStatus_2, AfterSaleStatus.afterSaleStatus_5, AfterSaleStatus.afterSaleStatus_203, AfterSaleStatus.afterSaleStatus_204);
        private static final Set<AfterSaleStatus> AFTER_SALE_KEY_SET_21 = Sets.newHashSet(AfterSaleStatus.afterSaleStatus_6);
        private static final Set<AfterSaleStatus> AFTER_SALE_KEY_SET_22 = Sets.newHashSet(AfterSaleStatus.afterSaleStatus_205, AfterSaleStatus.afterSaleStatus_206);
        private static final Set<AfterSaleStatus> AFTER_SALE_KEY_SET_30 = Sets.newHashSet(AfterSaleStatus.afterSaleStatus_2, AfterSaleStatus.afterSaleStatus_5, AfterSaleStatus.afterSaleStatus_303);
        private static final Set<AfterSaleStatus> AFTER_SALE_KEY_SET_31 = Sets.newHashSet(AfterSaleStatus.afterSaleStatus_6);
        private static final Set<AfterSaleStatus> AFTER_SALE_KEY_SET_32 = Sets.newHashSet(AfterSaleStatus.afterSaleStatus_304, AfterSaleStatus.afterSaleStatus_305, AfterSaleStatus.afterSaleStatus_306, AfterSaleStatus.afterSaleStatus_307);
        private static final Set<AfterSaleStatus> AFTER_SALE_KEY_SET_7 = Sets.newHashSet(AfterSaleStatus.afterSaleStatus_201, AfterSaleStatus.afterSaleStatus_301);
        private static final Set<AfterSaleStatus> AFTER_SALE_KEY_SET_8 = Sets.newHashSet(AfterSaleStatus.afterSaleStatus_10);
        private static final Set<AfterSaleStatus> AFTER_SALE_KEY_SET_9 = Sets.newHashSet(AfterSaleStatus.afterSaleStatus_3, AfterSaleStatus.afterSaleStatus_8, AfterSaleStatus.afterSaleStatus_11, AfterSaleStatus.afterSaleStatus_12);
        /**
         * 售后申请类型与售后关键节点的关系
         */
        private static final Map<AfterSaleKey, Set<AfterSaleStatus>> AFTER_SALE_APPLY_TYPE_1 = MapUtil.builder(AfterSaleKey.afterSaleKey_0, AFTER_SALE_KEY_SET_0)
                .put(AfterSaleKey.afterSaleKey_1, AFTER_SALE_KEY_SET_1)
                .put(AfterSaleKey.afterSaleKey_7, AFTER_SALE_KEY_SET_7)
                .put(AfterSaleKey.afterSaleKey_8, AFTER_SALE_KEY_SET_8)
                .put(AfterSaleKey.afterSaleKey_9, AFTER_SALE_KEY_SET_9)
                .put(AfterSaleKey.afterSaleKey_10, AFTER_SALE_KEY_SET_10)
                .put(AfterSaleKey.afterSaleKey_11, AFTER_SALE_KEY_SET_11)
                .build();
        private static final Map<AfterSaleKey, Set<AfterSaleStatus>> AFTER_SALE_APPLY_TYPE_2 = MapUtil.builder(AfterSaleKey.afterSaleKey_0, AFTER_SALE_KEY_SET_0)
                .put(AfterSaleKey.afterSaleKey_1, AFTER_SALE_KEY_SET_1)
                .put(AfterSaleKey.afterSaleKey_7, AFTER_SALE_KEY_SET_7)
                .put(AfterSaleKey.afterSaleKey_8, AFTER_SALE_KEY_SET_8)
                .put(AfterSaleKey.afterSaleKey_9, AFTER_SALE_KEY_SET_9)
                .put(AfterSaleKey.afterSaleKey_20, AFTER_SALE_KEY_SET_20)
                .put(AfterSaleKey.afterSaleKey_21, AFTER_SALE_KEY_SET_21)
                .put(AfterSaleKey.afterSaleKey_22, AFTER_SALE_KEY_SET_22)
                .build();
        private static final Map<AfterSaleKey, Set<AfterSaleStatus>> AFTER_SALE_APPLY_TYPE_3 = MapUtil.builder(AfterSaleKey.afterSaleKey_0, AFTER_SALE_KEY_SET_0)
                .put(AfterSaleKey.afterSaleKey_1, AFTER_SALE_KEY_SET_1)
                .put(AfterSaleKey.afterSaleKey_7, AFTER_SALE_KEY_SET_7)
                .put(AfterSaleKey.afterSaleKey_8, AFTER_SALE_KEY_SET_8)
                .put(AfterSaleKey.afterSaleKey_9, AFTER_SALE_KEY_SET_9)
                .put(AfterSaleKey.afterSaleKey_30, AFTER_SALE_KEY_SET_30)
                .put(AfterSaleKey.afterSaleKey_31, AFTER_SALE_KEY_SET_31)
                .put(AfterSaleKey.afterSaleKey_32, AFTER_SALE_KEY_SET_32)
                .build();
        /**
         * 售后申请类型与集合关系
         */
        private static final Map<String, Map<AfterSaleKey, Set<AfterSaleStatus>>> AFTER_SALE_APPLY_TYPE_MAP = MapUtil.builder(AfterSaleApplyType.only_refund.getCode(), AFTER_SALE_APPLY_TYPE_1)
                .put(AfterSaleApplyType.return_and_refund.getCode(), AFTER_SALE_APPLY_TYPE_2)
                .put(AfterSaleApplyType.exchange.getCode(), AFTER_SALE_APPLY_TYPE_3)
                .build();
        /**
         * 售后状态与售后协商记录变更类型的关系
         */
        private static final Map<String, AfterSaleChangeType> AFTER_SALE_CHANGE_TYPE_MAP = MapUtil.builder(AfterSaleStatus.afterSaleStatus_6.getCode(), AfterSaleChangeType.change_customer_logistics)
                .put(AfterSaleStatus.afterSaleStatus_304.getCode(), AfterSaleChangeType.change_business_logistics)
                .build();

        private final String code;

        AfterSaleStatus(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return AfterSaleStatus.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        /**
         * 根据售后申请类型和售后当前状态映射售后单关键节点
         *
         * @param afterSaleApplyType 售后申请类型
         * @param afterSaleStatus    售后状态
         * @return 售后关键节点
         */
        public static AfterSaleKey getAfterSaleKey(String afterSaleApplyType, String afterSaleStatus) {
            AfterSaleStatus afterSaleStatusEnum = getEnum(afterSaleStatus);
            Map<AfterSaleKey, Set<AfterSaleStatus>> afterSaleKeySetMap = AFTER_SALE_APPLY_TYPE_MAP.get(afterSaleApplyType);
            if (CollectionUtil.isNotEmpty(afterSaleKeySetMap)) {
                for (Map.Entry<AfterSaleKey, Set<AfterSaleStatus>> afterSaleKeySetEntry : afterSaleKeySetMap.entrySet()) {
                    AfterSaleKey afterSaleKey = afterSaleKeySetEntry.getKey();
                    Set<AfterSaleStatus> afterSaleStatusSet = afterSaleKeySetEntry.getValue();
                    if (afterSaleStatusSet.contains(afterSaleStatusEnum)) {
                        return afterSaleKey;
                    }
                }
            }

            throw new WeikbestException("无法根据售后申请类型和售后当前状态匹配到具体的售后关键节点!");
        }

        /**
         * 获取售后状态
         *
         * @param code
         * @return
         */
        public static AfterSaleStatus getEnum(String code) {
            AfterSaleStatus[] values = AfterSaleStatus.values();
            for (AfterSaleStatus afterSaleStatus : values) {
                if (StrUtil.equals(afterSaleStatus.getCode(), code)) {
                    return afterSaleStatus;
                }
            }

            throw new WeikbestException(code + "无法匹配售后状态!");
        }

        /**
         * 售后状态是否完成
         *
         * @param code
         * @return
         */
        public static boolean isFinish(String code) {
            for (AfterSaleStatus afterSaleStatusFinish : AFTER_SALE_STATUS_FINISH) {
                if (StrUtil.equals(afterSaleStatusFinish.getCode(), code)) {
                    return true;
                }
            }
            return false;
        }


        /**
         * 售后状态与售后协商记录变更类型的关系
         *
         * @param code 售后状态
         * @return 售后关键节点
         */
        public static AfterSaleChangeType getAfterSaleChangeType(String code) {
            AfterSaleChangeType afterSaleChangeType = AFTER_SALE_CHANGE_TYPE_MAP.get(code);
            if (ObjectUtil.isNotNull(afterSaleChangeType)) {
                return afterSaleChangeType;
            }

            // 匹配不到，默认返回状态变更
            return AfterSaleChangeType.change_status;
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }


    /**
     * 售后阶段
     */
    public enum AfterSaleStage implements ConstantCode<String> {
        /**
         * 提交申请
         */
        afterSaleStage_1("1"),
        /**
         * 商家同意
         */
        afterSaleStage_2("2"),
        /**
         * 寄回商品
         */
        afterSaleStage_3("3"),
        /**
         * 商家退款
         */
        afterSaleStage_4("4"),
        /**
         * 退款成功
         */
        afterSaleStage_5("5"),
        /**
         * 商家拒绝
         */
        afterSaleStage_6("6"),
        /**
         * 退款失败
         */
        afterSaleStage_7("7"),
        /**
         * 商家发货
         */
        afterSaleStage_8("8"),
        /**
         * 换货成功
         */
        afterSaleStage_9("9"),
        /**
         * 售后关闭
         */
        afterSaleStage_10("10"),
        ;

        private final String code;

        AfterSaleStage(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return AfterSaleStage.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 售后协商记录变更类型
     */
    public enum AfterSaleChangeType implements ConstantCode<String> {
        /**
         * 客户申请
         */
        customer_apply("1"),
        /**
         * 状态变更
         */
        change_status("2"),
        /**
         * 客户物流信息变更
         */
        change_customer_logistics("3"),
        /**
         * 商家物流信息变更
         */
        change_business_logistics("4"),
        ;

        private final String code;

        AfterSaleChangeType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return AfterSaleChangeType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 售后申请来源
     */
    public enum ApplySourceType implements ConstantCode<String> {
        /**
         * 客户申请
         */
        customer("1"),
        /**
         * 商家代申请
         */
        merchat("2"),
        ;

        private final String code;

        ApplySourceType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return ApplySourceType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 拒绝原因
     */
    public enum RejectReason implements ConstantCode<String> {
        /**
         * 已协商一致不退款
         */
        rejectReason_1("1"),
        /**
         * 买家要求退款和应退金额不一致
         */
        rejectReason_2("2"),
        /**
         * 买家退/换货商品不在承诺的售后范围
         */
        rejectReason_3("3"),
        /**
         * 买家退/换货商品为空包裹
         */
        rejectReason_4("4"),
        /**
         * 买家退/换货商品少件/漏发
         */
        rejectReason_5("5"),
        /**
         * 其他
         */
        other("9"),
        ;

        private final String code;

        RejectReason(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return RejectReason.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 退款去向
     */
    public enum RefundTrend implements ConstantCode<String> {
        /**
         * 原路返回
         */
        refundTrend_1("1"),
        ;

        private final String code;

        RefundTrend(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return RefundTrend.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 退款状态
     */
    public enum RefundStatus implements ConstantCode<String> {
        /**
         * 未退款
         */
        refundStatus_0("0"),
        /**
         * 已退款
         */
        refundStatus_1("1"),
        /**
         * 退款中
         */
        refundStatus_2("2"),
        ;

        private final String code;

        RefundStatus(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return RefundStatus.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }


    /**
     * 退款申请原因
     */
    public enum AfterSaleApplyReason implements ConstantCode<String> {
        /**
         * 少发/漏发
         */
        afterSaleApplyReason_1("1"),
        /**
         * 质量问题
         */
        afterSaleApplyReason_2("2"),
        /**
         * 货物与描述不符
         */
        afterSaleApplyReason_3("3"),
        /**
         * 未按约定时间发货
         */
        afterSaleApplyReason_4("4"),
        /**
         * 发票问题
         */
        afterSaleApplyReason_5("5"),
        /**
         * 卖家发错货
         */
        afterSaleApplyReason_6("6"),
        /**
         * 假冒品牌
         */
        afterSaleApplyReason_7("7"),
        /**
         * 退运费
         */
        afterSaleApplyReason_8("8"),
        /**
         * 尺寸拍错/不喜欢/效果不好
         */
        afterSaleApplyReason_9("9"),
        /**
         * 其他
         */
        afterSaleApplyReason_10("10"),
        ;

        private final String code;

        AfterSaleApplyReason(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return AfterSaleApplyReason.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }


    /**
     * 第三方平台类型
     */
    public enum CustThirdType implements ConstantCode<String> {
        /**
         * 微信
         */
        weixin("1"),
        ;

        private final String code;

        CustThirdType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return CustThirdType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }


    /**
     * 详情字段类型
     */
    public enum DetailFieldType implements ConstantCode<String> {
        /**
         * 文本
         */
        text("1"),
        /**
         * 链接
         */
        link("2"),
        ;

        private final String code;

        DetailFieldType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return DetailFieldType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }


    /**
     * 快递物流图片类型
     */
    public enum CourierImgType implements ConstantCode<String> {
        /**
         * 商家发货
         */
        courierImgType_1("1"),
        /**
         * 客户寄回
         */
        courierImgType_2("2"),
        /**
         * 商家再次发货
         */
        courierImgType_3("3"),
        ;

        private final String code;

        CourierImgType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return CourierImgType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }


    /**
     * 微信小程序类型
     */
    public enum WxMiniappType implements ConstantCode<String> {
        /**
         * 开发版
         */
        developer("developer", "develop"),
        /**
         * 体验版
         */
        trial("trial", "trial"),
        /**
         * 正式版
         */
        formal("formal", "release"),
        ;

        private final String code;
        private final String jumpWxaEnvVersion;

        WxMiniappType(String code, String jumpWxaEnvVersion) {
            this.code = code;
            this.jumpWxaEnvVersion = jumpWxaEnvVersion;
        }

        public static String getDictTypeNumber() {
            return WxMiniappType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        /**
         * 获取微信小程序类型
         *
         * @param code
         * @return
         */
        public static WxMiniappType getWxMiniappTypeByCode(String code) {
            WxMiniappType[] values = WxMiniappType.values();
            for (WxMiniappType value : values) {
                if (StrUtil.equals(value.getCode(), code)) {
                    return value;
                }
            }
            // 默认返回正式版
            return WxMiniappType.formal;
        }

        @Override
        public String getCode() {
            return this.code;
        }

        public String getJumpWxaEnvVersion() {
            return jumpWxaEnvVersion;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }


    /**
     * 支付状态
     */
    public enum PayStatus implements ConstantCode<String> {
        /**
         * 未支付
         */
        no_pay("1"),
        /**
         * 支付中
         */
        paying("2"),
        /**
         * 支付成功
         */
        success("3"),
        /**
         * 支付失败
         */
        fail("4"),
        ;

        private final String code;

        PayStatus(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return PayStatus.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 商户类别
     */
    public enum BusinessType implements ConstantCode<String> {
        /**
         * 普通商户
         */
        normal("1"),
        /**
         * 品牌商户
         */
        brand("2"),
        /**
         * 平台商户
         */
        platform("3"),

        ;

        private final String code;

        BusinessType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return BusinessType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }


    /**
     * 店铺类型
     */
    public enum ShopType implements ConstantCode<String> {
        /**
         * 非自营店铺
         */
        normal("1"),
        /**
         * 自营店铺
         */
        myself("2"),
        ;

        private final String code;

        ShopType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return ShopType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 优惠券类型
     */
    public enum CouponProdType implements ConstantCode<String> {
        /**
         * 全部商品
         */
        all_prod("1"),
        /**
         * 部分商品
         */
        special_prod("2"),
        ;

        private final String code;

        CouponProdType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return CouponProdType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 优惠券领取人类型
     */
    public enum RestrictUserType implements ConstantCode<String> {
        /**
         * 全部客户
         */
        all_customer("1"),
        /**
         * 部分客户
         */
        special_customer("2"),
        ;

        private final String code;

        RestrictUserType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return RestrictUserType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 优惠券使用门槛类型
     */
    public enum CouponUseType implements ConstantCode<String> {
        /**
         * 无使用门槛
         */
        none("0"),
        /**
         * 订单满多少金额可用
         */
        order_amount("1"),
        ;

        private final String code;

        CouponUseType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return CouponUseType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 优惠券样式类型
     */
    public enum CouponThemeType implements ConstantCode<String> {
        /**
         * 默认图片
         */
        default_img("1"),
        /**
         * 上传图片
         */
        upload_img("2"),
        ;

        private final String code;

        CouponThemeType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return CouponThemeType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 优惠券生效类型
     */
    public enum CouponEnableType implements ConstantCode<String> {
        /**
         * 领券后立即生效
         */
        now("1"),
        /**
         * 延迟生效
         */
        delay("2"),
        ;

        private final String code;

        CouponEnableType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return CouponEnableType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }


    /**
     * 微信卡包优惠券背景色
     */
    public enum CouponBackgroundColor implements ConstantCode<String> {
        /**
         * Color010
         */
        Color010("Color010"),
        /**
         * Color020
         */
        Color020("Color020"),
        /**
         * Color030
         */
        Color030("Color030"),
        /**
         * Color040
         */
        Color040("Color040"),
        /**
         * Color050
         */
        Color050("Color050"),
        /**
         * Color060
         */
        Color060("Color060"),
        /**
         * Color070
         */
        Color070("Color070"),
        /**
         * Color080
         */
        Color080("Color080"),
        /**
         * Color090
         */
        Color090("Color090"),
        /**
         * Color100
         */
        Color100("Color100"),
        ;

        private final String code;

        CouponBackgroundColor(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return CouponBackgroundColor.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 投诉图片类型
     */
    public enum ComplaintImgType implements ConstantCode<String> {
        /**
         * 客户申请投诉
         */
        type_1("1"),
        /**
         * 协商上传凭证
         */
        type_2("2"),
        ;

        private final String code;

        ComplaintImgType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return ComplaintImgType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }


    /**
     * 投诉状态（商家处理）
     */
    public enum ComplaintStatus implements ConstantCode<String> {
        /**
         * 1-发起投诉
         */
        complaintStatus_1("1"),
        /**
         * 100-商家同意和解
         */
        complaintStatus_100("100"),
        /**
         * 101-商家不同意和解
         */
        complaintStatus_101("101"),
        /**
         * 109-商家处理超时
         */
        complaintStatus_109("109"),
        /**
         * 200-用户认可和解
         */
        complaintStatus_200("200"),
        /**
         * 201-用户不认可和解
         */
        complaintStatus_201("201"),
        /**
         * 209-用户撤销投诉
         */
        complaintStatus_209("209"),
        ;

        private final String code;

        ComplaintStatus(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return ComplaintStatus.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 投诉状态（商家处理）
     */
    public enum ComplaintBusiStatus implements ConstantCode<String> {
        /**
         * 未处理
         */
        complaintBusiStatus_0("0"),
        /**
         * 处理中
         */
        complaintBusiStatus_1("1"),
        /**
         * 已处理
         */
        complaintBusiStatus_2("2"),
        ;

        private final String code;

        ComplaintBusiStatus(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return ComplaintBusiStatus.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }

    /**
     * 分账状态
     */
    public enum ReceiveRecordStatus implements ConstantCode<String> {

        /**
         * 分账中
         */
        code_0("0"),
        /**
         * 已分账
         */
        code_1("1"),
        /**
         * 回退中
         */
        code_2("2"),
        /**
         * 已回退
         */
        code_3("3"),
        /**
         * 分账失败
         */
        code_4("4"),
        /**
         * 回退失败
         */
        code_5("5"),
        /**
         * 已关闭
         */
        code_6("6"),

        /**
         * 已清算
         */
        code_8("8"),
        ;

        private final String code;

        ReceiveRecordStatus(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return ReceiveRecordStatus.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }

    }

    /**
     * 分账状态
     */
    public enum FinanceType implements ConstantCode<String> {

        /**
         * 订单收入
         */
        financetype_1("1"),
        /**
         * 分账押金退回
         */
        financetype_10("10"),
        /**
         * 分账押金扣款
         */
        financetype_20("20"),
        /**
         * 售后退款
         */
        financetype_30("30"),
        /**
         * 平台售后分账回退
         */
        financetype_40("40"),
        /**
         * 技术服务费-自然流量
         */
        financetype_50("50"),
        /**
         * 技术服务费回退
         */
        financetype_60("60"),
        ;

        private final String code;

        FinanceType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return FinanceType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }

    }

    /**
     * 对账单类型
     */
    public enum StatementType implements ConstantCode<String> {

        /**
         * 卖出交易
         */
        statementType_1("1"),
        /**
         * 退款交易
         */
        statementType_2("2"),

        ;

        private final String code;

        StatementType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return StatementType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }

    }


    /**
     * 结算状态
     */
    public enum SettleType implements ConstantCode<String> {

        /**
         * 未结算
         */
        settleType_0("0"),
        /**
         * 已结算
         */
        settleType_1("1"),

        ;

        private final String code;

        SettleType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return SettleType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }

    }

    /**
     * 资金流向
     */
    public enum CapitalFlowType implements ConstantCode<String> {

        /**
         * 1-收入
         */
        capitalFlowType_1("1"),
        /**
         * 2-支出
         */
        capitalFlowType_2("2"),

        ;

        private final String code;

        CapitalFlowType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return CapitalFlowType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }

    }

    /**
     * 访问类型
     */
    public enum VisitType implements ConstantCode<String> {
        /**
         * 访问店铺
         */
        visitType_1("1"),
        /**
         * 访问商品
         */
        visitType_2("2"),
        ;

        private final String code;

        VisitType(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return VisitType.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }


    /**
     * 广告回传来源类型
     */
    public enum AdReturnSource implements ConstantCode<String> {
        /**
         * 手动调用键入
         */
        MANUAL_INPUT("1"),
        /**
         * 返回页
         */
        RETURN_PAGES_INPUT("2"),
        /**
         * 广告引流
         */
        ADVERTISING_LEADS_INPUT("3"),
        ;

        private final String code;

        AdReturnSource(String code) {
            this.code = code;
        }

        public static String getDictTypeNumber() {
            return AdReturnSource.values()[WeikbestConstant.ZERO_INT].dictTypeNumber();
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public boolean isEquals(String anotherCode) {
            return StrUtil.equals(this.code, anotherCode);
        }
    }
    public static void main(String[] args) {

        String code = AfterSaleStatus.getAfterSaleKey("3", "304").getCode();
        System.out.println(code);
    }
}


