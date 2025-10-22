drop index idx_menu_parentid on t_sys_menu;

drop index idx_menu_module_unqiue on t_sys_menu_module;

drop index idx_mp_menuid on t_sys_menu_perm;

drop index idx_mp_permid on t_sys_menu_perm;

drop index idx_org_parentid on t_sys_org;

drop index idx_perm_unique on t_sys_perm;

drop index idx_role_unique on t_sys_role;

drop index idx_role_menu_permid on t_sys_role_menu;

drop index idx_role_menu_moduleid on t_sys_role_menu;

drop index idx_role_menu_menuid on t_sys_role_menu;

drop index idx_role_menu_roleid on t_sys_role_menu;

drop index idx_user_orgid on t_sys_user;

drop index idx_user_phone on t_sys_user;

drop index idx_user_number on t_sys_user;

drop index idx_user_login on t_sys_user_login;

drop index idx_ulc_userid on t_sys_user_login_record;

drop index idx_ulc_loginid on t_sys_user_login_record;

drop index idx_user_relate on t_sys_user_relate;

drop index idx_user_role_userid on t_sys_user_role;

drop index idx_user_role_roleid on t_sys_user_role;

/*==============================================================*/
/* Index: idx_menu_parentid                                     */
/*==============================================================*/
create index idx_menu_parentid on t_sys_menu
    (
     parent_id
        );

/*==============================================================*/
/* Index: idx_menu_module_unqiue                                */
/*==============================================================*/
create unique index idx_menu_module_unqiue on t_sys_menu_module
    (
     number
        );

/*==============================================================*/
/* Index: idx_mp_permid                                         */
/*==============================================================*/
create index idx_mp_permid on t_sys_menu_perm
    (
     perm_id
        );

/*==============================================================*/
/* Index: idx_mp_menuid                                         */
/*==============================================================*/
create index idx_mp_menuid on t_sys_menu_perm
    (
     menu_id
        );

/*==============================================================*/
/* Index: idx_org_parentid                                      */
/*==============================================================*/
create index idx_org_parentid on t_sys_org
    (
     parent_id
        );

/*==============================================================*/
/* Index: idx_perm_unique                                       */
/*==============================================================*/
create unique index idx_perm_unique on t_sys_perm
    (
     number
        );

/*==============================================================*/
/* Index: idx_role_unique                                       */
/*==============================================================*/
create unique index idx_role_unique on t_sys_role
    (
     number
        );

/*==============================================================*/
/* Index: idx_role_menu_roleid                                  */
/*==============================================================*/
create index idx_role_menu_roleid on t_sys_role_menu
    (
     role_id
        );

/*==============================================================*/
/* Index: idx_role_menu_menuid                                  */
/*==============================================================*/
create index idx_role_menu_menuid on t_sys_role_menu
    (
     menu_id
        );

/*==============================================================*/
/* Index: idx_role_menu_moduleid                                */
/*==============================================================*/
create index idx_role_menu_moduleid on t_sys_role_menu
    (
     menu_module_id
        );

/*==============================================================*/
/* Index: idx_role_menu_permid                                  */
/*==============================================================*/
create index idx_role_menu_permid on t_sys_role_menu
    (
     perm_id
        );

/*==============================================================*/
/* Index: idx_user_number                                       */
/*==============================================================*/
create unique index idx_user_number on t_sys_user
    (
     number
        );

/*==============================================================*/
/* Index: idx_user_phone                                        */
/*==============================================================*/
create unique index idx_user_phone on t_sys_user
    (
     phone
        );

/*==============================================================*/
/* Index: idx_user_orgid                                        */
/*==============================================================*/
create index idx_user_orgid on t_sys_user
    (
     org_id
        );

/*==============================================================*/
/* Index: idx_user_login                                        */
/*==============================================================*/
create index idx_user_login on t_sys_user_login
    (
     login_name,
     password
        );

/*==============================================================*/
/* Index: idx_ulc_loginid                                       */
/*==============================================================*/
create index idx_ulc_loginid on t_sys_user_login_record
    (
     user_login_id
        );

/*==============================================================*/
/* Index: idx_ulc_userid                                        */
/*==============================================================*/
create index idx_ulc_userid on t_sys_user_login_record
    (
     user_id
        );

/*==============================================================*/
/* Index: idx_user_relate                                       */
/*==============================================================*/
create index idx_user_relate on t_sys_user_relate
    (
     relate_id,
     relate_type,
     user_id
        );

/*==============================================================*/
/* Index: idx_user_role_roleid                                  */
/*==============================================================*/
create index idx_user_role_roleid on t_sys_user_role
    (
     role_id
        );

/*==============================================================*/
/* Index: idx_user_role_userid                                  */
/*==============================================================*/
create index idx_user_role_userid on t_sys_user_role
    (
     user_id
        );
