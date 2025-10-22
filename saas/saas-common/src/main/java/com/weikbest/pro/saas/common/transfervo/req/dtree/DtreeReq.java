package com.weikbest.pro.saas.common.transfervo.req.dtree;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wisdomelon
 * @date 2019/6/22 0022
 * @project saas
 * @jdk 1.8
 */
@Data
public class DtreeReq implements Serializable {

    private static final long serialVersionUID = -5650785780656774709L;

    /**
     * 节点ID
     */
    private String nodeId;
    /**
     * 上级节点ID
     */
    private String parentId;
    /**
     * 节点名称
     */
    private String context;
    /**
     * 选中状态
     */
    private String checked;
    /**
     * 复选框级别
     */
    private String dataType;
    /**
     * 初始选中状态
     */
    private String initchecked;
    /**
     * 是否叶子节点
     */
    @JsonProperty("leaf")
    private Boolean leaf;
    /**
     * 层级
     */
    private String level;
    /**
     * 展开状态
     */
    @JsonProperty("spread")
    private Boolean spread;
    /**
     * 扩展字段
     */
    private Object basicData;
}
