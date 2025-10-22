package com.weikbest.pro.saas.sys.param;

import com.weikbest.pro.saas.SaasBootApplication;
import com.weikbest.pro.saas.common.delay.DelayQueueManager;
import com.weikbest.pro.saas.merchat.order.delaytaskprocess.OrderWaitPayTimeoutDelayTaskProcess;
import com.weikbest.pro.saas.sys.param.service.DelayTaskConfigService;
import com.weikbest.pro.saas.sys.param.service.DelayTaskRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SaasBootApplication.class})
public class DelayTaskRecordTest {

    @Resource
    private DelayTaskConfigService delayTaskConfigService;

    @Resource
    private DelayTaskRecordService delayTaskRecordService;

    @Resource
    private DelayQueueManager delayQueueManager;

    @Resource
    OrderWaitPayTimeoutDelayTaskProcess orderWaitPayTimeoutDelayTaskProcess;

    @Test
    public void testInit() {
        long delayTime = 1668785049078L - System.currentTimeMillis();
        System.out.println(delayTime);

        orderWaitPayTimeoutDelayTaskProcess.putTask(123L);


//        List<DelayTaskVO> delayQueueTask = delayQueueManager.getDelayQueueTask();
//        delayQueueTask.forEach(delayTaskVO -> {
//            String taskId = delayTaskVO.getTaskId();
//            String redisKeyPrefix = DelayQueueFactory.getRedisKeyPrefix(taskId);
//            DelayTaskConfig delayTaskConfig = delayTaskConfigService.findByNumber(redisKeyPrefix);
//
//            DelayTaskRecord delayTaskRecord = delayTaskRecordService.getOne(new QueryWrapper<DelayTaskRecord>().eq(DelayTaskRecord.DELAY_TASK_ID, delayTaskConfig.getId()).eq(DelayTaskRecord.DELAY_TASK, taskId));
//            if(ObjectUtil.isNull(delayTaskRecord)) {
//                // 保存记录
//                delayTaskRecord = new DelayTaskRecord();
//                delayTaskRecord.setDelayTaskId(delayTaskConfig.getId())
//                        .setDelayTask(taskId)
//                        .setTaskStatus(DictConstant.DelayTaskStatus.waiting.getCode())
//                        .setName(delayTaskConfig.getName())
//                        .setTimeoutDate(delayTaskVO.getDate());
//                delayTaskRecordService.save(delayTaskRecord);
//            }
//        });

    }
}
