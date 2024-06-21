package com.example.provider.callback;

import com.example.base.log.LogUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Value;

/**
 * mqtt provider callback
 */
public class MqttProviderCallBack implements MqttCallback {

    private static final Logger logger = LogManager.getLogger(MqttProviderCallBack.class);

    @Value("${spring.mqtt.client.id}")
    private String clientId;

    /**
     * 与服务器断开连接的回调
     */
    @Override
    public void connectionLost(Throwable throwable) {
        LogUtil.warn(logger, "client connect lost.");
    }

    /**
     * 消息到达的回调
     */
    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) {
        LogUtil.info(logger, "message arrived,message=", mqttMessage);
    }

    /**
     * 消息发布成功的回调
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        IMqttAsyncClient client = iMqttDeliveryToken.getClient();
        LogUtil.info(logger, "deliver complete,clientId=", client.getClientId());
    }

}
