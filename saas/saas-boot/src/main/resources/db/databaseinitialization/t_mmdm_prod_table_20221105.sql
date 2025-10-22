drop table if exists t_mmdm_applet_dec_config;

drop table if exists t_mmdm_applet_dec_config_entry;

drop table if exists t_mmdm_applet_prod_category;

drop table if exists t_mmdm_applet_prod_category_relate;

drop table if exists t_mmdm_feight_template;

drop table if exists t_mmdm_feight_template_detail;

drop table if exists t_mmdm_feight_template_region;

drop table if exists t_mmdm_prod;

drop table if exists t_mmdm_prod_adv_back_account;

drop table if exists t_mmdm_prod_applet_relate;

drop table if exists t_mmdm_prod_attr;

drop table if exists t_mmdm_prod_attr_relate;

drop table if exists t_mmdm_prod_attr_val;

drop table if exists t_mmdm_prod_brand;

drop table if exists t_mmdm_prod_busi_addr;

drop table if exists t_mmdm_prod_category;

drop table if exists t_mmdm_prod_combo;

drop table if exists t_mmdm_prod_combo_attr_relate;

drop table if exists t_mmdm_prod_coupon;

drop table if exists t_mmdm_prod_coupon_relate;

drop table if exists t_mmdm_prod_dec_floor;

drop table if exists t_mmdm_prod_detail;

drop table if exists t_mmdm_prod_feight;

drop table if exists t_mmdm_prod_jump_link;

drop table if exists t_mmdm_prod_left_slide;

drop table if exists t_mmdm_prod_mainimg;

drop table if exists t_mmdm_prod_price;

drop table if exists t_mmdm_prod_sku;

drop table if exists t_mmdm_prod_stock;

drop table if exists t_mmdm_prod_theme;

/*==============================================================*/
/* Table: t_mmdm_applet_dec_config                              */
/*==============================================================*/
create table t_mmdm_applet_dec_config
(
    id                   bigint not null comment '主键ID ',
    name                 varchar(50) not null default '' comment '页面名称',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_applet_dec_config comment '小程序装修配置表';

/*==============================================================*/
/* Table: t_mmdm_applet_dec_config_entry                        */
/*==============================================================*/
create table t_mmdm_applet_dec_config_entry
(
    entry_id             bigint not null comment '主键ID ',
    id                   bigint not null default 0 comment '小程序装修配置ID ',
    applet_config_type   varchar(2) not null default '' comment '分录区域类型 1-轮播区 2-金刚区 3-魔方区 4-广告营销区',
    prod_id              bigint not null default 0 comment '关联商品ID ',
    entry_name           varchar(50) not null default '' comment '名称',
    entry_img            varchar(200) not null default '' comment '图片',
    entry_url            varchar(200) not null default '' comment '跳转链接',
    entry_ord            int not null default 1 comment '顺序',
    description          varchar(200) not null default '' comment '备注',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (entry_id)
);

alter table t_mmdm_applet_dec_config_entry comment '小程序装修配置分录表';

/*==============================================================*/
/* Table: t_mmdm_applet_prod_category                           */
/*==============================================================*/
create table t_mmdm_applet_prod_category
(
    id                   bigint not null comment '主键ID ',
    number               varchar(50) not null default '' comment '分类编码',
    name                 varchar(50) not null default '' comment '分类名称',
    icon                 varchar(200) not null default '' comment '分类图标',
    parent_id            bigint not null default 0 comment '上级分类ID ',
    category_level       int unsigned not null default 1 comment '分类层级 最多2层',
    category_ord         int unsigned not null default 1 comment '分类排序',
    description          varchar(200) not null default '' comment '备注',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_applet_prod_category comment '小程序商品类目表';

/*==============================================================*/
/* Table: t_mmdm_applet_prod_category_relate                    */
/*==============================================================*/
create table t_mmdm_applet_prod_category_relate
(
    id                   bigint not null comment '主键ID ',
    applet_prod_categoty_id bigint not null default 0 comment '关联商品小程序类目ID ',
    prod_id              bigint not null default 0 comment '关联商品ID ',
    prod_ord             int not null default 1 comment '商品在小程序类目中的排序',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_applet_prod_category_relate comment '小程序商品类目关联商品表';

/*==============================================================*/
/* Table: t_mmdm_feight_template                                */
/*==============================================================*/
create table t_mmdm_feight_template
(
    id                   bigint not null comment '主键ID ',
    business_id          bigint not null default 0 comment '商户ID',
    name                 varchar(50) not null default '' comment '模板名称',
    piecework_type       varchar(2) not null default '1' comment '计件方式 1-按件数',
    flag                 char(1) not null default '0' comment '逻辑删除 1（true）已删除， 0（false）未删除',
    description          varchar(200) not null default '' comment '备注',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_feight_template comment '运费模板表';

/*==============================================================*/
/* Table: t_mmdm_feight_template_detail                         */
/*==============================================================*/
create table t_mmdm_feight_template_detail
(
    entry_id             bigint not null comment '主键ID ',
    id                   bigint not null default 0 comment '运费模板ID',
    first_piece          int not null default 0 comment '首件(个)',
    first_amount         decimal(7,2) not null default 0 comment '首件金额(元)',
    keep_piece           int not null default 0 comment '续件(个)',
    keep_amount          decimal(7,2) not null default 0 comment '续件金额(元)',
    is_pinkage           char(1) not null default '0' comment '是否指定包邮条件 0-否 1-是',
    pinkage_num          int not null default 0 comment '满包邮数量',
    pinkage_type         varchar(2) not null default '1' comment '包邮条件类型 1-元 2-件',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (entry_id)
);

alter table t_mmdm_feight_template_detail comment '运费模板详情拆分多行表';

/*==============================================================*/
/* Table: t_mmdm_feight_template_region                         */
/*==============================================================*/
create table t_mmdm_feight_template_region
(
    sub_entry_id         bigint not null comment '主键ID ',
    id                   bigint not null default 0 comment '运费模板ID',
    entry_id             bigint not null default 0 comment '运费模板详情ID',
    region_adcode        int not null default 0 comment '关联系统地区adcode',
    region_name          varchar(50) not null default '' comment '地区名称',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (sub_entry_id)
);

alter table t_mmdm_feight_template_region comment '运费模板可配送地区详情拆分多行表';

/*==============================================================*/
/* Table: t_mmdm_prod                                           */
/*==============================================================*/
create table t_mmdm_prod
(
    id                   bigint not null comment '主键id',
    business_id          bigint not null default 0 comment '关联商户ID',
    shop_id              bigint not null default 0 comment '关联店铺ID',
    category_id          bigint not null default 0 comment '类目ID',
    brand_id             bigint not null default 0 comment '品牌ID',
    number               varchar(50) not null default '' comment '商品编码',
    name                 varchar(200) not null default '' comment '商品名称',
    tips                 varchar(100) not null default '' comment '商品内部备注',
    prod_ord             int unsigned not null default 0 comment '商品排序',
    sales                int not null default 0 comment '展示销量',
    safeguard            char(1) not null default '0' comment '隐私协议 0-不展示 1-展示',
    prod_status          varchar(2) not null default '1' comment '商品状态 0-暂存 1-上架中 2-已下架',
    join_shop_mainpage   varchar(2) not null default '0' comment '加入店铺首页 0-关闭 1-已加入',
    description          varchar(200) not null default '' comment '备注',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    flag                 char(1) not null default '0' comment '逻辑删除 0-否 1-是',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_prod comment '商品基本信息表';

/*==============================================================*/
/* Table: t_mmdm_prod_adv_back_account                          */
/*==============================================================*/
create table t_mmdm_prod_adv_back_account
(
    entry_id             bigint not null comment '多行表主键id ',
    id                   bigint comment '商品id ',
    adv_account_id       bigint not null default 0 comment '关联广告账户ID',
    back_ratio           decimal(10,2) not null default 0.00 comment '回传比率',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (entry_id)
);

alter table t_mmdm_prod_adv_back_account comment '商品装修落地页广告回传信息拆分多行表';

/*==============================================================*/
/* Table: t_mmdm_prod_applet_relate                             */
/*==============================================================*/
create table t_mmdm_prod_applet_relate
(
    id                   bigint not null comment '主键（商品ID）',
    applet_config_id     bigint not null default 0 comment '关联小程序配置表ID',
    applet_app_id        varchar(200) not null default '' comment '小程序appID',
    applet_original_id   varchar(200) not null default '' comment '小程序原始ID',
    applet_page_url      varchar(200) not null default '' comment '小程序落地页链接',
    applet_app_qrcode_url varchar(200) not null default '' comment '小程序二维码图片',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_prod_applet_relate comment '商品关联小程序拆分表';

/*==============================================================*/
/* Table: t_mmdm_prod_attr                                      */
/*==============================================================*/
create table t_mmdm_prod_attr
(
    id                   bigint not null comment '主键ID ',
    number               varchar(50) not null default '' comment '属性编码',
    name                 varchar(50) not null default '' comment '属性名称',
    attr_img             varchar(200) not null default '' comment '属性图标',
    attr_ord             int unsigned not null default 1 comment '属性排序',
    attr_type            varchar(2) not null default '' comment '属性类型 字典项',
    attr_val_type        varchar(2) not null default '1' comment '属性值类型 1-文字',
    is_search            char(1) not null default '0' comment '可查询 0-否 1-是',
    description          varchar(200) not null default '' comment '备注',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_prod_attr comment '商品属性表（本期不用）';

/*==============================================================*/
/* Table: t_mmdm_prod_attr_relate                               */
/*==============================================================*/
create table t_mmdm_prod_attr_relate
(
    id                   bigint not null comment '主键ID ',
    prod_id              bigint not null default 0 comment '商品ID',
    attr_id              bigint not null default 0 comment '商品属性ID',
    attr_name            varchar(200) not null default '' comment '商品属性名',
    attr_values          varchar(1000) not null default '' comment '规格属性值集，多个值用 , 分割',
    is_valuation         char(1) not null default '1' comment '是否参与计价 0-否 1-是',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_prod_attr_relate comment '商品销售规格属性关联表（本期不用）';

/*==============================================================*/
/* Table: t_mmdm_prod_attr_val                                  */
/*==============================================================*/
create table t_mmdm_prod_attr_val
(
    id                   bigint not null comment '主键ID ',
    attr_id              bigint not null comment '属性ID',
    attr_val_type        varchar(2) not null default '1' comment '属性值类型 1-文字',
    attr_val             varchar(200) comment '属性值',
    attr_val_ord         int unsigned not null default 1 comment '属性值排序',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_prod_attr_val comment '商品属性值表（本期不用）';

/*==============================================================*/
/* Table: t_mmdm_prod_brand                                     */
/*==============================================================*/
create table t_mmdm_prod_brand
(
    id                   bigint not null comment '主键ID ',
    number               varchar(50) not null default '' comment '品牌编码',
    name                 varchar(50) not null default '' comment '品牌名称',
    brand_logo           varchar(200) not null default '' comment '品牌logo',
    brand_ord            int unsigned not null default 1 comment '品牌排序',
    description          varchar(200) not null default '' comment '备注',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_prod_brand comment '商品品牌表（本期不用）';

/*==============================================================*/
/* Table: t_mmdm_prod_busi_addr                                 */
/*==============================================================*/
create table t_mmdm_prod_busi_addr
(
    id                   bigint not null comment '主键id ',
    prod_id              bigint not null default 0 comment '商品ID',
    busi_addr_id         bigint not null default 0 comment '关联地址ID ',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_prod_busi_addr comment '商品与商家详细地址管理表';

/*==============================================================*/
/* Table: t_mmdm_prod_category                                  */
/*==============================================================*/
create table t_mmdm_prod_category
(
    id                   bigint not null comment '主键ID ',
    number               varchar(50) not null default '' comment '分类编码',
    name                 varchar(50) not null default '' comment '分类名称',
    category_icon        varchar(200) not null default '' comment '分类图标',
    parent_id            bigint not null default 0 comment '上级分类ID ',
    category_level       int unsigned not null default 1 comment '分类层级 最多2层',
    category_ord         int unsigned not null default 1 comment '分类排序',
    description          varchar(200) not null default '' comment '备注',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_prod_category comment '商品分类表';

/*==============================================================*/
/* Table: t_mmdm_prod_combo                                     */
/*==============================================================*/
create table t_mmdm_prod_combo
(
    id                   bigint not null comment '主键ID ',
    prod_id              bigint not null default 0 comment '商品ID',
    combo_title          varchar(200) not null default '' comment '商品销售套餐标题',
    sell_point           varchar(200) not null default '' comment '商品卖点',
    combo_price          decimal(10,2) not null default 0.00 comment '商品销售套餐金额',
    combo_standard_price decimal(10,2) not null default 0.00 comment '商品套餐划线价',
    combo_cost_price     decimal(10,2) not null default 0.00 comment '商品成本价',
    combo_ord            int unsigned not null default 1 comment '套餐排序',
    flag                 char(1) not null default '0' comment '逻辑删除 0-否 1-是',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_prod_combo comment '商品销售套餐表';

/*==============================================================*/
/* Table: t_mmdm_prod_combo_attr_relate                         */
/*==============================================================*/
create table t_mmdm_prod_combo_attr_relate
(
    id                   bigint not null comment '主键ID ',
    prod_id              bigint not null default 0 comment '商品ID',
    prod_combo_id        bigint not null default 0 comment '商品套餐ID',
    attr_name            varchar(200) not null default '' comment '商品套餐规格属性名',
    attr_values          varchar(1000) not null default '' comment '规格属性值集，多个值用 , 分割',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_prod_combo_attr_relate comment '商品销售套餐规格属性关联表';

/*==============================================================*/
/* Table: t_mmdm_prod_coupon                                    */
/*==============================================================*/
create table t_mmdm_prod_coupon
(
    entry_id             bigint not null default 0 comment '主键id',
    id                   bigint not null default 0 comment '商品ID',
    shop_coupon_type     varchar(2) not null default '' comment '店铺优惠券类型 1-商品立减劵 2-回流优惠券',
    is_open_coupon       char(1) not null default '0' comment '是否设置优惠券 0-否 1-是',
    charge_off           char(1) not null default '0' comment '主动核销 0-不主动 1-主动',
    coupon_open_img      varchar(200) not null default '' comment '领劵弹窗图片',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (entry_id)
);

alter table t_mmdm_prod_coupon comment '商品优惠劵拆分表';

/*==============================================================*/
/* Table: t_mmdm_prod_coupon_relate                             */
/*==============================================================*/
create table t_mmdm_prod_coupon_relate
(
    sub_entry_id         bigint not null default 0 comment '主键id',
    entry_id             bigint not null default 0 comment '商品优惠券id',
    id                   bigint not null default 0 comment '商品ID ',
    coupon_id            bigint not null default 0 comment '优惠券ID ',
    coupon_type          varchar(2) not null default '' comment '优惠券类型 1-商品立减劵 2-回流优惠券 3-平台优惠券',
    is_expired           char(1) not null default '0' comment '优惠券是否过期 0-否 1-是',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (sub_entry_id)
);

alter table t_mmdm_prod_coupon_relate comment '商品与优惠券绑定表';

/*==============================================================*/
/* Table: t_mmdm_prod_dec_floor                                 */
/*==============================================================*/
create table t_mmdm_prod_dec_floor
(
    id                   bigint not null comment '主键id（商品ID）',
    page_title           varchar(10) not null default '' comment '页面名称',
    buy_btn_title        varchar(10) not null default '' comment '立即购买按钮文字',
    float_btn_title      varchar(10) not null default '' comment '悬浮按钮文字',
    countdown_offers     char(1) not null default '0' comment '优惠倒计时 0-不开启 1-开启',
    countdown_title      varchar(50) not null default '' comment '优惠倒计时文字',
    countdown_minute     int not null default 10 comment '优惠倒计时分钟数',
    success_pay_back     char(1) not null default '0' comment '支付成功回传 0-不回传 1-回传',
    adv_back_type        varchar(2) not null default '' comment '广告回传类型 1-全部回传 2-按投放账号回传',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_prod_dec_floor comment '商品装修落地页拆分表';

/*==============================================================*/
/* Table: t_mmdm_prod_detail                                    */
/*==============================================================*/
create table t_mmdm_prod_detail
(
    entry_id             bigint not null comment '主键id ',
    id                   bigint comment '商品ID',
    detail_img           varchar(200) not null default '' comment '商品详情图',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (entry_id)
);

alter table t_mmdm_prod_detail comment '商品详情拆分多行表';

/*==============================================================*/
/* Table: t_mmdm_prod_feight                                    */
/*==============================================================*/
create table t_mmdm_prod_feight
(
    id                   bigint not null comment '主键id（商品ID）',
    feight_type          varchar(2) not null default '1' comment '运费类型 1-统一运维 2-运费模板',
    feight_amount        decimal(7,2) not null default 0 comment '运费金额（元）',
    feight_template_id   bigint not null default 0 comment '运费模板ID',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_prod_feight comment '商品运费信息表';

/*==============================================================*/
/* Table: t_mmdm_prod_jump_link                                 */
/*==============================================================*/
create table t_mmdm_prod_jump_link
(
    entry_id             bigint not null comment '多行表主键id ',
    id                   bigint comment '商品ID',
    jump_link_type       varchar(2) not null default '' comment '跳转链接类型 1-左滑跳转 2-主页跳转',
    jump_link            varchar(200) not null default '' comment '跳转链接',
    jump_link_order      int not null default 1 comment '链接排序',
    is_quick_order_link  varchar(1) not null default '0' comment '是否快速下单链接 0-否 1-是',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (entry_id)
);

alter table t_mmdm_prod_jump_link comment '商品跳转链接拆分多行表';

/*==============================================================*/
/* Table: t_mmdm_prod_left_slide                                */
/*==============================================================*/
create table t_mmdm_prod_left_slide
(
    id                   bigint not null comment '主键id（商品ID）',
    switch_text          text comment '左滑切换弹框文本',
    switch_jump          char(1) not null default '0' comment '左滑跳转 0-不跳转 1-跳转',
    index_jump           char(1) not null default '0' comment '主页跳转 0-不跳转 1-跳转',
    switch_right         char(1) not null default '0' comment '右上角返回图标 0-不开启- 1-开启',
    joinpage_jump        char(1) not null default '0' comment '聚合页 0-不开启- 1-开启',
    joinpage_open_url    varchar(200) not null default '' comment '聚合页弹窗图片链接',
    joinpage_banner_url  varchar(200) not null default '' comment '聚合页banner图片链接',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_prod_left_slide comment '商品左滑设置拆分表';

/*==============================================================*/
/* Table: t_mmdm_prod_mainimg                                   */
/*==============================================================*/
create table t_mmdm_prod_mainimg
(
    entry_id             bigint not null comment '多行表主键id ',
    id                   bigint comment '商品ID',
    main_img             varchar(200) not null default '' comment '商品详情页轮播图',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (entry_id)
);

alter table t_mmdm_prod_mainimg comment '商品详情页轮播图拆分多行表';

/*==============================================================*/
/* Table: t_mmdm_prod_price                                     */
/*==============================================================*/
create table t_mmdm_prod_price
(
    id                   bigint not null comment '主键id（商品ID）',
    prod_sku_id          bigint not null default 0 comment '关联商品SKUID（价格最低的SKU）',
    sku_min_price        decimal(10,2) not null default 0.00 comment '商品销售金额（最低）',
    sku_min_standard_price decimal(10,2) not null default 0.00 comment '商品划线价（最低）',
    subsidy_price        decimal(10,2) not null default 0.00 comment '官方补贴金额',
    is_fail_pay_hint     char(1) not null default '0' comment '是否开启支付失败弹框 0-否 1-是',
    discount_price       decimal(10,2) not null default 0.00 comment '支付优惠金额',
    prod_combo_id        bigint not null default 0 comment '关联商品套餐ID（价格最低的套餐）',
    combo_min_price      decimal(10,2) not null default 0.00 comment '套餐商品销售金额（最低）',
    combo_min_standard_price decimal(10,2) not null default 0.00 comment '套餐商品划线价（最低）',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_prod_price comment '商品价格信息拆分表';

/*==============================================================*/
/* Table: t_mmdm_prod_sku                                       */
/*==============================================================*/
create table t_mmdm_prod_sku
(
    id                   bigint not null comment '主键ID ',
    prod_id              bigint not null default 0 comment '商品ID',
    prod_stock_id        bigint not null default 0 comment '库存ID ',
    sku_title            varchar(200) not null default '' comment '商品销售标题',
    sell_point           varchar(200) not null default '' comment '商品卖点',
    sku_conn_attr_ids    varchar(500) not null default '' comment '关联规格属性ID集  逗号分隔',
    sku_conn_attr_names  varchar(500) not null default '' comment '关联规格属性名集  逗号分隔',
    sku_conn_attr_values varchar(1000) not null default '' comment '关联规格属性值集 逗号分隔',
    sku_price            decimal(10,2) not null default 0.00 comment '商品销售金额',
    sku_standard_price   decimal(10,2) not null default 0.00 comment '商品划线价',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_prod_sku comment '商品销售属性表（本期不用）';

/*==============================================================*/
/* Table: t_mmdm_prod_stock                                     */
/*==============================================================*/
create table t_mmdm_prod_stock
(
    id                   bigint not null comment '主键ID ',
    prod_id              bigint not null default 0 comment '商品ID',
    stock                int not null default 0 comment '库存',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_prod_stock comment '商品库存表（本期不用）';

/*==============================================================*/
/* Table: t_mmdm_prod_theme                                     */
/*==============================================================*/
create table t_mmdm_prod_theme
(
    id                   bigint not null comment '主键id（商品ID）',
    show_img             varchar(200) not null default '' comment '商品展示图（缩略图）',
    main_num             int not null default 10 comment '商品详情页轮播图数量上限',
    main_ratio_type      varchar(2) not null default '1' comment '商品详情页轮播图比例 1-1:1 2-3:4',
    detail_num           int not null default 50 comment '商品详情图数量上限',
    prod_theme           varchar(2) not null default '' comment '跑马灯样式  1-淡入淡出浮层跑马灯 2-主图下方跑马灯',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_prod_theme comment '商品样式拆分表';
