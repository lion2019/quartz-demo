package com.example.demo;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "quartz")
public class QuartzController {
    private static final Logger log = LoggerFactory.getLogger(QuartzController.class);

    @Autowired
    private Scheduler scheduler;

    @PostMapping("add")
    public void addJob(@RequestBody TaskInfo taskInfo) throws ClassNotFoundException, SchedulerException {
        Class cls = Class.forName(taskInfo.getTaskClassName());
        JobDetail jobDetail = JobBuilder.newJob(cls)
                .withIdentity("task-"+taskInfo.getTaskName())
                .usingJobData("cmd", "1").build();

        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger-"+taskInfo.getTaskName())
                .withDescription("trigger1")
                .withSchedule(CronScheduleBuilder.cronSchedule(taskInfo.getTaskCron()))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }

    @GetMapping("standby")
    public void standby() throws SchedulerException {
        scheduler.standby();
    }

    @GetMapping("start")
    public void start() throws SchedulerException {
        scheduler.start();
    }

    @PostMapping("pause")
    public void pause(@RequestBody TaskInfo taskInfo) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey("task-"+taskInfo.getTaskName()));
    }

    @GetMapping("pauseAll")
    public void pauseAll() throws SchedulerException {
        scheduler.pauseAll();
    }

    @PostMapping("resume")
    public void resume(@RequestBody TaskInfo taskInfo) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey("task-"+taskInfo.getTaskName()));
    }

    @DeleteMapping("delete")
    public void delete(@RequestBody TaskInfo taskInfo) throws SchedulerException {
        scheduler.deleteJob(JobKey.jobKey("task-"+taskInfo.getTaskName()));
    }

    @GetMapping("shutdown")
    public void shutdown() throws SchedulerException {
        scheduler.shutdown(true);
    }

    @GetMapping("list")
    public List<TaskInfo> list() throws SchedulerException {
        log.debug("scheduler isStarted {}",scheduler.isStarted());
        List<TaskInfo> list = new ArrayList<>();
//        scheduler.getCurrentlyExecutingJobs().forEach(o->{
//            list.add(new TaskInfo(
//                    o.getJobDetail().getKey().getName(),
//                    o.getJobDetail().getKey().getGroup(),
//                    o.getJobDetail().getJobClass().getName(),
//                    o.getPreviousFireTime().toString(),
//                    o.getNextFireTime().toString()
//            ));
//        });

//        Set<JobKey> jobKeys1 = scheduler.getJobKeys(GroupMatcher.anyJobGroup());

//        for (String s:scheduler.getJobGroupNames()){
//            Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(s));
//        }
        for(JobKey jobKey : scheduler.getJobKeys(GroupMatcher.anyJobGroup())){
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            for(Trigger trigger : scheduler.getTriggersOfJob(jobKey)){
                list.add(new TaskInfo(jobKey.getName()
                        ,jobKey.getGroup()
                        ,jobDetail.getJobClass().getName()
                        , (trigger instanceof CronTrigger)
                        ?((CronTrigger)trigger).getCronExpression():""
//                        , Optional.ofNullable(trigger.getPreviousFireTime())
//                        .map(Date::toString).orElse("")
//                        .flatMap(o->Optional.of(o.toString())).orElse("")
                        ,trigger.getNextFireTime().toString()));
            }
        }
        return list;
    }

    @PatchMapping("updateTrigger")
    public void setTrigger(@RequestBody TaskInfo taskInfo) throws SchedulerException {
        CronTrigger trigger1 = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(taskInfo.getTaskCron()))
                .build();
        scheduler.rescheduleJob(TriggerKey.triggerKey("trigger-"+taskInfo.getTaskName()), trigger1);
    }
}
