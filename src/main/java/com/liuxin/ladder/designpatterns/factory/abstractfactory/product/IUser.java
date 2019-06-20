package com.liuxin.ladder.designpatterns.factory.abstractfactory.product;

import com.liuxin.ladder.designpatterns.factory.abstractfactory.bean.User;

public interface IUser {
     void insert(User user);
     User getUser(int uid);
}
