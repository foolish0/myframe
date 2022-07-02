package com.gabriel.myframe;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello";
    }
}
