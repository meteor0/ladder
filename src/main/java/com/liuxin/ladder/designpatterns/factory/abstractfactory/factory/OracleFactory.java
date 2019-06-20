package com.liuxin.ladder.designpatterns.factory.abstractfactory.factory;

import com.liuxin.ladder.designpatterns.factory.abstractfactory.product.ILogin;
import com.liuxin.ladder.designpatterns.factory.abstractfactory.product.IUser;
import com.liuxin.ladder.designpatterns.factory.abstractfactory.product.OracleLogin;
import com.liuxin.ladder.designpatterns.factory.abstractfactory.product.OracleUser;

public class OracleFactory implements SqlFactory {
    @Override
    public ILogin createLogin() {
        return new OracleLogin();
    }

    @Override
    public IUser createUser() {
        return new OracleUser();
    }
}
