package com.liuxin.ladder.designpatterns.strategy;

/**策略模式的简单实现*/
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public void contextInterface(){
        strategy.strategyInterface();
    }
}
