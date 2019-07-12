package com.liuxin.ladder.designpatterns.decorator;

import java.math.BigDecimal;

/**
 * @description: 定义焦糖奶茶 去实现顶层接口
 * @author: liuxin79
 * @date: 2019-07-12 11:44
 */
public class GarameMilkTea implements  DrinkMilkTea {
    @Override
    public BigDecimal getTotalPrice() {
        return new BigDecimal(16);
    }

    @Override
    public String useMeterial() {
        return "焦糖奶茶:";
    }
}
