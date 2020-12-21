package com.cherry.platform.springboot.quartz.vo;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @Author zhujun
 * @Email
 * @Date 2020/12/14  11:03 AM
 * @Version
 **/
@Data
public class Result {
	
	private String code;
	
	private String message;
	
	private Object obj;
	
	
	public static Result fail(){
		return fail(null);
	}
	
	public static Result fail(String message){
		return result("0",message==null?"处理失败":message);
	}
	
	
	
	public static Result sucess(){
		return sucess(null);
	}
	
	public static Result sucess(String message){
		return result("1",message==null?"处理成功":message);
	}
	
	public static Result result(String code, String message){
		Result result = new Result();
		result.code = code;
		result.message = message;
		return result;
	}
	
	
}
