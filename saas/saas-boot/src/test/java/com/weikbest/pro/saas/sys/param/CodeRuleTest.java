package com.weikbest.pro.saas.sys.param;

import com.weikbest.pro.saas.SaasBootApplication;
import com.weikbest.pro.saas.sys.common.constant.CodeRuleConstant;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.module.dto.CodeRuleDTO;
import com.weikbest.pro.saas.sys.param.service.CodeRuleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 西瓜瓜
 * @project weikbest
 * @jdk 1.8
 * @since :2022/9/17
 * <p>
 * 编码规则单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SaasBootApplication.class})
public class CodeRuleTest {

    @Resource
    CodeRuleService codeRuleService;

    @Test
    public void testInsert() {
        CodeRuleDTO codeRuleDTO = new CodeRuleDTO();
        codeRuleDTO.setNumber("t_mmdm_shop")
                .setName("店铺表编码生成规则")
                .setPrefix("SHOP")
                .setConnector("-")
                .setInitDigit(4)
                .setRuleType(DictConstant.CodeRuleType.DATETIME_SERIAL.getCode())
                .setDescription("店铺表编码生成规则");
        codeRuleService.insert(codeRuleDTO);
    }

    @Test
    public void testNext() {
        for (int i = 0; i < 100; i++) {
            String shopNum = codeRuleService.nextNum(CodeRuleConstant.T_MMDM_SHOP);
            System.out.println(shopNum);
        }
        System.out.println("--------------------");
        for (int i = 0; i < 100; i++) {
            String prodNum = codeRuleService.nextNum(CodeRuleConstant.T_MMDM_PROD);
            System.out.println(prodNum);
        }
    }
}
