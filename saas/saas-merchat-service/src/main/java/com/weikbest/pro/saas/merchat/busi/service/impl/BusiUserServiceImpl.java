package com.weikbest.pro.saas.merchat.busi.service.impl;

import cn.hutool.core.collection.CollectionUtil;
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
import com.weikbest.pro.saas.merchat.busi.entity.BusiUser;
import com.weikbest.pro.saas.merchat.busi.entity.BusiUserRelate;
import com.weikbest.pro.saas.merchat.busi.mapper.BusiUserMapper;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiUserBasicDTO;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiUserDTO;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiUserRegistDTO;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusinessDTO;
import com.weikbest.pro.saas.merchat.busi.module.mapstruct.BusiUserMapStruct;
import com.weikbest.pro.saas.merchat.busi.module.mapstruct.BusinessMapStruct;
import com.weikbest.pro.saas.merchat.busi.module.qo.BusiUserQO;
import com.weikbest.pro.saas.merchat.busi.module.vo.BusiUserPageVO;
import com.weikbest.pro.saas.merchat.busi.service.BusiUserRelateService;
import com.weikbest.pro.saas.merchat.busi.service.BusiUserService;
import com.weikbest.pro.saas.merchat.busi.service.BusinessService;
import com.weikbest.pro.saas.merchat.shop.entity.ShopUser;
import com.weikbest.pro.saas.merchat.shop.service.ShopUserService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.system.service.UserRelateService;
import com.weikbest.pro.saas.sys.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 商户账户表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-12
 */
@Slf4j
@Service
public class BusiUserServiceImpl extends ServiceImpl<BusiUserMapper, BusiUser> implements BusiUserService {

    @Resource
    private UserService userService;

    @Resource
    private BusinessService businessService;

    @Resource
    private BusiUserRelateService busiUserRelateService;

    @Resource
    private UserRelateService userRelateService;

    @Resource
    private ShopUserService shopUserService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long insertSubReturnId(BusiUserDTO busiUserDTO, Long businessId) {
        // 新增商户用户
        BusiUser busiUser = this.getOne(new QueryWrapper<BusiUser>().eq(BusiUser.PHONE, busiUserDTO.getPhone()));
        if(busiUser == null) {
            // 手机号不存在商户号，注册一个商户账户
            busiUser = BusiUserMapStruct.INSTANCE.converToEntity(busiUserDTO);
            this.save(busiUser);

            // 非平台用户注册
            userService.registerRelateUser(busiUserDTO.getPhone(), busiUserDTO.getName(), busiUserDTO.getEmail(), busiUserDTO.getAvatar(), DictConstant.UserRelateType.merchat.getCode(), busiUser.getId());
        }

        // 新增商户与商户用户关联
        BusiUserRelate busiUserRelate = new BusiUserRelate().setBusinessUserId(busiUser.getId()).setBusinessId(businessId).setIsMainUser(DictConstant.Whether.no.getCode());
        busiUserRelateService.save(busiUserRelate);
        return busiUser.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long insertMainReturnId(BusinessDTO businessDTO, Long businessId) {
        BusiUserDTO busiUserDTO = BusinessMapStruct.INSTANCE.converToBusiUserDTO(businessDTO, businessId);
        BusiUser busiUser = BusiUserMapStruct.INSTANCE.converToEntity(busiUserDTO);
        busiUser.setIsMainUser(BasicConstant.STATE_1);
        this.save(busiUser);

        // 新增商户与商户用户关联
        BusiUserRelate busiUserRelate = new BusiUserRelate().setBusinessUserId(busiUser.getId()).setBusinessId(businessId).setIsMainUser(DictConstant.Whether.yes.getCode());
        busiUserRelateService.save(busiUserRelate);

        return busiUser.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(Long id, BusiUserDTO busiUserDTO) {
        // 判断手机号在商户表是否存在
        String phone = busiUserDTO.getPhone();
        if (StrUtil.isNotBlank(phone)) {
            BusiUser find = this.getByPhone(phone);
            if (ObjectUtil.isNotEmpty(find) && ObjectUtil.notEqual(find.getId(), id)) {
                throw new WeikbestException("该手机号已被别的商户使用，建议更换新手机号！");
            }
        }

        BusiUser busiUser = this.findById(id);
        BusiUserMapStruct.INSTANCE.copyProperties(busiUserDTO, busiUser);
        busiUser.setId(id);
        boolean update = this.updateById(busiUser);

        // 更新平台表的信息
        userService.updateByRelateUser(busiUserDTO.getPhone(), busiUserDTO.getName(), busiUserDTO.getEmail(), busiUserDTO.getAvatar(), DictConstant.UserRelateType.merchat.getCode(), id);
        return update;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateDataStatusById(Long id, String dataStatus) {
        // 1. 更新商户账号表
        BusiUser busiUser = this.findById(id);
        busiUser.setDataStatus(dataStatus);
        boolean update = this.updateById(busiUser);

        // 2.更新商户店铺账号表
        List<ShopUser> shopUserList = shopUserService.list(new QueryWrapper<ShopUser>().eq(ShopUser.BUSINESS_USER_ID, id));
        if (CollectionUtil.isNotEmpty(shopUserList)) {
            shopUserList.forEach(shopUser -> shopUser.setDataStatus(dataStatus));
            shopUserService.updateBatchById(shopUserList);
        }
        return update;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateBasicById(Long id, BusiUserBasicDTO busiUserBasicDTO) {
        BusiUser busiUser = this.findById(id);
        busiUser.setName(busiUserBasicDTO.getName())
                .setAvatar(busiUserBasicDTO.getAvatar())
                .setEmail(busiUserBasicDTO.getEmail());
        return this.updateById(busiUser);
    }

    @Override
    public boolean updateLoginNameById(Long id, String phone) {
        // 判断手机号在商户表是否存在
        BusiUser find = this.getByPhone(phone);
        if (ObjectUtil.isNotEmpty(find) && ObjectUtil.notEqual(find.getId(), id)) {
            throw new WeikbestException("该手机号已被别的商户使用，建议更换新手机号！");
        }

        BusiUser busiUser = this.findById(id);
        busiUser.setPhone(phone);
        boolean update = this.updateById(busiUser);

        // 更新平台表的信息
        userService.updateByRelateUser(busiUser.getPhone(), busiUser.getName(), busiUser.getEmail(), busiUser.getAvatar(), DictConstant.UserRelateType.merchat.getCode(), id);

        return update;
    }

    @Override
    public BusiUser getByPhone(String phone) {
        return this.getOne(new QueryWrapper<BusiUser>().eq(BusiUser.PHONE, phone));
    }

    @Override
    public BusiUser getByBusinessIdAndPhone(Long businessId, String phone) {
        BusiUser busiUser = getByPhone(phone);
        if(busiUser == null) return null;
        Long busiUserId = busiUser.getId();
        BusiUserRelate busiUserRelate = busiUserRelateService.getByBusinessIdAndBusinessUserId(businessId, busiUserId);
        if(busiUserRelate == null) return null;
        return busiUser;
    }

    @Override
    public BusiUser findByApiKey(String apiKey) {
        return this.getOne(new QueryWrapper<BusiUser>().eq(BusiUser.API_KEY,apiKey));
    }

    @Override
    public BusiUser findById(Long id) {
        BusiUser busiUser = this.getById(id);
        if (ObjectUtil.isNull(busiUser)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return busiUser;
    }

    @Override
    public IPage<BusiUserPageVO> queryPage(BusiUserQO busiUserQO, PageReq pageReq) {
        return this.baseMapper.queryPage(new Page<>(pageReq.getPage(), pageReq.getLimit()), busiUserQO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean regist(BusiUserRegistDTO busiUserRegistDTO) {
        String phone = busiUserRegistDTO.getPhone();
        BusiUser find = this.getByPhone(phone);
        if (ObjectUtil.isNotEmpty(find)) {
            throw new WeikbestException("该手机号已被别的商户使用，建议更换新手机号！");
        }

        return businessService.regist(busiUserRegistDTO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updatePassword(Long id, String oldPassword, String password) {
        BusiUser busiUser = this.findById(id);

        // 判断用户旧密码是否正确
        boolean isExist = userService.checkLoginPassword(id, busiUser.getPhone(), oldPassword, DictConstant.UserRelateType.merchat.getCode());
        if (!isExist) {
            throw new WeikbestException(ResultConstant.PHONE_PASSWORD_FAIL);
        }

        // 更新用户登录密码
        return userService.updateLoginPassword(busiUser.getPhone(), password, DictConstant.UserRelateType.merchat.getCode());
    }

    @Override
    public BusiUser findMainByBusinessId(Long businessId) {
        BusiUserRelate busiUserRelate = busiUserRelateService.findMainByBusinessId(businessId);
        BusiUser busiUser = this.getOne(new QueryWrapper<BusiUser>().eq(BusiUser.ID, busiUserRelate.getBusinessUserId())
                .eq(BusiUser.DATA_STATUS, DictConstant.Status.enable.getCode()));
        if (ObjectUtil.isEmpty(busiUser)) {
            throw new WeikbestException("商户主账号信息为空或被禁用，请检查数据！");
        }
        return busiUser;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBatchByBusinessIds(List<Long> businessIdList) {
        List<BusiUserRelate> busiUserRelateList = busiUserRelateService.list(new QueryWrapper<BusiUserRelate>().in(BusiUserRelate.BUSINESS_ID, businessIdList));
        if(CollectionUtil.isNotEmpty(busiUserRelateList)) {
            List<Long> busiUserIdList = busiUserRelateList.stream().map(BusiUserRelate::getBusinessUserId).collect(Collectors.toList());
            // 删除商户与商户账户关联表数据
            busiUserRelateService.remove(new QueryWrapper<BusiUserRelate>().in(BusiUserRelate.BUSINESS_ID, businessIdList));

            // 查询这些账户是否在关联表还存在数据
            busiUserRelateList = busiUserRelateService.list(new QueryWrapper<BusiUserRelate>().in(BusiUserRelate.BUSINESS_USER_ID, busiUserIdList));
            Set<Long> existsBusiUserIdSet = busiUserRelateList.stream().map(BusiUserRelate::getBusinessUserId).collect(Collectors.toSet());

            // 这些数据是不存在商户账户关联表的账户ID，说明这个账户已经没有用了
            List<Long> notExistsBusiUserIdList = busiUserIdList.stream().filter(busiUserId -> !existsBusiUserIdSet.contains(busiUserId)).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(notExistsBusiUserIdList)) {
                this.removeBatchByIds(notExistsBusiUserIdList);
            }
            // 删除系统用户关联表数据
            userRelateService.deleteBatchByRelateIds(notExistsBusiUserIdList);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBatchByIds(List<Long> ids) {
        this.removeBatchByIds(ids);

        // 删除系统用户关联表数据
        userRelateService.deleteBatchByRelateIds(ids);
    }


}
