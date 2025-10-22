package com.weikbest.pro.saas.sys.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.json.JsonUtils;
import com.weikbest.pro.saas.sys.system.entity.User;
import com.weikbest.pro.saas.sys.system.entity.UserActivex;
import com.weikbest.pro.saas.sys.system.mapper.UserActivexMapper;
import com.weikbest.pro.saas.sys.system.module.dto.UserActivexDTO;
import com.weikbest.pro.saas.sys.system.module.mapstruct.UserActivexMapStruct;
import com.weikbest.pro.saas.sys.system.module.qo.UserActivexQO;
import com.weikbest.pro.saas.sys.system.service.UserActivexService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统用户控件关联表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-20
 */
@Service
public class UserActivexServiceImpl extends ServiceImpl<UserActivexMapper, UserActivex> implements UserActivexService {

    @Resource
    private RedisContext redisContext;

    @Resource
    private UserActivexMapper userActivexMapper;

    @Override
    public boolean insert(UserActivexDTO userActivexDTO) {
        UserActivex userActivex = UserActivexMapStruct.INSTANCE.converToEntity(userActivexDTO);
        return this.save(userActivex);
    }

    @Override
    public boolean updateById(Long id, UserActivexDTO userActivexDTO) {
        UserActivex userActivex = this.findById(id);
        UserActivexMapStruct.INSTANCE.copyProperties(userActivexDTO, userActivex);
        userActivex.setId(id);
        return this.updateById(userActivex);
    }

    @Override
    public UserActivex findById(Long id) {
        UserActivex userActivex = this.getById(id);
        if (ObjectUtil.isNull(userActivex)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return userActivex;
    }

    @Override
    public IPage<UserActivex> queryPage(UserActivexQO userActivexQO, PageReq pageReq) {
        QueryWrapper<UserActivex> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(userActivexQO.getAvatar())) {
            wrapper.eq(UserActivex.AVATAR, userActivexQO.getAvatar());
        }
        if (StrUtil.isNotBlank(userActivexQO.getNumber())) {
            wrapper.eq(UserActivex.NUMBER, userActivexQO.getNumber());
        }
        if (StrUtil.isNotBlank(userActivexQO.getName())) {
            wrapper.eq(UserActivex.NAME, userActivexQO.getName());
        }
        if (StrUtil.isNotBlank(userActivexQO.getPhone())) {
            wrapper.eq(UserActivex.PHONE, userActivexQO.getPhone());
        }
        if (StrUtil.isNotBlank(userActivexQO.getEmail())) {
            wrapper.eq(UserActivex.EMAIL, userActivexQO.getEmail());
        }
        if (StrUtil.isNotBlank(userActivexQO.getActivexDataStatus())) {
            wrapper.eq(UserActivex.ACTIVEX_DATA_STATUS, userActivexQO.getActivexDataStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void disabledByIds(List<Long> idList) {
        userActivexMapper.disabledByIds(idList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveByUser(UserActivex userActivex) {
        this.save(userActivex);

        // 存入缓存中
        redisContext.hset(RedisKey.MANAGE_USER_ACTIVEX_KEY, String.valueOf(userActivex.getId()), JsonUtils.bean2Json(userActivex));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateByUser(Long id, User user) {
        UserActivex userActivex = this.findById(id);

        // 不更新手机号，更新其他字段
        if (StrUtil.isBlank(userActivex.getName()) && StrUtil.isNotBlank(user.getName())) {
            userActivex.setName(user.getName());
        }
        if (StrUtil.isBlank(userActivex.getEmail()) && StrUtil.isNotBlank(user.getEmail())) {
            userActivex.setEmail(user.getEmail());
        }
        if (StrUtil.isBlank(userActivex.getAvatar()) && StrUtil.isNotBlank(user.getAvatar())) {
            userActivex.setAvatar(user.getAvatar());
        }
        this.updateById(userActivex);

        // 存入缓存中
        redisContext.hset(RedisKey.MANAGE_USER_ACTIVEX_KEY, String.valueOf(userActivex.getId()), JsonUtils.bean2Json(userActivex));
    }

    @Override
    public Map<String, Object> refreshCache() {
        List<UserActivex> userActivexes = this.list();

        Map<String, Object> resultMap = new HashMap<>(userActivexes.size());
        userActivexes.forEach(userActivex -> {
            String key = String.valueOf(userActivex.getId());
            String value = JsonUtils.bean2Json(userActivex);
            // 存入缓存中
            redisContext.hset(RedisKey.MANAGE_USER_ACTIVEX_KEY, key, value);
            // 存入返回对象
            resultMap.put(key, userActivex);
        });

        return resultMap;
    }

    @Override
    public UserActivex getByIdWithCache(Long userId) {
        UserActivex userActivex = this.getById(userId);
        if (ObjectUtil.isNotEmpty(userActivex)) {
            // 存入缓存中
            redisContext.hset(RedisKey.MANAGE_USER_ACTIVEX_KEY, String.valueOf(userId), JsonUtils.bean2Json(userActivex));
        }
        return userActivex;
    }

    @Override
    public UserActivex getByCache(Long id) {
        Map<String, Object> resultMap = new HashMap<>(WeikbestConstant.HASHMAP_INITIALCAPACITY);
        Object hget = redisContext.hget(RedisKey.MANAGE_USER_ACTIVEX_KEY, String.valueOf(id));
        if (ObjectUtil.isEmpty(hget)) {
            // 缓存查出为空，就去删除
            resultMap.putAll(this.refreshCache());
            return (UserActivex) resultMap.get(String.valueOf(id));
        }

        if (ObjectUtil.isNotEmpty(hget)) {
            return JsonUtils.json2Bean(String.valueOf(hget), UserActivex.class);
        }
        return this.getByIdWithCache(id);
    }
}
