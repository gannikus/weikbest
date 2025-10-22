package com.weikbest.pro.saas.sys.capital.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.sys.capital.entity.CapitalPool;
import com.weikbest.pro.saas.sys.capital.mapper.CapitalPoolMapper;
import com.weikbest.pro.saas.sys.capital.module.dto.CapitalPoolDTO;
import com.weikbest.pro.saas.sys.capital.module.mapstruct.CapitalPoolMapStruct;
import com.weikbest.pro.saas.sys.capital.module.qo.CapitalPoolQO;
import com.weikbest.pro.saas.sys.capital.service.CapitalPoolService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 平台资金池表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Service
public class CapitalPoolServiceImpl extends ServiceImpl<CapitalPoolMapper, CapitalPool> implements CapitalPoolService {

    @Override
    public boolean insert(CapitalPoolDTO capitalPoolDTO) {
        CapitalPool capitalPool = CapitalPoolMapStruct.INSTANCE.converToEntity(capitalPoolDTO);
        return this.save(capitalPool);
    }

    @Override
    public boolean updateById(Long id, CapitalPoolDTO capitalPoolDTO) {
        CapitalPool capitalPool = this.findById(id);
        CapitalPoolMapStruct.INSTANCE.copyProperties(capitalPoolDTO, capitalPool);
        capitalPool.setId(id);
        return this.updateById(capitalPool);
    }

    @Override
    public CapitalPool findById(Long id) {
        CapitalPool capitalPool = this.getById(id);
        if (ObjectUtil.isNull(capitalPool)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return capitalPool;
    }

    @Override
    public IPage<CapitalPool> queryPage(CapitalPoolQO capitalPoolQO, PageReq pageReq) {
        QueryWrapper<CapitalPool> wrapper = new QueryWrapper<>();
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
