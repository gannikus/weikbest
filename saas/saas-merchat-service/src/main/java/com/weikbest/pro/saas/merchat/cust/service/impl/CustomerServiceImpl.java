package com.weikbest.pro.saas.merchat.cust.service.impl;

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
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.merchat.cust.entity.Customer;
import com.weikbest.pro.saas.merchat.cust.mapper.CustomerMapper;
import com.weikbest.pro.saas.merchat.cust.module.dto.CustomerDTO;
import com.weikbest.pro.saas.merchat.cust.module.mapstruct.CustomerMapStruct;
import com.weikbest.pro.saas.merchat.cust.module.qo.CustomerQO;
import com.weikbest.pro.saas.merchat.cust.module.vo.CustomerVO;
import com.weikbest.pro.saas.merchat.cust.service.CustomerService;
import com.weikbest.pro.saas.merchat.cust.util.CustomerUtil;
import com.weikbest.pro.saas.sys.common.constant.CodeRuleConstant;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.common.service.CurrentUserService;
import com.weikbest.pro.saas.sys.param.service.CodeRuleService;
import com.weikbest.pro.saas.sys.system.entity.User;
import com.weikbest.pro.saas.sys.system.entity.UserLogin;
import com.weikbest.pro.saas.sys.system.entity.UserRelate;
import com.weikbest.pro.saas.sys.system.service.UserLoginService;
import com.weikbest.pro.saas.sys.system.service.UserRelateService;
import com.weikbest.pro.saas.sys.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-16
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Resource
    private CurrentUserService currentUserService;

    @Resource
    private UserService userService;

    @Resource
    private UserRelateService userRelateService;

    @Resource
    private UserLoginService userLoginService;

    @Resource
    private CodeRuleService codeRuleService;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public boolean insert(CustomerDTO customerDTO) {
        Customer customer = CustomerMapStruct.INSTANCE.converToEntity(customerDTO);
        return this.save(customer);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TokenUser registerCustomer(Customer customer, String custThirdType, String loginIp) {
        Long id = GenerateIDUtil.nextId();
        customer.setCustThirdType(custThirdType);
        customer.setNumber(codeRuleService.nextNum(CodeRuleConstant.T_CCMM_CUSTOMER));
        customer.setId(id);
        this.save(customer);

        // 非平台用户注册
        Long userId = userService.registerRelateUser(customer.getUserUnique(), customer.getName(), customer.getEmail(), customer.getAvatar(), DictConstant.UserRelateType.applet.getCode(), id);
        // 用户登录
        return getTokenUser(customer, userId, loginIp);
    }

    private TokenUser getTokenUser(Customer customer, Long userId, String loginIp) {
        UserLogin userLogin;
         userLogin = userLoginService.findByUserIdAndLoginType(userId, DictConstant.LoginType.phone.getCode());
        if (ObjectUtil.isNull(userLogin)){
            userLogin = userLoginService.getOne(new QueryWrapper<UserLogin>().eq(UserLogin.LOGIN_NAME, customer.getUserUnique()).eq(UserLogin.LOGIN_TYPE, DictConstant.LoginType.phone.getCode()));
            if (ObjectUtil.isNull(userLogin)){
                throw new WeikbestException("用户不存在！");
            }else {
                userLogin.setUserId(userId);
                userLoginService.updateById(userLogin);
            }
        }
        TokenUser tokenUser = userLoginService.loginUser(userLogin, DictConstant.UserRelateType.applet.getCode(), loginIp, StrUtil.isNotBlank(customer.getPhone()) ? customer.getPhone() : customer.getUserUnique(), customer.getId());
        tokenUser.setOpenId(customer.getWxOpenid()).setUnionId(customer.getWxUnionid());
        return tokenUser;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TokenUser updateById(Long id, CustomerDTO customerDTO, String loginIp) {
        // 存在，更新用户
        Customer customer = this.findById(id);
        customer = CustomerMapStruct.INSTANCE.converToEntity(customerDTO, customer);
        customer.setId(id);
        this.updateById(customer);
        User user;
        // 查询平台用户ID
        UserRelate userRelate = userRelateService.findByRelateId(id, DictConstant.UserRelateType.applet.getCode());
        if (ObjectUtil.isNull(userRelate)) {
            user = userService.getOne(new QueryWrapper<User>().eq(User.PHONE, customer.getUserUnique()));
        }else {
            user = userService.findById(userRelate.getUserId());
        }
        boolean flag = CustomerUtil.isChange(customer, user);
        if (flag) {
            // 非平台用户更新平台表的信息
            Long userId = userService.updateByRelateUser(customer.getPhone(), customer.getName(), customer.getEmail(), customer.getAvatar(), DictConstant.UserRelateType.applet.getCode(), customer.getId());
            // 用户重新登录，返回新的token
            return getTokenUser(customer, userId, loginIp);
        }
        // 未发生变化，则返回当前的token
        return currentUserService.getAppTokenUser();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TokenUser loginCustomerAndUpdate(Customer customer, String loginIp) {
        // 存在，更新用户
        this.updateById(customer);
        User user;
        // 查询平台用户ID
        UserRelate userRelate = userRelateService.findByRelateId(customer.getId(), DictConstant.UserRelateType.applet.getCode());
        if (ObjectUtil.isNull(userRelate)) {
             user = userService.getOne(new QueryWrapper<User>().eq(User.PHONE, customer.getUserUnique()));
        }else {
             user = userService.findById(userRelate.getUserId());
        }
        Long userId = user.getId();
        boolean flag = CustomerUtil.isChange(customer, user);
        if (flag) {
            // 非平台用户更新平台表的信息
            userId = userService.updateByRelateUser(customer.getUserUnique(), customer.getName(), customer.getEmail(), customer.getAvatar(), DictConstant.UserRelateType.applet.getCode(), customer.getId());
        }
        // 用户登录，返回token
        return getTokenUser(customer, userId, loginIp);
    }


    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public Customer findById(Long id) {
        Customer customer = this.getById(id);
        if (ObjectUtil.isNull(customer)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return customer;
    }

    @Override
    public Customer findByOpenid(String openid) {
        Customer customer = this.getOne(new QueryWrapper<Customer>().eq(Customer.WX_OPENID, openid));
        if (ObjectUtil.isNull(customer)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return customer;
    }

    @Override
    public IPage<Customer> queryPage(CustomerQO customerQO, PageReq pageReq) {
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(customerQO.getNumber())) {
            wrapper.eq(Customer.NUMBER, customerQO.getNumber());
        }
        if (StrUtil.isNotBlank(customerQO.getName())) {
            wrapper.eq(Customer.NAME, customerQO.getName());
        }
        if (StrUtil.isNotBlank(customerQO.getAvatar())) {
            wrapper.eq(Customer.AVATAR, customerQO.getAvatar());
        }
        if (StrUtil.isNotBlank(customerQO.getPhone())) {
            wrapper.eq(Customer.PHONE, customerQO.getPhone());
        }
        if (StrUtil.isNotBlank(customerQO.getSex())) {
            wrapper.eq(Customer.SEX, customerQO.getSex());
        }
        if (StrUtil.isNotBlank(customerQO.getCustCountry())) {
            wrapper.eq(Customer.CUST_COUNTRY, customerQO.getCustCountry());
        }
        if (StrUtil.isNotBlank(customerQO.getCustProvince())) {
            wrapper.eq(Customer.CUST_PROVINCE, customerQO.getCustProvince());
        }
        if (StrUtil.isNotBlank(customerQO.getCustCity())) {
            wrapper.eq(Customer.CUST_CITY, customerQO.getCustCity());
        }
        if (StrUtil.isNotBlank(customerQO.getCustDistrict())) {
            wrapper.eq(Customer.CUST_DISTRICT, customerQO.getCustDistrict());
        }
        if (StrUtil.isNotBlank(customerQO.getCustAddr())) {
            wrapper.eq(Customer.CUST_ADDR, customerQO.getCustAddr());
        }
        if (StrUtil.isNotBlank(customerQO.getCustThirdType())) {
            wrapper.eq(Customer.CUST_THIRD_TYPE, customerQO.getCustThirdType());
        }
        if (StrUtil.isNotBlank(customerQO.getAppId())) {
            wrapper.eq(Customer.APP_ID, customerQO.getAppId());
        }
        if (StrUtil.isNotBlank(customerQO.getWxOpenid())) {
            wrapper.eq(Customer.WX_OPENID, customerQO.getWxOpenid());
        }
        if (StrUtil.isNotBlank(customerQO.getWxUnionid())) {
            wrapper.eq(Customer.WX_UNIONID, customerQO.getWxUnionid());
        }
        if (StrUtil.isNotBlank(customerQO.getWxQrcode())) {
            wrapper.eq(Customer.WX_QRCODE, customerQO.getWxQrcode());
        }
        if (StrUtil.isNotBlank(customerQO.getDataStatus())) {
            wrapper.eq(Customer.DATA_STATUS, customerQO.getDataStatus());
        }
        if (StrUtil.isNotBlank(customerQO.getDescription())) {
            wrapper.eq(Customer.DESCRIPTION, customerQO.getDescription());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public Customer getCustomerByOpenidAndCustThirdType(String openid, String custThirdType) {
        return this.getOne(new QueryWrapper<Customer>().eq(Customer.WX_OPENID, openid).eq(Customer.CUST_THIRD_TYPE, custThirdType));
    }

    @Override
    public Map<Long, CustomerVO> queryVOMapByIdList(List<Long> customerIdList) {
        if (CollectionUtil.isEmpty(customerIdList)) {
            return new HashMap<>(1);
        }
        List<Customer> customerList = this.listByIds(customerIdList);
        return customerList.stream().map(CustomerMapStruct.INSTANCE::converToVO).collect(Collectors.toMap(CustomerVO::getId, customerVO -> customerVO));
    }

    @Override
    public List<Long> getIdsById(Long id){
        return customerMapper.getIdsById(id);
    }
}
