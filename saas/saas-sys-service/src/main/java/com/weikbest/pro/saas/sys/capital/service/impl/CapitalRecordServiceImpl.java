package com.weikbest.pro.saas.sys.capital.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.sys.capital.entity.CapitalRecord;
import com.weikbest.pro.saas.sys.capital.mapper.CapitalRecordMapper;
import com.weikbest.pro.saas.sys.capital.module.dto.CapitalRecordDTO;
import com.weikbest.pro.saas.sys.capital.module.mapstruct.CapitalRecordMapStruct;
import com.weikbest.pro.saas.sys.capital.module.qo.CapitalRecordQO;
import com.weikbest.pro.saas.sys.capital.service.CapitalRecordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 平台资金出入账记录表  服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Service
public class CapitalRecordServiceImpl extends ServiceImpl<CapitalRecordMapper, CapitalRecord> implements CapitalRecordService {

    @Override
    public boolean insert(CapitalRecordDTO capitalRecordDTO) {
        CapitalRecord capitalRecord = CapitalRecordMapStruct.INSTANCE.converToEntity(capitalRecordDTO);
        return this.save(capitalRecord);
    }

    @Override
    public boolean updateById(Long id, CapitalRecordDTO capitalRecordDTO) {
        CapitalRecord capitalRecord = this.findById(id);
        CapitalRecordMapStruct.INSTANCE.copyProperties(capitalRecordDTO, capitalRecord);
        capitalRecord.setId(id);
        return this.updateById(capitalRecord);
    }

    @Override
    public CapitalRecord findById(Long id) {
        CapitalRecord capitalRecord = this.getById(id);
        if (ObjectUtil.isNull(capitalRecord)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return capitalRecord;
    }

    @Override
    public IPage<CapitalRecord> queryPage(CapitalRecordQO capitalRecordQO, PageReq pageReq) {
        QueryWrapper<CapitalRecord> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(capitalRecordQO.getBillType())) {
            wrapper.eq(CapitalRecord.BILL_TYPE, capitalRecordQO.getBillType());
        }
        if (StrUtil.isNotBlank(capitalRecordQO.getBillAmount())) {
            wrapper.eq(CapitalRecord.BILL_AMOUNT, capitalRecordQO.getBillAmount());
        }
        if (StrUtil.isNotBlank(capitalRecordQO.getDescription())) {
            wrapper.eq(CapitalRecord.DESCRIPTION, capitalRecordQO.getDescription());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
