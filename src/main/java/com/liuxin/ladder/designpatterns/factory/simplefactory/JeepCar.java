package com.liuxin.ladder.designpatterns.factory.simplefactory;

public class JeepCar implements Car {

    public static final String JEEPCAR ="jeepCar";

    @Override
    public void getCar() {
        System.out.println("我是吉普车");
    }
}
