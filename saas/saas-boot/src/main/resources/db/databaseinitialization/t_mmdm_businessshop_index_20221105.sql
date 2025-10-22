drop index idx_busi_addr_busiid on t_mmdm_busi_address;

drop index idx_mmdm_busi_user_phone on t_mmdm_busi_user;

drop index idx_mmdm_busi_user_busiid on t_mmdm_busi_user;

drop index idx_mmdm_business_unique on t_mmdm_business;

drop index ids_mmdm_shop_busid on t_mmdm_shop;

drop index idx_mmdm_shop_unique on t_mmdm_shop;

drop index idx_sfc_shopid on t_mmdm_shop_finance_account;

drop index idx_sfd_shopfaid on t_mmdm_shop_finance_detail;

drop index idx_mmdm_snr_shopid on t_mmdm_shop_non_region;

drop index idx_user_role_busiid on t_mmdm_shop_role;

drop index idx_user_role_shopid on t_mmdm_shop_role;

drop index idx_std_orderid on t_mmdm_shop_statement_detail;

drop index idx_std_shopid on t_mmdm_shop_statement_detail;

drop index idx_shop_third_rec_id on t_mmdm_shop_third_receive;

drop index idx_mmdm_shop_user_userid on t_mmdm_shop_user;

drop index idx_mmdm_shop_user_shopid on t_mmdm_shop_user;

drop index ids_mmdm_shop_user_busid on t_mmdm_shop_user;

drop index idx_user_role_busiuserid on t_mmdm_shop_user_role;

drop index idx_user_role_shopuserid on t_mmdm_shop_user_role;

drop index idx_user_role_roleid on t_mmdm_shop_user_role;

drop index idx_sv_shop_customer_id on t_mmdm_shop_visit;

/*==============================================================*/
/* Index: idx_busi_addr_busiid                                  */
/*==============================================================*/
create index idx_busi_addr_busiid on t_mmdm_busi_address
    (
     business_id
        );

/*==============================================================*/
/* Index: idx_mmdm_busi_user_busiid                             */
/*==============================================================*/
create index idx_mmdm_busi_user_busiid on t_mmdm_busi_user
    (
     business_id
        );

/*==============================================================*/
/* Index: idx_mmdm_busi_user_phone                              */
/*==============================================================*/
create index idx_mmdm_busi_user_phone on t_mmdm_busi_user
    (
     phone
        );

/*==============================================================*/
/* Index: idx_mmdm_business_unique                              */
/*==============================================================*/
create unique index idx_mmdm_business_unique on t_mmdm_business
    (
     number
        );

/*==============================================================*/
/* Index: idx_mmdm_shop_unique                                  */
/*==============================================================*/
create unique index idx_mmdm_shop_unique on t_mmdm_shop
    (
     number
        );

/*==============================================================*/
/* Index: ids_mmdm_shop_busid                                   */
/*==============================================================*/
create index ids_mmdm_shop_busid on t_mmdm_shop
    (
     business_id
        );

/*==============================================================*/
/* Index: idx_sfc_shopid                                        */
/*==============================================================*/
create index idx_sfc_shopid on t_mmdm_shop_finance_account
    (
     shop_id
        );

/*==============================================================*/
/* Index: idx_sfd_shopfaid                                      */
/*==============================================================*/
create index idx_sfd_shopfaid on t_mmdm_shop_finance_detail
    (
     shop_id,
     finance_account_id
        );

/*==============================================================*/
/* Index: idx_mmdm_snr_shopid                                   */
/*==============================================================*/
create index idx_mmdm_snr_shopid on t_mmdm_shop_non_region
    (
     shop_id
        );

/*==============================================================*/
/* Index: idx_user_role_shopid                                  */
/*==============================================================*/
create index idx_user_role_shopid on t_mmdm_shop_role
    (
     shop_id
        );

/*==============================================================*/
/* Index: idx_user_role_busiid                                  */
/*==============================================================*/
create index idx_user_role_busiid on t_mmdm_shop_role
    (
     business_id
        );

/*==============================================================*/
/* Index: idx_std_shopid                                        */
/*==============================================================*/
create index idx_std_shopid on t_mmdm_shop_statement_detail
    (
     shop_id
        );

/*==============================================================*/
/* Index: idx_std_orderid                                       */
/*==============================================================*/
create index idx_std_orderid on t_mmdm_shop_statement_detail
    (

        );

/*==============================================================*/
/* Index: idx_shop_third_rec_id                                 */
/*==============================================================*/
create index idx_shop_third_rec_id on t_mmdm_shop_third_receive
    (
     id
        );

/*==============================================================*/
/* Index: ids_mmdm_shop_user_busid                              */
/*==============================================================*/
create index ids_mmdm_shop_user_busid on t_mmdm_shop_user
    (
     business_id
        );

/*==============================================================*/
/* Index: idx_mmdm_shop_user_shopid                             */
/*==============================================================*/
create index idx_mmdm_shop_user_shopid on t_mmdm_shop_user
    (
     shop_id
        );

/*==============================================================*/
/* Index: idx_mmdm_shop_user_userid                             */
/*==============================================================*/
create index idx_mmdm_shop_user_userid on t_mmdm_shop_user
    (
     business_user_id
        );

/*==============================================================*/
/* Index: idx_user_role_roleid                                  */
/*==============================================================*/
create index idx_user_role_roleid on t_mmdm_shop_user_role
    (
     role_id
        );

/*==============================================================*/
/* Index: idx_user_role_shopuserid                              */
/*==============================================================*/
create index idx_user_role_shopuserid on t_mmdm_shop_user_role
    (
     shop_user_id
        );

/*==============================================================*/
/* Index: idx_user_role_busiuserid                              */
/*==============================================================*/
create index idx_user_role_busiuserid on t_mmdm_shop_user_role
    (
     business_user_id
        );

/*==============================================================*/
/* Index: idx_sv_shop_customer_id                               */
/*==============================================================*/
create index idx_sv_shop_customer_id on t_mmdm_shop_visit
    (
     shop_id,
     customer_id
        );
