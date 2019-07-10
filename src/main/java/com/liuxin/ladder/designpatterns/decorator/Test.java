package com.liuxin.ladder.designpatterns.decorator;

/**
 * @description:
 * @author: liuxin79
 * @date: 2019-07-10 17:38
 */
public class Test {

    public static void main(String[] args) {
        ComponentNews dbNews = new DBNews();
        DecoratorNews decoratorNews = new ConcreteDecortorAddAmount(dbNews);
       //decoratorNews = new ConcreteDecortorAddRss(decoratorNews);
        decoratorNews.getListOfNews();

    }
}
