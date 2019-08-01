package com.liuxin.ladder.jdk8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @description: Lambda 表达式
 * @author: liuxin79
 * @date: 2019-08-01 11:35
 */
public class Lambda1 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        //list排序方式1
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
        System.out.println(names);

        //list排序方式2
        Collections.sort(names, (String a, String b) -> {
            return a.compareTo(b);
        });

        System.out.println(names);
        //list排序方式3
        Collections.sort(names, (String a, String b) -> b.compareTo(a));

        //list排序方式4
        names.sort((a, b) -> b.compareTo(a));
        names.sort(Collections.reverseOrder());

        System.out.println(names);
    }
}
