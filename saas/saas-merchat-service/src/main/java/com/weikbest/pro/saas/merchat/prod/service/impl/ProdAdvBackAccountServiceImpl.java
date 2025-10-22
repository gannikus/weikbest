package com.weikbest.pro.saas.merchat.prod.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.prod.entity.ProdAdvBackAccount;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdAdvBackAccountMapper;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdAdvBackAccountDTO;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdDecFloorDTO;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdAdvBackAccountMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdAdvBackAccountQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdAdvBackAccountVO;
import com.weikbest.pro.saas.merchat.prod.service.ProdAdvBackAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品广告回传信息关联广告账户拆分多行表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Slf4j
@Service
@SuppressWarnings("all")
public class ProdAdvBackAccountServiceImpl extends ServiceImpl<ProdAdvBackAccountMapper, ProdAdvBackAccount> implements ProdAdvBackAccountService {

    @Autowired
    private ProdAdvBackAccountMapper prodAdvBackAccountMapper;

    @Override
    public boolean insert(ProdAdvBackAccountDTO prodAdvBackAccountDTO) {
        ProdAdvBackAccount prodAdvBackAccount = ProdAdvBackAccountMapStruct.INSTANCE.converToEntity(prodAdvBackAccountDTO);
        return this.save(prodAdvBackAccount);
    }

    @Override
    public boolean updateById(Long id, ProdAdvBackAccountDTO prodAdvBackAccountDTO) {
        ProdAdvBackAccount prodAdvBackAccount = this.findById(id);
        ProdAdvBackAccountMapStruct.INSTANCE.copyProperties(prodAdvBackAccountDTO, prodAdvBackAccount);
        prodAdvBackAccount.setId(id);
        return this.updateById(prodAdvBackAccount);
    }

    @Override
    public ProdAdvBackAccount findById(Long id) {
        ProdAdvBackAccount prodAdvBackAccount = this.getById(id);
        if (ObjectUtil.isNull(prodAdvBackAccount)) {
            prodAdvBackAccount = new ProdAdvBackAccount();
        }
        return prodAdvBackAccount;
    }

    @Override
    public IPage<ProdAdvBackAccount> queryPage(ProdAdvBackAccountQO prodAdvBackAccountQO, PageReq pageReq) {
        QueryWrapper<ProdAdvBackAccount> wrapper = new QueryWrapper<>();
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public void insertBatchWithProd(Long prodId, List<ProdAdvBackAccountDTO> prodAdvBackAccountDTOList) {
        if (CollectionUtil.isEmpty(prodAdvBackAccountDTOList)) {
            throw new WeikbestException("商品广告回传关联广告账户信息为空，请检查数据！");
        }

        // 转换实体
        List<ProdAdvBackAccount> prodAdvBackAccountList = prodAdvBackAccountDTOList.stream()
                .map(prodAdvBackAccountDTO -> ProdAdvBackAccountMapStruct.INSTANCE.converToEntity(prodAdvBackAccountDTO, prodId))
                .collect(Collectors.toList());
        this.saveBatch(prodAdvBackAccountList);
    }

    @Override
    public List<ProdAdvBackAccountVO> queryVOById(Long id) {
        List<ProdAdvBackAccount> prodAdvBackAccountList = this.list(new QueryWrapper<ProdAdvBackAccount>().eq(ProdAdvBackAccount.ID, id));
        return prodAdvBackAccountList.stream().map(ProdAdvBackAccountMapStruct.INSTANCE::converToVO).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateProdAdvBackAccount(Long id, ProdDecFloorDTO prodDecFloorDTO) {
        this.remove(new QueryWrapper<ProdAdvBackAccount>().eq(ProdAdvBackAccount.ID, id));
        List<ProdAdvBackAccountDTO> prodAdvBackAccountDTOList = prodDecFloorDTO.getProdAdvBackAccountDTOList();
        if (CollectionUtil.isNotEmpty(prodAdvBackAccountDTOList)) {
            List<ProdAdvBackAccount> prodAdvBackAccountList = prodAdvBackAccountDTOList.stream().map(prodAdvBackAccountDTO -> {
                ProdAdvBackAccount prodAdvBackAccount = ProdAdvBackAccountMapStruct.INSTANCE.converToEntity(prodAdvBackAccountDTO);
                prodAdvBackAccount.setId(id);
                return prodAdvBackAccount;
            }).collect(Collectors.toList());
            this.saveBatch(prodAdvBackAccountList);
        }
    }

    @Override
    public ProdAdvBackAccount queryRandomOneByProdId(Long prodId) {
        List<ProdAdvBackAccount> prodAdvBackAccountList = this.list(new QueryWrapper<ProdAdvBackAccount>().eq(ProdAdvBackAccount.ID, prodId));
        if(CollectionUtil.isEmpty(prodAdvBackAccountList)) {
            log.info("商品{}，未设置投放账号的广告回传比率，默认返回0", prodId);
            ProdAdvBackAccount prodAdvBackAccount = new ProdAdvBackAccount();
            prodAdvBackAccount.setBackRatio(BigDecimal.ZERO).setAdvAccountId(0L);
            return prodAdvBackAccount;
        }
        // 随机回传一个
        return RandomUtil.randomEle(prodAdvBackAccountList);
    }

    @Override
    public int deleteByProdId(Long prodId){
        return prodAdvBackAccountMapper.deleteByProdId(prodId);
    }

    @Override
    public int updateByProdId(ProdAdvBackAccount account){
        return prodAdvBackAccountMapper.updateByProdId(account);
    }


    @Override
    public ProdAdvBackAccount findByProdId(Long id){
        List<ProdAdvBackAccount> accounts = prodAdvBackAccountMapper.selectList(new LambdaQueryWrapper<ProdAdvBackAccount>().eq(ProdAdvBackAccount::getId, id));
        if (CollectionUtil.isEmpty(accounts)){
            return new ProdAdvBackAccount();
        }else {
            return accounts.get(0);
        }
    }


    @Override
    public ProdAdvBackAccount findByProdIdAndAccountId(Long prodId, Long advAccountId){
        List<ProdAdvBackAccount> accounts = prodAdvBackAccountMapper.selectList(new LambdaQueryWrapper<ProdAdvBackAccount>().eq(ProdAdvBackAccount::getId, prodId).eq(ProdAdvBackAccount::getAdvAccountId, advAccountId));
        if (CollectionUtil.isEmpty(accounts)){
            return new ProdAdvBackAccount();
        }else {
            return accounts.get(0);
        }
    }
}
