package com.liuxin.ladder.designpatterns.decorator;

import java.util.List;

/**
 * @description:
 * @author: liuxin79
 * @date: 2019-07-10 17:34
 */
public class ConcreteDecortorAddAmount extends DecoratorNews {

    public ConcreteDecortorAddAmount(ComponentNews componentNews) {
        super(componentNews);
    }

    private void addAmount() {
        System.out.println("新闻人气已经加一");
    }

    @Override
    public List<News> getListOfNews() {
        addAmount();
        return super.getListOfNews();
    }
}
