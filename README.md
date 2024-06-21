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
```
fetch("http://1.95.10.8:8080/demo/sendMessage?qos=1&retained=false&topic=topic_test_1&monitorName=监控1&monitorStatus=ONLINE&action=ADD", {
  "headers": {
    "accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
    "accept-language": "zh-CN,zh;q=0.9,en;q=0.8,zh-TW;q=0.7",
    "cache-control": "max-age=0",
    "sec-ch-ua": "\"Google Chrome\";v=\"125\", \"Chromium\";v=\"125\", \"Not.A/Brand\";v=\"24\"",
    "sec-ch-ua-mobile": "?0",
    "sec-ch-ua-platform": "\"macOS\"",
    "sec-fetch-dest": "document",
    "sec-fetch-mode": "navigate",
    "sec-fetch-site": "none",
    "sec-fetch-user": "?1",
    "upgrade-insecure-requests": "1"
  },
  "referrerPolicy": "strict-origin-when-cross-origin",
  "body": null,
  "method": "GET",
  "mode": "cors",
  "credentials": "omit"
});
```

## 其他依赖
1. 部署mysql server
2. redis server
3. emqx 消息中间件
4. 部署脚本参见 build.sh