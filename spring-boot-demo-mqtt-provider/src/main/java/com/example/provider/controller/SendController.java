package com.example.provider.controller;

import com.alibaba.fastjson2.JSON;
import com.example.base.entity.MonitorEntity;
import com.example.base.log.LogUtil;
import com.example.base.model.MqttAction;
import com.example.provider.config.MqttProviderConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SendController {
    private static final Logger logger = LogManager.getLogger(SendController.class);

    @Autowired
    private MqttProviderConfig providerClient;

    @RequestMapping("/sendMessage")
    @ResponseBody
    public String sendMessage(int qos, boolean retained, String topic, String monitorName, String monitorStatus, String action) {
        try {
            MonitorEntity monitor = new MonitorEntity();
            monitor.setName(monitorName);
            monitor.setStatus(monitorStatus);

            MqttAction mqttAction = new MqttAction();
            mqttAction.setAction(action);
            mqttAction.setMonitor(monitor);

            providerClient.publish(qos, retained, topic, JSON.toJSONString(action));
            return "发送成功";
        } catch (Exception e) {
            LogUtil.warn(logger, e, "send message failed,monitorName=", monitorName);
            return "发送失败";
        }
    }
}
