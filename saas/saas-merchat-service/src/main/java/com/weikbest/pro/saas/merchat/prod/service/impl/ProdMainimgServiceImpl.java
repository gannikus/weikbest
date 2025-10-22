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
import com.weikbest.pro.saas.merchat.prod.entity.ProdMainimg;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdMainimgMapper;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdMainimgDTO;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdMainimgMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdMainimgQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdMainimgVO;
import com.weikbest.pro.saas.merchat.prod.service.ProdMainimgService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品详情页轮播图拆分多行表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ProdMainimgServiceImpl extends ServiceImpl<ProdMainimgMapper, ProdMainimg> implements ProdMainimgService {

    @Override
    public boolean insert(ProdMainimgDTO prodMainimgDTO) {
        ProdMainimg prodMainimg = ProdMainimgMapStruct.INSTANCE.converToEntity(prodMainimgDTO);
        return this.save(prodMainimg);
    }

    @Override
    public boolean updateById(Long id, ProdMainimgDTO prodMainimgDTO) {
        ProdMainimg prodMainimg = this.findById(id);
        ProdMainimgMapStruct.INSTANCE.copyProperties(prodMainimgDTO, prodMainimg);
        prodMainimg.setId(id);
        return this.updateById(prodMainimg);
    }

    @Override
    public ProdMainimg findById(Long id) {
        ProdMainimg prodMainimg = this.getById(id);
        if (ObjectUtil.isNull(prodMainimg)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return prodMainimg;
    }

    @Override
    public IPage<ProdMainimg> queryPage(ProdMainimgQO prodMainimgQO, PageReq pageReq) {
        QueryWrapper<ProdMainimg> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(prodMainimgQO.getMainImg())) {
            wrapper.eq(ProdMainimg.MAIN_IMG, prodMainimgQO.getMainImg());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public void insertBatchWithProd(Long prodId, List<ProdMainimgDTO> prodMainimgDTOList) {
        // 实体转换
        List<ProdMainimg> prodMainimgList = prodMainimgDTOList.stream()
                .map(prodMainimgDTO -> ProdMainimgMapStruct.INSTANCE.converToEntity(prodMainimgDTO, prodId))
                .collect(Collectors.toList());

        // 先删除，后添加
        this.remove(new QueryWrapper<ProdMainimg>().eq(ProdMainimg.ID, prodId));
        this.saveBatch(prodMainimgList);
    }

    @Override
    public List<ProdMainimgVO> queryVOByProdId(Long prodId) {
        List<ProdMainimg> prodMainimgList = this.list(new QueryWrapper<ProdMainimg>().eq(ProdMainimg.ID, prodId));
        return prodMainimgList.stream().map(ProdMainimgMapStruct.INSTANCE::converToVO).collect(Collectors.toList());
    }


    @Override
    public List<ProdMainimg> selectByProdId(Long id){
        return Optional.ofNullable(this.list(new LambdaQueryWrapper<ProdMainimg>().eq(ProdMainimg::getId , id))).orElseGet(ArrayList::new);
    }
}
