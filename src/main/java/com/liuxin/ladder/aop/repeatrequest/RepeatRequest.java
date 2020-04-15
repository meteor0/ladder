package com.liuxin.ladder.aop.repeatrequest;

import java.lang.annotation.*;

/**
 * @author laiaiqin
 * @since 2018/7/9
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RepeatRequest {
    /**
     * 调用的方法名称
     * @return
     */
    String method() default"";
}
