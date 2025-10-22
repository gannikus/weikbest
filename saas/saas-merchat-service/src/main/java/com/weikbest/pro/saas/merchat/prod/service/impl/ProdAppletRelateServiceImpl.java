package com.weikbest.pro.saas.merchat.prod.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaQrcodeService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaCodeLineColor;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.third.aliyun.oss.AliyunOssService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.prod.entity.ProdAppletRelate;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdAppletRelateMapper;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdAppletRelateDTO;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdAppletRelateMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdAppletRelateQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdAppletRelateVO;
import com.weikbest.pro.saas.merchat.prod.service.ProdAppletRelateService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.entity.AppletConfig;
import com.weikbest.pro.saas.sys.param.service.AppletConfigService;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;

/**
 * <p>
 * 商品关联小程序拆分表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ProdAppletRelateServiceImpl extends ServiceImpl<ProdAppletRelateMapper, ProdAppletRelate> implements ProdAppletRelateService {

    @Resource
    private AppletConfigService appletConfigService;

    @Resource
    private ThirdConfigService thirdConfigService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(Long id, ProdAppletRelateDTO prodAppletRelateDTO) {
        ProdAppletRelate prodAppletRelate = ProdAppletRelateMapStruct.INSTANCE.converToEntity(prodAppletRelateDTO);
        prodAppletRelate.setId(id);
        // 生成小程序图片
        String appletAppQrcodeUrl = createWxaCodeUnlimit(id, prodAppletRelateDTO);
        prodAppletRelate.setAppletAppQrcodeUrl(appletAppQrcodeUrl);
        return this.save(prodAppletRelate);
    }

    private String createWxaCodeUnlimit(Long prodId, ProdAppletRelateDTO prodAppletRelateDTO) {
        String appletPageUrl = prodAppletRelateDTO.getAppletPageUrl();
        String[] appletPageUrlArr = appletPageUrl.split("\\?id=");
        String page = StrUtil.subSuf(appletPageUrlArr[0], 1);
        String scene = "id=" + appletPageUrlArr[1];

        AppletConfig appletConfig = appletConfigService.findById(prodAppletRelateDTO.getAppletConfigId());
        WxMaService wxMaService = appletConfigService.wxMaService(prodAppletRelateDTO.getAppletAppId());
        WxMaQrcodeService qrcodeService = wxMaService.getQrcodeService();
        InputStream is = null;
        File wxaCodeUnlimitFile = null;
        try {
            // 调用微信小程序图片生成接口
            wxaCodeUnlimitFile = qrcodeService.createWxaCodeUnlimit(scene, page, true, DictConstant.WxMiniappType.getWxMiniappTypeByCode(appletConfig.getWxMiniappType()).getJumpWxaEnvVersion(), 430, false, new WxMaCodeLineColor(), false);
            is = new FileInputStream(wxaCodeUnlimitFile);
            // 上传到阿里云oss
            AliyunOssService aliyunOssService = thirdConfigService.aliyunOssService();
            return aliyunOssService.uploadFileAvatar(is, "prod", wxaCodeUnlimitFile.getName());
        } catch (WxErrorException | FileNotFoundException e) {
            log.error(String.format("商品：%s,生成小程序图片失败！", prodId), e);
            throw new WeikbestException(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
            // 删除临时文件
            FileUtil.del(wxaCodeUnlimitFile);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(Long id, ProdAppletRelateDTO prodAppletRelateDTO) {
        ProdAppletRelate prodAppletRelate = this.findById(id);
        if (!StrUtil.equals(prodAppletRelate.getAppletAppId(), prodAppletRelateDTO.getAppletAppId())
                || !StrUtil.equals(prodAppletRelate.getAppletPageUrl(), prodAppletRelateDTO.getAppletPageUrl())
                || StrUtil.isBlank(prodAppletRelate.getAppletPageUrl())) {
            // 生成小程序图片
            String appletAppQrcodeUrl = createWxaCodeUnlimit(id, prodAppletRelateDTO);
            prodAppletRelate.setAppletAppQrcodeUrl(appletAppQrcodeUrl);
        }
        ProdAppletRelateMapStruct.INSTANCE.copyProperties(prodAppletRelateDTO, prodAppletRelate);
        prodAppletRelate.setId(id);
        return this.updateById(prodAppletRelate);
    }

    @Override
    public ProdAppletRelate findById(Long id) {
        ProdAppletRelate prodAppletRelate = this.getById(id);
        if (ObjectUtil.isNull(prodAppletRelate)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return prodAppletRelate;
    }

    @Override
    public IPage<ProdAppletRelate> queryPage(ProdAppletRelateQO prodAppletRelateQO, PageReq pageReq) {
        QueryWrapper<ProdAppletRelate> wrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(prodAppletRelateQO.getAppletConfigId())) {
            wrapper.eq(ProdAppletRelate.APPLET_CONFIG_ID, prodAppletRelateQO.getAppletConfigId());
        }
        if (StrUtil.isNotBlank(prodAppletRelateQO.getAppletOriginalId())) {
            wrapper.eq(ProdAppletRelate.APPLET_ORIGINAL_ID, prodAppletRelateQO.getAppletOriginalId());
        }
        if (StrUtil.isNotBlank(prodAppletRelateQO.getAppletAppId())) {
            wrapper.eq(ProdAppletRelate.APPLET_APP_ID, prodAppletRelateQO.getAppletAppId());
        }
        if (StrUtil.isNotBlank(prodAppletRelateQO.getAppletPageUrl())) {
            wrapper.eq(ProdAppletRelate.APPLET_PAGE_URL, prodAppletRelateQO.getAppletPageUrl());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertWithProd(Long prodId, ProdAppletRelateDTO prodAppletRelateDTO) {
        if (ObjectUtil.isEmpty(prodAppletRelateDTO)) {
            throw new WeikbestException("商品关联小程序信息为空，请检查数据！");
        }

        // 转换实体
        ProdAppletRelate prodAppletRelate = ProdAppletRelateMapStruct.INSTANCE.converToEntity(prodAppletRelateDTO, prodId);
        this.save(prodAppletRelate);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrUpdateProdAppletRelate(Long prodId, ProdAppletRelateDTO prodAppletRelateDTO) {
        if (!prodAppletRelateDTO.getAppletPageUrl().contains("isSe")){
            prodAppletRelateDTO.setAppletPageUrl(prodAppletRelateDTO.getAppletPageUrl()+"&isSe=true");
        }
        ProdAppletRelate prodAppletRelate = this.getById(prodId);
        if (ObjectUtil.isEmpty(prodAppletRelate)) {
            // 新增
            return this.insert(prodId, prodAppletRelateDTO);
        }
        // 更新
        return this.updateById(prodId, prodAppletRelateDTO);
    }

    @Override
    public ProdAppletRelateVO findVOById(Long prodId) {
        ProdAppletRelate prodAppletRelate = this.getById(prodId);
        return ProdAppletRelateMapStruct.INSTANCE.converToVO(prodAppletRelate);
    }

    @Override
    public ProdAppletRelate findByAppletPageUrl(String appletPageUrl) {
        ProdAppletRelate prodAppletRelate = this.getOne(new QueryWrapper<ProdAppletRelate>().eq(ProdAppletRelate.APPLET_PAGE_URL, appletPageUrl));
        if (ObjectUtil.isNull(prodAppletRelate)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return prodAppletRelate;
    }
}
