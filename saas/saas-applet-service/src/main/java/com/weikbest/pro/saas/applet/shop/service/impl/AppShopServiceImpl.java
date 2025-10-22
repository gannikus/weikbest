package com.weikbest.pro.saas.applet.shop.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weikbest.pro.saas.applet.commodity.module.qo.AppProdQO;
import com.weikbest.pro.saas.applet.commodity.service.AppCommodityService;
import com.weikbest.pro.saas.applet.shop.module.dto.AppShopVisitDTO;
import com.weikbest.pro.saas.applet.shop.module.mapstruct.AppShopListMapStruct;
import com.weikbest.pro.saas.applet.shop.module.mapstruct.AppShopMapStruct;
import com.weikbest.pro.saas.applet.shop.module.mapstruct.AppShopVisitMapStruct;
import com.weikbest.pro.saas.applet.shop.module.vo.AppShopListVO;
import com.weikbest.pro.saas.applet.shop.module.vo.AppShopVO;
import com.weikbest.pro.saas.applet.shop.service.AppShopService;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.common.util.WeikbestObjectUtil;
import com.weikbest.pro.saas.merchat.cust.entity.CustShopAttent;
import com.weikbest.pro.saas.merchat.cust.module.qo.CustShopAttentQO;
import com.weikbest.pro.saas.merchat.cust.service.CustShopAttentService;
import com.weikbest.pro.saas.merchat.prod.entity.Prod;
import com.weikbest.pro.saas.merchat.prod.module.dto.AppProdDTO;
import com.weikbest.pro.saas.merchat.prod.service.ProdService;
import com.weikbest.pro.saas.merchat.shop.entity.Shop;
import com.weikbest.pro.saas.merchat.shop.entity.ShopThird;
import com.weikbest.pro.saas.merchat.shop.entity.ShopVisit;
import com.weikbest.pro.saas.merchat.shop.service.ShopService;
import com.weikbest.pro.saas.merchat.shop.service.ShopThirdService;
import com.weikbest.pro.saas.merchat.shop.service.ShopVisitService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.common.service.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class AppShopServiceImpl implements AppShopService {

    @Resource
    private ShopService shopService;

    @Resource
    private ProdService prodService;

    @Resource
    private CustShopAttentService custShopAttentService;

    @Resource
    private CurrentUserService currentUserService;

    @Resource
    private AppCommodityService appCommodityService;

    @Resource
    private ShopThirdService shopThirdService;

    @Resource
    private ShopVisitService shopVisitService;


    @Override
    public AppShopVO findshop(Long id,Long customerId) {

        //默认设置不关注
        String isFollow = "0";

        if(WeikbestObjectUtil.isNotEmpty(customerId)){
            QueryWrapper<CustShopAttent> custShopAttentQueryWrapper = new QueryWrapper<>();
            custShopAttentQueryWrapper.eq(CustShopAttent.SHOP_ID,id);
            custShopAttentQueryWrapper.eq(CustShopAttent.CUSTOMER_ID,customerId);
            CustShopAttent custShopAttent = custShopAttentService.getOne(custShopAttentQueryWrapper);
            if(ObjectUtil.isNotNull(custShopAttent)){
                isFollow = "1";
            }
        }

        Shop shop = shopService.findById(id);
        AppShopVO appShopVO = AppShopMapStruct.INSTANCE.converToVO(shop);

        QueryWrapper<Prod> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Prod.SHOP_ID, id);
        queryWrapper.eq(Prod.JOIN_SHOP_MAINPAGE,"1");
        appShopVO.setCoundProd(prodService.count(queryWrapper));
        appShopVO.setIsFollow(isFollow);

        ShopThird shopThird = shopThirdService.findById(id);
        appShopVO.setWxBusiness(shopThird.getWxBusiness());

        return appShopVO;
    }

    @Override
    public List<AppShopListVO> queryShopListByCustomerId(PageReq pageReq){

        List<AppShopListVO> appShopListVOList = new ArrayList<>();

        CustShopAttentQO custShopAttentQO = new CustShopAttentQO();
        custShopAttentQO.setCustomerId(currentUserService.getAppTokenUser().getId());

        List<CustShopAttent> list = custShopAttentService.queryPage(custShopAttentQO,pageReq).getRecords();
        for (CustShopAttent custShopAttent: list) {
            Shop shop = shopService.findById(custShopAttent.getShopId());
            AppShopListVO vo = AppShopListMapStruct.INSTANCE.converToVO(shop);
            QueryWrapper<Prod> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(Prod.SHOP_ID, custShopAttent.getShopId());
            queryWrapper.eq(Prod.JOIN_SHOP_MAINPAGE,"1");
            vo.setCoundProd(prodService.count(queryWrapper));

            PageReq pageReqProd = new PageReq();
            pageReqProd.setPage(1);
            pageReqProd.setLimit(3);
            List<AppProdDTO> appProdDTOList = appCommodityService.queryPageCommodity(new AppProdQO().setShopId(custShopAttent.getShopId()),pageReqProd);
            vo.setAppProdDTOList(appProdDTOList);
            appShopListVOList.add(vo);
        }

        return appShopListVOList;

    }

    @Override
    public Boolean doAttent(Long shopId,String isAttent){

        boolean bool = false;
        Long businessId =  shopService.findById(shopId).getBusinessId();
        Long customerId = currentUserService.getAppTokenUser().getId();
        QueryWrapper<CustShopAttent> custShopAttentQueryWrapper = new QueryWrapper<>();
        custShopAttentQueryWrapper.eq(CustShopAttent.SHOP_ID,shopId);
        custShopAttentQueryWrapper.eq(CustShopAttent.CUSTOMER_ID,customerId);
        CustShopAttent custShopAttent = custShopAttentService.getOne(custShopAttentQueryWrapper);

        if(ObjectUtil.isNotNull(custShopAttent) && "0".equals(isAttent)){
            //如果用户取消关注
            bool = custShopAttentService.removeById(custShopAttent);
        }
        if(ObjectUtil.isNull(custShopAttent) && "1".equals(isAttent)){
            //如果用户进行关注
            CustShopAttent shopAttent = new CustShopAttent();
            Long id = GenerateIDUtil.nextId();
            shopAttent.setId(id);
            shopAttent.setShopId(shopId);
            shopAttent.setBusinessId(businessId);
            shopAttent.setCustomerId(customerId);
            bool = custShopAttentService.save(shopAttent);
        }
        return bool;
    }

    @Override
    public Boolean saveShopVisit(AppShopVisitDTO appShopVisitDTO){

        Boolean bool = false;

        String datestr = DateUtil.format(new Date(),"yyyy-MM-dd");

        QueryWrapper<ShopVisit> shopVisitQueryWrapper = new QueryWrapper<>();
        shopVisitQueryWrapper.eq(ShopVisit.SHOP_ID,appShopVisitDTO.getShopId());
        shopVisitQueryWrapper.eq(ShopVisit.CUSTOMER_ID,appShopVisitDTO.getCustomerId());
        shopVisitQueryWrapper.like(ShopVisit.VISIT_TIME,datestr);

        List<ShopVisit> shopVisitList = shopVisitService.list(shopVisitQueryWrapper);
        //判断该客户当日是否存在访问情况，如果存在则不进行记录
        if(shopVisitList != null && shopVisitList.size() > 0){
            return true;
        }else{
            ShopVisit shopVisit = AppShopVisitMapStruct.INSTANCE.converToEntity(appShopVisitDTO);

            shopVisit.setVisitType(DictConstant.VisitType.visitType_1.getCode());
            shopVisit.setVisitTime(DateUtil.date());

            bool =  shopVisitService.save(shopVisit);
        }

        return bool;
    }

    @Override
    public Boolean saveShopProdVisit(AppShopVisitDTO appShopVisitDTO){

        ShopVisit shopVisit = AppShopVisitMapStruct.INSTANCE.converToEntity(appShopVisitDTO);

        shopVisit.setVisitType(DictConstant.VisitType.visitType_2.getCode());
        shopVisit.setVisitTime(DateUtil.date());

        Boolean bool =  shopVisitService.save(shopVisit);

        return bool;
    }

}
