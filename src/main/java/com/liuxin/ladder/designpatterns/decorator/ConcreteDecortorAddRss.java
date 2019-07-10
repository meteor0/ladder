package com.liuxin.ladder.designpatterns.decorator;

import java.util.List;

/**
 * @description:
 * @author: liuxin79
 * @date: 2019-07-10 17:34
 */
public class ConcreteDecortorAddRss extends DecoratorNews {

    private void addRss(){
        System.out.println("新闻标题已经加入到RSS中");
    }
    public ConcreteDecortorAddRss(ComponentNews componentNews) {
        super(componentNews);
    }

    @Override
    public List<News> getListOfNews() {
        addRss();
        return super.getListOfNews();
    }
}
