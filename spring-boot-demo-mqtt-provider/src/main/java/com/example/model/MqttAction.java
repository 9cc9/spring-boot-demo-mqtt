package com.example.model;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * mqtt操作类
 */
@Data
public class MqttAction implements Serializable {

  /**
   * 操作
   * ADD/UPDATE/DELETE
   */
  private String action;

  /**
   * 监控对象
   */
  private MonitorEntity monitor;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
