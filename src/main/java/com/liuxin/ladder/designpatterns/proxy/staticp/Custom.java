package com.liuxin.ladder.designpatterns.proxy.staticp;

/**客户类*/
 public class Custom implements  Home {
    @Override
    public void selectHome() {
        System.out.println("选择房子!!!");
    }
}
