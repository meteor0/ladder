package com.liuxin.ladder.designpatterns.decorator;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: liuxin79
 * @date: 2019-07-10 17:29
 */
public class XmlNews implements ComponentNews {
    @Override
    public List<News> getListOfNews() {
        List<News> list = new ArrayList<>();

        System.out.println("我是xml news");
        return list;
    }
}
