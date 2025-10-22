package com.weikbest.pro.saas.merchat.prod.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.constant.BasicConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.BeanUtil;
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.merchat.prod.entity.ProdCombo;
import com.weikbest.pro.saas.merchat.prod.entity.ProdComboAttrRelate;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdComboMapper;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdMapper;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdComboAttrRelateDTO;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdComboDTO;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdComboMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdComboQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdComboAttrRelateVO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdComboVO;
import com.weikbest.pro.saas.merchat.prod.service.ProdComboAttrRelateService;
import com.weikbest.pro.saas.merchat.prod.service.ProdComboService;
import org.apache.commons.collections4.CollectionUtils;
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
 * 商品销售套餐表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ProdComboServiceImpl extends ServiceImpl<ProdComboMapper, ProdCombo> implements ProdComboService {

    @Resource
    private ProdComboAttrRelateService prodComboAttrRelateService;

    @Autowired
    private ProdMapper prodMapper;

    @Override
    public boolean insert(ProdComboDTO prodComboDTO) {
        ProdCombo prodCombo = ProdComboMapStruct.INSTANCE.converToEntity(prodComboDTO);
        return this.save(prodCombo);
    }

    @Override
    public boolean updateById(Long id, ProdComboDTO prodComboDTO) {
        ProdCombo prodCombo = this.findById(id);
        ProdComboMapStruct.INSTANCE.copyProperties(prodComboDTO, prodCombo);
        prodCombo.setId(id);
        return this.updateById(prodCombo);
    }

    @Override
    public ProdCombo findById(Long id) {
        ProdCombo prodCombo = this.getById(id);
        if (ObjectUtil.isNull(prodCombo)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return prodCombo;
    }

    @Override
    public IPage<ProdCombo> queryPage(ProdComboQO prodComboQO, PageReq pageReq) {
        QueryWrapper<ProdCombo> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(prodComboQO.getComboTitle())) {
            wrapper.eq(ProdCombo.COMBO_TITLE, prodComboQO.getComboTitle());
        }
        if (StrUtil.isNotBlank(prodComboQO.getSellPoint())) {
            wrapper.eq(ProdCombo.SELL_POINT, prodComboQO.getSellPoint());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ProdCombo insertBatchWithProd(Long prodId,Integer setMealType, String goodsType, List<ProdComboDTO> prodComboDTOList) { //修改商品是关联上随手购
        //有套餐Id的 设置为修改 , 没有套餐Id的 设置为新增 , 原本有的套餐这次没传过来设置为删除并设置随手购商品套餐关联着这个套餐的数据的随手购套餐Id为null
        ProdCombo lowestPricedPackage = null;
        ProdCombo theFirstPackage = null;
        List<ProdCombo> addProdCombos = new ArrayList<>(); //新增的套餐列表
        List<ProdCombo> upProdCombos = new ArrayList<>(); //修改的套餐列表
        List<Long> removeProdComboIds = new ArrayList<>(); //不删除的套餐Ids

        List<ProdComboAttrRelate> addAttrRelates = new ArrayList<>(); //新增的套餐属性列表
        List<ProdComboAttrRelate> upAttrRelates = new ArrayList<>(); //修改的套餐属性列表
        List<Long> removeAttrRelateIds = new ArrayList<>(); //不删除的套餐属性Ids

        //获取当前的套餐信息
        List<Long> comboIds = Optional.ofNullable(this.baseMapper.getIdByProdIdAndSetMealType(prodId,setMealType)).orElseGet(ArrayList::new);
        List<Long> attrRelateIds = prodComboAttrRelateService.getIdByProdId(prodId);
        //套餐筛选
        if (CollectionUtils.isNotEmpty(prodComboDTOList)){
            for (ProdComboDTO prodComboDTO : prodComboDTOList) {
                ProdCombo prodCombo = BeanUtil.copyProperties(prodComboDTO, ProdCombo.class);
                prodCombo.setProdId(prodId);
                prodCombo.setSetMealType(setMealType);
                if (ObjectUtil.isNull(prodComboDTO.getId())){
                    Long prodComboId = GenerateIDUtil.nextId();
                    prodCombo.setId(prodComboId);
                    prodComboDTO.setId(prodComboId);
                    addProdCombos.add(prodCombo);
                }else {
                    upProdCombos.add(prodCombo);
                    removeProdComboIds.add(prodCombo.getId());
                }
                List<ProdComboAttrRelateDTO> prodComboAttrRelateDTOList = Optional.ofNullable(prodComboDTO.getProdComboAttrRelateDTOList()).orElseGet(ArrayList::new);
                for (ProdComboAttrRelateDTO prodComboAttrRelateDTO : prodComboAttrRelateDTOList) {
                    ProdComboAttrRelate attrRelate = BeanUtil.copyProperties(prodComboAttrRelateDTO, ProdComboAttrRelate.class);
                    attrRelate.setProdId(prodId);
                    if (ObjectUtil.isNull(prodComboAttrRelateDTO.getId())){
                        attrRelate.setProdId(prodId);
                        attrRelate.setProdComboId(prodCombo.getId());
                        addAttrRelates.add(attrRelate);
                    }else {
                        upAttrRelates.add(attrRelate);
                        removeAttrRelateIds.add(prodComboAttrRelateDTO.getId());
                    }
                }

                //获取最低价格的套餐
                if (lowestPricedPackage == null || lowestPricedPackage.getComboPrice().compareTo(prodCombo.getComboPrice()) == 1) {
                    lowestPricedPackage = prodCombo;
                }
                //获取第一个套餐
                if (ObjectUtil.isNull(theFirstPackage)){
                    theFirstPackage = prodCombo;
                }
            }
        }
        Iterator<Long> comboIterator = comboIds.iterator();
        while(comboIterator.hasNext()){
            long prodComboId = comboIterator.next();
            if (removeProdComboIds.contains(prodComboId)){
                comboIterator.remove();
            }
        }
        Iterator<Long> attrRelateIterator = attrRelateIds.iterator();
        while(attrRelateIterator.hasNext()){
            long attrRelateId = attrRelateIterator.next();
            if (removeAttrRelateIds.contains(attrRelateId)){
                attrRelateIterator.remove();
            }
        }

        //执行新增
        this.saveBatch(addProdCombos);
        prodComboAttrRelateService.saveBatch(addAttrRelates);
        //执行修改
        this.updateBatchById(upProdCombos);
        prodComboAttrRelateService.updateBatchById(upAttrRelates);
        //执行删除,同时删除随手购关联此套餐的数据
        if (this.removeBatchByIds(comboIds)){
            prodComboAttrRelateService.removeBatchByIds(attrRelateIds);
            prodMapper.updateByShoppingComboIds(comboIds , theFirstPackage.getId());
        }
        return lowestPricedPackage;
    }

    @Override
    public ProdCombo findMinProdComboByProdId(Long prodId, Integer setMealType) {
        setMealType = setMealType == null ? 1 : setMealType;
        List<ProdCombo> prodComboList = this.list(new QueryWrapper<ProdCombo>().eq(ProdCombo.PROD_ID, prodId).eq(ProdCombo.SET_MEAL_TYPE , setMealType));
        // 获取销售套餐金额最低的那条数据
        return prodComboList.stream().min(Comparator.comparing(ProdCombo::getComboPrice)).get();
    }

    @Override
    public List<ProdComboVO> queryVOByProdId(Long prodId , Integer setMealType) {
        setMealType = setMealType == null ? 1 : setMealType;
        List<ProdCombo> prodComboList = this.list(new QueryWrapper<ProdCombo>().eq(ProdCombo.PROD_ID, prodId).eq(ProdCombo.SET_MEAL_TYPE , setMealType).eq(ProdCombo.FLAG , BasicConstant.INT_0));
        Map<Long, List<ProdComboAttrRelateVO>> prodComboAttrRelateVOListMap = prodComboAttrRelateService.queryVOByProdIdGroupByProdCombo(prodId);
        List<ProdComboVO> vos = new ArrayList<>();
        if (CollectionUtils.isEmpty(prodComboList)){
            return vos;
        }
        for (ProdCombo prodCombo : prodComboList) {
            ProdComboVO prodComboVO = ProdComboMapStruct.INSTANCE.converToVO(prodCombo);
            List<ProdComboAttrRelateVO> prodComboAttrRelateVOList = prodComboAttrRelateVOListMap.get(prodCombo.getId());
            prodComboVO.setProdComboAttrRelateVOList(prodComboAttrRelateVOList);
            vos.add(prodComboVO);
        }
        return vos;
    }


    @Override
    public List<ProdComboDTO> queryDTOByProdId(Long prodId, Integer setMealType){
        setMealType = setMealType == null ? 1 : setMealType;
        List<ProdCombo> prodComboList = this.list(new QueryWrapper<ProdCombo>().eq(ProdCombo.PROD_ID, prodId).eq(ProdCombo.SET_MEAL_TYPE , setMealType).eq(ProdCombo.FLAG , BasicConstant.INT_0));
        Map<Long, List<ProdComboAttrRelateVO>> prodComboAttrRelateVOListMap = prodComboAttrRelateService.queryVOByProdIdGroupByProdCombo(prodId);
        List<ProdComboDTO> dtos = new ArrayList<>();
        if (CollectionUtils.isEmpty(prodComboList)){
            return dtos;
        }
        for (ProdCombo prodCombo : prodComboList) {
            ProdComboDTO dto = ProdComboMapStruct.INSTANCE.converToDTO(prodCombo);
            List<ProdComboAttrRelateDTO> prodComboAttrRelateDTOS = BeanUtil.copyProperties(prodComboAttrRelateVOListMap.get(prodCombo.getId()), ProdComboAttrRelateDTO.class);
            dto.setProdComboAttrRelateDTOList(prodComboAttrRelateDTOS);
            dtos.add(dto);
        }
        return dtos;
    }


    @Override
    public List<ProdComboVO> getShoppingList(List<Long> prodIds){
        List<ProdCombo> combos = this.list(new QueryWrapper<ProdCombo>().in(ProdCombo.PROD_ID, prodIds).eq(ProdCombo.FLAG, BasicConstant.STATE_0).orderByAsc(ProdCombo.COMBO_ORD));
        List<ProdComboAttrRelate> attrRelates = prodComboAttrRelateService.list(new QueryWrapper<ProdComboAttrRelate>().in(ProdComboAttrRelate.PROD_ID, prodIds));
        Map<Long, List<ProdComboAttrRelate>> comboMapAttrRelate = attrRelates.stream().collect(Collectors.groupingBy(a -> a.getProdComboId()));
        List<ProdComboVO> comboVOS = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(combos)){
            for (ProdCombo combo : combos) {
                ProdComboVO comboVO = BeanUtil.copyProperties(combo, ProdComboVO.class);
                comboVO.setComboTitle(productName(combo.getSetMealType(),combo.getComboTitle(),combo.getSellPoint()));
                comboVO.setProdComboAttrRelateVOList(comboMapAttrRelate.containsKey(combo.getId()) ? BeanUtil.copyProperties(comboMapAttrRelate.get(combo.getId()), ProdComboAttrRelateVO.class) : new ArrayList<>());
                comboVOS.add(comboVO);
            }
        }
        return comboVOS;
    }


    public String productName(Integer setMealType , String comboTitle , String sellPoint){
        if (BasicConstant.INT_2.equals(setMealType)){
            String[] comboTitles = comboTitle.split(BasicConstant.CLEAVER);
            String[] sellPoints = sellPoint.split(BasicConstant.CLEAVER);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < comboTitles.length; i++) {
                builder.append(comboTitles[i] + ":" + sellPoints[i] + ",");
            }
            String productName = builder.toString();
            return productName.substring(0,productName.length()-1);
        }else {
            return comboTitle;
        }
    }
}
