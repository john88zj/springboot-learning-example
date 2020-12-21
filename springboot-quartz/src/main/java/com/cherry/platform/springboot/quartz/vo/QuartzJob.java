package com.cherry.platform.springboot.quartz.vo;

import lombok.Data;

import java.util.Map;

/**
 * @Description
 * @Author zhujun
 * @Email
 * @Date 2020/12/14  1:50 PM
 * @Version
 **/
@Data
public class QuartzJob {
	
	private Class clazz;
	
	private String jobName;
	
	private String jobGroupName;
	
	private String cronExpression;
	
	private Map<String, Object> argMap;
	
}
