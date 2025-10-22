package com.weikbest.pro.saas.applet.complaint.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.applet.complaint.module.dto.AppOrderComplaintDTO;
import com.weikbest.pro.saas.applet.complaint.module.mapstruct.AppOrderComplaintMapStruct;
import com.weikbest.pro.saas.applet.complaint.module.vo.AppOrderComplaintVO;
import com.weikbest.pro.saas.applet.complaint.service.AppOrderComplaintService;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.common.util.OrderUtil;
import com.weikbest.pro.saas.common.util.WeikbestObjectUtil;
import com.weikbest.pro.saas.merchat.complaint.delaytaskprocess.OrderComplaintBusinessExecuteTimeoutDelayTaskProcess;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaint;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaintImg;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaintImgHis;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaintRecord;
import com.weikbest.pro.saas.merchat.complaint.mapper.OrderComplaintMapper;
import com.weikbest.pro.saas.merchat.complaint.module.mapstruct.OrderComplaintImgHisMapStruct;
import com.weikbest.pro.saas.merchat.complaint.module.mapstruct.OrderComplaintRecordMapStruct;
import com.weikbest.pro.saas.merchat.complaint.service.OrderComplaintImgHisService;
import com.weikbest.pro.saas.merchat.complaint.service.OrderComplaintImgService;
import com.weikbest.pro.saas.merchat.complaint.service.OrderComplaintRecordService;
import com.weikbest.pro.saas.merchat.complaint.service.OrderComplaintService;
import com.weikbest.pro.saas.merchat.order.entity.OrderInfo;
import com.weikbest.pro.saas.merchat.order.service.OrderInfoService;
import com.weikbest.pro.saas.merchat.prod.entity.ProdTheme;
import com.weikbest.pro.saas.merchat.prod.service.ProdThemeService;
import com.weikbest.pro.saas.merchat.shop.entity.Shop;
import com.weikbest.pro.saas.merchat.shop.entity.ShopThird;
import com.weikbest.pro.saas.merchat.shop.service.ShopService;
import com.weikbest.pro.saas.merchat.shop.service.ShopThirdService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.common.service.CurrentUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单投诉表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-29
 */
@Slf4j
@Service
public class AppOrderComplaintServiceImpl extends ServiceImpl<OrderComplaintMapper, OrderComplaint> implements AppOrderComplaintService {


    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private OrderComplaintService orderComplaintService;

    @Resource
    private OrderComplaintImgService orderComplaintImgService;

    @Resource
    private ShopThirdService shopThirdService;

    @Resource
    private ProdThemeService prodThemeService;

    @Resource
    private ShopService shopService;

    @Resource
    private CurrentUserService currentUserService;

    @Resource
    private OrderComplaintRecordService orderComplaintRecordService;

    @Resource
    private OrderComplaintImgHisService orderComplaintImgHisService;

    @Resource
    private OrderComplaintBusinessExecuteTimeoutDelayTaskProcess orderComplaintBusinessExecuteTimeoutDelayTaskProcess;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(AppOrderComplaintDTO appOrderComplaintDTO) {

        OrderComplaint orderComplaint = AppOrderComplaintMapStruct.INSTANCE.converToEntity(appOrderComplaintDTO);

        if(WeikbestObjectUtil.isEmpty(appOrderComplaintDTO.getOrderId())){
            throw new WeikbestException("当前订单不存在！");
        }

        OrderInfo orderInfo = orderInfoService.findById(appOrderComplaintDTO.getOrderId());
        ProdTheme prodTheme = prodThemeService.findById(orderInfo.getProdId());
        Shop shop = shopService.findById(orderInfo.getShopId());

        Long id = GenerateIDUtil.nextId();
        String number = OrderUtil.getOrderComplaintNumber();
        orderComplaint.setId(id);
        orderComplaint.setNumber(number);
        orderComplaint.setOrderAfterSaleId(orderInfo.getOrderAfterSaleId());
        ShopThird shopThird = shopThirdService.findById(orderInfo.getShopId());
        orderComplaint.setApplyTime(DateUtil.date());
        orderComplaint.setMchId(shopThird.getWxPayMchId());
        orderComplaint.setProdId(orderInfo.getProdId());
        orderComplaint.setCustomerId(orderInfo.getCustomerId());
        orderComplaint.setComplaintBusiStatus(DictConstant.ComplaintBusiStatus.complaintBusiStatus_0.getCode());
        orderComplaint.setComplaintStatus(DictConstant.ComplaintStatus.complaintStatus_1.getCode());
        orderComplaint.setShowImg(prodTheme.getShowImg());
        orderComplaint.setComplaintObject(shop.getName());

        // 新建售后协商历史记录表
        OrderComplaintRecord complaintRecord = OrderComplaintRecordMapStruct.INSTANCE.converToEntity(orderComplaint);
        // 设置售后协商历史ID
        Long historyId = GenerateIDUtil.nextId();
        complaintRecord.setHistoryId(historyId);
        // 设置变更人相关字段
        complaintRecord.setChangerUserType(currentUserService.getAppTokenUser().getRelateType());
        complaintRecord.setChangerUserId(currentUserService.getAppTokenUser().getId());
        complaintRecord.setChangeTime(DateUtil.date());
        orderComplaintRecordService.save(complaintRecord);

        List<String> complaintImgs = appOrderComplaintDTO.getComplaintImgs();
        if(complaintImgs != null && complaintImgs.size()>0 ){
            for (String imgurl: complaintImgs) {
                OrderComplaintImg orderComplaintImg = new OrderComplaintImg();
                Long imgId = GenerateIDUtil.nextId();
                orderComplaintImg.setEntryId(imgId);
                orderComplaintImg.setId(id);
                orderComplaintImg.setImgPath(imgurl);
                orderComplaintImgService.save(orderComplaintImg);

                // 新建售后商家再次发货凭证图片历史
                OrderComplaintImgHis orderComplaintImgHis = OrderComplaintImgHisMapStruct.INSTANCE.converToEntity(orderComplaintImg);
                orderComplaintImgHis.setHistoryId(historyId);
                orderComplaintImgHisService.save(orderComplaintImgHis);
            }
        }

        // 添加商户处理延时队列
        orderComplaintBusinessExecuteTimeoutDelayTaskProcess.putTask(id);
        return orderComplaintService.save(orderComplaint);
    }


    @Override
    public boolean userUndo(Long orderComplaintId){

        OrderComplaint orderComplaint = orderComplaintService.findById(orderComplaintId);
        if(ObjectUtil.isNull(orderComplaint)){
            throw new WeikbestException("当前投诉单不存在！");
        }
        orderComplaint.setComplaintStatus(DictConstant.ComplaintStatus.complaintStatus_209.getCode());
        orderComplaint.setComplaintBusiStatus(DictConstant.ComplaintBusiStatus.complaintBusiStatus_2.getCode());
        orderComplaint.setFinishTime(DateUtil.date());
        orderComplaint.setCustOperateTime(DateUtil.date());

        // 新建售后协商历史记录表
        OrderComplaintRecord complaintRecord = OrderComplaintRecordMapStruct.INSTANCE.converToEntity(orderComplaint);
        // 设置售后协商历史ID
        Long historyId = GenerateIDUtil.nextId();
        complaintRecord.setHistoryId(historyId);
        // 设置变更人相关字段
        complaintRecord.setChangerUserType(currentUserService.getAppTokenUser().getRelateType());
        complaintRecord.setChangerUserId(currentUserService.getAppTokenUser().getId());
        complaintRecord.setChangeTime(DateUtil.date());
        orderComplaintRecordService.save(complaintRecord);

        // 删除商户处理延时队列
        orderComplaintBusinessExecuteTimeoutDelayTaskProcess.removeTask(orderComplaint.getId());

        return orderComplaintService.updateById(orderComplaint);
    }

    @Override
    public boolean userDo(Long orderComplaintId,String complaintCustStatus){

        OrderComplaint orderComplaint = orderComplaintService.findById(orderComplaintId);
        if(ObjectUtil.isNull(orderComplaint)){
            throw new WeikbestException("当前投诉单不存在！");
        }

        orderComplaint.setComplaintCustStatus(complaintCustStatus);
        if("0".equals(complaintCustStatus)) {
            orderComplaint.setComplaintStatus(DictConstant.ComplaintStatus.complaintStatus_201.getCode());
        }else{
            orderComplaint.setComplaintStatus(DictConstant.ComplaintStatus.complaintStatus_200.getCode());
            orderComplaint.setComplaintBusiStatus(DictConstant.ComplaintBusiStatus.complaintBusiStatus_2.getCode());
        }
        orderComplaint.setCustOperateTime(DateUtil.date());

        // 新建售后协商历史记录表
        OrderComplaintRecord complaintRecord = OrderComplaintRecordMapStruct.INSTANCE.converToEntity(orderComplaint);
        // 设置售后协商历史ID
        Long historyId = GenerateIDUtil.nextId();
        complaintRecord.setHistoryId(historyId);
        // 设置变更人相关字段
        complaintRecord.setChangerUserType(currentUserService.getAppTokenUser().getRelateType());
        complaintRecord.setChangerUserId(currentUserService.getAppTokenUser().getId());
        complaintRecord.setChangeTime(DateUtil.date());
        orderComplaintRecordService.save(complaintRecord);

        Boolean bool = orderComplaintService.updateById(orderComplaint);

        return bool;
    }

    @Override
    public List<AppOrderComplaintVO> queryListPage(PageReq pageReq){

        Long customerId = currentUserService.getAppTokenUser().getId();

        QueryWrapper<OrderComplaint> wrapper = new QueryWrapper<>();
        wrapper.eq(OrderComplaint.CUSTOMER_ID,customerId);
        wrapper.orderByDesc(OrderComplaint.APPLY_TIME);
        IPage<OrderComplaint> iPage = orderComplaintService.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
        List<AppOrderComplaintVO> list = new ArrayList<>();
        List<OrderComplaint> orderComplaints = iPage.getRecords();
        for (OrderComplaint orderComplaint: orderComplaints) {
            AppOrderComplaintVO vo = AppOrderComplaintMapStruct.INSTANCE.converToVO(orderComplaint);
            list.add(vo);
        }
        return list;
    }
}
