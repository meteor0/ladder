package com.liuxin.ladder.aop.redis;


import java.lang.annotation.*;

/**
 * 使用redis缓存注解
 *
 * @author meteor
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface DeleteRedis {
    /**
     * 缓存key的前缀
     */
    String prefix();

    /**
     * 请求参数的名称,支持对象中属性
     */
    String [] params()  default "" ;

}
