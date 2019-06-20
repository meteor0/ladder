package com.liuxin.ladder.designpatterns.factory.factorymode;

import com.liuxin.ladder.designpatterns.factory.factorymode.factory.CarFactory;
import com.liuxin.ladder.designpatterns.factory.factorymode.factory.HatchbackFactory;

public class Test {
    public static void main(String[] args) throws Exception  {
        /**通过创建对象获取工厂类*/
        CarFactory hatchbackFactory = new HatchbackFactory();
        Car car1 = hatchbackFactory.CreateCar();
        car1.getCar();

        //通过反射获取工厂类
        CarFactory carFactory = (CarFactory) Class.forName("com.liuxin.ladder.designpatterns.factory.factorymode.factory.JeepFactory").newInstance();
        Car car = carFactory.CreateCar();
        car.getCar();
    }
}
