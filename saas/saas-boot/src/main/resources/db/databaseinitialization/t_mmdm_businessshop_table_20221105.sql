drop table if exists t_mmdm_busi_address;

drop table if exists t_mmdm_busi_user;

drop table if exists t_mmdm_business;

drop table if exists t_mmdm_cust_business_bind;

drop table if exists t_mmdm_shop;

drop table if exists t_mmdm_shop_finance_account;

drop table if exists t_mmdm_shop_finance_detail;

drop table if exists t_mmdm_shop_non_region;

drop table if exists t_mmdm_shop_role;

drop table if exists t_mmdm_shop_statement_detail;

drop table if exists t_mmdm_shop_third;

drop table if exists t_mmdm_shop_third_receive;

drop table if exists t_mmdm_shop_user;

drop table if exists t_mmdm_shop_user_role;

drop table if exists t_mmdm_shop_visit;

/*==============================================================*/
/* Table: t_mmdm_busi_address                                   */
/*==============================================================*/
create table t_mmdm_busi_address
(
    id                   bigint not null comment '主键id ',
    business_id          bigint not null default 0 comment '商户ID',
    name                 varchar(50) not null default '' comment '联系人',
    busi_phone           varchar(50) not null default '' comment '联系方式',
    busi_area_code       varchar(50) not null default '' comment '区号',
    busi_landline_number varchar(50) not null default '' comment '座机号',
    busi_extension_number varchar(50) not null default '' comment '分机号',
    busi_province        varchar(60) not null default '' comment '省、直辖市',
    busi_city            varchar(60) not null default '' comment '市',
    busi_district        varchar(60) not null default '' comment '区、县',
    addr                 varchar(500) not null default '' comment '详细地址',
    def                  char(1) not null default '0' comment '是否默认 0-否 1-是',
    description          varchar(200) not null default '' comment '备注',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_busi_address comment '商家详细地址信息表';

/*==============================================================*/
/* Table: t_mmdm_busi_user                                      */
/*==============================================================*/
create table t_mmdm_busi_user
(
    id                   bigint unsigned not null comment '主键id',
    business_id          bigint not null default 0 comment '商户ID',
    name                 varchar(50) not null default '' comment '姓名',
    avatar               varchar(200) not null default '' comment '头像',
    phone                varchar(200) not null default '' comment '手机号',
    email                varchar(30) not null default '' comment '邮箱',
    is_main_user         char(1) not null default '0' comment '是否主账号 0-否 1-是',
    description          varchar(200) not null default '' comment '备注',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    flag                 char(1) not null default '0' comment '逻辑删除 0-否 1-是',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_busi_user comment '商户账户表';

/*==============================================================*/
/* Table: t_mmdm_business                                       */
/*==============================================================*/
create table t_mmdm_business
(
    id                   bigint unsigned not null comment '主键id',
    number               varchar(50) not null default '' comment '商户编码',
    name                 varchar(50) not null default '' comment '商户名称',
    business_type        varchar(2) not null default '' comment '商户类别 1-普通商户 2-品牌商户 3-特约商户',
    is_super             char(1) not null default '0' comment '是否平台超级商户 0-否 1-是',
    description          varchar(200) not null default '' comment '备注',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    flag                 char(1) not null default '0' comment '逻辑删除 0-否 1-是',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_business comment '商户表';

/*==============================================================*/
/* Table: t_mmdm_cust_business_bind                             */
/*==============================================================*/
create table t_mmdm_cust_business_bind
(
    id                   bigint not null comment '主键id ',
    customer_id          bigint not null default 0 comment '客户ID',
    business_id          bigint not null default 0 comment '商户ID',
    shop_id              bigint not null default 0 comment '店铺ID',
    prod_id              bigint not null default 0 comment '商品ID',
    number               varchar(50) not null default '' comment '订单号',
    app_id               varchar(100) not null default '' comment '关联小程序appid',
    bind_time            datetime comment '绑定时间 yyyy-MM-dd HH:mm:ss',
    validity_day         varchar(10) not null default '' comment '有效期',
    bind_status          varchar(2) not null default '' comment '绑定状态 1-绑定 2-解除绑定',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_cust_business_bind comment '分账商户绑定表';

/*==============================================================*/
/* Table: t_mmdm_shop                                           */
/*==============================================================*/
create table t_mmdm_shop
(
    id                   bigint unsigned not null comment '主键id',
    business_id          bigint not null default 0 comment '商户ID',
    number               varchar(50) not null default '' comment '店铺编码',
    name                 varchar(50) not null default '' comment '店铺名称',
    logo                 varchar(200) not null default '' comment '店铺logo',
    is_brand             char(1) not null default '0' comment '是否品牌店铺 0-否 1-是',
    contact              varchar(50) not null default '' comment '联系人电话',
    company_name         varchar(100) not null default '' comment '企业名称（工商注册全名）',
    company_address      varchar(200) not null default '' comment '企业地址',
    company_staff        int not null default 0 comment '企业人数',
    business_listence    varchar(200) not null default '' comment '营业执照',
    shop_type            varchar(2) not null default '' comment '店铺类型 0-非自营店铺 1-自营店铺',
    trade_type           varchar(2) not null default '' comment '所属行业',
    service_intro        varchar(200) not null default '' comment '服务说明',
    description          varchar(200) not null default '' comment '备注',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    flag                 char(1) not null default '0' comment '逻辑删除 0-否 1-是',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_shop comment '商户店铺表';

/*==============================================================*/
/* Table: t_mmdm_shop_finance_account                           */
/*==============================================================*/
create table t_mmdm_shop_finance_account
(
    id                   bigint not null comment '主键id ',
    shop_id              bigint not null default 0 comment '关联店铺ID',
    balance_amount       decimal(10,2) not null default 0.00 comment '已结算金额',
    settle_amount        decimal(10,2) not null default 0.00 comment '待结算金额',
    description          varchar(200) not null default '' comment '备注',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_shop_finance_account comment '店铺资金账户表';

/*==============================================================*/
/* Table: t_mmdm_shop_finance_detail                            */
/*==============================================================*/
create table t_mmdm_shop_finance_detail
(
    id                   bigint not null comment '主键id ',
    shop_id              bigint not null default 0 comment '关联店铺ID',
    finance_account_id   bigint not null default 0 comment '资金账户ID',
    number               varchar(50) not null default '' comment '订单号',
    finance_type         varchar(2) not null default '' comment '账务类型 1-订单收入 10-分账押金退回 20-分账押金扣款 30-售后退款 40-平台售后分账回退 50-技术服务费-自然流量',
    capital_flow_type    varchar(2) not null default '' comment '资金流向 1-收入 2-支出',
    enter_time           datetime comment '入账时间',
    wx_order_id          varchar(100) not null default '' comment '微信业务单号',
    amount_detail        decimal(10,2) not null default 0.00 comment '收支金额',
    description          varchar(200) not null default '' comment '备注',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_shop_finance_detail comment '店铺资金明细表';

/*==============================================================*/
/* Table: t_mmdm_shop_non_region                                */
/*==============================================================*/
create table t_mmdm_shop_non_region
(
    id                   bigint not null comment '主键ID ',
    shop_id              bigint not null default 0 comment '关联店铺ID',
    region_adcode        int not null default 0 comment '关联系统地区adcode',
    region_name          varchar(50) not null default '' comment '地区名称',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_shop_non_region comment '不配送地区表';

/*==============================================================*/
/* Table: t_mmdm_shop_role                                      */
/*==============================================================*/
create table t_mmdm_shop_role
(
    id                   bigint unsigned not null comment '主键id（角色ID）',
    business_id          bigint not null default 0 comment '商户ID',
    shop_id              bigint not null default 0 comment '店铺ID',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统部门信息表 s_org';

alter table t_mmdm_shop_role comment '店铺角色表';

/*==============================================================*/
/* Table: t_mmdm_shop_statement_detail                          */
/*==============================================================*/
create table t_mmdm_shop_statement_detail
(
    id                   bigint not null comment '主键id ',
    statement_type       varchar(2) not null default '' comment '对账单类型 1-卖出交易 2-退款交易',
    number               varchar(50) not null default '' comment '订单号',
    order_after_sale_id  bigint not null default 0 comment '售后单号',
    shop_id              bigint not null default 0 comment '关联店铺ID',
    customer_id          bigint not null default 0 comment '关联客户ID ',
    prod_name            varchar(100) not null default '' comment '商品名称',
    order_time           datetime comment '下单时间 yyyy-MM-dd HH:mm:ss',
    pay_trace_id         varchar(100) not null default '' comment '支付流水号',
    settle_type          varchar(2) not null default '1' comment '结算状态 0-未结算 1-已结算',
    pay_type             varchar(2) not null default '' comment '支付渠道 1-微信支付',
    pay_time             datetime comment '支付时间',
    pay_amount           decimal(10,2) not null default 0.00 comment '实付金额',
    refund_amount        decimal(10,2) not null default 0.00 comment '退款金额',
    refund_time          datetime comment '退款时间 yyyy-MM-dd HH:mm:ss',
    description          varchar(200) not null default '' comment '备注',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_shop_statement_detail comment '店铺对账单明细表';

/*==============================================================*/
/* Table: t_mmdm_shop_third                                     */
/*==============================================================*/
create table t_mmdm_shop_third
(
    id                   bigint not null comment '主键id ',
    is_add_receive       char(1) not null default '0' comment '是否添加分账接收方 0-否1-是',
    wx_mch_type          varchar(2) not null default '1' comment '微信商户类型 1-普通商户',
    wx_pay_mch_id        varchar(100) not null default '' comment '微信支付-商户号',
    wx_pay_mch_key       varchar(200) not null default '' comment '微信支付-微信支付商户密钥',
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
    is_bind_busi_favor   char(1) default '0' comment '是否绑定商家券回调 0-否 1-是',
    busi_favor_callback  varchar(1000) default '' comment '绑定商家券回调返回数据',
    wx_business          varchar(200) not null default '' comment '企业微信客服',
    flag                 char(1) not null default '0' comment '逻辑删除 0-否 1-是',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_shop_third comment '店铺第三方平台拆分表';

/*==============================================================*/
/* Table: t_mmdm_shop_third_receive                             */
/*==============================================================*/
create table t_mmdm_shop_third_receive
(
    entry_id             bigint not null comment '主键ID',
    id                   bigint not null default 0 comment '店铺ID',
    wx_pay_app_id        varchar(100) not null default '' comment '微信支付-微信公众号或者小程序等的appid',
    wx_pay_receive_mch_id varchar(100) not null default '' comment '微信支付-分账接收方商户号',
    join_time            datetime comment '添加日期',
    wx_pay_receive_type  varchar(20) not null default 'MERCHANT_ID' comment '分账接收方类型  MERCHANT_ID-商户',
    wx_pay_receive_name  varchar(100) not null default '' comment '分账接收方名称',
    wx_pay_receive_relation_type varchar(100) not null default 'PARTNER' comment '分账接收方关系  PARTNER-合作伙伴',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (entry_id)
);

alter table t_mmdm_shop_third_receive comment '店铺第三方平台分账接收方拆分多行表';

/*==============================================================*/
/* Table: t_mmdm_shop_user                                      */
/*==============================================================*/
create table t_mmdm_shop_user
(
    id                   bigint unsigned not null comment '主键id',
    business_id          bigint not null default 0 comment '商户ID',
    shop_id              bigint not null default 0 comment '店铺ID',
    business_user_id     bigint not null default 0 comment '商户用户ID',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_shop_user comment '商户店铺账号表';

/*==============================================================*/
/* Table: t_mmdm_shop_user_role                                 */
/*==============================================================*/
create table t_mmdm_shop_user_role
(
    id                   bigint unsigned not null comment '主键id',
    shop_user_id         bigint not null default 0 comment '店铺用户ID',
    business_user_id     bigint not null default 0 comment '商户用户ID',
    role_id              bigint unsigned not null default 0 comment '角色ID',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统部门信息表 s_org';

alter table t_mmdm_shop_user_role comment '店铺用户角色关联表';

/*==============================================================*/
/* Table: t_mmdm_shop_visit                                     */
/*==============================================================*/
create table t_mmdm_shop_visit
(
    id                   bigint not null comment '主键id ',
    shop_id              bigint not null default 0 comment '关联店铺ID',
    customer_id          bigint not null default 0 comment '关联客户ID ',
    prod_id              bigint not null default 0 comment '关联商品ID ',
    app_id               varchar(100) not null default '' comment '关联小程序ID',
    visit_shop_page      varchar(200) not null default '' comment '访问店铺页面URL',
    visit_prod_page      varchar(200) not null default '' comment '访问商品页面URL',
    visit_type           char(1) not null default '' comment '访问类型 1-访问店铺 2-访问商品',
    visit_time           datetime comment '访问时间',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_shop_visit comment '店铺访问表';
