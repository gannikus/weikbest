package com.weikbest.pro.saas.merchat.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.merchat.shop.entity.ShopUser;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopUserQO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopUserListVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商户店铺用户表 Mapper 接口
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ShopUserMapper extends BaseMapper<ShopUser> {

    IPage<ShopUserListVO> queryPage(IPage<ShopUserListVO> page, @Param("shopUser") ShopUserQO shopUserQO);

}
