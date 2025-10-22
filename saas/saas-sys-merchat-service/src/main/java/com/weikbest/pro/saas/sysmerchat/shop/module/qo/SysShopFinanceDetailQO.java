package com.weikbest.pro.saas.sysmerchat.shop.module.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/12/24
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "SysShopFinanceDetailQO对象", description = "平台财务概况查询")
public class SysShopFinanceDetailQO  implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单号")
    private String number;

    @ApiModelProperty("微信业务单号")
    private String wxOrderId;

    @ApiModelProperty("账务类型 1-订单收入 10-分账押金退回 20-分账押金扣款 30-售后退款 40-平台售后分账回退 50-技术服务费-自然流量")
    private String financeType;

    @ApiModelProperty("资金流向 1-收入 2-支出")
    private String capitalFlowType;

    @ApiModelProperty("创建开始日期")
    private Date startDate;

    @ApiModelProperty("创建结束日期")
    private Date endDate;
}
