package com.weikbest.pro.saas.merchat.shop.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.shop.entity.Shop;
import com.weikbest.pro.saas.merchat.shop.entity.ShopFinanceAccount;
import com.weikbest.pro.saas.merchat.shop.entity.ShopStatementDetail;
import com.weikbest.pro.saas.merchat.shop.mapper.ShopFinanceAccountMapper;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopFinanceAccountDTO;
import com.weikbest.pro.saas.merchat.shop.module.mapstruct.ShopFinanceAccountMapStruct;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopFinanceAccountQO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopAccountSettleAmountVO;
import com.weikbest.pro.saas.merchat.shop.service.ShopFinanceAccountService;
import com.weikbest.pro.saas.merchat.shop.service.ShopService;
import com.weikbest.pro.saas.merchat.shop.service.ShopStatementDetailService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 店铺资金账户表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ShopFinanceAccountServiceImpl extends ServiceImpl<ShopFinanceAccountMapper, ShopFinanceAccount> implements ShopFinanceAccountService {

    @Resource
    private ShopService shopService;

    @Resource
    private ShopStatementDetailService shopStatementDetailService;

    @Override
    public boolean insert(ShopFinanceAccountDTO shopFinanceAccountDTO) {
        ShopFinanceAccount shopFinanceAccount = ShopFinanceAccountMapStruct.INSTANCE.converToEntity(shopFinanceAccountDTO);
        return this.save(shopFinanceAccount);
    }

    @Override
    public boolean updateById(Long id, ShopFinanceAccountDTO shopFinanceAccountDTO) {
        ShopFinanceAccount shopFinanceAccount = this.findById(id);
        ShopFinanceAccountMapStruct.INSTANCE.copyProperties(shopFinanceAccountDTO, shopFinanceAccount);
        shopFinanceAccount.setId(id);
        return this.updateById(shopFinanceAccount);
    }

    @Override
    public ShopFinanceAccount findById(Long id) {
        ShopFinanceAccount shopFinanceAccount = this.getById(id);
        if (ObjectUtil.isNull(shopFinanceAccount)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return shopFinanceAccount;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertWithShop(Long shopId) {
        ShopFinanceAccount shopFinanceAccount = new ShopFinanceAccount();
        shopFinanceAccount.setShopId(shopId);
        return this.save(shopFinanceAccount);
    }

    @Override
    public IPage<ShopFinanceAccount> queryPage(ShopFinanceAccountQO shopFinanceAccountQO, PageReq pageReq) {
        QueryWrapper<ShopFinanceAccount> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(shopFinanceAccountQO.getDescription())) {
            wrapper.eq(ShopFinanceAccount.DESCRIPTION, shopFinanceAccountQO.getDescription());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public ShopAccountSettleAmountVO findShopAccountSettleAmount(Long businessId) {
        ShopAccountSettleAmountVO shopAccountSettleAmountVO = new ShopAccountSettleAmountVO();

        List<Shop> shopList = shopService.list(new QueryWrapper<Shop>().eq(Shop.BUSINESS_ID, businessId));
        if (CollectionUtil.isEmpty(shopList)) {
            return shopAccountSettleAmountVO;
        }
        List<Long> shopIdList = shopList.stream().map(Shop::getId).collect(Collectors.toList());

        // 查询待结算金额
        List<ShopFinanceAccount> shopFinanceAccountList = this.list(new QueryWrapper<ShopFinanceAccount>().in(ShopFinanceAccount.SHOP_ID, shopIdList));
        BigDecimal settleAmountTotal = shopFinanceAccountList.stream().map(ShopFinanceAccount::getSettleAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        shopAccountSettleAmountVO.setSettleAmountTotal(settleAmountTotal);

        // 查询待结算笔数
        long settleAmountCount = shopStatementDetailService.count(new QueryWrapper<ShopStatementDetail>().in(ShopStatementDetail.SHOP_ID, shopIdList)
                .eq(ShopStatementDetail.STATEMENT_TYPE, DictConstant.StatementType.statementType_1.getCode())
                .eq(ShopStatementDetail.SETTLE_TYPE, DictConstant.SettleType.settleType_0.getCode()));
        shopAccountSettleAmountVO.setSettleAmountCount(new BigDecimal(settleAmountCount).intValue());
        return shopAccountSettleAmountVO;
    }

    @Override
    public ShopFinanceAccount findByShopId(Long shopId) {
        ShopFinanceAccount shopFinanceAccount = this.getOne(new QueryWrapper<ShopFinanceAccount>().eq(ShopFinanceAccount.SHOP_ID, shopId));
        if (ObjectUtil.isNull(shopFinanceAccount)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return shopFinanceAccount;
    }
}
