package com.liuxin.ladder.designpatterns.proxy.jdk;

public class BookFacadeImp来 implements  BookFacade {
    @Override
    public void addBook() {
        System.out.println("增加图书方法。。。");
    }
}
