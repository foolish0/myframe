package com.gabriel.myframe;

import com.gabriel.myframe.common.SqlInfo;
import com.google.common.collect.Lists;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyframeApplication {

    public static void main(String[] args) {
        SqlInfo sqlInfo = new SqlInfo(Lists.newArrayList(), Lists.newLinkedList());
        System.out.println(sqlInfo);
        SpringApplication.run(MyframeApplication.class, args);
    }

}
