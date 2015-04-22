package org.jenkinsci.plugins.promotedeploy.models;

import java.util.Calendar;

public class Deploy implements Comparable<Deploy> {

    private Environment environment;

    private String id;
    
    private String desc;
    
    private String username;
    
    private String version;
    
    private long timeInMillis;

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }
    
    public Calendar getDateTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        return calendar;
    }
    
    public String toString() {
        return id;
    }

    public int compareTo(Deploy d) {
        if (timeInMillis == d.getTimeInMillis()) {
            return 0;
        }
        return timeInMillis < d.getTimeInMillis() ? -1 : 1;
    }
    
}
