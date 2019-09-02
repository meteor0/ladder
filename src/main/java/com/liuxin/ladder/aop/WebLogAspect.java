package com.liuxin.ladder.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @description: 定义一个切面
 * @author: liuxin79
 * @date: 2019-09-02 20:15
 */

@Aspect
@Component
@Order(100)
public class WebLogAspect {

    /**定义一个切点*/
    @Pointcut("execution(* cn.itweknow.sbaop.controller..*.*(..))")
    public void webLog() {}
}
