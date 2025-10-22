#!/bin/bash

# Java ENV
export JAVA_HOME=/opt/jdk1.8.0_251
export JRE_HOME=${JAVA_HOME}/jre

# Apps Info
# 应用存放地址
APP_HOME=/home/admin/application/saas/saas-boot/target
# 应用名称
APP_NAME=saas-boot

# Shell Info

# 使用说明，用来提示输入参数
usage() {
    echo "Usage: sh saas-boot [start|stop|restart|status]"
    exit 1
}

# 检查程序是否在运行
is_exist(){
        # 获取PID
        PID=$(ps -ef |grep ${APP_NAME} | grep -v $0 |grep -v grep |awk '{print $2}')
        # -z "${pid}"判断pid是否存在，如果不存在返回1，存在返回0
        if [ -z "${PID}" ]; then
                # 如果进程不存在返回1
                return 1
        else
                # 进程存在返回0
                return 0
        fi
}

# 定义启动程序函数
start(){
        is_exist
        if [ $? -eq "0" ]; then
                echo "${APP_NAME} is already running, PID=${PID}"
        else
                echo "nohup ${JRE_HOME}/bin/java -jar ${APP_HOME}/${APP_NAME}.jar --spring.profiles.active=prod >/dev/null 2>&1 &"
                nohup ${JRE_HOME}/bin/java -jar -Xms4096m -Xmx6144m ${APP_HOME}/${APP_NAME}.jar --spring.profiles.active=prod  >/dev/null 2>&1 &
                PID=$(echo $!)
                echo "${APP_NAME} start success, PID=$!"
        fi
}

# 停止进程函数
stop(){
        is_exist
        if [ $? -eq "0" ]; then
                kill -9 ${PID}
                echo "${APP_NAME} process stop, PID=${PID}"
        else
                echo "There is not the process of ${APP_NAME}"
        fi
}

# 重启进程函数
restart(){
        stop
        start
}

# 查看进程状态
status(){
        is_exist
        if [ $? -eq "0" ]; then
                echo "${APP_NAME} is running, PID=${PID}"
        else
                echo "There is not the process of ${APP_NAME}"
        fi
}

case $1 in
"start")
        start
        ;;
"stop")
        stop
        ;;
"restart")
        restart
        ;;
"status")
       status
        ;;
	*)
	usage
	;;
esac
exit 0
