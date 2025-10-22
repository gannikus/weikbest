package com.weikbest.pro.saas.common.transfervo.resp.dtree;

import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wisdomelon
 * @date 2019/6/11 0011
 * @project saas
 * @jdk 1.8
 * 树类
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel(value = "Dtree对象", description = "树形对象")
public class Dtree implements Serializable {

    @ApiModelProperty("节点ID")
    private String id;

    @ApiModelProperty("上级节点ID")
    private String parentId;

    @ApiModelProperty("节点名称")
    private String title;

    @ApiModelProperty("是否展开节点")
    private Boolean spread;

    @ApiModelProperty("是否最后一级节点")
    private Boolean last;

    @ApiModelProperty("是否隐藏")
    private Boolean hide;

    @ApiModelProperty("是否禁用")
    private Boolean disabled;

    @ApiModelProperty("自定义图标class")
    private String iconClass;

    @ApiModelProperty("自定义需要存储在树节点中的数据")
    private Object basicData;

    @ApiModelProperty("复选框集合")
    private List<CheckArr> checkArr = new ArrayList<CheckArr>();

    @ApiModelProperty("单个复选框, 0-不选中 1-选中 2-半选，默认0-不选中")
    private String checked = "0";

    @ApiModelProperty("子节点集合")
    private List<Dtree> children = new ArrayList<Dtree>();

    public Dtree(String id, String title, String parentId) {
        this.id = id;
        this.title = title;
        this.parentId = parentId;
    }

    public Dtree(String id, String title, String parentId, String basicData) {
        this.id = id;
        this.title = title;
        this.parentId = parentId;
        this.basicData = basicData;
    }

    public Dtree(String id, String title, String parentId, boolean last) {
        this.id = id;
        this.title = title;
        this.parentId = parentId;
        this.last = last;
    }

    public Dtree(String id, String title, String parentId, String checked, String basicData) {
        this.id = id;
        this.title = title;
        this.parentId = parentId;
        this.checked = checked;
        this.basicData = basicData;
    }

    /**
     * 默认根节点
     *
     * @param rootTitle
     * @return
     */
    public static Dtree defaultRoot(String rootTitle) {
        return new Dtree(WeikbestConstant.ROOT_ID, rootTitle, WeikbestConstant.ROOT_PARENT_ID);
    }

    public void addCheckArr(CheckArr checkArr) {
        this.checkArr.add(checkArr);
    }
}
