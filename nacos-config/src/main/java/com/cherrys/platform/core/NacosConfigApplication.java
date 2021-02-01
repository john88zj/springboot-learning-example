package com.cherrys.platform.core;

import com.alibaba.nacos.api.config.annotation.NacosProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;

@SpringBootApplication
@NacosPropertySource(dataId = "nacos-test",autoRefreshed = true)
@NacosPropertySource(dataId = "test",autoRefreshed = true)
@NacosPropertySource(dataId = "mysql-test",autoRefreshed = true)
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class NacosConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosConfigApplication.class, args);
	}

}
