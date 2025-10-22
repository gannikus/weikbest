drop index idx_mmdm_oas_customerid on t_mmdm_order_after_sale;

drop index idx_mmdm_oas_appletid on t_mmdm_order_after_sale;

drop index idx_mmdm_oas_orderid on t_mmdm_order_after_sale;

drop index idx_mmdm_oascr_appletid on t_mmdm_order_after_sale_consult_record;

drop index idx_mmdm_oascr_customerid on t_mmdm_order_after_sale_consult_record;

drop index idx_mmdm_oascr_orderid on t_mmdm_order_after_sale_consult_record;

drop index idx_mmdm_oascr_id on t_mmdm_order_after_sale_consult_record;

drop index idx_mmdm_oasi_id on t_mmdm_order_after_sale_img;

drop index idx_mmdm_oasih_hisid on t_mmdm_order_after_sale_img_his;

drop index idx_mmdm_oasih_id on t_mmdm_order_after_sale_img_his;

drop index idx_mmdm_oasli_id on t_mmdm_order_after_sale_logistics_img;

drop index idx_mmdm_oaslih_hisid on t_mmdm_order_after_sale_logistics_img_his;

drop index idx_mmdm_oaslih_id on t_mmdm_order_after_sale_logistics_img_his;

drop index idx_order_batch_deliver_operator on t_mmdm_order_batch_deliver;

drop index idx_order_batch_deliver_record_ordernum on t_mmdm_order_batch_deliver_record;

drop index idx_mmdm_oc_ordersaleid on t_mmdm_order_complaint;

drop index idx_mmdm_oc_orderid on t_mmdm_order_complaint;

drop index idx_mmdm_oci_id on t_mmdm_order_complaint_img;

drop index idx_mmdm_ocli_id on t_mmdm_order_complaint_img_his;

drop index idx_mmdm_ocr_ordersaleid on t_mmdm_order_complaint_record;

drop index idx_mmdm_ocr_orderid on t_mmdm_order_complaint_record;

drop index idx_mmdm_oci_custid on t_mmdm_order_cust_info;

drop index idx_mmdm_order_info_appletid on t_mmdm_order_info;

drop index idx_mmdm_order_info_custid on t_mmdm_order_info;

drop index idx_mmdm_order_info_busiid on t_mmdm_order_info;

drop index idx_mmdm_order_info_unique on t_mmdm_order_info;

drop index idx_order_logistics_batchdeliverid on t_mmdm_order_logistics;

drop index idx_order_logistics_orderid on t_mmdm_order_logistics;

drop index idx_mmdm_oli_id on t_mmdm_order_logistics_img;

drop index idx_mmdm_opc_orderid on t_mmdm_order_pay_record;

drop index idx_mmdm_opi_prodcomboid on t_mmdm_order_prod_info;

drop index idx_mmdm_opi_prodskuid on t_mmdm_order_prod_info;

drop index idx_mmdm_opi_prodid on t_mmdm_order_prod_info;

drop index idx_mmdm_ora_custid on t_mmdm_order_rec_addr;

drop index idx_mmdm_order_source_scale on t_mmdm_order_source_scale;

drop index idx_mmdm_osr_orderid on t_mmdm_order_stat_record;

/*==============================================================*/
/* Index: idx_mmdm_oas_orderid                                  */
/*==============================================================*/
create index idx_mmdm_oas_orderid on t_mmdm_order_after_sale
    (
     order_id
        );

/*==============================================================*/
/* Index: idx_mmdm_oas_appletid                                 */
/*==============================================================*/
create index idx_mmdm_oas_appletid on t_mmdm_order_after_sale
    (

        );

/*==============================================================*/
/* Index: idx_mmdm_oas_customerid                               */
/*==============================================================*/
create index idx_mmdm_oas_customerid on t_mmdm_order_after_sale
    (
     customer_id
        );

/*==============================================================*/
/* Index: idx_mmdm_oascr_id                                     */
/*==============================================================*/
create index idx_mmdm_oascr_id on t_mmdm_order_after_sale_consult_record
    (
     id
        );

/*==============================================================*/
/* Index: idx_mmdm_oascr_orderid                                */
/*==============================================================*/
create index idx_mmdm_oascr_orderid on t_mmdm_order_after_sale_consult_record
    (
     order_id
        );

/*==============================================================*/
/* Index: idx_mmdm_oascr_customerid                             */
/*==============================================================*/
create index idx_mmdm_oascr_customerid on t_mmdm_order_after_sale_consult_record
    (
     customer_id
        );

/*==============================================================*/
/* Index: idx_mmdm_oascr_appletid                               */
/*==============================================================*/
create index idx_mmdm_oascr_appletid on t_mmdm_order_after_sale_consult_record
    (

        );

/*==============================================================*/
/* Index: idx_mmdm_oasi_id                                      */
/*==============================================================*/
create index idx_mmdm_oasi_id on t_mmdm_order_after_sale_img
    (
     id
        );

/*==============================================================*/
/* Index: idx_mmdm_oasih_id                                     */
/*==============================================================*/
create index idx_mmdm_oasih_id on t_mmdm_order_after_sale_img_his
    (
     id
        );

/*==============================================================*/
/* Index: idx_mmdm_oasih_hisid                                  */
/*==============================================================*/
create index idx_mmdm_oasih_hisid on t_mmdm_order_after_sale_img_his
    (
     history_id
        );

/*==============================================================*/
/* Index: idx_mmdm_oasli_id                                     */
/*==============================================================*/
create index idx_mmdm_oasli_id on t_mmdm_order_after_sale_logistics_img
    (
     id
        );

/*==============================================================*/
/* Index: idx_mmdm_oaslih_id                                    */
/*==============================================================*/
create index idx_mmdm_oaslih_id on t_mmdm_order_after_sale_logistics_img_his
    (
     id
        );

/*==============================================================*/
/* Index: idx_mmdm_oaslih_hisid                                 */
/*==============================================================*/
create index idx_mmdm_oaslih_hisid on t_mmdm_order_after_sale_logistics_img_his
    (
     history_id
        );

/*==============================================================*/
/* Index: idx_order_batch_deliver_operator                      */
/*==============================================================*/
create index idx_order_batch_deliver_operator on t_mmdm_order_batch_deliver
    (
     operator
        );

/*==============================================================*/
/* Index: idx_order_batch_deliver_record_ordernum               */
/*==============================================================*/
create index idx_order_batch_deliver_record_ordernum on t_mmdm_order_batch_deliver_record
    (
     order_number
        );

/*==============================================================*/
/* Index: idx_mmdm_oc_orderid                                   */
/*==============================================================*/
create index idx_mmdm_oc_orderid on t_mmdm_order_complaint
    (
     order_id
        );

/*==============================================================*/
/* Index: idx_mmdm_oc_ordersaleid                               */
/*==============================================================*/
create index idx_mmdm_oc_ordersaleid on t_mmdm_order_complaint
    (
     order_after_sale_id
        );

/*==============================================================*/
/* Index: idx_mmdm_oci_id                                       */
/*==============================================================*/
create index idx_mmdm_oci_id on t_mmdm_order_complaint_img
    (
     id
        );

/*==============================================================*/
/* Index: idx_mmdm_ocli_id                                      */
/*==============================================================*/
create index idx_mmdm_ocli_id on t_mmdm_order_complaint_img_his
    (
     id
        );

/*==============================================================*/
/* Index: idx_mmdm_ocr_orderid                                  */
/*==============================================================*/
create index idx_mmdm_ocr_orderid on t_mmdm_order_complaint_record
    (
     order_id
        );

/*==============================================================*/
/* Index: idx_mmdm_ocr_ordersaleid                              */
/*==============================================================*/
create index idx_mmdm_ocr_ordersaleid on t_mmdm_order_complaint_record
    (
     order_after_sale_id
        );

/*==============================================================*/
/* Index: idx_mmdm_oci_custid                                   */
/*==============================================================*/
create index idx_mmdm_oci_custid on t_mmdm_order_cust_info
    (
     customer_id
        );

/*==============================================================*/
/* Index: idx_mmdm_order_info_unique                            */
/*==============================================================*/
create unique index idx_mmdm_order_info_unique on t_mmdm_order_info
    (
     number
        );

/*==============================================================*/
/* Index: idx_mmdm_order_info_busiid                            */
/*==============================================================*/
create index idx_mmdm_order_info_busiid on t_mmdm_order_info
    (
     business_id,
     shop_id
        );

/*==============================================================*/
/* Index: idx_mmdm_order_info_custid                            */
/*==============================================================*/
create index idx_mmdm_order_info_custid on t_mmdm_order_info
    (
     customer_id
        );

/*==============================================================*/
/* Index: idx_mmdm_order_info_appletid                          */
/*==============================================================*/
create index idx_mmdm_order_info_appletid on t_mmdm_order_info
    (
     app_id
        );

/*==============================================================*/
/* Index: idx_order_logistics_orderid                           */
/*==============================================================*/
create index idx_order_logistics_orderid on t_mmdm_order_logistics
    (
     order_id
        );

/*==============================================================*/
/* Index: idx_order_logistics_batchdeliverid                    */
/*==============================================================*/
create index idx_order_logistics_batchdeliverid on t_mmdm_order_logistics
    (
     batch_deliver_id
        );

/*==============================================================*/
/* Index: idx_mmdm_oli_id                                       */
/*==============================================================*/
create index idx_mmdm_oli_id on t_mmdm_order_logistics_img
    (
     id
        );

/*==============================================================*/
/* Index: idx_mmdm_opc_orderid                                  */
/*==============================================================*/
create index idx_mmdm_opc_orderid on t_mmdm_order_pay_record
    (
     order_id
        );

/*==============================================================*/
/* Index: idx_mmdm_opi_prodid                                   */
/*==============================================================*/
create index idx_mmdm_opi_prodid on t_mmdm_order_prod_info
    (
     prod_id
        );

/*==============================================================*/
/* Index: idx_mmdm_opi_prodskuid                                */
/*==============================================================*/
create index idx_mmdm_opi_prodskuid on t_mmdm_order_prod_info
    (
     prod_sku_id
        );

/*==============================================================*/
/* Index: idx_mmdm_opi_prodcomboid                              */
/*==============================================================*/
create index idx_mmdm_opi_prodcomboid on t_mmdm_order_prod_info
    (
     prod_combo_id
        );

/*==============================================================*/
/* Index: idx_mmdm_ora_custid                                   */
/*==============================================================*/
create index idx_mmdm_ora_custid on t_mmdm_order_rec_addr
    (
     customer_id
        );

/*==============================================================*/
/* Index: idx_mmdm_order_source_scale                           */
/*==============================================================*/
create index idx_mmdm_order_source_scale on t_mmdm_order_source_scale
    (

        );

/*==============================================================*/
/* Index: idx_mmdm_osr_orderid                                  */
/*==============================================================*/
create index idx_mmdm_osr_orderid on t_mmdm_order_stat_record
    (
     order_id
        );
