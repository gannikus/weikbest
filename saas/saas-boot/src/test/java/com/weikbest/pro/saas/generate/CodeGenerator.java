package com.weikbest.pro.saas.generate;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wisdomelon
 * @date 2020/4/4 0004
 * @project saas
 * @jdk 1.8
 */
public class CodeGenerator {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
//            .Builder("jdbc:mysql://47.105.168.195:3306/saas?serverTimezone=GMT%2B8", "root", "Watermelon-mysql@8023");
            .Builder("jdbc:mysql://120.79.190.213:3306/saas?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF8", "root", "ZaqWsxCde3$");

    // 用户登录权限体系实体
    Entity sysUserEntity = new Entity("D:/IdeaProjects/weikbest/saas/saas-sys-service",
            new String[]{"t_sys_user", "t_sys_user_relate", "t_sys_user_login", "t_sys_user_login_record", "t_sys_user_activex",
                    "t_sys_org",
                    "t_sys_role", "t_sys_user_role",
                    "t_sys_menu_module", "t_sys_menu", "t_sys_role_menu", "t_sys_perm", "t_sys_menu_perm"},
            "t_sys_",
            "system",
            "com.weikbest.pro.saas.sys");

    // 系统参数
    Entity sysParamEntity = new Entity("D:/IdeaProjects/weikbest/saas/saas-sys-service",
            new String[]{"t_sys_region", "t_sys_config", "t_sys_dict_type", "t_sys_dict", "t_sys_log", "t_sys_logistics_company",
                    "t_sys_sms_template", "t_sys_sms_record", "t_sys_settle", "t_sys_deal", "t_sys_prod_standard", "t_sys_site", "t_sys_privacy_policy",
                    "t_sys_delay_task", "t_sys_delay_task_record", "t_sys_excel_template",
                    "t_sys_third_config", "t_sys_applet_config", "t_sys_applet_subscribe_config", "t_sys_applet_subscribe_record", "t_sys_applet_sms_config", "t_sys_pay_config",
                    "t_sys_tencent_adv_config", "t_sys_tencent_adv_scope_config", "t_sys_tencent_adv_usersource", "t_sys_tencent_adv_userrecord"},
            "t_sys_",
            "param",
            "com.weikbest.pro.saas.sys");

    // 平台资金池
    Entity sysCapitalEntity = new Entity("D:/IdeaProjects/weikbest/saas/saas-sys-service",
            new String[]{"t_sys_capital_pool", "t_sys_capital_record"},
            "t_sys_",
            "capital",
            "com.weikbest.pro.saas.sys");

    // 商户实体
    Entity merchatBusiEntity = new Entity("D:/IdeaProjects/weikbest/saas/saas-merchat-service",
            new String[]{"t_mmdm_business", "t_mmdm_busi_address", "t_mmdm_busi_user", "t_mmdm_busi_user_relate"},
            "t_mmdm_",
            "busi",
            "com.weikbest.pro.saas.merchat");

    // 商户店铺实体
    Entity merchatBusiShopEntity = new Entity("D:/IdeaProjects/weikbest/saas/saas-merchat-service",
            new String[]{"t_mmdm_shop", "t_mmdm_shop_third", "t_mmdm_shop_third_receive", "t_mmdm_shop_finance_account",
                    "t_mmdm_shop_finance_detail", "t_mmdm_shop_non_region", "t_mmdm_shop_statement_detail",
                    "t_mmdm_shop_user", "t_mmdm_shop_role", "t_mmdm_shop_user_role", "t_mmdm_shop_visit"},
            "t_mmdm_",
            "shop",
            "com.weikbest.pro.saas.merchat");

    // 商户店铺优惠券实体
    Entity merchatBusiCouponEntity = new Entity("D:/IdeaProjects/weikbest/saas/saas-merchat-service",
            new String[]{"t_mmdm_coupon", "t_mmdm_coupon_customer_entry", "t_mmdm_coupon_prod_entry", "t_mmdm_coupon_scene", "t_mmdm_coupon_scene_config"},
            "t_mmdm_",
            "coupon",
            "com.weikbest.pro.saas.merchat");

    // 运费模板实体
    Entity merchatFeightEntity = new Entity("D:/IdeaProjects/weikbest/saas/saas-merchat-service",
            new String[]{"t_mmdm_feight_template", "t_mmdm_feight_template_detail", "t_mmdm_feight_template_region"},
            "t_mmdm_",
            "feight",
            "com.weikbest.pro.saas.merchat");

    // 订单实体
    Entity merchatOrderEntity = new Entity("D:/IdeaProjects/weikbest/saas/saas-merchat-service",
            new String[]{"t_mmdm_order_info", "t_mmdm_order_cust_info", "t_mmdm_order_logistics", "t_mmdm_order_logistics_img", "t_mmdm_order_pay_record", "t_mmdm_order_prod_info", "t_mmdm_order_rec_addr", "t_mmdm_order_stat_record",
                    "t_mmdm_order_batch_deliver", "t_mmdm_order_batch_deliver_record", "t_mmdm_order_source_scale", "t_mmdm_order_receive_record"},
            "t_mmdm_",
            "order",
            "com.weikbest.pro.saas.merchat");

    // 订单售后实体
    Entity merchatOrderAfterSaleEntity = new Entity("D:/IdeaProjects/weikbest/saas/saas-merchat-service",
            new String[]{"t_mmdm_order_after_sale", "t_mmdm_order_after_sale_img", "t_mmdm_order_after_sale_logistics_img", "t_mmdm_order_after_sale_consult_record", "t_mmdm_order_after_sale_img_his", "t_mmdm_order_after_sale_logistics_img_his",
                    "t_mmdm_order_complaint", "t_mmdm_order_complaint_process_record"},
            "t_mmdm_",
            "aftersale",
            "com.weikbest.pro.saas.merchat");

    // 订单投诉实体
    Entity merchatOrderComplaintEntity = new Entity("D:/IdeaProjects/weikbest/saas/saas-merchat-service",
            new String[]{"t_mmdm_order_complaint", "t_mmdm_order_complaint_img", "t_mmdm_order_complaint_record", "t_mmdm_order_complaint_img_his"},
            "t_mmdm_",
            "complaint",
            "com.weikbest.pro.saas.merchat");

    // 商品分类实体
    Entity merchatProdCategoryEntity = new Entity("D:/IdeaProjects/weikbest/saas/saas-merchat-service",
            new String[]{"t_mmdm_prod_category", "t_mmdm_prod_brand", "t_mmdm_prod_attr", "t_mmdm_prod_attr_val",
                    "t_mmdm_applet_prod_category", "t_mmdm_applet_prod_category_relate", "t_mmdm_applet_dec_config", "t_mmdm_applet_dec_config_entry"},
            "t_mmdm_",
            "category",
            "com.weikbest.pro.saas.merchat");

    // 商品实体
    Entity merchatProdEntity = new Entity("D:/IdeaProjects/weikbest/saas/saas-merchat-service",
            new String[]{"t_mmdm_prod", "t_mmdm_prod_feight", "t_mmdm_prod_theme", "t_mmdm_prod_detail", "t_mmdm_prod_mainimg", "t_mmdm_prod_busi_addr", "t_mmdm_prod_price",
                    "t_mmdm_prod_dec_floor", "t_mmdm_prod_adv_back_account",
                    "t_mmdm_prod_left_slide", "t_mmdm_prod_jump_link",
                    "t_mmdm_prod_coupon", "t_mmdm_prod_coupon_relate", "t_mmdm_prod_applet_relate",
                    "t_mmdm_prod_combo", "t_mmdm_prod_combo_attr_relate",
                    "t_mmdm_prod_sku", "t_mmdm_prod_attr_relate", "t_mmdm_prod_stock"},
            "t_mmdm_",
            "prod",
            "com.weikbest.pro.saas.merchat");

    // 客户实体
    Entity merchatCustEntity = new Entity("D:/IdeaProjects/weikbest/saas/saas-merchat-service",
            new String[]{"t_ccmm_customer", "t_ccmm_cust_address", "t_mmdm_cust_business_bind"},
            "t_ccmm_",
            "cust",
            "com.weikbest.pro.saas.merchat");

    // 客户评论实体
    Entity merchatCustCommentEntity = new Entity("D:/IdeaProjects/weikbest/saas/saas-merchat-service",
            new String[]{"t_ccmm_cust_comment_img", "t_ccmm_cust_comment"},
            "t_ccmm_",
            "comment",
            "com.weikbest.pro.saas.merchat");

    // 客户售后实体
    Entity merchatCustAfterSaleEntity = new Entity("D:/IdeaProjects/weikbest/saas/saas-merchat-service",
            new String[]{"t_ccmm_cust_after_sale", "t_ccmm_cust_after_sale_img"},
            "t_ccmm_",
            "aftersale",
            "com.weikbest.pro.saas.merchat");

    // 客户优惠券实体
    Entity merchatCustCouponEntity = new Entity("D:/IdeaProjects/weikbest/saas/saas-merchat-service",
            new String[]{"t_ccmm_cust_coupon_restrict"},
            "t_ccmm_",
            "coupon",
            "com.weikbest.pro.saas.merchat");

    // 更新实体
    Entity updateEntity = new Entity("D:/IdeaProjects/weikbest/saas/saas-merchat-service",
            new String[]{"t_mmdm_busi_user_relate"},
            "t_mmdm_",
            "busi",
            "com.weikbest.pro.saas.merchat");

    Entity update2Entity = new Entity("D:/IdeaProjects/weikbest/saas/saas-merchat-service",
            new String[]{"t_mmdm_cust_business_bind"},
            "t_mmdm_",
            "busi",
            "com.weikbest.pro.saas.merchat");
    ;

    Entity update3Entity = new Entity("D:/IdeaProjects/weikbest/saas/saas-merchat-service",
            new String[]{"t_mmdm_order_receive_record"},
            "t_mmdm_",
            "order",
            "com.weikbest.pro.saas.merchat");

    Entity update4Entity = new Entity("D:/IdeaProjects/weikbest/saas/saas-merchat-service",
            new String[]{"t_mmdm_order_batch_deliver_record"},
            "t_mmdm_",
            "order",
            "com.weikbest.pro.saas.merchat");


    @Test
    public void main1() {
//        generator(sysUserEntity, IdType.ASSIGN_ID, false);
//        generator(sysParamEntity, IdType.ASSIGN_ID, false);
//        generator(sysCouponEntity, IdType.ASSIGN_ID, true);
//        generator(sysCapitalEntity, IdType.ASSIGN_ID, false);

//        generator(merchatBusiEntity, IdType.ASSIGN_ID, false);
//        generator(merchatBusiShopEntity, IdType.ASSIGN_ID, false);
//        generator(merchatBusiCouponEntity, IdType.ASSIGN_ID, true);
//        generator(merchatFeightEntity, IdType.ASSIGN_ID, false);
//        generator(merchatOrderComplaintEntity, IdType.ASSIGN_ID, false);
//        generator(merchatOrderAfterSaleEntity, IdType.ASSIGN_ID, false);
//        generator(merchatOrderEntity, IdType.ASSIGN_ID, false);
//        generator(merchatProdCategoryEntity, IdType.ASSIGN_ID, false);
//        generator(merchatProdEntity, IdType.ASSIGN_ID, false);
//
//        generator(merchatCustEntity, IdType.ASSIGN_ID, false);
//        generator(merchatCustCommentEntity, IdType.ASSIGN_ID, false);
//        generator(merchatCustAfterSaleEntity, IdType.ASSIGN_ID, false);
//        generator(merchatCustCouponEntity, IdType.ASSIGN_ID, true);

       generator(updateEntity, IdType.ASSIGN_ID, true);
//        generator(update2Entity, IdType.ASSIGN_ID, false);
//        generator(update3Entity, IdType.ASSIGN_ID, false);
//       generator(update4Entity, IdType.ASSIGN_ID, false);

//        generator(merchatOrderEntity, IdType.ASSIGN_ID, false);
//        generator(merchatFeightEntity, IdType.ASSIGN_ID, false);
//        generator(merchatOrderAfterSaleEntity, IdType.ASSIGN_ID, false);
//        generator(merchatProdCategoryEntity, IdType.ASSIGN_ID, false);
//        generator(merchatCustEntity, IdType.ASSIGN_ID, false);
//        generator(merchatCustAfterSaleEntity, IdType.ASSIGN_ID, false);

    }

    private void generator(Entity entity, IdType idType, boolean fileOverrideFlag) {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 1、全局配置
                .globalConfig(builder -> builder.author("wisdomelon")
                        .outputDir(entity.projectName + "/src/main/java")
                        .dateType(DateType.ONLY_DATE)
                        .enableSwagger()
                        .disableOpenDir())
                // 2、包配置

                .packageConfig(builder -> builder.parent(entity.rootPackageName)
                        .moduleName(entity.moduleName)
                        .controller("controller")
                        .entity("entity")
                        .service("service")
                        .mapper("mapper")
                        .xml("mapper")
                        .pathInfo(MapUtil.of(OutputFile.xml, entity.projectName + "/src/main/resources/mapper/" + entity.moduleName + "/"))) // 配置xml文件的生成路径
                // 3、策略配置
                .strategyConfig(builder -> {
                    // 全局策略配置
                    builder.addInclude(entity.tableName) // 增加表匹配(内存过滤)
                            .addTablePrefix(entity.tablePrefix); // 生成实体时去掉表前缀

                    // controller 策略配置
                    builder.controllerBuilder()
                            .enableRestStyle() // 开启生成@RestController 控制器
                            .enableHyphenStyle(); // 开启驼峰转连字符

                    // service 策略配置
                    builder.serviceBuilder()
                            .formatServiceFileName("%sService"); //去掉Service接口的首字母I

                    // mapper 策略配置
                    builder.mapperBuilder();

                    // entity 实体策略配置
                    builder.entityBuilder()
                            .naming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                            .enableLombok() // 开启 lombok 模型
                            .enableColumnConstant() // 开启生成字段常量
                            .enableChainModel() // 开启链式模型
                            .columnNaming(NamingStrategy.underline_to_camel) // 数据库表字段映射到实体的命名策略
                            .enableTableFieldAnnotation() // 开启生成实体时生成字段注解
                            .idType(IdType.ASSIGN_ID) // 全局主键类型
                            .versionColumnName("gmt_modified") // 乐观锁字段名(数据库)
                            .logicDeleteColumnName("flag") // 逻辑删除字段名(数据库)
                            .addTableFills(new Column("creator", FieldFill.INSERT)) // 表字段填充
                            .addTableFills(new Column("gmt_create", FieldFill.INSERT)) // 表字段填充
                            .addTableFills(new Column("modifier", FieldFill.INSERT_UPDATE)) // 表字段填充
                            .addTableFills(new Column("gmt_modified", FieldFill.INSERT_UPDATE)); // 表字段填充
                    if (fileOverrideFlag) {
                        // 覆盖已有文件
                        builder.controllerBuilder().enableFileOverride();
                        builder.serviceBuilder().enableFileOverride();
                        builder.mapperBuilder().enableFileOverride();
                        builder.entityBuilder().enableFileOverride();
                    }


                })
                // 4、模板配置
                .templateConfig(builder -> builder
                        .controller("/template/controller.java.vm")
                        .service("/template/service.java.vm")
                        .serviceImpl("/template/serviceImpl.java.vm")
                        .entity("/template/entity.java.vm"))
                // 5、注入配置
                .injectionConfig(builder -> {
                    // 设置自定义属性
                    Map<String, Object> paramMap = new HashMap<>();
                    paramMap.put("projectName", "saas");
                    paramMap.put("noInsertFields", Lists.newArrayList("id", "creator", "modifier", "gmt_create", "gmt_modified"));
                    paramMap.put("noQueryFields", Lists.newArrayList("id", "creator", "modifier", "gmt_create", "gmt_modified"));
                    paramMap.put("defaultFields", Lists.newArrayList("isPreset", "description", "dataStatus"));
                    paramMap.put("packageQO", "module.qo");
                    paramMap.put("packageVO", "module.vo");
                    paramMap.put("packageDTO", "module.dto");
                    paramMap.put("packageMapstruct", "module.mapstruct");
                    builder.customMap(paramMap);

                    // 添加自定义输出文件
                    String ooFilePath = entity.projectName + "/src/main/java/" + StrUtil.replace(entity.rootPackageName, ".", "/") + "/" + entity.moduleName + "/module/";
                    CustomFile.Builder qoBuilder = new CustomFile.Builder().fileName("QO.java").templatePath("/template/qo.java.vm").filePath(ooFilePath + "qo/");
                    CustomFile.Builder voBuilder = new CustomFile.Builder().fileName("VO.java").templatePath("/template/vo.java.vm").filePath(ooFilePath + "vo/");
                    CustomFile.Builder dtoBuilder = new CustomFile.Builder().fileName("DTO.java").templatePath("/template/dto.java.vm").filePath(ooFilePath + "dto/");
                    CustomFile.Builder mapstructBuilder = new CustomFile.Builder().fileName("MapStruct.java").templatePath("/template/mapstruct.java.vm").filePath(ooFilePath + "mapstruct/");
                    if (fileOverrideFlag) {
                        // 覆盖已有文件
                        qoBuilder.enableFileOverride();
                        voBuilder.enableFileOverride();
                        dtoBuilder.enableFileOverride();
                        mapstructBuilder.enableFileOverride();
                    }
                    builder.customFile(Lists.newArrayList(qoBuilder.build(), voBuilder.build(), dtoBuilder.build(), mapstructBuilder.build()));
                })
                // 执行
                .execute();
    }

    static class Entity {
        String projectName;

        String[] tableName;

        String tablePrefix;

        String moduleName;

        String rootPackageName;

        public Entity(String projectName, String[] tableName, String tablePrefix, String moduleName, String rootPackageName) {
            this.projectName = projectName;
            this.tableName = tableName;
            this.tablePrefix = tablePrefix;
            this.moduleName = moduleName;
            this.rootPackageName = rootPackageName;
        }
    }
}
