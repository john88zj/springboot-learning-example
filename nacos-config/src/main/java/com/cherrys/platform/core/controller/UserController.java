package com.cherrys.platform.core.controller;

import com.cherrys.platform.core.domain.TUser;
import com.cherrys.platform.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author zhujun
 * @Email
 * @Date 2021/2/1  4:32 PM
 * @Version
 **/
@RestController
@RequestMapping(value = "user")
public class UserController {
	
	
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {this.userService = userService;}
	
	@GetMapping
	@ResponseBody
	public TUser get(@RequestParam int id) {
		return userService.findById(id);
	}

}
