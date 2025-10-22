package com.weikbest.pro.saas.sys.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.sys.system.entity.UserLoginRecord;
import com.weikbest.pro.saas.sys.system.mapper.UserLoginRecordMapper;
import com.weikbest.pro.saas.sys.system.module.dto.UserLoginRecordDTO;
import com.weikbest.pro.saas.sys.system.module.mapstruct.UserLoginRecordMapStruct;
import com.weikbest.pro.saas.sys.system.module.qo.UserLoginRecordQO;
import com.weikbest.pro.saas.sys.system.service.UserLoginRecordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户登录记录表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Service
public class UserLoginRecordServiceImpl extends ServiceImpl<UserLoginRecordMapper, UserLoginRecord> implements UserLoginRecordService {

    @Override
    public boolean insert(UserLoginRecordDTO userLoginRecordDTO) {
        UserLoginRecord userLoginRecord = UserLoginRecordMapStruct.INSTANCE.converToEntity(userLoginRecordDTO);
        return this.save(userLoginRecord);
    }

    @Override
    public boolean updateById(Long id, UserLoginRecordDTO userLoginRecordDTO) {
        UserLoginRecord userLoginRecord = this.findById(id);
        UserLoginRecordMapStruct.INSTANCE.copyProperties(userLoginRecordDTO, userLoginRecord);
        userLoginRecord.setId(id);
        return this.updateById(userLoginRecord);
    }

    @Override
    public UserLoginRecord findById(Long id) {
        UserLoginRecord userLoginRecord = this.getById(id);
        if (ObjectUtil.isNull(userLoginRecord)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return userLoginRecord;
    }

    @Override
    public IPage<UserLoginRecord> queryPage(UserLoginRecordQO userLoginRecordQO, PageReq pageReq) {
        QueryWrapper<UserLoginRecord> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(userLoginRecordQO.getLoginIp())) {
            wrapper.eq(UserLoginRecord.LOGIN_IP, userLoginRecordQO.getLoginIp());
        }
        if (StrUtil.isNotBlank(userLoginRecordQO.getOnline())) {
            wrapper.eq(UserLoginRecord.ONLINE, userLoginRecordQO.getOnline());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
