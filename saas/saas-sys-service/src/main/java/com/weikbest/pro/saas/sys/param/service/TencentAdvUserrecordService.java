package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.entity.TencentAdvUserrecord;
import com.weikbest.pro.saas.sys.param.module.dto.TencentAdvUserrecordDTO;
import com.weikbest.pro.saas.sys.param.module.dto.TencentAdvUserrecordSendDTO;
import com.weikbest.pro.saas.sys.param.module.qo.TencentAdvUserrecordQO;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 腾讯广告数据上报用户行为数据记录表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-28
 */
public interface TencentAdvUserrecordService extends IService<TencentAdvUserrecord> {

    /**
     * 新增数据
     *
     * @param tencentAdvUserrecordDTO tencentAdvUserrecordDTO
     * @return 新增结果
     */
    boolean insert(TencentAdvUserrecordDTO tencentAdvUserrecordDTO);

    /**
     * 更新数据
     *
     * @param id                      ID
     * @param tencentAdvUserrecordDTO tencentAdvUserrecordDTO
     * @return 更新结果
     */
    boolean updateById(Long id, TencentAdvUserrecordDTO tencentAdvUserrecordDTO);

    /**
     * 删除数据
     *
     * @param ids ID集合
     */
    boolean deleteBatchByIds(List<Long> ids);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    TencentAdvUserrecord findById(Long id);

    /**
     * 分页查询
     *
     * @param tencentAdvUserrecordQO
     * @param pageReq
     * @return
     */
    IPage<TencentAdvUserrecord> queryPage(TencentAdvUserrecordQO tencentAdvUserrecordQO, PageReq pageReq);

    /**
     * 回传腾讯广告,根据回传比率，可能最后也不会回传
     *
     * @param tencentAdvUserrecordSendDTO 订单回调回传
     * @param source {@link DictConstant.AdReturnSource}
     * @param payType {@link DictConstant.PayType}
     * @return 是否成功回传 0-否 1-是
     */
    String sendWithRatio(TencentAdvUserrecordSendDTO tencentAdvUserrecordSendDTO,String source,String payType);
}
