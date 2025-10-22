drop index idx_mmdm_pcar_id on t_mmdm_applet_dec_config_entry;

drop index idx_mmdm_pcar_prodid on t_mmdm_applet_dec_config_entry;

drop index idx_mmdm_apc_pspid on t_mmdm_applet_prod_category;

drop index idx_mmdm_pc_apcid on t_mmdm_applet_prod_category_relate;

drop index idx_mmdm_pc_prodid on t_mmdm_applet_prod_category_relate;

drop index idx_mmdm_ft_businessid on t_mmdm_feight_template;

drop index idx_mmdm_ftd_ftid on t_mmdm_feight_template_detail;

drop index idx_mmdm_ftr_ftid on t_mmdm_feight_template_region;

drop index idx_mmdm_prod_shopid on t_mmdm_prod;

drop index idx_mmdm_prod_busiid on t_mmdm_prod;

drop index idx_paba_id on t_mmdm_prod_adv_back_account;

drop index idx_paba_account_id on t_mmdm_prod_adv_back_account;

drop index idx_mmdm_pa_number_unique on t_mmdm_prod_attr;

drop index idx_mmdm_pca_paid on t_mmdm_prod_attr_relate;

drop index idx_mmdm_pav_attrid on t_mmdm_prod_attr_val;

drop index idx_mmdm_pba_busiaddrid on t_mmdm_prod_busi_addr;

drop index idx_mmdm_pba_prodid on t_mmdm_prod_busi_addr;

drop index idx_prod_category_pid on t_mmdm_prod_category;

drop index idx_mmdm_prod_combo_prodid on t_mmdm_prod_combo;

drop index idx_mmdm_prod_car_comboid on t_mmdm_prod_combo_attr_relate;

drop index idx_mmdm_prod_car_prodid on t_mmdm_prod_combo_attr_relate;

drop index idx_prod_coupon_prodid on t_mmdm_prod_coupon;

drop index idx_mmdm_pcr_couponid on t_mmdm_prod_coupon_relate;

drop index idx_mmdm_pcr_prodid on t_mmdm_prod_coupon_relate;

drop index idx_mmdm_prod_detail_prodid on t_mmdm_prod_detail;

drop index idx_mmdm_pf_templateid on t_mmdm_prod_feight;

drop index idx_pjl_id on t_mmdm_prod_jump_link;

drop index idx_prod_mainimg_prodid on t_mmdm_prod_mainimg;

drop index idx_prod_sku_id on t_mmdm_prod_price;

drop index idx_mmdm_prod_sku_stockid on t_mmdm_prod_sku;

drop index idx_mmdm_prod_sku_prodid on t_mmdm_prod_sku;

drop index idx_prod_stock_prodid on t_mmdm_prod_stock;

/*==============================================================*/
/* Index: idx_mmdm_pcar_prodid                                  */
/*==============================================================*/
create index idx_mmdm_pcar_prodid on t_mmdm_applet_dec_config_entry
    (
     prod_id
        );

/*==============================================================*/
/* Index: idx_mmdm_pcar_id                                      */
/*==============================================================*/
create index idx_mmdm_pcar_id on t_mmdm_applet_dec_config_entry
    (
     id
        );

/*==============================================================*/
/* Index: idx_mmdm_apc_pspid                                    */
/*==============================================================*/
create index idx_mmdm_apc_pspid on t_mmdm_applet_prod_category
    (
     parent_id
        );

/*==============================================================*/
/* Index: idx_mmdm_pc_prodid                                    */
/*==============================================================*/
create index idx_mmdm_pc_prodid on t_mmdm_applet_prod_category_relate
    (
     prod_id
        );

/*==============================================================*/
/* Index: idx_mmdm_pc_apcid                                     */
/*==============================================================*/
create index idx_mmdm_pc_apcid on t_mmdm_applet_prod_category_relate
    (
     applet_prod_categoty_id
        );

/*==============================================================*/
/* Index: idx_mmdm_ft_businessid                                */
/*==============================================================*/
create index idx_mmdm_ft_businessid on t_mmdm_feight_template
    (
     business_id
        );

/*==============================================================*/
/* Index: idx_mmdm_ftd_ftid                                     */
/*==============================================================*/
create index idx_mmdm_ftd_ftid on t_mmdm_feight_template_detail
    (
     id
        );

/*==============================================================*/
/* Index: idx_mmdm_ftr_ftid                                     */
/*==============================================================*/
create index idx_mmdm_ftr_ftid on t_mmdm_feight_template_region
    (
     id,
     entry_id
        );

/*==============================================================*/
/* Index: idx_mmdm_prod_busiid                                  */
/*==============================================================*/
create index idx_mmdm_prod_busiid on t_mmdm_prod
    (
     business_id
        );

/*==============================================================*/
/* Index: idx_mmdm_prod_shopid                                  */
/*==============================================================*/
create index idx_mmdm_prod_shopid on t_mmdm_prod
    (
     shop_id
        );

/*==============================================================*/
/* Index: idx_paba_account_id                                   */
/*==============================================================*/
create index idx_paba_account_id on t_mmdm_prod_adv_back_account
    (
     adv_account_id
        );

/*==============================================================*/
/* Index: idx_paba_id                                           */
/*==============================================================*/
create index idx_paba_id on t_mmdm_prod_adv_back_account
    (
     id
        );

/*==============================================================*/
/* Index: idx_mmdm_pa_number_unique                             */
/*==============================================================*/
create unique index idx_mmdm_pa_number_unique on t_mmdm_prod_attr
    (
     number
        );

/*==============================================================*/
/* Index: idx_mmdm_pca_paid                                     */
/*==============================================================*/
create index idx_mmdm_pca_paid on t_mmdm_prod_attr_relate
    (
     prod_id,
     attr_id
        );

/*==============================================================*/
/* Index: idx_mmdm_pav_attrid                                   */
/*==============================================================*/
create index idx_mmdm_pav_attrid on t_mmdm_prod_attr_val
    (
     attr_id
        );

/*==============================================================*/
/* Index: idx_mmdm_pba_prodid                                   */
/*==============================================================*/
create index idx_mmdm_pba_prodid on t_mmdm_prod_busi_addr
    (
     prod_id
        );

/*==============================================================*/
/* Index: idx_mmdm_pba_busiaddrid                               */
/*==============================================================*/
create index idx_mmdm_pba_busiaddrid on t_mmdm_prod_busi_addr
    (
     busi_addr_id
        );

/*==============================================================*/
/* Index: idx_prod_category_pid                                 */
/*==============================================================*/
create index idx_prod_category_pid on t_mmdm_prod_category
    (
     parent_id
        );

/*==============================================================*/
/* Index: idx_mmdm_prod_combo_prodid                            */
/*==============================================================*/
create index idx_mmdm_prod_combo_prodid on t_mmdm_prod_combo
    (
     prod_id
        );

/*==============================================================*/
/* Index: idx_mmdm_prod_car_prodid                              */
/*==============================================================*/
create index idx_mmdm_prod_car_prodid on t_mmdm_prod_combo_attr_relate
    (
     prod_id
        );

/*==============================================================*/
/* Index: idx_mmdm_prod_car_comboid                             */
/*==============================================================*/
create index idx_mmdm_prod_car_comboid on t_mmdm_prod_combo_attr_relate
    (
     prod_combo_id
        );

/*==============================================================*/
/* Index: idx_prod_coupon_prodid                                */
/*==============================================================*/
create index idx_prod_coupon_prodid on t_mmdm_prod_coupon
    (
     id
        );

/*==============================================================*/
/* Index: idx_mmdm_pcr_prodid                                   */
/*==============================================================*/
create index idx_mmdm_pcr_prodid on t_mmdm_prod_coupon_relate
    (
     id
        );

/*==============================================================*/
/* Index: idx_mmdm_pcr_couponid                                 */
/*==============================================================*/
create index idx_mmdm_pcr_couponid on t_mmdm_prod_coupon_relate
    (
     coupon_id
        );

/*==============================================================*/
/* Index: idx_mmdm_prod_detail_prodid                           */
/*==============================================================*/
create index idx_mmdm_prod_detail_prodid on t_mmdm_prod_detail
    (
     id
        );

/*==============================================================*/
/* Index: idx_mmdm_pf_templateid                                */
/*==============================================================*/
create index idx_mmdm_pf_templateid on t_mmdm_prod_feight
    (
     feight_template_id
        );

/*==============================================================*/
/* Index: idx_pjl_id                                            */
/*==============================================================*/
create index idx_pjl_id on t_mmdm_prod_jump_link
    (
     id
        );

/*==============================================================*/
/* Index: idx_prod_mainimg_prodid                               */
/*==============================================================*/
create index idx_prod_mainimg_prodid on t_mmdm_prod_mainimg
    (
     id
        );

/*==============================================================*/
/* Index: idx_prod_sku_id                                       */
/*==============================================================*/
create index idx_prod_sku_id on t_mmdm_prod_price
    (
     prod_sku_id
        );

/*==============================================================*/
/* Index: idx_mmdm_prod_sku_prodid                              */
/*==============================================================*/
create index idx_mmdm_prod_sku_prodid on t_mmdm_prod_sku
    (
     prod_id
        );

/*==============================================================*/
/* Index: idx_mmdm_prod_sku_stockid                             */
/*==============================================================*/
create index idx_mmdm_prod_sku_stockid on t_mmdm_prod_sku
    (
     prod_stock_id
        );

/*==============================================================*/
/* Index: idx_prod_stock_prodid                                 */
/*==============================================================*/
create index idx_prod_stock_prodid on t_mmdm_prod_stock
    (
     prod_id
        );
