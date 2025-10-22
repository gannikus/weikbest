package com.weikbest.pro.saas.merchat.busi.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商户与商户账户关联表
 * </p>
 *
 * @author wisdomelon
 * @since 2023-02-23
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_busi_user_relate")
@ApiModel(value = "BusiUserRelate对象", description = "商户与商户账户关联表")
public class BusiUserRelate implements Serializable {

    public static final String ID = "id";
    public static final String BUSINESS_ID = "business_id";
    public static final String BUSINESS_USER_ID = "business_user_id";
    public static final String IS_MAIN_USER = "is_main_user";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户ID")
    @TableField("business_id")
    private Long businessId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户用户ID")
    @TableField("business_user_id")
    private Long businessUserId;
    @ApiModelProperty("是否主账号 0-否 1-是")
    @TableField(value = "is_main_user", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isMainUser;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
