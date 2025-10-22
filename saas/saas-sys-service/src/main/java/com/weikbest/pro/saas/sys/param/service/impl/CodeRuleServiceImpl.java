package com.weikbest.pro.saas.sys.param.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.coderule.CodeRuleUtil;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.redis.RedisLock;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.entity.CodeRule;
import com.weikbest.pro.saas.sys.param.mapper.CodeRuleMapper;
import com.weikbest.pro.saas.sys.param.module.dto.CodeRuleDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.CodeRuleMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.CodeRuleQO;
import com.weikbest.pro.saas.sys.param.service.CodeRuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 系统编码规则表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Service
public class CodeRuleServiceImpl extends ServiceImpl<CodeRuleMapper, CodeRule> implements CodeRuleService {

    @Resource
    private RedisContext redisContext;

    @Resource
    private RedisLock redisLock;

    @Override
    public boolean insert(CodeRuleDTO codeRuleDTO) {
        CodeRule codeRule = CodeRuleMapStruct.INSTANCE.converToEntity(codeRuleDTO);
        // 生成默认编码
        Integer realDigit = codeRule.getRealDigit();
        String currNum = CodeRuleUtil.initCodeRule(codeRule.getRuleType(), CodeRuleUtil.initSerial(realDigit));
        codeRule.setCurrNum(currNum);
        boolean save = this.save(codeRule);

        if (save) {
            // 放入缓存
            String redisCodeRuleKey = getRedisCodeRuleKey(codeRule);
            if (!redisContext.hasKey(redisCodeRuleKey)) {
                // 新建编码服务缓存
                long realNum = CodeRuleUtil.realNum(codeRule.getCurrNum());
                redisContext.set(redisCodeRuleKey, realNum, RedisContext.EIGHT_HOUR_EXPIRE);
            }
        }
        return save;
    }

    private String getRedisCodeRuleKey(CodeRule codeRule) {
        String redisCodeRuleKey;
        if (StrUtil.equals(codeRule.getRuleType(), DictConstant.CodeRuleType.DATETIME_SERIAL.getCode())) {
            redisCodeRuleKey = RedisKey.generalKey(RedisKey.MANAGE_CODE_RULE, codeRule.getNumber(), DateUtil.format(new DateTime(), DatePattern.PURE_DATE_FORMAT));
        } else {
            redisCodeRuleKey = RedisKey.generalKey(RedisKey.MANAGE_CODE_RULE, codeRule.getNumber());
        }
        return redisCodeRuleKey;
    }

    @Override
    public boolean updateById(Long id, CodeRuleDTO codeRuleDTO) {
        CodeRule codeRule = this.findById(id);
        CodeRuleMapStruct.INSTANCE.copyProperties(codeRuleDTO, codeRule);
        codeRule.setId(id);
        return this.updateById(codeRule);
    }

    @Override
    public CodeRule findById(Long id) {
        CodeRule codeRule = this.getById(id);
        if (ObjectUtil.isNull(codeRule)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return codeRule;
    }

    @Override
    public IPage<CodeRule> queryPage(CodeRuleQO codeRuleQO, PageReq pageReq) {
        QueryWrapper<CodeRule> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(codeRuleQO.getNumber())) {
            wrapper.eq(CodeRule.NUMBER, codeRuleQO.getNumber());
        }
        if (StrUtil.isNotBlank(codeRuleQO.getName())) {
            wrapper.eq(CodeRule.NAME, codeRuleQO.getName());
        }
        if (StrUtil.isNotBlank(codeRuleQO.getPrefix())) {
            wrapper.eq(CodeRule.PREFIX, codeRuleQO.getPrefix());
        }
        if (StrUtil.isNotBlank(codeRuleQO.getConnector())) {
            wrapper.eq(CodeRule.CONNECTOR, codeRuleQO.getConnector());
        }
        if (StrUtil.isNotBlank(codeRuleQO.getCurrNum())) {
            wrapper.eq(CodeRule.CURR_NUM, codeRuleQO.getCurrNum());
        }
        if (StrUtil.isNotBlank(codeRuleQO.getDescription())) {
            wrapper.eq(CodeRule.DESCRIPTION, codeRuleQO.getDescription());
        }
        if (StrUtil.isNotBlank(codeRuleQO.getDataStatus())) {
            wrapper.eq(CodeRule.DATA_STATUS, codeRuleQO.getDataStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public String nextNum(String number) {
        String lock = RedisKey.generalKey(RedisKey.Lock.LOCK_CODE_RULE, number);
        redisLock.lock(lock);
        try {
            // 根据number查询编码规则服务
            CodeRule codeRule = this.getOne(new QueryWrapper<CodeRule>().eq(CodeRule.NUMBER, number));
            if (ObjectUtil.isEmpty(codeRule)) {
                throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
            }

            // 判断缓存中是否存在该编码规则服务
            String redisCodeRuleKey = getRedisCodeRuleKey(codeRule);
            if (!redisContext.hasKey(redisCodeRuleKey)) {
                if (StrUtil.equals(codeRule.getRuleType(), DictConstant.CodeRuleType.DATETIME_SERIAL.getCode())) {
                    // 日期时间前缀的编码规则
                    String currNum = codeRule.getCurrNum();
                    // 获取日期时间前缀字符串
                    String datetime = StrUtil.sub(currNum, 0, DatePattern.PURE_DATE_PATTERN.length());
                    if (DateUtil.parse(datetime).compareTo(DateUtil.beginOfDay(new DateTime())) == 0) {
                        // 新建编码服务缓存流水号
                        long realNum = CodeRuleUtil.realNum(codeRule.getCurrNum());
                        redisContext.set(redisCodeRuleKey, realNum, RedisContext.EIGHT_HOUR_EXPIRE);
                    } else {
                        // 重建流水号
                        currNum = CodeRuleUtil.initCodeRule(codeRule.getRuleType(), CodeRuleUtil.initSerial(codeRule.getRealDigit()));
                        long realNum = CodeRuleUtil.realNum(currNum);
                        redisContext.set(redisCodeRuleKey, realNum, RedisContext.EIGHT_HOUR_EXPIRE);
                    }
                } else {
                    // 新建编码服务缓存流水号
                    long realNum = CodeRuleUtil.realNum(codeRule.getCurrNum());
                    redisContext.set(redisCodeRuleKey, realNum, RedisContext.EIGHT_HOUR_EXPIRE);
                }

            }
            // redis自增
            long incrNum = redisContext.incr(redisCodeRuleKey);
            // 获取真正编码
            String currNum = CodeRuleUtil.convertNum(codeRule.getRuleType(), incrNum, codeRule.getRealDigit());
            codeRule.setCurrNum(currNum);
            codeRule.setRealDigit(CodeRuleUtil.realDigit(codeRule.getRuleType(), currNum.length()));

            // 更新流水号信息
            this.updateById(codeRule);

            return codeRule.getPrefix() + codeRule.getConnector() + currNum;
        } finally {
            redisLock.unlock(lock);
        }
    }
}
