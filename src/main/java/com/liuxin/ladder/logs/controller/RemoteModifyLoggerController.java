package com.liuxin.ladder.logs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;

/**
 * @Description: 远程修改日志级别
 * @author liuxin
 * @date 2019年6月13日 下午8:20:43
 *
 */
@RestController
public class RemoteModifyLoggerController {
  
  
  private Logger log = LoggerFactory.getLogger(RemoteModifyLoggerController.class);

  @RequestMapping(value = "/logback")
  public String logj() {
    log.error("我是error");
    log.warn("我是warn");
    log.info("我是info");
    log.debug("我是debug");
    return "success";
  }


  /**
   * logback动态修改包名的日志级别,
            按照范围从小到大排序：OFF level > FATAL > ERROR > WARN > INFO > DEBUG > ALL level
   * @param level 日志级别 debug,info,warn,error
   * @param packageName 包名
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/level")
  public String updateLogbackLevel(@RequestParam(value = "level") String level,
      @RequestParam(value = "packageName", defaultValue = "-1") String packageName)
      throws Exception {
    LoggerContext loggerContext =(LoggerContext) LoggerFactory.getILoggerFactory();
    if (packageName.equals("-1")) {
      // 默认值-1，更改全局日志级别；否则按传递的包名或类名修改日志级别。
      log.info("root修改了日志级别为:{}",level);
      loggerContext.getLogger("root").setLevel(Level.toLevel(level));
    } else {
      log.info("{}修改了日志级别为:{}",packageName,level);
      loggerContext.getLogger(packageName).setLevel(Level.valueOf(level));
    }
    return "success";
  }
}

