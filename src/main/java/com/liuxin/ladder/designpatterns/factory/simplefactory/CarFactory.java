package com.liuxin.ladder.designpatterns.factory.simplefactory;

public class CarFactory {

    public Car getCarType(String carType) throws Exception {

        switch (carType){
            case "jeepCar":
                return new JeepCar();
            case "hatchbackCar":
                return new HatchbackCar();
            case "sportCar":
                return new SportCar();
            default:
                throw new Exception("爱上一匹野马,可我的家里没有草原. 你走吧！");
        }
    }
}
