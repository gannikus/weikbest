package com.weikbest.pro.saas.common.ext.user;

import com.weikbest.pro.saas.common.ext.user.entity.UserExt;

import java.util.List;
import java.util.Map;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/9/20
 * <p>
 * 用户额外的服务
 */
public interface UserExtService {

    /**
     * 用户关联类型
     *
     * @return
     */
    String getUserRelateType();

    /**
     * 根据ID获取用户数据
     *
     * @param id
     * @return
     */
    UserExt getUser(Long id);

    /**
     * 根据ID列表删除
     *
     * @param idList
     * @return
     */
    boolean removeByIds(List<Long> idList);

    /**
     * 根据业务字段获取用户数据
     *
     * @param queryMap
     * @return
     */
    default UserExt getUser(Map<String, Object> queryMap) {
        return UserExt.defaultUserExt();
    }
}
