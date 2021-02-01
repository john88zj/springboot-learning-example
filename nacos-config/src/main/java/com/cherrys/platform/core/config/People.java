package com.cherrys.platform.core.config;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Description
 * @Author zhujun
 * @Email
 * @Date 2021/2/1  3:44 PM
 * @Version
 **/
@ConfigurationProperties(prefix = "people")
@Configuration
@Data
public class People {

	private int id;
	
	private String name;
	
	private int age;
	
	@Override
	public String toString() {
		return "People{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
