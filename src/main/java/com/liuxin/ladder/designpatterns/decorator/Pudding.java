package com.liuxin.ladder.designpatterns.decorator;

import java.math.BigDecimal;

/**
 * @description:
 * @author: liuxin79
 * @date: 2019-07-12 11:45
 */
public class Pudding extends MilkTeaMeterial {
    public Pudding(DrinkMilkTea drinkMilkTea) {
        super(drinkMilkTea);
    }

    @Override
    public BigDecimal getTotalPrice() {
        return super.getTotalPrice().add(new BigDecimal(2));
    }

    @Override
    public String useMeterial() {
        return super.useMeterial()+"添加:布丁,";
    }
}
