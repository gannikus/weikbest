drop table if exists t_mmdm_order_after_sale;

drop table if exists t_mmdm_order_after_sale_consult_record;

drop table if exists t_mmdm_order_after_sale_img;

drop table if exists t_mmdm_order_after_sale_img_his;

drop table if exists t_mmdm_order_after_sale_logistics_img;

drop table if exists t_mmdm_order_after_sale_logistics_img_his;

drop table if exists t_mmdm_order_batch_deliver;

drop table if exists t_mmdm_order_batch_deliver_record;

drop table if exists t_mmdm_order_complaint;

drop table if exists t_mmdm_order_complaint_img;

drop table if exists t_mmdm_order_complaint_img_his;

drop table if exists t_mmdm_order_complaint_record;

drop table if exists t_mmdm_order_cust_info;

drop table if exists t_mmdm_order_info;

drop table if exists t_mmdm_order_logistics;

drop table if exists t_mmdm_order_logistics_img;

drop table if exists t_mmdm_order_pay_record;

drop table if exists t_mmdm_order_prod_info;

drop table if exists t_mmdm_order_rec_addr;

drop table if exists t_mmdm_order_receive_record;

drop table if exists t_mmdm_order_source_scale;

drop table if exists t_mmdm_order_stat_record;

/*==============================================================*/
/* Table: t_mmdm_order_after_sale                               */
/*==============================================================*/
create table t_mmdm_order_after_sale
(
    id                   bigint unsigned not null comment '主键id',
    number               varchar(50) not null default '' comment '售后编号 规则为年月日时分秒豪秒',
    order_id             bigint not null default 0 comment '订单ID',
    app_id               varchar(100) not null default '' comment '关联小程序ID',
    order_applet_type    varchar(2) not null default '1' comment '下单小程序类型来源 1-微信小程序',
    customer_id          bigint not null default 0 comment '客户ID ',
    take_delivery_type   varchar(2) not null default '' comment '收货状态 0-未收货 1-已收货',
    after_sale_num       int not null default 1 comment '订单第几次售后',
    apply_time           datetime comment '申请时间 yyyy-MM-dd HH:mm:ss',
    apply_type           varchar(2) not null default '' comment '申请类型 1-仅退款 2-退货退款 3-换货',
    apply_reason         varchar(2) not null default '' comment '申请原因 1-少发/漏发 2-质量问题 3-货物与描述不符 4-未按约定时间发货 5-发票问题 6-卖家发错货 7-假冒品牌',
    apply_source_type    varchar(2) not null default '1' comment '申请来源 1-客户申请 2-商家代申请',
    goods_num            int not null default 1 comment '货物件数',
    apply_amount         decimal(10,2) not null default 0.00 comment '申请金额',
    customer_phone       varchar(50) not null default '' comment '联系电话',
    question_detail      varchar(200) not null default '' comment '问题描述',
    refund_trend         varchar(2) not null default '' comment '退款去向 1-原路返回',
    is_fast_sale         char(1) not null default '0' comment '是否极速退款售后单 0-否 1-是',
    after_sale_key       varchar(2) not null default '' comment '售后关键节点 0-售后关闭 1-售后成功 10-仅退款待处理 11-仅退款处理中 20-退货退款待处理 21-退货退款待商家收货  22-退货退款处理中 30-换货待处理 31-换货待商家收货 32-换货处理中 7-待买家退货 8-客户处理中 9-平台处理中 ',
    after_sale_status    varchar(4) not null default '' comment '售后状态 0-售后关闭 1-售后成功 2-客户申请售后，待商家处理 3-客户未处理，平台自动关闭售后 4-客户撤销申请 5-客户修改申请信息，待商家处理 6-客户已寄回商品，待商家收货 8-极速退款中 9-极速退款成功 10-商家不同意申请，待客户处理 11-商家未处理，平台同意申请 101-商家同意仅退款  201-商家同意退货退款，待客户寄回商品 203-商家已收货，待商家确认签收 204-商家已签收，待商家确认 205-商家已同意退款 206-商家拒绝退款，待客户处理 301-商家同意换货，待客户寄回商品 303-商家已收货，待商家确认 304-商家已重新发货，待客户确认 305-客户确认收货 306-商家拒绝换货，待客户处理 307-客户未收到货，待重新发起售后',
    reject_reason        varchar(2) not null default '' comment '拒绝原因 9-其他',
    reject_detail        varchar(200) not null default '' comment '拒绝说明',
    back_address         varchar(200) not null default '' comment '退货地址',
    back_logistics_company varchar(50) not null default '' comment '客户寄回物流公司 字典表CODE',
    back_courier_number  varchar(50) not null default '' comment '客户寄回快递单号',
    back_courier_phone   varchar(50) not null default '' comment '客户寄回手机号',
    back_logistics_time  datetime comment '客户寄回发货时间',
    back_content         varchar(3000) not null default '' comment '客户寄回第三方接口返回的物流信息',
    send_type            varchar(2) not null default '' comment '发货状态 0-未发货 1-已发货',
    send_logistics_company varchar(50) not null default '' comment '商家再次发送物流公司 字典表CODE',
    send_courier_number  varchar(50) not null default '' comment '商家再次发送快递单号',
    send_courier_phone   varchar(50) not null default '' comment '商家再次发送手机号',
    send_logistics_time  datetime comment '商家再次发送发货时间',
    send_content         varchar(3000) not null default '' comment '商家再次发送第三方接口返回的物流信息',
    finish_time          datetime comment '完成时间 yyyy-MM-dd HH:mm:ss',
    description          varchar(200) not null default '' comment '备注',
    flag                 char(1) not null default '0' comment '逻辑删除 0-否 1-是',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_order_after_sale comment '订单售后表';

/*==============================================================*/
/* Table: t_mmdm_order_after_sale_consult_record                */
/*==============================================================*/
create table t_mmdm_order_after_sale_consult_record
(
    history_id           bigint unsigned not null comment '主键id',
    id                   bigint not null default 0 comment '订单售后ID',
    number               varchar(50) not null default '' comment '售后编号 规则为年月日时分秒豪秒',
    order_id             bigint not null default 0 comment '订单ID',
    app_id               varchar(100) not null default '' comment '关联小程序ID',
    order_applet_type    varchar(2) not null default '1' comment '下单小程序类型来源 1-微信小程序',
    customer_id          varchar(200) not null default '' comment '客户ID ',
    history_ord          int not null default 1 comment '协商排序',
    take_delivery_type   varchar(2) not null default '' comment '收货状态 0-未收货 1-已收货',
    after_sale_num       int not null default 1 comment '订单第几次售后',
    apply_time           datetime comment '申请时间 yyyy-MM-dd HH:mm:ss',
    apply_type           varchar(2) not null default '' comment '申请类型 1-仅退款 2-退货退款 3-换货',
    apply_reason         varchar(2) not null default '' comment '申请原因 1-少发/漏发 2-质量问题 3-货物与描述不符 4-未按约定时间发货 5-发票问题 6-卖家发错货 7-假冒品牌',
    apply_source_type    varchar(2) not null default '1' comment '申请来源 1-客户申请 2-商家代申请',
    goods_num            int not null default 1 comment '货物件数',
    apply_amount         decimal(10,2) not null default 0.00 comment '申请金额',
    customer_phone       varchar(50) not null default '' comment '联系电话',
    question_detail      varchar(200) not null default '' comment '问题描述',
    refund_trend         varchar(2) not null default '' comment '退款去向 1-原路返回',
    is_fast_sale         char(1) not null default '0' comment '是否极速退款售后单 0-否 1-是',
    after_sale_key       varchar(2) not null default '' comment '售后关键节点 0-售后关闭 1-售后成功 10-仅退款待处理 11-仅退款处理中 20-退货退款待处理 21-退货退款待商家收货  22-退货退款处理中 30-换货待处理 31-换货待商家收货 32-换货处理中 7-待买家退货 9-平台处理中 ',
    after_sale_status    varchar(4) not null default '' comment '售后状态 0-售后关闭 1-售后成功 2-客户申请售后，待商家处理 3-客户未处理，平台自动关闭售后 4-客户撤销申请 5-客户修改申请信息，待商家处理 6-客户已寄回商品，待商家收货 8-极速退款中 9-极速退款成功 10-商家不同意申请，待客户处理 11-商家未处理，平台同意申请 101-商家同意仅退款  201-商家同意退货退款，待客户寄回商品 203-商家已收货，待商家确认签收 204-商家已签收，待商家确认 205-商家已同意退款 206-商家拒绝退款，待客户处理 301-商家同意换货，待客户寄回商品 303-商家已收货，待商家确认 304-商家已重新发货，待客户确认 305-客户确认收货 306-商家拒绝换货，待客户处理 307-客户未收到货，待重新发起售后',
    reject_reason        varchar(2) not null default '' comment '拒绝原因 9-其他',
    reject_detail        varchar(200) not null default '' comment '拒绝说明',
    back_address         varchar(200) not null default '' comment '退货地址',
    back_logistics_company varchar(50) not null default '' comment '客户寄回物流公司 字典表CODE',
    back_courier_number  varchar(50) not null default '' comment '客户寄回快递单号',
    back_courier_phone   varchar(50) not null default '' comment '客户寄回手机号',
    back_logistics_time  datetime comment '客户寄回发货时间',
    send_type            varchar(2) not null default '' comment '发货状态 0-未发货 1-已发货',
    send_logistics_company varchar(50) not null default '' comment '商家再次发送物流公司 字典表CODE',
    send_courier_number  varchar(50) not null default '' comment '商家再次发送快递单号',
    send_courier_phone   varchar(50) not null default '' comment '商家再次发送手机号',
    send_logistics_time  datetime comment '商家再次发送发货时间',
    finish_time          datetime comment '完成时间 yyyy-MM-dd HH:mm:ss',
    change_type          varchar(2) not null default '' comment '变更类型 1-客户申请 2-状态变更 3-客户物流信息变更 4-商家物流信息变更',
    changer_user_type    varchar(2) not null default '' comment '变更人类型 0-平台用户 1-商家端用户 2-App端用户',
    changer_user_id      bigint not null default 0 comment '变更人ID',
    change_time          datetime comment '变更时间 yyyy-MM-dd HH:mm:ss',
    description          varchar(200) not null default '' comment '备注',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (history_id)
);

alter table t_mmdm_order_after_sale_consult_record comment '订单售后协商历史记录表';

/*==============================================================*/
/* Table: t_mmdm_order_after_sale_img                           */
/*==============================================================*/
create table t_mmdm_order_after_sale_img
(
    entry_id             bigint unsigned not null comment '主键id',
    id                   bigint not null default 0 comment '订单售后ID',
    courier_img_path     varchar(400) not null default '' comment '售后图片链接',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (entry_id)
);

alter table t_mmdm_order_after_sale_img comment '订单售后图片拆分表';

/*==============================================================*/
/* Table: t_mmdm_order_after_sale_img_his                       */
/*==============================================================*/
create table t_mmdm_order_after_sale_img_his
(
    entry_id             bigint unsigned not null comment '主键id',
    history_id           bigint unsigned not null comment '售后协商历史ID',
    id                   bigint not null default 0 comment '订单售后ID',
    courier_img_path     varchar(400) not null default '' comment '售后图片链接',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (entry_id)
);

alter table t_mmdm_order_after_sale_img_his comment '订单售后图片拆分历史表';

/*==============================================================*/
/* Table: t_mmdm_order_after_sale_logistics_img                 */
/*==============================================================*/
create table t_mmdm_order_after_sale_logistics_img
(
    entry_id             bigint unsigned not null comment '主键id',
    id                   bigint comment '主键id',
    courier_img_type     varchar(2) not null default '' comment '快递物流图片类型 1-商家发货 2-客户寄回 3-商家再次发货',
    courier_img_path     varchar(400) not null default '' comment '快递图片链接',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (entry_id)
);

alter table t_mmdm_order_after_sale_logistics_img comment '订单售后物流图片拆分表';

/*==============================================================*/
/* Table: t_mmdm_order_after_sale_logistics_img_his             */
/*==============================================================*/
create table t_mmdm_order_after_sale_logistics_img_his
(
    entry_id             bigint unsigned not null comment '主键id',
    history_id           bigint unsigned not null comment '售后协商历史ID',
    id                   bigint comment '主键id',
    courier_img_type     varchar(2) not null default '' comment '快递物流图片类型 1-商家发货 2-客户寄回 3-商家再次发货',
    courier_img_path     varchar(400) not null default '' comment '快递图片链接',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (entry_id)
);

alter table t_mmdm_order_after_sale_logistics_img_his comment '订单售后物流图片拆分历史表';

/*==============================================================*/
/* Table: t_mmdm_order_batch_deliver                            */
/*==============================================================*/
create table t_mmdm_order_batch_deliver
(
    id                   bigint unsigned not null comment '主键id',
    business_id          bigint not null default 0 comment '关联商户ID',
    shop_id              bigint not null default 0 comment '关联店铺ID',
    deliver_type         varchar(2) not null default '1' comment '操作类型 1-批量发货',
    record_time          datetime comment '操作时间 yyyy-MM-dd HH:mm:ss',
    operator             bigint not null default 0 comment '操作人',
    operator_type        varchar(2) not null default '' comment '操作人类型 0-平台用户 1-商家端用户 2-App端用户',
    operate_excel_name   varchar(100) not null default '' comment 'excel导入文件名称',
    operate_excel_url    varchar(200) not null default '' comment 'excel导入文件',
    total_num            int not null default 0 comment '操作订单数',
    success_num          int not null default 0 comment '操作成功数',
    error_num            int not null default 0 comment '操作失败数',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_order_batch_deliver comment '订单批量发货记录表';

/*==============================================================*/
/* Table: t_mmdm_order_batch_deliver_record                     */
/*==============================================================*/
create table t_mmdm_order_batch_deliver_record
(
    entry_id             bigint unsigned not null comment '主键id',
    id                   bigint not null default 0 comment '订单批量发货ID',
    order_id             bigint not null default 0 comment '订单ID',
    order_number         varchar(50) not null default '' comment '订单号',
    import_status        varchar(2) not null default '' comment '导入状态 0-失败 1-成功',
    logistics_company_name varchar(50) not null default '' comment '物流公司',
    courier_number       varchar(50) not null default '' comment '快递单号',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (entry_id)
);

alter table t_mmdm_order_batch_deliver_record comment '订单批量发货记录拆分表';

/*==============================================================*/
/* Table: t_mmdm_order_complaint                                */
/*==============================================================*/
create table t_mmdm_order_complaint
(
    id                   bigint unsigned not null comment '主键id',
    number               varchar(50) not null default '' comment '投诉编号 规则为年月日时分秒豪秒',
    order_id             bigint not null default 0 comment '订单ID',
    order_after_sale_id  bigint not null default 0 comment '订单售后ID',
    app_id               varchar(100) not null default '' comment '关联小程序ID',
    mch_id               varchar(100) not null default '' comment '关联微信支付商户号ID',
    order_applet_type    varchar(2) not null default '1' comment '下单小程序类型来源 1-微信小程序',
    prod_id              bigint not null default 0 comment '关联商品ID',
    customer_id          bigint not null default 0 comment '关联客户ID ',
    complaint_type       varchar(2) not null default '' comment '投诉类型 1-微信支付投诉 2-店铺投诉',
    complaint_reason     varchar(2) not null default '' comment '投诉原因 1-发货问题 2-商品问题 3-客服问题 4-其他问题',
    complaint_busi_status varchar(2) not null default '' comment '投诉状态（商家处理）0-未处理 1-处理中 2-已处理',
    complaint_cust_status varchar(2) not null default '' comment '投诉状态（用户认可）0-不认可 1-认可',
    complaint_platform_status varchar(2) not null default '' comment '投诉状态（平台介入）0-未介入 1-已介入',
    complaint_status     varchar(3) not null default '' comment '投诉状态1-发起投诉 100-商家同意和解 101-商家不同意和解 109-商家处理超时 200-用户认可和解 201-用户不认可和解 209-用户撤销投诉',
    customer_phone       varchar(50) not null default '' comment '客户联系电话',
    feedback_reason      varchar(200) not null default '' comment '反馈原因',
    complaint_content    varchar(200) not null default '' comment '沟通内容',
    apply_time           datetime comment '申请时间 yyyy-MM-dd HH:mm:ss',
    busi_operate_time    datetime comment '商家处理时间 yyyy-MM-dd HH:mm:ss',
    cust_operate_time    datetime comment '客户处理时间 yyyy-MM-dd HH:mm:ss',
    finish_time          datetime comment '完成时间 yyyy-MM-dd HH:mm:ss',
    complaint_object     varchar(50) not null default '' comment '投诉对象',
    show_img             varchar(200) not null default '' comment '商品展示图（缩略图）',
    description          varchar(200) not null default '' comment '备注',
    flag                 char(1) not null default '0' comment '逻辑删除 0-否 1-是',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_order_complaint comment '订单投诉表';

/*==============================================================*/
/* Table: t_mmdm_order_complaint_img                            */
/*==============================================================*/
create table t_mmdm_order_complaint_img
(
    entry_id             bigint unsigned not null comment '主键id',
    id                   bigint not null default 0 comment '订单投诉ID',
    img_path             varchar(200) not null default '' comment '投诉图片链接',
    img_type             varchar(2) not null default '' comment '投诉图片类型 1-客户申请投诉 2-协商上传凭证',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (entry_id)
);

alter table t_mmdm_order_complaint_img comment '订单投诉图片拆分表';

/*==============================================================*/
/* Table: t_mmdm_order_complaint_img_his                        */
/*==============================================================*/
create table t_mmdm_order_complaint_img_his
(
    entry_id             bigint unsigned not null comment '主键id',
    history_id           bigint unsigned not null comment '订单投诉历史ID',
    id                   bigint not null default 0 comment '订单投诉ID',
    img_path             varchar(200) not null default '' comment '投诉图片链接',
    img_type             varchar(2) not null default '' comment '投诉图片类型 1-客户申请投诉 2-协商上传凭证',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (entry_id)
);

alter table t_mmdm_order_complaint_img_his comment '订单投诉图片拆分历史表';

/*==============================================================*/
/* Table: t_mmdm_order_complaint_record                         */
/*==============================================================*/
create table t_mmdm_order_complaint_record
(
    history_id           bigint unsigned not null comment '主键id',
    id                   bigint not null default 0 comment '订单投诉ID',
    number               varchar(50) not null default '' comment '投诉编号 规则为年月日时分秒豪秒',
    order_id             bigint not null default 0 comment '订单ID',
    order_after_sale_id  bigint not null default 0 comment '订单售后ID',
    app_id               varchar(100) not null default '' comment '关联小程序ID',
    mch_id               varchar(100) not null default '' comment '关联微信支付商户号ID',
    order_applet_type    varchar(2) not null default '1' comment '下单小程序类型来源 1-微信小程序',
    prod_id              bigint not null default 0 comment '关联商品ID',
    customer_id          bigint not null default 0 comment '关联客户ID ',
    complaint_type       varchar(2) not null default '' comment '投诉类型 1-微信支付投诉 2-店铺投诉',
    complaint_reason     varchar(2) not null default '' comment '投诉原因 1-发货问题 2-商品问题 3-客服问题 4-其他问题',
    complaint_busi_status varchar(2) not null default '' comment '投诉状态（商家处理）0-未处理 1-处理中 2-已处理',
    complaint_cust_status varchar(2) not null default '' comment '投诉状态（用户认可）0-不认可 1-认可',
    complaint_platform_status varchar(2) not null default '' comment '投诉状态（平台介入）0-未介入 1-已介入',
    complaint_status     varchar(3) not null default '' comment '投诉状态1-发起投诉 100-商家同意和解 101-商家不同意和解 109-商家处理超时 200-用户认可和解 201-用户不认可和解 209-用户撤销投诉',
    customer_phone       varchar(50) not null default '' comment '客户联系电话',
    cust_reason          varchar(200) not null default '' comment '反馈原因（用户）',
    busi_content         varchar(200) not null default '' comment '沟通内容（商户）',
    apply_time           datetime comment '申请时间 yyyy-MM-dd HH:mm:ss',
    busi_operate_time    datetime comment '商家处理时间 yyyy-MM-dd HH:mm:ss',
    cust_operate_time    datetime comment '客户处理时间 yyyy-MM-dd HH:mm:ss',
    finish_time          datetime comment '完成时间 yyyy-MM-dd HH:mm:ss',
    complaint_object     varchar(50) not null default '' comment '投诉对象',
    show_img             varchar(200) not null default '' comment '商品展示图（缩略图）',
    changer_user_type    varchar(2) not null default '' comment '变更人类型 0-平台用户 1-商家端用户 2-App端用户',
    changer_user_id      bigint not null default 0 comment '变更人ID',
    change_time          datetime comment '变更时间 yyyy-MM-dd HH:mm:ss',
    description          varchar(200) not null default '' comment '备注',
    flag                 char(1) not null default '0' comment '逻辑删除 0-否 1-是',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (history_id)
);

alter table t_mmdm_order_complaint_record comment '订单投诉处理记录表';

/*==============================================================*/
/* Table: t_mmdm_order_cust_info                                */
/*==============================================================*/
create table t_mmdm_order_cust_info
(
    id                   bigint unsigned not null comment '主键id（订单ID）',
    customer_id          bigint not null default 0 comment '关联客户ID ',
    customer_name        varchar(50) not null default '' comment '客户姓名',
    customer_province    varchar(60) not null default '' comment '客户所在地区省、直辖市',
    customer_city        varchar(60) not null default '' comment '客户所在地区市',
    customer_district    varchar(60) not null default '' comment '客户所在地区区、县',
    customer_addr        varchar(500) not null default '' comment '详细地址',
    customer_phone       varchar(30) not null default '' comment '手机',
    customer_description varchar(500) not null default '' comment '客户备注',
    tp_type              varchar(2) not null default '1' comment '第三方平台类型 1-微信',
    tp_name              varchar(50) not null default '' comment '昵称',
    tp_photo             varchar(200) not null default '' comment '头像',
    tp_openid            varchar(200) not null default '' comment 'openid',
    tp_qrcode            varchar(200) not null default '' comment '推广二维码',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_order_cust_info comment '客户订单与客户关联拆分表';

/*==============================================================*/
/* Table: t_mmdm_order_info                                     */
/*==============================================================*/
create table t_mmdm_order_info
(
    id                   bigint not null comment '主键ID',
    number               varchar(50) not null default '' comment '订单号 规则为年月日时分秒豪秒',
    name                 varchar(200) not null default '' comment '订单名称',
    business_id          bigint not null default 0 comment '关联商户ID',
    shop_id              bigint not null default 0 comment '关联店铺ID',
    prod_id              bigint not null default 0 comment '关联商品ID',
    prod_sku_id          bigint not null default 0 comment '关联商品SKUID',
    prod_combo_id        bigint not null default 0 comment '商品套餐ID',
    customer_id          bigint not null default 0 comment '关联客户ID ',
    app_id               varchar(100) not null default '' comment '关联小程序ID',
    order_applet_type    varchar(2) not null default '1' comment '下单小程序类型来源 1-微信小程序',
    order_time           datetime not null default CURRENT_TIMESTAMP comment '下单时间 yyyy-MM-dd HH:mm:ss',
    order_source         varchar(2) not null default '1' comment '订单来源 1-自然流量 5-广告引流 10-回流流量-未回传 15-回流流量-已回传 20-平台流量',
    order_path           varchar(2) not null default '1' comment '下单路径',
    order_status         varchar(2) not null default '1' comment '订单状态1-待付款 10-待发货 20-已发货 30-已完成 99-已关闭',
    pay_time             datetime comment '支付时间 yyyy-MM-dd HH:mm:ss',
    pay_type             varchar(2) not null default '1' comment '支付方式 1-微信支付',
    order_amount         decimal(10,2) not null default 0.00 comment '订单金额',
    pay_amount           decimal(10,2) not null default 0.00 comment '支付金额',
    pay_carriage         decimal(10,2) not null default 0.00 comment '支付运费',
    discount_amount      decimal(10,2) not null default 0.00 comment '优惠金额',
    cust_coupon_restrict_id bigint not null default 0 comment '关联客户领用优惠券记录ID',
    coupon_id            bigint not null default 0 comment '关联优惠券ID ',
    coupon_type          varchar(2) not null default '' comment '优惠券类型 1-商品立减劵 2-回流优惠券 3-平台优惠券',
    order_deliver_timeout char(1) not null default '0' comment '发货超时 0-否 1-是 ',
    order_after_flag     char(1) not null default '0' comment '售后中 0-否 1-是 ',
    order_after_sale_id  bigint not null default 0 comment '当前售后服务ID',
    order_after_sale_num int not null default 0 comment '售后次数',
    customer_operate_del char(1) not null default '0' comment '客户是否删除订单 0-否 1-是',
    order_logistics_id   bigint not null default 0 comment '订单物流ID',
    order_finish_time    datetime comment '交易完成时间 yyyy-MM-dd HH:mm:ss',
    is_adv_back          char(1) not null default '0' comment '广告是否回传 0-否 1-是',
    adv_account_id       bigint not null default 0 comment '关联广告账户ID',
    ad_aid               varchar(100) not null default '' comment '腾讯广告id/腾讯广告计划id',
    click_id             varchar(100) not null default '' comment '腾讯广告点击id',
    adv_channel_type     varchar(2) not null default '1' comment '广告渠道类型 1-腾讯广告 2-快手广告 3-序言泽联盟广告 4-腾讯视频号广告 5-美柚广告',
    back_ratio           decimal(10,2) not null default 0.00 comment '回传比率',
    deliver_notify       char(1) not null default '0' comment '开启订单发货通知 0-否 1-是',
    wait_pay_notify      char(1) not null default '0' comment '开启订单待付款通知 0-否 1-是',
    source_prod_id       bigint not null default 0 comment '订单来源商品id',
    source_coupon_code   varchar(50) not null default '' comment '订单来源优惠券code',
    receive_business_id  bigint not null default 0 comment '分账商户id',
    wx_pay_receive_mch_id varchar(100) not null default '' comment '分账商户mch_id或者sub_mch_id',
    is_receive_order     char(1) not null default '0' comment '是否为分账订单 0-否 1-是',
    order_receive_status varchar(2) not null default '' comment '订单分账状态 0-未分账 1-已分账 2-已回退 3-分账失败 4-回退失败',
    description          varchar(200) not null default '' comment '备注',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    flag                 char(1) not null default '0' comment '逻辑删除 0-否 1-是',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_order_info comment '订单表';

/*==============================================================*/
/* Table: t_mmdm_order_logistics                                */
/*==============================================================*/
create table t_mmdm_order_logistics
(
    id                   bigint unsigned not null comment '主键id',
    order_id             bigint not null default 0 comment '订单ID',
    consigner            varchar(50) not null default '' comment '发货人',
    contact              varchar(60) not null default '' comment '发货人联系方式',
    addr_province        varchar(60) not null default '' comment '发货地址 省、直辖市',
    addr_city            varchar(60) not null default '' comment '发货地址 市',
    addr_district        varchar(200) not null default '' comment '发货地址 区、县',
    addr                 varchar(500) not null default '' comment '发货详细地址 ',
    logistics_company    varchar(50) not null default '' comment '物流公司 字典表CODE',
    courier_number       varchar(50) not null default '' comment '快递单号',
    logistics_time       datetime comment '发货时间',
    content              varchar(3000) not null default '' comment '第三方接口返回的物流信息',
    is_update            char(1) not null default '0' comment '是否已修改 0-否 1-是',
    is_batch_deliver     char(1) not null default '0' comment '是否批量发货 0-否 1-是',
    batch_deliver_id     bigint not null default 0 comment '批量发货记录ID',
    description          varchar(200) not null default '' comment '备注',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_order_logistics comment '订单物流记录表';

/*==============================================================*/
/* Table: t_mmdm_order_logistics_img                            */
/*==============================================================*/
create table t_mmdm_order_logistics_img
(
    entry_id             bigint unsigned not null comment '主键id',
    id                   bigint not null default 0 comment '订单物流ID',
    courier_img_path     varchar(400) not null default '' comment '快递图片链接',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (entry_id)
);

alter table t_mmdm_order_logistics_img comment '订单物流图片表';

/*==============================================================*/
/* Table: t_mmdm_order_pay_record                               */
/*==============================================================*/
create table t_mmdm_order_pay_record
(
    id                   bigint unsigned not null comment '主键id',
    order_id             bigint not null default 0 comment '关联订单ID',
    customer_id          bigint not null default 0 comment '关联客户ID',
    pay_amount           decimal(10,2) not null default 0.00 comment '付款金额',
    pay_mode             varchar(2) not null default '1' comment '付款方式 1- 微信支付',
    pay_status           varchar(2) not null default '1' comment '付款状态1-未支付 2-支付中 3-支付成功 4-支付失败',
    pay_time             datetime comment '付款时间 yyyy-MM-dd HH:mm:ss',
    pay_activity_period  int unsigned not null default 0 comment '支付有效时长 秒级 取商品的有效时长',
    pay_content          varchar(500) not null default '' comment '付款内容',
    trading_flow         varchar(50) not null default '' comment '流水号',
    out_trade_no         varchar(50) not null default '' comment '支付订单号',
    pay_resp_content     text comment '支付返回内容',
    callback_content     text comment '支付回调返回内容',
    callback_time        datetime comment '支付回调返回时间',
    is_settle            char(1) not null default '0' comment '是否结算 0-否 1-是',
    refund_id            varchar(50) not null default '' comment '退款流水号',
    refund_trade_no      varchar(50) not null default '' comment '退款号',
    refund_amount        decimal(10,2) not null default 0.00 comment '退款金额',
    refund_reason        varchar(200) not null default '' comment '退款原因',
    refund_status        varchar(2) not null default '' comment '退款状态 0-未退款 1-已退款 2-退款中',
    refund_time          datetime comment '退款时间 yyyy-MM-dd HH:mm:ss',
    refund_resp_content  text comment '退款返回内容',
    refund_callback_content text comment '退款回调返回内容',
    refund_callback_time datetime comment '退款回调返回时间',
    unfreeze_trade_no    varchar(50) not null default '' comment '解冻流水号',
    description          varchar(200) not null default '' comment '备注',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_order_pay_record comment '订单支付记录表';

/*==============================================================*/
/* Table: t_mmdm_order_prod_info                                */
/*==============================================================*/
create table t_mmdm_order_prod_info
(
    id                   bigint not null comment '主键id（订单ID）',
    prod_id              bigint not null default 0 comment '关联商品ID',
    category_id          bigint not null default 0 comment '所属分类ID',
    brand_id             bigint not null default 0 comment '品牌ID',
    buy_number           int not null default 1 comment '数量',
    prod_name            varchar(200) not null default '' comment '商品标题',
    prod_sku_id          bigint not null default 0 comment '关联商品销售属性ID',
    prod_sku_name        varchar(200) not null default '' comment '商品销售属性标题',
    prod_sku_attr_values varchar(500) not null default '' comment '关联商品属性组合（JSON）',
    prod_sku_price       decimal(10,2) not null default 0.00 comment '商品销售金额',
    prod_stock_id        bigint not null default 0 comment '关联商品库存ID',
    prod_combo_id        bigint not null default 0 comment '商品套餐ID',
    prod_combo_title     varchar(200) not null default '' comment '商品销售套餐标题',
    prod_combo_attr_values varchar(500) not null default '' comment '商品销售套餐属性组合（JSON）  {"attrname":attrValue,"attrname":attrValue}',
    prod_combo_price     decimal(10,2) not null default 0.00 comment '商品销售套餐金额',
    prod_combo_standard_price decimal(10,2) not null default 0.00 comment '商品套餐划线价',
    prod_combo_cost_price decimal(10,2) not null default 0.00 comment '商品成本价',
    prod_img             varchar(200) not null default '' comment '商品图片',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_order_prod_info comment '订单商品参数详细表';

/*==============================================================*/
/* Table: t_mmdm_order_rec_addr                                 */
/*==============================================================*/
create table t_mmdm_order_rec_addr
(
    id                   bigint unsigned not null comment '主键id（订单ID）',
    customer_id          bigint not null default 0 comment '关联客户ID ',
    consignee            varchar(50) not null default '' comment '收货人',
    phone                varchar(30) not null default '' comment '手机号 ',
    addr_province        varchar(60) not null default '' comment '收货地址 省、直辖市',
    addr_city            varchar(60) not null default '' comment '收货地址 市',
    addr_district        varchar(200) not null default '' comment '收货地址 区、县',
    addr                 varchar(500) not null default '' comment '详细地址 ',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_order_rec_addr comment '订单收货地址拆分表';

/*==============================================================*/
/* Table: t_mmdm_order_receive_record                           */
/*==============================================================*/
create table t_mmdm_order_receive_record
(
    id                   bigint unsigned not null comment '主键id',
    number               varchar(50) not null default '' comment '订单号 规则为年月日时分秒豪秒',
    business_type        varchar(2) not null default '' comment '分账接收方类型 1-商户 3-平台',
    wx_order_id          varchar(50) not null default '' comment '微信分账订单ID',
    out_trade_no         varchar(50) not null default '' comment '商户分账单号',
    transaction_id       varchar(50) not null default '' comment '微信支付订单号',
    mch_id               varchar(100) not null default '' comment '分账接收方账号',
    amount               int unsigned not null default 0 comment '分账金额（分）',
    receive_time         datetime comment '分账发生时间 yyyy-MM-dd HH:mm:ss',
    detail_id            varchar(50) not null default '' comment '分账明细单号',
    receive_states       varchar(20) not null default '' comment '分账单状态 PROCESSING-处理中  FINISHED-分账完成',
    receive_result       varchar(20) not null default '' comment '分账结果 PENDING-待分账 SUCCESS-分账成功  CLOSED-已关闭',
    receive_fail_reason  varchar(50) not null default '' comment '分账失败原因 1、ACCOUNT_ABNORMAL：分账接收账户异常
              2、NO_RELATION：分账关系已解除
              3、RECEIVER_HIGH_RISK：高风险接收方
              4、RECEIVER_REAL_NAME_NOT_VERIFIED：接收方未实名
              5、NO_AUTH：分账权限已解除
              6、RECEIVER_RECEIPT_LIMIT：接收方已达收款限额
              7、PAYER_ACCOUNT_ABNORMAL：分出方账户异常',
    out_return_no        varchar(50) not null default '' comment '商户回退单号',
    return_time          datetime comment '申请回退时间 yyyy-MM-dd HH:mm:ss',
    return_result        varchar(20) not null default '' comment '回退结果 PENDING-待分账 SUCCESS-分账成功  CLOSED-已关闭',
    return_fail_reason   varchar(50) not null default '' comment '回退失败原因 11、ACCOUNT_ABNORMAL：原分账接收方账户异常
              2、TIME_OUT_CLOSED：超时关单
              3、PAYER_ACCOUNT_ABNORMAL：原分账分出方账户异常',
    receive_record_status varchar(2) not null default '' comment '分账状态 0-分账中 1-已分账 2-回退中 3-已回退 4-分账失败 5-回退失败 6-已关闭 7-回退中（押金） 8-已回退（押金）',
    return_id            varchar(50) not null default '' comment '微信回退单号',
    return_type          varchar(2) not null default '' comment '回退类型 1-平台分账回退  2-平台售后分账回退',
    return_amount        decimal(10,2) not null default 0.00 comment '回退金额',
    return_description   varchar(100) not null default '' comment '回退备注 例：押金扣除平台服务费XX元',
    deposit_scale        decimal(10,2) not null default 0.00 comment '押金比例',
    receive_scale        decimal(10,2) not null default 0.00 comment '分账比例',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_order_receive_record comment '订单分账记录表';

/*==============================================================*/
/* Table: t_mmdm_order_source_scale                             */
/*==============================================================*/
create table t_mmdm_order_source_scale
(
    id                   bigint unsigned not null comment '主键id',
    order_source         varchar(2) not null default '1' comment '订单来源 1-主动打开微信小程序 5-点击所投放的广告进入小程序 10-卡包回流优惠券 15-卡包平台券',
    order_path           varchar(2) not null default '1' comment '下单路径',
    business_scale       decimal(10,2) not null default 0.00 comment '商户分账比例',
    platform_scale       decimal(10,2) not null default 0.00 comment '平台分账比例',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_order_source_scale comment '订单来源分账比例表';

/*==============================================================*/
/* Table: t_mmdm_order_stat_record                              */
/*==============================================================*/
create table t_mmdm_order_stat_record
(
    id                   bigint unsigned not null comment '主键id',
    order_id             bigint not null default 0 comment '订单ID',
    current_state        varchar(2) not null default '' comment '当前状态（订单状态）',
    change_status        varchar(2) not null default '' comment '变更状态（订单状态）',
    changer_user_id      bigint not null default 0 comment '变更人ID',
    changer_user         varchar(50) not null default '' comment '变更人',
    change_time          datetime comment '变更时间 yyyy-MM-dd HH:mm:ss',
    description          varchar(200) not null default '' comment '备注',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_mmdm_order_stat_record comment '订单状态变更记录表 ';
