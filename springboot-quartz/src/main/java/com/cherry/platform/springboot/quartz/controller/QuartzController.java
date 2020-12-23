package com.cherry.platform.springboot.quartz.controller;

import com.cherry.platform.springboot.quartz.exception.QuartzException;
import com.cherry.platform.springboot.quartz.task.TaskOne;
import com.cherry.platform.springboot.quartz.util.QuartzJobUtil;
import com.cherry.platform.springboot.quartz.vo.QuartzJob;
import com.cherry.platform.springboot.quartz.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Description 定时任务控制
 * @Author zhujun
 * @Email
 * @Date 2020/12/14  11:00 AM
 * @Version
 **/
@Controller
@RequestMapping("/quartz")
@Slf4j
public class QuartzController {
	
	@Autowired
	private QuartzJobUtil quartzJobUtil;
	
	
	
	/**
	 * 列表
	 * @return
	 */
	@RequestMapping("list")
	public String list(Model model, HttpServletRequest request) {
		model.addAttribute("data","springboot Quartz");
		model.addAttribute("date",new Date());
		model.addAttribute("dateStr","2020-03-18 15:45:54");
		
		List<Map<String, Object>> jobList = null;
		
		try {
			jobList = quartzJobUtil.getAllJob();
			
		} catch (QuartzException e) {
			e.printStackTrace();
		}
		model.addAttribute("jobList",jobList);
//		request.getSession().setAttribute("people","jime");
		
		return "index";
	}
	
	
	
	/**
	 * 新增页面
	 * @return
	 */
	@RequestMapping("create")
	public String create(Model model, HttpServletRequest request) {
		log.info("====quartz::create,{}","start");
		
		return "create";
	}
	
	
	/**
	 * 列表
	 * @return
	 */
	@RequestMapping("edit")
	public String edit(Model model, HttpServletRequest request,
					   String jobName,String jobGroupName) {
		log.info("====quartz::edit,jobName:{},jobGroupName:{}",jobName,jobGroupName);
		QuartzJob jobDetail = quartzJobUtil.getDetail(jobName,jobGroupName);
		model.addAttribute("detail",jobDetail);
		return "edit";
	}
	
	/**
	 * 列表
	 * @return
	 */
	@RequestMapping("getAll")
	@ResponseBody
	public Result getAll() {
		log.info("====quartz::getAll, {}", "start");
		Result ret = null;
		List<Map<String, Object>> jobList = null;
		try {
			jobList = quartzJobUtil.getAllJob();
			ret = Result.sucess();
			ret.setObj(jobList);
		} catch (QuartzException e) {
			e.printStackTrace();
			ret = Result.fail();
		}
		log.info("====quartz::getAll, {}", "end");
		return ret;
	}
	
	
	/**
	 * 新增任务
	 * @return
	 */
	@RequestMapping("addJob")
	@ResponseBody
	public Result addJob(String className,
						 String jobName,
						 String jobGroupName,
						 String description,
						 String cronExpression) {
		log.info("====quartz::addJob, className:{},jobName:{},jobGroupName:{}, description:{},cronExpression:{}",
				className,jobName,jobGroupName,description,cronExpression);
		Boolean isValid = CronExpression.isValidExpression(cronExpression);
		if(!isValid){
			return Result.fail("表达式不合法");
		}
		try {
			Class clz = Class.forName(className);
			quartzJobUtil.addJob(clz, jobName, jobGroupName,description, cronExpression);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.fail();
		}
		return Result.sucess();
	}
	
	
	/**
	 * 编辑保存
	 * @param jobName
	 * @param jobGroupName
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public Result save(String jobName, String jobGroupName,String cronExpression,String description) {
		log.info("====quartz::save, {}, jobName:{},jobGroupName:{},cronExpression:{},description:{}", "start",
				jobName, jobGroupName,cronExpression,description);
		try {
			quartzJobUtil.updateJob(jobName, jobGroupName,cronExpression,description);
		} catch (QuartzException e) {
			e.printStackTrace();
		}
		log.info("====quartz::save, {}, jobName:{},jobGroupName:{},cronExpression:{}", "end",
				jobName, jobGroupName,cronExpression);
		return Result.sucess();
	}
	
	
	
	/**
	 * 删除任务
	 * @return
	 */
	@RequestMapping("delJob")
	@ResponseBody
	public Result delJob(String jobName,String jobGroupName) {
		log.info("====quartz::delJob, {}, jobName:{},jobGroupName:{}", "start", jobName, jobGroupName);
//		String jobName = "taskOne";
//		String jobGroupName = "group1";
		try {
			quartzJobUtil.deleteJob(jobName, jobGroupName);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.fail();
		}
		log.info("====quartz::delJob, {}, jobName:{},jobGroupName:{}", "end", jobName, jobGroupName);
		return Result.sucess();
	}
	
	
	/**
	 * 恢复任务
	 * @param jobName
	 * @param jobGroupName
	 * @return
	 */
	@RequestMapping("resume")
	@ResponseBody
	public Result resume(String jobName, String jobGroupName) {
		log.info("====quartz::resume, {}, jobName:{},jobGroupName:{}", "start", jobName, jobGroupName);
		try {
			quartzJobUtil.resumeJob(jobName, jobGroupName);
		} catch (QuartzException e) {
			e.printStackTrace();
		}
		log.info("====quartz::resume, {}, jobName:{},jobGroupName:{}", "end", jobName, jobGroupName);
		return Result.sucess();
	}
	
	/**
	 * 任务挂起
	 * @param jobName
	 * @param jobGroupName
	 * @return
	 */
	@RequestMapping("pause")
	@ResponseBody
	public Result pause(String jobName,String jobGroupName) {
		log.info("====quartz::pause, {}, jobName:{},jobGroupName:{}", "start", jobName, jobGroupName);
		try {
			quartzJobUtil.pauseJob(jobName, jobGroupName);
		} catch (QuartzException e) {
			e.printStackTrace();
		}
		log.info("====quartz::pause, {}, jobName:{},jobGroupName:{}", "end", jobName, jobGroupName);
		return Result.sucess();
	}
	
	
}
