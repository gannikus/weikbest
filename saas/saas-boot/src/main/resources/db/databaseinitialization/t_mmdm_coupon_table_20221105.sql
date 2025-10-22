drop table if exists t_mmdm_coupon;

drop table if exists t_mmdm_coupon_customer_entry;

drop table if exists t_mmdm_coupon_prod_entry;

drop table if exists t_mmdm_coupon_scene;

drop table if exists t_mmdm_coupon_scene_config;

/*==============================================================*/
/* Table: t_mmdm_coupon                                         */
/*==============================================================*/
create table t_mmdm_coupon
(
    id                   bigint not null comment '主键ID ',
    shop_id              bigint not null default 0 comment '关联店铺ID',
    name                 varchar(200) not null default '' comment '优惠券名称',
    tips                 varchar(200) not null default '' comment '优惠券内部备注',
    coupon_type          varchar(2) not null default '' comment '优惠券类型 1-立减券 2-回流劵 3-平台券',
    coupon_status        varchar(2) not null default '1' comment '优惠券状态 1-待发布 5-活动未开始 10-进行中 15-已结束 20-已取消',
    event_start_time     datetime comment '活动开始时间',
    event_end_time       datetime comment '活动结束时间',
    coupon_num           int not null default 0 comment '优惠券发行总量',
    coupon_prod_type     varchar(2) not null default '' comment '适用商品类型 1-全部商品 2-指定商品',
    coupon_use_type      varchar(2) not null default '' comment '使用门槛 0-无使用门槛 1-订单满多少金额可用',
    coupon_use_price     decimal(10,2) not null default 0.00 comment '使用门槛价格（元）',
    app_id               varchar(100) not null default '' comment '核销小程序ID',
    discount_amount      decimal(10,2) not null default 0.00 comment '优惠金额（元）',
    get_start_time       datetime comment '领劵开始时间',
    get_end_time         datetime comment '领劵结束时间',
    restrict_count       int not null default 0 comment '每人限领次数',
    restrict_user_type   varchar(2) not null default '' comment '领取人限制类型 1-不限制，所有人可领取 2-指定客户领取',
    is_open              char(1) not null default '0' comment '是否公开 0-否 1-是',
    use_start_time       datetime comment '用劵开始时间',
    use_end_time         datetime comment '用劵结束时间',
    enable_type          varchar(2) not null default '' comment '生效类型 1-领券后立即生效 2-延迟生效',
    delay_enable_day     int not null default 0 comment '领券（天）后生效',
    validity_day         int not null default 0 comment '有效期（天）',
    coupon_use_url       varchar(200) not null default '' comment '用券页面链接',
    coupon_theme_type    varchar(2) not null default '1' comment '优惠券样式 1-默认图片 2-上传图片',
    coupon_image_url     varchar(200) not null default '' comment '卡包详情图片链接',
    coupon_image_ossurl  varchar(200) not null default '' comment '卡包详情图片链接(oss链接)',
    merchat_logo_url     varchar(200) not null default '' comment '卡包图标链接',
    merchat_logo_ossurl  varchar(200) not null default '' comment '卡包图标链接(oss链接)',
    out_request_no       varchar(100) not null default '' comment '商户创建批次凭据号',
    bind_mch_id          varchar(100) not null default '' comment '绑定商户号ID',
    bind_wxgzh_app_id    varchar(100) not null default '' comment '绑定微信公众号ID',
    stock_id             varchar(100) not null default '' comment '微信优惠券批次号',
    create_time          varchar(100) not null default '' comment '微信优惠券创建时间 YYYY-MM-DDTHH:mm:ss+TIMEZONE',
    bind_partner_merchant_id varchar(100) not null default '' comment '绑定微信优惠券合作伙伴商户号ID',
    description          varchar(200) not null default '' comment '备注',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    flag                 char(1) not null default '0' comment '逻辑删除 0-否 1-是',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_coupon comment '优惠券表';

/*==============================================================*/
/* Table: t_mmdm_coupon_customer_entry                          */
/*==============================================================*/
create table t_mmdm_coupon_customer_entry
(
    entry_id             bigint not null comment '主键id ',
    id                   bigint not null default 0 comment '关联优惠券ID ',
    coupon_type          varchar(2) not null default '' comment '优惠券类型 1-立减券 2-回流劵 3-平台券',
    customer_id          bigint not null default 0 comment '关联客户ID ',
    restrict_user_phone  varchar(40) not null default '' comment '领取人手机号',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (entry_id)
);

alter table t_mmdm_coupon_customer_entry comment '优惠券与适用客户拆分表';

/*==============================================================*/
/* Table: t_mmdm_coupon_prod_entry                              */
/*==============================================================*/
create table t_mmdm_coupon_prod_entry
(
    entry_id             bigint not null comment '主键id ',
    id                   bigint not null default 0 comment '关联优惠券ID ',
    prod_id              bigint not null default 0 comment '关联商品ID ',
    coupon_type          varchar(2) not null default '' comment '优惠券类型 1-立减券 2-回流劵 3-平台券',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (entry_id)
);

alter table t_mmdm_coupon_prod_entry comment '优惠券与适用商品拆分表';

/*==============================================================*/
/* Table: t_mmdm_coupon_scene                                   */
/*==============================================================*/
create table t_mmdm_coupon_scene
(
    entry_id             bigint unsigned not null comment '主键id',
    id                   bigint not null default 0 comment '关联优惠券使用场景配置ID',
    coupon_id            bigint not null default 0 comment '关联优惠券ID',
    coupon_type          varchar(2) not null default '3' comment '优惠券类型 1-立减券 2-回流劵 3-平台券',
    coupon_scene_type    varchar(2) not null default '' comment '场景类型 1-广告进入绑定回流优惠券领取 2-新用户进入小程序点击领取',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (entry_id)
);

alter table t_mmdm_coupon_scene comment '优惠券使用场景表';

/*==============================================================*/
/* Table: t_mmdm_coupon_scene_config                            */
/*==============================================================*/
create table t_mmdm_coupon_scene_config
(
    id                   bigint unsigned not null comment '主键id',
    coupon_scene_type    varchar(2) not null default '' comment '场景类型 1-广告进入绑定回流优惠券领取 2-新用户进入小程序点击领取',
    platform_coupon_receive_open_url varchar(200) not null default '' comment '平台优惠券领取弹窗',
    platform_coupon_receive_banner_url varchar(200) not null default '' comment '平台券领取banner图',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_coupon_scene_config comment '优惠券使用场景配置表';
