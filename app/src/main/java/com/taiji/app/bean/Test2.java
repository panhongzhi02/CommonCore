package com.taiji.app.bean;

/**
 * 描述：
 * 创建人： panho
 * 创建时间： 2016-11-24
 */

public class Test2 {

    private String name;

    private String activityName;

    public Test2(String name, String activityName) {
        this.name = name;
        this.activityName = activityName;
    }

    public Test2() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    @Override
    public String toString() {
        return "Test2{" +
                "name='" + name + '\'' +
                ", activityName='" + activityName + '\'' +
                '}';
    }
}
