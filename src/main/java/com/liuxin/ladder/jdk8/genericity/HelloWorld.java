package com.liuxin.ladder.jdk8.genericity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 泛型类示例
 * @author meteor
 * @param <T>
 */
public class HelloWorld<T> {
    private final static Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    private T t;

    public T getValue() {
        return t;
    }

    public void setValue(T t) {
        this.t = t;
    }

    public static void main(String[] args) {
        
    }
}
