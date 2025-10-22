package com.weikbest.pro.saas.sysmerchat.prod.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weikbest.pro.saas.common.constant.BasicConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.util.BeanUtil;
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.merchat.prod.entity.*;
import com.weikbest.pro.saas.merchat.prod.module.dto.*;
import com.weikbest.pro.saas.merchat.prod.module.vo.*;
import com.weikbest.pro.saas.merchat.prod.service.*;
import com.weikbest.pro.saas.sys.common.constant.CodeRuleConstant;
import com.weikbest.pro.saas.sys.param.service.CodeRuleService;
import com.weikbest.pro.saas.sysmerchat.prod.mapper.SysProdMapper;
import com.weikbest.pro.saas.sysmerchat.prod.module.mapstruct.SysProdMapStruct;
import com.weikbest.pro.saas.sysmerchat.prod.module.qo.CloneProdDTO;
import com.weikbest.pro.saas.sysmerchat.prod.module.qo.SysProdQO;
import com.weikbest.pro.saas.sysmerchat.prod.module.vo.SysProdListVO;
import com.weikbest.pro.saas.sysmerchat.prod.module.vo.SysProdVO;
import com.weikbest.pro.saas.sysmerchat.prod.service.SysProdService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/1
 */
@Slf4j
@Service
public class SysProdServiceImpl implements SysProdService {

    @Resource
    private ProdService prodService;

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
    private SysProdMapper sysProdMapper;

    @Autowired
    private CodeRuleService codeRuleService;

    @Autowired
    private ProdServiceCommitmentService prodServiceCommitmentService;

    @Autowired
    private ProdPurchaseRestrictionsService prodPurchaseRestrictionsService;

    @Override
    public SysProdVO findVOById(Long id) {
        Prod prod = prodService.findById(id);
        Integer setMealType = prod.getSetMealType();
        SysProdVO sysProdVO = SysProdMapStruct.INSTANCE.converToVO(prod);

        sysProdVO.setSetMealType(setMealType);
        List<ProdComboVO> prodComboVOList = prodComboService.queryVOByProdId(id , setMealType);
        if (BasicConstant.INT_1.equals(setMealType)){
            //普通套餐
            sysProdVO.setProdComboVOList(prodComboVOList);
        }else if (BasicConstant.INT_2.equals(setMealType)){
            //多规格套餐
            List<MoreSpecVo> moreSpecVos = new ArrayList<>();
            for (ProdComboVO comboVO : prodComboVOList) {
                MoreSpecVo specVo = new MoreSpecVo();
                specVo.setSpecValue(comboVO.getSellPoint());
                specVo.setComboPrice(comboVO.getComboPrice());
                specVo.setComboCostPrice(comboVO.getComboCostPrice());
                specVo.setComboStandardPrice(comboVO.getComboStandardPrice());
                specVo.setImg(comboVO.getImg());
                moreSpecVos.add(specVo);

                if (StringUtils.isBlank(sysProdVO.getTitle())){
                    sysProdVO.setTitle(comboVO.getComboTitle() + BasicConstant.CLEAVER + "售价" + BasicConstant.CLEAVER + "划单价" + BasicConstant.CLEAVER + "成本价" + BasicConstant.CLEAVER + "图片");
                }
            }
            sysProdVO.setMoreSpecs(moreSpecVos);
        }

        ProdPriceVO prodPriceVO = prodPriceService.getVOById(id);
        sysProdVO.setProdPriceVO(prodPriceVO);

        ProdFeightVO prodFeightVO = prodFeightService.getVOById(id);
        sysProdVO.setProdFeightVO(prodFeightVO);

        ProdThemeVO prodThemeVO = prodThemeService.getVOById(id);
        sysProdVO.setProdThemeVO(prodThemeVO);

        List<ProdDetailVO> prodDetailVOList = prodDetailService.queryVOByProdId(id);
        sysProdVO.setProdDetailVOList(prodDetailVOList);

        List<ProdMainimgVO> prodMainimgVOList = prodMainimgService.queryVOByProdId(id);
        sysProdVO.setProdMainimgVOList(prodMainimgVOList);

        ProdDecFloorVO prodDecFloorVO = prodDecFloorService.getVOById(id);
        sysProdVO.setProdDecFloorVO(prodDecFloorVO);

        ProdLeftSlideVO prodLeftSlideVO = prodLeftSlideService.getVOById(id);
        sysProdVO.setProdLeftSlideVO(prodLeftSlideVO);

        List<ProdCouponVO> prodCouponVOList = prodCouponService.queryProdCouponVOById(id);
        sysProdVO.setProdCouponVOList(prodCouponVOList);

        ProdAppletRelateVO prodAppletRelateVO = prodAppletRelateService.findVOById(id);
        sysProdVO.setProdAppletRelateVO(prodAppletRelateVO);

        return sysProdVO;
    }

    @Override
    public IPage<SysProdListVO> queryPage(SysProdQO sysProdQO, PageReq pageReq) {
        return sysProdMapper.queryPage(new Page<>(pageReq.getPage(), pageReq.getLimit()), sysProdQO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateProdOrd(Long id, Integer prodOrd) {
        Prod prod = prodService.findById(id);
        prod.setProdOrd(prodOrd);
        return prodService.updateById(prod);
    }


    @Override
    @Transactional
    public void cloneProd(CloneProdDTO cloneProdDTO) {
        Prod prod = prodService.getById(cloneProdDTO.getId());
        if (ObjectUtils.isEmpty(prod)){
            throw new WeikbestException("未查询到商品相关信息!");
        }
        prod.setShopId(cloneProdDTO.getShopId());
        prod.setBusinessId(cloneProdDTO.getBusinessId());

        // 获取商品其他信息
        List<ProdComboDTO> comboVOS = prodComboService.queryDTOByProdId(cloneProdDTO.getId(), prod.getSetMealType()); //多规格套餐
        comboVOS.forEach(combo->{
            combo.setId(null);
            if (CollectionUtil.isNotEmpty(combo.getProdComboAttrRelateDTOList())){
                combo.getProdComboAttrRelateDTOList().forEach(cc-> cc.setId(null));
            }
        });
        ProdPrice prodPrice = prodPriceService.findById(cloneProdDTO.getId()); //商品价格
        ProdFeight prodFeight = prodFeightService.findById(cloneProdDTO.getId()); //运费信息
        ProdTheme prodTheme = prodThemeService.findById(cloneProdDTO.getId()); //样式信息
        List<ProdDetail> prodDetails = prodDetailService.selectByProdId(cloneProdDTO.getId()); //详情信息
        List<ProdMainimg> prodMainimgs = prodMainimgService.selectByProdId(cloneProdDTO.getId()); //商品详情页轮播图信息
        ProdServiceCommitment prodServiceCommitment = prodServiceCommitmentService.selectByProdId(cloneProdDTO.getId()); //商品服务承诺信息
        ProdPurchaseRestrictions restrictions = prodPurchaseRestrictionsService.getById(cloneProdDTO.getId());
        //设置参数
        ProdDTO prodDTO = BeanUtil.copyProperties(prod, ProdDTO.class);
        prodDTO.setGoodsType(String.valueOf(prod.getGoodsType()));
        prodDTO.setProdComboDTOList(BeanUtil.copyProperties(comboVOS, ProdComboDTO.class));
        prodDTO.setProdPriceDTO(BeanUtil.copyProperties(prodPrice, ProdPriceDTO.class));
        prodDTO.setProdFeightDTO(BeanUtil.copyProperties(prodFeight, ProdFeightDTO.class));
        prodDTO.setProdThemeDTO(BeanUtil.copyProperties(prodTheme, ProdThemeDTO.class));
        prodDTO.setProdDetailDTOList(BeanUtil.copyProperties(prodDetails, ProdDetailDTO.class));
        prodDTO.setProdMainimgDTOList(BeanUtil.copyProperties(prodMainimgs, ProdMainimgDTO.class));
        prodDTO.setProdServiceCommitmentDTO(BeanUtil.copyProperties(prodServiceCommitment, ProdServiceCommitmentDTO.class));
        prodDTO.setProdPurchaseRestrictionsDto(BeanUtil.copyProperties(restrictions , ProdPurchaseRestrictionsDto.class));
        prodService.insert(prodDTO);
    }
}
