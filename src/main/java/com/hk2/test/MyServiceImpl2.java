package com.hk2.test;

import org.jvnet.hk2.annotations.Service;

import javax.annotation.PostConstruct;

@Service
public class MyServiceImpl2 implements MyService {
    public static int count = 0;

    public MyServiceImpl2() {
        System.out.println(this.getClass().getName() + " " + count++);
    }

    @Override
    public void execute() {
        System.out.println(this.getClass().getName() + " " + count++);
        System.out.println(this.getClass().getName() + " executed");
    }

    @PostConstruct
    public void increment() {
        System.out.println(this.getClass().getName() + " incremented " + count++);
    }
}
