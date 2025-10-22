package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.param.entity.ExcelTemplate;
import com.weikbest.pro.saas.sys.param.module.dto.ExcelTemplateDTO;
import com.weikbest.pro.saas.sys.param.module.qo.ExcelTemplateQO;

import java.util.List;

/**
 * <p>
 * 系统excel模板表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-23
 */
public interface ExcelTemplateService extends IService<ExcelTemplate> {

    /**
     * 新增数据
     *
     * @param excelTemplateDTO excelTemplateDTO
     * @return 新增结果
     */
    boolean insert(ExcelTemplateDTO excelTemplateDTO);

    /**
     * 更新数据
     *
     * @param id               ID
     * @param excelTemplateDTO excelTemplateDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ExcelTemplateDTO excelTemplateDTO);

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
    ExcelTemplate findById(Long id);

    /**
     * 分页查询
     *
     * @param excelTemplateQO
     * @param pageReq
     * @return
     */
    IPage<ExcelTemplate> queryPage(ExcelTemplateQO excelTemplateQO, PageReq pageReq);

    /**
     * 根据number查询
     *
     * @param number
     * @return
     */
    ExcelTemplate findByNumber(String number);
}
