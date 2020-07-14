package com.kong.quartz;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzDemo implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("hello world");
    }

    public static void main(String[] args){
        QuartzManager.getInstance().doSchedulerAction();
    }

    public void settingParam(){
        try {
            String jobName = this.getClass().getName() + "Job";
            String triigetName = this.getClass().getName() + "Trigger";

            //定时器对象
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //定义一个工作对象 设置工作名称与组名
            JobDetail job =JobBuilder.newJob(QuartzDemo.class).withIdentity(jobName,"normalGroup").build();
            //定义一个任务调度的Trigger 设置工作名称与组名 每天的24:00触发一次
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triigetName,"normalGroup").withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?")).build();
            //设置工作 与触发器
            scheduler.scheduleJob(job, trigger);
            //开始定时任务
            scheduler.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


