package com.weikbest.pro.saas.sysmerchat.business.module.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/1
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "SysBusiListVO对象", description = "平台商家账号分页查询结果")
public class SysBusiListVO {

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户账号ID")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户ID")
    @TableField("business_id")
    private Long businessId;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("是否主账号 0-否 1-是")
    private String isMainUser;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("备注")
    private String description;
    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("创建人")
    private Long creator;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("更新人")
    private Long modifier;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @Version
    private Date gmtModified;

    @ApiModelProperty("更新人名称")
    private String modifierName;


    @ApiModelProperty("商户类别 1-普通商户 2-品牌商户 3-特约商户")
    private String businessType;
}
