package com.weikbest.pro.saas.sysmerchat.prod.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.sysmerchat.prod.module.qo.CloneProdDTO;
import com.weikbest.pro.saas.sysmerchat.prod.module.qo.SysProdQO;
import com.weikbest.pro.saas.sysmerchat.prod.module.vo.SysProdListVO;
import com.weikbest.pro.saas.sysmerchat.prod.module.vo.SysProdVO;
import com.weikbest.pro.saas.sysmerchat.prod.service.SysProdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/1
 */
@Slf4j
@Api(tags = {"SysProd::平台商品接口"})
@RestController
@RequestMapping("/sys/prod")
public class SysProdController {

    @Resource
    private SysProdService sysProdService;

    @UseToken
    @QueryLog(value = "根据ID查询商品数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<SysProdVO> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        SysProdVO prod = sysProdService.findVOById(id);
        return DataResp.ok(prod);
    }

    @UseToken
    @QueryLog(value = "分页查询商品基本信息表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<SysProdListVO>> queryPage(
            @ApiParam(name = "sysProdQO", value = "查询条件")
                    SysProdQO sysProdQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<SysProdListVO> pageModel = sysProdService.queryPage(sysProdQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }

    @UseToken
    @UpdateLog(value = "商品设置排序")
    @ApiOperation(value = "商品设置排序")
    @PutMapping("/updateProdOrd/{id}/{prodOrd}")
    public DataResp<Boolean> updateProdOrd(@ApiParam(name = "id", value = "ID", required = true)
                                           @PathVariable Long id,
                                           @ApiParam(name = "prodOrd", value = "排序号", required = true)
                                           @PathVariable Integer prodOrd) {

        boolean update = sysProdService.updateProdOrd(id, prodOrd);
        return DataResp.ok(update);
    }


    @UseToken
    @UpdateLog(value = "商品复制")
    @ApiOperation(value = "商品复制")
    @PostMapping("/cloneProd")
    public DataResp<Object> cloneProd(@ApiParam(name = "cloneProdDTO", value = "商品复制", required = true)
                                          @RequestBody @Valid CloneProdDTO cloneProdDTO) {
        sysProdService.cloneProd(cloneProdDTO);
        return DataResp.ok();
    }
}
