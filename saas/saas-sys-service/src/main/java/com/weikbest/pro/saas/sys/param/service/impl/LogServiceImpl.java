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
import com.weikbest.pro.saas.sys.param.entity.Log;
import com.weikbest.pro.saas.sys.param.mapper.LogMapper;
import com.weikbest.pro.saas.sys.param.module.dto.LogDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.LogMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.LogQO;
import com.weikbest.pro.saas.sys.param.service.LogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    @Override
    public boolean insert(LogDTO logDTO) {
        Log log = LogMapStruct.INSTANCE.converToEntity(logDTO);
        return this.save(log);
    }

    @Override
    public boolean updateById(Long id, LogDTO logDTO) {
        Log log = this.findById(id);
        LogMapStruct.INSTANCE.copyProperties(logDTO, log);
        log.setId(id);
        return this.updateById(log);
    }

    @Override
    public Log findById(Long id) {
        Log log = this.getById(id);
        if (ObjectUtil.isNull(log)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return log;
    }

    @Override
    public IPage<Log> queryPage(LogQO logQO, PageReq pageReq) {
        QueryWrapper<Log> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(logQO.getTraceId())) {
            wrapper.eq(Log.TRACE_ID, logQO.getTraceId());
        }
        if (StrUtil.isNotBlank(logQO.getClassName())) {
            wrapper.eq(Log.CLASS_NAME, logQO.getClassName());
        }
        if (StrUtil.isNotBlank(logQO.getMethodName())) {
            wrapper.eq(Log.METHOD_NAME, logQO.getMethodName());
        }
        if (StrUtil.isNotBlank(logQO.getDetails())) {
            wrapper.eq(Log.DETAILS, logQO.getDetails());
        }
        if (StrUtil.isNotBlank(logQO.getOperateIp())) {
            wrapper.eq(Log.OPERATE_IP, logQO.getOperateIp());
        }
        if (StrUtil.isNotBlank(logQO.getLogType())) {
            wrapper.eq(Log.LOG_TYPE, logQO.getLogType());
        }
        if (StrUtil.isNotBlank(logQO.getRecordSource())) {
            wrapper.eq(Log.RECORD_SOURCE, logQO.getRecordSource());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
