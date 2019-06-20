package com.liuxin.ladder.designpatterns.factory.abstractfactory.product;

import com.liuxin.ladder.designpatterns.factory.abstractfactory.bean.Login;

public interface ILogin {

     void insert(Login login);
     Login getLogin(int id);
}
