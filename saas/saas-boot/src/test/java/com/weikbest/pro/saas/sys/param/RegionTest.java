package com.weikbest.pro.saas.sys.param;

import com.weikbest.pro.saas.SaasBootApplication;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.third.aliyun.areas.config.AliyunAreas;
import com.weikbest.pro.saas.common.third.aliyun.areas.config.AliyunAreasResult;
import com.weikbest.pro.saas.common.third.aliyun.areas.config.AliyunAreasUrlConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/3
 * <p>
 * 行政区划单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SaasBootApplication.class})
public class RegionTest {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private AliyunAreasUrlConfig aliyunAreasUrlConfig;

    @Test
    public void test() {
        AliyunAreasResult body = restTemplate.getForObject(String.format(aliyunAreasUrlConfig.getProvinceUrl(), WeikbestConstant.CHINA), AliyunAreasResult.class);
        List<AliyunAreas> rows = body.getRows();
        for (AliyunAreas row : rows) {
            System.out.println(row);
        }
    }
}
