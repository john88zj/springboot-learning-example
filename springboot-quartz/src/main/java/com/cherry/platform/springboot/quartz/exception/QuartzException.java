package com.cherry.platform.springboot.quartz.exception;

import org.quartz.SchedulerException;

/**
 * @Description
 * @Author zhujun
 * @Email
 * @Date 2020/12/14  4:11 PM
 * @Version
 **/
public class QuartzException extends Exception {
	
	public QuartzException() {
		super();
	}
	
	public QuartzException(String message) {
		super(message);
	}
	
	public QuartzException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public QuartzException(Throwable cause) {
		super(cause);
	}
	
	

}
