package com.liuxin.ladder.designpatterns.factory.factorymode.factory;

import com.liuxin.ladder.designpatterns.factory.factorymode.Car;
import com.liuxin.ladder.designpatterns.factory.factorymode.JeepCar;

public class JeepFactory implements CarFactory  {
    @Override
    public Car CreateCar() {
        return new JeepCar();
    }
}
