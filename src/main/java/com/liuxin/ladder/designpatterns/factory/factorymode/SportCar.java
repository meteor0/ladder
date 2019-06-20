package com.liuxin.ladder.designpatterns.factory.factorymode;


public class SportCar implements Car {

    public static final String SPORTCAR ="sportCar";
    @Override
    public void getCar() {
        System.out.println("我是跑车!!!");
    }
}
