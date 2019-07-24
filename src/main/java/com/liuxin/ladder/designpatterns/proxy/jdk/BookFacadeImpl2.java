package com.liuxin.ladder.designpatterns.proxy.jdk;

public class BookFacadeImpl2 implements  BookFacade {
    @Override
    public void addBook() {
        System.out.println("增加图书方法222。。。");
    }
}
