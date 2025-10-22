package com.weikbest.pro.saas.sys.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.sys.system.entity.Org;
import com.weikbest.pro.saas.sys.system.mapper.OrgMapper;
import com.weikbest.pro.saas.sys.system.module.dto.OrgDTO;
import com.weikbest.pro.saas.sys.system.module.mapstruct.OrgMapStruct;
import com.weikbest.pro.saas.sys.system.module.qo.OrgQO;
import com.weikbest.pro.saas.sys.system.service.OrgService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统部门表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-22
 */
@Service
public class OrgServiceImpl extends ServiceImpl<OrgMapper, Org> implements OrgService {

    @Override
    public boolean insert(OrgDTO orgDTO) {
        Org org = OrgMapStruct.INSTANCE.converToEntity(orgDTO);
        return this.save(org);
    }

    @Override
    public boolean updateById(Long id, OrgDTO orgDTO) {
        Org org = this.findById(id);
        OrgMapStruct.INSTANCE.copyProperties(orgDTO, org);
        org.setId(id);
        return this.updateById(org);
    }

    @Override
    public Org findById(Long id) {
        Org org = this.getById(id);
        if (ObjectUtil.isNull(org)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return org;
    }

    @Override
    public IPage<Org> queryPage(OrgQO orgQO, PageReq pageReq) {
        QueryWrapper<Org> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(orgQO.getName())) {
            wrapper.eq(Org.NAME, orgQO.getName());
        }
        if (StrUtil.isNotBlank(orgQO.getDescription())) {
            wrapper.eq(Org.DESCRIPTION, orgQO.getDescription());
        }
        if (StrUtil.isNotBlank(orgQO.getDataStatus())) {
            wrapper.eq(Org.DATA_STATUS, orgQO.getDataStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
