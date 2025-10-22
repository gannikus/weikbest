drop index idx_mmdm_outrequestno on t_mmdm_coupon;

drop index idx_mmdm_shopid on t_mmdm_coupon;

drop index idx_mmdm_pcr_couponid on t_mmdm_coupon_customer_entry;

drop index idx_mmdm_pcr_customerid on t_mmdm_coupon_customer_entry;

drop index idx_mmdm_pcr_couponid on t_mmdm_coupon_prod_entry;

drop index idx_mmdm_pcr_prodid on t_mmdm_coupon_prod_entry;

drop index idx_coupon_scene_config_id on t_mmdm_coupon_scene;

drop index idx_coupon_scene_id on t_mmdm_coupon_scene;

/*==============================================================*/
/* Index: idx_mmdm_shopid                                       */
/*==============================================================*/
create index idx_mmdm_shopid on t_mmdm_coupon
    (
     shop_id
        );

/*==============================================================*/
/* Index: idx_mmdm_outrequestno                                 */
/*==============================================================*/
create unique index idx_mmdm_outrequestno on t_mmdm_coupon
    (
     out_request_no
        );

/*==============================================================*/
/* Index: idx_mmdm_pcr_customerid                               */
/*==============================================================*/
create index idx_mmdm_pcr_customerid on t_mmdm_coupon_customer_entry
    (
     customer_id
        );

/*==============================================================*/
/* Index: idx_mmdm_pcr_couponid                                 */
/*==============================================================*/
create index idx_mmdm_pcr_couponid on t_mmdm_coupon_customer_entry
    (
     id
        );

/*==============================================================*/
/* Index: idx_mmdm_pcr_prodid                                   */
/*==============================================================*/
create index idx_mmdm_pcr_prodid on t_mmdm_coupon_prod_entry
    (
     prod_id
        );

/*==============================================================*/
/* Index: idx_mmdm_pcr_couponid                                 */
/*==============================================================*/
create index idx_mmdm_pcr_couponid on t_mmdm_coupon_prod_entry
    (
     id
        );

/*==============================================================*/
/* Index: idx_coupon_scene_id                                   */
/*==============================================================*/
create index idx_coupon_scene_id on t_mmdm_coupon_scene
    (
     coupon_id
        );

/*==============================================================*/
/* Index: idx_coupon_scene_config_id                            */
/*==============================================================*/
create index idx_coupon_scene_config_id on t_mmdm_coupon_scene
    (
     id
        );
