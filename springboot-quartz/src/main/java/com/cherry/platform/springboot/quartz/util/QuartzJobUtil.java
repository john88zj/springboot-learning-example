package com.cherry.platform.springboot.quartz.util;

import com.cherry.platform.springboot.quartz.exception.QuartzException;
import com.cherry.platform.springboot.quartz.vo.QuartzJob;
import com.sun.tools.javac.util.StringUtils;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 *@Description quartz工具类
 *@Author   zhujun
 *@email   john88zj@163.com
 *@Date    2020/12/14 9:37 AM
 *@Version
 **/
@Component
public class QuartzJobUtil {

    private static final Logger logger = LoggerFactory.getLogger(QuartzJobUtil.class);

    private static QuartzJobUtil quartzJobUtil;

    @Autowired
    private Scheduler scheduler;
//
//    public QuartzJobUtil() {
//        logger.info("init jobUtil");
//        quartzJobUtil = this;
//    }
//
//    public static QuartzJobUtil getInstance() {
//        logger.info("return  JobCreateUtil");
//        return QuartzJobUtil.quartzJobUtil;
//    }
    
    /**
     * 创建 job
     * @param clazz
     * @param jobName
     * @param jobGroupName
     * @param cronExpression
     */
    public void addJob(Class clazz, String jobName, String jobGroupName,String description, String cronExpression) throws QuartzException {
        addJob(clazz, jobName, jobGroupName, cronExpression,description, null);
    }
    
    
    /**
     * 创建job
     * @param job
     */
    public void addJob(QuartzJob job) throws QuartzException {
        try {
            // 启动调度器
            scheduler.start();
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(((Job) job.getClazz().newInstance()).getClass()).withIdentity(job.getJobName(), job.getJobGroupName()).withDescription(job.getDescription()).build();
            //表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroupName()).withSchedule(scheduleBuilder).build();
            //获得JobDataMap，写入数据
            if (job.getArgMap() != null) {
                trigger.getJobDataMap().putAll(job.getArgMap());
            }
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new QuartzException(e);
        }
    }
    
    /**
     * 创建 job
     * @param clazz
     * @param jobName
     * @param jobGroupName
     * @param cronExpression
     * @param argMap
     */
    public void addJob(Class clazz, String jobName, String jobGroupName, String cronExpression,String description, Map<String, Object> argMap) throws QuartzException {
        try {
            // 启动调度器
            scheduler.start();
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(((Job) clazz.newInstance()).getClass()).withIdentity(jobName, jobGroupName).withDescription(description).build();
            //表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName).withSchedule(scheduleBuilder).withDescription(description).build();
            //获得JobDataMap，写入数据
            if (argMap != null) {
                trigger.getJobDataMap().putAll(argMap);
            }
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new QuartzException(e);
        }
    }
    
    /**
     * 暂停job
     * @param jobName
     * @param jobGroupName
     */
    public void pauseJob(String jobName, String jobGroupName) throws QuartzException{
        try {
            scheduler.pauseJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw  new QuartzException(e);
        }
    }
    
    /**
     * 恢复job
     * @param jobName
     * @param jobGroupName
     */
    public void resumeJob(String jobName, String jobGroupName) throws QuartzException {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new QuartzException(e);
        }
    }
    
    
    /**
     * 更新job频率
     * @param jobName
     * @param jobGroupName
     * @param cronExpression
     */
    public void updateJob(String jobName, String jobGroupName, String cronExpression,String description) throws QuartzException{
        updateJob(jobName, jobGroupName, cronExpression, description,null);
    }
    
    
    /**
     * 更新job频率和参数
     * @param jobName
     * @param jobGroupName
     * @param cronExpression
     * @param argMap
     */
    public void updateJob(String jobName, String jobGroupName, String cronExpression,String description, Map<String, Object> argMap) throws QuartzException {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).withDescription(description).build();
            //修改map
            if (argMap != null) {
                trigger.getJobDataMap().putAll(argMap);
            }
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new QuartzException(e);
        }
    }
    
    /**
     * 更新job参数
     * @param jobName
     * @param jobGroupName
     * @param argMap
     */
    public void updateJob(String jobName, String jobGroupName, Map<String, Object> argMap) throws QuartzException {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //修改map
            trigger.getJobDataMap().putAll(argMap);
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new QuartzException(e);
        }

    }
    
    
    /**
     * 删除job
     * @param jobName
     * @param jobGroupName
     */
    public void deleteJob(String jobName, String jobGroupName) throws QuartzException {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobName, jobGroupName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobName, jobGroupName));
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (Exception e) {
            e.printStackTrace();
            throw  new QuartzException(e);
        }
    }
    
    
    /**
     * 启动所有定时任务
     */
    public void startAllJobs() {
        try {
            scheduler.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 关闭所有定时任务
     */
    public void shutdownAllJobs() {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
    /**
     * 获取所有任务列表
     * @return
     */
    public List<Map<String, Object>> getAllJob() throws QuartzException {
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        List<Map<String, Object>> jobList = new ArrayList<>();
        Set<JobKey> jobKeys = null;
        try {
            jobKeys = scheduler.getJobKeys(matcher);
            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    Map<String, Object> job = new HashMap<>();
                    job.put("jobName", jobKey.getName());
                    job.put("jobGroupName", jobKey.getGroup());
                    job.put("trigger", trigger.getKey());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    job.put("jobStatus", triggerState.name());
                    job.put("description",trigger.getDescription());
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        job.put("cronExpression", cronExpression);
                    }
                    jobList.add(job);
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw  new QuartzException(e);
        }

        return jobList;
    }
    
    
    
    public QuartzJob getDetail(String jobName,String jobGroupName){
        QuartzJob ret = null;
        try {
            JobKey jobKey = new JobKey(jobName,jobGroupName);
            JobDetail detail =scheduler.getJobDetail(jobKey);
            if(null != detail){
                TriggerKey key = new TriggerKey(jobName,jobGroupName);
                Trigger trigger = scheduler.getTrigger(key);
                ret = new QuartzJob();
                ret.setJobName(detail.getKey().getName());
                ret.setJobGroupName(detail.getKey().getGroup());
                ret.setClazz(detail.getJobClass());
                ret.setDescription(detail.getDescription());
                if(trigger instanceof CronTrigger){
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    ret.setCronExpression(cronTrigger.getCronExpression());
                }
//                ret.setCronExpression(trigger.get);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return ret;
    }
    
    /**
     *  获取任务列表
     * @return
     */
    public List<Map<String, Object>> queryJob(String jobName,String jobGroupName,String cronExpression) throws QuartzException {

        //TODO  需要查询数据库表中数据
//        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
//        if(null != jobGroupName && "".equals(jobGroupName)){
//            matcher = GroupMatcher.jobGroupEquals(jobGroupName);
//        }
//        if(null != jobName && "".equals(jobName)){
//            matcher = (GroupMatcher<JobKey>) GroupMatcher.jobGroupEquals()
//        }
//
//        List<Map<String, Object>> jobList = new ArrayList<>();
//        Set<JobKey> jobKeys = null;
//        try {
//            jobKeys = scheduler.getJobKeys(matcher);
//            for (JobKey jobKey : jobKeys) {
//                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
//                for (Trigger trigger : triggers) {
//                    Map<String, Object> job = new HashMap<>();
//                    job.put("jobName", jobKey.getName());
//                    job.put("jobGroupName", jobKey.getGroup());
//                    job.put("trigger", trigger.getKey());
//                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
//                    job.put("jobStatus", triggerState.name());
//                    if (trigger instanceof CronTrigger) {
//                        CronTrigger cronTrigger = (CronTrigger) trigger;
//                        String cronExpression = cronTrigger.getCronExpression();
//                        job.put("cronExpression", cronExpression);
//                    }
//                    jobList.add(job);
//                }
//            }
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//            throw  new QuartzException(e);
//        }
//
//        return jobList;
        return null;
    }
}
