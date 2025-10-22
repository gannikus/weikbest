package com.weikbest.pro.saas.sys.param.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.sys.param.entity.DelayTaskConfig;
import com.weikbest.pro.saas.sys.param.mapper.DelayTaskConfigMapper;
import com.weikbest.pro.saas.sys.param.module.dto.DelayTaskConfigDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.DelayTaskConfigMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.DelayTaskConfigQO;
import com.weikbest.pro.saas.sys.param.service.DelayTaskConfigService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统延时任务表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-23
 */
@Service
public class DelayTaskConfigServiceImpl extends ServiceImpl<DelayTaskConfigMapper, DelayTaskConfig> implements DelayTaskConfigService {

    @Override
    public boolean insert(DelayTaskConfigDTO delayTaskConfigDTO) {
        DelayTaskConfig delayTaskConfig = DelayTaskConfigMapStruct.INSTANCE.converToEntity(delayTaskConfigDTO);
        return this.save(delayTaskConfig);
    }

    @Override
    public boolean updateById(Long id, DelayTaskConfigDTO delayTaskConfigDTO) {
        DelayTaskConfig delayTaskConfig = this.findById(id);
        DelayTaskConfigMapStruct.INSTANCE.copyProperties(delayTaskConfigDTO, delayTaskConfig);
        delayTaskConfig.setId(id);
        return this.updateById(delayTaskConfig);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public DelayTaskConfig findById(Long id) {
        DelayTaskConfig delayTaskConfig = this.getById(id);
        if (ObjectUtil.isNull(delayTaskConfig)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return delayTaskConfig;
    }

    @Override
    public DelayTaskConfig findByNumber(String number) {
        DelayTaskConfig delayTaskConfig = this.getOne(new QueryWrapper<DelayTaskConfig>().eq(DelayTaskConfig.NUMBER, number));
        if (ObjectUtil.isNull(delayTaskConfig)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return delayTaskConfig;
    }

    @Override
    public IPage<DelayTaskConfig> queryPage(DelayTaskConfigQO delayTaskConfigQO, PageReq pageReq) {
        QueryWrapper<DelayTaskConfig> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(delayTaskConfigQO.getNumber())) {
            wrapper.eq(DelayTaskConfig.NUMBER, delayTaskConfigQO.getNumber());
        }
        if (StrUtil.isNotBlank(delayTaskConfigQO.getName())) {
            wrapper.eq(DelayTaskConfig.NAME, delayTaskConfigQO.getName());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
