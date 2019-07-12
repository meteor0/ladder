package com.liuxin.ladder.designpatterns.decorator;

/**
 * @description:
 * @author: liuxin79
 * @date: 2019-07-12 11:48
 */
public class Test {
    public static void main(String[] args) {
        DrinkMilkTea drinkMilkTea = new GarameMilkTea();
        System.out.println(drinkMilkTea.useMeterial()+"花了:"+drinkMilkTea.getTotalPrice()+"元");

        drinkMilkTea = new Pudding(drinkMilkTea);
        System.out.println(drinkMilkTea.useMeterial()+"花了:"+drinkMilkTea.getTotalPrice()+"元");

        drinkMilkTea = new HighlandBarley(drinkMilkTea);
        System.out.println(drinkMilkTea.useMeterial()+"花了:"+drinkMilkTea.getTotalPrice()+"元");


        drinkMilkTea = new HighlandBarley(drinkMilkTea);
        System.out.println(drinkMilkTea.useMeterial()+"再加一份青稞花了:"+drinkMilkTea.getTotalPrice()+"元");
    }
}
