package com.example.provider.controller;

import com.example.base.entity.MonitorEntity;
import com.example.provider.service.MonitorService;
import com.example.base.util.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 监控控制器
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController {

  @Autowired
  private MonitorService monitorService;

  @PostMapping("/add.json")
  public BaseResult<MonitorEntity> addMonitor(@RequestBody MonitorEntity monitor) {
    monitorService.addOrUpdate(monitor);

    return BaseResult.success(monitor);
  }
}
