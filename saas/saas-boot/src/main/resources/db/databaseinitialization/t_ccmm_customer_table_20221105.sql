drop table if exists t_ccmm_cust_address;

drop table if exists t_ccmm_cust_comment;

drop table if exists t_ccmm_cust_comment_img;

drop table if exists t_ccmm_cust_coupon_restrict;

drop table if exists t_ccmm_cust_shop_attent;

drop table if exists t_ccmm_customer;

/*==============================================================*/
/* Table: t_ccmm_cust_address                                   */
/*==============================================================*/
create table t_ccmm_cust_address
(
    id                   bigint unsigned not null comment '主键id',
    customer_id          bigint not null default 0 comment '客户ID',
    consignee            varchar(50) not null default '' comment '收货人',
    cons_phone           varchar(30) not null default '' comment '收货手机号 ',
    addr_province        varchar(60) not null default '' comment '收货地址 省、直辖市',
    addr_city            varchar(60) not null default '' comment '收货地址 市',
    addr_district        varchar(60) not null default '' comment '收货地址 区、县',
    addr                 varchar(500) not null default '' comment '详细地址 ',
    def                  char(1) not null default '0' comment '是否默认 0-否 1-是',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_ccmm_cust_address comment '客户收货地址表';

/*==============================================================*/
/* Table: t_ccmm_cust_comment                                   */
/*==============================================================*/
create table t_ccmm_cust_comment
(
    id                   bigint not null comment '主键ID ',
    business_id          bigint not null default 0 comment '商户ID',
    customer_id          bigint not null default 0 comment '客户ID ',
    prod_sku_id          bigint not null default 0 comment '关联商品SKUID ',
    prod_combo_id        bigint not null default 0 comment '关联商品套餐ID ',
    order_id             bigint not null default 0 comment '关联订单ID',
    is_anonymous         char(1) not null default '0' comment '是否匿名 0-否 1-是',
    comment_detail       varchar(1000) not null default '' comment '客户评价',
    comment_order        tinyint not null default 0 comment '评价订单 1-5星',
    comment_prod         tinyint not null default 0 comment '评价订单 1-5星',
    comment_server       tinyint not null default 0 comment '评价服务 1-5星',
    comment_logistics    tinyint not null default 0 comment '评价物流 1-5星',
    comment_type         varchar(2) not null default '0' comment '评论类型 0-好评 1-中评 2-差评',
    comment_flag         varchar(2) not null default '0' comment '评论标记 0-正常 1-商家回复 2-客户回复  3-追评',
    is_business_comm     char(1) default '' comment '商家是否回复客户 0-否 1-是',
    comm_time            datetime comment '评价时间 yyyy-MM-dd HH:mm:ss',
    prev_comm_id         bigint not null default 0 comment '上一条评论ID',
    audit_type           varchar(2) not null default '0' comment '审核类型 0-待审核 1-审核不通过 2-审核通过',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_ccmm_cust_comment comment '客户评论表';

/*==============================================================*/
/* Table: t_ccmm_cust_comment_img                               */
/*==============================================================*/
create table t_ccmm_cust_comment_img
(
    id                   bigint not null comment '主键ID ',
    comment_id           bigint not null default 0 comment '评论ID',
    img_url              varchar(200) not null default '' comment '图片地址',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_ccmm_cust_comment_img comment '用户评论图片表';

/*==============================================================*/
/* Table: t_ccmm_cust_coupon_restrict                           */
/*==============================================================*/
create table t_ccmm_cust_coupon_restrict
(
    id                   bigint unsigned not null comment '主键id',
    customer_id          bigint not null default 0 comment '关联客户ID ',
    shop_id              bigint not null default 0 comment '关联店铺ID',
    prod_id              bigint not null default 0 comment '关联商品ID',
    app_id               varchar(100) not null default '' comment '关联小程序appid',
    coupon_id            bigint not null default 0 comment '优惠券ID',
    coupon_type          varchar(2) not null default '' comment '优惠券类型 1-商品立减劵 2-回流优惠券 3-平台优惠券',
    restrict_type        varchar(2) not null default '' comment '优惠券领用状态 1-未生效 5-未使用 10-已过期 15-冻结中 20-用券核销 25-主动核销',
    restrict_user_phone  varchar(40) not null default '' comment '领取人手机号',
    get_request_no       varchar(100) not null default '' comment '领券请求单号',
    restrict_date        datetime comment '领用日期 yyyy-MM-dd HH:mm:ss',
    restrict_use_date    datetime comment '领用使用日期 yyyy-MM-dd HH:mm:ss',
    coupon_code          varchar(50) not null default '' comment '微信优惠券唯一标识',
    is_coupons_use       char(1) not null default '0' comment '是否核销标识 0-否 1-是',
    out_request_no       varchar(100) not null default '' comment '微信优惠券核销凭据号',
    coupons_use_time     datetime comment '核销时间 yyyy-MM-dd HH:mm:ss',
    is_coupons_return    char(1) not null default '0' comment '是否退券标识 0-否 1-是',
    return_request_no    varchar(100) not null default '' comment '退券请求单据号',
    coupons_return_time  datetime comment '退券时间 yyyy-MM-dd HH:mm:ss',
    is_coupons_deactivate char(1) not null default '0' comment '是否失效标识 0-否 1-是',
    deactivate_request_no varchar(100) not null default '' comment '失效请求单据号',
    coupons_deactivate_time datetime comment '失效时间 yyyy-MM-dd HH:mm:ss',
    is_terrace           char(1) not null default '0' comment '是否授权平台 0-否 1-是',
    terrace_wx_pay_mch_id varchar(100) not null default '' comment '平台wxPayMchId',
    terrace_wx_pay_mch_key varchar(100) not null default '' comment '平台v2key',
    ad_aid               varchar(100) not null default '' comment '腾讯广告id/腾讯广告计划id',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统部门信息表 s_org';

alter table t_ccmm_cust_coupon_restrict comment '客户领用优惠券表';

/*==============================================================*/
/* Table: t_ccmm_cust_shop_attent                               */
/*==============================================================*/
create table t_ccmm_cust_shop_attent
(
    id                   bigint not null comment '主键ID ',
    business_id          bigint not null default 0 comment '商户ID',
    shop_id              bigint not null default 0 comment '店铺ID',
    customer_id          bigint not null default 0 comment '客户ID ',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_ccmm_cust_shop_attent comment '客户关注店铺表';

/*==============================================================*/
/* Table: t_ccmm_customer                                       */
/*==============================================================*/
create table t_ccmm_customer
(
    id                   bigint unsigned not null comment '主键id',
    number               varchar(50) not null default '' comment '客户编码',
    name                 varchar(50) not null default '' comment '客户姓名',
    avatar               varchar(200) not null default '' comment '头像',
    phone                varchar(200) not null default '' comment '手机号',
    user_unique          varchar(200) not null default '' comment '平台用户唯一索引',
    sex                  varchar(2) not null default '' comment '性别 1-男性 2-女性 9-未知',
    email                varchar(30) not null default '' comment '邮箱',
    cust_country         varchar(60) not null default '' comment '客户所在国家',
    cust_province        varchar(60) not null default '' comment '客户所在地区省',
    cust_city            varchar(60) not null default '' comment '客户所在地区市',
    cust_district        varchar(60) not null default '' comment '客户所在地区区',
    cust_addr            varchar(500) not null default '' comment '详细地址',
    cust_third_type      varchar(2) not null default '1' comment '第三方平台类型 1-微信',
    app_id               varchar(100) not null default '' comment '关联小程序ID',
    wx_openid            varchar(200) not null default '' comment 'openid',
    wx_unionid           varchar(200) not null default '' comment '微信开放平台',
    wx_qrcode            varchar(200) not null default '' comment '推广二维码',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    description          varchar(200) not null default '' comment '备注',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_ccmm_customer comment '客户表';
