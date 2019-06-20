package com.liuxin.ladder.designpatterns.factory.simplefactory;

public class HatchbackCar implements  Car {

    public static final String HATCHBACKCAR ="hatchbackCar";

    @Override
    public void getCar() {
        System.out.println("我是大货车");
    }
}
