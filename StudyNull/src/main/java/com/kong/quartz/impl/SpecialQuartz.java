package com.kong.quartz.impl;

import com.kong.quartz.QuartzBase;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SpecialQuartz extends QuartzBase {
    public static final String cron = "0/1 * * * * ?";
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("this is a specialQuartz");
    }

    @Override
    public void start() {
        super.settingParam(cron);
    }
}
