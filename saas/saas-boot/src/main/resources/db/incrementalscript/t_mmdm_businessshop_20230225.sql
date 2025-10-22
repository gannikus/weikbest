drop table if exists t_mmdm_busi_user_relate;

/*==============================================================*/
/* Table: t_mmdm_busi_user_relate                               */
/*==============================================================*/
create table t_mmdm_busi_user_relate
(
   id                   bigint unsigned not null comment '主键id',
   business_id          bigint not null default 0 comment '商户ID',
   business_user_id     bigint not null default 0 comment '商户用户ID',
   is_main_user         char(1) not null default '0' comment '是否主账号 0-否 1-是',
   gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
   gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
   primary key (id)
)
engine=innodb default charset=utf8 comment='系统部门信息表 s_org';

alter table t_mmdm_busi_user_relate comment '商户与商户账户关联表';

/*==============================================================*/
/* Index: idx_user_role_businessid                              */
/*==============================================================*/
create index idx_user_role_businessid on t_mmdm_busi_user_relate
(
   business_id
);

/*==============================================================*/
/* Index: idx_user_role_busiuserid                              */
/*==============================================================*/
create index idx_user_role_busiuserid on t_mmdm_busi_user_relate
(
   business_user_id
);



insert into t_mmdm_busi_user_relate(id, business_id, business_user_id, is_main_user, gmt_create, gmt_modified) select id, business_id, id, is_main_user, gmt_create, gmt_modified from t_mmdm_busi_user;