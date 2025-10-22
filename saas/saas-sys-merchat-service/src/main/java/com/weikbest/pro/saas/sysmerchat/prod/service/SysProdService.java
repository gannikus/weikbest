package com.weikbest.pro.saas.sysmerchat.prod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sysmerchat.prod.module.qo.CloneProdDTO;
import com.weikbest.pro.saas.sysmerchat.prod.module.qo.SysProdQO;
import com.weikbest.pro.saas.sysmerchat.prod.module.vo.SysProdListVO;
import com.weikbest.pro.saas.sysmerchat.prod.module.vo.SysProdVO;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/1
 */
public interface SysProdService {

    /**
     * 分页查询
     *
     * @param sysProdQO
     * @param pageReq
     * @return
     */
    IPage<SysProdListVO> queryPage(SysProdQO sysProdQO, PageReq pageReq);

    /**
     * 根据商品ID查询商品详情
     *
     * @param id
     * @return
     */
    SysProdVO findVOById(Long id);

    /**
     * 更新商品排序号
     *
     * @param id
     * @param prodOrd
     * @return
     */
    boolean updateProdOrd(Long id, Integer prodOrd);


    /**
     * 复制商品
     * @param cloneProdDTO
     * @return
     */
    void cloneProd(CloneProdDTO cloneProdDTO);
}
