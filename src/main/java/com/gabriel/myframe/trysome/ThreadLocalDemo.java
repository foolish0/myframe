package com.gabriel.myframe.trysome;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

public class ThreadLocalDemo {
    private static ThreadLocal<Map<String, Object>> local = new ThreadLocal<>();

    static void print(String str) {
        System.out.println(str + ":" + local.get());
//        local.remove();
    }

    public static void main(String[] args) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("Outer", Arrays.asList(1, 2, 3, 4, 5));
        local.set(param);
        print(Thread.currentThread().getName());

        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String, Object> param = Maps.newHashMap();
                param.put("Inner", Thread.currentThread().getName());
                local.set(param);
                print(Thread.currentThread().getName());
            }
        }, "thread0").start();

        print(Thread.currentThread().getName() + " -- after");
    }
}
