package com.weikbest.pro.saas.sys.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.sys.system.entity.MenuModule;
import com.weikbest.pro.saas.sys.system.module.dto.MenuModuleDTO;
import com.weikbest.pro.saas.sys.system.module.qo.MenuModuleQO;
import com.weikbest.pro.saas.sys.system.module.vo.MenuModuleVO;

import java.util.List;

/**
 * <p>
 * 系统模块表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
public interface MenuModuleService extends IService<MenuModule> {

    /**
     * 新增数据
     *
     * @param menuModuleDTO menuModuleDTO
     * @return 新增结果
     */
    boolean insert(MenuModuleDTO menuModuleDTO);

    /**
     * 更新数据
     *
     * @param id            ID
     * @param menuModuleDTO menuModuleDTO
     * @return 更新结果
     */
    boolean updateById(Long id, MenuModuleDTO menuModuleDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    MenuModule findById(Long id);

    /**
     * 分页查询
     *
     * @param menuModuleQO
     * @param pageReq
     * @return
     */
    IPage<MenuModule> queryPage(MenuModuleQO menuModuleQO, PageReq pageReq);

    /**
     * 根据类型查询，返回字典对象
     *
     * @param type
     * @return
     */
    List<DictEntry> queryDictByType(String type);

    /**
     * 根据ID列表删除模块
     *
     * @param idList
     */
    void deleteBatchByIds(List<Long> idList);

    /**
     * 根据模块类型 查询当前登录用户有权限的模块
     *
     * @param tokenUser
     * @param moduleType
     * @return
     */
    List<MenuModuleVO> queryAuthModuleByTokenUser(TokenUser tokenUser, String moduleType);
}
