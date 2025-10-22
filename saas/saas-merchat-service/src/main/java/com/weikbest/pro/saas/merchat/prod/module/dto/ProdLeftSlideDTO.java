package com.weikbest.pro.saas.merchat.prod.module.dto;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品左滑设置拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdLeftSlideDTO对象", description = "商品左滑设置拆分表")
public class ProdLeftSlideDTO implements Serializable {

    private static final long serialVersionUID = -8726884204964755956L;

    @ApiModelProperty(value = "左滑切换弹框文本", required = true)
    private String switchText;

    @NotBlank(message = "左滑跳转不为空!")
    @ApiModelProperty(value = "左滑跳转 0-不跳转 1-跳转", required = true)
    private String switchJump;

    @NotBlank(message = "主页跳转不为空!")
    @ApiModelProperty(value = "主页跳转 0-不跳转 1-跳转", required = true)
    private String indexJump;

    @NotBlank(message = "集合页样式不为空!")
    @ApiModelProperty(value = "集合页样式_0:不开启 1:文字样式 2:购物红包 3:提示样式 4:商场红包", required = true)
    private String joinpageState;

    @ApiModelProperty("右上角返回图标 0-不开启- 1-开启")
    private String switchRight;

    @NotBlank(message = "聚合页不为空!")
    @ApiModelProperty(value = "聚合页 0不开启- 1-开启", required = true)
    private String joinpageJump;

    @ApiModelProperty("聚合页弹窗图片链接")
    @TableField(value = "joinpage_open_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String joinpageOpenUrl;

    @ApiModelProperty("聚合页banner图片链接")
    @TableField(value = "joinpage_banner_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String joinpageBannerUrl;

    @ApiModelProperty("商品跳转链接信息集合")
    private List<ProdJumpLinkDTO> prodJumpLinkDTOList = new ArrayList<>();

}