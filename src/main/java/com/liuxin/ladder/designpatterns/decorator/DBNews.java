package com.liuxin.ladder.designpatterns.decorator;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: liuxin79
 * @date: 2019-07-10 17:29
 */
public class DBNews implements ComponentNews {
    @Override
    public List<News> getListOfNews() {
        List<News> list = new ArrayList<>();

        System.out.println("我是DB news");
        return list;
    }
}
