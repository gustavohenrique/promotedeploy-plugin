package org.jenkinsci.plugins.promotedeploy.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.kohsuke.stapler.DataBoundConstructor;

public class Configuration implements Serializable {

    private static final long serialVersionUID = 7995734168148388259L;
    
    private String labelId;
    
    private String labelDesc;
    
    private List<Environment> environments = new ArrayList<Environment>();
    
    public Configuration() {}
    
    @DataBoundConstructor
    public Configuration(String labelId, String labelDesc, List<Environment> environments) {
        this.labelId = labelId;
        this.labelDesc = labelDesc;
        this.environments = environments;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public String getLabelDesc() {
        return labelDesc;
    }

    public void setLabelDesc(String labelDesc) {
        this.labelDesc = labelDesc;
    }

    public List<Environment> getEnvironments() {
        return environments;
    }

    public void setEnvironments(List<Environment> environments) {
        this.environments = environments;
    }
    
}
