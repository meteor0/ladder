package com.liuxin.ladder.designpatterns.strategy;


/**策略模式与简单工厂模式结合*/
public class ContextFactory {
    private Strategy strategy;


    public ContextFactory(String type){
        switch (type){
            case "A":
                strategy = new ConcreteStrategyA();
                break;
            case "B":
                strategy = new ConcreteStrategyB();
                break;
            case "C":
                strategy = new ConcreteStrategyC();
                break;
        }
    }

    public void contextInterface(){
        strategy.strategyInterface();
    }
}
