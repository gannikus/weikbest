package com.weikbest.pro.saas.merchat.busi.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.RemoveLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.redis.RedisService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.common.util.web.IpUtil;
import com.weikbest.pro.saas.merchat.busi.entity.BusiUser;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiUserBasicDTO;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiUserRegistDTO;
import com.weikbest.pro.saas.merchat.busi.module.qo.BusiUserQO;
import com.weikbest.pro.saas.merchat.busi.module.vo.BusiUserPageVO;
import com.weikbest.pro.saas.merchat.busi.service.BusiUserService;
import com.weikbest.pro.saas.sys.common.ext.CreateAvatarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 商户账户表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-12
 */
@Slf4j
@Api(tags = {"busi::商户账户表接口"})
@RestController
@RequestMapping("/busi/busi-user")
public class BusiUserController {

    @Resource
    private BusiUserService busiUserService;

    @Resource
    private CreateAvatarService createAvatarService;

    @Resource
    private RedisService redisService;

    @UseToken
    @SaveLog(value = "新商户注册", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "新商户注册")
    @PostMapping("/regist")
    public DataResp<Boolean> regist(HttpServletRequest request,
                                    @ApiParam(name = "busiUserRegistDTO", value = "保存数据信息", required = true)
                                    @Valid BusiUserRegistDTO busiUserRegistDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        String loginIp = IpUtil.getIpAddress(request);
        // 判断验证码是否失效
        String verifyKey = SecureUtil.md5(loginIp + busiUserRegistDTO.getPhone());
        // 通过手机号+手机验证码登录
        redisService.validateVerify(verifyKey, busiUserRegistDTO.getVerify());

        if (StrUtil.isBlank(busiUserRegistDTO.getAvatar())) {
            // 生成默认头像
            String avatar = createAvatarService.generateImageAndUploadOss(busiUserRegistDTO.getName());
            busiUserRegistDTO.setAvatar(avatar);
        }

        boolean save = busiUserService.regist(busiUserRegistDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新商户账户个人信息数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "更新商户账户个人信息数据")
    @PutMapping("/updateBasic/{id}")
    public DataResp<Boolean> updateBasic(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "busiUserBasicDTO", value = "更新数据信息", required = true)
            @Valid BusiUserBasicDTO busiUserBasicDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = busiUserService.updateBasicById(id, busiUserBasicDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @UpdateLog(value = "更新商户登录账号", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "更新商户登录账号")
    @PutMapping("/updateLoginName/{id}")
    public DataResp<Boolean> updateLoginName(HttpServletRequest request,
                                             @ApiParam(name = "id", value = "ID", required = true)
                                             @PathVariable Long id,
                                             @ApiParam(name = "phone", value = "手机号", required = true)
                                             @RequestParam String phone,
                                             @ApiParam(name = "verify", value = "验证码", required = true)
                                             @RequestParam String verify) {

        String loginIp = IpUtil.getIpAddress(request);
        // 判断验证码是否失效
        String verifyKey = SecureUtil.md5(loginIp + phone);
        // 通过手机号+手机验证码登录
        redisService.validateVerify(verifyKey, verify);

        boolean update = busiUserService.updateLoginNameById(id, phone);
        return DataResp.ok(update);
    }

    @UseToken
    @UpdateLog(value = "更新商户登录密码", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "更新商户登录密码")
    @PutMapping("/updatePassword/{id}")
    public DataResp<Boolean> updatePassword(@ApiParam(name = "id", value = "ID", required = true)
                                            @PathVariable Long id,
                                            @ApiParam(name = "oldPassword", value = "原密码", required = true)
                                            @RequestParam String oldPassword,
                                            @ApiParam(name = "password", value = "新密码", required = true)
                                            @RequestParam String password) {

        boolean update = busiUserService.updatePassword(id, oldPassword, password);
        return DataResp.ok(update);
    }

    @UseToken
    @UpdateLog(value = "禁用/启用商户账户", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "禁用/启用商户账户")
    @PutMapping("/updateDataStatus/{id}")
    public DataResp<Boolean> updateDataStatus(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "dataStatus", value = "数据状态 0-禁用 1-可用", required = true)
            @RequestParam String dataStatus) {

        boolean update = busiUserService.updateDataStatusById(id, dataStatus);
        return DataResp.ok(update);
    }

    @UseToken
    @RemoveLog(value = "根据ID删除商户账户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        busiUserService.deleteBatchByIds(Collections.singletonList(id));
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据ID列表删除商户账户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID列表删除")
    @DeleteMapping("/delete")
    public DataResp<Object> delete(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        busiUserService.deleteBatchByIds(ids);
        return DataResp.ok();
    }

    @UseToken
    @QueryLog(value = "根据ID查询商户账户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<BusiUser> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        BusiUser busiUser = busiUserService.findById(id);
        return DataResp.ok(busiUser);
    }

    @UseToken
    @QueryLog(value = "分页查询商户账户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<BusiUserPageVO>> queryPage(
            @ApiParam(name = "busiUserQO", value = "查询条件")
                    BusiUserQO busiUserQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<BusiUserPageVO> pageModel = busiUserService.queryPage(busiUserQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
