package com.weikbest.pro.saas.merchat.busi.util;

import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.util.json.JsonUtils;
import com.weikbest.pro.saas.merchat.busi.entity.BusiAddress;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/22
 * <p>
 * 商家地址帮助类
 */
public class BusiAddressUtil {

    /**
     * 商家地址对象转成字符串
     *
     * @param busiAddress
     * @return
     */
    public static String busiAddress(BusiAddress busiAddress) {
        Map<String, Object> strMap = new HashMap<>(WeikbestConstant.HASHMAP_INITIALCAPACITY);
        // 收件人
        strMap.put("name", busiAddress.getName());
        // 联系方式
        strMap.put("contact", getContact(busiAddress));
        // 收件地址
        strMap.put("detail", getAddr(busiAddress));
        return JsonUtils.bean2Json(strMap);
    }

    /**
     * 获取联系方式
     *
     * @param busiAddress
     * @return
     */
    public static String getContact(BusiAddress busiAddress) {
        if (StrUtil.isNotBlank(busiAddress.getBusiPhone())) {
            return busiAddress.getBusiPhone();
        } else {
            return StrUtil.join(" ", busiAddress.getBusiAreaCode(), busiAddress.getBusiLandlineNumber(), busiAddress.getBusiExtensionNumber());
        }
    }


    /**
     * 获取地址
     *
     * @param busiAddress
     * @return
     */
    public static String getAddr(BusiAddress busiAddress) {
        return StrUtil.join(" ", busiAddress.getBusiProvince(), busiAddress.getBusiCity(), busiAddress.getBusiDistrict(), busiAddress.getAddr());
    }
}
