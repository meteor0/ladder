package com.liuxin.ladder.ladder.logs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  
  
  @GetMapping(value = "/test")
  public String test1(String test){
   throw new RuntimeException("错误");
  }

}
