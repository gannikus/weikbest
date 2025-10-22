package com.weikbest.pro.saas.merchat.aftersale.service.impl;

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
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSale;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSaleConsultRecord;
import com.weikbest.pro.saas.merchat.aftersale.mapper.OrderAfterSaleConsultRecordMapper;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleConsultRecordDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.mapstruct.OrderAfterSaleConsultRecordMapStruct;
import com.weikbest.pro.saas.merchat.aftersale.module.qo.OrderAfterSaleConsultRecordQO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleConsultRecordDetailVO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleConsultRecordVO;
import com.weikbest.pro.saas.merchat.aftersale.service.OrderAfterSaleConsultRecordService;
import com.weikbest.pro.saas.merchat.aftersale.service.OrderAfterSaleImgHisService;
import com.weikbest.pro.saas.merchat.aftersale.service.OrderAfterSaleLogisticsImgHisService;
import com.weikbest.pro.saas.merchat.aftersale.util.OrderAfterSaleUtil;
import com.weikbest.pro.saas.sys.common.cache.Memory;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.common.service.CurrentUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单售后协商历史记录表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Service
public class OrderAfterSaleConsultRecordServiceImpl extends ServiceImpl<OrderAfterSaleConsultRecordMapper, OrderAfterSaleConsultRecord> implements OrderAfterSaleConsultRecordService {

    @Resource
    private CurrentUserService currentUserService;

    @Resource
    private UserExtServiceFactory userExtServiceFactory;

    @Resource
    private OrderAfterSaleImgHisService orderAfterSaleImgHisService;

    @Resource
    private OrderAfterSaleLogisticsImgHisService orderAfterSaleLogisticsImgHisService;

    @Override
    public boolean insert(OrderAfterSaleConsultRecordDTO orderAfterSaleConsultRecordDTO) {
        OrderAfterSaleConsultRecord orderAfterSaleConsultRecord = OrderAfterSaleConsultRecordMapStruct.INSTANCE.converToEntity(orderAfterSaleConsultRecordDTO);
        return this.save(orderAfterSaleConsultRecord);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long insertByOrderAfterSale(OrderAfterSale orderAfterSale, String changeType) {
        // 新建售后协商历史记录表
        OrderAfterSaleConsultRecord orderAfterSaleConsultRecord = OrderAfterSaleConsultRecordMapStruct.INSTANCE.converToEntity(orderAfterSale);
        // 设置售后协商历史ID
        Long historyId = GenerateIDUtil.nextId();
        orderAfterSaleConsultRecord.setHistoryId(historyId);
        // 设置变更人相关字段
        orderAfterSaleConsultRecord.setChangeType(changeType);
        orderAfterSaleConsultRecord.setChangerUserType(currentUserService.getTokenUser().getRelateType());
        orderAfterSaleConsultRecord.setChangerUserId(currentUserService.getTokenUser().getId());
        orderAfterSaleConsultRecord.setChangeTime(DateUtil.date());
        this.save(orderAfterSaleConsultRecord);
        return historyId;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long appinsertByOrderAfterSale(OrderAfterSale orderAfterSale, String changeType) {
        // 新建售后协商历史记录表
        OrderAfterSaleConsultRecord orderAfterSaleConsultRecord = OrderAfterSaleConsultRecordMapStruct.INSTANCE.converToEntity(orderAfterSale);
        // 设置售后协商历史ID
        Long historyId = GenerateIDUtil.nextId();
        orderAfterSaleConsultRecord.setHistoryId(historyId);
        // 设置变更人相关字段
        orderAfterSaleConsultRecord.setChangeType(changeType);
        orderAfterSaleConsultRecord.setChangerUserType(currentUserService.getAppTokenUser().getRelateType());
        orderAfterSaleConsultRecord.setChangerUserId(currentUserService.getAppTokenUser().getId());
        orderAfterSaleConsultRecord.setChangeTime(DateUtil.date());
        this.save(orderAfterSaleConsultRecord);
        return historyId;
    }

    @Override
    public boolean updateById(Long id, OrderAfterSaleConsultRecordDTO orderAfterSaleConsultRecordDTO) {
        OrderAfterSaleConsultRecord orderAfterSaleConsultRecord = this.findById(id);
        OrderAfterSaleConsultRecordMapStruct.INSTANCE.copyProperties(orderAfterSaleConsultRecordDTO, orderAfterSaleConsultRecord);
        orderAfterSaleConsultRecord.setId(id);
        return this.updateById(orderAfterSaleConsultRecord);
    }

    @Override
    public OrderAfterSaleConsultRecord findById(Long id) {
        OrderAfterSaleConsultRecord orderAfterSaleConsultRecord = this.getById(id);
        if (ObjectUtil.isNull(orderAfterSaleConsultRecord)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderAfterSaleConsultRecord;
    }

    @Override
    public IPage<OrderAfterSaleConsultRecord> queryPage(OrderAfterSaleConsultRecordQO orderAfterSaleConsultRecordQO, PageReq pageReq) {
        QueryWrapper<OrderAfterSaleConsultRecord> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(orderAfterSaleConsultRecordQO.getTakeDeliveryType())) {
            wrapper.eq(OrderAfterSaleConsultRecord.TAKE_DELIVERY_TYPE, orderAfterSaleConsultRecordQO.getTakeDeliveryType());
        }
        if (StrUtil.isNotBlank(orderAfterSaleConsultRecordQO.getApplyType())) {
            wrapper.eq(OrderAfterSaleConsultRecord.APPLY_TYPE, orderAfterSaleConsultRecordQO.getApplyType());
        }
        if (StrUtil.isNotBlank(orderAfterSaleConsultRecordQO.getApplyReason())) {
            wrapper.eq(OrderAfterSaleConsultRecord.APPLY_REASON, orderAfterSaleConsultRecordQO.getApplyReason());
        }
        if (StrUtil.isNotBlank(orderAfterSaleConsultRecordQO.getCustomerPhone())) {
            wrapper.eq(OrderAfterSaleConsultRecord.CUSTOMER_PHONE, orderAfterSaleConsultRecordQO.getCustomerPhone());
        }
        if (StrUtil.isNotBlank(orderAfterSaleConsultRecordQO.getQuestionDetail())) {
            wrapper.eq(OrderAfterSaleConsultRecord.QUESTION_DETAIL, orderAfterSaleConsultRecordQO.getQuestionDetail());
        }
        if (StrUtil.isNotBlank(orderAfterSaleConsultRecordQO.getIsFastSale())) {
            wrapper.eq(OrderAfterSaleConsultRecord.IS_FAST_SALE, orderAfterSaleConsultRecordQO.getIsFastSale());
        }
        if (StrUtil.isNotBlank(orderAfterSaleConsultRecordQO.getAfterSaleKey())) {
            wrapper.eq(OrderAfterSaleConsultRecord.AFTER_SALE_KEY, orderAfterSaleConsultRecordQO.getAfterSaleKey());
        }
        if (StrUtil.isNotBlank(orderAfterSaleConsultRecordQO.getAfterSaleStatus())) {
            wrapper.eq(OrderAfterSaleConsultRecord.AFTER_SALE_STATUS, orderAfterSaleConsultRecordQO.getAfterSaleStatus());
        }
        if (StrUtil.isNotBlank(orderAfterSaleConsultRecordQO.getBackAddress())) {
            wrapper.eq(OrderAfterSaleConsultRecord.BACK_ADDRESS, orderAfterSaleConsultRecordQO.getBackAddress());
        }
        if (StrUtil.isNotBlank(orderAfterSaleConsultRecordQO.getBackLogisticsCompany())) {
            wrapper.eq(OrderAfterSaleConsultRecord.BACK_LOGISTICS_COMPANY, orderAfterSaleConsultRecordQO.getBackLogisticsCompany());
        }
        if (StrUtil.isNotBlank(orderAfterSaleConsultRecordQO.getBackCourierNumber())) {
            wrapper.eq(OrderAfterSaleConsultRecord.BACK_COURIER_NUMBER, orderAfterSaleConsultRecordQO.getBackCourierNumber());
        }
        if (StrUtil.isNotBlank(orderAfterSaleConsultRecordQO.getSendLogisticsCompany())) {
            wrapper.eq(OrderAfterSaleConsultRecord.SEND_LOGISTICS_COMPANY, orderAfterSaleConsultRecordQO.getSendLogisticsCompany());
        }
        if (StrUtil.isNotBlank(orderAfterSaleConsultRecordQO.getSendCourierNumber())) {
            wrapper.eq(OrderAfterSaleConsultRecord.SEND_COURIER_NUMBER, orderAfterSaleConsultRecordQO.getSendCourierNumber());
        }
        if (StrUtil.isNotBlank(orderAfterSaleConsultRecordQO.getChangeType())) {
            wrapper.eq(OrderAfterSaleConsultRecord.CHANGE_TYPE, orderAfterSaleConsultRecordQO.getChangeType());
        }
        if (StrUtil.isNotBlank(orderAfterSaleConsultRecordQO.getChangerUserType())) {
            wrapper.eq(OrderAfterSaleConsultRecord.CHANGER_USER_TYPE, orderAfterSaleConsultRecordQO.getChangerUserType());
        }
        if (StrUtil.isNotBlank(orderAfterSaleConsultRecordQO.getDescription())) {
            wrapper.eq(OrderAfterSaleConsultRecord.DESCRIPTION, orderAfterSaleConsultRecordQO.getDescription());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public List<OrderAfterSaleConsultRecordVO> queryVOByOrderAfterSaleId(Long orderAfterSaleId) {
        List<OrderAfterSaleConsultRecord> orderAfterSaleConsultRecordList = this.list(new QueryWrapper<OrderAfterSaleConsultRecord>().eq(OrderAfterSaleConsultRecord.ID, orderAfterSaleId));
        return orderAfterSaleConsultRecordList.stream().map(OrderAfterSaleConsultRecordMapStruct.INSTANCE::converToVO).collect(Collectors.toList());
    }


    @Override
    public List<OrderAfterSaleConsultRecordDetailVO> queryDetailVOByOrderAfterSaleId(Long orderAfterSaleId) {
        List<OrderAfterSaleConsultRecord> orderAfterSaleConsultRecordList = this.list(new QueryWrapper<OrderAfterSaleConsultRecord>().eq(OrderAfterSaleConsultRecord.ID, orderAfterSaleId).orderByDesc(OrderAfterSaleConsultRecord.HISTORY_ID));

        // 构建协商记录
        List<OrderAfterSaleConsultRecordDetailVO> orderAfterSaleConsultRecordDetailVOList = new ArrayList<>();
        for (OrderAfterSaleConsultRecord orderAfterSaleConsultRecord : orderAfterSaleConsultRecordList) {
            // 转换
            OrderAfterSaleConsultRecordDetailVO orderAfterSaleConsultRecordDetailVO = OrderAfterSaleConsultRecordMapStruct.INSTANCE.converToDetailVO(orderAfterSaleConsultRecord);

            // 1.获取变更人信息
            String changerUserType = orderAfterSaleConsultRecord.getChangerUserType();
            UserExt user = userExtServiceFactory.getUserExtService(changerUserType).getUser(orderAfterSaleConsultRecord.getChangerUserId());
            orderAfterSaleConsultRecordDetailVO.setAvatar(user.getAvatar());
            orderAfterSaleConsultRecordDetailVO.setName(user.getName());

            // 2.设置协商标题
            String afterSaleStatus = orderAfterSaleConsultRecord.getAfterSaleStatus();
            String title = Memory.getDict(DictConstant.AfterSaleStatus.getDictTypeNumber(), afterSaleStatus);
            orderAfterSaleConsultRecordDetailVO.setTitle(title);

            // 3. 设置详细字段
            List<OrderAfterSaleConsultRecordDetailVO.DetailVO> detailVOList = buildDetailVO(orderAfterSaleConsultRecord);
            orderAfterSaleConsultRecordDetailVO.setDetailVOList(detailVOList);

            orderAfterSaleConsultRecordDetailVOList.add(orderAfterSaleConsultRecordDetailVO);
        }

        return orderAfterSaleConsultRecordDetailVOList;
    }

    /**
     * 设置协商记录详细
     *
     * @param orderAfterSaleConsultRecord
     * @return
     */
    private List<OrderAfterSaleConsultRecordDetailVO.DetailVO> buildDetailVO(OrderAfterSaleConsultRecord orderAfterSaleConsultRecord) {
        List<OrderAfterSaleConsultRecordDetailVO.DetailVO> detailVOList = new ArrayList<>();
        // 这里要根据具体的关键节点来变换
        String afterSaleKey = orderAfterSaleConsultRecord.getAfterSaleKey();
        String afterSaleStatus = orderAfterSaleConsultRecord.getAfterSaleStatus();
        String applyType = orderAfterSaleConsultRecord.getApplyType();
        String applyTypeText = Memory.getDict(DictConstant.AfterSaleApplyType.getDictTypeNumber(), applyType);

        // 待处理
        if (DictConstant.AfterSaleKey.isPending(afterSaleKey)) {
            // 商家不同意申请
            if (StrUtil.equals(afterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_10.getCode())) {
                // 拒绝原因
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("拒绝原因", Memory.getDict(DictConstant.RejectReason.getDictTypeNumber(), orderAfterSaleConsultRecord.getRejectReason())));
                // 拒绝说明
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("拒绝说明", orderAfterSaleConsultRecord.getRejectDetail()));
            }
            // 商家已重新发货，待客户确认
            else if (StrUtil.equals(afterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_304.getCode())) {
                // 显示：快递公司，快递单号，上传凭证
                // 快递公司
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("快递公司", Memory.getLogisticsCompanyName(orderAfterSaleConsultRecord.getSendLogisticsCompany())));
                // 快递单号
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("快递单号", orderAfterSaleConsultRecord.getSendCourierNumber()));
                // 备注
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("备注", orderAfterSaleConsultRecord.getDescription()));
                // 上传凭证
                // 查询凭证信息
                List<String> orderAfterSaleLogisticsImgHisList = orderAfterSaleLogisticsImgHisService.queryImgByHistoryIdAndCourierImgType(orderAfterSaleConsultRecord.getHistoryId(), DictConstant.CourierImgType.courierImgType_3.getCode());
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("上传凭证", StrUtil.join(",", orderAfterSaleLogisticsImgHisList.toArray()), DictConstant.DetailFieldType.link.getCode()));
            }
            // 其他情况
            else {
                // 显示：售后类型，[退款金额]，%s原因，%s数量，联系电话，申请说明，上传凭证
                // 售后类型
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("售后类型", applyTypeText));
                if (!StrUtil.equals(applyType, DictConstant.AfterSaleApplyType.exchange.getCode())) {
                    // 不等于换货，显示退款金额
                    detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("退款金额", OrderAfterSaleUtil.showAmount(orderAfterSaleConsultRecord.getApplyAmount())));
                }
                // %s原因
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO(String.format("%s原因", applyTypeText), Memory.getDict(DictConstant.AfterSaleApplyReason.getDictTypeNumber(), orderAfterSaleConsultRecord.getApplyReason())));
                if (!StrUtil.equals(applyType, DictConstant.AfterSaleApplyType.exchange.getCode())) {
                    // 不等于换货，%s数量 = 退货数量
                    detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("退货数量", orderAfterSaleConsultRecord.getGoodsNum().toString()));
                } else {
                    // 等于换货，%s数量 = 换货数量
                    detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("换货数量", orderAfterSaleConsultRecord.getGoodsNum().toString()));
                }

                // 联系电话
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("联系电话", orderAfterSaleConsultRecord.getCustomerPhone()));
                // 申请说明
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("申请说明", orderAfterSaleConsultRecord.getQuestionDetail()));
                // 上传凭证
                // 查询凭证信息
                List<String> orderAfterSaleImgHisList = orderAfterSaleImgHisService.queryImgByHistoryId(orderAfterSaleConsultRecord.getHistoryId());
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("上传凭证", StrUtil.join(",", orderAfterSaleImgHisList.toArray()), DictConstant.DetailFieldType.link.getCode()));
            }

        }

        // 处理中
        if (DictConstant.AfterSaleKey.isProcessing(afterSaleKey)) {
            // 极速退款中 || 商家已同意退款
            if (StrUtil.equals(afterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_8.getCode())
                    || StrUtil.equals(afterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_205.getCode())) {
                // 退款金额
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("退款金额", OrderAfterSaleUtil.showAmount(orderAfterSaleConsultRecord.getApplyAmount())));
            }
            // 商家同意退货退款，待客户寄回商品 || 商家同意换货，待客户寄回商品
            else if (StrUtil.equals(afterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_201.getCode())
                    || StrUtil.equals(afterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_301.getCode())) {
                // 退货地址
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("退货地址", orderAfterSaleConsultRecord.getBackAddress()));
            }
            // 客户已寄回商品，待商家收货
            else if (StrUtil.equals(afterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_6.getCode())) {
                // 显示：快递公司，快递单号，联系电话，上传凭证
                // 快递公司
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("快递公司", Memory.getLogisticsCompanyName(orderAfterSaleConsultRecord.getBackLogisticsCompany())));
                // 快递单号
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("快递单号", orderAfterSaleConsultRecord.getBackCourierNumber()));
                // 联系电话
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("联系电话", orderAfterSaleConsultRecord.getCustomerPhone()));
                // 上传凭证
                // 查询凭证信息
                List<String> orderAfterSaleLogisticsImgHisList = orderAfterSaleLogisticsImgHisService.queryImgByHistoryIdAndCourierImgType(orderAfterSaleConsultRecord.getHistoryId(), DictConstant.CourierImgType.courierImgType_2.getCode());
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("上传凭证", StrUtil.join(",", orderAfterSaleLogisticsImgHisList.toArray()), DictConstant.DetailFieldType.link.getCode()));
            }
            // 商家不同意申请，待客户处理 || 商家拒绝退款，待客户处理 || 商家拒绝换货，待客户处理
            else if (StrUtil.equals(afterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_10.getCode())
                    || StrUtil.equals(afterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_206.getCode())
                    || StrUtil.equals(afterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_306.getCode())) {
                // 拒绝原因
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("拒绝原因", Memory.getDict(DictConstant.RejectReason.getDictTypeNumber(), orderAfterSaleConsultRecord.getRejectReason())));
                // 拒绝说明
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("拒绝说明", orderAfterSaleConsultRecord.getRejectDetail()));
            }
            // 商家换货已重新发货,待客户确认
            else if (StrUtil.equals(afterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_304.getCode())){
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("快递公司", Memory.getLogisticsCompanyName(orderAfterSaleConsultRecord.getBackLogisticsCompany())));
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("快递单号", orderAfterSaleConsultRecord.getBackCourierNumber()));
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("收货地址", orderAfterSaleConsultRecord.getBackAddress()));
            }
            else {
                // TODO 其他情况先待定
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO(Memory.getDict(DictConstant.AfterSaleStatus.getDictTypeNumber(), afterSaleStatus)));
            }

        }

        // 已处理
        if (DictConstant.AfterSaleKey.isFinish(afterSaleKey)) {
            // APP客户撤销申请
            if (StrUtil.equals(afterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_4.getCode())) {
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("可在订单可售后期再次发起申请，仍有疑问可联系商家或者平台客服沟通解决"));
            }
            // 极速退款成功
            else if (StrUtil.equals(afterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_9.getCode())) {
                // 退款金额
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("退款金额", OrderAfterSaleUtil.showAmount(orderAfterSaleConsultRecord.getApplyAmount())));
                // 退款
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO("退款", OrderAfterSaleUtil.showAmount(orderAfterSaleConsultRecord.getApplyAmount()) + Memory.getDict(DictConstant.RefundTrend.getDictTypeNumber(), orderAfterSaleConsultRecord.getRefundTrend())));
            } else {
                // TODO 其他情况先待定
                detailVOList.add(OrderAfterSaleConsultRecordDetailVO.buildDetailVO(Memory.getDict(DictConstant.AfterSaleStatus.getDictTypeNumber(), afterSaleStatus)));
            }
        }

        return detailVOList;
    }

}
