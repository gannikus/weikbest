package com.weikbest.pro.saas.merchat.prod.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.constant.BasicConstant;
import com.weikbest.pro.saas.common.constant.ImgBasicConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.ext.user.UserExtService;
import com.weikbest.pro.saas.common.ext.user.UserExtServiceFactory;
import com.weikbest.pro.saas.common.ext.user.entity.UserExt;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.merchat.order.entity.OrderInfo;
import com.weikbest.pro.saas.merchat.order.service.OrderInfoService;
import com.weikbest.pro.saas.merchat.prod.entity.*;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdMapper;
import com.weikbest.pro.saas.merchat.prod.module.dto.*;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdComboMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdPurchaseRestrictionsMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdQO;
import com.weikbest.pro.saas.merchat.prod.module.qo.SlideFloorQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.*;
import com.weikbest.pro.saas.merchat.prod.service.*;
import com.weikbest.pro.saas.merchat.shop.entity.Shop;
import com.weikbest.pro.saas.merchat.shop.service.ShopService;
import com.weikbest.pro.saas.sys.common.constant.CodeRuleConstant;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.entity.AppletConfig;
import com.weikbest.pro.saas.sys.param.service.AppletConfigService;
import com.weikbest.pro.saas.sys.param.entity.ThirdConfig;
import com.weikbest.pro.saas.sys.param.service.CodeRuleService;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品基本信息表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ProdServiceImpl extends ServiceImpl<ProdMapper, Prod> implements ProdService {

    @Resource
    private CodeRuleService codeRuleService;

    @Resource
    private UserExtServiceFactory userExtServiceFactory;

    @Resource
    private ProdComboService prodComboService;

    @Resource
    private ProdPriceService prodPriceService;

    @Resource
    private ProdFeightService prodFeightService;

    @Resource
    private ProdThemeService prodThemeService;

    @Resource
    private ProdDetailService prodDetailService;

    @Resource
    private ProdMainimgService prodMainimgService;

    @Resource
    private ProdDecFloorService prodDecFloorService;

    @Resource
    private ProdLeftSlideService prodLeftSlideService;

    @Resource
    private ProdCouponService prodCouponService;

    @Resource
    private ProdAppletRelateService prodAppletRelateService;

    @Resource
    private OrderInfoService orderInfoService;

    @Autowired
    private ProdReturnService prodReturnService;

    @Autowired
    private ProdAdvBackAccountService prodAdvBackAccountService;

    @Resource
    private ProdServiceCommitmentService prodServiceCommitmentService;

    @Resource
    private ThirdConfigService thirdConfigService;

    @Autowired
    private AppletConfigService appletConfigService;

    @Resource
    private ProdPurchaseRestrictionsService prodPurchaseRestrictionsService;

    @Resource
    private ShopService shopService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(ProdDTO prodDTO) {
        Prod prod = ProdMapStruct.INSTANCE.converToEntity(prodDTO);
        Long prodId = GenerateIDUtil.nextId();
        String number = codeRuleService.nextNum(CodeRuleConstant.T_MMDM_PROD);
        prod.setId(prodId);
        prod.setNumber(number);
        boolean save = this.save(prod);

        ProdDecFloor floor = new ProdDecFloor();
        floor.setId(prodId);
        floor.setPageTitle("仅此页面下单有优惠");
        floor.setBuyBtnTitle( "超值特惠，立即下单");
        floor.setFloatBtnTitle("点我购买，优先发货");
        floor.setCountdownOffers("1");
        floor.setCountdownTitle("只此页面享受优惠，倒计时");
        floor.setCountdownMinute(30);
        floor.setAdvBackType(BasicConstant.STATE_2);
        if (!prodDecFloorService.save(floor)){
            throw new WeikbestException("保存落地页信息时出现了异常");
        }

        // 保存商品的其他关联信息
        saveOrUpdateRelateProd(prodId, prodDTO);

        return save;
    }

    /**
     * 保存商品的其他关联信息
     *
     * @param prodId
     * @param prodDTO
     */
    private void saveOrUpdateRelateProd(Long prodId, ProdDTO prodDTO) {
        if (CollectionUtil.isEmpty(prodDTO.getProdComboDTOList())){
            throw new WeikbestException("当前商品并未设置商品套餐!");
        }
        // 保存销售套餐信息，返回目前套餐价格最低的套餐信息
        ProdCombo prodCombo = prodComboService.insertBatchWithProd(prodId ,prodDTO.getSetMealType(), prodDTO.getGoodsType(), prodDTO.getProdComboDTOList());

        // 保存商品价格信息
        prodPriceService.saveOrUpdateByProdComboWithProd(prodId, prodCombo, prodDTO.getProdPriceDTO());

        // 保存商品运费信息
        prodFeightService.saveOrUpdateWithProd(prodId, prodDTO.getProdFeightDTO());

        // 保存商品样式信息
        prodThemeService.saveOrUpdateWithProd(prodId, prodDTO.getProdThemeDTO());

        // 保存商品详情信息
        prodDetailService.insertBatchWithProd(prodId, prodDTO.getProdDetailDTOList());

        // 保存商品详情页轮播图信息
        prodMainimgService.insertBatchWithProd(prodId, prodDTO.getProdMainimgDTOList());

        // 保存商品服务承诺信息
        prodServiceCommitmentService.saveOrUpdateWithProd(prodId,prodDTO.getProdServiceCommitmentDTO());

        //保存货更新限购配置
        prodPurchaseRestrictionsService.saveOrUpdateWithProd(prodId,prodDTO.getProdPurchaseRestrictionsDto());

    }

    @Override
    public boolean updateById(Long id, ProdDTO prodDTO) {
        Prod prod = this.findById(id);
        prod = ProdMapStruct.INSTANCE.converToEntity(prodDTO);
        prod.setId(id);
        boolean update = this.updateById(prod);

        // 保存商品的其他关联信息
        saveOrUpdateRelateProd(id, prodDTO);

        return update;
    }

    @Override
    public Prod findById(Long id) {
        Prod prod = this.getById(id);
        if (ObjectUtil.isNull(prod)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return prod;
    }

    @Override
    public ProdShowDetailVO findDetailVOById(Long id) {
        Prod prod = this.findById(id);
        ProdShowDetailVO prodShowDetailVO = ProdMapStruct.INSTANCE.converToShowDetailVO(prod);
        Integer setMealType = prod.getSetMealType() == null ? 1 : prod.getSetMealType();
        List<ProdComboVO> prodComboVOList = prodComboService.queryVOByProdId(id , setMealType);
        prodShowDetailVO.setProdComboVOList(prodComboVOList);

        //如果开启，返回限购信息
        if (BasicConstant.STATE_1.equals(prod.getIsOpenOrderLimit())){
            ProdPurchaseRestrictions byId = prodPurchaseRestrictionsService.getById(prod.getId());
            if (ObjectUtil.isNotNull(byId)){
                ProdPurchaseRestrictionsVo prodPurchaseRestrictionsVo = ProdPurchaseRestrictionsMapStruct.INSTANCE.converToVO(byId);
                prodShowDetailVO.setProdPurchaseRestrictionsVo(prodPurchaseRestrictionsVo);
            }
        }

        ProdPriceVO prodPriceVO = prodPriceService.getVOById(id);
        prodShowDetailVO.setProdPriceVO(prodPriceVO);

        ProdFeightVO prodFeightVO = prodFeightService.getVOById(id);
        prodShowDetailVO.setProdFeightVO(prodFeightVO);

        ProdThemeVO prodThemeVO = prodThemeService.getVOById(id);
        prodShowDetailVO.setProdThemeVO(prodThemeVO);

        List<ProdDetailVO> prodDetailVOList = prodDetailService.queryVOByProdId(id);
        prodShowDetailVO.setProdDetailVOList(prodDetailVOList);

        List<ProdMainimgVO> prodMainimgVOList = prodMainimgService.queryVOByProdId(id);
        prodShowDetailVO.setProdMainimgVOList(prodMainimgVOList);

        //根据商品id查询服务承诺
        ProdServiceCommitmentDTO prodServiceCommitmentDTO = prodServiceCommitmentService.getByProdId(id);
        prodShowDetailVO.setProdServiceCommitmentDTO(prodServiceCommitmentDTO);

//        ProdDecFloorVO prodDecFloorVO = prodDecFloorService.getVOById(id);
//        prodShowDetailVO.setProdDecFloorVO(prodDecFloorVO);
//
//        ProdLeftSlideVO prodLeftSlideVO = prodLeftSlideService.getVOById(id);
//        prodShowDetailVO.setProdLeftSlideVO(prodLeftSlideVO);
//
//        ProdCouponVO prodCouponVO = prodCouponService.getVOById(id);
//        prodShowDetailVO.setProdCouponVO(prodCouponVO);
//
//        List<ProdAppletRelateVO> prodAppletRelateVOList = prodAppletRelateService.queryVOByProdId(id);
//        prodShowDetailVO.setProdAppletRelateVOList(prodAppletRelateVOList);

        prodShowDetailVO.setIsImg(0);
        //设置多规格的回显
        if (ObjectUtil.isNotNull(setMealType) && BasicConstant.INT_2.equals(setMealType)){
            prodShowDetailVO.setEchoMuchSizes(echoMuchSizes(prodShowDetailVO));
        }
        prodShowDetailVO.setSetMealType(setMealType);

        return prodShowDetailVO;
    }

    @Override
    public List<ProdShowDetailVO> queryDetailVOById(List<Long> prodIdList) {
        List<ProdShowDetailVO> resultList = new ArrayList<>(prodIdList.size());
        for (Long prodId : prodIdList) {
            resultList.add(findDetailVOById(prodId));
        }
        return resultList;
    }

    @Override
    public IPage<ProdListVO> queryPage(ProdQO prodQO, PageReq pageReq) {
        IPage<ProdListVO> prodListVOIPage = this.baseMapper.queryPage(new Page<>(pageReq.getPage(), pageReq.getLimit()), prodQO);
        List<ProdListVO> records = prodListVOIPage.getRecords();

        // 根据商品ID查询订单
        List<Long> prodIdList = records.stream().map(ProdListVO::getId).collect(Collectors.toList());
        List<Long> aDProdIdList = records.stream().filter(p -> BasicConstant.STATE_2.equals(p.getGoodsType())).map(ProdListVO::getId).collect(Collectors.toList()); //只留下广告商品的Id
        if(CollectionUtil.isNotEmpty(prodIdList)) {
            //设置列表中的回传按钮
            List<SlideFloorQO> slideFloorS = new ArrayList<>();
            List<ProdAdvBackAccount> advBackAccounts = new ArrayList<>();
            if (CollectionUtil.isNotEmpty(aDProdIdList)){
                slideFloorS = this.baseMapper.getSlideFloorByIds(aDProdIdList);
                advBackAccounts = prodAdvBackAccountService.list(new LambdaQueryWrapper<ProdAdvBackAccount>().in(ProdAdvBackAccount::getId, aDProdIdList));
            }
            List<OrderInfo> orderInfoList = orderInfoService.list(new QueryWrapper<OrderInfo>().in(OrderInfo.PROD_ID, prodIdList).ne(OrderInfo.ORDER_STATUS , BasicConstant.STATE_1));
            //去除掉订单中已关闭中的未付款信息
            if (CollectionUtil.isNotEmpty(orderInfoList)){
                orderInfoList = orderInfoList.stream().filter(orderInfo -> !(BasicConstant.STATE_99.equals(orderInfo.getOrderStatus()) && BigDecimal.ZERO.compareTo(orderInfo.getPayAmount()) == 0)).collect(Collectors.toList());
            }
            Map<Long, List<OrderInfo>> orderInfoListMap = orderInfoList.stream().collect(Collectors.groupingBy(OrderInfo::getProdId));
            Map<Long, SlideFloorQO> slideFloorQOMap = slideFloorS.stream().collect(Collectors.toMap(SlideFloorQO::getId, slideFloorQO -> slideFloorQO));
            Map<Long, List<ProdAdvBackAccount>> advBackAccountListMap = advBackAccounts.stream().collect(Collectors.groupingBy(ProdAdvBackAccount::getId));

            // 查询更新人
            UserExtService userExtService = userExtServiceFactory.getUserExtService(DictConstant.UserRelateType.sys.getCode());
            records.forEach(prodListVO -> {
                Long prodId = prodListVO.getId();
                UserExt user = userExtService.getUser(prodListVO.getModifier());
                prodListVO.setModifierName(user.getName());

                List<OrderInfo> orderInfos = orderInfoListMap.get(prodId);
                if(CollectionUtil.isNotEmpty(orderInfos)) {
                    Map<String, List<OrderInfo>> orderSourceOrderListMap = orderInfos.stream()
                            .collect(Collectors.groupingBy(OrderInfo::getOrderSource));

                    Map<String, Integer> orderSourceCountMap = new HashMap<>(orderSourceOrderListMap.size());
                    for (Map.Entry<String, List<OrderInfo>> entry : orderSourceOrderListMap.entrySet()) {
                        orderSourceCountMap.put(entry.getKey(), entry.getValue().size());
                    }
                    prodListVO.setRealSales(orderInfos.size());
                    prodListVO.setOrderSourceCountMap(orderSourceCountMap);
                }else{
                    prodListVO.setRealSales(0);
                }

                prodListVO.setBackIsOpen(BasicConstant.STATE_0);
                if (slideFloorQOMap.containsKey(prodId) && advBackAccountListMap.containsKey(prodId)){
                    SlideFloorQO slideFloorQO = slideFloorQOMap.get(prodId);
                    ProdAdvBackAccount account = advBackAccountListMap.get(prodId).get(0);
                    if (StringUtils.isNotBlank(slideFloorQO.getAdLinksName()) || ObjectUtil.isNotNull(account.getAdvAccountId())){
                        prodListVO.setBackIsOpen(BasicConstant.STATE_1);
                        prodListVO.setSuccessPayBack(slideFloorQO.getSuccessPayBack());
                        prodListVO.setBackRatio(account.getBackRatio());
                    }
                }
            });
        }
        return prodListVOIPage;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateProdStatus(List<Long> ids, String prodStatus) {
        List<Prod> prodList = this.listByIds(ids);
        prodList.forEach(prod -> prod.setProdStatus(prodStatus));
        return this.updateBatchById(prodList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateJoinShopMainpage(List<Long> ids, String joinShopMainpage) {
        List<Prod> prodList = this.listByIds(ids);
        prodList.forEach(prod -> prod.setJoinShopMainpage(joinShopMainpage));
        return this.updateBatchById(prodList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateProdFeight(List<Long> ids, ProdFeightDTO prodFeightDTO) {

        return prodFeightService.updateProdFeight(ids, prodFeightDTO);
    }

    @Override
    public ProdDecFloorVO getProdDecFloorVOById(Long id) {
        return prodDecFloorService.getVOById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrUpdateProdDecFloor(Long id, ProdDecFloorDTO prodDecFloorDTO) {
        return prodDecFloorService.saveOrUpdateProdDecFloor(id, prodDecFloorDTO);
    }

    @Override
    public ProdLeftSlideVO getProdLeftSlideVOById(Long id) {
        return prodLeftSlideService.getVOById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrUpdateProdLeftSlide(Long id, ProdLeftSlideDTO prodLeftSlideDTO) {
        return prodLeftSlideService.saveOrUpdateProdLeftSlide(id, prodLeftSlideDTO);
    }

    @Override
    public List<ProdCouponVO> queryProdCouponVOById(Long id) {
        return prodCouponService.queryProdCouponVOById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrUpdateProdCouponDTO(Long id, ProdCouponDTO prodCouponDTO) {
        //设置默认领劵弹窗
        if (StringUtils.isBlank(prodCouponDTO.getCouponOpenImg())){
            ThirdConfig thirdConfig = thirdConfigService.findThirdConfig(true);
            if (ImgBasicConstant.CY_ALIYUN_OSS_FILE_BUCKETNAME.equals(thirdConfig.getAliyunOssFileBucketname())){
                prodCouponDTO.setCouponOpenImg(ImgBasicConstant.CY_COUPON_OPEN_IMG);
            }else if (ImgBasicConstant.HW_ALIYUN_OSS_FILE_BUCKETNAME.equals(thirdConfig.getAliyunOssFileBucketname())){
                prodCouponDTO.setCouponOpenImg(ImgBasicConstant.HW_COUPON_OPEN_IMG);
            }
        }
        return prodCouponService.saveOrUpdateProdCouponDTO(id, prodCouponDTO);
    }

    @Override
    public ProdAppletRelateVO findProdAppletRelateVOById(Long id) {
        return prodAppletRelateService.findVOById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrUpdateProdAppletRelate(Long prodId, ProdAppletRelateDTO prodAppletRelateDTO) {
        return prodAppletRelateService.saveOrUpdateProdAppletRelate(prodId, prodAppletRelateDTO);
    }

    @Override
    public MinProdComboVO findMinProdComboVOById(Long id) {
        ProdPrice prodPrice = prodPriceService.findById(id);
        Long prodComboId = prodPrice.getProdComboId();
        ProdCombo prodCombo = prodComboService.findById(prodComboId);
        return ProdComboMapStruct.INSTANCE.converToMinProdComboVO(prodCombo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateMinProdCombo(MinProdComboDTO minProdComboDTO) {
        Prod prod = this.getById(minProdComboDTO.getProdId());
        if (ObjectUtil.isNull(prod)){
            throw new WeikbestException("商品已经不存在!");
        }
        // 更新商品销售套餐信息
        ProdCombo prodCombo = prodComboService.findById(minProdComboDTO.getId());
        ProdComboMapStruct.INSTANCE.copyProperties(minProdComboDTO, prodCombo);
        prodCombo.setId(minProdComboDTO.getId());
        prodComboService.updateById(prodCombo);

        // 查询目前最低金额套餐
        ProdCombo minProdCombo = prodComboService.findMinProdComboByProdId(prod.getId() , prod.getSetMealType());
        // 更新商品价格表
        ProdPrice prodPrice = prodPriceService.findById(minProdComboDTO.getProdId());
        prodPrice.setProdComboId(minProdCombo.getId());
        prodPrice.setComboMinPrice(minProdCombo.getComboPrice());
        prodPrice.setComboMinStandardPrice(minProdCombo.getComboStandardPrice());
        return prodPriceService.updateById(prodPrice);
    }

    @Override
    public List<AppProdDTO> queryPageCommodityByCouponId(Map<String, Object> paramMap){
        return this.baseMapper.queryPageCommodityByCouponId(paramMap);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addAdLinks(AdLinksDTO dto){
        //设置默认值
        if (StringUtils.isBlank(dto.getJoinpageBannerUrl())) dto.setJoinpageBannerUrl(BasicConstant.JOINPAGE_BANNER);
        if (ObjectUtil.isNotNull(dto.getMarketingPageSet()) && BasicConstant.INT_1.equals(dto.getMarketingPageSet()) && StringUtils.isBlank(dto.getJoinpageOpenUrl())) dto.setJoinpageOpenUrl(BasicConstant.JOINPAGE_OPEN);

        Long prodId = dto.getProdId();

        Prod prod = this.getById(prodId);
        Shop shop = shopService.findById(prod.getShopId());

        //更新支付方式
        prod.setId(prodId)
                .setCustomerServiceSwitch(StrUtil.isBlank(dto.getCustomerServiceSwitch()) ? BasicConstant.STATE_0 : dto.getCustomerServiceSwitch())
                .setPayType(dto.getPayType())
                .setIsOpenCashOnDeliverySms(ObjectUtil.isNull(dto.getIsOpenCashOnDeliverySms()) ? BasicConstant.INT_0 : dto.getIsOpenCashOnDeliverySms());
        this.saveOrUpdate(prod);
        //保存落地页信息
        ProdDecFloor floor = new ProdDecFloor();
        floor.setId(prodId);
        floor.setBuyBtnTitle(StringUtils.isBlank(dto.getBuyBtnTitle()) ? "超值特惠，立即下单" : dto.getBuyBtnTitle());
        floor.setFloatBtnTitle(StringUtils.isBlank(dto.getFloatBtnTitle()) ? "点我购买，优先发货" : dto.getFloatBtnTitle());
        floor.setCountdownOffers(StringUtils.isBlank(dto.getCountdownOffers()) ? "1" : dto.getCountdownOffers());
        floor.setCountdownTitle(StringUtils.isBlank(dto.getCountdownTitle()) ? "只此页面享受优惠，倒计时" : dto.getCountdownTitle());
        floor.setCountdownMinute(dto.getCountdownMinute() == null ? 30 : dto.getCountdownMinute());
        floor.setAdvBackType(BasicConstant.STATE_2);
        if (BasicConstant.STATE_1.equals(shop.getIsControlAdCallback())){
            floor.setSuccessPayBack(dto.getSuccessPayBack());
        }else {
            floor.setSuccessPayBack(BasicConstant.STATE_1);
        }
        if (!prodDecFloorService.saveOrUpdate(floor)){
            throw new WeikbestException("保存落地页信息时出现了异常");
        }

        //判断是否有修改回流开关的权限
        boolean isControlAdCallback = BasicConstant.STATE_1.equals(shop.getIsControlAdCallback()) ? true : false;
        //保存回传信息
        ProdAdvBackAccount account = prodAdvBackAccountService.findByProdIdAndAccountId(prodId,dto.getAdvAccountId());
        account.setId(prodId);
        account.setAdvAccountId(dto.getAdvAccountId());
        if (isControlAdCallback){
            account.setBackRatio(dto.getBackRatio());
        }else {
            account.setBackRatio(new BigDecimal(100));
        }
        prodAdvBackAccountService.deleteByProdId(prodId);
        if (!prodAdvBackAccountService.saveOrUpdate(account)){
            throw new WeikbestException("保存回传信息失败!");
        }

        //保存返回页信息
        BigDecimal marketingPageAmount = new BigDecimal(0);
        List<ProdReturn> returns = new ArrayList<>();
        if (dto.getBackCost() != null){
            ProdReturn aReturn = new ProdReturn();
            aReturn.setProdId(prodId);
            aReturn.setReturnPage(BasicConstant.INT_1);
            aReturn.setReturnAmount(dto.getBackCost());
            aReturn.setSort(BasicConstant.INT_1);
            aReturn.setOpenOrNot(BasicConstant.INT_1);
            returns.add(aReturn);
            marketingPageAmount = dto.getBackCost();
        }
        if (dto.getBackSecondCost() != null && BasicConstant.INT_1.equals(dto.getPageType())){
            ProdReturn aReturn = new ProdReturn();
            aReturn.setProdId(prodId);
            aReturn.setReturnPage(BasicConstant.INT_2);
            aReturn.setReturnAmount(dto.getBackSecondCost());
            aReturn.setSort(BasicConstant.INT_2);
            aReturn.setOpenOrNot(BasicConstant.INT_1);
            returns.add(aReturn);
            marketingPageAmount = dto.getBackSecondCost();
        }
        //返回三:营销页
        if (StringUtils.isNotBlank(dto.getJoinpageOpenUrl())){
            ProdReturn aReturn = new ProdReturn();
            aReturn.setProdId(prodId);
            aReturn.setReturnPage(BasicConstant.INT_3);
            aReturn.setReturnAmount(marketingPageAmount);
            aReturn.setSort(BasicConstant.INT_3);
            aReturn.setOpenOrNot(BasicConstant.INT_1);
            returns.add(aReturn);
        }
        if (CollectionUtil.isNotEmpty(dto.getMlcReflowSets())){
            List<AdLinksDTO.MlcReflowSet> mlcReflowSets = dto.getMlcReflowSets();
            mlcReflowSets.forEach(set -> {
                ProdReturn aReturn = new ProdReturn();
                aReturn.setProdId(prodId);
                aReturn.setReturnPage(set.getReflowType());
                aReturn.setReturnAmount(set.getReflowSum());
                aReturn.setSort(set.getSort());
                aReturn.setOpenOrNot(isControlAdCallback ? set.getOpenOrNot() : BasicConstant.INT_1);
                returns.add(aReturn);
            });
        }
        if (CollectionUtil.isNotEmpty(returns)){
            prodReturnService.deleteByProdId(prodId);
            if (!prodReturnService.saveOrUpdateBatch(returns)){
                throw new WeikbestException("更新返回页信息失败!");
            }
        }

        //保存营销页和落地页相关信息
        ProdLeftSlide slide = new ProdLeftSlide();
        slide.setId(prodId);
        slide.setAdLinksName(dto.getAdLinksName());
        slide.setJoinpageOpenUrl(dto.getJoinpageOpenUrl());
        slide.setJoinpageJump(dto.getJoinpageJump());
        slide.setJoinpageBannerUrl(dto.getJoinpageBannerUrl());
        slide.setUploadGoodsImg(dto.getUploadGoodsImg());
        slide.setBottomBannerImg(dto.getBottomBannerImg());
        slide.setPictureFirstPicture(dto.getPictureFirstPicture());
        slide.setBackgroundColor(dto.getBackgroundColor());
        slide.setVirtualKey(dto.getVirtualKey());
        slide.setMarketingPageSet(dto.getMarketingPageSet());
        slide.setPageType(dto.getPageType());
        slide.setMlcReflowSet(dto.getMlcReflowSet());
        slide.setAdLinksCreationTime(new Date(System.currentTimeMillis()));
        slide.setFullDiscountOnOff(dto.getFullDiscountOnOff());
        slide.setFullDiscountMoney(dto.getFullDiscountMoney());
        slide.setDefaultPaymentType(dto.getDefaultPaymentType());
        if (!prodLeftSlideService.saveOrUpdate(slide)){
            throw new WeikbestException("保存营销返回设置出现了异常");
        }

        //查询商品是否关联小程序,如果没有则设置默认关联小程序
        ProdAppletRelate appletRelate = prodAppletRelateService.getById(prodId);
        if (ObjectUtil.isNull(appletRelate)){
            AppletConfig config = appletConfigService.getStochasticAllocation();
            if (ObjectUtil.isNull(config)){
                throw new WeikbestException("请先检查小程序配置是否可用");
            }
            ProdAppletRelateDTO appletRelateDTO = new ProdAppletRelateDTO();
            appletRelateDTO.setAppletConfigId(config.getId());
            appletRelateDTO.setAppletAppId(config.getWxMiniappAppId());
            appletRelateDTO.setAppletOriginalId(config.getWxMiniappOriginalId());
            appletRelateDTO.setAppletPageUrl("/pages/goods/advert?id="+prodId+"&isSe=true");
            prodAppletRelateService.insert(prodId, appletRelateDTO);
        }
        return 1;
    }


    @Override
    @Transactional
    public int backHaul(BackHaulDTO dto){
        Long prodId = dto.getProdId();
        Prod prod = this.getById(prodId);
        Shop shop = shopService.findById(prod.getShopId());
        if (!BasicConstant.STATE_1.equals(shop.getIsControlAdCallback())){
            throw new WeikbestException("店铺没有修改权限！请联系平台处理！");
        }
        ProdDecFloor floor = new ProdDecFloor();
        floor.setId(prodId);
        floor.setSuccessPayBack(dto.getSuccessPayBack());
        floor.setAdvBackType(BasicConstant.STATE_2);
        if (!prodDecFloorService.saveOrUpdate(floor)){
            throw new WeikbestException("保存订单回传信息失败!");
        }
        ProdAdvBackAccount account = new ProdAdvBackAccount();
        account.setId(prodId);
        account.setBackRatio(dto.getBackRatio());
        return prodAdvBackAccountService.updateByProdId(account);
    }


    @Override
    public AdLinksDTO adLinksEcho(Long id){
        AdLinksDTO vo = new AdLinksDTO();
        Prod prod = this.findById(id);
        vo.setIsOpenCashOnDeliverySms(Optional.ofNullable(prod.getIsOpenCashOnDeliverySms()).orElse(BasicConstant.INT_0));
        vo.setCustomerServiceSwitch(StrUtil.isBlank(prod.getCustomerServiceSwitch()) ? BasicConstant.STATE_0 : prod.getCustomerServiceSwitch());
        ArrayList<Map<String, String>> payTypeList = new ArrayList<>();
        if (StrUtil.isNotBlank(prod.getPayType())) {
            vo.setPayType(prod.getPayType());
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
            vo.setPayType(DictConstant.PayType.TYPE_1.getCode());
        }
        vo.setPayTypeList(payTypeList);
        //落地页信息
        ProdDecFloor floor = prodDecFloorService.findById(id);
        if (floor != null) {
            vo.setBuyBtnTitle(floor.getBuyBtnTitle());
            vo.setFloatBtnTitle(floor.getFloatBtnTitle());
            vo.setCountdownOffers(floor.getCountdownOffers());
            vo.setCountdownTitle(floor.getCountdownTitle());
            vo.setCountdownMinute(floor.getCountdownMinute());
            vo.setSuccessPayBack(floor.getSuccessPayBack());
        }
        //回传信息
        ProdAdvBackAccount account = prodAdvBackAccountService.findByProdId(id);
        if (account != null) {
            vo.setAdvAccountId(account.getAdvAccountId());
            if (account.getBackRatio() == null){
                vo.setBackRatio(new BigDecimal(0));
                vo.setSuccessPayBack(BasicConstant.STATE_0);
            }else {
                vo.setBackRatio(account.getBackRatio());
            }
        }
        //返回页信息
        List<AdLinksDTO.MlcReflowSet> mlcReflowSets = new ArrayList<>();
        List<ProdReturn> returns = prodReturnService.list(new LambdaQueryWrapper<ProdReturn>().eq(ProdReturn::getProdId, id));
        if (CollectionUtil.isNotEmpty(returns)) {
            for (int i = 0; i < returns.size(); i++) {
                ProdReturn aReturn = returns.get(i);
                if (i == 0 && BasicConstant.INT_1.equals(aReturn.getSort())){
                    //首次
                    vo.setBackCost(aReturn.getReturnAmount());
                }
                if (i == 1 && BasicConstant.INT_2.equals(aReturn.getSort())){
                    //二次
                    vo.setBackSecondCost(aReturn.getReturnAmount());
                }
                if (i == 3 && BasicConstant.INT_3.equals(aReturn.getSort())){
                    //营销页
                    continue;
                }
                //其他
                AdLinksDTO.MlcReflowSet set = new AdLinksDTO.MlcReflowSet();
                set.setReflowType(aReturn.getReturnPage());
                set.setReflowSum(aReturn.getReturnAmount());
                set.setSort(aReturn.getSort());
                set.setOpenOrNot(aReturn.getOpenOrNot());
                mlcReflowSets.add(set);
            }
        }
        vo.setMlcReflowSets(mlcReflowSets);
        //返回营销页和落地页信息
        ProdLeftSlide slide = prodLeftSlideService.findById(id);
        vo.setAdLinksName(slide.getAdLinksName());
        vo.setJoinpageOpenUrl(slide.getJoinpageOpenUrl());
        vo.setJoinpageJump(slide.getJoinpageJump());
        vo.setJoinpageBannerUrl(slide.getJoinpageBannerUrl());
        vo.setUploadGoodsImg(slide.getUploadGoodsImg());
        vo.setBottomBannerImg(slide.getBottomBannerImg());
        vo.setPictureFirstPicture(slide.getPictureFirstPicture());
        vo.setBackgroundColor(slide.getBackgroundColor());
        vo.setVirtualKey(slide.getVirtualKey());
        vo.setMarketingPageSet(slide.getMarketingPageSet());
        vo.setPageType(slide.getPageType());
        vo.setMlcReflowSet(slide.getMlcReflowSet());
        vo.setCreationTime(slide.getAdLinksCreationTime());
        vo.setFullDiscountOnOff(ObjectUtil.isNull(slide.getFullDiscountOnOff()) ? BasicConstant.INT_0 : slide.getFullDiscountOnOff());
        vo.setFullDiscountMoney(ObjectUtil.isNull(slide.getFullDiscountMoney()) ? new BigDecimal(BigInteger.ZERO) : slide.getFullDiscountMoney());
        vo.setDefaultPaymentType(ObjectUtil.isNull(slide.getDefaultPaymentType()) ? BasicConstant.INT_0 : slide.getDefaultPaymentType());
        return vo;
    }


    @Override
    public Map<String,List<ProdShowDetailVO.EchoMuchSize>> getComboByProdId(Long id, Integer setMealType){
        List<ProdComboVO> prodComboVOList = prodComboService.queryVOByProdId(id , setMealType);
        ProdShowDetailVO vo = new ProdShowDetailVO();
        vo.setProdComboVOList(prodComboVOList);
        vo.setId(id);
        vo.setSetMealType(setMealType);
       return echoMuchSizes(vo);
    }


    @Override
    public IPage<ProdShoppingListVo> getShoppingList(ProdQO prodQO, PageReq pageReq){
        //获取商品信息
        IPage<ProdShoppingListVo> voIPage = this.baseMapper.getShoppingList(new Page<>(pageReq.getPage(), pageReq.getLimit()), prodQO);
        List<ProdShoppingListVo> records = voIPage.getRecords();

        //获取商品下套餐信息
        if (CollectionUtil.isNotEmpty(records)){
            List<Long> prodIds = records.stream().map(ProdShoppingListVo::getId).collect(Collectors.toList());
            List<ProdComboVO> comboVOS = prodComboService.getShoppingList(prodIds);
            Map<Long, List<ProdComboVO>> comboMapProdId = comboVOS.stream().collect(Collectors.groupingBy(c -> c.getProdId()));
            for (ProdShoppingListVo record : records) {
                if (comboMapProdId.containsKey(record.getId())){
                    record.setComboVOS(Optional.ofNullable(comboMapProdId.get(record.getId()).stream().filter(c -> c.getSetMealType() == record.getSetMealType()).collect(Collectors.toList())).orElseGet(ArrayList::new));
                }
            }
        }
        return voIPage;
    }


    @Override
    public void IdRelevancyShoppingId(Long id, Long shoppingId, Long shoppingComboId){
        shoppingId = shoppingId == 0L ? null : shoppingId;
        shoppingComboId = shoppingComboId == 0L ? null : shoppingComboId;
        this.baseMapper.IdRelevancyShoppingId(id , shoppingId , shoppingComboId);
    }


    @Override
    @Transactional
    public void delete(Long id){
        if (this.removeById(id)){
            this.baseMapper.removeShoppingId(id);
        }
    }


    @Override
    @Transactional
    public void deletes(List<Long> ids){
        if (this.removeBatchByIds(ids)){
            this.baseMapper.removeShoppingIds(ids);
        }
    }

    @Override
    @Transactional
    public int addAdLinksCallback(AdLinksDTO dto) {
        /*if (StringUtils.isBlank(dto.getJoinpageBannerUrl())) dto.setJoinpageBannerUrl(BasicConstant.JOINPAGE_BANNER);
        if (ObjectUtil.isNotNull(dto.getMarketingPageSet()) && BasicConstant.INT_1.equals(dto.getMarketingPageSet()) && StringUtils.isBlank(dto.getJoinpageOpenUrl())) dto.setJoinpageOpenUrl(BasicConstant.JOINPAGE_OPEN);

        Long prodId = dto.getProdId();

        Prod prod = this.getById(prodId);
        Shop shop = shopService.findById(prod.getShopId());
        if (BasicConstant.INT_1.equals(dto.getAddOrUpdate()) && !BasicConstant.STATE_1.equals(shop.getIsControlAdCallback())){
            throw new WeikbestException("店铺没有修改权限！请联系平台处理！");
        }
        //保存回传信息
        ProdAdvBackAccount account = prodAdvBackAccountService.findByProdIdAndAccountId(prodId,dto.getAdvAccountId());
        account.setId(prodId);
        account.setAdvAccountId(dto.getAdvAccountId());
        if (BasicConstant.STATE_1.equals(shop.getIsControlAdCallback())){
            account.setBackRatio(dto.getBackRatio());
        }else {
            account.setBackRatio(new BigDecimal(100));
        }
        prodAdvBackAccountService.deleteByProdId(prodId);
        if (!prodAdvBackAccountService.saveOrUpdate(account)){
            throw new WeikbestException("保存回传信息失败!");
        }

        //保存返回页信息
        BigDecimal marketingPageAmount = new BigDecimal(0);
        List<ProdReturn> returns = new ArrayList<>();
        if (dto.getBackCost() != null){
            ProdReturn aReturn = new ProdReturn();
            aReturn.setProdId(prodId);
            aReturn.setReturnPage(BasicConstant.INT_1);
            aReturn.setReturnAmount(dto.getBackCost());
            aReturn.setSort(BasicConstant.INT_1);
            aReturn.setOpenOrNot(BasicConstant.INT_1);
            returns.add(aReturn);
            marketingPageAmount = dto.getBackCost();
        }
        if (dto.getBackSecondCost() != null && BasicConstant.INT_1.equals(dto.getPageType())){
            ProdReturn aReturn = new ProdReturn();
            aReturn.setProdId(prodId);
            aReturn.setReturnPage(BasicConstant.INT_2);
            aReturn.setReturnAmount(dto.getBackSecondCost());
            aReturn.setSort(BasicConstant.INT_2);
            aReturn.setOpenOrNot(BasicConstant.INT_1);
            returns.add(aReturn);
            marketingPageAmount = dto.getBackSecondCost();
        }
        //返回三:营销页
        if (StringUtils.isNotBlank(dto.getJoinpageOpenUrl())){
            ProdReturn aReturn = new ProdReturn();
            aReturn.setProdId(prodId);
            aReturn.setReturnPage(BasicConstant.INT_3);
            aReturn.setReturnAmount(marketingPageAmount);
            aReturn.setSort(BasicConstant.INT_3);
            aReturn.setOpenOrNot(BasicConstant.INT_1);
            returns.add(aReturn);
        }
        if (CollectionUtil.isNotEmpty(dto.getMlcReflowSets())){
            List<AdLinksDTO.MlcReflowSet> mlcReflowSets = dto.getMlcReflowSets();
            mlcReflowSets.forEach(set -> {
                ProdReturn aReturn = new ProdReturn();
                aReturn.setProdId(prodId);
                aReturn.setReturnPage(set.getReflowType());
                aReturn.setReturnAmount(set.getReflowSum());
                aReturn.setSort(set.getSort());
                aReturn.setOpenOrNot(set.getOpenOrNot());
                returns.add(aReturn);
            });
        }
        if (CollectionUtil.isNotEmpty(returns)){
            prodReturnService.deleteByProdId(prodId);
            if (!prodReturnService.saveOrUpdateBatch(returns)){
                throw new WeikbestException("更新返回页信息失败!");
            }
        }*/
        return 1;
    }

    /**
     * 多规格回显设置
     * @param prodShowDetailVO
     * @return
     */
    public static Map<String,List<ProdShowDetailVO.EchoMuchSize>> echoMuchSizes(ProdShowDetailVO prodShowDetailVO) {
        List<ProdComboVO> cVos = prodShowDetailVO.getProdComboVOList();
        if (CollectionUtil.isEmpty(cVos)){
            return null;
        }
        Map<String,List<ProdShowDetailVO.EchoMuchSize>> map = new LinkedHashMap<>();
        //获取规格名
        String comboTitle = Optional.ofNullable(cVos.get(0).getComboTitle()).orElseGet(() ->"");
        //创建key对应的values
        for (String key : comboTitle.split(BasicConstant.CLEAVER)) {
            map.put(key, new ArrayList<>());
        }
        //获取规格名 对应 配图的map
        for (ProdComboVO cVo : cVos) {
            if (prodShowDetailVO.getIsImg() == 0 && StringUtils.isNotBlank(cVo.getImg())){
                prodShowDetailVO.setIsImg(1);
            }
            int index = 0; //规格名的索引
            String[] sellPoints = cVo.getSellPoint().split(BasicConstant.CLEAVER);
            for (String key : map.keySet()) {
                List<ProdShowDetailVO.EchoMuchSize> echoMuchSizes = map.get(key);
                String value = sellPoints[index];
                //判断当前规格名对应规格值得map中是否有这个规格值存在,没有就添加进去,有就不做处理
                if(!echoMuchSizes.stream().filter(e->e.getValue().equals(value)).findAny().isPresent()) {
                    ProdShowDetailVO.EchoMuchSize echoMuchSize = new ProdShowDetailVO.EchoMuchSize();
                    echoMuchSize.setValue(value);
                    echoMuchSize.setImg(cVo.getImg());
                    echoMuchSize.setComboCode(cVo.getComboCode());
                    echoMuchSizes.add(echoMuchSize);
                    map.put(key,echoMuchSizes);
                }
                index ++;
            }
        }
        return map;
    }

}
