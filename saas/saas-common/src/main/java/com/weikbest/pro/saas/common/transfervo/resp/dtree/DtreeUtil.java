package com.weikbest.pro.saas.common.transfervo.resp.dtree;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/9
 */

public class DtreeUtil {

    /**
     * 未选中
     */
    public static final String CHECKED_0 = "0";
    /**
     * 全选中
     */
    public static final String CHECKED_1 = "1";
    /**
     * 半选中
     */
    public static final String CHECKED_2 = "2";

    /**
     * 将list数据转换为层级数据
     *
     * @param dtreeList 原始数据
     * @param parentId  父节点ID
     * @return
     */
    public static List<Dtree> converListToLevel(List<Dtree> dtreeList, String parentId) {
        if (CollectionUtil.isEmpty(dtreeList)) {
            return dtreeList;
        }

        // 分组
        Map<String, List<Dtree>> parentIdDtreeListMap = dtreeList.stream().collect(Collectors.groupingBy(Dtree::getParentId));
        return converListToLevelChild(parentIdDtreeListMap, parentId);
    }

    /**
     * 通过分组数据查找下级数据
     *
     * @param parentIdDtreeListMap 分组数据
     * @param parentId             父节点ID
     * @return
     */
    private static List<Dtree> converListToLevelChild(Map<String, List<Dtree>> parentIdDtreeListMap, String parentId) {
        // 找到节点
        List<Dtree> resultList = parentIdDtreeListMap.get(parentId);
        if (CollectionUtil.isEmpty(resultList)) {
            return resultList;
        }

        for (Dtree dtree : resultList) {
            // 找下级节点
            String id = dtree.getId();
            List<Dtree> children = converListToLevelChild(parentIdDtreeListMap, id);
            dtree.setChildren(children);
        }

        return resultList;
    }

    /**
     * 修正选中状态
     *
     * @param dtreeList 待修正数据集
     */
    public static void correctionChecked(List<Dtree> dtreeList) {
        for (Dtree dtree : dtreeList) {
            // 如果一开始就是0，说明一开始就没有选中，就排除掉
            if (StrUtil.equals(dtree.getChecked(), CHECKED_1)) {
                correctionChecked(dtreeList, dtree, dtreeList.stream().filter(subTree1 -> StrUtil.equals(dtree.getId(), subTree1.getParentId())).collect(Collectors.toList()));
            }
        }
    }

    /**
     * 修正选中状态
     *
     * @param dtreeList   全部数据节点
     * @param dtree       当前数据节点
     * @param subTreeList 当前数据节点的所有下级数据
     */
    private static void correctionChecked(List<Dtree> dtreeList, Dtree dtree, List<Dtree> subTreeList) {
        if (CollectionUtil.isEmpty(subTreeList)) {
            return;
        }
        // 如果所有子节点有部分未选中，则修改当前节点的选中状态为 2-半选
        long hasAuthPermCount = subTreeList.stream().filter(permDtree -> StrUtil.equals(permDtree.getChecked(), DtreeUtil.CHECKED_1)).count();
        if (!NumberUtil.equals(NumberUtil.toBigDecimal(subTreeList.size()), NumberUtil.toBigDecimal(hasAuthPermCount))) {
            dtree.setChecked(DtreeUtil.CHECKED_2);
        }
        // 向下遍历
        for (Dtree subTree : subTreeList) {
            // 如果一开始就是0，说明一开始就没有选中，就排除掉
            if (StrUtil.equals(subTree.getChecked(), CHECKED_1)) {
                correctionChecked(dtreeList, subTree, dtreeList.stream().filter(subTree1 -> StrUtil.equals(subTree.getId(), subTree1.getParentId())).collect(Collectors.toList()));
            }
        }
    }


}
