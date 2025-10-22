package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.param.entity.CodeRule;
import com.weikbest.pro.saas.sys.param.module.dto.CodeRuleDTO;
import com.weikbest.pro.saas.sys.param.module.qo.CodeRuleQO;

/**
 * <p>
 * 系统编码规则表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
public interface CodeRuleService extends IService<CodeRule> {

    /**
     * 新增数据
     *
     * @param codeRuleDTO codeRuleDTO
     * @return 新增结果
     */
    boolean insert(CodeRuleDTO codeRuleDTO);

    /**
     * 更新数据
     *
     * @param id          ID
     * @param codeRuleDTO codeRuleDTO
     * @return 更新结果
     */
    boolean updateById(Long id, CodeRuleDTO codeRuleDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    CodeRule findById(Long id);

    /**
     * 分页查询
     *
     * @param codeRuleQO
     * @param pageReq
     * @return
     */
    IPage<CodeRule> queryPage(CodeRuleQO codeRuleQO, PageReq pageReq);

    /**
     * 获取编码规则
     *
     * @param number 编码规则编码
     * @return 编码规则号
     */
    String nextNum(String number);
}
