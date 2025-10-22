package com.weikbest.pro.saas.sys.system.module.qo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "UserQO对象", description = "系统用户表")
public class UserQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("工号")
    private String number;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("是否管理员 0-否 1-是")
    private String isSuper;

    @ApiModelProperty("是否系统用户 0-否 1-是")
    private String isSysuser;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("部门ID")
    private Long orgId;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;

    @ApiModelProperty("关联类型集合 0-系统平台用户 1-商家端用户 2-小程序端用户")
    private List<String> relateTypeList;


}