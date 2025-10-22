package com.weikbest.pro.saas.merchat.busi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.busi.entity.BusiUser;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiUserBasicDTO;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiUserDTO;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiUserRegistDTO;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusinessDTO;
import com.weikbest.pro.saas.merchat.busi.module.qo.BusiUserQO;
import com.weikbest.pro.saas.merchat.busi.module.vo.BusiUserPageVO;

import java.util.List;

/**
 * <p>
 * 商户账户表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-12
 */
public interface BusiUserService extends IService<BusiUser> {

    /**
     * 新增数据
     *
     * @param busiUserDTO 商户账户信息
     * @param businessId 商户ID
     * @return 新增结果主键
     */
    Long insertSubReturnId(BusiUserDTO busiUserDTO, Long businessId);

    /**
     * 新增数据
     *
     * @param businessDTO
     * @param businessId
     * @return
     */
    Long insertMainReturnId(BusinessDTO businessDTO, Long businessId);

    /**
     * 更新数据
     *
     * @param id          ID
     * @param busiUserDTO busiUserDTO
     * @return 更新结果
     */
    boolean updateById(Long id, BusiUserDTO busiUserDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    BusiUser findById(Long id);

    /**
     * 分页查询
     *
     * @param busiUserQO
     * @param pageReq
     * @return
     */
    IPage<BusiUserPageVO> queryPage(BusiUserQO busiUserQO, PageReq pageReq);

    /**
     * 新商户注册
     *
     * @param busiUserRegistDTO
     * @return
     */
    boolean regist(BusiUserRegistDTO busiUserRegistDTO);

    /**
     * 更新商户登录密码
     *
     * @param id
     * @param oldPassword
     * @param password
     * @return
     */
    boolean updatePassword(Long id, String oldPassword, String password);

    /**
     * 查询商户的主账号
     *
     * @param businessId
     * @return
     */
    BusiUser findMainByBusinessId(Long businessId);

    /**
     * 删除商户用户数据
     *
     * @param businessIdList
     */
    void deleteBatchByBusinessIds(List<Long> businessIdList);

    /**
     * 删除商户用户数据
     *
     * @param ids
     */
    void deleteBatchByIds(List<Long> ids);

    /**
     * 禁用、启用商户账户
     *
     * @param id
     * @param dataStatus
     * @return
     */
    boolean updateDataStatusById(Long id, String dataStatus);

    /**
     * 更新账户基本信息
     *
     * @param id
     * @param busiUserBasicDTO
     * @return
     */
    boolean updateBasicById(Long id, BusiUserBasicDTO busiUserBasicDTO);

    /**
     * 更新账户登录账号
     *
     * @param id
     * @param phone
     * @return
     */
    boolean updateLoginNameById(Long id, String phone);

    /**
     * 根据手机号查询
     *
     * @param phone
     * @return
     */
    BusiUser getByPhone(String phone);

    /**
     * 根据商户ID和手机号查询
     *
     * @param businessId
     * @param phone
     * @return
     */
    BusiUser getByBusinessIdAndPhone(Long businessId, String phone);

    BusiUser findByApiKey(String apiKey);
}
