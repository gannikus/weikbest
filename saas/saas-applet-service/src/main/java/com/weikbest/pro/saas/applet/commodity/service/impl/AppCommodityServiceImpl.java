package com.weikbest.pro.saas.applet.commodity.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weikbest.pro.saas.applet.commodity.module.mapstruct.*;
import com.weikbest.pro.saas.applet.commodity.module.qo.AppProdQO;
import com.weikbest.pro.saas.applet.commodity.module.vo.*;
import com.weikbest.pro.saas.applet.commodity.service.AppCommodityService;
import com.weikbest.pro.saas.common.constant.BasicConstant;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.util.WeikbestObjectUtil;
import com.weikbest.pro.saas.merchat.busi.entity.Business;
import com.weikbest.pro.saas.merchat.busi.service.BusinessService;
import com.weikbest.pro.saas.merchat.category.entity.AppletProdCategoryRelate;
import com.weikbest.pro.saas.merchat.category.service.AppletProdCategoryRelateService;
import com.weikbest.pro.saas.merchat.feight.entity.FeightTemplate;
import com.weikbest.pro.saas.merchat.feight.entity.FeightTemplateDetail;
import com.weikbest.pro.saas.merchat.feight.entity.FeightTemplateRegion;
import com.weikbest.pro.saas.merchat.feight.service.FeightTemplateDetailService;
import com.weikbest.pro.saas.merchat.feight.service.FeightTemplateRegionService;
import com.weikbest.pro.saas.merchat.feight.service.FeightTemplateService;
import com.weikbest.pro.saas.merchat.prod.entity.*;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdMapper;
import com.weikbest.pro.saas.merchat.prod.module.dto.AppProdDTO;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdServiceCommitmentDTO;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdAdvBackAccountMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdJumpLinkMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdPurchaseRestrictionsMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdAdvBackAccountVO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdJumpLinkVO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdPurchaseRestrictionsVo;
import com.weikbest.pro.saas.merchat.prod.service.*;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.entity.ProdStandard;
import com.weikbest.pro.saas.sys.param.service.ProdStandardService;
import io.jsonwebtoken.lang.Collections;
import jodd.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppCommodityServiceImpl implements AppCommodityService {

    @Autowired
    private ProdService prodService;

    @Resource
    private ProdMapper prodMapper;

    @Autowired
    private AppletProdCategoryRelateService appletProdCategoryRelateService;

    @Autowired
    private ProdMainimgService prodMainimgService;

    @Autowired
    private ProdPriceService prodPriceService;

    @Autowired
    private ProdDetailService prodDetailService;

    @Autowired
    private ProdThemeService prodThemeService;

    @Autowired
    private ProdComboService prodComboService;

    @Autowired
    private ProdComboAttrRelateService prodComboAttrRelateService;

    @Resource
    private ProdLeftSlideService prodLeftSlideService;

    @Resource
    private ProdJumpLinkService prodJumpLinkService;

    @Resource
    private ProdDecFloorService prodDecFloorService;

    @Resource
    private ProdAdvBackAccountService prodAdvBackAccountService;

    @Resource
    private ProdFeightService prodFeightService;

    @Resource
    private FeightTemplateService feightTemplateService;

    @Resource
    private FeightTemplateDetailService feightTemplateDetailService;

    @Resource
    private FeightTemplateRegionService feightTemplateRegionService;

    @Resource
    private BusinessService businessService;

    @Resource
    private ProdStandardService prodStandardService;

    @Autowired
    private ProdReturnService prodReturnService;

    @Resource
    private ProdServiceCommitmentService prodServiceCommitmentService;

    @Resource
    private ProdPurchaseRestrictionsService prodPurchaseRestrictionsService;


    @Override
    public AppProdDetailVO findProdDetail(Long prodId, Integer click) {

        /**
         * 1，获取商品基本信息，t_mmdm_prod
         * 2，获取商品详情页轮播图信息，t_mmdm_prod_mainimg
         * 3，获取商品价格信息，t_mmdm_prod_price
         * 4，获取商品详细图信息，t_mmdm_prod_detail
         * 5，获取商品样式拆分表，t_mmdm_prod_theme
         * 6，获取商品销售套餐及规格属性 t_mmdm_prod_combo 和 t_mmdm_prod_combo_attr_relate
         * 7，获取运费信息，t_mmdm_prod_feight,t_mmdm_feight_template,t_mmdm_feight_template_detail,t_mmdm_feight_template_region
         */
        Prod prod = prodService.findById(prodId);
        AppProdDetailVO appProdDetailVO = new AppProdDetailVO();
        appProdDetailVO.setId(prod.getId());
        appProdDetailVO.setName(prod.getName());
        appProdDetailVO.setSales(prod.getSales());
        appProdDetailVO.setShopId(prod.getShopId());
        appProdDetailVO.setSafeguard(prod.getSafeguard());
        appProdDetailVO.setGoodsType(prod.getGoodsType());
        appProdDetailVO.setIsOpenOrderLimit(prod.getIsOpenOrderLimit());
        appProdDetailVO.setCustomerServiceSwitch(StrUtil.isBlank(prod.getCustomerServiceSwitch()) ? BasicConstant.STATE_0 : prod.getCustomerServiceSwitch());

        if (BasicConstant.STATE_1.equals(appProdDetailVO.getIsOpenOrderLimit())){
            ProdPurchaseRestrictions byId = prodPurchaseRestrictionsService.getById(prodId);
            if (ObjectUtil.isNotNull(byId)){
                ProdPurchaseRestrictionsVo prodPurchaseRestrictionsVo = ProdPurchaseRestrictionsMapStruct.INSTANCE.converToVO(byId);
                appProdDetailVO.setProdPurchaseRestrictionsVo(prodPurchaseRestrictionsVo);
            }
        }
        //商品支付方式和短信验证开关
        appProdDetailVO.setIsOpenCashOnDeliverySms(Optional.ofNullable(prod.getIsOpenCashOnDeliverySms()).orElse(BasicConstant.INT_0));
        ArrayList<Map<String, String>> payTypeList = new ArrayList<>();
        if (StrUtil.isNotBlank(prod.getPayType())) {
            for (String s : prod.getPayType().split(",")) {
                HashMap<String, String> map = new HashMap<>();
                if (DictConstant.PayType.TYPE_1.getCode().equals(s)) {
                    map.put(s, BasicConstant.PayType.WX_PAY);
                } else if (DictConstant.PayType.TYPE_2.getCode().equals(s)) {
                    map.put(s, BasicConstant.PayType.CASH_ON_DELIVERY);
                }
                payTypeList.add(map);
            }
        }else {//如果没有 默认显示微信支付
            HashMap<String, String> map = new HashMap<>();
            map.put(DictConstant.PayType.TYPE_1.getCode(), BasicConstant.PayType.WX_PAY);
            payTypeList.add(map);
        }
        appProdDetailVO.setPayTypeList(payTypeList);

        QueryWrapper<ProdMainimg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ProdMainimg.ID, prodId);
        List<ProdMainimg> prodMainimgs = prodMainimgService.list(queryWrapper);
        List<String> mainimgs = new ArrayList<>();
        for (ProdMainimg prodMainimg : prodMainimgs) {
            mainimgs.add(prodMainimg.getMainImg());
        }
        appProdDetailVO.setMainimgs(mainimgs);

        //商品服务承诺
        ProdServiceCommitmentDTO serviceCommitmentDTO = prodServiceCommitmentService.getByProdId(prodId);
        ArrayList<String> list = new ArrayList<>();
        Map<String, String> serviceMap = new HashMap<>();
        //将状态码和服务承诺对应关系存储在Map中
        serviceMap.put(BasicConstant.STATE_1, BasicConstant.ServiceCommitment.SERVICE_COMMITMENT_STATUS_1);
        serviceMap.put(BasicConstant.STATE_2, BasicConstant.ServiceCommitment.SERVICE_COMMITMENT_STATUS_2);
        serviceMap.put(BasicConstant.STATE_3, BasicConstant.ServiceCommitment.SERVICE_COMMITMENT_STATUS_3);
        serviceMap.put(BasicConstant.STATE_4, BasicConstant.ServiceCommitment.SERVICE_COMMITMENT_STATUS_4);
        serviceMap.put(BasicConstant.STATE_5, BasicConstant.ServiceCommitment.SERVICE_COMMITMENT_STATUS_5);
        serviceMap.put(BasicConstant.STATE_6, BasicConstant.ServiceCommitment.SERVICE_COMMITMENT_STATUS_6);
        serviceMap.put(BasicConstant.STATE_7, BasicConstant.ServiceCommitment.SERVICE_COMMITMENT_STATUS_7);
        if (serviceCommitmentDTO != null && !serviceCommitmentDTO.getServiceList().isEmpty()) {
            for (String s : serviceCommitmentDTO.getServiceList()) {
                //将状态码对应的服务承诺添加到list中
                if (serviceMap.containsKey(s)) {
                    list.add(serviceMap.get(s));
                }
            }
            serviceCommitmentDTO.setServiceList(list);
        }
        appProdDetailVO.setProdServiceCommitmentDTO(serviceCommitmentDTO);
        ProdFeight prodFeight = prodFeightService.findById(prodId);
        appProdDetailVO.setFeightType(prodFeight.getFeightType());
        appProdDetailVO.setFeightAmount(prodFeight.getFeightAmount());
        //如果取值为运费模板，则读取模块信息
        if ("2".equals(prodFeight.getFeightType())) {
            FeightTemplate feightTemplate = feightTemplateService.findById(prodFeight.getFeightTemplateId());
            AppFeightTemplateVO appFeightTemplateVO = AppFeightTemplateMapStruct.INSTANCE.converToVO(feightTemplate);

            List<AppFeightTemplateDetailVO> appFeightTemplateDetailVOList = new ArrayList<>();
            QueryWrapper<FeightTemplateDetail> feightTemplateDetailQueryWrapper = new QueryWrapper<>();
            feightTemplateDetailQueryWrapper.eq(FeightTemplateDetail.ID, feightTemplate.getId());
            List<FeightTemplateDetail> feightTemplateDetailList = feightTemplateDetailService.list(feightTemplateDetailQueryWrapper);
            for (FeightTemplateDetail feightTemplateDetail : feightTemplateDetailList) {
                AppFeightTemplateDetailVO appFeightTemplateDetailVO = AppFeightTemplateDetailMapStruct.INSTANCE.converToVO(feightTemplateDetail);
                List<AppFeightTemplateRegionVO> appFeightTemplateRegionVOList = new ArrayList<>();
                QueryWrapper<FeightTemplateRegion> feightTemplateRegionQueryWrapper = new QueryWrapper<>();
                feightTemplateRegionQueryWrapper.eq(FeightTemplateRegion.ID, feightTemplate.getId());
                feightTemplateRegionQueryWrapper.eq(FeightTemplateRegion.ENTRY_ID, feightTemplateDetail.getEntryId());
                List<FeightTemplateRegion> feightTemplateRegionList = feightTemplateRegionService.list(feightTemplateRegionQueryWrapper);
                for (FeightTemplateRegion feightTemplateRegion : feightTemplateRegionList) {
                    AppFeightTemplateRegionVO appFeightTemplateRegionVO = AppFeightTemplateRegionMapStruct.INSTANCE.converToVO(feightTemplateRegion);
                    appFeightTemplateRegionVOList.add(appFeightTemplateRegionVO);
                }
                appFeightTemplateDetailVO.setAppFeightTemplateRegionVOList(appFeightTemplateRegionVOList);
                appFeightTemplateDetailVOList.add(appFeightTemplateDetailVO);
            }
            appFeightTemplateVO.setAppFeightTemplateDetailVOList(appFeightTemplateDetailVOList);
            appProdDetailVO.setAppFeightTemplateVO(appFeightTemplateVO);
        }

        //设置商户类型
        Business business = businessService.findById(prod.getBusinessId());
        appProdDetailVO.setBusinessType(business.getBusinessType());

        ProdStandard prodStandard = prodStandardService.findProdStandard();
        appProdDetailVO.setPayFailThemeUrl(prodStandard.getPayFailThemeUrl());

        QueryWrapper<ProdDetail> queryWrapperProdDetail = new QueryWrapper<>();
        queryWrapperProdDetail.eq(ProdDetail.ID, prodId);
        List<ProdDetail> prodDetails = prodDetailService.list(queryWrapperProdDetail);
        List<String> prodDetailImgs = new ArrayList<>();
        for (ProdDetail prodDetail : prodDetails) {
            prodDetailImgs.add(prodDetail.getDetailImg());
        }
        appProdDetailVO.setProdDetailImgs(prodDetailImgs);

        ProdTheme prodTheme = prodThemeService.findById(prodId);
        AppProdThemeVO appProdThemeVO = AppProdThemeMapStruct.INSTANCE.converToVO(prodTheme);
        appProdDetailVO.setAppProdThemeVO(appProdThemeVO);

        BigDecimal minPriceSpec = null;
        List<ProdCombo> prodCombos = new ArrayList<>();
        //--------------------------------------- 上面是共有逻辑,不做变动 -------------------------------------


        //当没有当前点击次数的时候, 原先的逻辑不变, 有点击次数的时候, 变动当前逻辑
        /**
         * 传入click:
         *      -1: 首次进入
         * 返回的click
         *      9999: 最后一个返回页,后一页没有数据了
         */
        Integer[] clicks = {0 , 0}; //[0]: 当前返回的次数 , [1]: 下次返回的次数
        boolean isSkip = false; //营销页是否关闭开关, 营销页关闭了返回页数据就从4开始查
        if (ObjectUtil.isNotNull(click)){
            if (click == -1){
                //当click = -1 : 说明是第一次点进来 不需要获取返回页信息直接走之前的逻辑(设置click = null) 并将下次返回的click设置成 8888(跟前端定义的)
                appProdDetailVO.setClick(8888);
                click = null;
            }else {
                //当click != -1 : 说明是带有返回次数的,需要根据click获取当前的返回次数 并将下次需要返回的次数用click在返给前端
                clicks[0] = click;
                if (click.equals(BasicConstant.INT_0)){
                    appProdDetailVO.setIsFirstEntry(click); //前端用来判断是不是返回页第一次进来的字段
                }
            }
        }

        //判断开关是否开启
        ProdLeftSlide slide = prodLeftSlideService.findById(prodId);
        appProdDetailVO.setFullDiscountOnOff(ObjectUtil.isNull(slide.getFullDiscountOnOff()) ? BasicConstant.INT_0 : slide.getFullDiscountOnOff());
        appProdDetailVO.setFullDiscountMoney(ObjectUtil.isNull(slide.getFullDiscountMoney()) ? new BigDecimal(BigInteger.ZERO) : slide.getFullDiscountMoney());
        appProdDetailVO.setDefaultPaymentType(ObjectUtil.isNull(slide.getDefaultPaymentType()) ? BasicConstant.INT_0 : slide.getDefaultPaymentType());

        if (slide.getMarketingPageSet() == null || BasicConstant.INT_0.equals(slide.getMarketingPageSet())) {
            //营销页关闭
            if (ObjectUtil.isNotNull(click) && click <= BasicConstant.INT_3){
                slide.setJoinpageOpenUrl("");
                slide.setJoinpageJump("");
                slide.setJoinpageBannerUrl("");
                slide.setPictureFirstPicture("");
                slide.setBackgroundColor("");
                isSkip = true;
            }
        }
        ProdReturn prodReturn = prodReturnService.getReturnByProdAndClick(prodId , clicks , isSkip);
        appProdDetailVO.setIsThereAReturnPage(ObjectUtil.isNotNull(prodReturn) ? BasicConstant.INT_1 : BasicConstant.INT_0);
        if (ObjectUtil.isNotNull(click) && ObjectUtil.isNull(prodReturn)){
            click = null;
        }

        //判断返回页开关是否开启, 如果没有开启并且当前查出来的返回页信息是 多级回流的 就不返回
        if (slide.getMlcReflowSet() == null || BasicConstant.INT_0.equals(slide.getMlcReflowSet())) {
            if (clicks[1] > BasicConstant.INT_3){
                prodReturn = null;
            }
        }
        //当营销页设置和返回页设置都关闭时 , 不返回
        if ((slide.getMarketingPageSet() == null || BasicConstant.INT_0.equals(slide.getMarketingPageSet()))
                && (slide.getMlcReflowSet() == null || BasicConstant.INT_0.equals(slide.getMlcReflowSet()))){
            prodReturn = null;
        }

        if (ObjectUtil.isNull(click) || ObjectUtil.isNull(prodReturn) || ObjectUtil.isNull(prodReturn.getId())){
            //这是之前逻辑的代码
            ProdPrice prodPrice = prodPriceService.findById(prodId);
            appProdDetailVO.setComboMinPrice(prodPrice.getComboMinPrice());
            appProdDetailVO.setComboMinStandardPrice(prodPrice.getComboMinStandardPrice());
            appProdDetailVO.setSubsidyPrice(prodPrice.getSubsidyPrice());
            appProdDetailVO.setIsFailPayHint(prodPrice.getIsFailPayHint());
            appProdDetailVO.setDiscountPrice(prodPrice.getDiscountPrice());

            //套餐信息
            QueryWrapper<ProdCombo> prodComboQueryWrapper = new QueryWrapper<>();
            prodComboQueryWrapper.eq(ProdCombo.PROD_ID, prodId);
            prodComboQueryWrapper.eq(ProdCombo.SET_MEAL_TYPE, prod.getSetMealType() == null ? 1 : prod.getSetMealType());
            prodComboQueryWrapper.orderByAsc(ProdCombo.COMBO_ORD);
            prodCombos = prodComboService.list(prodComboQueryWrapper);
            List<AppProdComboVO> appProdComboVOS = new ArrayList<>();
            for (ProdCombo prodCombo : prodCombos) {
                AppProdComboVO appProdComboVO = AppProdComboMapStruct.INSTANCE.converToVO(prodCombo);
                QueryWrapper<ProdComboAttrRelate> prodComboAttrRelateQueryWrapper = new QueryWrapper<>();
                prodComboAttrRelateQueryWrapper.eq(ProdComboAttrRelate.PROD_ID, prodId);
                prodComboAttrRelateQueryWrapper.eq(ProdComboAttrRelate.PROD_COMBO_ID, prodCombo.getId());
                prodComboAttrRelateQueryWrapper.orderByAsc(ProdComboAttrRelate.ID);
                List<ProdComboAttrRelate> prodComboAttrRelates = prodComboAttrRelateService.list(prodComboAttrRelateQueryWrapper);
                List<AppProdComboAttrRelateVO> appProdComboAttrRelateVOS = new ArrayList<>();
                for (ProdComboAttrRelate prodComboAttrRelate : prodComboAttrRelates) {
                    appProdComboAttrRelateVOS.add(AppProdComboAttrRelateMapStruct.INSTANCE.converToVO(prodComboAttrRelate));
                }
                appProdComboVO.setAppProdComboAttrRelateVOS(appProdComboAttrRelateVOS);
                appProdComboVO.setPaidInAmount(appProdComboVO.getComboPrice());
                appProdComboVOS.add(appProdComboVO);

                if (ObjectUtil.isNull(minPriceSpec)){
                    minPriceSpec = prodCombo.getComboPrice();
                    appProdDetailVO.setMinPriceSpec(prodCombo.getSellPoint());
                }
                //获取最低实付价
                if (appProdComboVO.getComboPrice().compareTo(appProdDetailVO.getComboMinPrice()) == BasicConstant.INT_0
                        && appProdComboVO.getComboStandardPrice().compareTo(appProdDetailVO.getComboMinStandardPrice()) == BasicConstant.INT_0){
                    appProdDetailVO.setComboMinPaidInAmount(appProdComboVO.getPaidInAmount());
                }
            }
            appProdDetailVO.setAppProdComboVOS(appProdComboVOS);

            //判断开关是否开启
            List<ProdReturn> prodReturns = new ArrayList<>(); //返回页改版, 改成全部返回的时候返回一个空数组
            if (slide.getMarketingPageSet() == null || BasicConstant.INT_0.equals(slide.getMarketingPageSet())) {
                //营销页关闭
                prodReturns = prodReturns.stream().filter(p -> p.getSort() > 2).collect(Collectors.toList());
                slide.setJoinpageOpenUrl("");
                slide.setJoinpageJump("");
                slide.setJoinpageBannerUrl("");
                slide.setPictureFirstPicture("");
                slide.setBackgroundColor("");
            }
            if (slide.getMlcReflowSet() == null || BasicConstant.INT_0.equals(slide.getMlcReflowSet())) {
                prodReturns = prodReturns.stream().filter(p -> p.getSort() < 4).collect(Collectors.toList());
            }
            if((slide.getMarketingPageSet() == null && slide.getMlcReflowSet() == null)
                    || (BasicConstant.INT_0.equals(slide.getMarketingPageSet()) && BasicConstant.INT_0.equals(slide.getMlcReflowSet()))){
                prodReturns = new ArrayList<>();
            }
            //设置返回页数据
            appProdDetailVO.setReturns(prodReturns);
            //获取营销页数据
            appProdDetailVO.setJoinpageOpenUrl(slide.getJoinpageOpenUrl());
            appProdDetailVO.setJoinpageBannerUrl(slide.getJoinpageBannerUrl());
            //虚拟按键
            appProdDetailVO.setVirtualKey(slide.getVirtualKey());
        }else {
            //这是有click并且有返回页信息的代码

            //获取当前返回页信息
            appProdDetailVO.setClick(clicks[0]);

            //最低销售价格要改,当click有值时
            ProdPrice prodPrice = prodPriceService.findById(prodId);
            appProdDetailVO.setComboMinPrice(prodPrice.getComboMinPrice());
            /*if (ObjectUtil.isNotNull(prodPrice.getComboMinPrice()) && ObjectUtil.isNotNull(prodReturn.getReturnAmount())){
                //套餐商品销售金额（最低） = 最低金额 - 优惠金额
                appProdDetailVO.setComboMinPrice(prodPrice.getComboMinPrice().subtract(prodReturn.getReturnAmount()));
            }else {
                appProdDetailVO.setComboMinPrice(prodPrice.getComboMinPrice());
            }*/
            appProdDetailVO.setComboMinStandardPrice(prodPrice.getComboMinStandardPrice());
            appProdDetailVO.setSubsidyPrice(prodPrice.getSubsidyPrice());
            appProdDetailVO.setIsFailPayHint(prodPrice.getIsFailPayHint());
            appProdDetailVO.setDiscountPrice(prodPrice.getDiscountPrice());

            //TODO
            //每个套餐添加一个实付金额(售价- 返回页金额)
            //套餐信息
            QueryWrapper<ProdCombo> prodComboQueryWrapper = new QueryWrapper<>();
            prodComboQueryWrapper.eq(ProdCombo.PROD_ID, prodId);
            prodComboQueryWrapper.eq(ProdCombo.SET_MEAL_TYPE, prod.getSetMealType() == null ? 1 : prod.getSetMealType());
            prodComboQueryWrapper.orderByAsc(ProdCombo.COMBO_ORD);
            prodCombos = prodComboService.list(prodComboQueryWrapper);
            List<AppProdComboVO> appProdComboVOS = new ArrayList<>();
            for (ProdCombo prodCombo : prodCombos) {
                AppProdComboVO appProdComboVO = AppProdComboMapStruct.INSTANCE.converToVO(prodCombo);
                QueryWrapper<ProdComboAttrRelate> prodComboAttrRelateQueryWrapper = new QueryWrapper<>();
                prodComboAttrRelateQueryWrapper.eq(ProdComboAttrRelate.PROD_ID, prodId);
                prodComboAttrRelateQueryWrapper.eq(ProdComboAttrRelate.PROD_COMBO_ID, prodCombo.getId());
                prodComboAttrRelateQueryWrapper.orderByAsc(ProdComboAttrRelate.ID);
                List<ProdComboAttrRelate> prodComboAttrRelates = prodComboAttrRelateService.list(prodComboAttrRelateQueryWrapper);
                List<AppProdComboAttrRelateVO> appProdComboAttrRelateVOS = new ArrayList<>();
                for (ProdComboAttrRelate prodComboAttrRelate : prodComboAttrRelates) {
                    appProdComboAttrRelateVOS.add(AppProdComboAttrRelateMapStruct.INSTANCE.converToVO(prodComboAttrRelate));
                }
                appProdComboVO.setAppProdComboAttrRelateVOS(appProdComboAttrRelateVOS);
                //设置当前套餐的实付金额
                /*if (ObjectUtil.isNotNull(prodCombo.getComboPrice()) && ObjectUtil.isNotNull(prodReturn.getReturnAmount())){
                    //满减开关已经开启
                    if (ObjectUtil.isNotNull(slide.getFullDiscountOnOff()) && slide.getFullDiscountOnOff().equals(BasicConstant.INT_1)){
                        if (ObjectUtil.isNotNull(slide.getFullDiscountMoney()) && slide.getFullDiscountMoney().compareTo(prodCombo.getComboPrice()) == -1){
                            appProdComboVO.setPaidInAmount(prodCombo.getComboPrice().subtract(prodReturn.getReturnAmount()));
                        }else {
                            appProdComboVO.setPaidInAmount(prodCombo.getComboPrice());
                        }
                    }else {
                        appProdComboVO.setPaidInAmount(prodCombo.getComboPrice().subtract(prodReturn.getReturnAmount()));
                    }
                }*/
                appProdComboVO.setPaidInAmount(prodCombo.getComboPrice());
                appProdComboVOS.add(appProdComboVO);

                if (ObjectUtil.isNull(minPriceSpec)){
                    minPriceSpec = prodCombo.getComboPrice();
                    appProdDetailVO.setMinPriceSpec(prodCombo.getSellPoint());
                }
                //获取最低实付价
                if (appProdComboVO.getComboPrice().compareTo(appProdDetailVO.getComboMinPrice()) == BasicConstant.INT_0
                        && appProdComboVO.getComboStandardPrice().compareTo(appProdDetailVO.getComboMinStandardPrice()) == BasicConstant.INT_0){
                    appProdDetailVO.setComboMinPaidInAmount(appProdComboVO.getPaidInAmount());
                }
            }
            appProdDetailVO.setAppProdComboVOS(appProdComboVOS);

            //获取营销页数据
            appProdDetailVO.setJoinpageOpenUrl(slide.getJoinpageOpenUrl());
            appProdDetailVO.setJoinpageBannerUrl(slide.getJoinpageBannerUrl());
            //设置返回页数据
            appProdDetailVO.setReturns(java.util.Collections.singletonList(prodReturn));
            //虚拟按键
            appProdDetailVO.setVirtualKey(slide.getVirtualKey());
        }
        appProdDetailVO.setSetMealType(prod.getSetMealType());
        appProdDetailVO.setEchoMuchSizes(echoMuchSizes(prodCombos));
        return appProdDetailVO;
    }
    /*public AppProdDetailVO findProdDetail(Long prodId) {
        Prod prod = prodService.findById(prodId);
        AppProdDetailVO appProdDetailVO = new AppProdDetailVO();
        appProdDetailVO.setId(prod.getId());
        appProdDetailVO.setName(prod.getName());
        appProdDetailVO.setSales(prod.getSales());
        appProdDetailVO.setShopId(prod.getShopId());
        appProdDetailVO.setSafeguard(prod.getSafeguard());
        appProdDetailVO.setGoodsType(prod.getGoodsType());

        QueryWrapper<ProdMainimg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ProdMainimg.ID, prodId);
        List<ProdMainimg> prodMainimgs = prodMainimgService.list(queryWrapper);
        List<String> mainimgs = new ArrayList<>();
        for (ProdMainimg prodMainimg : prodMainimgs) {
            mainimgs.add(prodMainimg.getMainImg());
        }
        appProdDetailVO.setMainimgs(mainimgs);


        ProdPrice prodPrice = prodPriceService.findById(prodId);
        appProdDetailVO.setComboMinPrice(prodPrice.getComboMinPrice());
        appProdDetailVO.setComboMinStandardPrice(prodPrice.getComboMinStandardPrice());
        appProdDetailVO.setSubsidyPrice(prodPrice.getSubsidyPrice());
        appProdDetailVO.setIsFailPayHint(prodPrice.getIsFailPayHint());
        appProdDetailVO.setDiscountPrice(prodPrice.getDiscountPrice());

        ProdStandard prodStandard = prodStandardService.findProdStandard();
        appProdDetailVO.setPayFailThemeUrl(prodStandard.getPayFailThemeUrl());


        QueryWrapper<ProdDetail> queryWrapperProdDetail = new QueryWrapper<>();
        queryWrapperProdDetail.eq(ProdDetail.ID, prodId);
        List<ProdDetail> prodDetails = prodDetailService.list(queryWrapperProdDetail);
        List<String> prodDetailImgs = new ArrayList<>();
        for (ProdDetail prodDetail : prodDetails) {
            prodDetailImgs.add(prodDetail.getDetailImg());
        }
        appProdDetailVO.setProdDetailImgs(prodDetailImgs);

        ProdTheme prodTheme = prodThemeService.findById(prodId);
        AppProdThemeVO appProdThemeVO = AppProdThemeMapStruct.INSTANCE.converToVO(prodTheme);
        appProdDetailVO.setAppProdThemeVO(appProdThemeVO);
        //套餐信息
        QueryWrapper<ProdCombo> prodComboQueryWrapper = new QueryWrapper<>();
        prodComboQueryWrapper.eq(ProdCombo.PROD_ID, prodId);
        prodComboQueryWrapper.eq(ProdCombo.SET_MEAL_TYPE, prod.getSetMealType() == null ? 1 : prod.getSetMealType());
        prodComboQueryWrapper.orderByAsc(ProdCombo.COMBO_ORD);
        List<ProdCombo> prodCombos = prodComboService.list(prodComboQueryWrapper);
        List<AppProdComboVO> appProdComboVOS = new ArrayList<>();
        for (ProdCombo prodCombo : prodCombos) {
            AppProdComboVO appProdComboVO = AppProdComboMapStruct.INSTANCE.converToVO(prodCombo);
            QueryWrapper<ProdComboAttrRelate> prodComboAttrRelateQueryWrapper = new QueryWrapper<>();
            prodComboAttrRelateQueryWrapper.eq(ProdComboAttrRelate.PROD_ID, prodId);
            prodComboAttrRelateQueryWrapper.eq(ProdComboAttrRelate.PROD_COMBO_ID, prodCombo.getId());
            prodComboAttrRelateQueryWrapper.orderByAsc(ProdComboAttrRelate.ID);
            List<ProdComboAttrRelate> prodComboAttrRelates = prodComboAttrRelateService.list(prodComboAttrRelateQueryWrapper);
            List<AppProdComboAttrRelateVO> appProdComboAttrRelateVOS = new ArrayList<>();
            for (ProdComboAttrRelate prodComboAttrRelate : prodComboAttrRelates) {
                appProdComboAttrRelateVOS.add(AppProdComboAttrRelateMapStruct.INSTANCE.converToVO(prodComboAttrRelate));
            }
            appProdComboVO.setAppProdComboAttrRelateVOS(appProdComboAttrRelateVOS);
            appProdComboVOS.add(appProdComboVO);
        }
        appProdComboVOS = appProdComboVOS.stream().sorted(Comparator.comparing(AppProdComboVO::getComboPrice)).collect(Collectors.toList());
        appProdDetailVO.setAppProdComboVOS(appProdComboVOS);

        //商品服务承诺
        ProdServiceCommitmentDTO serviceCommitmentDTO = prodServiceCommitmentService.getByProdId(prodId);
        ArrayList<String> list = new ArrayList<>();
        Map<String, String> serviceMap = new HashMap<>();
        //将状态码和服务承诺对应关系存储在Map中
        serviceMap.put(BasicConstant.STATE_1, BasicConstant.ServiceCommitment.SERVICE_COMMITMENT_STATUS_1);
        serviceMap.put(BasicConstant.STATE_2, BasicConstant.ServiceCommitment.SERVICE_COMMITMENT_STATUS_2);
        serviceMap.put(BasicConstant.STATE_3, BasicConstant.ServiceCommitment.SERVICE_COMMITMENT_STATUS_3);
        serviceMap.put(BasicConstant.STATE_4, BasicConstant.ServiceCommitment.SERVICE_COMMITMENT_STATUS_4);
        serviceMap.put(BasicConstant.STATE_5, BasicConstant.ServiceCommitment.SERVICE_COMMITMENT_STATUS_5);
        serviceMap.put(BasicConstant.STATE_6, BasicConstant.ServiceCommitment.SERVICE_COMMITMENT_STATUS_6);
        serviceMap.put(BasicConstant.STATE_7, BasicConstant.ServiceCommitment.SERVICE_COMMITMENT_STATUS_7);
        if (serviceCommitmentDTO != null && !serviceCommitmentDTO.getServiceList().isEmpty()) {
            for (String s : serviceCommitmentDTO.getServiceList()) {
                //将状态码对应的服务承诺添加到list中
                if (serviceMap.containsKey(s)) {
                    list.add(serviceMap.get(s));
                }
            }
            serviceCommitmentDTO.setServiceList(list);
        }
        appProdDetailVO.setProdServiceCommitmentDTO(serviceCommitmentDTO);
        ProdFeight prodFeight = prodFeightService.findById(prodId);
        appProdDetailVO.setFeightType(prodFeight.getFeightType());
        appProdDetailVO.setFeightAmount(prodFeight.getFeightAmount());
        //如果取值为运费模板，则读取模块信息
        if ("2".equals(prodFeight.getFeightType())) {
            FeightTemplate feightTemplate = feightTemplateService.findById(prodFeight.getFeightTemplateId());
            AppFeightTemplateVO appFeightTemplateVO = AppFeightTemplateMapStruct.INSTANCE.converToVO(feightTemplate);

            List<AppFeightTemplateDetailVO> appFeightTemplateDetailVOList = new ArrayList<>();
            QueryWrapper<FeightTemplateDetail> feightTemplateDetailQueryWrapper = new QueryWrapper<>();
            feightTemplateDetailQueryWrapper.eq(FeightTemplateDetail.ID, feightTemplate.getId());
            List<FeightTemplateDetail> feightTemplateDetailList = feightTemplateDetailService.list(feightTemplateDetailQueryWrapper);
            for (FeightTemplateDetail feightTemplateDetail : feightTemplateDetailList) {
                AppFeightTemplateDetailVO appFeightTemplateDetailVO = AppFeightTemplateDetailMapStruct.INSTANCE.converToVO(feightTemplateDetail);
                List<AppFeightTemplateRegionVO> appFeightTemplateRegionVOList = new ArrayList<>();
                QueryWrapper<FeightTemplateRegion> feightTemplateRegionQueryWrapper = new QueryWrapper<>();
                feightTemplateRegionQueryWrapper.eq(FeightTemplateRegion.ID, feightTemplate.getId());
                feightTemplateRegionQueryWrapper.eq(FeightTemplateRegion.ENTRY_ID, feightTemplateDetail.getEntryId());
                List<FeightTemplateRegion> feightTemplateRegionList = feightTemplateRegionService.list(feightTemplateRegionQueryWrapper);
                for (FeightTemplateRegion feightTemplateRegion : feightTemplateRegionList) {
                    AppFeightTemplateRegionVO appFeightTemplateRegionVO = AppFeightTemplateRegionMapStruct.INSTANCE.converToVO(feightTemplateRegion);
                    appFeightTemplateRegionVOList.add(appFeightTemplateRegionVO);
                }
                appFeightTemplateDetailVO.setAppFeightTemplateRegionVOList(appFeightTemplateRegionVOList);
                appFeightTemplateDetailVOList.add(appFeightTemplateDetailVO);
            }
            appFeightTemplateVO.setAppFeightTemplateDetailVOList(appFeightTemplateDetailVOList);
            appProdDetailVO.setAppFeightTemplateVO(appFeightTemplateVO);
        }

        //设置商户类型
        Business business = businessService.findById(prod.getBusinessId());
        appProdDetailVO.setBusinessType(business.getBusinessType());

        //判断开关是否开启
        List<ProdReturn> prodReturns = Optional.ofNullable(prodReturnService.list(new LambdaQueryWrapper<ProdReturn>().eq(ProdReturn::getProdId, prodId).orderByAsc(ProdReturn::getSort))).orElseGet(ArrayList::new);
        ProdLeftSlide slide = prodLeftSlideService.findById(prodId);
        if (slide.getMarketingPageSet() == null || BasicConstant.INT_0.equals(slide.getMarketingPageSet())) {
            //营销页关闭
            prodReturns = prodReturns.stream().filter(p -> p.getSort() > 2).collect(Collectors.toList());
            slide.setJoinpageOpenUrl("");
            slide.setJoinpageJump("");
            slide.setJoinpageBannerUrl("");
            slide.setPictureFirstPicture("");
            slide.setBackgroundColor("");
        }
        if (slide.getMlcReflowSet() == null || BasicConstant.INT_0.equals(slide.getMlcReflowSet())) {
            prodReturns = prodReturns.stream().filter(p -> p.getSort() < 4).collect(Collectors.toList());
        }
        if((slide.getMarketingPageSet() == null && slide.getMlcReflowSet() == null)
                || (BasicConstant.INT_0.equals(slide.getMarketingPageSet()) && BasicConstant.INT_0.equals(slide.getMlcReflowSet()))){
            prodReturns = new ArrayList<>();
        }
        //设置返回页数据
        appProdDetailVO.setReturns(prodReturns);
        //获取营销页数据
        appProdDetailVO.setJoinpageOpenUrl(slide.getJoinpageOpenUrl());
        appProdDetailVO.setJoinpageBannerUrl(slide.getJoinpageBannerUrl());
        //虚拟按键
        appProdDetailVO.setVirtualKey(slide.getVirtualKey());
        return appProdDetailVO;
    }*/

    @Override
    public List<AppProdDTO> queryPageCommodity(AppProdQO appProdQO, PageReq pageReq) {

        //查询逻辑说明
        /**
         * 1，如果 name （商品名称）有值，则查询所有营销分类下的商品信息
         * 2，如果 shopId （关联店铺ID）有值，则查询该店铺下的商品信息
         * 3，如果 semCategotyId （关联商品小程序类目ID ）有值，则查询该分类下的商品信息
         * 4，如果 types 不为空，则查询所有商品营销分类下的商品信息
         */

        // 构建查询参数
        Map<String, Object> paramMap = MapUtil.newHashMap();
        paramMap.putAll(BeanUtils.beanToMap(appProdQO));
        paramMap.put("start", PageUtil.getStart(pageReq.getPage(), pageReq.getLimit()));
        paramMap.put("limit", pageReq.getLimit());

        List<AppProdDTO> appProdDTOList = null;

        if (WeikbestObjectUtil.isNotEmpty(appProdQO.getShopId())) {
            appProdDTOList = prodMapper.queryPageCommodityByShopId(paramMap);
        } else {
            appProdDTOList = prodMapper.queryPageCommodity(paramMap);
        }

        return appProdDTOList;
    }

    @Override
    public List<AppProdDTO> queryPageCommodityByAggregationPage(AppProdQO appProdQO, PageReq pageReq) {

        //查询逻辑说明
        /**
         * 1，如果 name （商品名称）有值，则查询所有营销分类下的商品信息
         * 2，如果 shopId （关联店铺ID）有值，则查询该店铺下的商品信息
         * 3，如果 semCategotyId （关联商品小程序类目ID ）有值，则查询该分类下的商品信息
         * 4，如果 types 不为空，则查询所有商品营销分类下的商品信息
         */

        // 构建查询参数
        Map<String, Object> paramMap = MapUtil.newHashMap();
        paramMap.putAll(BeanUtils.beanToMap(appProdQO));
        paramMap.put("start", PageUtil.getStart(pageReq.getPage(), pageReq.getLimit()));
        paramMap.put("limit", pageReq.getLimit());

        List<AppProdDTO> appProdDTOList = null;
        ArrayList<AppProdDTO> appProdDTOS = new ArrayList<>();

        if (WeikbestObjectUtil.isNotEmpty(appProdQO.getShopId())) {
            appProdDTOList = prodMapper.queryPageCommodityByShopIdAndAggregationPage(paramMap);
        } else {
            appProdDTOList = prodMapper.queryPageCommodity(paramMap);
        }

        //商品不为空 查询返回页信息
        if (ObjectUtil.isNotNull(appProdQO.getProdId()) && BasicConstant.INT_1.equals(pageReq.getPage())) {
            //返回页信息 根据排序字段升序
            ProdReturn prodReturn3 = prodReturnService.getOne(new QueryWrapper<ProdReturn>().lambda().eq(ProdReturn::getProdId, appProdQO.getProdId()).eq(ProdReturn::getSort, BasicConstant.INT_3));
            //存在则计算价格 ，将该商品放在list的第一个，并将其他商品加入到新的list
            if (ObjectUtil.isNotEmpty(prodReturn3)) {
                if (CollectionUtils.isNotEmpty(appProdDTOList)) {
                    AppProdDTO appProdDTO1 = prodMapper.getByProdId(appProdQO.getProdId());
                    if (appProdDTO1!=null){
                        BigDecimal subtract = appProdDTO1.getComboMinPrice().subtract(prodReturn3.getReturnAmount());
                        appProdDTO1.setComboMinPrice(subtract);
                        appProdDTOS.add(appProdDTO1);
                    }
                    appProdDTOS.addAll(appProdDTOList.stream().filter(s -> !s.getId().equals(appProdQO.getProdId())).collect(Collectors.toList()));
                }
            }

        }else {
            appProdDTOS.addAll(appProdDTOList);
        }
        return appProdDTOS;
    }

    public List<AppProdDTO> queryPageCommodity_bak(AppProdQO appProdQO, PageReq pageReq) {

        //查询逻辑说明
        /**
         * 1，如果 name （商品名称）有值，则查询所有营销分类下的商品信息
         * 2，如果 shopId （关联店铺ID）有值，则查询该店铺下的商品信息
         * 3，如果 semCategotyId （关联商品小程序类目ID ）有值，则查询该分类下的商品信息
         * 4，如果 types 不为空，则查询所有商品营销分类下的商品信息
         */

        // 构建查询参数
        Map<String, Object> paramMap = MapUtil.newHashMap();
        String prods = "";

        if ((WeikbestObjectUtil.isNotEmpty(appProdQO.getSemCategotyId())) || StrUtil.isNotBlank(appProdQO.getTypes())) {
            QueryWrapper<AppletProdCategoryRelate> wrapper = new QueryWrapper<>();
            if (WeikbestObjectUtil.isNotEmpty(appProdQO.getSemCategotyId())) {
                wrapper.eq(AppletProdCategoryRelate.APPLET_PROD_CATEGOTY_ID, appProdQO.getSemCategotyId());
                wrapper.orderByAsc(AppletProdCategoryRelate.PROD_ORD);
            } else {
                wrapper.select("DISTINCT prod_id");
            }
            List<AppletProdCategoryRelate> prodSemRelates = appletProdCategoryRelateService.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper).getRecords();
            if (Collections.isEmpty(prodSemRelates) && prodSemRelates.size() == 0) {
                return null;
            } else {
                for (AppletProdCategoryRelate appletProdCategoryRelate : prodSemRelates) {
                    prods += appletProdCategoryRelate.getProdId() + ",";
                }
                prods = prods.substring(0, prods.length() - 1);
            }
        } else if (StringUtil.isNotBlank(appProdQO.getName())) {

            Map<String, Object> prodparamMap = MapUtil.newHashMap();
            prodparamMap.put("name", appProdQO.getName());
            prodparamMap.put("start", PageUtil.getStart(pageReq.getPage(), pageReq.getLimit()));
            prodparamMap.put("limit", pageReq.getLimit());
            List<Long> prodIds = prodMapper.queryPageProdsByName(prodparamMap);
            if (prodIds != null && prodIds.size() > 0) {
                for (Long id : prodIds) {
                    prods += id + ",";
                }
                prods = prods.substring(0, prods.length() - 1);
            }
        }

        paramMap.putAll(BeanUtils.beanToMap(appProdQO));
        paramMap.put("prods", prods);
        paramMap.put("start", PageUtil.getStart(pageReq.getPage(), pageReq.getLimit()));
        paramMap.put("limit", pageReq.getLimit());


        return prodMapper.queryPageCommodity(paramMap);
    }

    @Override
    public AppProdLeftSlideVO findProdLeftSlide(Long prodId) {

        ProdLeftSlide prodLeftSlide = prodLeftSlideService.findById(prodId);
        AppProdLeftSlideVO vo = AppProdLeftSlideMapStruct.INSTANCE.converToVO(prodLeftSlide);

        QueryWrapper<ProdJumpLink> prodJumpLinkQueryWrapper = new QueryWrapper<>();
        prodJumpLinkQueryWrapper.eq(ProdJumpLink.ID, prodId);
        prodJumpLinkQueryWrapper.orderByAsc(ProdJumpLink.JUMP_LINK_ORDER);
        List<ProdJumpLink> list = prodJumpLinkService.list(prodJumpLinkQueryWrapper);
        List<ProdJumpLinkVO> prodJumpLinkVOS = new ArrayList<>();
        /*if (CollectionUtils.isNotEmpty(list)) {
            for (ProdJumpLink prodJumpLink : list) {
                ProdJumpLinkVO prodJumpLinkVO = ProdJumpLinkMapStruct.INSTANCE.converToVO(prodJumpLink);
                prodJumpLinkVOS.add(prodJumpLinkVO);
            }
        } else {
            ProdJumpLinkVO prodJumpLinkVO = new ProdJumpLinkVO();
            prodJumpLinkVO.setJumpLinkType(BasicConstant.STATE_2);
            prodJumpLinkVO.setJumpLink("/pages/goods/details?id=" + prodId);
            prodJumpLinkVO.setJumpLinkOrder(BasicConstant.INT_1);
            prodJumpLinkVO.setIsQuickOrderLink(BasicConstant.STATE_0);
            prodJumpLinkVOS.add(prodJumpLinkVO);
        }

        Prod prod = prodService.findById(prodId);
        ProdPrice prodPrice = prodPriceService.findById(prodId);
        ProdTheme prodTheme = prodThemeService.findById(prodId);
        AppProdLeftSlideProdDetailVO appProdLeftSlideProdDetailVO = new AppProdLeftSlideProdDetailVO();
        appProdLeftSlideProdDetailVO.setId(prod.getId());
        appProdLeftSlideProdDetailVO.setName(prod.getName());
        appProdLeftSlideProdDetailVO.setSales(prod.getSales());
        appProdLeftSlideProdDetailVO.setShopId(prod.getShopId());
        appProdLeftSlideProdDetailVO.setTips(prod.getTips());
        appProdLeftSlideProdDetailVO.setShowImg(prodTheme.getShowImg());
        appProdLeftSlideProdDetailVO.setProdComboId(prodPrice.getProdComboId());
        appProdLeftSlideProdDetailVO.setComboMinPrice(prodPrice.getComboMinPrice());
        appProdLeftSlideProdDetailVO.setComboMinStandardPrice(prodPrice.getComboMinStandardPrice());

        vo.setAppProdLeftSlideProdDetailVO(appProdLeftSlideProdDetailVO);*/
        for (ProdJumpLink prodJumpLink: list) {
            ProdJumpLinkVO prodJumpLinkVO = ProdJumpLinkMapStruct.INSTANCE.converToVO(prodJumpLink);
            prodJumpLinkVOS.add(prodJumpLinkVO);

            //如果聚合页开启，并且存在主页链接，则读取聚合页信息
            if("1".equals(prodLeftSlide.getJoinpageJump()) && "2".equals(prodJumpLink.getJumpLinkType())){
                String indexProdUrl = prodJumpLink.getJumpLink();
                String subprod = indexProdUrl.split("id=")[1];
                Long indexProdId = Long.parseLong(subprod);

                Prod prod = prodService.findById(indexProdId);
                ProdPrice prodPrice = prodPriceService.findById(indexProdId);
                ProdTheme prodTheme = prodThemeService.findById(indexProdId);
                AppProdLeftSlideProdDetailVO appProdLeftSlideProdDetailVO = new AppProdLeftSlideProdDetailVO();
                appProdLeftSlideProdDetailVO.setId(prod.getId());
                appProdLeftSlideProdDetailVO.setName(prod.getName());
                appProdLeftSlideProdDetailVO.setSales(prod.getSales());
                appProdLeftSlideProdDetailVO.setShopId(prod.getShopId());
                appProdLeftSlideProdDetailVO.setTips(prod.getTips());
                appProdLeftSlideProdDetailVO.setShowImg(prodTheme.getShowImg());
                appProdLeftSlideProdDetailVO.setProdComboId(prodPrice.getProdComboId());
                appProdLeftSlideProdDetailVO.setComboMinPrice(prodPrice.getComboMinPrice());
                appProdLeftSlideProdDetailVO.setComboMinStandardPrice(prodPrice.getComboMinStandardPrice());

                vo.setAppProdLeftSlideProdDetailVO(appProdLeftSlideProdDetailVO);
            }
        }
        vo.setProdJumpLinkVOArrayList(prodJumpLinkVOS);
        return vo;
    }

    @Override
    public AppProdDecFloorVO findProdDecFloor(Long prodId) {

        ProdDecFloor prodDecFloor = prodDecFloorService.findById(prodId);
        AppProdDecFloorVO vo = AppProdDecFloorMapStruct.INSTANCE.converToVO(prodDecFloor);

        //设置落地页信息
        vo.setPageTitle(StringUtils.isBlank(vo.getPageTitle()) ? "仅此页面下单有优惠" : vo.getPageTitle());
        vo.setBuyBtnTitle("超值特惠，立即下单");
        vo.setFloatBtnTitle(StringUtils.isBlank(vo.getFloatBtnTitle()) ? "点我购买，优先发货" : vo.getFloatBtnTitle());
        vo.setCountdownOffers(StringUtils.isBlank(vo.getCountdownOffers()) ? "1" : vo.getCountdownOffers());
        vo.setCountdownTitle(StringUtils.isBlank(vo.getCountdownTitle()) ? "只此页面享受优惠，倒计时" : vo.getCountdownTitle());
        vo.setAdvBackType(StringUtils.isBlank(vo.getAdvBackType()) ? "BasicConstant.STATE_2" : vo.getAdvBackType());
        vo.setCountdownMinute(vo.getCountdownMinute() == null ? 30 : vo.getCountdownMinute());

        QueryWrapper<ProdAdvBackAccount> prodAdvBackAccountQueryWrapper = new QueryWrapper<>();
        prodAdvBackAccountQueryWrapper.eq(ProdAdvBackAccount.ID, prodId);
        List<ProdAdvBackAccountVO> list = new ArrayList<>();
        List<ProdAdvBackAccount> prodAdvBackAccounts = prodAdvBackAccountService.list(prodAdvBackAccountQueryWrapper);
        for (ProdAdvBackAccount prodAdvBackAccount : prodAdvBackAccounts) {
            ProdAdvBackAccountVO prodAdvBackAccountVO = ProdAdvBackAccountMapStruct.INSTANCE.converToVO(prodAdvBackAccount);
            list.add(prodAdvBackAccountVO);
        }
        vo.setProdAdvBackAccountVOList(list);

        return vo;
    }


    @Override
    public AddParametersVo addParameters(Long prodId){
        AddParametersVo vo = new AddParametersVo();
        ProdLeftSlide slide = Optional.ofNullable(prodLeftSlideService.findById(prodId)).orElseGet(ProdLeftSlide::new);
        List<ProdReturn> prodReturns = Optional.ofNullable(prodReturnService.list(new LambdaQueryWrapper<ProdReturn>().eq(ProdReturn::getProdId, prodId).orderByAsc(ProdReturn::getSort))).orElseGet(ArrayList::new);
        Prod prod = Optional.ofNullable(prodService.findById(prodId)).orElseGet(Prod::new);
        vo.setJoinpageBannerUrl(slide.getJoinpageBannerUrl());
        vo.setUploadGoodsImg(slide.getUploadGoodsImg());
        vo.setBottomBannerImg(slide.getBottomBannerImg());
        vo.setPictureFirstPicture(slide.getPictureFirstPicture());
        vo.setBackgroundColor(slide.getBackgroundColor());
        vo.setJoinpageOpenUrl(slide.getJoinpageOpenUrl());
        vo.setShopId(prod.getShopId());

        if (slide.getMarketingPageSet() == null || BasicConstant.INT_0.equals(slide.getMarketingPageSet())){
            prodReturns = prodReturns.stream().filter(p -> p.getSort() > 2).collect(Collectors.toList());
            slide.setJoinpageOpenUrl("");
            slide.setJoinpageJump("");
            slide.setJoinpageBannerUrl("");
            slide.setPictureFirstPicture("");
            slide.setBackgroundColor("");
        }
        if (slide.getMlcReflowSet() == null ||  BasicConstant.INT_0.equals(slide.getMlcReflowSet())){
            prodReturns = prodReturns.stream().filter(p -> p.getSort() < 4).collect(Collectors.toList());
        }
        vo.setReturns(prodReturns);
        return vo;
    }


    @Override
    public AppProdDetailVO shoppingProdById(Long id){
        Prod tMmdmProd = prodService.findById(id);
        if (ObjectUtil.isNull(tMmdmProd.getShoppingProdId()) || ObjectUtil.isNull(tMmdmProd.getShoppingComboId())){
            return null;
        }

        AppProdDetailVO vo = new AppProdDetailVO();
        Prod prod = prodService.findById(tMmdmProd.getShoppingProdId());
        if (ObjectUtil.isNull(prod)){
            return null;
        }
        Long prodId = prod.getId();
        vo.setId(prodId);
        vo.setName(prod.getName());
        vo.setSales(prod.getSales());
        vo.setShopId(prod.getShopId());
        vo.setSafeguard(prod.getSafeguard());
        vo.setGoodsType(prod.getGoodsType());
        vo.setIsOpenCashOnDeliverySms(Optional.ofNullable(prod.getIsOpenCashOnDeliverySms()).orElse(BasicConstant.INT_0));

        ArrayList<Map<String, String>> payTypeList = new ArrayList<>();
        if (StrUtil.isNotBlank(prod.getPayType())) {
            for (String s : prod.getPayType().split(",")) {
                HashMap<String, String> map = new HashMap<>();
                if (DictConstant.PayType.TYPE_1.getCode().equals(s)) {
                    map.put(s, BasicConstant.PayType.WX_PAY);
                } else if (DictConstant.PayType.TYPE_2.getCode().equals(s)) {
                    map.put(s, BasicConstant.PayType.CASH_ON_DELIVERY);
                }
                payTypeList.add(map);
            }
        }else {//如果没有 默认显示微信支付
            HashMap<String, String> map = new HashMap<>();
            map.put(DictConstant.PayType.TYPE_1.getCode(), BasicConstant.PayType.WX_PAY);
            payTypeList.add(map);
        }
        vo.setPayTypeList(payTypeList);

        QueryWrapper<ProdMainimg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ProdMainimg.ID, prodId);
        List<ProdMainimg> prodMainimgs = prodMainimgService.list(queryWrapper);
        List<String> mainimgs = new ArrayList<>();
        for (ProdMainimg prodMainimg : prodMainimgs) {
            mainimgs.add(prodMainimg.getMainImg());
        }
        vo.setMainimgs(mainimgs);

        //商品服务承诺
        ProdServiceCommitmentDTO serviceCommitmentDTO = prodServiceCommitmentService.getByProdId(prodId);
        ArrayList<String> list = new ArrayList<>();
        Map<String, String> serviceMap = new HashMap<>();
        //将状态码和服务承诺对应关系存储在Map中
        serviceMap.put(BasicConstant.STATE_1, BasicConstant.ServiceCommitment.SERVICE_COMMITMENT_STATUS_1);
        serviceMap.put(BasicConstant.STATE_2, BasicConstant.ServiceCommitment.SERVICE_COMMITMENT_STATUS_2);
        serviceMap.put(BasicConstant.STATE_3, BasicConstant.ServiceCommitment.SERVICE_COMMITMENT_STATUS_3);
        serviceMap.put(BasicConstant.STATE_4, BasicConstant.ServiceCommitment.SERVICE_COMMITMENT_STATUS_4);
        serviceMap.put(BasicConstant.STATE_5, BasicConstant.ServiceCommitment.SERVICE_COMMITMENT_STATUS_5);
        serviceMap.put(BasicConstant.STATE_6, BasicConstant.ServiceCommitment.SERVICE_COMMITMENT_STATUS_6);
        serviceMap.put(BasicConstant.STATE_7, BasicConstant.ServiceCommitment.SERVICE_COMMITMENT_STATUS_7);
        if (serviceCommitmentDTO != null && !serviceCommitmentDTO.getServiceList().isEmpty()) {
            for (String s : serviceCommitmentDTO.getServiceList()) {
                //将状态码对应的服务承诺添加到list中
                if (serviceMap.containsKey(s)) {
                    list.add(serviceMap.get(s));
                }
            }
            serviceCommitmentDTO.setServiceList(list);
        }
        vo.setProdServiceCommitmentDTO(serviceCommitmentDTO);
        ProdFeight prodFeight = prodFeightService.findById(prodId);
        vo.setFeightType(prodFeight.getFeightType());
        vo.setFeightAmount(prodFeight.getFeightAmount());
        //如果取值为运费模板，则读取模块信息
        if ("2".equals(prodFeight.getFeightType())) {
            FeightTemplate feightTemplate = feightTemplateService.findById(prodFeight.getFeightTemplateId());
            AppFeightTemplateVO appFeightTemplateVO = AppFeightTemplateMapStruct.INSTANCE.converToVO(feightTemplate);

            List<AppFeightTemplateDetailVO> appFeightTemplateDetailVOList = new ArrayList<>();
            QueryWrapper<FeightTemplateDetail> feightTemplateDetailQueryWrapper = new QueryWrapper<>();
            feightTemplateDetailQueryWrapper.eq(FeightTemplateDetail.ID, feightTemplate.getId());
            List<FeightTemplateDetail> feightTemplateDetailList = feightTemplateDetailService.list(feightTemplateDetailQueryWrapper);
            for (FeightTemplateDetail feightTemplateDetail : feightTemplateDetailList) {
                AppFeightTemplateDetailVO appFeightTemplateDetailVO = AppFeightTemplateDetailMapStruct.INSTANCE.converToVO(feightTemplateDetail);
                List<AppFeightTemplateRegionVO> appFeightTemplateRegionVOList = new ArrayList<>();
                QueryWrapper<FeightTemplateRegion> feightTemplateRegionQueryWrapper = new QueryWrapper<>();
                feightTemplateRegionQueryWrapper.eq(FeightTemplateRegion.ID, feightTemplate.getId());
                feightTemplateRegionQueryWrapper.eq(FeightTemplateRegion.ENTRY_ID, feightTemplateDetail.getEntryId());
                List<FeightTemplateRegion> feightTemplateRegionList = feightTemplateRegionService.list(feightTemplateRegionQueryWrapper);
                for (FeightTemplateRegion feightTemplateRegion : feightTemplateRegionList) {
                    AppFeightTemplateRegionVO appFeightTemplateRegionVO = AppFeightTemplateRegionMapStruct.INSTANCE.converToVO(feightTemplateRegion);
                    appFeightTemplateRegionVOList.add(appFeightTemplateRegionVO);
                }
                appFeightTemplateDetailVO.setAppFeightTemplateRegionVOList(appFeightTemplateRegionVOList);
                appFeightTemplateDetailVOList.add(appFeightTemplateDetailVO);
            }
            appFeightTemplateVO.setAppFeightTemplateDetailVOList(appFeightTemplateDetailVOList);
            vo.setAppFeightTemplateVO(appFeightTemplateVO);
        }

        //设置商户类型
        Business business = businessService.findById(prod.getBusinessId());
        vo.setBusinessType(business.getBusinessType());

        ProdStandard prodStandard = prodStandardService.findProdStandard();
        vo.setPayFailThemeUrl(prodStandard.getPayFailThemeUrl());

        QueryWrapper<ProdDetail> queryWrapperProdDetail = new QueryWrapper<>();
        queryWrapperProdDetail.eq(ProdDetail.ID, prodId);
        List<ProdDetail> prodDetails = prodDetailService.list(queryWrapperProdDetail);
        List<String> prodDetailImgs = new ArrayList<>();
        for (ProdDetail prodDetail : prodDetails) {
            prodDetailImgs.add(prodDetail.getDetailImg());
        }
        vo.setProdDetailImgs(prodDetailImgs);

        ProdTheme prodTheme = prodThemeService.findById(prodId);
        AppProdThemeVO appProdThemeVO = AppProdThemeMapStruct.INSTANCE.converToVO(prodTheme);
        vo.setAppProdThemeVO(appProdThemeVO);

        ProdLeftSlide slide = prodLeftSlideService.findById(prodId);
        vo.setFullDiscountOnOff(ObjectUtil.isNull(slide.getFullDiscountOnOff()) ? BasicConstant.INT_0 : slide.getFullDiscountOnOff());
        vo.setFullDiscountMoney(ObjectUtil.isNull(slide.getFullDiscountMoney()) ? new BigDecimal(BigInteger.ZERO) : slide.getFullDiscountMoney());
        vo.setDefaultPaymentType(ObjectUtil.isNull(slide.getDefaultPaymentType()) ? BasicConstant.INT_0 : slide.getDefaultPaymentType());

        ProdPrice prodPrice = prodPriceService.findById(prodId);
        vo.setComboMinPrice(prodPrice.getComboMinPrice());
        vo.setComboMinStandardPrice(prodPrice.getComboMinStandardPrice());
        vo.setSubsidyPrice(prodPrice.getSubsidyPrice());
        vo.setIsFailPayHint(prodPrice.getIsFailPayHint());
        vo.setDiscountPrice(prodPrice.getDiscountPrice());

        //套餐信息
        ProdCombo prodCombo = prodComboService.getById(tMmdmProd.getShoppingComboId());
        if (ObjectUtil.isNull(prodCombo)){
            return null;
        }
        List<AppProdComboVO> appProdComboVOS = new ArrayList<>();
        AppProdComboVO appProdComboVO = AppProdComboMapStruct.INSTANCE.converToVO(prodCombo);
        QueryWrapper<ProdComboAttrRelate> prodComboAttrRelateQueryWrapper = new QueryWrapper<>();
        prodComboAttrRelateQueryWrapper.eq(ProdComboAttrRelate.PROD_ID, prodId);
        prodComboAttrRelateQueryWrapper.eq(ProdComboAttrRelate.PROD_COMBO_ID, prodCombo.getId());
        prodComboAttrRelateQueryWrapper.orderByAsc(ProdComboAttrRelate.ID);
        List<ProdComboAttrRelate> prodComboAttrRelates = prodComboAttrRelateService.list(prodComboAttrRelateQueryWrapper);
        List<AppProdComboAttrRelateVO> appProdComboAttrRelateVOS = new ArrayList<>();
        for (ProdComboAttrRelate prodComboAttrRelate : prodComboAttrRelates) {
            AppProdComboAttrRelateVO attrRelateVO = AppProdComboAttrRelateMapStruct.INSTANCE.converToVO(prodComboAttrRelate);
            attrRelateVO.setAttrValues(StringUtils.isNotBlank(attrRelateVO.getAttrValues()) ? attrRelateVO.getAttrValues().split(",")[0] : null);
            appProdComboAttrRelateVOS.add(attrRelateVO);
        }
        appProdComboVO.setAppProdComboAttrRelateVOS(appProdComboAttrRelateVOS);
        appProdComboVO.setPaidInAmount(appProdComboVO.getComboPrice());
        appProdComboVOS.add(appProdComboVO);

        BigDecimal minPriceSpec = null;
        if (ObjectUtil.isNull(minPriceSpec)){
            minPriceSpec = prodCombo.getComboPrice();
            vo.setMinPriceSpec(prodCombo.getSellPoint());
        }
        //获取最低实付价
        if (appProdComboVO.getComboPrice().compareTo(vo.getComboMinPrice()) == BasicConstant.INT_0
                && appProdComboVO.getComboStandardPrice().compareTo(vo.getComboMinStandardPrice()) == BasicConstant.INT_0){
            vo.setComboMinPaidInAmount(appProdComboVO.getPaidInAmount());
        }

        vo.setAppProdComboVOS(appProdComboVOS);

        //获取营销页数据
        vo.setJoinpageOpenUrl(slide.getJoinpageOpenUrl());
        vo.setJoinpageBannerUrl(slide.getJoinpageBannerUrl());
        //虚拟按键
        vo.setVirtualKey(slide.getVirtualKey());

        vo.setSetMealType(prod.getSetMealType());
        return vo;
    }


    public static Map<String,List<AppProdDetailVO.EchoMuchSize>> echoMuchSizes(List<ProdCombo> prodCombos) {
        if (CollectionUtil.isEmpty(prodCombos)){
            return null;
        }
        Map<String,List<AppProdDetailVO.EchoMuchSize>> map = new LinkedHashMap<>();
        //获取规格名
        String comboTitle = Optional.ofNullable(prodCombos.get(0).getComboTitle()).orElseGet(() -> "");
        //创建key对应的values
        for (String key : comboTitle.split(BasicConstant.CLEAVER)) {
            map.put(key, new ArrayList<>());
        }
        //获取规格名 对应 配图的map
        for (ProdCombo prodCombo : prodCombos) {
            int index = 0; //规格名的索引
            String[] sellPoints = prodCombo.getSellPoint().split(BasicConstant.CLEAVER);
            for (String key : map.keySet()) {
                List<AppProdDetailVO.EchoMuchSize> echoMuchSizes = map.get(key);
                String value = sellPoints[index];
                //判断当前规格名对应规格值得map中是否有这个规格值存在,没有就添加进去,有就不做处理
                if(!echoMuchSizes.stream().filter(e->e.getValue().equals(value)).findAny().isPresent()) {
                    AppProdDetailVO.EchoMuchSize echoMuchSize = new AppProdDetailVO.EchoMuchSize();
                    echoMuchSize.setValue(value);
                    echoMuchSize.setImg(prodCombo.getImg());
                    echoMuchSizes.add(echoMuchSize);
                    map.put(key,echoMuchSizes);
                }
                index ++;
            }
        }
        return map;
    }
}
