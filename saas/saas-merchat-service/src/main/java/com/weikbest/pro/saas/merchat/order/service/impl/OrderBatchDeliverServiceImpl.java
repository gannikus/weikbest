package com.weikbest.pro.saas.merchat.order.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.constant.BasicConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.ext.user.UserExtServiceFactory;
import com.weikbest.pro.saas.common.ext.user.entity.UserExt;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.redis.RedisLock;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.common.util.WeikbestObjectUtil;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSale;
import com.weikbest.pro.saas.merchat.aftersale.service.OrderAfterSaleService;
import com.weikbest.pro.saas.merchat.order.entity.OrderBatchDeliver;
import com.weikbest.pro.saas.merchat.order.entity.OrderBatchDeliverRecord;
import com.weikbest.pro.saas.merchat.order.entity.OrderInfo;
import com.weikbest.pro.saas.merchat.order.entity.OrderLogistics;
import com.weikbest.pro.saas.merchat.order.mapper.OrderBatchDeliverMapper;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderBatchDeliverDTO;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderBatchDeliverRecordExcelDTO;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderLogisticsDTO;
import com.weikbest.pro.saas.merchat.order.module.mapstruct.OrderBatchDeliverMapStruct;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderBatchDeliverQO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderBatchDeliverListVO;
import com.weikbest.pro.saas.merchat.order.service.OrderBatchDeliverRecordService;
import com.weikbest.pro.saas.merchat.order.service.OrderBatchDeliverService;
import com.weikbest.pro.saas.merchat.order.service.OrderInfoService;
import com.weikbest.pro.saas.merchat.order.service.OrderLogisticsService;
import com.weikbest.pro.saas.sys.common.cache.Memory;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.common.service.CurrentUserService;
import com.weikbest.pro.saas.sys.param.entity.LogisticsCompany;
import com.weikbest.pro.saas.sys.param.service.LogisticsCompanyService;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单批量发货记录表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-26
 */
@Slf4j
@Service
public class OrderBatchDeliverServiceImpl extends ServiceImpl<OrderBatchDeliverMapper, OrderBatchDeliver> implements OrderBatchDeliverService {

    @Resource
    private CurrentUserService currentUserService;

    @Resource
    private OrderAfterSaleService orderAfterSaleService;

    @Resource
    private OrderLogisticsService orderLogisticsService;

    @Resource
    private ThirdConfigService thirdConfigService;

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private UserExtServiceFactory userExtServiceFactory;

    @Resource
    private OrderBatchDeliverRecordService orderBatchDeliverRecordService;
    @Resource
    private RedisLock redisLock;

    @Autowired
    private LogisticsCompanyService logisticsCompanyService;

    private static final String REDIS_LOCK_BATCH_DELIVER = "REDIS_LOCK_BATCH_DELIVER";

    @Override
    public boolean insert(OrderBatchDeliverDTO orderBatchDeliverDTO) {
        OrderBatchDeliver orderBatchDeliver = OrderBatchDeliverMapStruct.INSTANCE.converToEntity(orderBatchDeliverDTO);
        return this.save(orderBatchDeliver);
    }

    @Override
    public boolean updateById(Long id, OrderBatchDeliverDTO orderBatchDeliverDTO) {
        OrderBatchDeliver orderBatchDeliver = this.findById(id);
        OrderBatchDeliverMapStruct.INSTANCE.copyProperties(orderBatchDeliverDTO, orderBatchDeliver);
        orderBatchDeliver.setId(id);
        return this.updateById(orderBatchDeliver);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public OrderBatchDeliver findById(Long id) {
        OrderBatchDeliver orderBatchDeliver = this.getById(id);
        if (ObjectUtil.isNull(orderBatchDeliver)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderBatchDeliver;
    }

    @Override
    public IPage<OrderBatchDeliverListVO> queryPage(OrderBatchDeliverQO orderBatchDeliverQO, PageReq pageReq) {
        IPage<OrderBatchDeliverListVO> orderBatchDeliverListVOIPage = this.baseMapper.queryPage(new Page<>(pageReq.getPage(), pageReq.getLimit()), orderBatchDeliverQO);
        List<OrderBatchDeliverListVO> records = orderBatchDeliverListVOIPage.getRecords();
        records.forEach(orderBatchDeliverListVO -> {
            // 1.获取操作人信息
            String operatorType = orderBatchDeliverListVO.getOperatorType();
            UserExt user = userExtServiceFactory.getUserExtService(operatorType).getUser(orderBatchDeliverListVO.getOperator());
            orderBatchDeliverListVO.setOperatorName(user.getName());
        });
        return orderBatchDeliverListVOIPage;
    }

    @Override
    public Long batchOrderDeliverAndUpdateOrderStatus(Long businessId, Long shopId, MultipartFile excelFile) {
        Long returnId = GenerateIDUtil.nextId();
        try {
            ExcelReader reader = ExcelUtil.getReader(excelFile.getInputStream(), 0);
            List<Map<String, Object>> batchOrderDeliverMapList = reader.readAll();
            if (CollectionUtil.isEmpty(batchOrderDeliverMapList)) {
                throw new WeikbestException("导入的excel数据为空，请检查！");
            }

            //查询当前存在的快递公司名称
            List<LogisticsCompany> companys = logisticsCompanyService.getLogisticsCompanys();
            List<String> names = companys.stream().map(LogisticsCompany::getName).collect(Collectors.toList());

            //生成锁id
            String key = RedisKey.generalKey(REDIS_LOCK_BATCH_DELIVER, businessId, shopId);

            // 获取数量信息
            int total = batchOrderDeliverMapList.size();

            String fileName = String.format("批量发货-%s.xlsx", DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN));

            // 保存批量发货记录
            OrderBatchDeliver orderBatchDeliver = new OrderBatchDeliver();
            orderBatchDeliver.setId(returnId)
                    .setBusinessId(businessId).setShopId(shopId)
                    .setOperateExcelName(fileName)
                    .setRecordTime(DateUtil.date())
                    .setStatus(DictConstant.OrderBatchDeliverStatus.uploading.getCode())
                    .setOperator(currentUserService.getTokenUser().getId())
                    .setOperatorType(currentUserService.getTokenUser().getRelateType())
                    .setTotalNum(total);
            this.save(orderBatchDeliver);

            //另起线程执行
            ThreadUtil.execute(()-> saveBatchDeliver(returnId, batchOrderDeliverMapList, key, shopId));

        } catch (IOException e) {
            log.error(e.getMessage());
            throw new WeikbestException(e);
        }

        return returnId;
    }

    public void saveBatchDeliver(Long id,List<Map<String, Object>> batchOrderDeliverMapList,String key, Long shopId) {
        // 返回对象
        List<OrderBatchDeliverRecordExcelDTO> batchOrderDeliverResultList = new ArrayList<>(batchOrderDeliverMapList.size());
        //查询当前存在的快递公司名称
        List<LogisticsCompany> companys = logisticsCompanyService.getLogisticsCompanys();
        List<String> names = companys.stream().map(LogisticsCompany::getName).collect(Collectors.toList());

        //加锁
//            redisLock.lock(key,60);
        boolean tryLock = redisLock.tryLock(key);

        if (!tryLock) {
            throw new WeikbestException("当前文件正在上传，请稍后再试！");
        } else {
            try {
                for (Map<String, Object> batchOrderDeliverMap : batchOrderDeliverMapList) {
                    // 构建实体
                    OrderBatchDeliverRecordExcelDTO orderBatchDeliverRecordExcelDTO = new OrderBatchDeliverRecordExcelDTO()
                            .setOrderNumber(MapUtil.getStr(batchOrderDeliverMap, "订单号"))
                            .setLogisticsCompanyName(MapUtil.getStr(batchOrderDeliverMap, "快递公司"))
                            .setCourierNumber(MapUtil.getStr(batchOrderDeliverMap, "快递单号"))
                            .setResult("成功");
                    try {
                        // 查询
                        OrderInfo orderInfo = orderInfoService.getByOrderNumber(orderBatchDeliverRecordExcelDTO.getOrderNumber());
                        if (ObjectUtil.isNull(orderInfo)) {
                            orderBatchDeliverRecordExcelDTO.setResult("失败");
                            orderBatchDeliverRecordExcelDTO.setRemark("订单号不存在！");
                            // 保存数据
                            batchOrderDeliverResultList.add(orderBatchDeliverRecordExcelDTO);
                            continue;
                        }
                        if (ObjectUtil.isNotNull(orderInfo.getShopId()) && !shopId.equals(orderInfo.getShopId())) {
                            orderBatchDeliverRecordExcelDTO.setResult("失败");
                            orderBatchDeliverRecordExcelDTO.setRemark("非当前店铺的订单！");
                            // 保存数据
                            batchOrderDeliverResultList.add(orderBatchDeliverRecordExcelDTO);
                            continue;
                        }
                        if (!orderInfo.getOrderStatus().equals(BasicConstant.STATE_10) && !orderInfo.getOrderStatus().equals(DictConstant.OrderStatus.orderStatus_20.getCode())) {
                            orderBatchDeliverRecordExcelDTO.setResult("失败");
                            orderBatchDeliverRecordExcelDTO.setRemark("订单不在待发货状态中！");
                            // 保存数据
                            batchOrderDeliverResultList.add(orderBatchDeliverRecordExcelDTO);
                            continue;
                        }
                        if (BasicConstant.STATE_1.equals(orderInfo.getOrderAfterFlag())) {
                            orderBatchDeliverRecordExcelDTO.setResult("失败");
                            orderBatchDeliverRecordExcelDTO.setRemark("当前订单还有售后未处理！");
                            // 保存数据
                            batchOrderDeliverResultList.add(orderBatchDeliverRecordExcelDTO);
                            continue;
                        }
                        //验证物流单号
                        if (StrUtil.isBlank(orderBatchDeliverRecordExcelDTO.getCourierNumber())) {
                            orderBatchDeliverRecordExcelDTO.setResult("失败");
                            orderBatchDeliverRecordExcelDTO.setRemark("快递单号为空！");
                            // 保存数据
                            batchOrderDeliverResultList.add(orderBatchDeliverRecordExcelDTO);
                            continue;
                        }
                        //添加快递公司名称检验
                        if (!names.contains(orderBatchDeliverRecordExcelDTO.getLogisticsCompanyName())) {
                            orderBatchDeliverRecordExcelDTO.setResult("失败");
                            orderBatchDeliverRecordExcelDTO.setRemark("快递公司名称不规范！");
                            // 保存数据
                            batchOrderDeliverResultList.add(orderBatchDeliverRecordExcelDTO);
                            continue;
                        }

                        /*OrderLogistics orderLogistics = orderLogisticsService.getByOrderId(orderInfo.getId());
                        if (ObjectUtil.isNotNull(orderLogistics)) {
                            orderBatchDeliverRecordExcelDTO.setResult("失败");
                            orderBatchDeliverRecordExcelDTO.setRemark("订单已发货！");
                            // 保存数据
                            batchOrderDeliverResultList.add(orderBatchDeliverRecordExcelDTO);
                            continue;
                        }*/

                        // 查询该订单是否有售后记录
                        Long orderAfterSaleId = orderInfo.getOrderAfterSaleId();
                        if (WeikbestObjectUtil.isNotEmpty(orderAfterSaleId)) {
                            OrderAfterSale orderAfterSale = orderAfterSaleService.findById(orderAfterSaleId);
                            if (StrUtil.equals(orderAfterSale.getAfterSaleStatus(), DictConstant.AfterSaleStatus.afterSaleStatus_9.getCode())) {
                                orderBatchDeliverRecordExcelDTO.setResult("失败");
                                orderBatchDeliverRecordExcelDTO.setRemark("已退款");
                                // 保存数据
                                batchOrderDeliverResultList.add(orderBatchDeliverRecordExcelDTO);
                                continue;
                            }
                            if (StrUtil.equals(orderAfterSale.getAfterSaleStatus(), DictConstant.AfterSaleStatus.afterSaleStatus_2.getCode())) {
                                orderBatchDeliverRecordExcelDTO.setResult("失败");
                                orderBatchDeliverRecordExcelDTO.setRemark("售后中");
                                // 保存数据
                                batchOrderDeliverResultList.add(orderBatchDeliverRecordExcelDTO);
                                continue;
                            }
                        }

                        // 成功导入发货信息
                        orderBatchDeliverRecordExcelDTO.setOrderId(orderInfo.getId());

                        // 更新订单信息
                        OrderLogisticsDTO orderLogisticsDTO = new OrderLogisticsDTO()
                                .setOrderId(orderInfo.getId())
                                .setLogisticsCompany(Memory.getLogisticsCompanyNumberByName(orderBatchDeliverRecordExcelDTO.getLogisticsCompanyName()))
                                .setCourierNumber(orderBatchDeliverRecordExcelDTO.getCourierNumber())
                                .setIsBatchDeliver(DictConstant.Whether.yes.getCode())
                                .setBatchDeliverId(id);
                        orderLogisticsService.orderDeliverAndUpdateOrderStatus(orderLogisticsDTO);

                        // 保存数据
                        batchOrderDeliverResultList.add(orderBatchDeliverRecordExcelDTO);
                    } catch (Exception e) {
                        log.error(e.getMessage());
                        orderBatchDeliverRecordExcelDTO.setResult("失败");
                        orderBatchDeliverRecordExcelDTO.setRemark("订单数据保存失败！" + e.getMessage());
                        batchOrderDeliverResultList.add(orderBatchDeliverRecordExcelDTO);
                    }
                }
            }catch (Exception e){
                log.error(e.getMessage());
            }finally {
                redisLock.unlock(key);
            }
        }

        // 获取数量信息
        int total = batchOrderDeliverResultList.size();
        int success = (int) batchOrderDeliverResultList.stream().filter(batchOrderDeliver -> StrUtil.isBlank(batchOrderDeliver.getRemark())).count();
        int error = total - success;
        log.info("批量发货id:{}，批量发货结果，总数：{}，成功：{}，失败：{}", id, total, success, error);

        OrderBatchDeliver orderBatchDeliver = this.getById(id);

        // 输出excel
        File tempFile = FileUtil.file(FileUtil.getTmpDirPath(), System.currentTimeMillis() + ".xlsx");
        EasyExcel.write(tempFile, OrderBatchDeliverRecordExcelDTO.class).sheet("批量发货结果").doWrite(batchOrderDeliverResultList);

        // 获取文件流
        InputStream inputStream = IoUtil.toStream(tempFile);
        String ossPath = thirdConfigService.aliyunOssService().uploadFileAvatar(inputStream, "order", orderBatchDeliver.getOperateExcelName());

        // 保存批量发货记录
        orderBatchDeliver
                .setOperateExcelUrl(ossPath)
                .setRecordTime(DateUtil.date())
                .setStatus(DictConstant.OrderBatchDeliverStatus.uploaded.getCode())
                .setTotalNum(total).setSuccessNum(success).setErrorNum(error);
        this.updateById(orderBatchDeliver);
        // 保存批量发货记录
        List<OrderBatchDeliverRecord> orderBatchDeliverRecordList = new ArrayList<>(batchOrderDeliverResultList.size());
        for (OrderBatchDeliverRecordExcelDTO orderBatchDeliverRecordExcelDTO : batchOrderDeliverResultList) {
            OrderBatchDeliverRecord orderBatchDeliverRecord = new OrderBatchDeliverRecord();
            orderBatchDeliverRecord.setId(id);
            orderBatchDeliverRecord.setOrderId(orderBatchDeliverRecordExcelDTO.getOrderId());
            orderBatchDeliverRecord.setOrderNumber(orderBatchDeliverRecordExcelDTO.getOrderNumber());
            orderBatchDeliverRecord.setLogisticsCompanyName(orderBatchDeliverRecordExcelDTO.getLogisticsCompanyName());
            orderBatchDeliverRecord.setCourierNumber(orderBatchDeliverRecordExcelDTO.getCourierNumber());
            orderBatchDeliverRecord.setImportStatus(StrUtil.isNotBlank(orderBatchDeliverRecordExcelDTO.getRemark()) ? DictConstant.Whether.no.getCode() : DictConstant.Whether.yes.getCode());
            orderBatchDeliverRecordList.add(orderBatchDeliverRecord);
        }
        orderBatchDeliverRecordService.saveBatch(orderBatchDeliverRecordList);

        // 删除临时文件
        try {
            FileUtil.del(tempFile);
        } catch (IORuntimeException e) {
            log.error(String.format("临时文件：%s 删除异常！ %s", tempFile.getName(), e.getMessage()));
        }
    }
}
