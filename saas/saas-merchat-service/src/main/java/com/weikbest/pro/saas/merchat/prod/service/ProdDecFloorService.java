package com.weikbest.pro.saas.merchat.prod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.prod.entity.ProdDecFloor;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdDecFloorDTO;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdDecFloorQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.PayBackProdAdvBackAccountVO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdDecFloorVO;

/**
 * <p>
 * 商品装修落地页拆分表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
public interface ProdDecFloorService extends IService<ProdDecFloor> {

    /**
     * 新增数据
     *
     * @param id
     * @param prodDecFloorDTO prodDecFloorDTO
     * @return 新增结果
     */
    boolean insert(Long id, ProdDecFloorDTO prodDecFloorDTO);

    /**
     * 更新数据
     *
     * @param id              ID
     * @param prodDecFloorDTO prodDecFloorDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ProdDecFloorDTO prodDecFloorDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ProdDecFloor findById(Long id);

    /**
     * 分页查询
     *
     * @param prodDecFloorQO
     * @param pageReq
     * @return
     */
    IPage<ProdDecFloor> queryPage(ProdDecFloorQO prodDecFloorQO, PageReq pageReq);

    /**
     * 根据ID查询装修落地页信息
     *
     * @param id
     * @return
     */
    ProdDecFloorVO getVOById(Long id);

    /**
     * 新增或更新装修落地页信息
     *
     * @param id
     * @param prodDecFloorDTO
     * @return
     */
    boolean saveOrUpdateProdDecFloor(Long id, ProdDecFloorDTO prodDecFloorDTO);

    /**
     * 获取支付成功是否回传和回传比率
     *
     * @param prodId
     * @return
     */
    PayBackProdAdvBackAccountVO findPayBackAndBackRatio(Long prodId);
}
