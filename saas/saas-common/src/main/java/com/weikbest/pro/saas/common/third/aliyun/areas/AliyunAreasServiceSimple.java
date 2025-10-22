package com.weikbest.pro.saas.common.third.aliyun.areas;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.third.aliyun.areas.config.AliyunAreas;
import com.weikbest.pro.saas.common.third.aliyun.areas.config.AliyunAreasResult;
import com.weikbest.pro.saas.common.third.aliyun.areas.config.AliyunAreasUrlConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/3
 */
@Slf4j
@Service
public class AliyunAreasServiceSimple implements AliyunAreasService {

    private static final String PROVINCE = "province";
    private static final String CITY = "city";
    private static final String DISTRICT = "district";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private AliyunAreasUrlConfig aliyunAreasUrlConfig;

    @Override
    public List<AliyunAreas> queryAreas(Integer adcode, String level) {
        List<AliyunAreas> resultList = new ArrayList<>();
        String url = "";
        if (StrUtil.equals(level, PROVINCE)) {
            url = String.format(aliyunAreasUrlConfig.getProvinceUrl(), adcode);
        } else if (StrUtil.equals(level, CITY)) {
            url = String.format(aliyunAreasUrlConfig.getCityUrl(), adcode);
        } else if (StrUtil.equals(level, DISTRICT)) {
            url = String.format(aliyunAreasUrlConfig.getDistrictUrl(), adcode);
        }

        if (StrUtil.isNotEmpty(url)) {
//            log.info("AliyunAreasServiceSimple url : {}", url);
            try {
                AliyunAreasResult body = restTemplate.getForObject(url, AliyunAreasResult.class);
                if (ObjectUtil.isNotEmpty(body)) {
                    resultList = body.getRows();
                }
            } catch (HttpClientErrorException e) {
                log.error(String.format("AliyunAreasServiceSimple url : %s", url), e);
            }
        }

        return resultList;
    }

    @Override
    public List<AliyunAreas> queryAllAreas() {
        List<AliyunAreas> resultList = new ArrayList<>();

        // 1. 查询省
        List<AliyunAreas> provinceAreas = queryAreas(WeikbestConstant.CHINA, PROVINCE);
        // 2. 查询市
        for (AliyunAreas provinceArea : provinceAreas) {
            provinceArea.setParentAdcode(WeikbestConstant.CHINA);

            List<AliyunAreas> cityAreas = queryAreas(provinceArea.getAdcode(), CITY);
            // 过滤是自己下面的数据
            cityAreas = cityAreas.stream().filter(cityArea -> StrUtil.equals(provinceArea.getName(), cityArea.getParent())).collect(Collectors.toList());
            // 3. 查询县
            for (AliyunAreas cityArea : cityAreas) {
                cityArea.setParentAdcode(provinceArea.getAdcode());

                List<AliyunAreas> districtAreas = queryAreas(cityArea.getAdcode(), DISTRICT);
                // 过滤是自己下面的数据
                districtAreas = districtAreas.stream().filter(districtArea -> StrUtil.equals(cityArea.getName(), districtArea.getParent())).collect(Collectors.toList());
                for (AliyunAreas districtArea : districtAreas) {
                    districtArea.setParentAdcode(cityArea.getAdcode());
                }
                // 添加返回
                resultList.addAll(districtAreas);
            }
            // 添加返回
            resultList.addAll(cityAreas);
        }
        // 添加返回
        resultList.addAll(provinceAreas);

        // 排序返回
        return CollectionUtil.sortByProperty(resultList, "adcode");
    }
}
