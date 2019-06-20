package com.liuxin.ladder.designpatterns.factory.abstractfactory.factory;

import com.liuxin.ladder.designpatterns.factory.abstractfactory.product.ILogin;
import com.liuxin.ladder.designpatterns.factory.abstractfactory.product.IUser;
import com.liuxin.ladder.designpatterns.factory.abstractfactory.product.MysqlLogin;
import com.liuxin.ladder.designpatterns.factory.abstractfactory.product.MysqlUser;

public class MysqlFactory implements SqlFactory {
    @Override
    public ILogin createLogin() {
        return new MysqlLogin();
    }

    @Override
    public IUser createUser() {
        return new MysqlUser();
    }
}
