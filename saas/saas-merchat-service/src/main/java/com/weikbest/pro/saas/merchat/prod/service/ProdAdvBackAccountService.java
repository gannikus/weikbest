package com.weikbest.pro.saas.merchat.prod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.prod.entity.ProdAdvBackAccount;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdAdvBackAccountDTO;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdDecFloorDTO;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdAdvBackAccountQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdAdvBackAccountVO;

import java.util.List;

/**
 * <p>
 * 商品广告回传信息关联广告账户拆分多行表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdAdvBackAccountService extends IService<ProdAdvBackAccount> {

    /**
     * 新增数据
     *
     * @param prodAdvBackAccountDTO prodAdvBackAccountDTO
     * @return 新增结果
     */
    boolean insert(ProdAdvBackAccountDTO prodAdvBackAccountDTO);

    /**
     * 更新数据
     *
     * @param id                    ID
     * @param prodAdvBackAccountDTO prodAdvBackAccountDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ProdAdvBackAccountDTO prodAdvBackAccountDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ProdAdvBackAccount findById(Long id);

    /**
     * 分页查询
     *
     * @param prodAdvBackAccountQO
     * @param pageReq
     * @return
     */
    IPage<ProdAdvBackAccount> queryPage(ProdAdvBackAccountQO prodAdvBackAccountQO, PageReq pageReq);

    /**
     * 保存商品时，保存商品广告回传关联广告账户信息
     *
     * @param prodId
     * @param prodAdvBackAccountDTOList
     */
    void insertBatchWithProd(Long prodId, List<ProdAdvBackAccountDTO> prodAdvBackAccountDTOList);

    /**
     * 根据商品ID查询
     *
     * @param id
     * @return
     */
    List<ProdAdvBackAccountVO> queryVOById(Long id);

    /**
     * 根据商品ID更新关联账号回传信息
     *
     * @param id
     * @param prodDecFloorDTO
     */
    void updateProdAdvBackAccount(Long id, ProdDecFloorDTO prodDecFloorDTO);

    /**
     * 根据商品ID随机获取一个回传比率
     * @param prodId
     * @return
     */
    ProdAdvBackAccount queryRandomOneByProdId(Long prodId);

    /**
     * 根据商品Id删除商品回传信息
     * @param prodId
     * @return
     */
    int deleteByProdId(Long prodId);

    /**
     * 根据商品Id修改广告回传信息
     * @param account
     * @return
     */
    int updateByProdId(ProdAdvBackAccount account);

    /**
     * 根据商品Id查询广告回传信息
     * @param id
     * @return
     */
    ProdAdvBackAccount findByProdId(Long id);

    /**
     * 根据商品Id和广告账号返回对应的实体
     *
     * @param prodId
     * @param advAccountId
     * @return
     */
    ProdAdvBackAccount findByProdIdAndAccountId(Long prodId, Long advAccountId);
}
