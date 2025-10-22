package com.weikbest.pro.saas.merchat.busi.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.merchat.busi.entity.BusiUser;
import com.weikbest.pro.saas.merchat.busi.entity.BusiUserRelate;
import com.weikbest.pro.saas.merchat.busi.entity.Business;
import com.weikbest.pro.saas.merchat.busi.mapper.BusinessMapper;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiUserRegistDTO;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusinessDTO;
import com.weikbest.pro.saas.merchat.busi.module.mapstruct.BusiUserMapStruct;
import com.weikbest.pro.saas.merchat.busi.module.mapstruct.BusinessMapStruct;
import com.weikbest.pro.saas.merchat.busi.module.qo.BusinessQO;
import com.weikbest.pro.saas.merchat.busi.module.vo.BusiUserLoginInfoVO;
import com.weikbest.pro.saas.merchat.busi.service.BusiUserRelateService;
import com.weikbest.pro.saas.merchat.busi.service.BusiUserService;
import com.weikbest.pro.saas.merchat.busi.service.BusinessService;
import com.weikbest.pro.saas.merchat.shop.entity.ShopUser;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopVO;
import com.weikbest.pro.saas.merchat.shop.service.ShopService;
import com.weikbest.pro.saas.merchat.shop.service.ShopUserService;
import com.weikbest.pro.saas.sys.common.constant.CodeRuleConstant;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.service.CodeRuleService;
import com.weikbest.pro.saas.sys.system.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 商户表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements BusinessService {

    @Resource
    private UserService userService;

    @Resource
    private BusiUserService busiUserService;

    @Resource
    private BusiUserRelateService busiUserRelateService;

    @Resource
    private ShopService shopService;

    @Resource
    private ShopUserService shopUserService;

    @Resource
    private CodeRuleService codeRuleService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(BusinessDTO businessDTO) {
        // 新增商户
        Business business = BusinessMapStruct.INSTANCE.converToEntity(businessDTO);
        String number = codeRuleService.nextNum(CodeRuleConstant.T_MMDM_BUSINESS);
        business.setNumber(number);
        boolean save = this.save(business);

        // 新增商户主账号
        Long busiUserId = busiUserService.insertMainReturnId(businessDTO, business.getId());

        // 非平台用户注册
        userService.registerRelateUser(businessDTO.getPhone(), businessDTO.getUserName(), businessDTO.getEmail(), businessDTO.getAvatar(), DictConstant.UserRelateType.merchat.getCode(), busiUserId);
        return save;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean regist(BusiUserRegistDTO busiUserRegistDTO) {
        // 新增商户
        BusinessDTO businessDTO = BusinessMapStruct.INSTANCE.converToBusinessDTO(busiUserRegistDTO);
        return this.insert(businessDTO);
    }

    @Override
    public boolean updateById(Long id, BusinessDTO businessDTO) {
        Business business = this.findById(id);
        BusinessMapStruct.INSTANCE.copyProperties(businessDTO, business);
        business.setId(id);
        // 平台商户则设置为超级商户类型
        if (StrUtil.equals(businessDTO.getBusinessType(), DictConstant.BusinessType.platform.getCode())) {
            business.setIsSuper(DictConstant.Whether.yes.getCode());
        }

        return this.updateById(business);
    }

    @Override
    public Business findById(Long id) {
        Business business = this.getById(id);
        if (ObjectUtil.isNull(business)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return business;
    }

    @Override
    public IPage<Business> queryPage(BusinessQO businessQO, PageReq pageReq) {
        QueryWrapper<Business> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(businessQO.getNumber())) {
            wrapper.eq(Business.NUMBER, businessQO.getNumber());
        }
        if (StrUtil.isNotBlank(businessQO.getName())) {
            wrapper.eq(Business.NAME, businessQO.getName());
        }
        if (StrUtil.isNotBlank(businessQO.getBusinessType())) {
            wrapper.eq(Business.BUSINESS_TYPE, businessQO.getBusinessType());
        }
        if (StrUtil.isNotBlank(businessQO.getIsSuper())) {
            wrapper.eq(Business.IS_SUPER, businessQO.getIsSuper());
        }
        if (StrUtil.isNotBlank(businessQO.getDescription())) {
            wrapper.eq(Business.DESCRIPTION, businessQO.getDescription());
        }
        if (StrUtil.isNotBlank(businessQO.getDataStatus())) {
            wrapper.eq(Business.DATA_STATUS, businessQO.getDataStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public List<Long> findIdByTokenUser(TokenUser tokenUser) {
        Long currentRelateId = tokenUser.getId();
        BusiUser busiUser = busiUserService.findById(currentRelateId);

        List<BusiUserRelate> busiUserRelateList = busiUserRelateService.list(new QueryWrapper<BusiUserRelate>().eq(BusiUserRelate.BUSINESS_USER_ID, busiUser.getId()));
        return busiUserRelateList.stream().map(BusiUserRelate::getBusinessId).collect(Collectors.toList());
    }

    @Override
    public BusiUserLoginInfoVO findByTokenUser(TokenUser tokenUser) {
        Long currentRelateId = tokenUser.getId();
        BusiUser busiUser = busiUserService.findById(currentRelateId);
        BusiUserLoginInfoVO busiUserLoginInfoVO = BusiUserMapStruct.INSTANCE.converToLoginInfoVO(busiUser);

        List<ShopVO> shopVOList = new ArrayList<>();

        // 获取该用户自己开的店铺
        BusiUserRelate busiUserRelate = busiUserRelateService.getMainByBusinessUserId(busiUser.getId());
        if(ObjectUtil.isNotEmpty(busiUserRelate)) {
            Long businessId = busiUserRelate.getBusinessId();
            // 查询该商户下的店铺信息
            shopVOList.addAll(shopService.queryVOByBusinessId(businessId));
        }

        // 获取该用户关联的所有店铺ID
        List<ShopUser> shopUserList = shopUserService.queryByBusinessUserId(busiUser.getId());
        Set<Long> shopIdSet = shopUserList.stream().map(ShopUser::getShopId).collect(Collectors.toSet());
        if(CollectionUtil.isNotEmpty(shopIdSet)) {
            shopVOList.addAll(shopService.queryVOByIds(shopIdSet));
        }

        busiUserLoginInfoVO.setShopVOList(shopVOList);
        return busiUserLoginInfoVO;
    }
}
