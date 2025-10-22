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
import com.weikbest.pro.saas.merchat.prod.entity.ProdLeftSlide;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdLeftSlideMapper;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdLeftSlideDTO;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdLeftSlideMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdLeftSlideQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdJumpLinkVO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdLeftSlideVO;
import com.weikbest.pro.saas.merchat.prod.service.ProdJumpLinkService;
import com.weikbest.pro.saas.merchat.prod.service.ProdLeftSlideService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品左滑设置拆分表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Service
public class ProdLeftSlideServiceImpl extends ServiceImpl<ProdLeftSlideMapper, ProdLeftSlide> implements ProdLeftSlideService {

    @Resource
    private ProdJumpLinkService prodJumpLinkService;

    @Override
    public boolean insert(Long id, ProdLeftSlideDTO prodLeftSlideDTO) {
        ProdLeftSlide prodLeftSlide = ProdLeftSlideMapStruct.INSTANCE.converToEntity(prodLeftSlideDTO);
        prodLeftSlide.setId(id);
        boolean save = this.save(prodLeftSlide);

        // 更新商品链接信息
        prodJumpLinkService.updateProdJumpLink(id, prodLeftSlideDTO);
        return save;
    }

    @Override
    public boolean updateById(Long id, ProdLeftSlideDTO prodLeftSlideDTO) {
        ProdLeftSlide prodLeftSlide = this.findById(id);
        ProdLeftSlideMapStruct.INSTANCE.copyProperties(prodLeftSlideDTO, prodLeftSlide);
        prodLeftSlide.setId(id);
        boolean update = this.updateById(prodLeftSlide);

        // 更新商品链接信息
        prodJumpLinkService.updateProdJumpLink(id, prodLeftSlideDTO);
        return update;
    }

    @Override
    public ProdLeftSlide findById(Long id) {
        ProdLeftSlide prodLeftSlide = this.getById(id);
        if (ObjectUtil.isNull(prodLeftSlide)) {
            prodLeftSlide = new ProdLeftSlide();
        }
        return prodLeftSlide;
    }

    @Override
    public IPage<ProdLeftSlide> queryPage(ProdLeftSlideQO prodLeftSlideQO, PageReq pageReq) {
        QueryWrapper<ProdLeftSlide> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(prodLeftSlideQO.getSwitchText())) {
            wrapper.eq(ProdLeftSlide.SWITCH_TEXT, prodLeftSlideQO.getSwitchText());
        }
        if (StrUtil.isNotBlank(prodLeftSlideQO.getSwitchJump())) {
            wrapper.eq(ProdLeftSlide.SWITCH_JUMP, prodLeftSlideQO.getSwitchJump());
        }
        if (StrUtil.isNotBlank(prodLeftSlideQO.getIndexJump())) {
            wrapper.eq(ProdLeftSlide.INDEX_JUMP, prodLeftSlideQO.getIndexJump());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public ProdLeftSlideVO getVOById(Long id) {
        ProdLeftSlide prodLeftSlide = this.getById(id);
        ProdLeftSlideVO prodLeftSlideVO = ProdLeftSlideMapStruct.INSTANCE.converToVO(prodLeftSlide);
        if (ObjectUtil.isNotEmpty(prodLeftSlideVO)) {
            List<ProdJumpLinkVO> prodJumpLinkVOList = prodJumpLinkService.queryVOById(id);
            prodLeftSlideVO.setProdJumpLinkVOArrayList(prodJumpLinkVOList);
        }
        return prodLeftSlideVO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrUpdateProdLeftSlide(Long id, ProdLeftSlideDTO prodLeftSlideDTO) {
        ProdLeftSlide prodLeftSlide = this.getById(id);
        if (ObjectUtil.isEmpty(prodLeftSlide)) {
            // 新增
            return this.insert(id, prodLeftSlideDTO);
        }

        // 更新
        return this.updateById(id, prodLeftSlideDTO);
    }
}
