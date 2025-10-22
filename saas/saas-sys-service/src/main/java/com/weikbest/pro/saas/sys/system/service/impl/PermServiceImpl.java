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
import com.weikbest.pro.saas.sys.system.entity.MenuPerm;
import com.weikbest.pro.saas.sys.system.entity.Perm;
import com.weikbest.pro.saas.sys.system.entity.RoleMenu;
import com.weikbest.pro.saas.sys.system.mapper.PermMapper;
import com.weikbest.pro.saas.sys.system.module.dto.PermDTO;
import com.weikbest.pro.saas.sys.system.module.mapstruct.PermMapStruct;
import com.weikbest.pro.saas.sys.system.module.qo.PermQO;
import com.weikbest.pro.saas.sys.system.service.MenuPermService;
import com.weikbest.pro.saas.sys.system.service.PermService;
import com.weikbest.pro.saas.sys.system.service.RoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 系统权限项表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Service
public class PermServiceImpl extends ServiceImpl<PermMapper, Perm> implements PermService {

    @Resource
    private MenuPermService MenuPermService;

    @Resource
    private RoleMenuService roleMenuService;

    @Override
    public boolean insert(PermDTO permDTO) {
        Perm perm = PermMapStruct.INSTANCE.converToEntity(permDTO);
        return this.save(perm);
    }

    @Override
    public boolean updateById(Long id, PermDTO permDTO) {
        Perm perm = this.findById(id);
        PermMapStruct.INSTANCE.copyProperties(permDTO, perm);
        perm.setId(id);
        return this.updateById(perm);
    }

    @Override
    public Perm findById(Long id) {
        Perm perm = this.getById(id);
        if (ObjectUtil.isNull(perm)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return perm;
    }

    @Override
    public IPage<Perm> queryPage(PermQO permQO, PageReq pageReq) {
        QueryWrapper<Perm> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(permQO.getNumber())) {
            wrapper.eq(Perm.NUMBER, permQO.getNumber());
        }
        if (StrUtil.isNotBlank(permQO.getName())) {
            wrapper.eq(Perm.NAME, permQO.getName());
        }
        if (StrUtil.isNotBlank(permQO.getIsPreset())) {
            wrapper.eq(Perm.IS_PRESET, permQO.getIsPreset());
        }
        if (StrUtil.isNotBlank(permQO.getDescription())) {
            wrapper.eq(Perm.DESCRIPTION, permQO.getDescription());
        }
        if (StrUtil.isNotBlank(permQO.getDataStatus())) {
            wrapper.eq(Perm.DATA_STATUS, permQO.getDataStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBatchByIds(List<Long> idList) {

        // 删除权限项
        this.removeBatchByIds(idList);

        // 删除角色菜单关联
        roleMenuService.remove(new QueryWrapper<RoleMenu>().in(RoleMenu.PERM_ID, idList));

        // 删除菜单权限关联
        MenuPermService.remove(new QueryWrapper<MenuPerm>().in(MenuPerm.PERM_ID, idList));

    }
}
