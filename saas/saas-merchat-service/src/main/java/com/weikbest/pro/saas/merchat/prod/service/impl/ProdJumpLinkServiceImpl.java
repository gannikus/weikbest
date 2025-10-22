package com.weikbest.pro.saas.merchat.prod.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.prod.entity.ProdJumpLink;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdJumpLinkMapper;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdJumpLinkDTO;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdLeftSlideDTO;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdJumpLinkMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdJumpLinkQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdJumpLinkVO;
import com.weikbest.pro.saas.merchat.prod.service.ProdJumpLinkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品跳转链接拆分多行表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ProdJumpLinkServiceImpl extends ServiceImpl<ProdJumpLinkMapper, ProdJumpLink> implements ProdJumpLinkService {

    @Override
    public boolean insert(ProdJumpLinkDTO prodJumpLinkDTO) {
        ProdJumpLink prodJumpLink = ProdJumpLinkMapStruct.INSTANCE.converToEntity(prodJumpLinkDTO);
        return this.save(prodJumpLink);
    }

    @Override
    public boolean updateById(Long id, ProdJumpLinkDTO prodJumpLinkDTO) {
        ProdJumpLink prodJumpLink = this.findById(id);
        ProdJumpLinkMapStruct.INSTANCE.copyProperties(prodJumpLinkDTO, prodJumpLink);
        prodJumpLink.setId(id);
        return this.updateById(prodJumpLink);
    }

    @Override
    public ProdJumpLink findById(Long id) {
        ProdJumpLink prodJumpLink = this.getById(id);
        if (ObjectUtil.isNull(prodJumpLink)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return prodJumpLink;
    }

    @Override
    public IPage<ProdJumpLink> queryPage(ProdJumpLinkQO prodJumpLinkQO, PageReq pageReq) {
        QueryWrapper<ProdJumpLink> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(prodJumpLinkQO.getJumpLinkType())) {
            wrapper.eq(ProdJumpLink.JUMP_LINK_TYPE, prodJumpLinkQO.getJumpLinkType());
        }
        if (StrUtil.isNotBlank(prodJumpLinkQO.getJumpLink())) {
            wrapper.eq(ProdJumpLink.JUMP_LINK, prodJumpLinkQO.getJumpLink());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public void insertBatchWithProd(Long prodId, List<ProdJumpLinkDTO> prodJumpLinkDTOList) {
        if (CollectionUtil.isEmpty(prodJumpLinkDTOList)) {
            throw new WeikbestException("商品跳转链接信息为空，请检查数据！");
        }

        // 转换实体
        List<ProdJumpLink> prodJumpLinkList = prodJumpLinkDTOList.stream()
                .map(prodJumpLinkDTO -> ProdJumpLinkMapStruct.INSTANCE.converToEntity(prodJumpLinkDTO, prodId))
                .collect(Collectors.toList());
        this.saveBatch(prodJumpLinkList);
    }

    @Override
    public List<ProdJumpLinkVO> queryVOById(Long id) {
        List<ProdJumpLink> prodJumpLinkList = this.list(new QueryWrapper<ProdJumpLink>().eq(ProdJumpLink.ID, id));
        return prodJumpLinkList.stream().map(ProdJumpLinkMapStruct.INSTANCE::converToVO).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateProdJumpLink(Long id, ProdLeftSlideDTO prodLeftSlideDTO) {
        this.remove(new QueryWrapper<ProdJumpLink>().eq(ProdJumpLink.ID, id));
        List<ProdJumpLinkDTO> prodJumpLinkDTOList = prodLeftSlideDTO.getProdJumpLinkDTOList();
        if (CollectionUtil.isNotEmpty(prodJumpLinkDTOList)) {
            List<ProdJumpLink> prodJumpLinkList = prodJumpLinkDTOList.stream().map(prodJumpLinkDTO -> {
                ProdJumpLink prodJumpLink = ProdJumpLinkMapStruct.INSTANCE.converToEntity(prodJumpLinkDTO);
                prodJumpLink.setId(id);
                return prodJumpLink;
            }).collect(Collectors.toList());
            this.saveBatch(prodJumpLinkList);
        }
    }
}
