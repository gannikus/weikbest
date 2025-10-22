package com.weikbest.pro.saas.common.ext;

import com.weikbest.pro.saas.SaasBootApplication;
import com.weikbest.pro.saas.sys.common.ext.CreateAvatarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/9/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SaasBootApplication.class})
public class CreateAvatarServiceTest {

    @Resource
    private CreateAvatarService createAvatarService;

    @Test
    public void test() throws IOException {
        String name = "杭州娃";
//        CreateAvatarService.generateImg("哈哈", "D:\\app", "test");
        String headImage = createAvatarService.generateImageAndUploadOss(name);
        System.out.println(headImage);
    }
}
