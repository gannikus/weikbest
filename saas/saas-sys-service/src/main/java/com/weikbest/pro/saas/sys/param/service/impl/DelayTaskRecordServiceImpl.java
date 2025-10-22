package com.weikbest.pro.saas.sys.param.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.redis.RedisService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.entity.DelayTaskRecord;
import com.weikbest.pro.saas.sys.param.mapper.DelayTaskRecordMapper;
import com.weikbest.pro.saas.sys.param.module.dto.DelayTaskRecordDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.DelayTaskRecordMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.DelayTaskRecordQO;
import com.weikbest.pro.saas.sys.param.service.DelayTaskRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 系统延时任务执行记录表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-23
 */
@Service
public class DelayTaskRecordServiceImpl extends ServiceImpl<DelayTaskRecordMapper, DelayTaskRecord> implements DelayTaskRecordService {

    @Override
    public boolean insert(DelayTaskRecordDTO delayTaskRecordDTO) {
        DelayTaskRecord delayTaskRecord = DelayTaskRecordMapStruct.INSTANCE.converToEntity(delayTaskRecordDTO);
        return this.save(delayTaskRecord);
    }

    @Override
    public boolean updateById(Long id, DelayTaskRecordDTO delayTaskRecordDTO) {
        DelayTaskRecord delayTaskRecord = this.findById(id);
        DelayTaskRecordMapStruct.INSTANCE.copyProperties(delayTaskRecordDTO, delayTaskRecord);
        delayTaskRecord.setId(id);
        return this.updateById(delayTaskRecord);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public DelayTaskRecord findById(Long id) {
        DelayTaskRecord delayTaskRecord = this.getById(id);
        if (ObjectUtil.isNull(delayTaskRecord)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return delayTaskRecord;
    }

    @Override
    public IPage<DelayTaskRecord> queryPage(DelayTaskRecordQO delayTaskRecordQO, PageReq pageReq) {
        QueryWrapper<DelayTaskRecord> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(delayTaskRecordQO.getDelayTask())) {
            wrapper.eq(DelayTaskRecord.DELAY_TASK, delayTaskRecordQO.getDelayTask());
        }
        if (StrUtil.isNotBlank(delayTaskRecordQO.getName())) {
            wrapper.eq(DelayTaskRecord.NAME, delayTaskRecordQO.getName());
        }
        if (StrUtil.isNotBlank(delayTaskRecordQO.getTaskStatus())) {
            wrapper.eq(DelayTaskRecord.TASK_STATUS, delayTaskRecordQO.getTaskStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public List<DelayTaskRecord> queryLikeDelayTask(String delayTask) {
        return this.list(new QueryWrapper<DelayTaskRecord>().eq(DelayTaskRecord.TASK_STATUS, DictConstant.DelayTaskStatus.waiting.getCode()).like(DelayTaskRecord.DELAY_TASK, delayTask));
    }
}
