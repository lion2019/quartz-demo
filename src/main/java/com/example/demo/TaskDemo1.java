package com.example.demo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskDemo1 implements Job {
    public static final Logger log = LoggerFactory.getLogger(TaskDemo1.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.debug("task demo1 scheduled run time {}, actual time {}, args:{}"
                ,context.getScheduledFireTime()
                ,context.getFireTime()
                ,context.getJobDetail().getJobDataMap().get("cmd"));
    }
}
