package com.liuxin.ladder.jdk8.lambda;

/**
 * @description: Lambda 访问外部变量及接口默认方法 --访问局部变量
 * @author: liuxin79
 * @date: 2019-08-01 15:57
 */
public class Lambda3 {

    // 转换器
    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        /**num 变量必须为隐式的 final 类型，何为隐式的 final 呢？就是说到编译期为止，num 对象是不能被改变的,
         *如果lambda 表达式内部改变 num 值同样编译不通过
         */
        int num = 1;
        Converter<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num);
        String convert = stringConverter.convert(2);

        System.out.println(convert);
    }
}
