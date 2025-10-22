package com.weikbest.pro.saas.common.transfervo.resp;

import lombok.Getter;

/**
 * @author wisdomelon
 * @date 2019/6/11 0011
 * @project saas
 * @jdk 1.8
 * 常量枚举类， 用于存放公共常量数据
 */
@Getter
public enum ResultConstant {

    /**
     * 成功返回
     */
    SUCCESS(0, "success!"),
    /**
     * 失败
     */
    EXCEPTION_FAIL(9900, "失败!"),
    /**
     * 该条记录已不存在
     */
    DATA_NOT_FOUND(9900, "该条记录已不存在!"),
    /**
     * 该条记录的唯一标识已存在
     */
    DATA_EXISTS_FOUND(9901, "该条记录的唯一标识已存在!"),
    /**
     * 验证码已失效
     */
    VERIFY_TIMEOUT_FAIL(9900, "验证码已失效，请重新输入!"),
    /**
     * 验证码验证失败
     */
    VERIFY_CHECK_FAIL(9900, "验证码不正确，请重新输入!"),
    /**
     * 手机号格式不正确
     */
    PHONE_CHECK_FAIL(9901, "手机号格式不正确，请重新输入!"),
    /**
     * 该手机号已被注册，请重新输入！
     */
    PHONE_REGISTER_FAIL(2001, "该手机号已被注册，请重新输入！"),
    /**
     * 原密码不正确，请重新输入！
     */
    PHONE_PASSWORD_FAIL(2003, "原密码不正确，请重新输入！"),
    /**
     * 请求参数校验失败
     */
    VALID_FAIL(9900, "请求参数校验失败!"),
    /**
     * 数据库操作失败
     */
    DB_OPERATE_ERROR(9999, "数据库操作失败!"),
    /**
     * 无token，请重新登录
     */
    TOKEN_NOT_EXIST(1001, "Token已失效或不存在，请重新登录!"),
    /**
     * 无token警告
     */
    TOKEN_NOT_EXIST_WARN(1001, "Token已失效或不存在!"),
    /**
     * Token认证失败，请重新登录
     */
    TOKEN_AUTH_FAIL(1001, "Token认证失败，请重新登录!"),
    /**
     * Token认证失败，您的账户在其他设备上进行了登录，请重新登录
     */
    OTHER_IP_LOGIN_FAIL(1001, "您的账户在其他设备上进行了登录，请重新登录!"),
    /**
     * 用户登录已过期，请重新登录
     */
    USER_NOT_EXIST(1001, "用户登录已过期，请重新登录!"),

    /**
     * ApiKey为空
     */
    API_KEY_IS_NULL(1001, "ApiKey为空!"),

    /**
     * ApiKey为空
     */
    API_KEY_NOT_EXIST(1001, "ApiKey不存在!"),
    /**
     * 时间戳为空
     */

    TIMESTAMP_NOT_EXIST(1001, "时间戳为空!"),
    /**
     * 时间戳无效
     */

    TIMESTAMP_NOT_INVALID(1001, "时间戳无效!"),
    /**
     * 签名为空
     */
    SIGN_NOT_EXIST(1001, "签名为空!"),

    /**
     * 签名不一致
     */
    SIGN_NOT_MATCH(1001, "签名不一致!"),
    /**
     * 文件资源上传失败
     */
    UPLOADFILE_FAIL(2001, "文件资源上传失败!"),
    /**
     * 文件资源下载失败
     */
    DOWNLOADFILE_FAIL(2002, "文件资源下载失败!"),
    /**
     * 运行时异常
     */
    RUNTIME_FAIL(9900, "运行时异常!"),
    /**
     * 数据为空异常
     */
    NULL_FAIL(9901, "数据为空异常!"),
    /**
     * 类型转换异常
     */
    CLASSCAST_FAIL(9902, "类型转换异常!"),
    /**
     * IO异常
     */
    IO_FAIL(9903, "IO异常!"),
    /**
     * 未知方法异常
     */
    NOSUCHMETHOD_FAIL(9904, "未知方法异常!"),
    /**
     * 数组越界异常
     */
    INDEXOUTOFBOUNDS_FAIL(9905, "数组越界异常!"),
    /**
     * 服务器内存溢出！
     */
    STACKOVERFLOW_FAIL(9906, "服务器内存溢出!"),
    /**
     * 服务器内部错误
     */
    SERVER_500_ERROR(9500, "服务器内部错误!"),

    /**
     * 订单已支付
     */
    ORDER_PAY_ERROR(3301, "订单已支付，请重新查询订单信息"),
    /**
     * 订单支付超时
     */
    ORDER_PAY_TIMEOUT_ERROR(3302, "订单支付超时，请重新下单"),

    /**
     * 第三方服务调用失败
     */
    THIRD_SERVICE_FAIL(5555, "第三方服务调用失败!"),


    /**
     * 商户信息不存在，请确认您的登录身份！
     */
    BUSI_USER_NOT_EXIST(4404, "商户信息不存在，请确认您的登录身份！"),
    ;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    ResultConstant(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
