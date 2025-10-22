# saas(saas商城)

## saas-boot(应用启动项)

#### 介绍

{**以下是码云平台说明，您可以替换此简介**
码云是 OSCHINA 推出的基于 Git 的代码托管平台（同时支持 SVN）。专为开发者提供稳定、高效、安全的云端软件开发协作平台
无论是个人、团队、或是企业，都能够用码云实现代码托管、项目管理、协作开发。企业项目请看 [https://gitee.com/enterprises](https://gitee.com/enterprises)}

#### 软件架构

1. Springboot 2.2.1.RELEASE
2. Swagger-ui [http://localhost:9001/swagger-ui.html](http://localhost:9001/swagger-ui.html)

3. Mybatis-Plus [https://mp.baomidou.com/](https://mp.baomidou.com/)
4. Hutool [https://www.hutool.cn/](https://www.hutool.cn/)
5.

Aliyun-Oss [https://help.aliyun.com/document_detail/32009.html](https://help.aliyun.com/document_detail/32009.html?spm=a2c4g.11186623.6.766.7288c06djMKC26)

#### 安装教程

1. 执行清理和打包的步骤，并且跳过单元测试：将此命令复制到与顶级项目同级的cmd控制台执行

#### 使用说明

1. 通过命令行的方式启动
    1. java -Dloader.path="lib/" -jar saas-boot-1.0.jar （--spring.profiles.active=prod）
    2. 说明：
        1. -Dloader.path="lib/" 表示依赖外部的jar包
        2. （）里面的参数： -- 表示启动时携带的参数，所有的参数均在application.properties文件中配置，也可不携带任何参数启动。

3. 通过脚本方式启动
    1. cd /app/sh
    2. sh boot.sh start 启动
    3. sh boot.sh stop 停止
    4. sh boot.sh restart 重启
    5. sh boot.sh status 查看当前启动状态

