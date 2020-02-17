package com.liuxin.ladder.aop.redis;


import java.lang.annotation.*;

/**
 * 使用redis缓存注解

 * @author meteor
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface EnableRedis {
    /**
     * 缓存key的前缀
     */
    String prefix() ;

    /**
     * 请求参数的名称,支持对象中属性
     * 如果是基本类型 = 参数名称,如果是对象类型 = user.id(user为形参名称)
     * key的拼接顺序按params前后顺序 一次拼接
     * 如果为空,redis的key = prefix
     */
    String [] params()  default {} ;

    /**
     * 过期时间
     */
    int expireTimes() default 60;

}
