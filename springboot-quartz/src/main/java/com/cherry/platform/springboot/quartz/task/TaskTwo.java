package com.cherry.platform.springboot.quartz.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

///**
// * @author
// * @description
// * @date 2019-07-16 16:07
// */
//public class TaskOne extends QuartzJobBean {
//
//    private final Logger logger= LoggerFactory.getLogger(TaskOne.class);
//
//    @Autowired
////    private UserServiceImpl userService;userService
//
//    @Override
//    protected void executeInternal(JobExecutionContext jobExecutionContext) {
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        logger.info("用户列表：{}",new Gson().toJson(userService.getUserList()));
//        logger.info("任务1执行:{}",simpleDateFormat.format(new Date()));
//    }
//}

@Slf4j
public class TaskTwo implements Job{
    
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        logger.info("用户列表：{}",new Gson().toJson(userService.getUserList()));
        log.info("任务2执行:{}",simpleDateFormat.format(new Date()));
    }
}
