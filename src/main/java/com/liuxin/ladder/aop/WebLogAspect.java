package com.liuxin.ladder.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 定义一个切面
 * @author: liuxin79
 * @date: 2019-09-02 20:15
 */

@Aspect
@Component
@Order(100)
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);


    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    /**
     * 定义一个切点
     */
    @Pointcut("execution(* com.liuxin.ladder.controller..*.*(..))")
    public void webLog() {
    }

    /**
     * @Before 修饰的方法中的内容会在进入切点之前执行
     */
    @Before(value = "webLog()&& @annotation(controllerWebLog)")
    public void doBefore(JoinPoint joinPoint, ControllerWebLog controllerWebLog) {
        logger.info("{}doBefore - 开始调用", controllerWebLog.name());
    }

    /**
     * @AfterReturning，当程序正常执行有正确的返回时执行
     */
    @AfterReturning(value = "webLog()&& @annotation(controllerWebLog)", returning = "res")
    public void doAfterReturning(ControllerWebLog controllerWebLog, Object res) {
        logger.info("{}doAfterReturning - 开始调用", controllerWebLog.name());

    }

    /**
     * @AfterThrowing当程序发生异常时执行
     */
    @AfterThrowing(value = "webLog()&& @annotation(controllerWebLog)", throwing = "throwable")
    public void doAfterThrowing(ControllerWebLog controllerWebLog, Throwable throwable) {
        logger.info("{}AfterThrowing - 开始调用", controllerWebLog.name());
    }

    @Around(value = "@annotation(controllerWebLog)")
    public void doAround(ProceedingJoinPoint joinPoint, ControllerWebLog controllerWebLog) throws Throwable {

        logger.info("{}Around - 开始调用", controllerWebLog.name());

         joinPoint.proceed();
        logger.info("{}Around - 调用结束", controllerWebLog.name());

    }
}
