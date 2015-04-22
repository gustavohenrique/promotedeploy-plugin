package org.jenkinsci.plugins.promotedeploy.models;

import java.io.Serializable;

import org.kohsuke.stapler.DataBoundConstructor;

public class Environment implements Serializable {

    private static final long serialVersionUID = -6935937240509408059L;

    private String name;
    
    private String icon;
    
    public Environment() {
        this("", "");
    }

    @DataBoundConstructor
    public Environment(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    
}
