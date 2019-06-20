package com.liuxin.ladder.designpatterns.factory.factorymode.factory;

import com.liuxin.ladder.designpatterns.factory.factorymode.Car;
import com.liuxin.ladder.designpatterns.factory.factorymode.HatchbackCar;

public class HatchbackFactory implements CarFactory {
    @Override
    public Car CreateCar() {
        return new HatchbackCar();
    }
}
