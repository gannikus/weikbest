package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.param.entity.PrivacyPolicy;
import com.weikbest.pro.saas.sys.param.module.dto.PrivacyPolicyDTO;
import com.weikbest.pro.saas.sys.param.module.qo.PrivacyPolicyQO;

import java.util.List;

/**
 * <p>
 * 系统隐私声明表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-23
 */
public interface PrivacyPolicyService extends IService<PrivacyPolicy> {

    /**
     * 新增数据
     *
     * @param privacyPolicyDTO privacyPolicyDTO
     * @return 新增结果
     */
    boolean insert(PrivacyPolicyDTO privacyPolicyDTO);

    /**
     * 更新数据
     *
     * @param id               ID
     * @param privacyPolicyDTO privacyPolicyDTO
     * @return 更新结果
     */
    boolean updateById(Long id, PrivacyPolicyDTO privacyPolicyDTO);

    /**
     * 添加或更新数据
     *
     * @param privacyPolicyDTO
     * @return
     */
    boolean saveOrUpdate(PrivacyPolicyDTO privacyPolicyDTO);

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
    PrivacyPolicy findById(Long id);

    /**
     * 查询
     *
     * @return
     */
    PrivacyPolicy findPrivacyPolicy();

    /**
     * 分页查询
     *
     * @param privacyPolicyQO
     * @param pageReq
     * @return
     */
    IPage<PrivacyPolicy> queryPage(PrivacyPolicyQO privacyPolicyQO, PageReq pageReq);


}
