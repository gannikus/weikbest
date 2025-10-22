/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2022/9/3 11:49:17                            */
/*==============================================================*/

-- t_sys_user
INSERT INTO `t_sys_user`
VALUES (0, '', 'ID-0000', '系统', '18373239250', '', '1', '1', 0, '', '1', 0, 0, '2022-09-18 00:00:00',
        '2022-09-18 00:00:00');
-- t_sys_user_activex
INSERT INTO t_sys_user_activex
VALUES (0, '', 'ID-0000', 'admin', '18373239250', '', '1', '2022-09-18 00:00:00', '2022-09-18 00:00:00');
-- t_sys_user_relate
INSERT INTO `t_sys_user_relate`
VALUES (1010, 0, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
-- t_sys_user_relate
INSERT INTO `t_sys_user_login`
VALUES (1010, '18373239250', 'd8406e8445cc99a16ab984cc28f6931615c766fc', 1, 0, '2022-09-18 00:00:00',
        '2022-09-18 00:00:00');
INSERT INTO `t_sys_user_login`
VALUES (1020, 'ID-0000', 'd8406e8445cc99a16ab984cc28f6931615c766fc', 0, 0, '2022-09-18 00:00:00',
        '2022-09-18 00:00:00');

-- t_sys_org
INSERT INTO `t_sys_org`
VALUES (10000, '组织架构', 0, 1, 1, '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');


-- t_sys_deal
INSERT INTO `t_sys_deal` (`id`, `close_order_minute`, `auto_order_receive`, `auto_order_complete`,
                          `fast_refund_condition1`, `fast_refund_condition2`, `fast_refund_condition3`,
                          `refund_customer_timeout`, `refund_business_timeout`, `refund_repeal_timeout`,
                          `is_open_order_comment`, `is_show_order_comment`, `is_review_order_comment`, `creator`,
                          `modifier`, `gmt_create`, `gmt_modified`)
VALUES ('1010', '1440', '7', '15', '300', '2', '7', '7', '2', '2', '0', '0', '0', '0', '0', '2022-11-02 22:08:03',
        '2022-11-02 22:08:05');

-- t_sys_settle
INSERT INTO `t_sys_settle` (`id`, `bind_customer_max_day`, `bind_customer_continue_day`, `split_deposit_ratio`,
                            `platform_splitting_ratio`, `technical_ledger_ratio`, `commission_ratio`,
                            `order_fund_release_hour`, `rebate_of_deposit_day`, `is_open_cms`, `is_open_cms_detail`,
                            `update_cms_type`, `is_open_cms_withdrawal_review`, `update_cms_time`, `creator`,
                            `modifier`, `gmt_create`, `gmt_modified`)
VALUES ('1010', '60', '21', '30.00', '30.00', '5.00', '10.00', '24', '15', '0', '0', '0', '0', '00:00:00', '0', '0',
        '2022-11-02 21:45:15', '2022-11-02 21:45:18');

-- t_sys_prod_standard
INSERT INTO `t_sys_prod_standard` (`id`, `pay_fail_theme_url`, `left_slide_theme_url`, `reflux_coupon_theme_url`, `reflux_coupon_num`, `reflux_coupon_cardpackage_icon_url`, `reflux_coupon_cardpackage_img_url`, `reflux_coupon_cardpackage_theme`, `prod_price_floor`, `use_method`, `mini_programs_path`, `coupon_code_mode`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES ('1010', '', '', '1', '3', '', '', 'Color100', '1', 'MINI_PROGRAMS', '/pages/special/limited-time-offer/index', 'WECHATPAY_MODE', '0', '0', '2022-11-05 18:46:49', '2022-11-05 21:25:06');

-- t_sys_site
INSERT INTO `t_sys_site` (`id`, `name`, `icon_url`, `merchat_logo_url`, `platform_logo_url`, `wx_gzh_qrcode_url`, `wx_gzh_app_id`, `creator`, `modifier`, `gmt_create`, `gmt_modified`) VALUES ('1010', '', '', '', '', '', '', '0', '0', '2022-11-20 11:40:10', '2022-11-20 11:40:13');

-- t_sys_pay_config
INSERT INTO `t_sys_pay_config` (`id`, `pay_config_type`, `wx_pay_app_id`, `wx_pay_sub_app_id`, `wx_pay_mch_id`, `wx_pay_mch_name`, `wx_pay_mch_key`, `wx_pay_ent_pay_key`, `wx_pay_sub_mch_id`, `wx_pay_notify_url`, `wx_pay_key_path`, `wx_pay_private_key_path`, `wx_pay_private_cert_path`, `wx_pay_api_v3_key`, `wx_pay_api_cert_serial_no`, `wx_pay_mch_effective_time`, `wx_pay_mch_expire_time`, `wx_platform_serial_no`, `wx_platform_effective_time`, `wx_platform_expire_time`, `wx_operate_password`, `wx_pay_api_service_id`, `wx_pay_pay_score_notify_url`, `wx_pay_pay_score_permission_notify_url`, `data_status`, `creator`, `modifier`, `gmt_create`, `gmt_modified`) VALUES ('1020', '3', '', '', 'YOUR_MCH_ID_PLACEHOLDER', '', 'YOUR_MCH_KEY_PLACEHOLDER', '', '', '', 'YOUR_KEY_PATH_PLACEHOLDER', 'YOUR_PRIVATE_KEY_PATH_PLACEHOLDER', 'YOUR_PRIVATE_CERT_PATH_PLACEHOLDER', 'YOUR_API_V3_KEY_PLACEHOLDER', 'YOUR_API_CERT_SERIAL_NO_PLACEHOLDER', '2022-11-16 00:00:00', '2027-11-15 00:00:00', 'YOUR_PLATFORM_SERIAL_NO_PLACEHOLDER', '2022-11-16 21:36:56', '2027-11-15 21:36:56', 'YOUR_OPERATE_PASSWORD_PLACEHOLDER', '', '', '', '1', '0', '0', '2022-11-14 22:53:09', '2022-11-18 10:19:14');
INSERT INTO `t_sys_pay_config` (`id`, `pay_config_type`, `wx_pay_app_id`, `wx_pay_sub_app_id`, `wx_pay_mch_id`, `wx_pay_mch_name`, `wx_pay_mch_key`, `wx_pay_ent_pay_key`, `wx_pay_sub_mch_id`, `wx_pay_notify_url`, `wx_pay_key_path`, `wx_pay_private_key_path`, `wx_pay_private_cert_path`, `wx_pay_api_v3_key`, `wx_pay_api_cert_serial_no`, `wx_pay_mch_effective_time`, `wx_pay_mch_expire_time`, `wx_platform_serial_no`, `wx_platform_effective_time`, `wx_platform_expire_time`, `wx_operate_password`, `wx_pay_api_service_id`, `wx_pay_pay_score_notify_url`, `wx_pay_pay_score_permission_notify_url`, `data_status`, `creator`, `modifier`, `gmt_create`, `gmt_modified`) VALUES ('1593429145086783489', '1', '', '', 'YOUR_MCH_ID_PLACEHOLDER', '', 'YOUR_MCH_KEY_PLACEHOLDER', '', '', '', 'YOUR_KEY_PATH_PLACEHOLDER', 'YOUR_PRIVATE_KEY_PATH_PLACEHOLDER', 'YOUR_PRIVATE_CERT_PATH_PLACEHOLDER', 'YOUR_API_V3_KEY_PLACEHOLDER', 'YOUR_API_CERT_SERIAL_NO_PLACEHOLDER', '2022-11-15 00:00:00', '2027-11-14 00:00:00', 'YOUR_PLATFORM_SERIAL_NO_PLACEHOLDER', '2021-06-16 23:50:17', '2026-06-15 23:50:17', 'YOUR_OPERATE_PASSWORD_PLACEHOLDER', '', '', '', '1', '1577666450347397120', '1577666450347397120', '2022-11-18 10:21:27', '2022-11-18 10:21:27');


-- t_sys_applet_config
INSERT INTO `t_sys_applet_config` (`id`, `applet_name`, `applet_type`, `applet_health_type`, `wx_miniapp_app_id`,
                                   `wx_miniapp_app_secret`, `wx_miniapp_token`, `wx_miniapp_aes_key`,
                                   `wx_miniapp_original_id`, `wx_miniapp_msg_data_format`, `wx_miniapp_colud_env`,
                                   `data_status`, `creator`, `modifier`, `gmt_create`, `gmt_modified`,
                                   `wx_miniapp_type`)
VALUES (1010, '测试小程序', '1', '1', 'YOUR_APP_ID_PLACEHOLDER', 'YOUR_APP_SECRET_PLACEHOLDER', '', '', '', 'JSON', '', '1',
        '0', '0', '2022-09-18 00:00:00', '2022-09-18 00:00:00', 'trial');


-- t_sys_applet_subscribe_config
INSERT INTO `t_sys_applet_subscribe_config` (`id`, `applet_config_id`, `app_id`, `applet_page`, `number`, `name`, `subscribe_type`,
                                             `content`, `params`, `bind_url`, `description`, `data_status`, `creator`,
                                             `modifier`, `gmt_create`, `gmt_modified`, `applet_page`)
VALUES (1010, 1010, 'wx7b8f18a7b5a3f6c8', '/subpackagesA/pages/order/details?number=%s', 'fANFl0MAwi-yaWsHv5DbHkDQcE4tgCmhqDX0cwQ_QrM', '发货通知', '2',
        '订单编号:{{character_string7.DATA}}\\n订单商品:{{thing5.DATA}}\\n发货时间:{{time3.DATA}}\\n快递单号:{{character_string2.DATA}}\\n快递公司:{{thing1.DATA}}\\n',
        '{"character_string7":"${p0}","thing5":"${p1}","time3":"${p2}","character_string2":"${p3}","thing1":"${p4}"}',
        '/order-logistics/delivery',
        '订单编号:xjx20191212\\n订单商品:泰国小菠萝*1 金枕榴莲*2\\n发货时间:2019-11-1 09:29:26\\n快递单号:EP136542054362\\n快递公司:海硕高铁速递\\n', '1',
        '0', '0', '2022-09-18 00:00:00', '2022-09-18 00:00:00', '');
INSERT INTO `t_sys_applet_subscribe_config` (`id`, `applet_config_id`, `app_id`, `applet_page`, `number`, `name`, `subscribe_type`,
                                             `content`, `params`, `bind_url`, `description`, `data_status`, `creator`,
                                             `modifier`, `gmt_create`, `gmt_modified`, `applet_page`)
VALUES (1020, 1010, 'wx7b8f18a7b5a3f6c8', '/subpackagesA/pages/order/details?number=%s', 'nW4IQiVlau-BKNcz2sge3QEpCSHrXoXM9xmyt8ni6Xk', '待付款提醒', '2',
        '商品名称:{{thing2.DATA}}\\n订单数量:{{number11.DATA}}\\n订单金额:{{amount9.DATA}}\\n下单时间:{{date8.DATA}}\\n温馨提醒:{{thing5.DATA}}\\n',
        '{"thing2":"${p0}","number11":"${p1}","amount9":"${p2}","date8":"${p3}","thing5":"${p4}"}',
        '/order/waitPayTimeout', '商品名称:新款男装T恤\\n订单数量:10\\n订单金额:200元\\n下单时间:2019/11/11\\n温馨提醒:请在13:57之前完成支付\\n', '1',
        '0', '0', '2022-09-18 00:00:00', '2022-09-18 00:00:00', '');

-- t_sys_applet_sms_config
INSERT INTO `t_sys_applet_sms_config` (`id`, `sms_template_id`, `applet_config_id`, `app_id`, `applet_page`,
                                       `applet_url_param`, `description`, `data_status`, `creator`, `modifier`,
                                       `gmt_create`, `gmt_modified`)
VALUES (1010, 1020, 1010, 'wx7b8f18a7b5a3f6c8', '/subpackagesA/pages/order/details', 'number=${p0}', '待支付订单', '1', '0',
        '0', '2022-10-22 09:55:26', '2022-10-22 09:55:26');
INSERT INTO `t_sys_applet_sms_config` (`id`, `sms_template_id`, `applet_config_id`, `app_id`, `applet_page`,
                                       `applet_url_param`, `description`, `data_status`, `creator`, `modifier`,
                                       `gmt_create`, `gmt_modified`)
VALUES (1020, 1030, 1010, 'wx7b8f18a7b5a3f6c8', '/subpackagesA/pages/order/details', 'number=${p0}', '下单成功', '1', '0',
        '0', '2022-10-22 09:55:36', '2022-10-22 09:55:36');
INSERT INTO `t_sys_applet_sms_config` (`id`, `sms_template_id`, `applet_config_id`, `app_id`, `applet_page`,
                                       `applet_url_param`, `description`, `data_status`, `creator`, `modifier`,
                                       `gmt_create`, `gmt_modified`)
VALUES (1030, 1040, 1010, 'wx7b8f18a7b5a3f6c8', '/subpackagesA/pages/order/details', 'number=${p0}', '订单已发货', '1', '0',
        '0', '2022-10-22 09:55:44', '2022-10-22 09:55:44');


-- t_sys_sms_template
INSERT INTO `t_sys_sms_template` (`id`, `number`, `name`, `sms_type`, `content`, `params`, `bind_url`, `is_preset`,
                                  `description`, `data_status`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1010, 'SMS_219455227', '微客优选', '验证码', '您的验证码为：${code}，请勿泄露于他人！', '{"code":"${p0}"}', '/verifyPhone', '1', '',
        '1', '0', '0', '2022-09-18 18:36:51', '2022-09-18 18:36:51');
INSERT INTO `t_sys_sms_template` (`id`, `number`, `name`, `sms_type`, `content`, `params`, `bind_url`, `is_preset`,
                                  `description`, `data_status`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1020, 'SMS_255295879', '待支付订单', '短信通知', '您有待支付订单，库存告急，交易即将关闭，请及时支付。请点击https://saas.wkyxsc.cn/sms/${code}，完成支付。',
        '{"code":"${p0}"}', '/waitPayTimeout', '1', '', '1', '0', '0', '2022-10-22 09:28:27', '2022-10-22 09:28:27');
INSERT INTO `t_sys_sms_template` (`id`, `number`, `name`, `sms_type`, `content`, `params`, `bind_url`, `is_preset`,
                                  `description`, `data_status`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1030, 'SMS_255365868', '下单成功', '短信通知', '您已成功下单,点击https://saas.wkyxsc.cn/sms/${code}，查看订单信息,及时跟进订单状态',
        '{"code":"${p0}"}', '/orderSuccess', '1', '', '1', '0', '0', '2022-10-22 09:30:23', '2022-10-22 09:30:23');
INSERT INTO `t_sys_sms_template` (`id`, `number`, `name`, `sms_type`, `content`, `params`, `bind_url`, `is_preset`,
                                  `description`, `data_status`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1040, 'SMS_255275833', '订单已发货', '短信通知', '您的订单已发货，请注意查收，点击https://saas.wkyxsc.cn/sms/${code}，查看订单信息,及时跟进订单状态',
        '{"code":"${p0}"}', '/orderDeliverSuccess', '1', '', '1', '0', '0', '2022-10-22 09:31:18',
        '2022-10-22 09:31:18');


-- t_sys_code_rule
INSERT INTO `t_sys_code_rule`
VALUES (1010, 't_sys_user', '系统用户表工号生成规则', '1', 'ID', '-', 4, 4, '0000', '系统用户表工号生成规则', '1', 0, 0,
        '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_code_rule`
VALUES (1020, 't_mmdm_business', '商户表编码生成规则', '1', 'BUSI', '-', 4, 4, '0000', '商户表编码生成规则', '1', 0, 0,
        '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_code_rule`
VALUES (1030, 't_mmdm_prod', '商品表编码生成规则', '1', 'PROD', '-', 10, 10, '0000000000', '商品表编码生成规则', '1', 0, 0,
        '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_code_rule`
VALUES (1040, 't_mmdm_shop', '店铺表编码生成规则', '2', 'SHOP', '-', 4, 4, '202209180000', '店铺表编码生成规则', '1', 0, 0,
        '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_code_rule`
VALUES (1050, 't_sys_role', '角色表编码生成规则', '1', 'ROLE', '-', 4, 4, '0000', '角色表编码生成规则', '1', 0, 0, '2022-09-18 00:00:00',
        '2022-09-18 00:00:00');
INSERT INTO `t_sys_code_rule`
VALUES (1060, 't_ccmm_customer', '客户表编码规则', '1', 'CUST', '-', 5, 5, '00000', '客户表编码规则', '1', 0, 0,
        '2022-09-18 00:00:00', '2022-09-18 00:00:00');

-- t_sys_delay_task
INSERT INTO `t_sys_delay_task_config` (`id`, `number`, `name`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1000, 'USER_TOKEN', '登录过期', '0', '0', '2022-10-23 16:38:10', '2022-10-23 16:38:13');
INSERT INTO `t_sys_delay_task_config` (`id`, `number`, `name`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1010, 'DELAY_ORDER_WAIT_PAY_TIMEOUT', '订单等待支付超时延时队列', '0', '0', '2022-10-23 16:36:34', '2022-10-23 16:36:36');
INSERT INTO `t_sys_delay_task_config` (`id`, `number`, `name`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1020, 'DELAY_ORDER_PAY_TIMEOUT', '订单支付超时延时队列', '0', '0', '2022-10-23 16:38:10', '2022-10-23 16:38:13');
INSERT INTO `t_sys_delay_task_config` (`id`, `number`, `name`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1030, 'DELAY_ORDER_DELIVER_TIMEOUT', '订单商家发货超时延时队列', '0', '0', '2022-10-23 16:38:10', '2022-10-23 16:38:13');
INSERT INTO `t_sys_delay_task_config` (`id`, `number`, `name`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1040, 'DELAY_ORDER_FINISH_TIMEOUT', '订单自动完成延时队列', '0', '0', '2022-10-23 16:38:10', '2022-10-23 16:38:13');
INSERT INTO `t_sys_delay_task_config` (`id`, `number`, `name`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1100, 'DELAY_ORDER_DELIVER_CUSTOMER_TIMEOUT', '订单商家发货客户确认收货超时延时队列', '0', '0', '2022-10-23 16:38:10',
        '2022-10-23 16:38:13');
INSERT INTO `t_sys_delay_task_config` (`id`, `number`, `name`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1050, 'DELAY_ORDER_AFTER_SALE_BUSINESS_EXECUTETIMEOUT', '售后单商家处理客户申请超时延时队列', '0', '0', '2022-10-23 16:38:10',
        '2022-10-23 16:38:13');
INSERT INTO `t_sys_delay_task_config` (`id`, `number`, `name`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1060, 'DELAY_ORDER_AFTER_SALE_CUSTOMER_EXECUTETIMEOUT', '售后单商家拒绝后客户处理超时延时队列', '0', '0', '2022-10-23 16:38:10',
        '2022-10-23 16:38:13');
INSERT INTO `t_sys_delay_task_config` (`id`, `number`, `name`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1070, 'DELAY_ORDER_AFTER_SALE_CUSTOMER_DELIVERYTIMEOUT', '售后单商家同意后客户发货处理超时延时队列', '0', '0',
        '2022-10-23 16:38:10', '2022-10-23 16:38:13');
INSERT INTO `t_sys_delay_task_config` (`id`, `number`, `name`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1080, 'DELAY_ORDER_AFTER_SALE_BUSINESS_REJECTDELIVERY_CUSTOMER_EXECUTETIMEOUT', '售后单商家拒绝收货后客户处理超时延时队列', '0',
        '0', '2022-10-23 16:38:10', '2022-10-23 16:38:13');
INSERT INTO `t_sys_delay_task_config` (`id`, `number`, `name`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1090, 'DELAY_ORDER_AFTER_SALE_BUSINESS_DELIVERAFTER_YTIMEOUT', '售后单商家确认收货后处理超时延时队列', '0', '0',
        '2022-10-23 16:38:10', '2022-10-23 16:38:13');
INSERT INTO `t_sys_delay_task_config` (`id`, `number`, `name`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1100, 'DELAY_ORDER_DELIVER_CUSTOMER_TIMEOUT', '订单商家发货客户确认收货超时延时队列', '0',
        '0', '2022-10-23 16:38:10', '2022-10-23 16:38:13');
INSERT INTO `t_sys_delay_task_config` (`id`, `number`, `name`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1110, 'DELAY_COUPON_EVENT_END_KEY', '优惠券活动结束延时队列', '0', '0',
        '2022-10-23 16:38:10', '2022-10-23 16:38:13');
INSERT INTO `t_sys_delay_task_config` (`id`, `number`, `name`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1120, 'DELAY_COUPON_EVENT_START_KEY', '优惠券活动生效延时队列', '0',
        '0', '2022-10-23 16:38:10', '2022-10-23 16:38:13');
INSERT INTO `t_sys_delay_task_config` (`id`, `number`, `name`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1130, 'ORDER_COMPLAINT_BUSINESS_EXECUTE_TIMEOUT', '投诉单商家处理过期延时队列', '0',
        '0', '2022-10-23 16:38:10', '2022-10-23 16:38:13');
INSERT INTO `t_sys_delay_task_config` (`id`, `number`, `name`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1140, 'DELAY_COUPON_RESTRICT_TYPE_EXPIRED_KEY', '优惠券领券状态已过期延时任务前缀', '0', '0', '2022-12-04 17:02:39', '2022-12-04 17:02:42');
INSERT INTO `t_sys_delay_task_config` (`id`, `number`, `name`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1150, 'DELAY_COUPON_RESTRICT_TYPE_NOTUSED_KEY', '优惠券领用状态未使用队列前缀', '0', '0', '2022-12-04 17:03:04', '2022-12-04 17:03:07');
INSERT INTO `t_sys_delay_task_config` (`id`, `number`, `name`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1160, 'ORDER_CUST_BUSINESS_BIND_KEY', '客户绑定商户延时队列', '0', '0', '2022-12-04 17:03:04', '2022-12-04 17:03:07');
INSERT INTO `t_sys_delay_task_config` (`id`, `number`, `name`, `creator`, `modifier`, `gmt_create`, `gmt_modified`) VALUES (1170, 'ORDER_FUND_RELEASE_HOUR_TIMEOUT', '订单资金解冻超过小时数时解冻延时队列', '0', '0', '2022-12-20 08:38:31', '2022-12-20 08:38:34');
INSERT INTO `t_sys_delay_task_config` (`id`, `number`, `name`, `creator`, `modifier`, `gmt_create`, `gmt_modified`) VALUES (1180, 'ORDER_SETTLEMENT_REBATE_OF_DEPOSIT_DAY_TIMEOUT', '订单分账押金回退清算延时任务', '0', '0', '2022-12-25 11:34:03', '2022-12-25 11:34:06');
INSERT INTO `t_sys_delay_task_config` (`id`, `number`, `name`, `creator`, `modifier`, `gmt_create`, `gmt_modified`)
VALUES (1190, 'DELAYORDER_AFTER_SALE_BUSINESS_CONFIRM_CUSTOMER_DELIVERY_TIMEOUT', '售后单客户寄回商品后商家确认收货处理过期延时任务', '0', '0', '2022-12-04 17:03:04', '2022-12-04 17:03:07');

-- t_sys_config
INSERT INTO `t_sys_config`
VALUES (1010, 'projectName', 'saas', '1', '系统名称', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_config`
VALUES (1020, 'ban', '', '1', '禁止访问IP', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_config`
VALUES (1030, 'AccessControlAllowOrigin.URL', 'https://admin.wkyxsc.cn,https://saas.wkyxsc.cn,https://shop.wkyxsc.cn',
        '1', '放开跨域访问的url', '1', 0, 0, '2022-09-18 00:00:00',
        '2022-09-18 00:00:00');
INSERT INTO `t_sys_config`
VALUES (1040, 'queryLogisticsIntervalTime', '3', '1', '查询物流间隔时长（小时）', '1', 0, 0, '2022-09-18 00:00:00',
        '2022-09-18 00:00:00');
INSERT INTO `t_sys_config`
VALUES (1050, 'wxPayNotifyUrl', 'https://saas.wkyxsc.cn/AppNotifyUrl/wxPayNotifyUrl', '1', '微信支付回调地址', '1', 0, 0,
        '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_config`
VALUES (1060, 'wxRefundNotifyUrl', 'https://saas.wkyxsc.cn/merchat-wx-notify/notify/refund', '1', '微信退款回调地址', '1', 0, 0,
        '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_config`
VALUES (1070, 'wxBusiFavorNotifyUrl', 'https://saas.wkyxsc.cn/merchat-wx-notify/notify/busiFavor', '1', '微信领券事件回调地址', '1', 0, 0,
        '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_config`
VALUES (1080, 'tencentAdsOauthToken', 'https://saas.wkyxsc.cn/AppNotifyUrl/tencent/ads/oauthToken', '1', '腾讯广告获取accessToken地址', '1', 0, 0,
        '2022-09-18 00:00:00', '2022-09-18 00:00:00');

-- t_sys_dict_type
INSERT INTO `t_sys_dict_type`
VALUES (1000, 'status', '状态', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1010, 'whether', '是否', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1020, 'roleType', '角色类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1030, 'moduleType', '模块类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1040, 'iconClass', '菜单图标', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1050, 'menuType', '菜单类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1060, 'recordSource', '操作来源', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1070, 'regionLevel', '地区级别', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1080, 'logType', '日志类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1090, 'sex', '性别', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1100, 'wxMchType', '微信商户类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1110, 'wxPayReceiveType', '分账接收方类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1120, 'wxPayReceiveRelationType', '分账接收方关系', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1130, 'couponType', '优惠券类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1140, 'couponProdType', '优惠券适用商品类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1150, 'couponUseType', '优惠券使用门槛类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1160, 'couponRestrictUserType', '优惠券领取人限制类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1170, 'couponEnableType', '优惠券生效类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1180, 'shopType', '店铺类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1190, 'tradeType', '所属行业', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1200, 'statementType', '对账单类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1210, 'settleType', '结算状态', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1220, 'payType', '支付渠道类型/支付方式', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1230, 'couponThemeType', '优惠券样式类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1240, 'tpType', '第三方平台类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1250, 'financeType', '账务类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1260, 'capitalFlowType', '资金流向', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1270, 'orderSource', '订单来源', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1280, 'orderStatus', '订单状态', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1290, 'orderPath', '下单路径', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1300, 'complaintReason', '投诉原因', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1310, 'complaintBusiStatus', '投诉状态（商家处理）', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1311, 'complaintCustStatus', '投诉状态（用户认可）', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1320, 'complaintPlatformStatus', '投诉状态（平台介入）', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1330, 'complaintStatus', '投诉状态', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1340, 'complaintType', '投诉类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1350, 'afterSaleKey', '售后关键节点', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1360, 'afterSaleStatus', '售后状态', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1370, 'orderGroupStatus', '订单分组状态', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1380, 'afterSaleFlag', '售后标记', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1390, 'pieceworkType', '计件方式', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1400, 'prodStatus', '商品状态', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1410, 'prodPushType', '商品发布类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1420, 'payStatus', '付款状态', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1430, 'pinkageType', '包邮条件类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1440, 'feightType', '运费类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1450, 'mainRatioType', '商品详情页轮播图比例', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1460, 'prodTheme', '商品详情页轮播图比例', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1470, 'joinShopMainpage', '加入店铺首页', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1480, 'advBackType', '广告回传类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1490, 'jumpLinkType', '跳转链接类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1500, 'attrValType', '商品规格属性值类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1510, 'takeDeliveryType', '收货状态', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1520, 'afterSaleApplyType', '客户售后申请类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1530, 'afterSaleApplyReason', '客户售后申请原因', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1540, 'afterSaleType', '售后申请类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1550, 'appletConfigType', '小程序装修区域类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1560, 'sendType', '发货状态', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1570, 'courierImgType', '快递物流图片类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1580, 'applySourceType', '售后申请来源', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1590, 'userRelateType', '系统用户关联类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1600, 'refundStatus', '退款状态', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1610, 'afterSaleChangeType', '售后协商记录变更类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1620, 'advChannelType', '广告渠道类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1630, 'afterSaleStage', '售后阶段', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1640, 'refundTrend', '退款去向', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1650, 'rejectReason', '拒绝原因', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1660, 'businessType', '商户类别', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1670, 'recommendedSearch', '推荐搜索', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1680, 'couponBackgroundColor', '微信卡包优惠券背景色', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1690, 'couponStatus', '优惠券状态', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1700, 'restrictType', '优惠券领用状态', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1710, 'complaintImgType', '投诉图片类型', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict_type`
VALUES (1720, 'receiveRecordStatus', '分账状态', '1', '', 1, 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');


-- t_sys_dict
INSERT INTO `t_sys_dict`
VALUES (10001001, '1', '有效', 1000, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10001000, '0', '禁用', 1000, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10101001, '1', '是', 1010, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10101000, '0', '否', 1010, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10201000, '0', '平台角色', 1020, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10201001, '1', '商家端角色', 1020, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10201002, '2', 'APP端角色', 1020, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10301001, '0', '平台模块', 1030, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10301002, '1', '商家端模块', 1030, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10301003, '2', 'APP端模块', 1030, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10401000, 'layui-icon-home', 'layui-icon-home', 1040, 1, '1', '主页', '1', 0, 0, '2022-09-18 00:00:00',
        '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10401010, 'layui-icon-component', 'layui-icon-component', 1040, 2, '1', '组件', '1', 0, 0, '2022-09-18 00:00:00',
        '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10401020, 'layui-icon-template', 'layui-icon-template', 1040, 3, '1', '页面', '1', 0, 0, '2022-09-18 00:00:00',
        '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10401030, 'layui-icon-app', 'layui-icon-app', 1040, 4, '1', '应用', '1', 0, 0, '2022-09-18 00:00:00',
        '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10401040, 'layui-icon-senior', 'layui-icon-senior', 1040, 5, '1', '高级', '1', 0, 0, '2022-09-18 00:00:00',
        '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10401050, 'layui-icon-user', 'layui-icon-user', 1040, 6, '1', '用户', '1', 0, 0, '2022-09-18 00:00:00',
        '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10401060, 'layui-icon-set', 'layui-icon-set', 1040, 7, '1', '设置', '1', 0, 0, '2022-09-18 00:00:00',
        '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10401070, 'layui-icon-auz', 'layui-icon-auz', 1040, 8, '1', '授权', '1', 0, 0, '2022-09-18 00:00:00',
        '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10501000, '0', '平台菜单', 1050, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10501001, '1', '商家端菜单', 1050, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10501002, '2', 'APP端菜单', 1050, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10601000, '0', '平台端', 1060, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10601001, '1', '商家端', 1060, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10601002, '2', '小程序', 1060, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10701001, '1', '省、自治区、直辖市', 1070, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10701002, '2', '地级市、地区、自治州、盟', 1070, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10701003, '3', '市辖区、县级市、县', 1070, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10801000, 'S', '新增', 1080, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10801010, 'U', '更新', 1080, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10801020, 'R', '删除', 1080, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10801030, 'Q', '查询', 1080, 4, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10801040, 'A', '授权', 1080, 5, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10801050, 'SU', '新增或更新', 1080, 5, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10901001, '1', '男', 1090, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10901002, '2', '女', 1090, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (10901009, '9', '未知', 1090, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11001001, '1', '普通商户', 1100, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11101000, 'MERCHANT_ID', '商户', 1110, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11101010, 'PERSONAL_OPENID', '个人openid', 1110, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00',
        '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11201000, 'STORE', '门店', 1120, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11201010, 'STAFF', '员工', 1120, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11201020, 'STORE_OWNER', '店主', 1120, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11201030, 'PARTNER', '合作伙伴', 1120, 4, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11201040, 'HEADQUARTER', '总部', 1120, 5, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11201050, 'BRAND', '品牌方', 1120, 6, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11201060, 'DISTRIBUTOR', '分销商', 1120, 7, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11201070, 'USER', '用户', 1120, 8, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11201080, 'SUPPLIER', '供应商', 1120, 9, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11301001, '1', '商品立减劵', 1130, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11301002, '2', '回流优惠券', 1130, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11301003, '3', '平台优惠券', 1130, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11401001, '1', '全部商品', 1140, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11401002, '2', '部分商品', 1140, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11501000, '0', '无使用门槛', 1150, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11501001, '1', '订单满多少金额可用', 1150, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11601001, '1', '不限制，所有人可领取', 1160, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11601002, '2', '指定客户领取', 1160, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11701001, '1', '领券后立即生效', 1170, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11701002, '2', '延迟生效', 1170, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11801000, '0', '非自营店铺', 1180, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11801001, '1', '自营店铺', 1180, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (11901001, '1', '电子商务', 1190, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12001001, '1', '卖出交易', 1200, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12001002, '2', '退款交易', 1200, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12101000, '0', '未结算', 1210, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12101001, '1', '已结算', 1210, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12201001, '1', '微信支付', 1220, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12300001, '1', '默认图片', 1230, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12300002, '2', '上传图片', 1230, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12401001, '1', '微信', 1240, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12501001, '1', '订单收入', 1250, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12501010, '10', '分账押金退回', 1250, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12501020, '20', '分账押金扣款', 1250, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12501030, '30', '售后退款', 1250, 4, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12501040, '40', '平台售后分账回退', 1250, 5, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12501050, '50', '技术服务费-自然流量', 1250, 6, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12601001, '1', '收入', 1260, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12601002, '2', '支出', 1260, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12701001, '1', '自然流量', 1270, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12701005, '5', '广告引流', 1270, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12701010, '10', '回流流量-未回传', 1270, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12701015, '15', '回流流量-已回传', 1270, 4, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12701020, '20', '平台流量', 1270, 5, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
-- 订单状态
INSERT INTO `t_sys_dict`
VALUES (12801001, '1', '待付款', 1280, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12801010, '10', '待发货', 1280, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12801020, '20', '已发货', 1280, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12801030, '30', '已完成', 1280, 4, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12801099, '99', '已关闭', 1280, 5, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12801015, '15', '24小时待发货状态', 1280, 6, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12801040, '40', '发货已超时', 1280, 7, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (12801060, '60', '售后中', 1280, 8, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (12900001, '1', '小程序直接成交（未绑定商家）', 1290, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (12900005, '5', '小程序支付失败成交（未绑定商家）', 1290, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (12900010, '10', '小程序直接成交（绑定商家）', 1290, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (12900015, '15', '小程序支付失败成交（绑定商家）', 1290, 4, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (12900020, '20', '广告直接提交', 1290, 5, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (12900025, '25', '广告支付失败成交', 1290, 6, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (12900030, '30', '左滑操作直接成交', 1290, 7, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (12900035, '25', '左滑操作支付失败成交', 1290, 8, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (12900040, '40', '聚合页首产品直接成交', 1290, 9, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (12900045, '45', '聚合页首产品支付失败成交', 1290, 10, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (12900050, '50', '聚合页非首产品直接成交', 1290, 11, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (12900055, '55', '聚合页非首产品支付失败成交', 1290, 12, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (12900060, '60', '聚合页商户主页直接成交', 1290, 13, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (12900065, '65', '聚合页商户主页支付失败成交', 1290, 14, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (12900070, '70', '回流优惠券页直接成交', 1290, 15, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (12900075, '75', '回流优惠券页支付失败成交', 1290, 16, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (12900080, '80', '限时优惠直接成交', 1290, 17, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (12900085, '85', '限时优惠支付失败成交', 1290, 18, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
-- 投诉原因
INSERT INTO `t_sys_dict`
VALUES (13001000, '1', '发货问题', 1300, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13001001, '2', '商品问题', 1300, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13001003, '3', '客服问题', 1300, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13001004, '4', '其他问题', 1300, 4, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13101000, '0', '未处理', 1310, 0, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13101001, '1', '处理中', 1310, 1, '2', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13101002, '2', '已处理', 1310, 2, '3', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13111000, '0', '不认可', 1311, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13111001, '1', '认可', 1311, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13200000, '0', '未介入', 1320, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13200001, '1', '已介入', 1320, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
-- 投诉状态
INSERT INTO `t_sys_dict` VALUES (13300001, '1', '发起投诉', 1330, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (13300100, '100', '商家同意和解', 1330, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (13300101, '101', '商家不同意和解', 1330, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (13300109, '109', '商家处理超时', 1330, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (13300200, '200', '用户认可和解', 1330, 4, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (13300201, '201', '用户不认可和解', 1330, 5, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (13300209, '209', '用户撤销投诉', 1330, 6, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13401001, '1', '微信支付投诉', 1340, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13401002, '2', '店铺投诉', 1340, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
-- 售后关键节点
INSERT INTO `t_sys_dict`
VALUES (13500000, '0', '售后关闭', 1350, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13500001, '1', '售后成功', 1350, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13500010, '10', '仅退款待处理', 1350, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13500011, '11', '仅退款处理中', 1350, 4, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13500020, '20', '退货退款待处理', 1350, 5, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13500021, '21', '退货退款待商家收货', 1350, 6, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13500022, '22', '退货退款处理中', 1350, 7, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13500030, '30', '换货待处理', 1350, 8, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13500031, '31', '换货待商家收货', 1350, 9, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13500032, '32', '换货处理中', 1350, 10, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13500007, '7', '待买家退货', 1350, 11, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13500008, '8', '客户处理中', 1350, 12, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13500009, '9', '平台处理中', 1350, 13, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
-- 售后状态
INSERT INTO `t_sys_dict`
VALUES (13600000, '0', '售后关闭', 1360, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13600001, '1', '售后成功', 1360, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13600002, '2', '客户申请售后，待商家处理', 1360, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13600003, '3', '客户未处理，平台自动关闭售后', 1360, 4, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13600004, '4', '客户撤销申请', 1360, 5, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13600005, '5', '客户修改申请信息，待商家处理', 1360, 6, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13600006, '6', '客户已寄回商品，待商家收货', 1360, 7, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13600009, '9', '极速退款成功', 1360, 8, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13600010, '10', '商家不同意申请，待客户处理', 1360, 9, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13600011, '11', '商家未处理，平台同意申请', 1360, 10, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13600101, '101', '商家同意仅退款', 1360, 11, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13600201, '201', '商家同意退货退款，待客户寄回商品', 1360, 12, '1', '', '1', 0, 0, '2022-09-18 00:00:00',
        '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13600203, '203', '商家已收货，待商家确认签收', 1360, 13, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13600204, '204', '商家已签收，待商家确认', 1360, 14, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13600205, '205', '商家已同意退款', 1360, 15, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13600206, '206', '商家拒绝退款，待客户处理', 1360, 16, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13600301, '301', '商家同意换货，待客户寄回商品', 1360, 17, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13600303, '303', '商家已收货，待商家确认', 1360, 18, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13600304, '304', '商家已重新发货，待客户确认', 1360, 19, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13600305, '305', '客户确认收货', 1360, 20, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13600306, '306', '商家拒绝换货，待客户处理', 1360, 21, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13600307, '307', '客户未收到货，待重新发起售后', 1360, 22, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
-- 订单分组状态
INSERT INTO `t_sys_dict`
VALUES (13701001, '1', '待付款', 1370, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13701010, '10', '待发货', 1370, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13701020, '20', '已发货', 1370, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13701030, '30', '已完成', 1370, 4, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13701099, '99', '已关闭', 1370, 5, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13701015, '15', '24小时待发货状态', 1370, 6, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13701040, '40', '发货已超时', 1370, 7, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13701060, '60', '售后中', 1370, 8, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');

INSERT INTO `t_sys_dict`
VALUES (13801001, '1', '售后中', 1380, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13801002, '2', '历史售后', 1380, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (13901001, '1', '按件数', 1390, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14001000, '0', '暂存', 1400, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14001001, '1', '上架中', 1400, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14001002, '2', '已下架', 1400, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14101000, '0', '暂不上架', 1410, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14101001, '1', '立即上架', 1410, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14201001, '1', '未支付', 1420, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14201002, '2', '支付中', 1420, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14201003, '3', '支付成功', 1420, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14201004, '4', '支付失败', 1420, 4, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14301001, '1', '元', 1430, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14301002, '2', '件', 1430, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14401001, '1', '统一运费', 1440, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14401002, '2', '运费模板', 1440, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14501001, '1', '1:1', 1450, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14501002, '2', '3:4', 1450, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14601001, '1', '淡入淡出浮层跑马灯', 1460, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14601002, '2', '主图下方跑马灯', 1460, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14701000, '0', '关闭', 1470, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14701001, '1', '已加入', 1470, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14801001, '1', '全部回传', 1480, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14801002, '2', '按投放账号回传', 1480, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14901001, '1', '左滑跳转', 1490, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (14901002, '2', '主页跳转', 1490, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15001001, '1', '文字', 1500, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15101000, '0', '未收货', 1510, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15101001, '1', '已收货', 1510, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15201001, '1', '仅退款', 1520, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15201002, '2', '退货退款', 1520, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15201003, '3', '换货', 1520, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15301001, '1', '少发/漏发', 1530, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15301002, '2', '质量问题', 1530, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15301003, '3', '货物与描述不符', 1530, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15301004, '4', '未按约定时间发货', 1530, 4, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15301005, '5', '发票问题', 1530, 5, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15301006, '6', '卖家发错货', 1530, 6, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15301007, '7', '假冒品牌', 1530, 7, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15301008, '8', '退运费、', 1530, 8, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15301009, '9', '尺寸拍错/不喜欢/效果不好', 1530, 9, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15301010, '10', '其他', 1530, 10, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15400001, '1', '仅退款', 1540, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15400002, '2', '退货退款', 1540, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15400003, '3', '换货', 1540, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15500001, '1', '轮播区', 1550, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15500002, '2', '金刚区', 1550, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15500003, '3', '魔方区', 1550, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15500004, '4', '广告营销区', 1550, 4, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15600000, '0', '未发货', 1560, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15600001, '1', '已发货', 1560, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15700001, '1', '商家发货', 1570, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15700002, '2', '客户寄回', 1570, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15700003, '3', '商家再次发货', 1570, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15800001, '1', '客户申请', 1580, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15800002, '2', '商家代申请', 1580, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15900000, '0', '系统平台用户', 1590, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15900001, '1', '商家端用户', 1590, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (15900002, '2', '小程序端用户', 1590, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16000000, '0', '未退款', 1600, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16000001, '1', '已退款', 1600, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16000002, '2', '退款中', 1600, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16100001, '1', '客户申请', 1610, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16100002, '2', '状态变更', 1610, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16100003, '3', '客户物流信息变更', 1610, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16100004, '4', '商家物流信息变更', 1610, 4, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16200001, '1', '腾讯广告', 1620, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16200002, '2', '快手广告', 1620, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16200003, '3', '序言泽联盟广告', 1620, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16200004, '4', '腾讯视频号广告', 1620, 4, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16200005, '5', '美柚广告', 1620, 5, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16300001, '1', '提交申请', 1630, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16300002, '2', '商家同意', 1630, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16300003, '3', '寄回商品', 1630, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16300004, '4', '商家退款', 1630, 4, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16300005, '5', '退款成功', 1630, 5, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16300006, '6', '商家拒绝', 1630, 6, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16300007, '7', '退款失败', 1630, 7, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16300008, '8', '商家发货', 1630, 8, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16300009, '9', '换货成功', 1630, 9, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16300010, '10', '售后关闭', 1630, 10, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16400001, '1', '原路返回', 1640, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16500001, '1', '已协商一致不退款', 1650, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16500002, '2', '买家要求退款和应退金额不一致', 1650, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16500003, '3', '买家退/换货商品不在承诺的售后范围', 1650, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16500004, '4', '买家退/换货商品为空包裹', 1650, 4, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16500005, '5', '买家退/换货商品少件/漏发', 1650, 5, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16500009, '9', '其他', 1650, 9, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16600001, '1', '普通商户', 1660, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16600002, '2', '品牌商户', 1660, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict`
VALUES (16600003, '3', '特约商户', 1660, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16700001, '1', '领取新人福利', 1670, 1, '1', '/pages/special/new-customers/index', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16700002, '2', '29.9封顶', 1670, 2, '1', '/pages/special/select-good-products/index', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16700003, '3', '热销榜单', 1670, 3, '1', '/pages/special/hot-seller-list/index', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16800010, 'Color010', 'Color010', 1680, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16800020, 'Color020', 'Color020', 1680, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16800030, 'Color030', 'Color030', 1680, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16800040, 'Color040', 'Color040', 1680, 4, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16800050, 'Color050', 'Color050', 1680, 5, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16800060, 'Color060', 'Color060', 1680, 6, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16800070, 'Color070', 'Color070', 1680, 7, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16800080, 'Color080', 'Color080', 1680, 8, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16800090, 'Color090', 'Color090', 1680, 9, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16800100, 'Color100', 'Color100', 1680, 10, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16900001, '1', '待发布', 1690, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16900005, '5', '活动未开始', 1690, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16900010, '10', '进行中', 1690, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16900015, '15', '已结束', 1690, 4, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (16900020, '20', '已取消', 1690, 5, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (17000001, '1', '未生效', 1700, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (17000005, '5', '未使用', 1700, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (17000010, '10', '已过期', 1700, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (17000015, '15', '冻结中', 1700, 4, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (17000020, '20', '用券核销', 1700, 5, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (17000025, '25', '主动核销', 1700, 6, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (17100001, '1', '客户申请投诉', 1710, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (17100002, '2', '协商上传凭证', 1710, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (17200000, '0', '分账中', 1720, 1, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (17200001, '1', '已分账', 1720, 2, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (17200002, '2', '回退中', 1720, 3, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (17200003, '3', '已回退', 1720, 4, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (17200004, '4', '分账失败', 1720, 5, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (17200005, '5', '回退失败', 1720, 6, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (17200006, '6', '已关闭', 1720, 7, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (17200007, '7', '回退中（押金）', 1720, 8, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_dict` VALUES (17200008, '8', '已回退（押金）', 1720, 9, '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');


-- t_sys_menu_module
INSERT INTO `t_sys_menu_module`
VALUES (1010, 'SYS_MANAGE', '后台管理', '1', '1', '', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_menu_module`
VALUES (2010, 'MERCHAT_MAIL', '商城', '1', '1', '', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_menu_module`
VALUES (2020, 'MERCHAT_MANAGE', '管理', '2', '1', '', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');

-- t_sys_menu
INSERT INTO `t_sys_menu`
VALUES (20101010, 2010, '订单管理', 0, '', '', 1, 1, '1', '', '', '1', 0, 0, '2022-9-19 20:58:12', '2022-9-19 20:58:12');
INSERT INTO `t_sys_menu`
VALUES (20101020, 2010, '商品管理', 0, '', '', 1, 2, '1', '', '', '1', 0, 0, '2022-9-19 20:59:58', '2022-9-19 20:59:58');
INSERT INTO `t_sys_menu`
VALUES (20101030, 2010, '店铺营销', 0, '', '', 1, 3, '1', '', '', '1', 0, 0, '2022-9-19 21:01:34', '2022-9-19 21:01:34');
INSERT INTO `t_sys_menu`
VALUES (20101040, 2010, '数据中心', 0, '', '', 1, 4, '1', '', '', '1', 0, 0, '2022-9-19 21:02:04', '2022-9-19 21:02:04');
INSERT INTO `t_sys_menu`
VALUES (20101050, 2010, '财务管理', 0, '', '', 1, 5, '1', '', '', '1', 0, 0, '2022-9-19 21:02:42', '2022-9-19 21:02:42');
INSERT INTO `t_sys_menu`
VALUES (20101060, 2010, '店铺管理', 0, '', '', 1, 6, '1', '', '', '1', 0, 0, '2022-9-19 21:03:34', '2022-9-19 21:03:34');
INSERT INTO `t_sys_menu`
VALUES (20201010, 2020, '账号管理', 0, '', '', 1, 1, '1', '', '', '1', 0, 0, '2022-9-19 21:08:06', '2022-9-19 21:08:06');
INSERT INTO `t_sys_menu`
VALUES (20201020, 2020, '日志', 0, '', '', 1, 2, '1', '', '', '1', 0, 0, '2022-9-19 21:08:58', '2022-9-19 21:08:58');
INSERT INTO `t_sys_menu`
VALUES (201010101010, 2010, '订单查询', 20101010, '', '', 2, 1, '1', '', '', '1', 0, 0, '2022-9-19 20:58:54',
        '2022-9-19 20:58:54');
INSERT INTO `t_sys_menu`
VALUES (201010101020, 2010, '售后查询', 20101010, '', '', 2, 2, '1', '', '', '1', 0, 0, '2022-9-19 20:59:05',
        '2022-9-19 20:59:05');
INSERT INTO `t_sys_menu`
VALUES (201010101030, 2010, '批量发货', 20101010, '', '', 2, 3, '1', '', '', '1', 0, 0, '2022-9-19 20:59:17',
        '2022-9-19 20:59:17');
INSERT INTO `t_sys_menu`
VALUES (201010101040, 2010, '商家地址', 20101010, '', '', 2, 4, '1', '', '', '1', 0, 0, '2022-9-19 20:59:30',
        '2022-9-19 20:59:30');
INSERT INTO `t_sys_menu`
VALUES (201010201010, 2010, '商品列表', 20101020, '', '', 2, 1, '1', '', '', '1', 0, 0, '2022-9-19 21:00:15',
        '2022-9-19 21:00:15');
INSERT INTO `t_sys_menu`
VALUES (201010201020, 2010, '评价管理', 20101020, '', '', 2, 2, '1', '', '', '1', 0, 0, '2022-9-19 21:00:23',
        '2022-9-19 21:00:23');
INSERT INTO `t_sys_menu`
VALUES (201010201030, 2010, '投诉管理', 20101020, '', '', 2, 3, '1', '', '', '1', 0, 0, '2022-9-19 21:00:34',
        '2022-9-19 21:00:34');
INSERT INTO `t_sys_menu`
VALUES (201010201040, 2010, '运费模板', 20101020, '', '', 2, 4, '1', '', '', '1', 0, 0, '2022-9-19 21:00:52',
        '2022-9-19 21:00:52');
INSERT INTO `t_sys_menu`
VALUES (201010201050, 2010, '不配送地区', 20101020, '', '', 2, 5, '1', '', '', '1', 0, 0, '2022-9-19 21:01:12',
        '2022-9-19 21:01:12');
INSERT INTO `t_sys_menu`
VALUES (201010301010, 2010, '优惠券', 20101030, '', '', 2, 1, '1', '', '', '1', 0, 0, '2022-9-19 21:01:48',
        '2022-9-19 21:01:48');
INSERT INTO `t_sys_menu`
VALUES (201010401010, 2010, '数据概览', 20101040, '', '', 2, 1, '1', '', '', '1', 0, 0, '2022-9-19 21:02:20',
        '2022-9-19 21:02:20');
INSERT INTO `t_sys_menu`
VALUES (201010501010, 2010, '资产账户', 20101050, '', '', 2, 1, '1', '', '', '1', 0, 0, '2022-9-19 21:02:56',
        '2022-9-19 21:02:56');
INSERT INTO `t_sys_menu`
VALUES (201010501020, 2010, '交易订单对账', 20101050, '', '', 2, 2, '1', '', '', '1', 0, 0, '2022-9-19 21:03:10',
        '2022-9-19 21:03:10');
INSERT INTO `t_sys_menu`
VALUES (201010601010, 2010, '店铺信息', 20101060, '', '', 2, 1, '1', '', '', '1', 0, 0, '2022-9-19 21:03:46',
        '2022-9-19 21:03:46');
INSERT INTO `t_sys_menu`
VALUES (202010101010, 2020, '账号查询', 20201010, '', '', 2, 1, '1', '', '', '1', 0, 0, '2022-9-19 21:08:33',
        '2022-9-19 21:08:33');
INSERT INTO `t_sys_menu`
VALUES (202010101020, 2020, '角色查询', 20201010, '', '', 2, 2, '1', '', '', '1', 0, 0, '2022-9-19 21:08:45',
        '2022-9-19 21:08:45');
INSERT INTO `t_sys_menu`
VALUES (202010201010, 2020, '操作日志', 20201020, '', '', 2, 1, '1', '', '', '1', 0, 0, '2022-9-19 21:09:11',
        '2022-9-19 21:09:11');

-- t_sys_perm
INSERT INTO `t_sys_perm`
VALUES (1001, 'PERM_0001', '查询', '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_perm`
VALUES (1002, 'PERM_0002', '新增', '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_perm`
VALUES (1003, 'PERM_0003', '修改', '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_perm`
VALUES (1004, 'PERM_0004', '删除', '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_perm`
VALUES (1005, 'PERM_0005', '导入', '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');
INSERT INTO `t_sys_perm`
VALUES (1005, 'PERM_0005', '导出', '1', '', '1', 0, 0, '2022-09-18 00:00:00', '2022-09-18 00:00:00');

-- t_mmdm_order_source_scale
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1010', '1', '1', '0.00', '1.00', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1020', '1', '5', '0.00', '1.00', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1030', '1', '10', '0.10', '0.90', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1040', '1', '15', '0.10', '0.90', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1050', '5', '20', '1.00', '0.00', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1060', '5', '25', '0.95', '0.05', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1070', '5', '30', '0.95', '0.05', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1080', '5', '35', '0.95', '0.05', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1090', '5', '40', '0.95', '0.05', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1100', '5', '45', '0.95', '0.05', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1110', '5', '50', '0.10', '0.90', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1120', '5', '55', '0.10', '0.90', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1130', '5', '60', '0.70', '0.30', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1140', '5', '65', '0.70', '0.30', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1150', '5', '1', '0.10', '0.90', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1160', '5', '5', '0.10', '0.90', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1170', '10', '70', '0.95', '0.05', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1180', '10', '75', '0.95', '0.05', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1190', '10', '1', '0.10', '0.90', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1200', '10', '5', '0.10', '0.90', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1210', '15', '80', '0.10', '0.90', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1220', '15', '85', '0.10', '0.90', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1230', '15', '1', '0.10', '0.90', '1', '0', '0', null, null);
INSERT INTO `t_mmdm_order_source_scale` VALUES ('1240', '15', '5', '0.10', '0.90', '1', '0', '0', null, null);