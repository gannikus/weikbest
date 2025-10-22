package com.weikbest.pro.saas.applet.comm.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weikbest.pro.saas.applet.comm.module.mapstruct.*;
import com.weikbest.pro.saas.applet.comm.module.qo.AppProdSemPositionQO;
import com.weikbest.pro.saas.applet.comm.module.vo.*;
import com.weikbest.pro.saas.applet.comm.service.AppCommonService;
import com.weikbest.pro.saas.merchat.category.entity.AppletDecConfig;
import com.weikbest.pro.saas.merchat.category.entity.AppletDecConfigEntry;
import com.weikbest.pro.saas.merchat.category.entity.AppletProdCategory;
import com.weikbest.pro.saas.merchat.category.service.AppletDecConfigEntryService;
import com.weikbest.pro.saas.merchat.category.service.AppletDecConfigService;
import com.weikbest.pro.saas.merchat.category.service.AppletProdCategoryService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.entity.Dict;
import com.weikbest.pro.saas.sys.param.entity.DictType;
import com.weikbest.pro.saas.sys.param.entity.LogisticsCompany;
import com.weikbest.pro.saas.sys.param.module.mapstruct.LogisticsCompanyMapStruct;
import com.weikbest.pro.saas.sys.param.module.vo.LogisticsCompanyVO;
import com.weikbest.pro.saas.sys.param.service.DictService;
import com.weikbest.pro.saas.sys.param.service.DictTypeService;
import com.weikbest.pro.saas.sys.param.service.LogisticsCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppCommonServiceImpl implements AppCommonService {

    @Autowired
    private AppletDecConfigEntryService appletDecConfigEntryService;

    @Autowired
    private AppletDecConfigService appletDecConfigService;

    @Autowired
    private DictService dictService;

    @Autowired
    private DictTypeService dictTypeService;

    @Autowired
    private AppletProdCategoryService appletProdCategoryService;

    @Resource
    private LogisticsCompanyService logisticsCompanyService;


    @Override
    public List<AppProdCarouselVO> queryProdCarousels() {

        QueryWrapper<AppletDecConfigEntry> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(AppletDecConfigEntry.APPLET_CONFIG_TYPE, DictConstant.AppletConfigType.TYPE_1.getCode());
        queryWrapper.orderByAsc(AppletDecConfigEntry.ENTRY_ORD);
        List<AppletDecConfigEntry> appletDecConfigEntrys = appletDecConfigEntryService.list(queryWrapper);

        List<AppProdCarouselVO> appProdCarouselVOS = new ArrayList<>();
        for (AppletDecConfigEntry appletDecConfigEntry : appletDecConfigEntrys) {
            AppProdCarouselVO appProdCarouselVO = new AppProdCarouselVO();
            appProdCarouselVOS.add(AppProdCarouselMapStruct.INSTANCE.converToVO(appletDecConfigEntry));
        }

        return appProdCarouselVOS;
    }

    @Override
    public List<AppProdSemPositionVO> queryProdSemPositions(AppProdSemPositionQO appProdSemPositionQO) {

        QueryWrapper<AppletDecConfig> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(appProdSemPositionQO.getPositionnumber())) {
//            queryWrapper.eq(AppletDecConfig.NUMBER,appProdSemPositionQO.getPositionnumber());
        }
        queryWrapper.orderByAsc(AppletDecConfig.ID);
        List<AppletDecConfig> prodSemPositions = appletDecConfigService.list(queryWrapper);

        List<AppProdSemPositionVO> appProdSemPositionVOS = new ArrayList<>();
        for (AppletDecConfig appletDecConfig : prodSemPositions) {
            AppProdSemPositionVO appProdSemPositionVO = new AppProdSemPositionVO();
            appProdSemPositionVO = AppProdSemPositionMapStruct.INSTANCE.converToVO(appletDecConfig);

            QueryWrapper<AppletDecConfigEntry> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq(AppletDecConfigEntry.ID, appletDecConfig.getId());
            queryWrapper1.orderByAsc(AppletDecConfigEntry.ENTRY_ORD);
            List<AppletDecConfigEntry> prodSemCategories = appletDecConfigEntryService.list(queryWrapper1);
            List<AppProdSemCategoryVO> appProdSemCategoryVOList = new ArrayList<>();
            for (AppletDecConfigEntry prodSemCategory : prodSemCategories) {
                AppProdSemCategoryVO appProdSemCategoryVO = new AppProdSemCategoryVO();
                appProdSemCategoryVOList.add(AppProdSemCategoryMapStruct.INSTANCE.converToVO(prodSemCategory));
            }
            appProdSemPositionVO.setAppProdSemCategoryVOList(appProdSemCategoryVOList);
            appProdSemPositionVOS.add(appProdSemPositionVO);

        }

        return appProdSemPositionVOS;
    }

    @Override
    public List<AppDictVO> queryDictsByNumber(String number) {

        QueryWrapper<DictType> dictTypeQueryWrapper = new QueryWrapper<>();
        dictTypeQueryWrapper.eq(Dict.NUMBER, number);
        DictType dictType = dictTypeService.getOne(dictTypeQueryWrapper);
        List<AppDictVO> appDictVOS = new ArrayList<>();


        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.eq(Dict.DICT_TYPE_ID, dictType.getId());
        dictQueryWrapper.eq(Dict.DATA_STATUS,DictConstant.Whether.yes.getCode());
        dictQueryWrapper.orderByAsc(Dict.DICT_ORD);
        List<Dict> dicts = dictService.list(dictQueryWrapper);
        for (Dict dict : dicts) {
            AppDictVO appDictVO = AppDictMapStruct.INSTANCE.converToVO(dict);
            appDictVOS.add(appDictVO);
        }

        return appDictVOS;

    }

    @Override
    public List<AppAppletDecConfigEntryVO> queryIndexInfo(Long appletDecConfigId) {

        QueryWrapper<AppletDecConfigEntry> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(AppletDecConfigEntry.ID, appletDecConfigId);
        queryWrapper.orderByAsc(AppletDecConfigEntry.APPLET_CONFIG_TYPE);
        queryWrapper.orderByAsc(AppletDecConfigEntry.ENTRY_ORD);

        List<AppAppletDecConfigEntryVO> decConfigEntryVOS = new ArrayList<>();

        List<AppletDecConfigEntry> decConfigEntries = appletDecConfigEntryService.list(queryWrapper);
        for (AppletDecConfigEntry appletDecConfigEntry : decConfigEntries) {
            AppAppletDecConfigEntryVO vo = AppAppletDecConfigEntryMapStruct.INSTANCE.converToVO(appletDecConfigEntry);
            decConfigEntryVOS.add(vo);
        }
        return decConfigEntryVOS;
    }

    @Override
    public List<AppAppletProdCategoryVO> queryAppletProdCategorys(String number) {

        QueryWrapper<AppletProdCategory> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(number)) {
            queryWrapper.eq(AppletProdCategory.NUMBER, number);
        }
        queryWrapper.eq(AppletProdCategory.CATEGORY_LEVEL, 1);
        queryWrapper.orderByAsc(AppletProdCategory.CATEGORY_ORD);

        List<AppAppletProdCategoryVO> voList = new ArrayList<>();

        List<AppletProdCategory> list = appletProdCategoryService.list(queryWrapper);
        for (AppletProdCategory appletProdCategory : list) {
            QueryWrapper<AppletProdCategory> qw = new QueryWrapper<>();
            qw.eq(AppletProdCategory.PARENT_ID, appletProdCategory.getId());
            qw.eq(AppletProdCategory.CATEGORY_LEVEL, 2);
            qw.orderByAsc(AppletProdCategory.CATEGORY_ORD);

            List<AppAppletProdCategoryVO> voList2 = new ArrayList<>();
            List<AppletProdCategory> list2 = appletProdCategoryService.list(qw);
            for (AppletProdCategory appletProdCategory2 : list2) {
                AppAppletProdCategoryVO vo2 = AppAppletProdCategoryMapStruct.INSTANCE.converToVO(appletProdCategory2);
                voList2.add(vo2);
            }
            AppAppletProdCategoryVO vo = AppAppletProdCategoryMapStruct.INSTANCE.converToVO(appletProdCategory);
            vo.setChildren(voList2);
            voList.add(vo);
        }

        return voList;
    }

    @Override
    public List<LogisticsCompanyVO> queryLogisticsCompany() {

        List<LogisticsCompany> list = logisticsCompanyService.list(new QueryWrapper<LogisticsCompany>().orderByDesc(LogisticsCompany.SORT_BY));
        List<LogisticsCompanyVO> logisticsCompanyVOS = new ArrayList<>();
        for (LogisticsCompany logisticsCompany : list) {
            LogisticsCompanyVO logisticsCompanyVO = LogisticsCompanyMapStruct.INSTANCE.converToVO(logisticsCompany);
            logisticsCompanyVOS.add(logisticsCompanyVO);
        }
        return logisticsCompanyVOS;

    }
}
