package com.liuxin.ladder.logs.controller;

import java.net.BindException;

import com.liuxin.ladder.logs.MonitorLogger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  
  
  @GetMapping(value = "/test")
  public String test1(String test) throws BindException{
    try {
       MonitorLogger.printControllerRqeusetLog(TestController.class,"/test",1234 , "234");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("123");
    }
    return test;
      
  }
}
