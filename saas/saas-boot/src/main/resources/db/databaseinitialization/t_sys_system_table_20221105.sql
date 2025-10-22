drop table if exists t_sys_menu;

drop table if exists t_sys_menu_module;

drop table if exists t_sys_menu_perm;

drop table if exists t_sys_org;

drop table if exists t_sys_perm;

drop table if exists t_sys_role;

drop table if exists t_sys_role_menu;

drop table if exists t_sys_user;

drop table if exists t_sys_user_activex;

drop table if exists t_sys_user_login;

drop table if exists t_sys_user_login_record;

drop table if exists t_sys_user_relate;

drop table if exists t_sys_user_role;

/*==============================================================*/
/* Table: t_sys_menu                                            */
/*==============================================================*/
create table t_sys_menu
(
    id                   bigint unsigned not null comment '主键id',
    menu_module_id       bigint not null default 0 comment '模块ID',
    name                 varchar(50) not null default '' comment '菜单名称',
    parent_id            bigint unsigned not null default 0 comment '上级菜单id',
    tips                 varchar(30) not null default '' comment '菜单提示',
    url                  varchar(200) not null default '' comment '菜单url',
    menu_level           int unsigned not null default 1 comment '层级',
    menu_ord             int unsigned not null default 1 comment '菜单排序',
    menu_type            varchar(2) not null default '0' comment '菜单类型 0-平台菜单 1-商家端菜单 2-APP端菜单',
    icon_class           varchar(100) not null default '' comment '图标',
    description          varchar(200) not null default '' comment '备注',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统权限信息表 s_menu';

alter table t_sys_menu comment '系统菜单表';

/*==============================================================*/
/* Table: t_sys_menu_module                                     */
/*==============================================================*/
create table t_sys_menu_module
(
    id                   bigint unsigned not null comment '主键id',
    number               varchar(50) not null default '' comment '模块编码',
    name                 varchar(50) not null default '' comment '模块名称',
    module_ord           int unsigned not null default 1 comment '模块排序',
    module_type          varchar(2) not null default '0' comment '模块类型 0-平台模块 1-商家端模块 2-APP端模块',
    icon_class           varchar(100) not null default '' comment '图标',
    description          varchar(200) not null default '' comment '备注',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统权限信息表 s_menu';

alter table t_sys_menu_module comment '系统模块表';

/*==============================================================*/
/* Table: t_sys_menu_perm                                       */
/*==============================================================*/
create table t_sys_menu_perm
(
    id                   bigint unsigned not null comment '主键id',
    menu_id              bigint not null default 0 comment '菜单ID',
    perm_id              bigint unsigned not null default 0 comment '权限项ID',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统部门信息表 s_org';

alter table t_sys_menu_perm comment '系统菜单权限项关联表';

/*==============================================================*/
/* Table: t_sys_org                                             */
/*==============================================================*/
create table t_sys_org
(
    id                   bigint unsigned not null comment '主键id',
    name                 varchar(60) not null default '' comment '部门名称',
    parent_id            bigint unsigned not null default 0 comment '上级部门id',
    org_ord              int unsigned not null default 1 comment '部门排序',
    org_level            int unsigned not null default 1 comment '层级',
    description          varchar(200) comment '备注',
    data_status          varchar(2) not null default '1' comment '状态 0-禁用 1-有效',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统部门信息表 s_org';

alter table t_sys_org comment '系统部门表';

/*==============================================================*/
/* Table: t_sys_perm                                            */
/*==============================================================*/
create table t_sys_perm
(
    id                   bigint unsigned not null comment '主键id',
    number               varchar(50) not null default '' comment '编码',
    name                 varchar(50) not null default '' comment '名称',
    is_preset            char(1) not null default '0' comment '平台预置 0-否 1-是',
    description          varchar(200) not null default '' comment '描述',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统角色表 s_role';

alter table t_sys_perm comment '系统权限项表';

/*==============================================================*/
/* Table: t_sys_role                                            */
/*==============================================================*/
create table t_sys_role
(
    id                   bigint unsigned not null comment '主键id',
    number               varchar(50) not null default '' comment '编码',
    name                 varchar(50) not null default '' comment '名称',
    is_preset            char(1) not null default '0' comment '平台预置 0-否 1-是',
    role_type            varchar(2) not null default '0' comment '角色类型 0-平台角色 1-商家端角色 2-APP端角色',
    description          varchar(200) not null default '' comment '描述',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统角色表 s_role';

alter table t_sys_role comment '系统角色表';

/*==============================================================*/
/* Table: t_sys_role_menu                                       */
/*==============================================================*/
create table t_sys_role_menu
(
    id                   bigint unsigned not null comment '主键id',
    role_id              bigint unsigned not null default 0 comment '角色ID',
    menu_module_id       bigint not null default 0 comment '模块ID',
    menu_id              bigint not null default 0 comment '菜单ID',
    perm_id              bigint unsigned not null default 0 comment '权限项ID',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统部门信息表 s_org';

alter table t_sys_role_menu comment '系统角色模块菜单权限项关联表';

/*==============================================================*/
/* Table: t_sys_user                                            */
/*==============================================================*/
create table t_sys_user
(
    id                   bigint unsigned not null comment '主键id',
    avatar               varchar(200) not null default '' comment '头像',
    number               varchar(50) not null default '' comment '工号',
    name                 varchar(50) not null default '' comment '用户名',
    phone                varchar(200) not null default '' comment '手机号',
    email                varchar(30) not null default '' comment '邮箱',
    is_super             char(1) not null default '0' comment '是否管理员 0-否 1-是',
    is_sysuser           char(1) not null default '1' comment '是否系统用户 0-否 1-是',
    org_id               bigint unsigned not null default 0 comment '部门ID',
    description          varchar(200) not null default '' comment '备注',
    data_status          varchar(2) not null default '1' comment '数据状态 0-禁用 1-可用',
    creator              bigint not null default 0 comment '创建人',
    modifier             bigint not null default 0 comment '更新人',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统用户信息表 s_user';

alter table t_sys_user comment '系统用户表';

/*==============================================================*/
/* Table: t_sys_user_activex                                    */
/*==============================================================*/
create table t_sys_user_activex
(
    id                   bigint not null comment '主键id ',
    avatar               varchar(200) not null default '' comment '头像',
    number               varchar(50) not null default '' comment '工号',
    name                 varchar(50) not null default '' comment '用户名',
    phone                varchar(200) not null default '' comment '手机号',
    email                varchar(30) not null default '' comment '邮箱',
    activex_data_status  varchar(2) not null default '1' comment '控件数据状态 0-已删除 1-可用',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
);

alter table t_sys_user_activex comment '系统用户控件关联表';

/*==============================================================*/
/* Table: t_sys_user_login                                      */
/*==============================================================*/
create table t_sys_user_login
(
    id                   bigint unsigned not null comment '主键id',
    login_name           varchar(200) not null default '' comment '登录名',
    password             varchar(50) not null default '' comment '密码',
    login_type           varchar(2) not null default '1' comment '登录类型 0-工号 1-手机号',
    user_id              bigint not null default 0 comment '系统用户ID',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统用户信息表 s_user';

alter table t_sys_user_login comment '系统用户登录表';

/*==============================================================*/
/* Table: t_sys_user_login_record                               */
/*==============================================================*/
create table t_sys_user_login_record
(
    id                   bigint unsigned not null comment '主键id',
    user_id              bigint not null default 0 comment '系统用户ID',
    relate_id            bigint not null default 0 comment '关联用户ID',
    user_login_id        bigint not null default 0 comment '系统用户登录ID',
    login_ip             varchar(50) not null default '' comment '本次登录IP',
    login_time           datetime comment '本次登录时间 yyyy-MM-dd HH:mm:ss',
    logout_time          datetime comment '本次登出时间 yyyy-MM-dd HH:mm:ss',
    online               varchar(2) not null default '' comment '在线状态 0-登出 1-在线',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统用户信息表 s_user';

alter table t_sys_user_login_record comment '系统用户登录记录表';

/*==============================================================*/
/* Table: t_sys_user_relate                                     */
/*==============================================================*/
create table t_sys_user_relate
(
    id                   bigint unsigned not null comment '主键id',
    user_id              bigint not null default 0 comment '系统用户ID',
    relate_id            bigint unsigned not null default 0 comment '关联其他端用户ID',
    relate_type          varchar(2) not null default '' comment '关联类型 0-系统平台用户 1-商家端用户 2-小程序端用户',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统部门信息表 s_org';

alter table t_sys_user_relate comment '系统用户关联表';

/*==============================================================*/
/* Table: t_sys_user_role                                       */
/*==============================================================*/
create table t_sys_user_role
(
    id                   bigint unsigned not null comment '主键id',
    user_id              bigint not null default 0 comment '用户ID',
    role_id              bigint unsigned not null default 0 comment '角色ID',
    gmt_create           datetime comment '创建时间 yyyy-MM-dd HH:mm:ss',
    gmt_modified         datetime comment '更新时间 yyyy-MM-dd HH:mm:ss',
    primary key (id)
)
    engine=innodb default charset=utf8 comment='系统部门信息表 s_org';

alter table t_sys_user_role comment '系统用户角色关联表';
