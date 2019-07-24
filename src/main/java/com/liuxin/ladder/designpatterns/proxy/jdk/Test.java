package com.liuxin.ladder.designpatterns.proxy.jdk;

public class Test {
    public static void main(String[] args) {
        BookFacadeImpl bookFacadeImpl=new BookFacadeImpl();
        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacade bookfacade = (BookFacade) proxy.newProxy(bookFacadeImpl);
        bookfacade.addBook();
        BookFacadeImpl2 bookFacadeImpl2=new BookFacadeImpl2();
        BookFacade bookfacade2 = (BookFacade) proxy.newProxy(bookFacadeImpl2);
        bookfacade2.addBook();
    }
}
