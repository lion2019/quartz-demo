package com.example.demo;

public class TaskInfo {

    private String taskName;
    private String taskGroup;
    private String taskClassName;
    private String taskCron;
    private String nextFireTime;

    public TaskInfo() {
    }

    public TaskInfo(String taskName, String taskGroup, String taskClassName, String taskCron, String nextFireTime) {
        this.taskName = taskName;
        this.taskGroup = taskGroup;
        this.taskClassName = taskClassName;
        this.taskCron = taskCron;
        this.nextFireTime = nextFireTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(String taskGroup) {
        this.taskGroup = taskGroup;
    }

    public String getTaskClassName() {
        return taskClassName;
    }

    public void setTaskClassName(String taskClassName) {
        this.taskClassName = taskClassName;
    }

    public String getTaskCron() {
        return taskCron;
    }

    public void setTaskCron(String taskCron) {
        this.taskCron = taskCron;
    }

    public String getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(String nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    @Override
    public String toString() {
        return "TaskInfo{" +
                "taskName='" + taskName + '\'' +
                ", taskGroup='" + taskGroup + '\'' +
                ", taskClassName='" + taskClassName + '\'' +
                ", taskCron='" + taskCron + '\'' +
                ", nextFireTime='" + nextFireTime + '\'' +
                '}';
    }
}
