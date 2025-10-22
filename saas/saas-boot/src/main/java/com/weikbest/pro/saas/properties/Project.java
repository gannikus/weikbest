package com.weikbest.pro.saas.properties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wisdomelon
 * @date 2021/4/30
 * @project saas
 * @jdk 1.8
 */
@Data
@Component
@ConfigurationProperties(prefix = "project")
@ApiModel(value = "Project对象", description = "Project")
public class Project {

    @ApiModelProperty("title")
    private String title;

    @ApiModelProperty("createtime")
    private String createtime;
}
