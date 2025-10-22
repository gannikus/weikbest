package com.weikbest.pro.saas.sys.param.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.json.JsonUtils;
import com.weikbest.pro.saas.sys.param.entity.Deal;
import com.weikbest.pro.saas.sys.param.mapper.DealMapper;
import com.weikbest.pro.saas.sys.param.module.dto.DealDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.DealMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.DealQO;
import com.weikbest.pro.saas.sys.param.service.DealService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 系统交易规则表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Service
public class DealServiceImpl extends ServiceImpl<DealMapper, Deal> implements DealService {

    /**
     * 主键
     */
    private static final Long DEAL_ID = 1010L;

    @Resource
    private RedisContext redisContext;

    @Override
    public boolean insert(DealDTO dealDTO) {
        Deal deal = DealMapStruct.INSTANCE.converToEntity(dealDTO);
        deal.setId(DEAL_ID);
        return this.save(deal);
    }

    @Override
    public boolean updateById(Long id, DealDTO dealDTO) {
        Deal deal = this.findById(id);
        DealMapStruct.INSTANCE.copyProperties(dealDTO, deal);
        deal.setId(id);
        return this.updateById(deal);
    }

    @Override
    public boolean saveOrUpdate(DealDTO dealDTO) {
        try {
            redisContext.del(RedisKey.DEAL_CONFIG_EXPIRE_KEY);
            Deal deal = this.getById(DEAL_ID);
            if (ObjectUtil.isEmpty(deal)) {
                // 新增
                return insert(dealDTO);
            } else {
                // 更新
                return updateById(DEAL_ID, dealDTO);
            }
        } finally {
            redisContext.del(RedisKey.DEAL_CONFIG_EXPIRE_KEY);
        }

    }

    @Override
    public Deal findById(Long id) {
        Deal deal = this.getById(id);
        if (ObjectUtil.isNull(deal)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return deal;
    }

    @Override
    public IPage<Deal> queryPage(DealQO dealQO, PageReq pageReq) {
        QueryWrapper<Deal> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(dealQO.getIsOpenOrderComment())) {
            wrapper.eq(Deal.IS_OPEN_ORDER_COMMENT, dealQO.getIsOpenOrderComment());
        }
        if (StrUtil.isNotBlank(dealQO.getIsShowOrderComment())) {
            wrapper.eq(Deal.IS_SHOW_ORDER_COMMENT, dealQO.getIsShowOrderComment());
        }
        if (StrUtil.isNotBlank(dealQO.getIsReviewOrderComment())) {
            wrapper.eq(Deal.IS_REVIEW_ORDER_COMMENT, dealQO.getIsReviewOrderComment());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public Deal findDeal() {
        return findDeal(true);
    }

    @Override
    public Deal findDeal(boolean notnullFlag) {
        Deal deal;
        // 先从redis里查，redis查不到在到数据库里查
        String confRedis = (String) redisContext.get(RedisKey.DEAL_CONFIG_EXPIRE_KEY);
        if (StrUtil.isNotEmpty(confRedis)) {
            deal = JsonUtils.json2Bean(confRedis, Deal.class);
        } else {
            deal = this.getById(DEAL_ID);
            if (notnullFlag && ObjectUtil.isEmpty(deal)) {
                throw new WeikbestException("请先配置deal表数据！");
            }
            // 存入Redis中,一天后失效
            if (ObjectUtil.isNotEmpty(deal)) {
                redisContext.set(RedisKey.DEAL_CONFIG_EXPIRE_KEY, JsonUtils.bean2Json(deal), 24 * 60 * 60);
            }
        }
        return deal;
    }


}
