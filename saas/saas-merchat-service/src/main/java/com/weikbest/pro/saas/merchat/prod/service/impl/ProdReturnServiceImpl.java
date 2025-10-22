package com.weikbest.pro.saas.merchat.prod.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.constant.BasicConstant;
import com.weikbest.pro.saas.merchat.prod.entity.ProdReturn;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdReturnMapper;
import com.weikbest.pro.saas.merchat.prod.service.ProdReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Mr.Wang
 */
@Service
@SuppressWarnings("all")
public class ProdReturnServiceImpl extends ServiceImpl<ProdReturnMapper , ProdReturn> implements ProdReturnService {

    @Autowired
    private ProdReturnMapper prodReturnMapper;


    @Override
    public int deleteByProdId(Long prodId) {
        return prodReturnMapper.deleteByProdId(prodId);
    }


    public ProdReturn getReturnByProdAndClick(Long prodId, Integer[] clicks, boolean isSkip){
        //clicks[]数组的值: [0]:下次返回页的Sort , [1]:当前返回页的Sort
        Integer click = clicks[0];
        Integer stopIt = 0; //停止的值
        ProdReturn prodReturn = null;
        List<ProdReturn> returns = Optional.ofNullable(prodReturnMapper.selectList(new LambdaQueryWrapper<ProdReturn>().eq(ProdReturn::getProdId, prodId).orderByAsc(ProdReturn::getSort))).orElseGet(ArrayList::new);
        for (int i = 0; i < returns.size(); i++) {
            ProdReturn aReturn = returns.get(i);
            //当click = 0 时,返回第一个并且获取第二个返回页的Sort
            if ((BasicConstant.INT_0.equals(stopIt) && BasicConstant.INT_0.equals(click))){
                //isSkip 判断 如果营销页开关关闭, 从第四个返回页开始返回
                if (isSkip && aReturn.getSort() < 4){
                    continue;
                }
                stopIt ++;
                clicks[1] = aReturn.getSort();
                prodReturn = aReturn;
                continue;
            }else if (BasicConstant.INT_0.equals(stopIt) && aReturn.getSort().equals(click)){
                stopIt ++;
                clicks[1] = aReturn.getSort();
                prodReturn = aReturn;
                continue;
            } else if (BasicConstant.INT_1.equals(stopIt)){
                //当 stopIt = 1的时候 获取下一次返回页的Sort 并返回
                clicks[0] = aReturn.getSort();
                break;
            }
            //当click == 9999 时,说明已经没有返回页了, 返回最后一次的数据给前端并设置下次返回的值为null
            if (9999 == click && i == returns.size() -1){
                clicks[0] = null;
                clicks[1] = aReturn.getSort();
                prodReturn = aReturn;
            }
        }
        if (ObjectUtil.isNotNull(clicks[0]) && clicks[0] <= clicks[1]){
            clicks[0] = 9999;
        }
        return prodReturn;
    }

}
