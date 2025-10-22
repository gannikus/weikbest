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
import com.weikbest.pro.saas.sys.param.entity.Settle;
import com.weikbest.pro.saas.sys.param.mapper.SettleMapper;
import com.weikbest.pro.saas.sys.param.module.dto.SettleDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.SettleMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.SettleQO;
import com.weikbest.pro.saas.sys.param.service.SettleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 系统结算规则表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-02
 */
@Service
public class SettleServiceImpl extends ServiceImpl<SettleMapper, Settle> implements SettleService {

    /**
     * 系统结算规则表ID
     */
    private static final Long SETTLE_ID = 1010L;

    @Resource
    private RedisContext redisContext;

    @Override
    public boolean insert(SettleDTO settleDTO) {
        Settle settle = SettleMapStruct.INSTANCE.converToEntity(settleDTO);
        settle.setId(SETTLE_ID);
        return this.save(settle);
    }

    @Override
    public boolean updateById(Long id, SettleDTO settleDTO) {
        Settle settle = this.findById(id);
        SettleMapStruct.INSTANCE.copyProperties(settleDTO, settle);
        settle.setId(id);
        return this.updateById(settle);
    }

    @Override
    public boolean saveOrUpdate(SettleDTO settleDTO) {
        try {
            redisContext.del(RedisKey.SETTLE_CONFIG_EXPIRE_KEY);
            Settle settle = this.getById(SETTLE_ID);
            if (ObjectUtil.isEmpty(settle)) {
                // 新增
                return insert(settleDTO);
            } else {
                // 更新
                return updateById(SETTLE_ID, settleDTO);
            }
        } finally {
            redisContext.del(RedisKey.SETTLE_CONFIG_EXPIRE_KEY);
        }
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public Settle findById(Long id) {
        Settle settle = this.getById(id);
        if (ObjectUtil.isNull(settle)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return settle;
    }

    @Override
    public IPage<Settle> queryPage(SettleQO settleQO, PageReq pageReq) {
        QueryWrapper<Settle> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(settleQO.getIsOpenCms())) {
            wrapper.eq(Settle.IS_OPEN_CMS, settleQO.getIsOpenCms());
        }
        if (StrUtil.isNotBlank(settleQO.getIsOpenCmsDetail())) {
            wrapper.eq(Settle.IS_OPEN_CMS_DETAIL, settleQO.getIsOpenCmsDetail());
        }
        if (StrUtil.isNotBlank(settleQO.getUpdateCmsType())) {
            wrapper.eq(Settle.UPDATE_CMS_TYPE, settleQO.getUpdateCmsType());
        }
        if (StrUtil.isNotBlank(settleQO.getIsOpenCmsWithdrawalReview())) {
            wrapper.eq(Settle.IS_OPEN_CMS_WITHDRAWAL_REVIEW, settleQO.getIsOpenCmsWithdrawalReview());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public Settle findSettle() {
        return this.getById(SETTLE_ID);
    }

    @Override
    public Settle findSettle(boolean notnullFlag) {
        Settle settle;
        // 先从redis里查，redis查不到在到数据库里查
        String confRedis = (String) redisContext.get(RedisKey.SETTLE_CONFIG_EXPIRE_KEY);
        if (StrUtil.isNotEmpty(confRedis)) {
            settle = JsonUtils.json2Bean(confRedis, Settle.class);
        } else {
            settle = this.getById(SETTLE_ID);
            if (notnullFlag && ObjectUtil.isEmpty(settle)) {
                throw new WeikbestException("请先配置settle表数据！");
            }
            // 存入Redis中,一天后失效
            if (ObjectUtil.isNotEmpty(settle)) {
                redisContext.set(RedisKey.SETTLE_CONFIG_EXPIRE_KEY, JsonUtils.bean2Json(settle), 24 * 60 * 60);
            }
        }
        return settle;
    }
}
