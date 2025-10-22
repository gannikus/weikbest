package com.weikbest.pro.saas.common.delay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 延时队列任务
 *
 * @author wisdomelon
 * @date 2021/6/1
 * @project saas
 * @jdk 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "DelayTaskVO对象", description = "延时队列中的对象")
public class DelayTaskVO {

    @ApiModelProperty("延时队列任务ID")
    private String taskId;

    @ApiModelProperty("延时队列任务名称")
    private String name;

    @ApiModelProperty("延时队列任务超时时间")
    private Date date;

}
