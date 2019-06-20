package com.liuxin.ladder.designpatterns.factory.abstractfactory.factory;

import com.liuxin.ladder.designpatterns.factory.abstractfactory.product.ILogin;
import com.liuxin.ladder.designpatterns.factory.abstractfactory.product.IUser;

public interface SqlFactory {

     IUser createUser();
     ILogin createLogin();

}
