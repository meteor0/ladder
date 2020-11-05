package com.liuxin.ladder.reflect;

import cn.hutool.core.util.ReflectUtil;
import com.liuxin.ladder.service.impl.ServiceTestImpl;

/**
 * @description:
 * @author: liuxin79
 * @date: 2020-11-05 14:30
 */
public class ReflectTest {
    public static void main(String[] args) {
        try {
            ServiceTestImpl reflectTest = new ServiceTestImpl();
            Object invoke = ReflectUtil.invoke(reflectTest, "queryCode", "aaa");
            System.out.println("invoke="+invoke);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("bbb");
        }
    }
}
