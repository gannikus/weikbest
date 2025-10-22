package com.weikbest.pro.saas.controller;

import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.properties.Project;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wisdomelon
 * @date 2021/4/30
 * @project saas
 * @jdk 1.8
 */
@Slf4j
@Api(tags = {"Boot——ReadMe"})
@RestController
@RequestMapping("/")
public class BootController {

    @Autowired
    private Project project;

    @GetMapping("/readme")
    public DataResp<Project> hello() {
        return DataResp.ok(project);
    }
}
