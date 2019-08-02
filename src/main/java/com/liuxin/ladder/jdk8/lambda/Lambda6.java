package com.liuxin.ladder.jdk8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @description:
 * @author: liuxin79
 * @date: 2019-08-01 15:57
 */
public class Lambda6 {

    //https://www.cnblogs.com/junjiang3/p/8998509.html
    //https://blog.csdn.net/chenhao_c_h/article/details/80691284

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        // name，age
        List<Person> list = Arrays.asList(
                // name，age
                new Person("张三", "11"),
                new Person("王五", "20"),
                new Person("王五", "91"),
                new Person("张三", "8"),
                new Person("李四", "44"),
                new Person("李四", "34"),
                new Person("李四", "44")
        );
        //forEach() 使用该方法迭代流中的每个数据
        System.out.println("******************************************forEach******************************************");
        list.stream().forEach(user -> System.out.println(user));
        //sorted() 使用该方法排序数据
        System.out.println("******************************************sorted******************************************");
        list.stream().sorted(Comparator.comparing(Person::getLastName)).forEach(user -> System.out.println(user));
        //limit()：使用该方法截断
        System.out.println("******************************************limit******************************************");
        list.stream().limit(3).forEach(user -> System.out.println(user));
        //skip()：与limit互斥，使用该方法跳过元素
        System.out.println("******************************************skip******************************************");
        list.stream().skip(3).forEach(user -> System.out.println(user));
        //distinct()：使用该方法去重，注意：必须重写对应泛型的hashCode()和equals()方法
        System.out.println("******************************************distinct******************************************");
        list.stream().distinct().forEach(user -> System.out.println(user));

       // list.stream().max()
    }
}
