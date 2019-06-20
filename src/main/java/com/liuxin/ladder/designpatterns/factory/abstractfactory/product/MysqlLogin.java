package com.liuxin.ladder.designpatterns.factory.abstractfactory.product;

import com.liuxin.ladder.designpatterns.factory.abstractfactory.bean.Login;

public class MysqlLogin implements ILogin{
    @Override
    public void insert(Login login) {
        System.out.println("对 MySQL 里的 Login 表插入了一条数据");
    }

    @Override
    public Login getLogin(int id) {
        System.out.println("通过 uid 在 MySQL 里的 Login 表得到了一条数据");
        return null;
    }
}
