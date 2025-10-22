package com.weikbest.pro.saas.sys.param.util;

import cn.hutool.core.collection.CollectionUtil;
import com.weikbest.pro.saas.sys.param.entity.DelayTaskRecord;

import java.util.Date;
import java.util.List;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/23
 */
public class DelayTaskRecordUtil {

    /**
     * 获取这些延时队列中的最大超时时间
     *
     * @param delayTaskRecordList
     * @return
     */
    public static Date getMaxTimeoutDate(List<DelayTaskRecord> delayTaskRecordList) {
        if (CollectionUtil.isEmpty(delayTaskRecordList)) {
            return null;
        }
        return delayTaskRecordList.stream().map(DelayTaskRecord::getTimeoutDate).max(Date::compareTo).get();
    }
}
