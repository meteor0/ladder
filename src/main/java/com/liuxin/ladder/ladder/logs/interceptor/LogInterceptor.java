package com.liuxin.ladder.ladder.logs.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liuxin.ladder.ladder.logs.bean.ResLogsVO;

public class LogInterceptor implements HandlerInterceptor{

  Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
  
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
  //创建日志实体
    ResLogsVO sysLog = new ResLogsVO();
    //获取请求参数信息
    String param = JSON.toJSONString(request.getParameterMap());
    //设置时间
    sysLog.setTime(new Date());
    sysLog.setDomain("jiaowu.staff.xdf.cn");
    sysLog.setUrl(request.getRequestURI());
    sysLog.setParams(param);
    request.getMethod();
    System.out.println(JSONObject.toJSONString(sysLog));
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    logger.info("postHandle:请求后调用");
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    logger.info("afterCompletion执行完请求方法完全返回之后:{}",ex.getMessage());
    
  }
}
