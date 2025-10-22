package com.weikbest.pro.saas.sys.param;

import com.weikbest.pro.saas.SaasBootApplication;
import com.weikbest.pro.saas.sys.param.service.LogisticsCompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SaasBootApplication.class})
public class LogisticsCompanyTest {

    @Resource
    private LogisticsCompanyService logisticsCompanyService;

    @Test
    public void testSync() {
//        logisticsCompanyService.sync();


    }
}
