package com.cherrys.platform.core.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.cherrys.platform.core.config.People;
import com.cherrys.platform.core.config.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @Description
 * @Author zhujun
 * @Email
 * @Date 2021/2/1  2:58 PM
 * @Version
 **/
@Controller
@RequestMapping("config")
public class ConfigController {

	@NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
	private boolean useLocalCache;
	
	@Resource
	private People people;
	
	@Resource
	private User user;
	
	@RequestMapping(value = "/get", method = GET)
	@ResponseBody
	public boolean get() {
//		return false;
		return useLocalCache;
	}
	
	
	@RequestMapping(value = "/getPeople", method = GET)
	@ResponseBody
	public String getPeople() {
		return people.toString();
	}
	
	
	@RequestMapping(value = "/getUser", method = GET)
	@ResponseBody
	public String getUser() {
		return user.toString();
	}
	
	
}
