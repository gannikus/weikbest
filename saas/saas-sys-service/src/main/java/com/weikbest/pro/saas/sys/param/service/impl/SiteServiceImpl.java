package com.weikbest.pro.saas.sys.param.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.json.JsonUtils;
import com.weikbest.pro.saas.sys.param.entity.Site;
import com.weikbest.pro.saas.sys.param.mapper.SiteMapper;
import com.weikbest.pro.saas.sys.param.module.dto.SiteDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.SiteMapStruct;
import com.weikbest.pro.saas.sys.param.service.SiteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 系统站点设置表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-20
 */
@Service
public class SiteServiceImpl extends ServiceImpl<SiteMapper, Site> implements SiteService {

    /**
     * 系统站点设置表ID
     */
    private static final Long SITE_ID = 1010L;

    @Resource
    private RedisContext redisContext;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(SiteDTO siteDTO) {
        Site site = SiteMapStruct.INSTANCE.converToEntity(siteDTO);
        site.setId(SITE_ID);
        return this.save(site);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(Long id, SiteDTO siteDTO) {
        Site site = this.findById(id);
        SiteMapStruct.INSTANCE.copyProperties(siteDTO, site);
        site.setId(id);
        return this.updateById(site);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrUpdate(SiteDTO siteDTO) {
        try {
            redisContext.del(RedisKey.SITE_EXPIRE_KEY);
            Site site = this.getById(SITE_ID);

            if (ObjectUtil.isEmpty(site)) {
                // 新增
                return insert(siteDTO);
            } else {
                // 更新
                return updateById(SITE_ID, siteDTO);
            }
        } finally {
            redisContext.del(RedisKey.SITE_EXPIRE_KEY);
        }
    }

    @Override
    public Site findSite() {
        return this.findSite(true);
    }

    @Override
    public Site findSite(boolean notnullFlag) {
        Site site;
        // 先从redis里查，redis查不到在到数据库里查
        String confRedis = (String) redisContext.get(RedisKey.SITE_EXPIRE_KEY);
        if (StrUtil.isNotEmpty(confRedis)) {
            site = JsonUtils.json2Bean(confRedis, Site.class);
        } else {
            site = this.getById(SITE_ID);
            if (notnullFlag && ObjectUtil.isEmpty(site)) {
                throw new WeikbestException("请先配置site表数据！");
            }
            // 存入Redis中,一天后失效
            if (ObjectUtil.isNotEmpty(site)) {
                redisContext.set(RedisKey.SITE_EXPIRE_KEY, JsonUtils.bean2Json(site), 24 * 60 * 60);
            }
        }
        return site;
    }

    @Override
    public Site findById(Long id) {
        Site site = this.getById(id);
        if (ObjectUtil.isNull(site)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return site;
    }

}
