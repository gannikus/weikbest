drop index idx_ccmm_ca_custid on t_ccmm_cust_address;

drop index idx_ccmm_cc_businessid on t_ccmm_cust_comment;

drop index idx_ccmm_cc_customerid on t_ccmm_cust_comment;

drop index idx_scr_shopid on t_ccmm_cust_coupon_restrict;

drop index idx_scr_customerid on t_ccmm_cust_coupon_restrict;

drop index idx_scr_shopcouponid on t_ccmm_cust_coupon_restrict;

drop index idx_complaint_shopid on t_ccmm_cust_shop_attent;

drop index idx_complaint_businessid on t_ccmm_cust_shop_attent;

drop index idx_complaint_customerid on t_ccmm_cust_shop_attent;

drop index idx_ccmm_customer_wxopenid_unique on t_ccmm_customer;

drop index idx_ccmm_customer_unique on t_ccmm_customer;

drop index idx_ccmm_customer_number on t_ccmm_customer;

/*==============================================================*/
/* Index: idx_ccmm_ca_custid                                    */
/*==============================================================*/
create index idx_ccmm_ca_custid on t_ccmm_cust_address
    (
     customer_id
        );

/*==============================================================*/
/* Index: idx_ccmm_cc_customerid                                */
/*==============================================================*/
create index idx_ccmm_cc_customerid on t_ccmm_cust_comment
    (
     customer_id
        );

/*==============================================================*/
/* Index: idx_ccmm_cc_businessid                                */
/*==============================================================*/
create index idx_ccmm_cc_businessid on t_ccmm_cust_comment
    (
     business_id
        );

/*==============================================================*/
/* Index: idx_scr_shopcouponid                                  */
/*==============================================================*/
create index idx_scr_shopcouponid on t_ccmm_cust_coupon_restrict
    (
     coupon_id
        );

/*==============================================================*/
/* Index: idx_scr_customerid                                    */
/*==============================================================*/
create index idx_scr_customerid on t_ccmm_cust_coupon_restrict
    (
     customer_id
        );

/*==============================================================*/
/* Index: idx_scr_shopid                                        */
/*==============================================================*/
create index idx_scr_shopid on t_ccmm_cust_coupon_restrict
    (
     shop_id
        );

/*==============================================================*/
/* Index: idx_complaint_customerid                              */
/*==============================================================*/
create index idx_complaint_customerid on t_ccmm_cust_shop_attent
    (
     customer_id
        );

/*==============================================================*/
/* Index: idx_complaint_businessid                              */
/*==============================================================*/
create index idx_complaint_businessid on t_ccmm_cust_shop_attent
    (
     business_id
        );

/*==============================================================*/
/* Index: idx_complaint_shopid                                  */
/*==============================================================*/
create index idx_complaint_shopid on t_ccmm_cust_shop_attent
    (
     shop_id
        );

/*==============================================================*/
/* Index: idx_ccmm_customer_number                              */
/*==============================================================*/
create index idx_ccmm_customer_number on t_ccmm_customer
    (
     number
        );

/*==============================================================*/
/* Index: idx_ccmm_customer_unique                              */
/*==============================================================*/
create unique index idx_ccmm_customer_unique on t_ccmm_customer
    (
     user_unique,
     cust_third_type
        );

/*==============================================================*/
/* Index: idx_ccmm_customer_wxopenid_unique                     */
/*==============================================================*/
create unique index idx_ccmm_customer_wxopenid_unique on t_ccmm_customer
    (
     wx_openid
        );
