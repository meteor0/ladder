package com.liuxin.ladder.designpatterns.decorator;

import java.math.BigDecimal;

/**
 * @description:
 * @author: liuxin79
 * @date: 2019-07-12 11:48
 */
public class HighlandBarley extends MilkTeaMeterial {
    public HighlandBarley(DrinkMilkTea drinkMilkTea) {
        super(drinkMilkTea);
    }

    public BigDecimal getTotalPrice() {
        return super.getTotalPrice().add(new BigDecimal(3));
    }

    public String useMeterial() {
        return super.useMeterial()+"添加:青稞";
    }
}
