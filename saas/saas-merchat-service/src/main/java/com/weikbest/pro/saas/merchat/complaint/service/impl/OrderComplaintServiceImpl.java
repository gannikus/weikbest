package com.weikbest.pro.saas.merchat.complaint.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.wxpay.service.WxPayService;
import com.weikbest.pro.saas.common.constant.BasicConstant;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.complaint.delaytaskprocess.OrderComplaintBusinessExecuteTimeoutDelayTaskProcess;
import com.weikbest.pro.saas.merchat.complaint.module.dto.OrderComplaintBusinessConfirmDTO;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaint;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaintImg;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaintImgHis;
import com.weikbest.pro.saas.merchat.complaint.mapper.OrderComplaintMapper;
import com.weikbest.pro.saas.merchat.complaint.module.dto.OrderComplaintDTO;
import com.weikbest.pro.saas.merchat.complaint.module.mapstruct.OrderComplaintImgHisMapStruct;
import com.weikbest.pro.saas.merchat.complaint.module.mapstruct.OrderComplaintMapStruct;
import com.weikbest.pro.saas.merchat.complaint.module.qo.OrderComplaintQO;
import com.weikbest.pro.saas.merchat.complaint.module.qo.OrderComplaintWxQO;
import com.weikbest.pro.saas.merchat.complaint.module.resp.WxOrderComplaintResp;
import com.weikbest.pro.saas.merchat.complaint.module.vo.*;
import com.weikbest.pro.saas.merchat.complaint.service.OrderComplaintImgHisService;
import com.weikbest.pro.saas.merchat.complaint.service.OrderComplaintImgService;
import com.weikbest.pro.saas.merchat.complaint.service.OrderComplaintRecordService;
import com.weikbest.pro.saas.merchat.complaint.service.OrderComplaintService;
import com.weikbest.pro.saas.merchat.order.entity.OrderCustInfo;
import com.weikbest.pro.saas.merchat.order.entity.OrderInfo;
import com.weikbest.pro.saas.merchat.order.entity.OrderPayRecord;
import com.weikbest.pro.saas.merchat.order.entity.OrderProdInfo;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderCustInfoVO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderInfoVO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderPayRecordVO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderProdInfoVO;
import com.weikbest.pro.saas.merchat.order.service.OrderCustInfoService;
import com.weikbest.pro.saas.merchat.order.service.OrderInfoService;
import com.weikbest.pro.saas.merchat.order.service.OrderPayRecordService;
import com.weikbest.pro.saas.merchat.order.service.OrderProdInfoService;
import com.weikbest.pro.saas.merchat.shop.entity.ShopThird;
import com.weikbest.pro.saas.merchat.shop.service.ShopThirdService;
import com.weikbest.pro.saas.sys.common.cache.MemoryService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.entity.DelayTaskRecord;
import com.weikbest.pro.saas.sys.param.service.DelayTaskRecordService;
import com.weikbest.pro.saas.sys.param.util.DelayTaskRecordUtil;
import com.weikbest.pro.saas.sys.param.util.ThirdConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单投诉表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-19
 */
@Slf4j
@Service
public class OrderComplaintServiceImpl extends ServiceImpl<OrderComplaintMapper, OrderComplaint> implements OrderComplaintService {

    @Resource
    private MemoryService memoryService;

    @Resource
    private ShopThirdService shopThirdService;

    @Resource
    private DelayTaskRecordService delayTaskRecordService;

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private OrderCustInfoService orderCustInfoService;

    @Resource
    private OrderPayRecordService orderPayRecordService;

    @Resource
    private OrderProdInfoService orderProdInfoService;

    @Resource
    private OrderComplaintRecordService orderComplaintRecordService;

    @Resource
    private OrderComplaintImgService orderComplaintImgService;

    @Resource
    private OrderComplaintImgHisService orderComplaintImgHisService;

    @Resource
    private OrderComplaintBusinessExecuteTimeoutDelayTaskProcess orderComplaintBusinessExecuteTimeoutDelayTaskProcess;

    @Override
    public boolean insert(OrderComplaintDTO orderComplaintDTO) {
        OrderComplaint orderComplaint = OrderComplaintMapStruct.INSTANCE.converToEntity(orderComplaintDTO);
        return this.save(orderComplaint);
    }

    @Override
    public boolean updateById(Long id, OrderComplaintDTO orderComplaintDTO) {
        OrderComplaint orderComplaint = this.findById(id);
        OrderComplaintMapStruct.INSTANCE.copyProperties(orderComplaintDTO, orderComplaint);
        orderComplaint.setId(id);
        return this.updateById(orderComplaint);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public OrderComplaint findById(Long id) {
        OrderComplaint orderComplaint = this.getById(id);
        if (ObjectUtil.isNull(orderComplaint)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderComplaint;
    }

    @Override
    public IPage<OrderComplaintPageVO> queryPage(OrderComplaintQO orderComplaintQO, PageReq pageReq) {
        return this.baseMapper.queryPage(new Page<>(pageReq.getPage(), pageReq.getLimit()), orderComplaintQO);
    }

    @Override
    public List<ComplaintBusiStatusGroupVO> queryGroupComplaintBusiStatus(Long businessId, Long shopId, String complaintType) {
        // 查询订单售后状态字典
        Map<String, String> complaintBusiStatusDictMap = memoryService.queryDictReturnMap(DictConstant.ComplaintBusiStatus.getDictTypeNumber());
        List<ComplaintBusiStatusGroupVO> resultList = new ArrayList<>();
        // 初始总数
        resultList.add(new ComplaintBusiStatusGroupVO().setComplaintBusiStatus("").setTotal(WeikbestConstant.ZERO_INT));
        // 初始数据
        complaintBusiStatusDictMap.forEach((key, value) -> resultList.add(new ComplaintBusiStatusGroupVO().setComplaintBusiStatus(key).setTotal(WeikbestConstant.ZERO_INT)));

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("businessId", businessId);
        paramMap.put("shopId", shopId);
        paramMap.put("complaintType", complaintType);
        List<ComplaintBusiStatusGroupVO> complaintBusiStatusGroupVOList = this.baseMapper.queryGroupOrderComplaintBusiStatus(paramMap);
        Map<String, ComplaintBusiStatusGroupVO> orderComplaintBusiStatusGroupVOMap = complaintBusiStatusGroupVOList.stream().collect(Collectors.toMap(ComplaintBusiStatusGroupVO::getComplaintBusiStatus, orderComplaintBusiStatusGroup -> orderComplaintBusiStatusGroup));

        // 遍历数据赋值
        resultList.forEach(complaintBusiStatusGroupVO -> {
            ComplaintBusiStatusGroupVO totalVO = orderComplaintBusiStatusGroupVOMap.get(complaintBusiStatusGroupVO.getComplaintBusiStatus());
            if (ObjectUtil.isNotEmpty(totalVO)) {
                complaintBusiStatusGroupVO.setTotal(totalVO.getTotal());
            }
        });

        // 查询总数
        int sum = complaintBusiStatusGroupVOList.stream().mapToInt(ComplaintBusiStatusGroupVO::getTotal).sum();
        resultList.get(WeikbestConstant.ZERO_INT).setTotal(sum);
        return resultList;
    }

    @Override
    public WxOrderComplaintResp queryPageWithWx(OrderComplaintWxQO orderComplaintWxQO, PageReq pageReq) {
        WxPayService wxPayService = shopThirdService.findWxPayServiceById(orderComplaintWxQO.getShopId());

        WxOrderComplaintVO vo = new WxOrderComplaintVO();
        Integer totalUnprocessed = BasicConstant.INT_0; //未处理总数
        Integer totalNumberInProcess = BasicConstant.INT_0; //处理中总数
        Integer totalProcessed = BasicConstant.INT_0; //已处理总数
        List<WxOrderComplaintVO.WxComplaintVO> wxComplaintVOS = new ArrayList<>();
        // 查询
        Map<String, Object> paramMap = new HashMap<>(4);
        paramMap.put("beginDate", orderComplaintWxQO.getBeginDate());
        paramMap.put("endDate", orderComplaintWxQO.getEndDate());
        paramMap.put("offset", (pageReq.getPage() - 1)* pageReq.getLimit());
        paramMap.put("limit", pageReq.getLimit());
        //查询总投诉数量 统计 不分页
        Map<String, Object> stringObjectMap = ThirdConfigUtil.listComplaintsV2(wxPayService, paramMap);

//        Integer totalCount = MapUtil.getInt(stringObjectMap, "total_count");
        Integer totalCount = ThirdConfigUtil.getlistComplaintsV2TotalCount(wxPayService , paramMap);;


        //分页查询
//        Map<String, Object> originalData = ThirdConfigUtil.complaintsV2(wxPayService, paramMap);

        // 解析返回对象
        List<Map<String, Object>> dataList = MapUtil.get(stringObjectMap, "data", new TypeReference<List<Map<String, Object>>>() {});
        if (CollectionUtil.isNotEmpty(dataList)){
            for (Map<String, Object> data : dataList) {
                // 投诉单号
                String complaintId = MapUtil.getStr(data, "complaint_id");
                // 投诉时间
                String complaintTime = DateUtil.formatDateTime(DateUtil.parse(MapUtil.getStr(data, "complaint_time")));
                // 投诉详情
                String complaintDetail = MapUtil.getStr(data, "complaint_detail");
                // 投诉单状态
                String complaintState = MapUtil.getStr(data, "complaint_state");
                // 投诉单类型
                String problemType = MapUtil.getStr(data, "problem_type");

                List<Map<String, Object>> complaintOrderInfoList = MapUtil.get(data,"complaint_order_info",new TypeReference<List<Map<String, Object>>>() {});
                if (CollectionUtil.isNotEmpty(complaintOrderInfoList)) {
                    for (Map<String, Object> complaintOrderInfo : complaintOrderInfoList) {
                        String outTradeNo = MapUtil.getStr(complaintOrderInfo, "out_trade_no");
                        OrderPayRecord orderPayRecord = orderPayRecordService.findByOutTradeNo(MapUtil.getStr(complaintOrderInfo, "out_trade_no"));
                        if (ObjectUtil.isNull(orderPayRecord)){
                            continue;
                        }
                        Long orderId = orderPayRecord.getOrderId();
                        // 订单记录
                        OrderInfo orderInfo = orderInfoService.findById(orderId);
                        // 商品记录
                        OrderProdInfo orderProdInfo = orderProdInfoService.findById(orderId);
                        // 客户信息
                        OrderCustInfo orderCustInfo = orderCustInfoService.findById(orderId);
                        // 店铺信息
                        Long shopId = orderInfo.getShopId();
                        ShopThird shopThird = shopThirdService.findById(shopId);

                        // 添加字段
                        WxOrderComplaintVO.WxComplaintVO wxComplaintVO = new WxOrderComplaintVO.WxComplaintVO();
                        wxComplaintVO.setComplaintId(complaintId);
                        wxComplaintVO.setTpName(orderCustInfo.getTpName())
                                .setTpPhoto(orderCustInfo.getTpPhoto())
                                .setCustomerPhone(orderCustInfo.getCustomerPhone())
                                .setComplaintTime(complaintTime)
                                .setComplaintDetail(complaintDetail);
                        wxComplaintVO.setOrderId(orderId)
                                .setPayAmount(orderInfo.getPayAmount())
                                .setProdId(orderProdInfo.getProdId())
                                .setProdName(orderProdInfo.getProdName())
                                .setProdImg(orderProdInfo.getProdImg());
                        wxComplaintVO.setProblemType(problemType);
                        wxComplaintVO.setOutTradeNo(outTradeNo).setMchId(shopThird.getWxPayMchId());
                        wxComplaintVO.setComplaintState(complaintState);
                        wxComplaintVOS.add(wxComplaintVO);

                        if ("PENDING".equals(complaintState)) {
                            totalUnprocessed++;
                        } else if ("PROCESSING".equals(complaintState)) {
                            totalNumberInProcess++;
                        } else if ("PROCESSED".equals(complaintState)) {
                            totalProcessed++;
                        }
                    }
                }
            }
        }

        vo.setTotalUnprocessed(IntToMap("未处理" , totalUnprocessed));
        vo.setTotalNumberInProcess(IntToMap("处理中" , totalNumberInProcess));
        vo.setTotalProcessed(IntToMap("已处理" , totalProcessed));
        vo.setWxComplaintVOS(wxComplaintVOS);
        return WxOrderComplaintResp.ok(Long.valueOf(String.valueOf(totalCount)), vo, stringObjectMap);
    }

    @Override
    public OrderComplaintDetailVO findOrderComplaintDetailVOById(Long id) {
        OrderComplaint orderComplaint = this.findById(id);

        OrderComplaintDetailVO orderComplaintDetailVO = OrderComplaintMapStruct.INSTANCE.converToDetailVO(orderComplaint);

        // 查询售后单过期时间信息
        List<DelayTaskRecord> delayTaskRecordList = delayTaskRecordService.queryLikeDelayTask(String.valueOf(id));
        Date timeoutDate = DelayTaskRecordUtil.getMaxTimeoutDate(delayTaskRecordList);
        orderComplaintDetailVO.setTimeout(ObjectUtil.isNotNull(timeoutDate) ? timeoutDate : orderComplaint.getFinishTime());

        // 查询订单信息
        Long orderId = orderComplaint.getOrderId();
        OrderInfoVO orderInfoVO = orderInfoService.findVOById(orderId);
        orderComplaintDetailVO.setOrderInfoVO(orderInfoVO);

        // 查询商品信息
        OrderProdInfoVO orderProdInfoVO = orderProdInfoService.findVOById(orderId);
        orderComplaintDetailVO.setOrderProdInfoVO(orderProdInfoVO);

        // 查询订单客户信息
        OrderCustInfoVO orderCustInfoVO = orderCustInfoService.findVOById(orderId);
        orderComplaintDetailVO.setOrderCustInfoVO(orderCustInfoVO);

        // 查询订单支付记录信息
        OrderPayRecordVO orderPayRecordVO = orderPayRecordService.findVOByOrderId(orderId);
        orderComplaintDetailVO.setOrderPayRecordVO(orderPayRecordVO);

        // 设置处理记录
        List<OrderComplaintDetailRecordDetailVO> orderComplaintDetailRecordDetailVOList = orderComplaintRecordService.queryDetailVOByOrderComplaintId(id);
        orderComplaintDetailVO.setOrderComplaintDetailRecordDetailVOList(orderComplaintDetailRecordDetailVOList);

        QueryWrapper<OrderComplaintImg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(OrderComplaintImg.ID, id);
        queryWrapper.eq(OrderComplaintImg.IMG_TYPE, DictConstant.ComplaintImgType.type_1.getCode());
        List<OrderComplaintImg> orderComplainImgs = orderComplaintImgService.list(queryWrapper);
        List<String> imgList = new ArrayList<>();
        for (OrderComplaintImg orderComplainImg: orderComplainImgs) {
            imgList.add(orderComplainImg.getImgPath());
        }
        orderComplaintDetailVO.setImgList(imgList);

        ShopThird shopThird = shopThirdService.findById(orderInfoVO.getShopId());
        orderComplaintDetailVO.setWxBusiness(shopThird.getWxBusiness());

        return orderComplaintDetailVO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean businessExecuteComplaint(Long id, OrderComplaintBusinessConfirmDTO orderComplaintBusinessConfirmDTO) {
        OrderComplaint orderComplaint = this.findById(id);
        // 更新投诉记录
        orderComplaint.setComplaintBusiStatus(DictConstant.ComplaintBusiStatus.complaintBusiStatus_1.getCode());
        orderComplaint.setComplaintStatus(orderComplaintBusinessConfirmDTO.getComplaintStatus());
        orderComplaint.setComplaintContent(orderComplaintBusinessConfirmDTO.getComplaintContent());
        boolean update = this.updateById(orderComplaint);

        // 新增投诉沟通记录
        Long historyId = orderComplaintRecordService.insertByOrderComplaintRecord(orderComplaint);

        List<String> imgPathList = orderComplaintBusinessConfirmDTO.getImgPathList();
        if (CollectionUtil.isNotEmpty(imgPathList)) {
            // 新建售后商家再次发货凭证图片
            List<OrderComplaintImg> orderComplaintImgList = imgPathList.stream().map(imgPath -> {
                OrderComplaintImg orderComplaintImg = new OrderComplaintImg();
                orderComplaintImg.setImgPath(imgPath);
                orderComplaintImg.setImgType(DictConstant.ComplaintImgType.type_2.getCode());
                orderComplaintImg.setId(id);
                return orderComplaintImg;
            }).collect(Collectors.toList());
            orderComplaintImgService.saveBatch(orderComplaintImgList);

            // 新建售后商家再次发货凭证图片历史
            List<OrderComplaintImgHis> orderComplaintImgHisList = orderComplaintImgList.stream().map(orderComplaintImg -> {
                OrderComplaintImgHis orderComplaintImgHis = OrderComplaintImgHisMapStruct.INSTANCE.converToEntity(orderComplaintImg);
                orderComplaintImgHis.setHistoryId(historyId);
                return orderComplaintImgHis;
            }).collect(Collectors.toList());
            orderComplaintImgHisService.saveBatch(orderComplaintImgHisList);
        }

        // 删除商户处理延时队列
        orderComplaintBusinessExecuteTimeoutDelayTaskProcess.removeTask(orderComplaint.getId());

        return update;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void businessExecuteTimeout(Long id) {
        OrderComplaint orderComplaint = this.findById(id);

        if (StrUtil.equals(orderComplaint.getComplaintStatus(), DictConstant.ComplaintStatus.complaintStatus_1.getCode())) {
            // 投诉单仍然未处理，修改状态
            orderComplaint.setComplaintStatus(DictConstant.ComplaintStatus.complaintStatus_109.getCode());
            this.updateById(orderComplaint);

            // 新增投诉沟通记录
            orderComplaintRecordService.insertByOrderComplaintRecord(orderComplaint);
        }
        log.info("投诉单：{}，状态不是待处理，跳过商户超时处理。。。", orderComplaint);
    }

    public Map<String , Object> IntToMap(String writtenWords , Integer quantity){
        //设置参数
        Map<String , Object> map = new HashMap<>();
        map.put("writtenWords" , writtenWords);
        map.put("quantity" , quantity);
        return map;
    }


    public Map<String , Integer> getPageWithWxCount(OrderComplaintWxQO orderComplaintWxQO){
        Map<String , Integer> map = new HashMap<>();
        Integer totalUnprocessed = 0;
        WxPayService wxPayService = shopThirdService.findWxPayServiceById(orderComplaintWxQO.getShopId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        DateTime dateTime = DateUtil.offsetDay(date, -29);
        String beginDate = sdf.format(dateTime); //开始时间
        String endDate = sdf.format(date); //结束时间

        // 查询
        Map<String, Object> paramMap = new HashMap<>(4);
        paramMap.put("beginDate", beginDate);
        paramMap.put("endDate", endDate);
        paramMap.put("offset", 0);
        paramMap.put("limit", 50);
        //查询总投诉数量 统计 不分页
        Map<String, Object> stringObjectMap = ThirdConfigUtil.listComplaintsV2(wxPayService, paramMap);
        List<Map<String, Object>> dataList = MapUtil.get(stringObjectMap, "data", new TypeReference<List<Map<String, Object>>>() {});
        if (CollectionUtil.isNotEmpty(dataList)){
            for (Map<String, Object> data : dataList) {
                if ("PENDING".equals(MapUtil.getStr(data, "complaint_state"))) {
                    totalUnprocessed++;
                }
            }
        }
        map.put("totalUnprocessed", totalUnprocessed);
        return map;
    }

}
