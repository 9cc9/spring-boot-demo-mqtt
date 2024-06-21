package com.example.base.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 监控实体
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonitorEntity implements Serializable {

  private static final long serialVersionUID = 2892248514883451461L;

  /**
   * 主键id
   */
  private Integer id;

  /**
   * 姓名
   */
  private String name;

  private String status;

  private Date gmtCreate;

  private Date gmtModified;
}
