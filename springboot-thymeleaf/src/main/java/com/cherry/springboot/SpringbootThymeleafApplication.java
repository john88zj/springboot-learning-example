package com.cherry.springboot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootThymeleafApplication {

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(SpringbootThymeleafApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);

    }

}
