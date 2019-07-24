package com.liuxin.ladder.designpatterns.proxy.cglib;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


public class UserServiceCglib  implements MethodInterceptor {

    private Object target;

    public Object getInstance(Object target) {
        //给业务对象赋值
        this.target = target;

        //创建加强器，用来创建动态代理类
        Enhancer enhancer = new Enhancer();
        //为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
        enhancer.setSuperclass(this.target.getClass());
        //设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦
        enhancer.setCallback(this);
        // 创建动态代理类对象并返回
        return enhancer.create();
    }

    /**
     * 实现MethodInterceptor接口中重写的方法
     *
     * 回调方法
     */

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        Object res=null;
        System.out.println("CGLib动态代理前");
        res=method.invoke(target, objects);
        System.out.println("CGLib动态代理后");
        return res;    }
}
