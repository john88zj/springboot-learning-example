package com.cherrys.platform.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author zhujun
 * @Email
 * @Date 2021/2/1  3:44 PM
 * @Version
 **/
@Configuration
@ConfigurationProperties(prefix = "user")
@Data
public class User {

	private int id;
	
	private String name;
	
	private String phone;
	
	@Override
	public String toString() {
		return "TUser{" +
				"id=" + id +
				", name='" + name + '\'' +
				", phone='" + phone + '\'' +
				'}';
	}
}
