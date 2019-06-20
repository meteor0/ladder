package com.liuxin.ladder.designpatterns.factory.abstractfactory;

import com.liuxin.ladder.designpatterns.factory.abstractfactory.bean.Login;
import com.liuxin.ladder.designpatterns.factory.abstractfactory.bean.User;
import com.liuxin.ladder.designpatterns.factory.abstractfactory.factory.OracleFactory;
import com.liuxin.ladder.designpatterns.factory.abstractfactory.factory.SqlFactory;
import com.liuxin.ladder.designpatterns.factory.abstractfactory.product.ILogin;
import com.liuxin.ladder.designpatterns.factory.abstractfactory.product.IUser;

public class Test {
    public static void main(String[] args) throws Exception {
        User user=new User();
        Login login = new Login();

        // 只需要确定实例化哪一个数据库访问对象给factory
        // IFactory factory=new MysqlFactory();
        SqlFactory factory=new OracleFactory();

        // 已与具体的数据库访问解除了耦合
        IUser userOperation=factory.createUser();

        userOperation.getUser(1);
        userOperation.insert(user);

        // 已与具体的数据库访问解除了耦合
        ILogin loginOperation=factory.createLogin();

        loginOperation.insert(login);
        loginOperation.getLogin(1);
    }
}
