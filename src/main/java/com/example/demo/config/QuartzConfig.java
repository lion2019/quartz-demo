package com.example.demo.config;

import com.example.demo.TaskDemo1;
import com.example.demo.TestJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

//    @Bean
//    public JobDetail testJob1(){
//        return JobBuilder.newJob(TaskDemo1.class)
//                .withIdentity("TaskDemo1")
//                .withDescription("TaskDemo1")
//                .usingJobData("cmd","1").build();
//    }


}
