package com.weikbest.pro.saas.merchat.cust.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weikbest.pro.saas.merchat.cust.entity.Customer;

import java.util.List;

/**
 * <p>
 * 客户表 Mapper 接口
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-16
 */
public interface CustomerMapper extends BaseMapper<Customer> {

    List<Long> getIdsById(Long id);
}
