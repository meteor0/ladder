package com.liuxin.ladder.designpatterns.proxy.staticp;

public class Intermediary implements  Home {

    private Custom custom;

    public Intermediary(Custom custom){
        this.custom = new Custom();
    }

    @Override
    public void selectHome() {
        before();
        custom.selectHome();
        after();
    }

    private void before(){
        System.out.println("中介找房源");
    }

    private void after(){
        System.out.println("签合同");
    }
}
