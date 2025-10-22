package com.weikbest.pro.saas.merchat.shop.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.shop.entity.ShopStatementDetail;
import com.weikbest.pro.saas.merchat.shop.mapper.ShopStatementDetailMapper;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopStatementDetailDTO;
import com.weikbest.pro.saas.merchat.shop.module.mapstruct.ShopStatementDetailMapStruct;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopStatementDetailQO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopStatementDetailPageVO;
import com.weikbest.pro.saas.merchat.shop.service.ShopStatementDetailService;
import com.weikbest.pro.saas.sys.common.cache.Memory;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 店铺对账单明细表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ShopStatementDetailServiceImpl extends ServiceImpl<ShopStatementDetailMapper, ShopStatementDetail> implements ShopStatementDetailService {

    @Override
    public boolean insert(ShopStatementDetailDTO shopStatementDetailDTO) {
        ShopStatementDetail shopStatementDetail = ShopStatementDetailMapStruct.INSTANCE.converToEntity(shopStatementDetailDTO);
        return this.save(shopStatementDetail);
    }

    @Override
    public boolean updateById(Long id, ShopStatementDetailDTO shopStatementDetailDTO) {
        ShopStatementDetail shopStatementDetail = this.findById(id);
        ShopStatementDetailMapStruct.INSTANCE.copyProperties(shopStatementDetailDTO, shopStatementDetail);
        shopStatementDetail.setId(id);
        return this.updateById(shopStatementDetail);
    }

    @Override
    public ShopStatementDetail findById(Long id) {
        ShopStatementDetail shopStatementDetail = this.getById(id);
        if (ObjectUtil.isNull(shopStatementDetail)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return shopStatementDetail;
    }

    @Override
    public IPage<ShopStatementDetailPageVO> queryPage(ShopStatementDetailQO shopStatementDetailQO, PageReq pageReq) {
        return this.baseMapper.queryPage(new Page<>(pageReq.getPage(), pageReq.getLimit()), shopStatementDetailQO);
    }

    @Override
    public Map<String, String> queryStatementTypeGroup() {
        Map<String, String> dict = Memory.getDict(DictConstant.StatementType.getDictTypeNumber());
        return dict;
    }
}
