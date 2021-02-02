package com.cherrrys.platform.core;

import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableNacosDiscovery
//@EnableDiscoveryClient
public class NacosRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosRegisterApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
