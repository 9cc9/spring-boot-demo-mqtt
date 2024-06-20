package com.example.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Value;

/**
 * mqtt provider callback
 */
public class MqttProviderCallBack implements MqttCallback {


  @Value("${spring.mqtt.client.id}")
  private String clientId;

  /**
   * 与服务器断开连接的回调
   */
  @Override
  public void connectionLost(Throwable throwable) {
    System.out.println(clientId + "lost connection");
  }

  /**
   * 消息到达的回调
   */
  @Override
  public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
    System.out.println(clientId + "message arrived");
  }

  /**
   * 消息发布成功的回调
   */
  @Override
  public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    IMqttAsyncClient client = iMqttDeliveryToken.getClient();
    System.out.println(client.getClientId() + "deliver complete");
  }

}
