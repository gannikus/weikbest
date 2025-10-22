package com.weikbest.pro.saas.merchat.prod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.prod.entity.ProdLeftSlide;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdLeftSlideDTO;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdLeftSlideQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdLeftSlideVO;

/**
 * <p>
 * 商品左滑设置拆分表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
public interface ProdLeftSlideService extends IService<ProdLeftSlide> {

    /**
     * 新增数据
     *
     * @param id               ID
     * @param prodLeftSlideDTO prodLeftSlideDTO
     * @return 新增结果
     */
    boolean insert(Long id, ProdLeftSlideDTO prodLeftSlideDTO);

    /**
     * 更新数据
     *
     * @param id               ID
     * @param prodLeftSlideDTO prodLeftSlideDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ProdLeftSlideDTO prodLeftSlideDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ProdLeftSlide findById(Long id);

    /**
     * 分页查询
     *
     * @param prodLeftSlideQO
     * @param pageReq
     * @return
     */
    IPage<ProdLeftSlide> queryPage(ProdLeftSlideQO prodLeftSlideQO, PageReq pageReq);

    /**
     * 根据商品ID查询商品左滑设置
     *
     * @param id
     * @return
     */
    ProdLeftSlideVO getVOById(Long id);

    /**
     * 新增或更新左滑信息
     *
     * @param id
     * @param prodLeftSlideDTO
     * @return
     */
    boolean saveOrUpdateProdLeftSlide(Long id, ProdLeftSlideDTO prodLeftSlideDTO);
}
