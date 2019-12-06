package com.liuxin.ladder.spring;

/**
 * @description:
 * @author: liuxin79
 * @date: 2019-10-29 14:38
 */

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**获取bean的上下文*/
public class ApplicationContext {

    public static void main(String[] args) {
        //1. 创建 Spring 的 IOC 容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("XML_PATH");
        //2. 从 IOC 容器中获取 bean 的实例
        Object beanName = context.getBean("bean name");
        //使用 bean
        beanName.toString();
        context.close();
    }
}
