# saas(saas商城)

#### 介绍


#### 软件架构

1. Springboot 2.2.1.RELEASE
2. Swagger-ui [http://localhost:9001/swagger-ui.html](http://localhost:9001/swagger-ui.html)
3. Swagger-ui-layui [http://localhost:9001/docs.html](http://localhost:9001/docs.html)
4. Mybatis-Plus [https://mp.baomidou.com/](https://mp.baomidou.com/)
5. Hutool [https://www.hutool.cn/](https://www.hutool.cn/)
6. WxJava [https://github.com/wechat-group/WxJava](https://github.com/wechat-group/WxJava)
7.

Aliyun-Oss [https://help.aliyun.com/document_detail/32009.html](https://help.aliyun.com/document_detail/32009.html?spm=a2c4g.11186623.6.766.7288c06djMKC26)

8. Mybatis-Plus-Generator [https://github.com/baomidou/generator](https://github.com/baomidou/generator)

#### 安装教程

1. 执行清理和打包的步骤，并且跳过单元测试：将此命令复制到与顶级项目同级的cmd控制台执行 mvn clean package -Dmaven.test.skip=true

#### 使用说明

1. 通过命令行的方式启动
    1. java -Dloader.path="lib/" -jar saas-boot.jar （--spring.profiles.active=prod）
    2. 说明：
        1. -Dloader.path="lib/" 表示依赖外部的jar包
        2. （）里面的参数： -- 表示启动时携带的参数，所有的参数均在application.properties文件中配置，也可不携带任何参数启动。

3. 通过脚本方式启动
    1. cd /app/sh
    2. sh saas-boot.sh start 启动
    3. sh saas-boot.sh stop 停止
    4. sh saas-boot.sh restart 重启
    5. sh saas-boot.sh status 查看当前启动状态

4. redis服务端特殊配置
    1. 打开redis服务端配置文件 linux: redis.conf windows: redis.windows.conf
    2. 在文件第890行附近，将配置 notify-keyspace-events Ex 放开（将前面的#号去掉）后保存
    3. 启动redis windows在cmd执行：redis-server redis.windows.conf

