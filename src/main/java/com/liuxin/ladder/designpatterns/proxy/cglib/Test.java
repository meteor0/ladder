package com.liuxin.ladder.designpatterns.proxy.cglib;

public class Test {
    public static void main(String[] args) {
        HelloService helloService = new HelloService();
        UserServiceCglib userServiceCglib= new UserServiceCglib();
        HelloService instance = (HelloService)userServiceCglib.getInstance(helloService);
        instance.addUser();

    }
}
