package com.cherry.platform.springboot.quartz;

import com.cherry.platform.springboot.quartz.task.TaskOne;
import com.cherry.platform.springboot.quartz.util.QuartzJobUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description 测试
 * @Author zhujun
 * @Email
 * @Date 2020/12/14  10:33 AM
 * @Version
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootQuartzApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuartzTest {
	
	
	@Autowired
	private QuartzJobUtil quartzJobUtil;
	
	@Test
	public void addJob() throws Exception{
		quartzJobUtil.deleteJob("我的job","task");
		quartzJobUtil.addJob(TaskOne.class,"我的job","task","0 * * * * ?","");
		Thread.sleep(100000);
	}
	
	@Test
	public void pauseJob() throws Exception{
		quartzJobUtil.resumeJob("我的job","task");
	}
}
