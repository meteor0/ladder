package com.liuxin.ladder.aop;

import java.lang.annotation.*;

/**
 * @description: 编写一个注解，用来当切点使用
 * @author: liuxin79
 * @date: 2019-09-02 18:11
 *
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerWebLog  {
    String name();

    boolean intoDb() default false;
}
