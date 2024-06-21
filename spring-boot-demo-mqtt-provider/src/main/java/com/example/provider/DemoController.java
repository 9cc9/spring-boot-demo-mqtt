package com.example.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

  /**
   * Hello，World
   *
   * @return Hello, ${who}
   */
  @RequestMapping("/vm")
  public String sayHello() {
//        if (StrUtil.isBlank(who)) {
//            who = "World";
//        }
    return "test";
  }
}
