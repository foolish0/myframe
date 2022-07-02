package com.gabriel.myframe;

import com.gabriel.myframe.common.SqlInfo;
import com.google.common.collect.Lists;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class MyframeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyframeApplication.class, args);
    }

}
