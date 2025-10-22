package com.weikbest.pro.saas.merchat.shop.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.shop.entity.ShopFinanceDetail;
import com.weikbest.pro.saas.merchat.shop.mapper.ShopFinanceDetailMapper;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopFinanceDetailDTO;
import com.weikbest.pro.saas.merchat.shop.module.excel.ShopFinanceDetailExcel;
import com.weikbest.pro.saas.merchat.shop.module.mapstruct.ShopFinanceDetailMapStruct;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopFinanceDetailQO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopFinanceDetailPageVO;
import com.weikbest.pro.saas.merchat.shop.service.ShopFinanceDetailService;
import com.weikbest.pro.saas.sys.common.cache.Memory;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 店铺资金明细表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ShopFinanceDetailServiceImpl extends ServiceImpl<ShopFinanceDetailMapper, ShopFinanceDetail> implements ShopFinanceDetailService {

    @Override
    public boolean insert(ShopFinanceDetailDTO shopFinanceDetailDTO) {
        ShopFinanceDetail shopFinanceDetail = ShopFinanceDetailMapStruct.INSTANCE.converToEntity(shopFinanceDetailDTO);
        return this.save(shopFinanceDetail);
    }

    @Override
    public boolean updateById(Long id, ShopFinanceDetailDTO shopFinanceDetailDTO) {
        ShopFinanceDetail shopFinanceDetail = this.findById(id);
        ShopFinanceDetailMapStruct.INSTANCE.copyProperties(shopFinanceDetailDTO, shopFinanceDetail);
        shopFinanceDetail.setId(id);
        return this.updateById(shopFinanceDetail);
    }

    @Override
    public ShopFinanceDetail findById(Long id) {
        ShopFinanceDetail shopFinanceDetail = this.getById(id);
        if (ObjectUtil.isNull(shopFinanceDetail)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return shopFinanceDetail;
    }

    @Override
    public IPage<ShopFinanceDetailPageVO> queryPage(ShopFinanceDetailQO shopFinanceDetailQO, PageReq pageReq) {
        return this.baseMapper.queryPage(new Page<>(pageReq.getPage(), pageReq.getLimit()), shopFinanceDetailQO);
    }

    @Override
    public List<ShopFinanceDetailExcel> downloadDetail(ShopFinanceDetailQO shopFinanceDetailQO) {
        List<ShopFinanceDetailExcel> shopFinanceDetailExcels = this.baseMapper.downloadDetail(shopFinanceDetailQO);
        shopFinanceDetailExcels.forEach(shopFinanceDetailExcel -> {
            String financeType = Memory.getDict(DictConstant.FinanceType.getDictTypeNumber(), shopFinanceDetailExcel.getFinanceType());
            shopFinanceDetailExcel.setFinanceType(financeType);

            if (StrUtil.equals(shopFinanceDetailExcel.getCapitalFlowType(), DictConstant.CapitalFlowType.capitalFlowType_1.getCode())) {
                shopFinanceDetailExcel.setAmountDetail("+" + shopFinanceDetailExcel.getAmountDetail());
            } else {
                shopFinanceDetailExcel.setAmountDetail("-" + shopFinanceDetailExcel.getAmountDetail());
            }

            String capitalFlowType = Memory.getDict(DictConstant.CapitalFlowType.getDictTypeNumber(), shopFinanceDetailExcel.getCapitalFlowType());
            shopFinanceDetailExcel.setCapitalFlowType(capitalFlowType);

        });
        return shopFinanceDetailExcels;
    }
}
