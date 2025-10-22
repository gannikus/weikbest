package com.weikbest.pro.saas.merchat.complaint.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.ext.user.UserExtServiceFactory;
import com.weikbest.pro.saas.common.ext.user.entity.UserExt;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSaleConsultRecord;
import com.weikbest.pro.saas.merchat.aftersale.module.mapstruct.OrderAfterSaleConsultRecordMapStruct;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleConsultRecordDetailVO;
import com.weikbest.pro.saas.merchat.aftersale.service.impl.OrderAfterSaleImgHisServiceImpl;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaint;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaintRecord;
import com.weikbest.pro.saas.merchat.complaint.mapper.OrderComplaintRecordMapper;
import com.weikbest.pro.saas.merchat.complaint.module.dto.OrderComplaintRecordDTO;
import com.weikbest.pro.saas.merchat.complaint.module.mapstruct.OrderComplaintRecordMapStruct;
import com.weikbest.pro.saas.merchat.complaint.module.qo.OrderComplaintRecordQO;
import com.weikbest.pro.saas.merchat.complaint.module.vo.OrderComplaintDetailRecordDetailVO;
import com.weikbest.pro.saas.merchat.complaint.service.OrderComplaintImgHisService;
import com.weikbest.pro.saas.merchat.complaint.service.OrderComplaintRecordService;
import com.weikbest.pro.saas.sys.common.cache.Memory;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.common.service.CurrentUserService;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订单投诉处理记录表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-05
 */
@Service
public class OrderComplaintRecordServiceImpl extends ServiceImpl<OrderComplaintRecordMapper, OrderComplaintRecord> implements OrderComplaintRecordService {

    @Resource
    private CurrentUserService currentUserService;

    @Resource
    private UserExtServiceFactory userExtServiceFactory;

    @Resource
    private OrderComplaintImgHisService orderComplaintImgHisService;

    @Override
    public boolean insert(OrderComplaintRecordDTO orderComplaintRecordDTO) {
        OrderComplaintRecord orderComplaintRecord = OrderComplaintRecordMapStruct.INSTANCE.converToEntity(orderComplaintRecordDTO);
        return this.save(orderComplaintRecord);
    }

    @Override
    public boolean updateById(Long id, OrderComplaintRecordDTO orderComplaintRecordDTO) {
        OrderComplaintRecord orderComplaintRecord = this.findById(id);
        OrderComplaintRecordMapStruct.INSTANCE.copyProperties(orderComplaintRecordDTO, orderComplaintRecord);
        orderComplaintRecord.setId(id);
        return this.updateById(orderComplaintRecord);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public OrderComplaintRecord findById(Long id) {
        OrderComplaintRecord orderComplaintRecord = this.getById(id);
        if (ObjectUtil.isNull(orderComplaintRecord)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderComplaintRecord;
    }

    @Override
    public IPage<OrderComplaintRecord> queryPage(OrderComplaintRecordQO orderComplaintRecordQO, PageReq pageReq) {
        QueryWrapper<OrderComplaintRecord> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(orderComplaintRecordQO.getNumber())) {
            wrapper.eq(OrderComplaintRecord.NUMBER, orderComplaintRecordQO.getNumber());
        }
        if (StrUtil.isNotBlank(orderComplaintRecordQO.getAppId())) {
            wrapper.eq(OrderComplaintRecord.APP_ID, orderComplaintRecordQO.getAppId());
        }
        if (StrUtil.isNotBlank(orderComplaintRecordQO.getMchId())) {
            wrapper.eq(OrderComplaintRecord.MCH_ID, orderComplaintRecordQO.getMchId());
        }
        if (StrUtil.isNotBlank(orderComplaintRecordQO.getOrderAppletType())) {
            wrapper.eq(OrderComplaintRecord.ORDER_APPLET_TYPE, orderComplaintRecordQO.getOrderAppletType());
        }
        if (StrUtil.isNotBlank(orderComplaintRecordQO.getComplaintType())) {
            wrapper.eq(OrderComplaintRecord.COMPLAINT_TYPE, orderComplaintRecordQO.getComplaintType());
        }
        if (StrUtil.isNotBlank(orderComplaintRecordQO.getComplaintReason())) {
            wrapper.eq(OrderComplaintRecord.COMPLAINT_REASON, orderComplaintRecordQO.getComplaintReason());
        }
        if (StrUtil.isNotBlank(orderComplaintRecordQO.getComplaintBusiStatus())) {
            wrapper.eq(OrderComplaintRecord.COMPLAINT_BUSI_STATUS, orderComplaintRecordQO.getComplaintBusiStatus());
        }
        if (StrUtil.isNotBlank(orderComplaintRecordQO.getComplaintCustStatus())) {
            wrapper.eq(OrderComplaintRecord.COMPLAINT_CUST_STATUS, orderComplaintRecordQO.getComplaintCustStatus());
        }
        if (StrUtil.isNotBlank(orderComplaintRecordQO.getComplaintPlatformStatus())) {
            wrapper.eq(OrderComplaintRecord.COMPLAINT_PLATFORM_STATUS, orderComplaintRecordQO.getComplaintPlatformStatus());
        }
        if (StrUtil.isNotBlank(orderComplaintRecordQO.getComplaintStatus())) {
            wrapper.eq(OrderComplaintRecord.COMPLAINT_STATUS, orderComplaintRecordQO.getComplaintStatus());
        }
        if (StrUtil.isNotBlank(orderComplaintRecordQO.getCustomerPhone())) {
            wrapper.eq(OrderComplaintRecord.CUSTOMER_PHONE, orderComplaintRecordQO.getCustomerPhone());
        }
        if (StrUtil.isNotBlank(orderComplaintRecordQO.getFeedbackReason())) {
            wrapper.eq(OrderComplaintRecord.FEEDBACK_REASON, orderComplaintRecordQO.getFeedbackReason());
        }
        if (StrUtil.isNotBlank(orderComplaintRecordQO.getComplaintContent())) {
            wrapper.eq(OrderComplaintRecord.COMPLAINT_CONTENT, orderComplaintRecordQO.getComplaintContent());
        }
        if (StrUtil.isNotBlank(orderComplaintRecordQO.getComplaintObject())) {
            wrapper.eq(OrderComplaintRecord.COMPLAINT_OBJECT, orderComplaintRecordQO.getComplaintObject());
        }
        if (StrUtil.isNotBlank(orderComplaintRecordQO.getShowImg())) {
            wrapper.eq(OrderComplaintRecord.SHOW_IMG, orderComplaintRecordQO.getShowImg());
        }
        if (StrUtil.isNotBlank(orderComplaintRecordQO.getChangerUserType())) {
            wrapper.eq(OrderComplaintRecord.CHANGER_USER_TYPE, orderComplaintRecordQO.getChangerUserType());
        }
        if (StrUtil.isNotBlank(orderComplaintRecordQO.getDescription())) {
            wrapper.eq(OrderComplaintRecord.DESCRIPTION, orderComplaintRecordQO.getDescription());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long insertByOrderComplaintRecord(OrderComplaint orderComplaint) {
        // 新建售后协商历史记录表
        OrderComplaintRecord complaintRecord = OrderComplaintRecordMapStruct.INSTANCE.converToEntity(orderComplaint);
        // 设置售后协商历史ID
        Long historyId = GenerateIDUtil.nextId();
        complaintRecord.setHistoryId(historyId);
        // 设置变更人相关字段
        complaintRecord.setChangerUserType(currentUserService.getTokenUser().getRelateType());
        complaintRecord.setChangerUserId(currentUserService.getTokenUser().getId());
        complaintRecord.setChangeTime(DateUtil.date());
        this.save(complaintRecord);
        return historyId;
    }

    @Override
    public List<OrderComplaintDetailRecordDetailVO> queryDetailVOByOrderComplaintId(Long orderComplaintId) {
        List<OrderComplaintRecord> orderComplaintRecordList = this.list(new QueryWrapper<OrderComplaintRecord>().eq(OrderComplaintRecord.ID, orderComplaintId).orderByDesc(OrderComplaintRecord.HISTORY_ID));

        // 构建协商记录
        List<OrderComplaintDetailRecordDetailVO> orderComplaintDetailRecordDetailVOList = new ArrayList<>();
        for (OrderComplaintRecord orderComplaintRecord : orderComplaintRecordList) {
            // 转换
            OrderComplaintDetailRecordDetailVO orderComplaintDetailRecordDetailVO = OrderComplaintRecordMapStruct.INSTANCE.converToDetailVO(orderComplaintRecord);

            // 1.获取变更人信息
            String changerUserType = orderComplaintRecord.getChangerUserType();
            UserExt user = userExtServiceFactory.getUserExtService(changerUserType).getUser(orderComplaintRecord.getChangerUserId());
            orderComplaintDetailRecordDetailVO.setAvatar(user.getAvatar());
            orderComplaintDetailRecordDetailVO.setName(user.getName());

            // 2.设置协商标题
            String complaintStatus = orderComplaintRecord.getComplaintStatus();
            String title = Memory.getDict(DictConstant.ComplaintStatus.getDictTypeNumber(), complaintStatus);
            orderComplaintDetailRecordDetailVO.setTitle(title);

            // 3. 设置详细字段
            List<OrderComplaintDetailRecordDetailVO.DetailVO> detailVOList = buildDetailVO(orderComplaintRecord);
            orderComplaintDetailRecordDetailVO.setDetailVOList(detailVOList);

            orderComplaintDetailRecordDetailVOList.add(orderComplaintDetailRecordDetailVO);
        }

        return orderComplaintDetailRecordDetailVOList;
    }

    /**
     * 设置投诉处理记录详细
     *
     * @param orderComplaintRecord
     * @return
     */
    private List<OrderComplaintDetailRecordDetailVO.DetailVO> buildDetailVO(OrderComplaintRecord orderComplaintRecord) {
        List<OrderComplaintDetailRecordDetailVO.DetailVO> detailVOList = new ArrayList<>();
        // 这里要根据具体的关键节点来变换
        String complaintStatus = orderComplaintRecord.getComplaintStatus();

        // 发起投诉
        if (StrUtil.equals(complaintStatus, DictConstant.ComplaintStatus.complaintStatus_1.getCode())) {
            // 反馈原因
            detailVOList.add(OrderComplaintDetailRecordDetailVO.buildDetailVO("反馈原因", orderComplaintRecord.getFeedbackReason()));
            // 联系电话
            detailVOList.add(OrderComplaintDetailRecordDetailVO.buildDetailVO("联系电话", orderComplaintRecord.getCustomerPhone()));
            // 沟通内容
            detailVOList.add(OrderComplaintDetailRecordDetailVO.buildDetailVO("沟通内容", orderComplaintRecord.getComplaintContent()));
        }
        // 商家同意/不同意和解/处理超时
        if (StrUtil.equals(complaintStatus, DictConstant.ComplaintStatus.complaintStatus_100.getCode())
                || StrUtil.equals(complaintStatus, DictConstant.ComplaintStatus.complaintStatus_101.getCode())
                || StrUtil.equals(complaintStatus, DictConstant.ComplaintStatus.complaintStatus_109.getCode())) {
            // 沟通内容
            detailVOList.add(OrderComplaintDetailRecordDetailVO.buildDetailVO("沟通内容", orderComplaintRecord.getComplaintContent()));
        }
        // 用户认可/不认可和解/撤销和解
        if (StrUtil.equals(complaintStatus, DictConstant.ComplaintStatus.complaintStatus_200.getCode())
                || StrUtil.equals(complaintStatus, DictConstant.ComplaintStatus.complaintStatus_201.getCode())
                || StrUtil.equals(complaintStatus, DictConstant.ComplaintStatus.complaintStatus_209.getCode())) {
            // 沟通内容
            detailVOList.add(OrderComplaintDetailRecordDetailVO.buildDetailVO("沟通内容", orderComplaintRecord.getComplaintContent()));
        }
        // 查询凭证信息
        List<String> orderComplaintImgHisList = orderComplaintImgHisService.queryImgByHistoryIdAndImgType(orderComplaintRecord.getHistoryId(), DictConstant.ComplaintImgType.type_2.getCode());
        if(CollectionUtil.isNotEmpty(orderComplaintImgHisList)) {
            detailVOList.add(OrderComplaintDetailRecordDetailVO.buildDetailVO("上传凭证", StrUtil.join(",", orderComplaintImgHisList.toArray()), DictConstant.DetailFieldType.link.getCode()));
        }
        return detailVOList;
    }
}
