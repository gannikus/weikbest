package com.weikbest.pro.saas.merchat.prod.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.prod.entity.ProdAdvBackAccount;
import com.weikbest.pro.saas.merchat.prod.entity.ProdDecFloor;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdDecFloorMapper;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdDecFloorDTO;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdDecFloorMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdDecFloorQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.PayBackProdAdvBackAccountVO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdAdvBackAccountVO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdDecFloorVO;
import com.weikbest.pro.saas.merchat.prod.service.ProdAdvBackAccountService;
import com.weikbest.pro.saas.merchat.prod.service.ProdDecFloorService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 商品装修落地页拆分表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Service
public class ProdDecFloorServiceImpl extends ServiceImpl<ProdDecFloorMapper, ProdDecFloor> implements ProdDecFloorService {

    @Resource
    private ProdAdvBackAccountService prodAdvBackAccountService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(Long id, ProdDecFloorDTO prodDecFloorDTO) {
        ProdDecFloor prodDecFloor = ProdDecFloorMapStruct.INSTANCE.converToEntity(prodDecFloorDTO);
        prodDecFloor.setId(id);
        boolean save = this.save(prodDecFloor);

        // 新增关联账号回传信息
        prodAdvBackAccountService.updateProdAdvBackAccount(id, prodDecFloorDTO);
        return save;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(Long id, ProdDecFloorDTO prodDecFloorDTO) {
        ProdDecFloor prodDecFloor = this.findById(id);
        ProdDecFloorMapStruct.INSTANCE.copyProperties(prodDecFloorDTO, prodDecFloor);
        prodDecFloor.setId(id);
        boolean update = this.updateById(prodDecFloor);

        // 更新关联账号回传信息
        prodAdvBackAccountService.updateProdAdvBackAccount(id, prodDecFloorDTO);
        return update;
    }

    @Override
    public ProdDecFloor findById(Long id) {
        ProdDecFloor prodDecFloor = this.getById(id);
        if (ObjectUtil.isNull(prodDecFloor)) {
            prodDecFloor = new ProdDecFloor();
        }
        return prodDecFloor;
    }

    @Override
    public IPage<ProdDecFloor> queryPage(ProdDecFloorQO prodDecFloorQO, PageReq pageReq) {
        QueryWrapper<ProdDecFloor> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(prodDecFloorQO.getPageTitle())) {
            wrapper.eq(ProdDecFloor.PAGE_TITLE, prodDecFloorQO.getPageTitle());
        }
        if (StrUtil.isNotBlank(prodDecFloorQO.getBuyBtnTitle())) {
            wrapper.eq(ProdDecFloor.BUY_BTN_TITLE, prodDecFloorQO.getBuyBtnTitle());
        }
        if (StrUtil.isNotBlank(prodDecFloorQO.getFloatBtnTitle())) {
            wrapper.eq(ProdDecFloor.FLOAT_BTN_TITLE, prodDecFloorQO.getFloatBtnTitle());
        }
        if (StrUtil.isNotBlank(prodDecFloorQO.getCountdownOffers())) {
            wrapper.eq(ProdDecFloor.COUNTDOWN_OFFERS, prodDecFloorQO.getCountdownOffers());
        }
        if (StrUtil.isNotBlank(prodDecFloorQO.getCountdownTitle())) {
            wrapper.eq(ProdDecFloor.COUNTDOWN_TITLE, prodDecFloorQO.getCountdownTitle());
        }
        if (StrUtil.isNotBlank(prodDecFloorQO.getSuccessPayBack())) {
            wrapper.eq(ProdDecFloor.SUCCESS_PAY_BACK, prodDecFloorQO.getSuccessPayBack());
        }
        if (StrUtil.isNotBlank(prodDecFloorQO.getAdvBackType())) {
            wrapper.eq(ProdDecFloor.ADV_BACK_TYPE, prodDecFloorQO.getAdvBackType());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public ProdDecFloorVO getVOById(Long id) {
        ProdDecFloor prodDecFloor = this.getById(id);
        ProdDecFloorVO prodDecFloorVO = ProdDecFloorMapStruct.INSTANCE.converToVO(prodDecFloor);
        if (ObjectUtil.isNotEmpty(prodDecFloorVO)) {
            List<ProdAdvBackAccountVO> prodAdvBackAccountVOList = prodAdvBackAccountService.queryVOById(id);
            prodDecFloorVO.setProdAdvBackAccountVOList(prodAdvBackAccountVOList);
        }

        return prodDecFloorVO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrUpdateProdDecFloor(Long id, ProdDecFloorDTO prodDecFloorDTO) {
        ProdDecFloor prodDecFloor = this.getById(id);
        if (ObjectUtil.isEmpty(prodDecFloor)) {
            // 新增
            return this.insert(id, prodDecFloorDTO);
        }

        // 更新
        return this.updateById(id, prodDecFloorDTO);
    }

    @Override
    public PayBackProdAdvBackAccountVO findPayBackAndBackRatio(Long prodId) {
        PayBackProdAdvBackAccountVO payBackProdAdvBackAccountVO = new PayBackProdAdvBackAccountVO();

        ProdDecFloor prodDecFloor = this.getById(prodId);
        if(ObjectUtil.isNull(prodDecFloor)){
            return null;
        }

        if(StrUtil.equals(prodDecFloor.getSuccessPayBack(), DictConstant.Whether.yes.getCode())) {
            // 支付成功回传
            // 全部回传
            if(StrUtil.equals(prodDecFloor.getAdvBackType(), DictConstant.AdvBackType.TYPE_1.getCode())) {
                payBackProdAdvBackAccountVO.setBackRatio(new BigDecimal("100.00"));
            }
            // 按投放账号回传,随机找一个账号
            else {
                ProdAdvBackAccount prodAdvBackAccount = prodAdvBackAccountService.queryRandomOneByProdId(prodId);
                payBackProdAdvBackAccountVO.setAdvAccountId(prodAdvBackAccount.getAdvAccountId())
                        .setBackRatio(prodAdvBackAccount.getBackRatio());
            }
        }

        payBackProdAdvBackAccountVO.setId(prodId)
                .setAdvBackType(prodDecFloor.getAdvBackType())
                .setSuccessPayBack(prodDecFloor.getSuccessPayBack());
        return payBackProdAdvBackAccountVO;
    }
}
