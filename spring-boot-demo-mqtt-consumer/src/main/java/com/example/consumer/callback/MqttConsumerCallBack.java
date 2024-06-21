package com.example.consumer.callback;


import com.alibaba.fastjson2.JSON;
import com.example.base.log.LogUtil;
import com.example.base.model.MqttAction;
import com.example.consumer.service.MonitorService;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    /**
     * logger
     */
    private static final Logger logger = LogManager.getLogger(MqttConsumerCallBack.class);

    @Autowired
    private MonitorService monitorService;

    /**
     * 客户端断开连接的回调
     */
    @Override
    public void connectionLost(Throwable throwable) {
        LogUtil.warn(logger, "client connect lost.");
    }

    /**
     * 消息到达的回调
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {

        LogUtil.info(logger, "[接收消息]:", JSON.toJSONString(message));
        String payload = new String(message.getPayload());

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
                        LogUtil.warn(logger, "Action not match,action=", action);
                }
            }
        }
    }

    /**
     * 消息发布成功的回调
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        LogUtil.info(logger, "client delivery success.");
    }
}
