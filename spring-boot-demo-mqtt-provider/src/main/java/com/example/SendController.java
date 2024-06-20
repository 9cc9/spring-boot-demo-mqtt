package com.example;

import com.alibaba.fastjson2.JSON;
import com.example.config.MqttProviderConfig;
import com.example.model.MonitorEntity;
import com.example.model.MqttAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SendController {
  @Autowired
  private MqttProviderConfig providerClient;

  @RequestMapping("/sendMessage")
  @ResponseBody
  public String sendMessage(int qos, boolean retained, String topic, String monitorName) {
    try {
      MonitorEntity monitor = new MonitorEntity();
      monitor.setName(monitorName);
      monitor.setStatus("ONLINE");

      MqttAction action = new MqttAction();
      action.setAction("ADD");
      action.setMonitor(monitor);

      providerClient.publish(qos, retained, topic, JSON.toJSONString(action));
      return "发送成功";
    } catch (Exception e) {
      e.printStackTrace();
      return "发送失败";
    }
  }
}
