package com.liuxin.ladder.designpatterns.proxy.dynamic;

public class Test {
    public static void main(String[] args) {
        BookFacadeImpl bookFacadeImpl=new BookFacadeImpl();
        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacade bookfacade = (BookFacade) proxy.bind(bookFacadeImpl);
        bookfacade.addBook();
    }
}
