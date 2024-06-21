package com.example.provider.controller;

import com.example.base.entity.MonitorEntity;
import com.example.provider.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {

  @Autowired
  private MonitorService monitorService;

  @GetMapping("/list")
  public String list(Model model) {

    List<MonitorEntity> monitors = monitorService.getAll();
    model.addAttribute("monitors", monitors);

    return "monitor/list";
  }

  @GetMapping("/query")
  public String query(Model model, Integer id) {

    MonitorEntity monitor = monitorService.queryById(id);
    model.addAttribute("monitors", Collections.singletonList(monitor));

    return "monitor/list";
  }
}
