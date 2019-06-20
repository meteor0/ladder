package com.liuxin.ladder.designpatterns.factory.abstractfactory.product;

import com.liuxin.ladder.designpatterns.factory.abstractfactory.bean.User;

public class MysqlUser implements IUser{
    @Override
    public void insert(User user) {
        System.out.println("在mysql中的user表中插入一条元素");
    }

    @Override
    public User getUser(int uid) {
        System.out.println("在mysql中的user表得到id为"+uid+"的一条数据");
        return null;
    }
}
