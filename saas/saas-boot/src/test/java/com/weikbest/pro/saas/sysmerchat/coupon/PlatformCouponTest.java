package com.weikbest.pro.saas.sysmerchat.coupon;

import cn.hutool.core.date.DateUtil;
import com.weikbest.pro.saas.SaasBootApplication;
import com.weikbest.pro.saas.sysmerchat.coupon.module.dto.PlatformCouponDTO;
import com.weikbest.pro.saas.sysmerchat.coupon.service.PlatformCouponService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/11/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SaasBootApplication.class})
public class PlatformCouponTest {

    @Resource
    private PlatformCouponService platformCouponService;

    @Test
    public void testInsert() {
        PlatformCouponDTO platformCouponDTO = new PlatformCouponDTO();
        platformCouponDTO.setName("美背内衣立减20元！券后仅需59元！")
                .setTips("活动使用")
                .setCouponNum(100)
                .setCouponProdType("1")
                .setCouponUseType("1")
                .setCouponUsePrice(new BigDecimal("7900"))
                .setAppId("wx7b8f18a7b5a3f6c8")
                .setDiscountAmount(new BigDecimal("2000"))
                .setGetStartTime(DateUtil.parse("2022-11-07 00:00:00"))
                .setGetEndTime(DateUtil.parse("2022-11-11 00:00:00"))
                .setRestrictCount(4)
                .setDelayEnableDay(0)
                .setValidityDay(7)
                .setCouponThemeType("1");

        boolean insert = platformCouponService.insert(platformCouponDTO);
        System.out.println(insert);
    }
}
