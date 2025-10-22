package com.weikbest.pro.saas.applet.cust;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weikbest.pro.saas.SaasBootApplication;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.merchat.cust.module.dto.CustomerDTO;
import com.weikbest.pro.saas.merchat.cust.service.CustomerService;
import com.weikbest.pro.saas.sys.system.entity.UserRelate;
import com.weikbest.pro.saas.sys.system.service.UserRelateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/11/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SaasBootApplication.class})
public class AppCustomerTest {

    @Resource
    private CustomerService customerService;

    @Resource
    private UserRelateService userRelateService;

    @Test
    public void test() {
        List<UserRelate> list = userRelateService.list(new QueryWrapper<UserRelate>().eq(UserRelate.USER_ID, 1571489690878611456L)
                .eq(UserRelate.RELATE_TYPE, "2"));
        for (UserRelate userRelate : list) {
            Long id = userRelate.getId();
            UserRelate byId = userRelateService.getById(id);
            System.out.println(byId);
        }


        Long id = 1588868660582289408L;
        CustomerDTO customerDTO = new CustomerDTO().setPhone("15700802337");
        String loginIp = "127.0.0.1";
        TokenUser tokenUser = customerService.updateById(id, customerDTO, loginIp);
        System.out.println(tokenUser);
    }
}
