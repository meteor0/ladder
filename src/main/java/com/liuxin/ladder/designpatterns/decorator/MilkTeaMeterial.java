package com.liuxin.ladder.designpatterns.decorator;

import java.math.BigDecimal;

/**
 * @description:
 * @author: liuxin79
 * @date: 2019-07-12 11:42
 */
public  abstract  class MilkTeaMeterial implements DrinkMilkTea {

    private DrinkMilkTea drinkMilkTea;


    public MilkTeaMeterial(DrinkMilkTea drinkMilkTea) {
        this.drinkMilkTea = drinkMilkTea;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return drinkMilkTea.getTotalPrice();
    }

    @Override
    public String useMeterial() {
        return drinkMilkTea.useMeterial();
    }
}
