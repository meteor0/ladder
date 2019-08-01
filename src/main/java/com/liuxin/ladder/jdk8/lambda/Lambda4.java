package com.liuxin.ladder.jdk8.lambda;

/**
 * @description: 访问成员变量和静态变量
 * @author: liuxin79
 * @date: 2019-08-01 15:57
 */
public class Lambda4 {

    //局部变量相比，在 Lambda 表达式中对成员变量和静态变量拥有读写权限
    static int outerStaticNum;

    int outerNum;

    void testScopes() {
        Lambda2.Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 13;
            return String.valueOf(from);
        };

        Lambda2.Converter<Integer, String> stringConverter2 = (from) -> {
            // 对静态变量赋值
            outerStaticNum = 72;
            return String.valueOf(from);
        };

        String[] array = new String[1];
        Lambda2.Converter<Integer, String> stringConverter3 = (from) -> {
            array[0] = "Hi there";
            return String.valueOf(from);
        };
        String convert = stringConverter3.convert(23);

        System.out.println("outerNum: "+outerNum);
        System.out.println("outerStaticNum: "+outerStaticNum);
        System.out.println(convert);
        System.out.println(array[0]);
    }

    public static void main(String[] args) {
        new Lambda4().testScopes();
    }
}
