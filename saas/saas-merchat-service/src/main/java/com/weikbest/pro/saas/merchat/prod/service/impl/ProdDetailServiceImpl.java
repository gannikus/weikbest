package com.weikbest.pro.saas.merchat.prod.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.prod.entity.ProdDetail;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdDetailMapper;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdDetailDTO;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdDetailMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdDetailQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdDetailVO;
import com.weikbest.pro.saas.merchat.prod.service.ProdDetailService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品详情拆分多行表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ProdDetailServiceImpl extends ServiceImpl<ProdDetailMapper, ProdDetail> implements ProdDetailService {

    @Override
    public boolean insert(ProdDetailDTO prodDetailDTO) {
        ProdDetail prodDetail = ProdDetailMapStruct.INSTANCE.converToEntity(prodDetailDTO);
        return this.save(prodDetail);
    }

    @Override
    public boolean updateById(Long id, ProdDetailDTO prodDetailDTO) {
        ProdDetail prodDetail = this.findById(id);
        ProdDetailMapStruct.INSTANCE.copyProperties(prodDetailDTO, prodDetail);
        prodDetail.setId(id);
        return this.updateById(prodDetail);
    }

    @Override
    public ProdDetail findById(Long id) {
        ProdDetail prodDetail = this.getById(id);
        if (ObjectUtil.isNull(prodDetail)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return prodDetail;
    }

    @Override
    public IPage<ProdDetail> queryPage(ProdDetailQO prodDetailQO, PageReq pageReq) {
        QueryWrapper<ProdDetail> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(prodDetailQO.getDetailImg())) {
            wrapper.eq(ProdDetail.DETAIL_IMG, prodDetailQO.getDetailImg());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public void insertBatchWithProd(Long prodId, List<ProdDetailDTO> prodDetailDTOList) {
        // 实体转换
        List<ProdDetail> prodDetailList = prodDetailDTOList.stream()
                .map(prodDetailDTO -> ProdDetailMapStruct.INSTANCE.converToEntity(prodDetailDTO, prodId))
                .collect(Collectors.toList());

        // 先删除，后添加
        this.remove(new QueryWrapper<ProdDetail>().eq(ProdDetail.ID, prodId));
        this.saveBatch(prodDetailList);

    }

    @Override
    public List<ProdDetailVO> queryVOByProdId(Long prodId) {
        List<ProdDetail> prodDetailList = this.list(new QueryWrapper<ProdDetail>().eq(ProdDetail.ID, prodId));
        return prodDetailList.stream().map(ProdDetailMapStruct.INSTANCE::converToVO).collect(Collectors.toList());
    }


    @Override
    public List<ProdDetail> selectByProdId(Long id){
        return Optional.ofNullable(this.list(new LambdaQueryWrapper<ProdDetail>().eq(ProdDetail::getId , id))).orElseGet(ArrayList::new);
    }
}
