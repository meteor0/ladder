package com.liuxin.ladder.designpatterns.decorator;

import java.math.BigDecimal;

/**
 * @description: 定义一个顶层接口,喝奶茶  具有两个功能 这杯奶茶花了多少钱 用了什么材料
 * @author: liuxin79
 * @date: 2019-07-12 11:40
 */
public interface DrinkMilkTea {


    /**花了多少钱*/
    BigDecimal getTotalPrice();


    /**用了什么材料*/
    String useMeterial();


}
