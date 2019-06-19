package com.liuxin.ladder.logs.exception;

import java.net.BindException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liuxin.ladder.logs.bean.GlobalExceptionEnum;
import com.liuxin.ladder.logs.bean.RespLogsVO;

@ControllerAdvice
public class GlobalExceptionHandler {

  /**用于elk错误日志收集*/
  private static Logger elkLog = LoggerFactory.getLogger("elk_log");  
  
  @ExceptionHandler(BindException.class)
  public void runtimeException(HttpServletRequest request, Exception ex){
    
    RespLogsVO sysLog = new RespLogsVO();
    //获取请求参数信息
    String param = JSON.toJSONString(request.getParameterMap());
    //设置时间
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime localDateTime = LocalDateTime.now();
    sysLog.setTime(formatter.format(localDateTime));
    sysLog.setDomain("jiaowu.staff.xdf.cn");
    sysLog.setUrl(request.getRequestURI());
    sysLog.setParams(param);
    sysLog.setErroInfo(GlobalExceptionEnum.APPID_MISSING);
    elkLog.info(JSONObject.toJSONString(sysLog));
  }
}
