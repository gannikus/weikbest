package com.weikbest.pro.saas.merchat.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.merchat.shop.entity.ShopRole;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopRoleQO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopRoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 店铺角色表 Mapper 接口
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-06
 */
public interface ShopRoleMapper extends BaseMapper<ShopRole> {

    /**
     * 分页查询数量
     *
     * @param page
     * @param shopRoleQO
     * @return
     */
    IPage<ShopRoleVO> queryPage(IPage<ShopRoleVO> page, @Param("shopRole") ShopRoleQO shopRoleQO);

    /**
     * 查询所有角色
     *
     * @param businessId
     * @param shopId
     * @return
     */
    List<ShopRoleVO> queryAllRole(@Param("businessId") Long businessId, @Param("shopId") Long shopId);
}
