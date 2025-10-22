package com.weikbest.pro.saas.sys.common.task;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.common.util.token.TokenUtil;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.common.delaytaskprocess.LogoutDelayTaskProcess;
import com.weikbest.pro.saas.sys.system.entity.UserLogin;
import com.weikbest.pro.saas.sys.system.entity.UserLoginRecord;
import com.weikbest.pro.saas.sys.system.entity.UserRelate;
import com.weikbest.pro.saas.sys.system.service.UserLoginRecordService;
import com.weikbest.pro.saas.sys.system.service.UserLoginService;
import com.weikbest.pro.saas.sys.system.service.UserRelateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/12/6
 */
@Slf4j
@Configuration
@EnableScheduling
public class LogoutTask {

    @Resource
    private UserLoginRecordService userLoginRecordService;

    @Resource
    private UserRelateService userRelateService;

    @Resource
    private UserLoginService userLoginService;

    @Resource
    private LogoutDelayTaskProcess logoutDelayTaskProcess;

    /**
     * 每日0点执行
     */
    @Scheduled(cron = "0 0 0 1/1 * ?")
    public void logout() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("********************************* 自动清除过期登录 start ********************************* ");
        try {
            // 获取前30天的数据
            DateTime dateTime = DateUtil.offsetDay(new Date(), -30);
            List<UserLoginRecord> userLoginRecordList = userLoginRecordService.list(new QueryWrapper<UserLoginRecord>().eq(UserLoginRecord.ONLINE, DictConstant.Online.code_1.getCode())
                    .le(UserLoginRecord.LOGIN_TIME, DateUtil.formatDateTime(dateTime)));
            if(CollectionUtil.isNotEmpty(userLoginRecordList)) {
                // 强制下线
                Set<Long> relateIdSet = userLoginRecordList.stream().map(UserLoginRecord::getRelateId).collect(Collectors.toSet());
                List<UserRelate> userRelateList = userRelateService.list(new QueryWrapper<UserRelate>().in(UserRelate.RELATE_ID, relateIdSet));
                Map<Long, String> userRelateMap = userRelateList.stream().collect(Collectors.toMap(UserRelate::getRelateId, UserRelate::getRelateType, (k1, k2) -> k1));

                List<Long> userLoginIdList = userLoginRecordList.stream().map(UserLoginRecord::getUserLoginId).collect(Collectors.toList());
                List<UserLogin> userLoginList = userLoginService.listByIds(userLoginIdList);
                Map<Long, UserLogin> userLoginMap = userLoginList.stream().collect(Collectors.toMap(UserLogin::getId, userLogin -> userLogin));

                for (UserLoginRecord userLoginRecord : userLoginRecordList) {
                    Long relateId = userLoginRecord.getRelateId();
                    Long userLoginId = userLoginRecord.getUserLoginId();
                    UserLogin userLogin = userLoginMap.get(userLoginId);
                    if(ObjectUtil.isNotNull(userLogin)) {
                        String loginName = userLogin.getLoginName();
                        String relateType = userRelateMap.get(relateId);
                        TokenUser tokenUser = new TokenUser().setId(relateId).setLoginNameOrPhone(loginName).setRelateType(relateType).setUserLoginRecordId(userLoginId);
                        // 删除延时任务
                        logoutDelayTaskProcess.removeTask(tokenUser);
                        String taskId = TokenUtil.redisUserTokenKey(tokenUser);
                        // 手动执行延时任务 强制下线
                        logoutDelayTaskProcess.doProcess(taskId);
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            stopWatch.stop();
            log.info("********************************* 自动清除过期登录 end ********************************* use time: {} ms.", stopWatch.getTotalTimeMillis());
        }
    }
}
