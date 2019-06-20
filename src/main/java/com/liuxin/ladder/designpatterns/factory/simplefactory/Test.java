package com.liuxin.ladder.designpatterns.factory.simplefactory;

public class Test {
    public static void main(String[] args) throws Exception {
        CarFactory carFactory = new CarFactory();
        Car car = carFactory.getCarType(SportCar.SPORTCAR);
        car.getCar();
    }
}
