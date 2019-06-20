package com.liuxin.ladder.designpatterns.factory.factorymode.factory;

import com.liuxin.ladder.designpatterns.factory.factorymode.Car;
import com.liuxin.ladder.designpatterns.factory.factorymode.SportCar;

public class SportFactory implements CarFactory {
    @Override
    public Car CreateCar() {
        return new SportCar();
    }
}
