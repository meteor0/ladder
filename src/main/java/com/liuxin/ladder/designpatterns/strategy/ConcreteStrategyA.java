package com.liuxin.ladder.designpatterns.strategy;

public class ConcreteStrategyA implements Strategy {
    @Override
    public void strategyInterface() {
        System.out.println("我是策略A");
    }
}
