package com.weikbest.pro.saas.sysmerchat.business.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.ext.user.UserExtService;
import com.weikbest.pro.saas.common.ext.user.UserExtServiceFactory;
import com.weikbest.pro.saas.common.ext.user.entity.UserExt;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.busi.entity.BusiUser;
import com.weikbest.pro.saas.merchat.busi.entity.BusiUserRelate;
import com.weikbest.pro.saas.merchat.busi.entity.Business;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiUserDTO;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusinessDTO;
import com.weikbest.pro.saas.merchat.busi.module.mapstruct.BusiUserMapStruct;
import com.weikbest.pro.saas.merchat.busi.service.BusiUserRelateService;
import com.weikbest.pro.saas.merchat.busi.service.BusiUserService;
import com.weikbest.pro.saas.merchat.busi.service.BusinessService;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdListVO;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.common.ext.CreateAvatarService;
import com.weikbest.pro.saas.sysmerchat.business.mapper.SysBusiMapper;
import com.weikbest.pro.saas.sysmerchat.business.module.mapstruct.SysBusiMapStruct;
import com.weikbest.pro.saas.sysmerchat.business.module.qo.SysBusiQO;
import com.weikbest.pro.saas.sysmerchat.business.module.vo.SysBusiListVO;
import com.weikbest.pro.saas.sysmerchat.business.module.vo.SysBusiVO;
import com.weikbest.pro.saas.sysmerchat.business.service.SysBusiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/1
 */
@Slf4j
@Service
public class SysBusiServiceImpl implements SysBusiService {

    @Resource
    private BusinessService businessService;

    @Resource
    private BusiUserService busiUserService;

    @Resource
    private BusiUserRelateService busiUserRelateService;

    @Resource
    private CreateAvatarService createAvatarService;

    @Resource
    private SysBusiMapper sysBusiMapper;

    @Resource
    private UserExtServiceFactory userExtServiceFactory;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(BusinessDTO businessDTO) {
        String phone = businessDTO.getPhone();
        BusiUser find = busiUserService.getByPhone(phone);
        if (ObjectUtil.isNotEmpty(find)) {
            throw new WeikbestException("该手机号已被使用，建议更换新手机号！");
        }

        if (StrUtil.isBlank(businessDTO.getAvatar())) {
            // 生成默认头像
            String avatar = createAvatarService.generateImageAndUploadOss(businessDTO.getUserName());
            businessDTO.setAvatar(avatar);
        }

        if (StrUtil.equals(businessDTO.getBusinessType(), DictConstant.BusinessType.platform.getCode())) {
            businessDTO.setIsSuper(DictConstant.Whether.yes.getCode());
        }
        return businessService.insert(businessDTO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateByBusinessId(Long businessId, BusinessDTO businessDTO) {
        String phone = businessDTO.getPhone();
        BusiUser find = busiUserService.getByPhone(phone);
        List<BusiUserRelate> busiUserRelateList = busiUserRelateService.queryByBusinessUserId(find.getId());
        Set<Long> businessIdSet = busiUserRelateList.stream().map(BusiUserRelate::getBusinessId).collect(Collectors.toSet());
        if (ObjectUtil.isNotEmpty(find) && !businessIdSet.contains(businessId)) {
            throw new WeikbestException("该手机号已被使用，建议更换新手机号！");
        }

        // 更新商户表
        boolean update = businessService.updateById(businessId, businessDTO);
        // 更新商户账户表（主账号）
        BusiUser busiUser = busiUserService.findMainByBusinessId(businessId);

        BusiUserDTO busiUserDTO = BusiUserMapStruct.INSTANCE.converToDTO(busiUser);
        busiUserDTO.setPhone(businessDTO.getPhone())
                .setName(businessDTO.getUserName())
                .setAvatar(businessDTO.getAvatar())
                .setEmail(businessDTO.getEmail());
        busiUserService.updateById(busiUser.getId(), busiUserDTO);

        return update;
    }

//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void removeById(Long id) {
//        // 查询商户ID
//        BusiUser busiUser = busiUserService.findById(id);
//        Long businessId = busiUser.getBusinessId();
//
//        // 删除商户
//        businessService.removeById(businessId);
//        // 根据商户ID删除商户账户
//        busiUserService.remove(new QueryWrapper<BusiUser>().eq(BusiUser.BUSINESS_ID, businessId));
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void removeBatchByIds(List<Long> ids) {
//        // 查询商户ID
//        List<BusiUser> busiUserList = busiUserService.listByIds(ids);
//        List<Long> businessIdList = busiUserList.stream().map(BusiUser::getBusinessId).collect(Collectors.toList());
//
//        // 删除商户
//        businessService.removeByIds(businessIdList);
//        // 根据商户ID删除商户账户
//        busiUserService.remove(new QueryWrapper<BusiUser>().in(BusiUser.BUSINESS_ID, businessIdList));
//    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateDataStatusById(Long busiUserId, String dataStatus) {
        // 查询商户ID
        BusiUser busiUser = busiUserService.findById(busiUserId);

        busiUser.setDataStatus(dataStatus);
        boolean update = busiUserService.updateById(busiUser);

        BusiUserRelate busiUserRelate = busiUserRelateService.getMainByBusinessUserId(busiUser.getId());
        if(ObjectUtil.isNotEmpty(busiUserRelate)) {
            // 将主账号对应的商户的信息变更
            Business business = businessService.findById(busiUserRelate.getBusinessId());
            business.setDataStatus(dataStatus);
            businessService.updateById(business);
        }

        return update;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBatchByIds(List<Long> businessIdList) {
        // 删除商户
        businessService.removeByIds(businessIdList);

        // 删除商户用户数据
        busiUserService.deleteBatchByBusinessIds(businessIdList);
    }

    @Override
    public SysBusiVO findVOById(Long busiUserId) {
        BusiUser busiUser = busiUserService.findById(busiUserId);
        BusiUserRelate busiUserRelate = busiUserRelateService.getMainByBusinessUserId(busiUserId);
        Business business = businessService.findById(busiUserRelate.getBusinessId());
        return SysBusiMapStruct.INSTANCE.converToVO(business, busiUser);
    }

    @Override
    public IPage<SysBusiListVO> queryPage(SysBusiQO sysBusiQO, PageReq pageReq) {
        IPage<SysBusiListVO> sysBusiListVOIPage = sysBusiMapper.queryPage(new Page<>(pageReq.getPage(), pageReq.getLimit()), sysBusiQO);
        List<SysBusiListVO> records = sysBusiListVOIPage.getRecords();
        // 查询更新人
        UserExtService userExtService = userExtServiceFactory.getUserExtService(DictConstant.UserRelateType.sys.getCode());
        records.forEach(sysBusiListVO -> {
            UserExt user = userExtService.getUser(sysBusiListVO.getModifier());
            sysBusiListVO.setModifierName(user.getName());
        });
        return sysBusiListVOIPage;
    }


}
