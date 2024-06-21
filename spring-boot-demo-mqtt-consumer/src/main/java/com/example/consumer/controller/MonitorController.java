package com.example.consumer.controller;

import com.example.base.entity.MonitorEntity;
import com.example.base.exception.AssertUtil;
import com.example.consumer.service.MonitorService;
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
public class MonitorController extends AbstractController {

    @Autowired
    private MonitorService monitorService;

    @PostMapping("/add.json")
    public BaseResult<MonitorEntity> addMonitor(@RequestBody MonitorEntity monitor) {

        check(monitor);

        monitorService.addOrUpdate(monitor);

        return BaseResult.success(monitor);
    }

    private void check(MonitorEntity monitor) {
        AssertUtil.notNull(monitor);
        AssertUtil.isNotBlank(monitor.getName());
        AssertUtil.isNotBlank(monitor.getStatus());
    }
}
