package com.kong.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public abstract class QuartzBase implements Job {
    @Override
    public abstract void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException;

    public void settingParam(String cron){
        try {
            String jobName = this.getClass().getName() + "Job";
            String triigetName = this.getClass().getName() + "Trigger";

            //定时器对象
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //定义一个工作对象 设置工作名称与组名
            JobDetail job =JobBuilder.newJob(this.getClass()).withIdentity(jobName,"normalGroup").build();
            //定义一个任务调度的Trigger 设置工作名称与组名 每天的24:00触发一次
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triigetName,"normalGroup").withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
            //设置工作 与触发器
            scheduler.scheduleJob(job, trigger);
            //开始定时任务
            scheduler.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public abstract void start();
}
