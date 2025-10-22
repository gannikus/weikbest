package com.weikbest.pro.saas.merchat.busi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.merchat.busi.entity.Business;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiUserRegistDTO;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusinessDTO;
import com.weikbest.pro.saas.merchat.busi.module.qo.BusinessQO;
import com.weikbest.pro.saas.merchat.busi.module.vo.BusiUserLoginInfoVO;

import java.util.List;

/**
 * <p>
 * 商户表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface BusinessService extends IService<Business> {

    /**
     * 新增数据
     *
     * @param businessDTO businessDTO
     * @return 新增结果
     */
    boolean insert(BusinessDTO businessDTO);

    /**
     * 新商户注册
     *
     * @param busiUserRegistDTO
     * @return
     */
    boolean regist(BusiUserRegistDTO busiUserRegistDTO);

    /**
     * 更新数据
     *
     * @param id          ID
     * @param businessDTO businessDTO
     * @return 更新结果
     */
    boolean updateById(Long id, BusinessDTO businessDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    Business findById(Long id);

    /**
     * 分页查询
     *
     * @param businessQO
     * @param pageReq
     * @return
     */
    IPage<Business> queryPage(BusinessQO businessQO, PageReq pageReq);

    /**
     * 查询当前登录用户对应的商户信息
     *
     * @param tokenUser
     * @return
     */
    List<Long> findIdByTokenUser(TokenUser tokenUser);

    /**
     * 查询当前登录用户对应的商户信息
     *
     * @param tokenUser
     * @return
     */
    BusiUserLoginInfoVO findByTokenUser(TokenUser tokenUser);
}
