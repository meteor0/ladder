package com.liuxin.ladder.designpatterns.factory.factorymode.factory;

import com.liuxin.ladder.designpatterns.factory.factorymode.Car;

/**抽象工厂*/
public interface CarFactory {
    Car CreateCar();
}
