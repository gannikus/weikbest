package com.weikbest.pro.saas.merchat.shop.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.wxpay.bean.profitsharingV3.ProfitSharingReceiver;
import com.github.binarywang.wxpay.service.WxPayService;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.redis.RedisLock;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.shop.entity.ShopThird;
import com.weikbest.pro.saas.merchat.shop.entity.ShopThirdReceive;
import com.weikbest.pro.saas.merchat.shop.mapper.ShopThirdReceiveMapper;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopThirdReceiveDTO;
import com.weikbest.pro.saas.merchat.shop.module.mapstruct.ShopThirdReceiveMapStruct;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopThirdReceiveQO;
import com.weikbest.pro.saas.merchat.shop.service.ShopThirdReceiveService;
import com.weikbest.pro.saas.merchat.shop.service.ShopThirdService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 店铺第三方平台分账接收方拆分多行表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-18
 */
@Slf4j
@Service
public class ShopThirdReceiveServiceImpl extends ServiceImpl<ShopThirdReceiveMapper, ShopThirdReceive> implements ShopThirdReceiveService {

    @Resource
    private ShopThirdService shopThirdService;

    @Resource
    private ThirdConfigService thirdConfigService;

    @Resource
    private RedisLock redisLock;

    @Override
    public boolean insert(ShopThirdReceiveDTO shopThirdReceiveDTO) {
        ShopThirdReceive shopThirdReceive = ShopThirdReceiveMapStruct.INSTANCE.converToEntity(shopThirdReceiveDTO);
        return this.save(shopThirdReceive);
    }

    @Override
    public boolean updateById(Long id, ShopThirdReceiveDTO shopThirdReceiveDTO) {
        ShopThirdReceive shopThirdReceive = this.findById(id);
        ShopThirdReceiveMapStruct.INSTANCE.copyProperties(shopThirdReceiveDTO, shopThirdReceive);
        shopThirdReceive.setId(id);
        return this.updateById(shopThirdReceive);
    }

    @Override
    public ShopThirdReceive findById(Long id) {
        ShopThirdReceive shopThirdReceive = this.getById(id);
        if (ObjectUtil.isNull(shopThirdReceive)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return shopThirdReceive;
    }

    @Override
    public IPage<ShopThirdReceive> queryPage(ShopThirdReceiveQO shopThirdReceiveQO, PageReq pageReq) {
        QueryWrapper<ShopThirdReceive> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(shopThirdReceiveQO.getWxPayAppId())) {
            wrapper.eq(ShopThirdReceive.WX_PAY_APP_ID, shopThirdReceiveQO.getWxPayAppId());
        }
        if (StrUtil.isNotBlank(shopThirdReceiveQO.getWxPayReceiveMchId())) {
            wrapper.eq(ShopThirdReceive.WX_PAY_RECEIVE_MCH_ID, shopThirdReceiveQO.getWxPayReceiveMchId());
        }
        if (StrUtil.isNotBlank(shopThirdReceiveQO.getWxPayReceiveType())) {
            wrapper.eq(ShopThirdReceive.WX_PAY_RECEIVE_TYPE, shopThirdReceiveQO.getWxPayReceiveType());
        }
        if (StrUtil.isNotBlank(shopThirdReceiveQO.getWxPayReceiveName())) {
            wrapper.eq(ShopThirdReceive.WX_PAY_RECEIVE_NAME, shopThirdReceiveQO.getWxPayReceiveName());
        }
        if (StrUtil.isNotBlank(shopThirdReceiveQO.getWxPayReceiveRelationType())) {
            wrapper.eq(ShopThirdReceive.WX_PAY_RECEIVE_RELATION_TYPE, shopThirdReceiveQO.getWxPayReceiveRelationType());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertWithShop(Long shopId, ShopThirdReceiveDTO shopThirdReceiveDTO) {
        ShopThird shopThird = shopThirdService.findById(shopId);
        if (StrUtil.equals(shopThird.getWxPayMchId(), shopThirdReceiveDTO.getWxPayReceiveMchId())) {
            throw new WeikbestException("分账接收方的微信商户号不能与店铺的微信商户号相同！");
        }

        // 根据店铺的第三方微信信息切换当前wxPayService
        WxPayService wxPayService = shopThirdService.findWxPayServiceById(shopId);

        if (StrUtil.isNotBlank(shopThirdReceiveDTO.getWxPayAppId())) {
            // 店铺第三方平台分账信息表
            ShopThirdReceive shopThirdReceive = ShopThirdReceiveMapStruct.INSTANCE.converToEntity(shopThirdReceiveDTO);
            shopThirdReceive.setId(shopId);
            shopThirdReceive.setJoinTime(DateUtil.date());

            insertWithShop(shopThirdReceive, shopThird, wxPayService);
        }

        if (CollectionUtil.isNotEmpty(shopThirdReceiveDTO.getWxPayAppIds())) {
            for (String wxPayAppId : shopThirdReceiveDTO.getWxPayAppIds()) {
                // 店铺第三方平台分账信息表
                ShopThirdReceive shopThirdReceive = ShopThirdReceiveMapStruct.INSTANCE.converToEntity(shopThirdReceiveDTO);
                shopThirdReceive.setId(shopId);
                shopThirdReceive.setWxPayAppId(wxPayAppId);
                shopThirdReceive.setJoinTime(DateUtil.date());

                insertWithShop(shopThirdReceive, shopThird, wxPayService);
            }
        }
        return true;
    }

    /**
     * 保存第三方店铺信息
     *
     * @param shopThirdReceive
     * @param shopThird
     * @param wxPayService
     */
    private void insertWithShop(ShopThirdReceive shopThirdReceive, ShopThird shopThird, WxPayService wxPayService) {
        try {
            // 当前店铺的商户号添加为分账接收方
            // 添加平台的商户号为分账接收方
            ProfitSharingReceiver request = ProfitSharingReceiver.newBuilder()
                    .appid(shopThirdReceive.getWxPayAppId())
                    .type(WeikbestConstant.MERCHANT_ID)
                    .account(shopThirdReceive.getWxPayReceiveMchId())
                    .name(shopThirdReceive.getWxPayReceiveName())
                    .relationType(WeikbestConstant.PARTNER).build();
            ProfitSharingReceiver response = wxPayService.getProfitSharingV3Service().addProfitSharingReceiver(request);
            log.info("insertWithShop-----wxPayAppId:{}, addProfitSharingReceiver response:{}", shopThirdReceive.getWxPayAppId(), response);

            // 更新店铺第三方信息表字段，已添加分账接收方
            if (StrUtil.equals(shopThird.getIsAddReceive(), DictConstant.Whether.no.getCode())) {
                shopThird.setIsAddReceive(DictConstant.Whether.yes.getCode());
                shopThirdService.updateById(shopThird);
            }
        } catch (Exception exp) {
            log.error(exp.getMessage());
            throw new WeikbestException(exp);
        }
        this.save(shopThirdReceive);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeWithShop(Long shopId, Long id) {
        String lockKey = RedisKey.generalKey(RedisKey.Lock.LOCK_REMOVE_SHOP_THIRD_RECEIVE_KEY, shopId, id);
        redisLock.lock(lockKey);
        try {
            // 只剩最后一个分账账号则不让删除
            long count = this.count(new QueryWrapper<ShopThirdReceive>().eq(ShopThirdReceive.ID, id));
            if (count <= WeikbestConstant.ONE_LONG) {
                throw new WeikbestException("请确保至少存在一个分账账号！");
            }

            ShopThirdReceive shopThirdReceive = this.findById(id);
            try {
                // 根据店铺的第三方微信信息切换当前wxPayService
                WxPayService wxPayService = shopThirdService.findWxPayServiceById(shopId);

                // 删除分账接收方
                ProfitSharingReceiver request = ProfitSharingReceiver.newBuilder()
                        .appid(shopThirdReceive.getWxPayAppId())
                        .type(WeikbestConstant.MERCHANT_ID)
                        .account(shopThirdReceive.getWxPayReceiveMchId())
                        .name(shopThirdReceive.getWxPayReceiveName())
                        .relationType(WeikbestConstant.PARTNER).build();
                ProfitSharingReceiver response = wxPayService.getProfitSharingV3Service().deleteProfitSharingReceiver(request);
                log.info("removeWithShop-----deleteProfitSharingReceiver response:{}", response);

            } catch (Exception exp) {
                log.error(exp.getMessage());
                throw new WeikbestException(exp);
            }

            this.removeById(id);
        } finally {
            redisLock.unlock(lockKey);
        }
    }
}

