package com.liuxin.ladder.designpatterns.decorator;

import java.util.List;

/**
 * @description:
 * @author: liuxin79
 * @date: 2019-07-10 17:32
 */
public class DecoratorNews implements  ComponentNews {
    private ComponentNews componentNews;

    public DecoratorNews(ComponentNews componentNews) {
        this.componentNews = componentNews;
    }

    @Override
    public List<News> getListOfNews() {
        return componentNews.getListOfNews();
    }
}
