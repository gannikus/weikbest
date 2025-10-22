package com.weikbest.pro.saas.sysmerchat.common.mapper;

import com.weikbest.pro.saas.sysmerchat.common.module.dto.RealtimeDTO;
import com.weikbest.pro.saas.sysmerchat.common.module.qo.RealtimeQO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2023/2/18
 */
public interface SysIndexMapper {

    /**
     * 查询数据记录
     *
     * @param realtimeQO
     * @return
     */
    List<RealtimeDTO> queryRealtime(@Param("realtime") RealtimeQO realtimeQO);
}
