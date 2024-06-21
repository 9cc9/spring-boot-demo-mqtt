#!/bin/bash

# 设置变量
REPO_URL="git@github.com:9cc9/spring-boot-demo-mqtt.git"
PROJECT_DIR="/root/spring-boot-demo-mqtt"
# consumer project
CONSUMER_MODULE_DIR="$PROJECT_DIR/spring-boot-demo-mqtt-consumer"
CONSUMER_TARGET_DIR="$CONSUMER_MODULE_DIR/target"
CONSUMER_JAR_FILE="spring-boot-demo-mqtt-consumer.jar"
CONSUMER_LOG_FILE="/root/logs/consumer_run.log"

# provider project
PROVIDER_MODULE_DIR="$PROJECT_DIR/spring-boot-demo-mqtt-provider"
PROVIDER_TARGET_DIR="$PROVIDER_MODULE_DIR/target"
PROVIDER_JAR_FILE="spring-boot-demo-mqtt-provider.jar"
PROVIDER_LOG_FILE="/root/logs/provider_run.log"


# 确保日志目录存在
mkdir -p /root/logs

# 确保历史文件删除
echo "Clear history codes..."
rm -rf $PROJECT_DIR

# 停止之前启动的 Spring Boot 应用
echo "Stopping previous Spring Boot application..."
ps aux | grep $CONSUMER_JAR_FILE | grep -v grep | awk '{print $2}' | xargs -r kill -9
ps aux | grep $PROVIDER_JAR_FILE | grep -v grep | awk '{print $2}' | xargs -r kill -9

# 克隆仓库
echo "Cloning repository from $REPO_URL..."
git clone $REPO_URL

# 检查克隆是否成功
if [ $? -ne 0 ]; then
    echo "Failed to clone repository."
    exit 1
fi

# 进入项目目录并执行 Maven 构建
echo "Entering project directory $PROJECT_DIR and building the project..."
cd $PROJECT_DIR
mvn clean install

# 检查 Maven 构建是否成功
if [ $? -ne 0 ]; then
    echo "Maven build failed."
    exit 1
fi

# 进入目标目录并运行 JAR 文件
echo "Entering target directory $CONSUMER_TARGET_DIR and running the JAR file..."
cd $CONSUMER_TARGET_DIR
nohup java -jar $CONSUMER_JAR_FILE > $CONSUMER_LOG_FILE 2>&1 &

# 检查 JAR 文件是否成功启动
if [ $? -ne 0 ]; then
    echo "Failed to start the JAR file."
    exit 1
fi

echo "Entering target directory $PROVIDER_TARGET_DIR and running the JAR file..."
cd $PROVIDER_TARGET_DIR
nohup java -jar $PROVIDER_JAR_FILE > $PROVIDER_LOG_FILE 2>&1 &

# 检查 JAR 文件是否成功启动
if [ $? -ne 0 ]; then
    echo "Failed to start the JAR file."
    exit 1
fi

echo "Script executed successfully."
