package com.example.provider.controller.mqtt;


import com.alibaba.fastjson2.JSON;
import com.example.base.model.MqttAction;
import com.example.provider.service.MonitorService;
import org.apache.commons.lang.StringUtils;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mqtt订阅回调
 */
@Component
public class MqttConsumerCallBack implements MqttCallback {

    @Autowired
    private MonitorService monitorService;

    /**
     * 客户端断开连接的回调
     */
    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("与服务器断开连接，可重连");
    }

    /**
     * 消息到达的回调
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println(String.format("接收消息主题 : %s", topic));
        System.out.println(String.format("接收消息Qos : %d", message.getQos()));
        String payload = new String(message.getPayload());
        System.out.println(String.format("接收消息内容 : %s", payload));
        System.out.println(String.format("接收消息retained : %b", message.isRetained()));

        if (StringUtils.isNotBlank(payload)) {
            MqttAction action = JSON.parseObject(payload, MqttAction.class);
            if (action != null && StringUtils.isNotBlank(action.getAction())) {
                switch (action.getAction()) {
                    case ("ADD"):
                    case ("UPDATE"):
                        monitorService.addOrUpdate(action.getMonitor());
                        break;

                    case ("DELETE"):
                        monitorService.delete(action.getMonitor().getId());
                    default:


                }
            }
        }
    }

    /**
     * 消息发布成功的回调
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println(String.format("接收消息成功"));
    }
}
