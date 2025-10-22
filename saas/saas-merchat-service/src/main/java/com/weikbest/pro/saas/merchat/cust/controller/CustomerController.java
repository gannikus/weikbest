package com.weikbest.pro.saas.merchat.cust.controller;

import com.weikbest.pro.saas.merchat.cust.service.CustomerService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-16
 */
@Slf4j
@Api(tags = {"cust::客户表接口"})
@RestController
@RequestMapping("/cust/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

//    @UseToken
//    @SaveLog(value = "新增客户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "新增数据")
//    @PostMapping("/insert")
//    public DataResp<Boolean> insert(
//            @ApiParam(name = "customerDTO", value = "保存数据信息", required = true)
//            @Valid CustomerDTO customerDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean save = customerService.insert(customerDTO);
//        return DataResp.ok(save);
//    }

//    @UseToken
//    @UpdateLog(value = "更新客户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "更新数据")
//    @PutMapping("/update/{id}")
//    public DataResp<Boolean> update(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id,
//            @ApiParam(name = "customerDTO", value = "更新数据信息", required = true)
//            @Valid CustomerDTO customerDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean update = customerService.updateById(id, customerDTO);
//        return DataResp.ok(update);
//    }

//    @UseToken
//    @RemoveLog(value = "根据ID删除客户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        customerService.deleteBatchByIds(Collections.singletonList(id));
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除客户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        customerService.deleteBatchByIds(ids);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @QueryLog(value = "根据ID查询客户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "根据ID查询")
//    @GetMapping("/find/{id}")
//    public DataResp<Customer> find(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        Customer customer = customerService.findById(id);
//        return DataResp.ok(customer);
//    }
//
//    @UseToken
//    @QueryLog(value = "分页查询客户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "分页查询")
//    @GetMapping("/queryPage")
//    public DataResp<List<Customer>> queryPage(
//            @ApiParam(name = "customerQO", value = "查询条件")
//                    CustomerQO customerQO,
//            @ApiParam(name = "pageReq", value = "分页参数", required = true)
//                    PageReq pageReq) {
//
//        IPage<Customer> pageModel = customerService.queryPage(customerQO, pageReq);
//        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
//    }
}
