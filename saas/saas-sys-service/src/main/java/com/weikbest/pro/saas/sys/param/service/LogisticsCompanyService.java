package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.sys.param.entity.LogisticsCompany;
import com.weikbest.pro.saas.sys.param.module.dto.LogisticsCompanyDTO;
import com.weikbest.pro.saas.sys.param.module.qo.LogisticsCompanyQO;

import java.util.List;

/**
 * <p>
 * 物流快递公司表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-20
 */
public interface LogisticsCompanyService extends IService<LogisticsCompany> {

    /**
     * 新增数据
     *
     * @param logisticsCompanyDTO logisticsCompanyDTO
     * @return 新增结果
     */
    boolean insert(LogisticsCompanyDTO logisticsCompanyDTO);

    /**
     * 更新数据
     *
     * @param id                  ID
     * @param logisticsCompanyDTO logisticsCompanyDTO
     * @return 更新结果
     */
    boolean updateById(Long id, LogisticsCompanyDTO logisticsCompanyDTO);

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
    LogisticsCompany findById(Long id);

    /**
     * 分页查询
     *
     * @param logisticsCompanyQO
     * @param pageReq
     * @return
     */
    IPage<LogisticsCompany> queryPage(LogisticsCompanyQO logisticsCompanyQO, PageReq pageReq);

    /**
     * 去阿里云平台同步快递物流公司
     *
     * @return
     */
    boolean sync();

    /**
     * 查询快递公司数据字典
     *
     * @return
     */
    List<DictEntry> queryDict();

    /**
     * 获取所有物流公司名称
     * @return
     */
    List<LogisticsCompany> getLogisticsCompanys();

}
