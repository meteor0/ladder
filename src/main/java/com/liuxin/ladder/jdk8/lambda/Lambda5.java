package com.liuxin.ladder.jdk8.lambda;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * @description: 内置的函数式接口
 * @author: liuxin79
 * @date: 2019-08-01 15:57
 */
public class Lambda5 {
    @FunctionalInterface
    interface Fun {
        void foo();
    }



    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        /**Predicate 断言
         * negate 相反
         * and or 断言组合
         */
        Predicate<String> predicate1 = (s) -> s.length() > 0;
        Predicate<String> predicate2 = (s) -> s.length() > 2;
        Predicate<String> predicate3 = predicate2.and(predicate2);

        boolean foo1 = predicate1.test("foo");
        boolean foo2 = predicate1.negate().test("foo");
        boolean foo3 = predicate3.test("1234");

        System.out.println(foo1);
        System.out.println(foo2);
        System.out.println(foo3);

        Predicate<Boolean> nonNull = Objects::nonNull;
        boolean test1 = nonNull.test(true);
        Predicate<String> nonNull2 = Objects::isNull;
        boolean test2 = nonNull2.test("12");
        Predicate<String> predicate = String::isEmpty;
        Predicate<String> test3 = predicate.negate();
        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3);

    }
}
