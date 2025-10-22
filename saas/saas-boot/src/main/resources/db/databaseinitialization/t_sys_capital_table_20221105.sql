drop table if exists t_sys_capital_pool;

drop table if exists t_sys_capital_record;

/*==============================================================*/
/* Table: t_sys_capital_pool                                    */
/*==============================================================*/
create table t_sys_capital_pool
(
    id                   bigint unsigned not null comment '主键id',
    banance              decimal(10,2) not null default 0.00 comment '余额',
    total                decimal(10,2) not null default 0.00 comment '累计收入',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_sys_capital_pool comment '平台资金池表';

/*==============================================================*/
/* Table: t_sys_capital_record                                  */
/*==============================================================*/
create table t_sys_capital_record
(
    id                   bigint unsigned not null comment '主键id',
    shop_id              bigint not null default 0 comment '关联店铺ID',
    number               varchar(50) not null default '' comment '订单号',
    bill_type            varchar(2) not null default '' comment '账单来源',
    bill_amount          varchar(50) not null default '' comment '账单金额',
    account_banance      numeric(10,2) not null default 0.00 comment '账户余额',
    description          varchar(500) not null default '' comment '记账事件',
    bill_time            datetime comment '记账时间 yyyy-MM-dd HH:mm:ss',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_sys_capital_record comment '平台资金出入账记录表 ';
