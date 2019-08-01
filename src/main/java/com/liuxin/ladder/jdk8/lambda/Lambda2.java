package com.liuxin.ladder.jdk8.lambda;

import java.util.function.BiFunction;

/**
 * @description:
 * 1.函数式接口 Functional Interface
 * 2.便捷的引用类的构造器及方法
 * @author: liuxin79
 * @date: 2019-08-01 15:57
 */
public class Lambda2 {

    // 函数式接口（Functional Interface）就是只包含有且只有一个抽象方法的声明。针对该接口类型的所有 Lambda 表达式都会与这个抽象方法匹配。
    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }

    static class Something {
        String startsWith(String s) {
            return String.valueOf(s.charAt(0));
        }
    }

    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        /**
         * (from) -> Integer.valueOf(from)等价于重写Converter接口的Converter方法
            Converter converter1 = new Converter() {
            @Override
            public Object convert(Object from) {
                return Integer.valueOf(from.toString());
            }
        };
         */

       // Integer convert = converter.convert("123");

        /**
            Java 8 中允许你通过 :: 关键字来引用类的方法或构造器。
            意思为 使用Integer.valueOf("")重写 Converter接口的convert方法
         **/
        Converter<String, Integer> convert = Integer::valueOf;
        Integer converted = convert.convert("123");
        System.out.println(converted);

        //something::startsWith返回的函数式接口是自己选择的
        Something something = new Something();
        Converter<String, String> startsWith = something::startsWith;
        String convert2 = startsWith.convert("123");
        System.out.println(convert2);


        // Person::new直接引用 Person类构造器
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.println(person.firstName);

    }
}
