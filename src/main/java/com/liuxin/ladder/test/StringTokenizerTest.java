package com.liuxin.ladder.test;

import java.util.StringTokenizer;

/**
 * @description:
 * @author: liuxin79
 * @date: 2020-05-26 16:16
 */
public class StringTokenizerTest {

    public static void main(String[] args) {
        StringTokenizer tokenizer = new StringTokenizer("  阿萨,德",",");

        while(tokenizer.hasMoreTokens()) {
            System.out.println(tokenizer.nextToken());
        }
    }
}
