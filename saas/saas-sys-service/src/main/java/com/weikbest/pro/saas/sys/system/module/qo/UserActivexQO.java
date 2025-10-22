package com.weikbest.pro.saas.sys.system.module.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统用户控件关联表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-20
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "UserActivexQO对象", description = "系统用户控件关联表")
public class UserActivexQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("工号")
    private String number;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("控件数据状态 0-已删除 1-可用")
    private String activexDataStatus;


}