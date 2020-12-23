package com.cherry.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(value = {"com.cherry.springboot.servlet","com.cherry.springboot.filter"})
public class SpringbootServletApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootServletApplication.class, args);
    }

}
