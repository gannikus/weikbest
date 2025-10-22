drop index idx_applet_config_wxappid on t_sys_applet_config;

drop index idx_applet_sms_config_smstempid on t_sys_applet_sms_config;

drop index idx_applet_subconfig_appid on t_sys_applet_subscribe_config;

drop index idx_applet_subconfig_number on t_sys_applet_subscribe_config;

drop index idx_applet_sub_record_sconfigid on t_sys_applet_subscribe_record;

drop index idx_code_rule_unique on t_sys_code_rule;

drop index idx_config_unique on t_sys_config;

drop index idx_delay_task_config_number_unique on t_sys_delay_task_config;

drop index idx_delay_key on t_sys_delay_task_record;

drop index idx_dict_unique on t_sys_dict;

drop index idx_dict_type_unique on t_sys_dict_type;

drop index idx_excel_template_number_unique on t_sys_excel_template;

drop index idx_log_traceid on t_sys_log;

drop index idx_log_userid on t_sys_log;

drop index idx_logistics_company_namer_unique on t_sys_logistics_company;

drop index idx_logistics_company_number_unique on t_sys_logistics_company;

drop index idx_region_parentid on t_sys_region;

drop index idx_sms_record_smid on t_sys_sms_record;

drop index idx_sms_template_unique on t_sys_sms_template;

drop index idx_tencnet_adv_client_id on t_sys_tencent_adv_config;

drop index idx_tencnet_adv_scope_client_id on t_sys_tencent_adv_scope_config;

drop index idx_tencent_adv_usersource_order_id on t_sys_tencent_adv_userrecord;

drop index idx_tencent_adv_usersource_client_id on t_sys_tencent_adv_userrecord;

drop index idx_tencent_adv_usersource_client_id on t_sys_tencent_adv_usersource;

/*==============================================================*/
/* Index: idx_applet_config_wxappid                             */
/*==============================================================*/
create index idx_applet_config_wxappid on t_sys_applet_config
    (
     wx_miniapp_app_id
        );

/*==============================================================*/
/* Index: idx_applet_sms_config_smstempid                       */
/*==============================================================*/
create index idx_applet_sms_config_smstempid on t_sys_applet_sms_config
    (
     sms_template_id
        );

/*==============================================================*/
/* Index: idx_applet_subconfig_number                           */
/*==============================================================*/
create index idx_applet_subconfig_number on t_sys_applet_subscribe_config
    (
     number
        );

/*==============================================================*/
/* Index: idx_applet_subconfig_appid                            */
/*==============================================================*/
create index idx_applet_subconfig_appid on t_sys_applet_subscribe_config
    (
     app_id
        );

/*==============================================================*/
/* Index: idx_applet_sub_record_sconfigid                       */
/*==============================================================*/
create index idx_applet_sub_record_sconfigid on t_sys_applet_subscribe_record
    (
     subscribe_config_id
        );

/*==============================================================*/
/* Index: idx_code_rule_unique                                  */
/*==============================================================*/
create unique index idx_code_rule_unique on t_sys_code_rule
    (
     number
        );

/*==============================================================*/
/* Index: idx_config_unique                                     */
/*==============================================================*/
create unique index idx_config_unique on t_sys_config
    (
     number
        );

/*==============================================================*/
/* Index: idx_delay_task_config_number_unique                   */
/*==============================================================*/
create unique index idx_delay_task_config_number_unique on t_sys_delay_task_config
    (
     number
        );

/*==============================================================*/
/* Index: idx_delay_key                                         */
/*==============================================================*/
create index idx_delay_key on t_sys_delay_task_record
    (
     delay_task
        );

/*==============================================================*/
/* Index: idx_dict_unique                                       */
/*==============================================================*/
create unique index idx_dict_unique on t_sys_dict
    (
     number,
     dict_type_id
        );

/*==============================================================*/
/* Index: idx_dict_type_unique                                  */
/*==============================================================*/
create unique index idx_dict_type_unique on t_sys_dict_type
    (
     number
        );

/*==============================================================*/
/* Index: idx_excel_template_number_unique                      */
/*==============================================================*/
create unique index idx_excel_template_number_unique on t_sys_excel_template
    (
     number
        );

/*==============================================================*/
/* Index: idx_log_userid                                        */
/*==============================================================*/
create index idx_log_userid on t_sys_log
    (
     user_id
        );

/*==============================================================*/
/* Index: idx_log_traceid                                       */
/*==============================================================*/
create index idx_log_traceid on t_sys_log
    (
     trace_id
        );

/*==============================================================*/
/* Index: idx_logistics_company_number_unique                   */
/*==============================================================*/
create unique index idx_logistics_company_number_unique on t_sys_logistics_company
    (
     number
        );

/*==============================================================*/
/* Index: idx_logistics_company_namer_unique                    */
/*==============================================================*/
create unique index idx_logistics_company_namer_unique on t_sys_logistics_company
    (
     name
        );

/*==============================================================*/
/* Index: idx_region_parentid                                   */
/*==============================================================*/
create index idx_region_parentid on t_sys_region
    (
     parent_adcode
        );

/*==============================================================*/
/* Index: idx_sms_record_smid                                   */
/*==============================================================*/
create index idx_sms_record_smid on t_sys_sms_record
    (
     sms_template_id
        );

/*==============================================================*/
/* Index: idx_sms_template_unique                               */
/*==============================================================*/
create unique index idx_sms_template_unique on t_sys_sms_template
    (
     number
        );

/*==============================================================*/
/* Index: idx_tencnet_adv_client_id                             */
/*==============================================================*/
create index idx_tencnet_adv_client_id on t_sys_tencent_adv_config
    (
     client_id
        );

/*==============================================================*/
/* Index: idx_tencnet_adv_scope_client_id                       */
/*==============================================================*/
create index idx_tencnet_adv_scope_client_id on t_sys_tencent_adv_scope_config
    (
     client_id
        );

/*==============================================================*/
/* Index: idx_tencent_adv_usersource_client_id                  */
/*==============================================================*/
create index idx_tencent_adv_usersource_client_id on t_sys_tencent_adv_userrecord
    (
     ad_aid
        );

/*==============================================================*/
/* Index: idx_tencent_adv_usersource_order_id                   */
/*==============================================================*/
create index idx_tencent_adv_usersource_order_id on t_sys_tencent_adv_userrecord
    (
     order_id
        );

/*==============================================================*/
/* Index: idx_tencent_adv_usersource_client_id                  */
/*==============================================================*/
create index idx_tencent_adv_usersource_client_id on t_sys_tencent_adv_usersource
    (
     client_id
        );
