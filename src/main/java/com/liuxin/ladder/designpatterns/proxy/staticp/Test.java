package com.liuxin.ladder.designpatterns.proxy.staticp;

public class Test {

    public static void main(String[] args) {
        Custom custom = new Custom();
        Intermediary intermediary = new Intermediary(custom);
        intermediary.selectHome();
    }
}
