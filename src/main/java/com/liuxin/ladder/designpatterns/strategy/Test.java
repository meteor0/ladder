package com.liuxin.ladder.designpatterns.strategy;


public class Test {
    public static void main(String[] args) {
         Context context;

        context = new Context(new ConcreteStrategyA());
        context.contextInterface();


        context = new Context(new ConcreteStrategyB());
        context.contextInterface();


        context = new Context(new ConcreteStrategyC());
        context.contextInterface();

        /**结合简单工厂模式*/
        ContextFactory contextFactory =   new ContextFactory("A");
        contextFactory.contextInterface();
    }
}
