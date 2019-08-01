package com.liuxin.ladder.jdk8.lambda;

import sun.applet.Main;

/**
 * @description: 接口内允许添加默认实现的方法
 * @author: liuxin79
 * @date: 2019-08-01 11:16
 */
public class Interface {
    //定义一个公式接口
    interface  Formula{
        //计算
        double calculate(int a );

        // 求平方根
        // 通过 default 关键字这个新特性，可以非常方便地对之前的接口做拓展，而此接口的实现类不必做任何改动
        default double sqrt(int a){
            double sqrt = Math.sqrt(a);
            System.out.println("sqrt:"+sqrt);
           return sqrt;
        }
    }

    public static void main(String[] args) {
        //通过匿名对象实现了 Formula 接口,重写calculate方法
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                System.out.println("a+100:"+(a+100));
                return sqrt(a+100);
            }
        };
        formula.sqrt(100);
        formula.calculate(200);
    }
}
