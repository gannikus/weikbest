drop table if exists t_sys_applet_config;

drop table if exists t_sys_applet_sms_config;

drop table if exists t_sys_applet_subscribe_config;

drop table if exists t_sys_applet_subscribe_record;

drop table if exists t_sys_code_rule;

drop table if exists t_sys_config;

drop table if exists t_sys_deal;

drop table if exists t_sys_delay_task_config;

drop table if exists t_sys_delay_task_record;

drop table if exists t_sys_dict;

drop table if exists t_sys_dict_type;

drop table if exists t_sys_excel_template;

drop table if exists t_sys_log;

drop table if exists t_sys_logistics_company;

drop table if exists t_sys_pay_config;

drop table if exists t_sys_privacy_policy;

drop table if exists t_sys_prod_standard;

drop table if exists t_sys_region;

drop table if exists t_sys_settle;

drop table if exists t_sys_site;

drop table if exists t_sys_sms_record;

drop table if exists t_sys_sms_template;

drop table if exists t_sys_tencent_adv_config;

drop table if exists t_sys_tencent_adv_scope_config;

drop table if exists t_sys_tencent_adv_userrecord;

drop table if exists t_sys_tencent_adv_usersource;

drop table if exists t_sys_third_config;

/*==============================================================*/
/* Table: t_sys_applet_config                                   */
/*==============================================================*/
create table t_sys_applet_config
(
    id                   bigint not null comment '主键ID ',
    applet_name          varchar(50) not null default '' comment '小程序名称',
    applet_type          varchar(2) not null default '1' comment '小程序类型 1-微信小程序',
    applet_deal_trial_score int not null default 100 comment '小程序交易体验分 0-100分',
    applet_health_type   varchar(2) not null default '1' comment '小程序健康状态 1-健康 2-中风险 3-高风险',
    wx_miniapp_type      varchar(20) not null default 'formal' comment '微信小程序类型 developer-开发版 trial-体验版；formal-正式版',
    wx_miniapp_app_id    varchar(100) not null default '' comment '微信小程序-appId',
    wx_miniapp_app_secret varchar(100) not null default '' comment '微信小程序-secret',
    wx_miniapp_token     varchar(100) not null default '' comment '微信小程序-消息服务器配置的token',
    wx_miniapp_aes_key   varchar(100) not null default '' comment '微信小程序-消息服务器配置的EncodingAESKey',
    wx_miniapp_original_id varchar(100) not null default '' comment '微信小程序-小程序原始ID',
    wx_miniapp_msg_data_format varchar(10) not null default 'JSON' comment '微信小程序-消息格式，XML或者JSON，默认JSON',
    wx_miniapp_colud_env varchar(100) not null default '' comment '微信小程序-云环境ID',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_sys_applet_config comment '小程序配置表';

/*==============================================================*/
/* Table: t_sys_applet_sms_config                               */
/*==============================================================*/
create table t_sys_applet_sms_config
(
    id                   bigint not null comment '主键ID ',
    sms_template_id      bigint default 0 comment '短信模板ID',
    applet_config_id     bigint not null default 0 comment '小程序配置表ID',
    app_id               varchar(100) not null default '' comment '小程序ID',
    applet_page          varchar(200) not null default '' comment '小程序页面路径',
    applet_url_param     varchar(500) not null default '' comment '小程序链接参数',
    description          varchar(200) not null default '' comment '描述',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_sys_applet_sms_config comment '小程序绑定短信配置表';

/*==============================================================*/
/* Table: t_sys_applet_subscribe_config                         */
/*==============================================================*/
create table t_sys_applet_subscribe_config
(
    id                   bigint not null comment '主键ID ',
    applet_config_id     bigint not null default 0 comment '小程序配置表ID',
    app_id               varchar(100) not null default '' comment '小程序ID',
    applet_page          varchar(200) not null default '' comment '小程序页面路径',
    number               varchar(60) not null default '' comment '消息模板编码',
    name                 varchar(60) not null default '' comment '消息模板名称',
    subscribe_type       varchar(2) not null default '' comment '消息模板类型 2-一次性订阅 3-长期订阅',
    content              varchar(1000) not null default '' comment '消息模板内容',
    params               varchar(600) not null default '' comment '消息模板参数详情',
    bind_url             varchar(500) not null default '' comment '消息绑定路径，多个路径用,分割',
    description          varchar(200) not null default '' comment '描述',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_sys_applet_subscribe_config comment '小程序订阅消息配置表';

/*==============================================================*/
/* Table: t_sys_applet_subscribe_record                         */
/*==============================================================*/
create table t_sys_applet_subscribe_record
(
    id                   bigint unsigned not null comment '主键id',
    app_id               varchar(100) not null default '' comment '小程序ID',
    applet_page          varchar(200) not null default '' comment '小程序页面路径',
    subscribe_config_id  bigint default 0 comment '短信模板ID',
    number               varchar(60) not null default '' comment '消息模板编码',
    name                 varchar(60) not null default '' comment '消息模板名称',
    subscribe_type       varchar(2) not null default '' comment '消息模板类型 2-一次性订阅 3-长期订阅',
    send_content         varchar(500) not null default '' comment '发送内容 ',
    send_param           varchar(500) not null default '' comment '发送参数映射 ',
    send_phone           varchar(30) not null default '' comment '发送手机号 ',
    bind_url             varchar(500) not null default '' comment '发送消息绑定路径',
    receipt_openid       varchar(100) not null default '' comment '接收客户openid ',
    receipt_cust_name    varchar(100) not null default '' comment '接收客户名称 ',
    send_status          varchar(50) not null default '' comment '发送状态 ',
    send_time            datetime comment '发送时间  yyyy-MM-dd HH:mm:ss ',
    send_exception       text comment '发送失败错误信息 ',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_sys_applet_subscribe_record comment '小程序订阅消息发送记录表';

/*==============================================================*/
/* Table: t_sys_code_rule                                       */
/*==============================================================*/
create table t_sys_code_rule
(
    id                   bigint unsigned not null comment '主键id',
    number               varchar(50) not null default '' comment '编码规则编码',
    name                 varchar(100) not null default '' comment '编码规则名称',
    rule_type            varchar(2) not null default '1' comment '编码规则类型 1-流水号 2-日期时间+流水号',
    prefix               varchar(50) not null default '' comment '固定前缀',
    connector            varchar(10) not null default '' comment '连接符号',
    init_digit           int not null default 0 comment '初始编码位数',
    real_digit           int not null default 0 comment '实际编码位数',
    curr_num             varchar(50) not null default '' comment '当前编码',
    description          varchar(200) not null default '' comment '描述',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统配置表 s_config';

alter table t_sys_code_rule comment '系统编码规则表';

/*==============================================================*/
/* Table: t_sys_config                                          */
/*==============================================================*/
create table t_sys_config
(
    id                   bigint unsigned not null comment '主键id',
    number               varchar(50) not null default '' comment '配置码',
    name                 varchar(100) not null default '' comment '配置值',
    is_preset            char(1) not null default '0' comment '平台预置 0-否 1-是',
    description          varchar(200) not null default '' comment '描述',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统配置表 s_config';

alter table t_sys_config comment '系统配置表';

/*==============================================================*/
/* Table: t_sys_deal                                            */
/*==============================================================*/
create table t_sys_deal
(
    id                   bigint unsigned not null comment '主键id',
    close_order_minute   int not null default 1440 comment '未付款自动关闭分钟数',
    auto_order_receive   int not null default 7 comment '自动收货天数',
    auto_order_complete  int not null default 15 comment '收货后订单自动完成时间',
    fast_refund_condition1 int not null default 300 comment '极速退款条件：订单金额元以内',
    fast_refund_condition2 int not null default 2 comment '极速退款条件：下单小时内',
    fast_refund_condition3 int not null default 7 comment '极速退款条件：申请退款后未操作天数',
    is_open_condition2   char(1) not null default '0' comment '开启极速退款条件：下单小时内 0-否 1-是',
    is_open_condition3   char(1) not null default '0' comment '开启极速退款条件：申请退款后未操作天数 0-否 1-是',
    refund_customer_timeout int not null default 7 comment '买家未处理，系统自动关闭售后时间天',
    refund_business_timeout int not null default 2 comment '商家未处理，系统自动通过售后时间天',
    refund_repeal_timeout int not null default 2 comment '维权被拒绝后，系统自动关闭维权时间天',
    is_open_order_comment char(1) not null default '0' comment '开启订单评价 0-否 1-是',
    is_show_order_comment char(1) not null default '0' comment '显示订单评价 0-否 1-是',
    is_review_order_comment char(1) not null default '0' comment '审核订单评价 0-否 1-是',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统配置表 s_config';

alter table t_sys_deal comment '系统交易规则表';

/*==============================================================*/
/* Table: t_sys_delay_task_config                               */
/*==============================================================*/
create table t_sys_delay_task_config
(
    id                   bigint unsigned not null comment '主键id',
    number               varchar(200) not null default '' comment '延时任务编码',
    name                 varchar(100) not null default '' comment '延时任务名称',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统配置表 s_config';

alter table t_sys_delay_task_config comment '系统延时任务配置表';

/*==============================================================*/
/* Table: t_sys_delay_task_record                               */
/*==============================================================*/
create table t_sys_delay_task_record
(
    id                   bigint unsigned not null comment '主键id',
    delay_task_id        bigint not null default 0 comment '延时任务配置ID',
    delay_task           varchar(500) not null default '' comment '延时任务key',
    name                 varchar(100) not null default '' comment '延时任务名称',
    timeout_date         datetime comment '超时时间 yyyy-mm-dd hh:mm:ss',
    task_status          varchar(2) not null default '0' comment '任务执行状态 -1-已删除 0-等待执行 1-执行成功 2-执行异常 9-无法执行',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统日志表 s_log';

alter table t_sys_delay_task_record comment '系统延时任务执行记录表';

/*==============================================================*/
/* Table: t_sys_dict                                            */
/*==============================================================*/
create table t_sys_dict
(
    id                   bigint unsigned not null comment '主键id',
    number               varchar(60) not null default '' comment '编码',
    name                 varchar(60) not null default '' comment '名称',
    dict_type_id         bigint not null default 0 comment '字典类型ID',
    dict_ord             int unsigned not null default 1 comment '字典排序',
    is_preset            char(1) not null default '0' comment '平台预置 0-否 1-是',
    description          varchar(200) not null default '' comment '备注',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统字典表 s_dict';

alter table t_sys_dict comment '字典表';

/*==============================================================*/
/* Table: t_sys_dict_type                                       */
/*==============================================================*/
create table t_sys_dict_type
(
    id                   bigint unsigned not null comment '主键id',
    number               varchar(50) not null default '' comment '编码',
    name                 varchar(50) not null default '' comment '名称',
    is_preset            char(1) not null default '0' comment '平台预置 0-否 1-是',
    description          varchar(200) not null default '' comment '备注',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统字典类型表 s_dict_type';

alter table t_sys_dict_type comment '字典类型表';

/*==============================================================*/
/* Table: t_sys_excel_template                                  */
/*==============================================================*/
create table t_sys_excel_template
(
    id                   bigint unsigned not null comment '主键id',
    number               varchar(50) not null default '' comment 'excel模板编码',
    name                 varchar(100) not null default '' comment 'excel模板名称',
    template_url         varchar(400) not null default '' comment 'excel模板链接',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统配置表 s_config';

alter table t_sys_excel_template comment '系统excel模板表';

/*==============================================================*/
/* Table: t_sys_log                                             */
/*==============================================================*/
create table t_sys_log
(
    id                   bigint unsigned not null comment '主键id',
    user_id              bigint not null default 0 comment '操作用户ID',
    trace_id             varchar(40) not null default '' comment '操作流水号',
    class_name           varchar(100) not null default '' comment '操作类名',
    method_name          varchar(100) not null default '' comment '操作方法名',
    operate_time         datetime comment '操作时间 yyyy-mm-dd hh:mm:ss',
    details              longtext comment '操作详情',
    operate_ip           varchar(50) not null default '' comment '操作IP',
    log_type             varchar(2) not null default '' comment '日志类型 A-授权 Q-查询 R-删除 S-新增 U-更新 SU-新增或更新',
    record_source        varchar(2) not null default '0' comment '操作来源 0-平台端 1-商家端 2-小程序',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统日志表 s_log';

alter table t_sys_log comment '系统日志表';

/*==============================================================*/
/* Table: t_sys_logistics_company                               */
/*==============================================================*/
create table t_sys_logistics_company
(
    id                   bigint unsigned not null comment '主键id',
    number               varchar(50) not null default '' comment '物流快递公司编码',
    name                 varchar(100) not null default '' comment '物流快递公司名称',
    flag                 char(1) not null default '0' comment '逻辑删除 0-否 1-是',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统配置表 s_config';

alter table t_sys_logistics_company comment '物流快递公司表';

/*==============================================================*/
/* Table: t_sys_pay_config                                      */
/*==============================================================*/
create table t_sys_pay_config
(
    id                   bigint not null comment '主键ID ',
    pay_config_type      varchar(2) not null default '' comment '支付商户号类型  1-普通商户  3-特约商户',
    wx_pay_app_id        varchar(100) not null default '' comment '微信支付-微信公众号或者小程序等的appid',
    wx_pay_sub_app_id    varchar(100) not null default '' comment '微信支付-服务商模式下的子商户公众账号ID',
    wx_pay_mch_id        varchar(100) not null default '' comment '微信支付-商户号',
    wx_pay_mch_name      varchar(100) not null default '' comment '微信支付-商户工商注册全名',
    wx_pay_mch_key       varchar(200) not null default '' comment '微信支付-微信支付商户密钥',
    wx_pay_ent_pay_key   varchar(200) not null default '' comment '微信支付-企业支付密钥',
    wx_pay_sub_mch_id    varchar(200) not null default '' comment '微信支付-服务商模式下的子商户号',
    wx_pay_notify_url    varchar(200) not null default '' comment '微信支付-微信支付异步回调地址，通知url必须为直接可访问的url，不能携带参数',
    wx_pay_key_path      varchar(200) not null default '' comment '微信支付-微信支付商户号证书路径 p12证书的位置',
    wx_pay_private_key_path varchar(200) not null default '' comment '微信支付-apiclient_key.pem证书文件的绝对路径或者以classpath:开头的类路径',
    wx_pay_private_cert_path varchar(200) not null default '' comment '微信支付-apiclient_cert.pem证书文件的绝对路径或者以classpath:开头的类路径',
    wx_pay_api_v3_key    varchar(200) not null default '' comment '微信支付-APIv3密钥',
    wx_pay_api_cert_serial_no varchar(200) not null default '' comment '微信支付-APIv3 证书序列号值',
    wx_pay_mch_effective_time datetime comment '微信支付-微信商户号证书序列号生效时间',
    wx_pay_mch_expire_time datetime comment '微信支付-微信商户号证书序列号失效时间',
    wx_platform_serial_no varchar(100) not null default '' comment '微信支付-微信平台证书序列号',
    wx_platform_effective_time datetime comment '微信支付-微信平台证书序列号生效时间',
    wx_platform_expire_time datetime comment '微信支付-微信平台证书序列号失效时间',
    wx_operate_password  varchar(200) not null default '' comment '微信支付-微信在线申请新证书时填写的操作密码',
    wx_pay_api_service_id varchar(200) not null default '' comment '微信支付-微信支付分',
    wx_pay_pay_score_notify_url varchar(200) not null default '' comment '微信支付-微信支付分回调地址',
    wx_pay_pay_score_permission_notify_url varchar(200) not null default '' comment '微信支付-微信支付分授权回调地址',
    is_bind_busi_favor   char(1) default '0' comment '是否绑定商家券回调 0-否 1-是',
    busi_favor_callback  varchar(1000) default '' comment '绑定商家券回调返回数据',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_sys_pay_config comment '系统支付商户号配置表';

/*==============================================================*/
/* Table: t_sys_privacy_policy                                  */
/*==============================================================*/
create table t_sys_privacy_policy
(
    id                   bigint unsigned not null comment '主键id',
    privacy_policy       text comment '隐私声明',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统配置表 s_config';

alter table t_sys_privacy_policy comment '系统隐私声明表';

/*==============================================================*/
/* Table: t_sys_prod_standard                                   */
/*==============================================================*/
create table t_sys_prod_standard
(
    id                   bigint unsigned not null comment '主键id',
    pay_fail_theme_url   varchar(200) not null default '' comment '支付失败默认弹窗样式',
    left_slide_theme_url varchar(200) not null default '' comment '左滑弹窗默认样式',
    reflux_coupon_theme_url varchar(200) not null default '' comment '回流优惠券-弹框默认样式图片',
    reflux_coupon_num    int not null default 4 comment '回流优惠券数量上限',
    reflux_coupon_image_url varchar(200) not null default '' comment '回流优惠券卡包-默认图片',
    reflux_coupon_image_ossurl varchar(200) not null default '' comment '回流优惠券卡包-默认图片(oss存储)',
    reflux_background_color varchar(20) not null default 'Color100' comment '回流优惠券卡包-默认颜色',
    promptly_merchat_logo_url varchar(200) not null default '' comment '立减优惠券卡包-默认图标',
    promptly_merchat_logo_ossurl varchar(200) not null default '' comment '立减优惠券卡包-默认图标(oss存储)',
    promptly_coupon_image_url varchar(200) not null default '' comment '立减优惠券卡包-默认图片',
    promptly_coupon_image_ossurl varchar(200) not null default '' comment '立减优惠券卡包-默认图片(oss存储)',
    promptly_background_color varchar(20) not null default 'Color100' comment '立减优惠券卡包-默认颜色',
    platform_merchat_logo_url varchar(200) not null default '' comment '平台优惠券卡包-默认图标',
    platform_merchat_logo_ossurl varchar(200) not null default '' comment '平台优惠券卡包-默认图标(oss存储)',
    platform_coupon_image_url varchar(200) not null default '' comment '平台优惠券卡包-默认图片',
    platform_coupon_image_ossurl varchar(200) not null default '' comment '平台优惠券卡包-默认图片(oss存储)',
    platform_background_color varchar(20) not null default 'Color100' comment '平台优惠券卡包-默认颜色',
    prod_price_floor     int not null default 1 comment '商品售价下限元',
    use_method           varchar(20) not null default 'MINI_PROGRAMS' comment '微信优惠券核销方式：OFF_LINE：线下滴码核销 MINI_PROGRAMS：线上小程序核销 PAYMENT_CODE：微信支付付款码核销 SELF_CONSUME：用户自助核销',
    mini_programs_path   varchar(100) not null default '/pages/index/index' comment '回流优惠券默认核销小程序path',
    promptly_mini_programs_path varchar(100) not null default '/pages/index/index' comment '立减优惠券默认核销小程序path',
    platform_mini_programs_path varchar(100) not null default '/pages/index/index' comment '平台优惠券默认核销小程序path',
    coupon_code_mode     varchar(20) not null default 'WECHATPAY_MODE' comment '微信商品券code模式： WECHATPAY_MODE：系统分配券code。（固定22位纯数字）  MERCHANT_API：商户发放时接口指定券code。  MERCHANT_UPLOAD：商户上传自定义code，发券时系统随机选取上传的券code。',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统配置表 s_config';

alter table t_sys_prod_standard comment '系统商品规范表';

/*==============================================================*/
/* Table: t_sys_region                                          */
/*==============================================================*/
create table t_sys_region
(
    id                   bigint unsigned not null comment '主键id-地区id',
    adcode               int not null default 0 comment '地区adcode',
    name                 varchar(100) not null default '' comment '地区名称',
    parent_adcode        int not null default 0 comment '地区父adcode',
    parent_name          varchar(100) not null default '' comment '地区父名称',
    lng                  decimal(10,6) not null default 0.000000 comment '经度',
    lat                  decimal(10,6) not null default 0.000000 comment '纬度',
    region_level         varchar(20) not null default '' comment '地区级别 province-省、自治区、直辖市 city-地级市、地区、自治州、盟 district-市辖区、县级市、县',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='地区表 sys_region';

alter table t_sys_region comment '平台行政区划表';

/*==============================================================*/
/* Table: t_sys_settle                                          */
/*==============================================================*/
create table t_sys_settle
(
    id                   bigint unsigned not null comment '主键id',
    bind_customer_max_day int not null default 60 comment '绑定客户关系最长有效天数',
    bind_customer_continue_day int not null default 21 comment '绑定客户关系持续天数',
    split_deposit_ratio  decimal(5,2) not null default 30.00 comment '分账押金比率',
    platform_splitting_ratio decimal(5,2) not null default 30.00 comment '自然流量平台分账比率',
    technical_ledger_ratio decimal(5,2) not null default 5.00 comment '平台及技术分账比率',
    commission_ratio     decimal(5,2) not null default 10.00 comment '佣金比率',
    order_fund_release_hour int not null default 24 comment '订单资金解冻小时数',
    rebate_of_deposit_day int not null default 15 comment '分账押金回退天数',
    is_open_cms          char(1) not null default '0' comment '开启佣金收益 0-否 1-是',
    is_open_cms_detail   char(1) not null default '0' comment '开启显示佣金明细 0-否 1-是',
    update_cms_type      varchar(2) not null default '0' comment '佣金收益总额更新方式 0-手动更新 1-系统自动更新',
    is_open_cms_withdrawal_review char(1) not null default '0' comment '开启佣金提现审核 0-否 1-是',
    update_cms_time      varchar(20) not null default '00:00:00' comment '佣金收益总额更新时间 HH:mm:ss',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统配置表 s_config';

alter table t_sys_settle comment '系统结算规则表';

/*==============================================================*/
/* Table: t_sys_site                                            */
/*==============================================================*/
create table t_sys_site
(
    id                   bigint unsigned not null comment '主键id',
    name                 varchar(50) not null default '' comment '商城名称',
    icon_url             varchar(200) not null default '' comment '商城图标链接',
    merchat_logo_url     varchar(200) not null default '' comment '商城后端logo链接',
    platform_logo_url    varchar(200) not null default '' comment '后台业务管理系统logo链接',
    wx_gzh_qrcode_url    varchar(200) not null default '' comment '微信公众号二维码链接',
    wx_gzh_app_id        varchar(200) not null default '' comment '微信公众号appId',
    wx_business          varchar(200) not null default '' comment '企业微信客服',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统配置表 s_config';

alter table t_sys_site comment '系统站点设置表';

/*==============================================================*/
/* Table: t_sys_sms_record                                      */
/*==============================================================*/
create table t_sys_sms_record
(
    id                   bigint unsigned not null comment '主键id',
    sms_template_id      bigint default 0 comment '短信模板ID',
    sms_number           varchar(200) not null default '' comment '短信模板编码',
    sms_name             varchar(100) not null default '' comment '短信模板名称',
    sms_type             varchar(200) not null default '' comment '短信模板类型',
    send_content         varchar(500) not null default '' comment '发送内容 ',
    send_param           varchar(500) not null default '' comment '发送参数映射 ',
    send_phone           varchar(30) not null default '' comment '发送手机号 ',
    receipt_userid       bigint not null default 0 comment '接收用户ID ',
    receipt_username     varchar(100) not null default '' comment '接收用户名称 ',
    send_status          varchar(50) not null default '' comment '发送状态 ',
    send_time            datetime comment '发送时间  yyyy-MM-dd HH:mm:ss ',
    send_response        text comment '发送返回报文 ',
    relevance_id         varchar(200) not null default '' comment '关联ID ',
    relevance_type       varchar(20) not null default '' comment '关联ID类型 字典值',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_sys_sms_record comment '短信发送记录表';

/*==============================================================*/
/* Table: t_sys_sms_template                                    */
/*==============================================================*/
create table t_sys_sms_template
(
    id                   bigint unsigned not null comment '主键id',
    number               varchar(60) not null default '' comment '短信模板编码',
    name                 varchar(60) not null default '' comment '短信模板名称',
    sms_type             varchar(200) not null default '' comment '短信模板类型',
    content              varchar(500) not null default '' comment '短信模板内容',
    params               varchar(500) not null default '' comment '短信模板参数详情',
    bind_url             varchar(500) not null default '' comment '短信绑定路径，多个路径用,分割',
    is_preset            char(1) not null default '0' comment '平台预置 0-否 1-是',
    description          varchar(200) not null default '' comment '描述',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_sys_sms_template comment '短信模板表';

/*==============================================================*/
/* Table: t_sys_tencent_adv_config                              */
/*==============================================================*/
create table t_sys_tencent_adv_config
(
    id                   bigint not null comment '主键ID ',
    client_id            bigint not null default 0 comment '应用id',
    client_type          varchar(2) not null default '' comment '应用类型1私有应用、2第三方应用',
    client_icon_path     varchar(200) not null default '' comment '应用图标路径',
    client_secret        varchar(200) not null default '' comment '应用secret',
    client_name          varchar(200) not null default '' comment '应用名称',
    client_level         varchar(60) not null default '' comment '应用级别',
    client_introduce     varchar(200) not null default '' comment '应用介绍',
    client_token_url     varchar(200) not null default '' comment '回调地址',
    client_access_token  varchar(500) not null default '' comment '应用accessToken',
    client_refresh_token varchar(500) not null default '' comment '应用refreshToken',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_sys_tencent_adv_config comment '腾讯广告第三方应用配置表';

/*==============================================================*/
/* Table: t_sys_tencent_adv_scope_config                        */
/*==============================================================*/
create table t_sys_tencent_adv_scope_config
(
    id                   bigint not null comment '主键ID ',
    client_id            bigint not null default 0 comment '应用id',
    authorization_code   varchar(100) not null default '' comment '授权code值有效期5分钟',
    account_id           bigint not null default 0 comment '推广帐号id,有操作权限的帐号 id，包括代理商和广告主帐号 id',
    account_uin          bigint not null default 0 comment '授权的推广帐号对应的 QQ 号',
    scope_list           varchar(2000) not null default '' comment '权限列表，若为空，则表示拥有所属应用的所有权限',
    wechat_account_id    varchar(200) not null default '' comment '授权的推广帐号对应的微信帐号 id',
    account_role_type    varchar(100) not null default '' comment '授权账号身份类型，授权账号类型广告主,代理商,T1 账户,商务管家账户',
    account_type         varchar(100) not null default '' comment '账号类型',
    role_type            varchar(100) not null default '' comment '角色',
    access_token         varchar(500) not null default '' comment '应用accessToken',
    refresh_token        varchar(500) not null default '' comment '应用 refresh token，当 grant_type=refresh_token 时不返回',
    access_token_expires_in integer not null default 0 comment 'access_token 过期时间，单位（秒）',
    refresh_token_expires_in integer not null default 0 comment 'refresh_token 过期时间，单位（秒），当 grant_type=refresh_token 时不返回',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_sys_tencent_adv_scope_config comment '腾讯广告主授权腾讯广告第三方应用表';

/*==============================================================*/
/* Table: t_sys_tencent_adv_userrecord                          */
/*==============================================================*/
create table t_sys_tencent_adv_userrecord
(
    id                   bigint unsigned not null comment '主键id',
    order_id             bigint not null default 0 comment '关联订单ID',
    action_type          varchar(50) not null default '' comment '标准行为类型',
    account_id           bigint not null default 0 comment '推广帐号id,有操作权限的帐号 id，包括代理商和广告主帐号 id',
    user_action_set_id   bigint not null default 0 comment '用户行为源 id',
    ad_aid               varchar(100) not null default '' comment '广告id/广告计划id',
    click_id             varchar(100) not null default '' comment '点击id',
    prod_id              bigint not null default 0 comment '商品ID',
    pay_amount           decimal(10,2) not null default 0.00 comment '支付金额',
    wechat_app_id        varchar(100) not null default '' comment '微信小程序AppID',
    wechat_openid        varchar(100) not null default '' comment '小程序用户openid',
    wechat_unionid       varchar(100) not null default '' comment '小程序用户unionid',
    return_results       varchar(2000) not null default '' comment '应答结果json',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_sys_tencent_adv_userrecord comment '腾讯广告数据上报用户行为数据记录表';

/*==============================================================*/
/* Table: t_sys_tencent_adv_usersource                          */
/*==============================================================*/
create table t_sys_tencent_adv_usersource
(
    id                   bigint unsigned not null comment '主键id',
    client_id            bigint not null default 0 comment '应用id',
    account_id           bigint not null default 0 comment '推广帐号id,有操作权限的帐号 id，包括代理商和广告主帐号 id',
    user_action_set_id   bigint not null default 0 comment '用户行为源 id',
    name                 varchar(200) not null default '' comment '用户行为源名称',
    type                 varchar(60) not null default '' comment '用户行为源类型WEB',
    enable_conversion_claim char(1) not null default '1' comment '是否开启转化归因，true 表示开启，false 表示不开启，不传则默认开启 0-否 1-是',
    wechat_app_id        varchar(100) not null default '' comment '微信 AppID',
    description          varchar(200) not null default '' comment '用户行为源描述',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_sys_tencent_adv_usersource comment '腾讯广告数据上报用户行为数据源表';

/*==============================================================*/
/* Table: t_sys_third_config                                    */
/*==============================================================*/
create table t_sys_third_config
(
    id                   bigint unsigned not null comment '主键id',
    wx_pay_app_id        varchar(100) not null default '' comment '微信支付-微信公众号或者小程序等的appid',
    wx_pay_sub_app_id    varchar(100) not null default '' comment '微信支付-服务商模式下的子商户公众账号ID',
    wx_pay_mch_id        varchar(100) not null default '' comment '微信支付-商户号',
    wx_pay_mch_name      varchar(100) not null default '' comment '微信支付-商户工商注册全名',
    wx_pay_mch_key       varchar(200) not null default '' comment '微信支付-微信支付商户密钥',
    wx_pay_ent_pay_key   varchar(200) not null default '' comment '微信支付-企业支付密钥',
    wx_pay_sub_mch_id    varchar(200) not null default '' comment '微信支付-服务商模式下的子商户号',
    wx_pay_notify_url    varchar(200) not null default '' comment '微信支付-微信支付异步回掉地址，通知url必须为直接可访问的url，不能携带参数',
    wx_pay_key_path      varchar(200) not null default '' comment '微信支付-微信支付商户号证书路径 p12证书的位置',
    wx_pay_private_key_path varchar(200) not null default '' comment '微信支付-apiclient_key.pem证书文件的绝对路径或者以classpath:开头的类路径',
    wx_pay_private_cert_path varchar(200) not null default '' comment '微信支付-apiclient_cert.pem证书文件的绝对路径或者以classpath:开头的类路径',
    wx_pay_api_v3_key    varchar(200) not null default '' comment '微信支付-APIv3密钥',
    wx_pay_api_cert_serial_no varchar(200) not null default '' comment '微信支付-APIv3 证书序列号值',
    wx_pay_api_service_id varchar(200) not null default '' comment '微信支付-微信支付分',
    wx_pay_pay_score_notify_url varchar(200) not null default '' comment '微信支付-微信支付分回调地址',
    wx_pay_pay_score_permission_notify_url varchar(200) not null default '' comment '微信支付-微信支付分授权回调地址',
    wx_miniapp_app_id    varchar(100) not null default '' comment '微信小程序-appId',
    wx_miniapp_app_secret varchar(100) not null default '' comment '微信小程序-secret',
    wx_miniapp_token     varchar(100) not null default '' comment '微信小程序-消息服务器配置的token',
    wx_miniapp_aes_key   varchar(100) not null default '' comment '微信小程序-消息服务器配置的EncodingAESKey',
    wx_miniapp_original_id varchar(100) not null default '' comment '微信小程序-小程序原始ID',
    wx_miniapp_msg_data_format varchar(10) not null default 'JSON' comment '微信小程序-消息格式，XML或者JSON，默认JSON',
    wx_miniapp_colud_env varchar(100) not null default '' comment '微信小程序-云环境ID',
    aliyun_wuliu_appkey  varchar(100) not null default '' comment '阿里云物流appkey',
    aliyun_wuliu_appcode varchar(100) not null default '' comment '阿里云物流appcode',
    aliyun_wuliu_appsecret varchar(100) not null default '' comment '阿里云物流appsecret',
    aliyun_sms_signname  varchar(100) not null default '' comment '阿里云短信签名',
    aliyun_sms_accesskey_id varchar(100) not null default '' comment '阿里云短信id',
    aliyun_sms_accesskey_secret varchar(100) not null default '' comment '阿里云短信secret',
    aliyun_oss_file_endpoint varchar(100) not null default '' comment '阿里云对象存储endpoint',
    aliyun_oss_file_keyid varchar(100) not null default '' comment '阿里云对象存储keyid',
    aliyun_oss_file_keysecret varchar(100) not null default '' comment '阿里云对象存储keysecret',
    aliyun_oss_file_bucketname varchar(100) not null default '' comment '阿里云对象存储bucketname',
    aliyun_ocr_appsecret varchar(100) not null default '' comment '阿里云OCRappsrcret',
    aliyun_ocr_appcode   varchar(100) not null default '' comment '阿里云OCRappcode',
    alipay_appid         varchar(100) not null default '' comment '支付宝应用',
    alipay_pid           varchar(100) not null default '' comment '支付宝商户账号',
    alipay_private_key   varchar(200) not null default '' comment '支付宝私钥',
    alipay_public_key    varchar(200) not null default '' comment '支付宝公钥',
    alipay_return_url    varchar(200) not null default '' comment '支付宝支付回调',
    alipay_app_cert_path varchar(100) not null default '' comment '应用公钥证书文件路径',
    alipay_cert_path     varchar(100) not null default '' comment '支付宝公钥证书文件路径',
    alipay_root_cert_path varchar(100) not null default '' comment '支付宝CA根证书文件路径',
    toutiao_appid        varchar(100) not null default '' comment '字节跳动小程序id',
    toutiao_appsecret    varchar(100) not null default '' comment '字节跳动小程序密钥',
    toutiao_salt         varchar(100) not null default '' comment '字节跳动小程序签名',
    toutiao_notify_url   varchar(200) not null default '' comment '字节跳动小程序支付回调地址',
    toutiao_refund_notify_url varchar(200) not null default '' comment '字节跳动小程序退款回调地址',
    toutiao_access_token varchar(200) not null default '' comment '字节跳动小程序调用凭据',
    toutiao_access_token_update_time varchar(200) not null default '' comment '字节跳动小程序调用凭据更新时间',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_sys_third_config comment '第三方平台配置表 （微信、阿里云等配置）';
