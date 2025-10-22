package com.weikbest.pro.saas.common.ext.user.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/16
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserExt对象", description = "用户字典")
public class UserExt {

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id ")
    private Long id;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("手机号")
    private String phone;

    public static UserExt defaultUserExt() {
        return new UserExt().setId(WeikbestConstant.ZERO_LONG).setName("系统").setAvatar("").setPhone("");
    }
}
