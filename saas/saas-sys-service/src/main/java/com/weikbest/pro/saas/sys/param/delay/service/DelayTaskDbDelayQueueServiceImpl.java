package com.weikbest.pro.saas.sys.param.delay.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.delay.DelayQueueFactory;
import com.weikbest.pro.saas.common.delay.entity.DelayTask;
import com.weikbest.pro.saas.common.delay.entity.DelayTaskStatus;
import com.weikbest.pro.saas.common.delay.service.DelayQueueService;
import com.weikbest.pro.saas.sys.param.entity.DelayTaskConfig;
import com.weikbest.pro.saas.sys.param.entity.DelayTaskRecord;
import com.weikbest.pro.saas.sys.param.service.DelayTaskConfigService;
import com.weikbest.pro.saas.sys.param.service.DelayTaskRecordService;
import javafx.print.Collation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import javax.annotation.Resource;

/**
 * 延时队列管理器
 *
 * @author wisdomelon
 * @date 2021/6/1
 * @project saas
 * @jdk 1.8
 * 延时队列服务（入库）
 */
@Slf4j
@Component
public class DelayTaskDbDelayQueueServiceImpl implements DelayQueueService {

    @Resource
    private DelayTaskConfigService delayTaskConfigService;

    @Resource
    private DelayTaskRecordService delayTaskRecordService;

    @Override
    public void putTask(DelayTask delayTask) {
        String redisKeyPrefix = DelayQueueFactory.getRedisKeyPrefix(delayTask.getTaskId());
        DelayTaskConfig delayTaskConfig = delayTaskConfigService.findByNumber(redisKeyPrefix);

        DelayTaskRecord delayTaskRecord = delayTaskRecordService.getOne(new QueryWrapper<DelayTaskRecord>().eq(DelayTaskRecord.DELAY_TASK_ID, delayTaskConfig.getId()).eq(DelayTaskRecord.DELAY_TASK, delayTask.getTaskId()).ne(DelayTaskRecord.TASK_STATUS,DelayTaskStatus.DELETE));
        if (ObjectUtil.isNull(delayTaskRecord) || StrUtil.equals(delayTaskRecord.getTaskStatus(), DelayTaskStatus.DELETE)) {
            // 保存记录
            delayTaskRecord = new DelayTaskRecord();
            delayTaskRecord.setDelayTaskId(delayTaskConfig.getId())
                    .setDelayTask(delayTask.getTaskId())
                    .setTaskStatus(DelayTaskStatus.WAITING)
                    .setName(delayTaskConfig.getName())
                    .setTimeoutDate(DateUtil.date(delayTask.getExpire()));
            delayTaskRecordService.save(delayTaskRecord);
        } else {
            // 超时时间不一致则更新记录
            DateTime date = DateUtil.date(delayTask.getExpire());
            if (DateUtil.between(delayTaskRecord.getTimeoutDate(), date, DateUnit.SECOND) > WeikbestConstant.ZERO_INT) {
                delayTaskRecord.setTimeoutDate(date).setTaskStatus(DelayTaskStatus.WAITING);
                delayTaskRecordService.updateById(delayTaskRecord);
            }

        }
    }

    @Override
    public void putTaskInit(DelayTask delayTask) {

    }

    @Override
    public boolean removeTask(DelayTask delayTask) {
        String redisKeyPrefix = DelayQueueFactory.getRedisKeyPrefix(delayTask.getTaskId());
        DelayTaskConfig delayTaskConfig = delayTaskConfigService.findByNumber(redisKeyPrefix);

        List<DelayTaskRecord> delayTaskRecord = delayTaskRecordService.list(new QueryWrapper<DelayTaskRecord>().eq(DelayTaskRecord.DELAY_TASK_ID, delayTaskConfig.getId()).eq(DelayTaskRecord.DELAY_TASK, delayTask.getTaskId()));
        if (CollectionUtil.isNotEmpty(delayTaskRecord)) {
            // 更新记录状态: -1:已删除
            delayTaskRecord.forEach(d->d.setTaskStatus(DelayTaskStatus.DELETE));
            return delayTaskRecordService.updateBatchById(delayTaskRecord);
        }
        return false;
    }

    @Override
    public boolean contains(DelayTask delayTask) {
        String redisKeyPrefix = DelayQueueFactory.getRedisKeyPrefix(delayTask.getTaskId());
        DelayTaskConfig delayTaskConfig = delayTaskConfigService.findByNumber(redisKeyPrefix);

        DelayTaskRecord delayTaskRecord = delayTaskRecordService.getOne(new QueryWrapper<DelayTaskRecord>().eq(DelayTaskRecord.DELAY_TASK_ID, delayTaskConfig.getId()).eq(DelayTaskRecord.DELAY_TASK, delayTask.getTaskId()));
        return ObjectUtil.isNotNull(delayTaskRecord);
    }

    @Override
    public void updateTaskStatus(String taskId, String delayTaskStatus) {
        String redisKeyPrefix = DelayQueueFactory.getRedisKeyPrefix(taskId);
        DelayTaskConfig delayTaskConfig = delayTaskConfigService.findByNumber(redisKeyPrefix);

        DelayTaskRecord delayTaskRecord = delayTaskRecordService.getOne(new QueryWrapper<DelayTaskRecord>().eq(DelayTaskRecord.DELAY_TASK_ID, delayTaskConfig.getId()).eq(DelayTaskRecord.DELAY_TASK, taskId).ne(DelayTaskRecord.TASK_STATUS,DelayTaskStatus.DELETE));
        if (ObjectUtil.isNotNull(delayTaskRecord)) {
            // 更新记录状态
            delayTaskRecord.setTaskStatus(delayTaskStatus);
            delayTaskRecordService.updateById(delayTaskRecord);
        }
    }
}

