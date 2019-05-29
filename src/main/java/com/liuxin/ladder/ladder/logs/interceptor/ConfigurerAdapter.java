package com.liuxin.ladder.ladder.logs.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class ConfigurerAdapter implements WebMvcConfigurer {


  @Override
  public void addInterceptors(InterceptorRegistry registry) {
      /**
       * 添加日志的拦截器
       */
      registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
  }
}
