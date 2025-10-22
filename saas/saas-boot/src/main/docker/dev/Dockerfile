# 该镜像需要依赖的基础镜像 FROM openjdk:8-jdk-alpine  官方轻量镜像  下面的类库较全 frolvlad/alpine-java:jdk8-slim
FROM registry.cn-beijing.aliyuncs.com/wh-saas/saas:openjdk-8
# 将当前目录下的jar包复制到docker容器的/目录下
COPY ./saas-boot.jar /tmp/app.jar
# 环境变量
CMD ["--spring.profiles.active=devlocal"]
# 声明服务运行在9002端口
EXPOSE 9002
# 指定docker容器启动时运行jar包
ENTRYPOINT ["java", "-jar","/tmp/app.jar"]
# 指定维护者的名字
MAINTAINER gzm
