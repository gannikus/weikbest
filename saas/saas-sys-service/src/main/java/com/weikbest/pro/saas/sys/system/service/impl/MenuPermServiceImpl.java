package com.weikbest.pro.saas.sys.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.sys.system.entity.Menu;
import com.weikbest.pro.saas.sys.system.entity.MenuPerm;
import com.weikbest.pro.saas.sys.system.mapper.MenuPermMapper;
import com.weikbest.pro.saas.sys.system.module.dto.MenuPermDTO;
import com.weikbest.pro.saas.sys.system.module.mapstruct.MenuPermMapStruct;
import com.weikbest.pro.saas.sys.system.module.qo.MenuPermQO;
import com.weikbest.pro.saas.sys.system.service.MenuPermService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统菜单权限项关联表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Service
public class MenuPermServiceImpl extends ServiceImpl<MenuPermMapper, MenuPerm> implements MenuPermService {

    @Override
    public boolean insert(MenuPermDTO menuPermDTO) {
        MenuPerm menuPerm = MenuPermMapStruct.INSTANCE.converToEntity(menuPermDTO);
        return this.save(menuPerm);
    }

    @Override
    public boolean updateById(Long id, MenuPermDTO menuPermDTO) {
        MenuPerm menuPerm = this.findById(id);
        MenuPermMapStruct.INSTANCE.copyProperties(menuPermDTO, menuPerm);
        menuPerm.setId(id);
        return this.updateById(menuPerm);
    }

    @Override
    public MenuPerm findById(Long id) {
        MenuPerm menuPerm = this.getById(id);
        if (ObjectUtil.isNull(menuPerm)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return menuPerm;
    }

    @Override
    public IPage<MenuPerm> queryPage(MenuPermQO menuPermQO, PageReq pageReq) {
        QueryWrapper<MenuPerm> wrapper = new QueryWrapper<>();
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveAssociatePermList(Long menuId, List<Long> permIdList) {

        // 1. 根据菜单ID删除数据
        this.remove(new QueryWrapper<MenuPerm>().eq(MenuPerm.MENU_ID, menuId));

        // 2. 添加菜单关联权限数据
        if (CollectionUtil.isNotEmpty(permIdList)) {
            List<MenuPerm> menuPermList = permIdList.stream().map(permId -> new MenuPerm().setMenuId(menuId).setPermId(permId)).collect(Collectors.toList());
            return this.saveBatch(menuPermList);
        }

        return true;
    }

    @Override
    public List<MenuPerm> queryByMenuId(Long menuId) {
        return this.list(new QueryWrapper<MenuPerm>().eq(MenuPerm.MENU_ID, menuId));
    }
}
