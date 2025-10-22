package com.weikbest.pro.saas.applet.commodity.module.vo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdJumpLinkVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@ApiModel(value = "ProdLeftSlideVO对象", description = "商品左滑设置拆分表")
public class AppProdLeftSlideVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("左滑切换弹框文本")
    private String switchText;

    @ApiModelProperty("左滑跳转 0-不跳转 1-跳转")
    private String switchJump;

    @ApiModelProperty("主页跳转 0-不跳转 1-跳转")
    private String indexJump;

    @ApiModelProperty("集合页样式_0:不开启 1:文字样式 2:购物红包 3:提示样式 4:商场红包")
    private String joinpageState;

    @ApiModelProperty("聚合页 0不开启- 1-开启")
    private String joinpageJump;

    @ApiModelProperty("聚合页弹窗图片链接")
    private String joinpageOpenUrl;

    @ApiModelProperty("聚合页banner图片链接")
    private String joinpageBannerUrl;

    @ApiModelProperty("右上角返回图标 0-不开启- 1-开启")
    private String switchRight;

    @ApiModelProperty("商品跳转链接信息集合")
    private List<ProdJumpLinkVO> prodJumpLinkVOArrayList = new ArrayList<>();

    @ApiModelProperty("第一个商品信息")
    private AppProdLeftSlideProdDetailVO appProdLeftSlideProdDetailVO;

}