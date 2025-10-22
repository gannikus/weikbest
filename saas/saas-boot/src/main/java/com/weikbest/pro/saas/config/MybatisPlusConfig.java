package com.weikbest.pro.saas.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wisdomelon
 * @date 2020/4/4 0004
 * @project saas
 * @jdk 1.8
 * Mybatis-Plus 配置类
 */
@Configuration
@MapperScan(basePackages = {"com.weikbest.pro.saas.**.mapper"})
public class MybatisPlusConfig {

    /**
     * mybatis-plus
     * 乐观锁插件:OptimisticLockerInnerInterceptor, 统一作用在gmt_modified字段上
     * 阻止恶意的全表更新删除:BlockAttackInnerInterceptor
     * 分页插件：PaginationInnerInterceptor
     * SQL性能规范插件：IllegalSQLInnerInterceptor
     *
     * @return MybatisPlusInterceptor
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
//        interceptor.addInnerInterceptor(new IllegalSQLInnerInterceptor());
        return interceptor;
    }

}
