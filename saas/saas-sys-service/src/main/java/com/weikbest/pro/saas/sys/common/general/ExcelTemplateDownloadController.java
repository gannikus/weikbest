package com.weikbest.pro.saas.sys.common.general;

import com.weikbest.pro.saas.common.annotation.token.PassToken;
import com.weikbest.pro.saas.sys.param.entity.ExcelTemplate;
import com.weikbest.pro.saas.sys.param.service.ExcelTemplateService;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/23
 */
@Slf4j
@Api(tags = {"Manage——excel模板下载接口"})
@Controller
@RequestMapping("/excel-template-download")
public class ExcelTemplateDownloadController {

    @Resource
    private ThirdConfigService thirdConfigService;

    @Resource
    private ExcelTemplateService excelTemplateService;

    @PassToken
    @ApiOperation(value = "excel模板文件下载")
    @GetMapping("/downloadExcel/{number}")
    public void downloadExcel(
            HttpServletRequest request, HttpServletResponse response,
            @ApiParam(name = "number", value = "excel模板编码 t_sys_excel_template#number", required = true)
            @PathVariable String number) {

        // 获取excel模板
        ExcelTemplate excelTemplate = excelTemplateService.findByNumber(number);
        // excel下载
        thirdConfigService.aliyunOssService().downloadFileAvatarToWeb(excelTemplate.getTemplateUrl(), excelTemplate.getName(), response);
    }
}
