package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.dtree.Dtree;
import com.weikbest.pro.saas.sys.param.entity.Region;
import com.weikbest.pro.saas.sys.param.module.dto.RegionDTO;
import com.weikbest.pro.saas.sys.param.module.qo.RegionQO;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 平台行政区划表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-03
 */
public interface RegionService extends IService<Region> {

    /**
     * 同步行政区划
     *
     * @return
     */
    boolean sync();

    /**
     * 新增数据
     *
     * @param regionDTO regionDTO
     * @return 新增结果
     */
    boolean insert(RegionDTO regionDTO);

    /**
     * 更新数据
     *
     * @param id        ID
     * @param regionDTO regionDTO
     * @return 更新结果
     */
    boolean updateById(Long id, RegionDTO regionDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    Region findById(Long id);

    /**
     * 分页查询
     *
     * @param regionQO
     * @param pageReq
     * @return
     */
    IPage<Region> queryPage(RegionQO regionQO, PageReq pageReq);

    /**
     * 查询行政区划
     *
     * @return
     */
    List<Dtree> queryTree();

    /**
     * 查询行政区划
     *
     * @param adcode 选中的行政区划
     * @return
     */
    List<Dtree> queryTree(Collection<Integer> adcodeCollection);
}
