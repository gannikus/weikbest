package com.weikbest.pro.saas.sys.param.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.redis.RedisLock;
import com.weikbest.pro.saas.common.third.aliyun.areas.AliyunAreasService;
import com.weikbest.pro.saas.common.third.aliyun.areas.config.AliyunAreas;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.transfervo.resp.dtree.Dtree;
import com.weikbest.pro.saas.common.util.json.JsonUtils;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.entity.Region;
import com.weikbest.pro.saas.sys.param.mapper.RegionMapper;
import com.weikbest.pro.saas.sys.param.module.dto.RegionDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.RegionMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.RegionQO;
import com.weikbest.pro.saas.sys.param.service.RegionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 平台行政区划表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-03
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements RegionService {

    @Resource
    private AliyunAreasService aliyunAreasServiceSimple;

    @Resource
    private RedisLock redisLock;

    @Resource
    private RedisContext redisContext;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean sync() {
        boolean tryLock = redisLock.tryLock(RedisKey.Lock.LOCK_REGION_KEY);
        if (!tryLock) {
            throw new WeikbestException("平台行政区划正在同步中...");
        }
        try {
            List<AliyunAreas> aliyunAreas = aliyunAreasServiceSimple.queryAllAreas();

            List<Region> regionList = aliyunAreas.stream().map(RegionMapStruct.INSTANCE::converToEntity).collect(Collectors.toList());

            this.remove(new QueryWrapper<Region>().in(Region.REGION_LEVEL, DictConstant.RegionLevelType.PROVINCE.getCode(), DictConstant.RegionLevelType.CITY.getCode(), DictConstant.RegionLevelType.DISTRICT.getCode()));

            if (regionList.size() > WeikbestConstant.MAX_SAVE_BATCH_SIZE) {
                Iterator<Region> iterator = regionList.iterator();
                List<Region> saveList = new ArrayList<>(WeikbestConstant.MAX_SAVE_BATCH_SIZE);
                while (iterator.hasNext()) {
                    saveList.add(iterator.next());
                    if (saveList.size() == WeikbestConstant.MAX_SAVE_BATCH_SIZE) {
                        this.saveBatch(saveList);
                        saveList.clear();
                    }
                }
                if (saveList.size() > WeikbestConstant.ZERO_LONG) {
                    this.saveBatch(saveList);
                }
            } else {
                this.saveBatch(regionList);
            }
            // 删除redis数据
            redisContext.del(RedisKey.MANAGE_REGION_KEY);
        } catch (Exception e) {
            throw new WeikbestException("平台行政区划同步异常...", e);
        } finally {
            redisLock.unlock(RedisKey.Lock.LOCK_REGION_KEY);
        }

        return true;
    }

    @Override
    public boolean insert(RegionDTO regionDTO) {
        Region region = RegionMapStruct.INSTANCE.converToEntity(regionDTO);
        return this.save(region);
    }

    @Override
    public boolean updateById(Long id, RegionDTO regionDTO) {
        Region region = this.findById(id);
        RegionMapStruct.INSTANCE.copyProperties(regionDTO, region);
        region.setId(id);
        return this.updateById(region);
    }

    @Override
    public Region findById(Long id) {
        Region region = this.getById(id);
        if (ObjectUtil.isNull(region)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return region;
    }

    @Override
    public IPage<Region> queryPage(RegionQO regionQO, PageReq pageReq) {
        QueryWrapper<Region> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(regionQO.getName())) {
            wrapper.eq(Region.NAME, regionQO.getName());
        }
        if (StrUtil.isNotBlank(regionQO.getParentName())) {
            wrapper.eq(Region.PARENT_NAME, regionQO.getParentName());
        }
        if (StrUtil.isNotBlank(regionQO.getRegionLevel())) {
            wrapper.eq(Region.REGION_LEVEL, regionQO.getRegionLevel());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public List<Dtree> queryTree() {
        String cacheDtree = (String) redisContext.get(RedisKey.MANAGE_REGION_KEY);

        if (StrUtil.isNotBlank(cacheDtree)) {
            return JsonUtils.json2Array(cacheDtree, Dtree.class);
        }

        long count = this.count();
        if (count == WeikbestConstant.ZERO_LONG) {
            // 同步
            sync();
        }

        List<Dtree> dtrees = new ArrayList<>();

        // 查询全部数据
        List<Region> provinceRegionList = this.list(new QueryWrapper<Region>().in(Region.REGION_LEVEL, DictConstant.RegionLevelType.PROVINCE.getCode()));
        Map<Integer, List<Region>> cityRegionListMap = this.list(new QueryWrapper<Region>().in(Region.REGION_LEVEL, DictConstant.RegionLevelType.CITY.getCode()))
                .stream().collect(Collectors.groupingBy(Region::getParentAdcode));
        Map<Integer, List<Region>> districtRegionListMap = this.list(new QueryWrapper<Region>().in(Region.REGION_LEVEL, DictConstant.RegionLevelType.DISTRICT.getCode()))
                .stream().collect(Collectors.groupingBy(Region::getParentAdcode));

        if (CollectionUtil.isNotEmpty(provinceRegionList)) {
            dtrees = provinceRegionList.stream().map(provinceRegion -> {
                Dtree provinceDtree = new Dtree(String.valueOf(provinceRegion.getAdcode()), provinceRegion.getName(), String.valueOf(provinceRegion.getParentAdcode()));
                Integer provinceAdcode = provinceRegion.getAdcode();
                List<Region> cityRegionList = cityRegionListMap.get(provinceAdcode);

                if (CollectionUtil.isNotEmpty(cityRegionList)) {
                    List<Dtree> cityDtreeList = cityRegionList.stream().map(cityRegion -> {
                        Dtree cityDtree = new Dtree(String.valueOf(cityRegion.getAdcode()), cityRegion.getName(), String.valueOf(cityRegion.getParentAdcode()));
                        Integer cityAdcode = cityRegion.getAdcode();
                        List<Region> districtRegionList = districtRegionListMap.get(cityAdcode);

                        if (CollectionUtil.isNotEmpty(districtRegionList)) {
                            List<Dtree> districtDtreeList = districtRegionList.stream()
                                    .map(districtRegion -> new Dtree(String.valueOf(districtRegion.getAdcode()), districtRegion.getName(), String.valueOf(districtRegion.getParentAdcode())))
                                    .collect(Collectors.toList());
                            cityDtree.setChildren(districtDtreeList);
                        }
                        return cityDtree;
                    }).collect(Collectors.toList());
                    provinceDtree.setChildren(cityDtreeList);
                }

                return provinceDtree;
            }).collect(Collectors.toList());
        }

        // 放入缓存
        redisContext.set(RedisKey.MANAGE_REGION_KEY, JsonUtils.bean2Json(dtrees));
        return dtrees;
    }

    @Override
    public List<Dtree> queryTree(Collection<Integer> adcodeCollection) {
        List<Dtree> dtrees = new ArrayList<>();

        // 查询全部数据
        List<Region> provinceRegionList = this.list(new QueryWrapper<Region>().in(Region.REGION_LEVEL, DictConstant.RegionLevelType.PROVINCE.getCode()));
        Map<Integer, List<Region>> cityRegionListMap = this.list(new QueryWrapper<Region>().in(Region.REGION_LEVEL, DictConstant.RegionLevelType.CITY.getCode()))
                .stream().collect(Collectors.groupingBy(Region::getParentAdcode));
        Map<Integer, List<Region>> districtRegionListMap = this.list(new QueryWrapper<Region>().in(Region.REGION_LEVEL, DictConstant.RegionLevelType.DISTRICT.getCode()))
                .stream().collect(Collectors.groupingBy(Region::getParentAdcode));

        if (CollectionUtil.isNotEmpty(provinceRegionList)) {
            dtrees = provinceRegionList.stream().map(provinceRegion -> {
                Dtree provinceDtree = new Dtree(String.valueOf(provinceRegion.getAdcode()), provinceRegion.getName(), String.valueOf(provinceRegion.getParentAdcode()));
                Integer provinceAdcode = provinceRegion.getAdcode();
                if (adcodeCollection.contains(provinceAdcode)) {
                    provinceDtree.setChecked(WeikbestConstant.CHEKCED_1);
                }
                List<Region> cityRegionList = cityRegionListMap.get(provinceAdcode);

                if (CollectionUtil.isNotEmpty(cityRegionList)) {
                    List<Dtree> cityDtreeList = cityRegionList.stream().map(cityRegion -> {
                        Dtree cityDtree = new Dtree(String.valueOf(cityRegion.getAdcode()), cityRegion.getName(), String.valueOf(cityRegion.getParentAdcode()));
                        Integer cityAdcode = cityRegion.getAdcode();
                        if (adcodeCollection.contains(cityAdcode)) {
                            cityDtree.setChecked(WeikbestConstant.CHEKCED_1);
                        }
                        List<Region> districtRegionList = districtRegionListMap.get(cityAdcode);

                        if (CollectionUtil.isNotEmpty(districtRegionList)) {
                            List<Dtree> districtDtreeList = districtRegionList.stream()
                                    .map(districtRegion -> {
                                        Dtree districtDree = new Dtree(String.valueOf(districtRegion.getAdcode()), districtRegion.getName(), String.valueOf(districtRegion.getParentAdcode()));
                                        Integer districtAdcode = districtRegion.getAdcode();
                                        if (adcodeCollection.contains(districtAdcode)) {
                                            districtDree.setChecked(WeikbestConstant.CHEKCED_1);
                                        }
                                        return districtDree;
                                    }).collect(Collectors.toList());
                            cityDtree.setChildren(districtDtreeList);
                        }
                        return cityDtree;
                    }).collect(Collectors.toList());
                    provinceDtree.setChildren(cityDtreeList);
                }

                return provinceDtree;
            }).collect(Collectors.toList());
        }

        return dtrees;
    }
}
