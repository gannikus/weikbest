package com.weikbest.pro.saas.merchat.cust.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.merchat.cust.entity.Customer;
import com.weikbest.pro.saas.merchat.cust.module.dto.CustomerDTO;
import com.weikbest.pro.saas.merchat.cust.module.qo.CustomerQO;
import com.weikbest.pro.saas.merchat.cust.module.vo.CustomerVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-16
 */
public interface CustomerService extends IService<Customer> {

    /**
     * 新增数据
     *
     * @param customerDTO customerDTO
     * @return 新增结果
     */
    boolean insert(CustomerDTO customerDTO);

    /**
     * 更新数据
     *
     * @param id          ID
     * @param customerDTO customerDTO
     * @param loginIp     loginIp
     * @return 更新结果
     */
    TokenUser updateById(Long id, CustomerDTO customerDTO, String loginIp);

    /**
     * 删除数据
     *
     * @param ids ID集合
     */
    boolean deleteBatchByIds(List<Long> ids);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    Customer findById(Long id);

    /**
     * 分页查询
     *
     * @param customerQO
     * @param pageReq
     * @return
     */
    IPage<Customer> queryPage(CustomerQO customerQO, PageReq pageReq);

    /**
     * 查询APP用户表数据
     *
     * @param openid
     * @param custThirdType
     * @return
     */
    Customer getCustomerByOpenidAndCustThirdType(String openid, String custThirdType);

    /**
     * APP用户登录，更新用户信息
     *
     * @param customer
     * @param loginIp
     * @return
     */
    TokenUser loginCustomerAndUpdate(Customer customer, String loginIp);

    /**
     * APP用户注册
     *
     * @param customer
     * @param custThirdType
     * @param loginIp
     * @return
     */
    TokenUser registerCustomer(Customer customer, String custThirdType, String loginIp);

    /**
     * 根据OPENID查询
     *
     * @param openid
     * @return
     */
    Customer findByOpenid(String openid);

    /**
     * 根据客户ID集合查询客户信息
     *
     * @param customerIdList
     * @return
     */
    Map<Long, CustomerVO> queryVOMapByIdList(List<Long> customerIdList);

    /**
     * 根据一个客户Id查询此用户所有小程序下Id集合
     * @param id
     * @return
     */
    List<Long> getIdsById(Long id);
}
