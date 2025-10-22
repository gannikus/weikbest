package com.weikbest.pro.saas.sys.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.ext.user.UserExtService;
import com.weikbest.pro.saas.common.ext.user.UserExtServiceFactory;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.sys.system.entity.UserRelate;
import com.weikbest.pro.saas.sys.system.mapper.UserRelateMapper;
import com.weikbest.pro.saas.sys.system.module.dto.UserRelateDTO;
import com.weikbest.pro.saas.sys.system.module.mapstruct.UserRelateMapStruct;
import com.weikbest.pro.saas.sys.system.module.qo.UserRelateQO;
import com.weikbest.pro.saas.sys.system.service.UserRelateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户关联表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Service
public class UserRelateServiceImpl extends ServiceImpl<UserRelateMapper, UserRelate> implements UserRelateService {

    @Resource
    private UserExtServiceFactory userExtServiceFactory;

    @Override
    public boolean insert(UserRelateDTO userRelateDTO) {
        UserRelate userRelate = UserRelateMapStruct.INSTANCE.converToEntity(userRelateDTO);
        return this.save(userRelate);
    }

    @Override
    public boolean updateById(Long id, UserRelateDTO userRelateDTO) {
        UserRelate userRelate = this.findById(id);
        UserRelateMapStruct.INSTANCE.copyProperties(userRelateDTO, userRelate);
        userRelate.setId(id);
        return this.updateById(userRelate);
    }

    @Override
    public UserRelate findById(Long id) {
        UserRelate userRelate = this.getById(id);
        if (ObjectUtil.isNull(userRelate)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return userRelate;
    }

    @Override
    public IPage<UserRelate> queryPage(UserRelateQO userRelateQO, PageReq pageReq) {
        QueryWrapper<UserRelate> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(userRelateQO.getRelateType())) {
            wrapper.eq(UserRelate.RELATE_TYPE, userRelateQO.getRelateType());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBatchByIds(List<Long> userIdList) {
        // 查询系统用户关联表
        List<UserRelate> userRelateList = this.list(new QueryWrapper<UserRelate>().in(UserRelate.USER_ID, userIdList));
        if (CollectionUtil.isNotEmpty(userRelateList)) {
            // 将关联用户类型与数据集建立映射关系
            Map<String, List<UserRelate>> userRelateTypeUserRelateListMap = userRelateList.stream().collect(Collectors.groupingBy(UserRelate::getRelateType));

            List<UserExtService> userExtServiceList = userExtServiceFactory.getAllUserExtService();
            for (UserExtService userExtService : userExtServiceList) {
                List<UserRelate> userRelates = userRelateTypeUserRelateListMap.get(userExtService.getUserRelateType());
                if (CollectionUtil.isNotEmpty(userRelates)) {
                    // 执行删除逻辑
                    userExtService.removeByIds(userRelateList.stream().map(UserRelate::getRelateId).collect(Collectors.toList()));
                }
            }

            // 删除系统用户关联表
            this.removeBatchByIds(userRelateList.stream().map(UserRelate::getId).collect(Collectors.toList()));
        }
    }

    @Override
    public UserRelate findByRelateId(Long relateId, String relateType) {
        UserRelate userRelate = this.getOne(new QueryWrapper<UserRelate>().eq(UserRelate.RELATE_ID, relateId).eq(UserRelate.RELATE_TYPE, relateType));
//        if (ObjectUtil.isNull(userRelate)) {
//            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
//        }
        return userRelate;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBatchByRelateIds(List<Long> relateIdList) {
        if (CollectionUtil.isNotEmpty(relateIdList)) {
            this.remove(new QueryWrapper<UserRelate>().in(UserRelate.RELATE_ID, relateIdList));
        }
    }
}
