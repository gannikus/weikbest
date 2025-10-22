package com.weikbest.pro.saas;

import com.weikbest.pro.saas.common.util.web.LocalIpUtil;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.io.Serializable;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Optional;

@SpringBootApplication
public class SaasBootApplication {

    public static String port;

    public static String active;
    @Value("${server.port}")
    public void setPort(String value) {
        port = value;
    }

    @Value("${spring.profiles.active}")
    public void setActive(String value) {
        active = value;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SaasBootApplication.class, args);

        Thread.sleep(100);
        Optional<Inet4Address> localIp4Address = LocalIpUtil.getLocalIp4Address();
        String ip = localIp4Address.map(Inet4Address::getHostAddress).orElse("null");
        System.out.println("◤======================================     ☠     ======================================◥");
        System.out.println("|                                                                                          |");
        System.out.println("|                                 ✍      ip:  " + ip + "                                  |");
        System.out.println("|                                 ✍    port:  "+port+"                                         |");
        System.out.println("|                                 ✍  active:  "+active+"                                     |");
        System.out.println("|                                                                                          |");
        System.out.println("◣======================================     ☠     ======================================◢");

    }

}
