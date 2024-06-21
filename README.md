# spring-boot-demo-mqtt

## spring-boot-demo-mqtt-base 通用module，被上层依赖
1. common模型层 
2. log4j2，日志打印

## spring-boot-demo-mqtt-consumer 标准web后端，支持如下功能
1. mysql+mybatis，数据持久化
2. redis，缓存
3. mqtt client，订阅消息

## spring-boot-demo-mqtt-provider 
1. mqtt client，发布消息

## 其他依赖
1. 部署mysql server
2. redis server
3. emqx 消息中间件
4. 部署脚本参见 build.sh