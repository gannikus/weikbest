package com.weikbest.pro.saas.sys.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.system.entity.UserLoginRecord;
import com.weikbest.pro.saas.sys.system.module.dto.UserLoginRecordDTO;
import com.weikbest.pro.saas.sys.system.module.qo.UserLoginRecordQO;

/**
 * <p>
 * 系统用户登录记录表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
public interface UserLoginRecordService extends IService<UserLoginRecord> {

    /**
     * 新增数据
     *
     * @param userLoginRecordDTO userLoginRecordDTO
     * @return 新增结果
     */
    boolean insert(UserLoginRecordDTO userLoginRecordDTO);

    /**
     * 更新数据
     *
     * @param id                 ID
     * @param userLoginRecordDTO userLoginRecordDTO
     * @return 更新结果
     */
    boolean updateById(Long id, UserLoginRecordDTO userLoginRecordDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    UserLoginRecord findById(Long id);

    /**
     * 分页查询
     *
     * @param userLoginRecordQO
     * @param pageReq
     * @return
     */
    IPage<UserLoginRecord> queryPage(UserLoginRecordQO userLoginRecordQO, PageReq pageReq);
}
