package com.gabriel.myframe.common;

import com.google.common.collect.Lists;

public class Main {
    public static void main(String[] args) {
        SqlInfo sqlInfo = new SqlInfo(Lists.newArrayList(), Lists.newLinkedList());
        System.out.println(sqlInfo);
    }
}
