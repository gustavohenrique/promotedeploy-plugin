package org.jenkinsci.plugins.promotedeploy.actions;

import hudson.model.Action;
import hudson.model.AbstractProject;
import hudson.model.Run;

import java.util.ArrayList;
import java.util.List;

import jenkins.model.Jenkins;

import org.jenkinsci.plugins.promotedeploy.helpers.DeployHelper;
import org.jenkinsci.plugins.promotedeploy.models.Configuration;
import org.jenkinsci.plugins.promotedeploy.models.Deploy;

public class ProjectAction implements Action {

    private AbstractProject project;
    

    public ProjectAction(AbstractProject project) {
        this.project = project;
    }

    public String getIconFileName() {
        return null;
    }

    public String getDisplayName() {
        return "";
    }

    public String getUrlName() {
        return "";
    }
    
    public List<Deploy> getDeploys() {
        List<Deploy> deploys = new ArrayList<Deploy>();
        List<Run> builds = project.getBuilds();
        for (Run build : builds) {
            BuildAction action = build.getAction(BuildAction.class);
            Deploy deploy = action.getLastDeploy();
            if (deploy.getEnvironment() != null) {
                deploys.add(deploy);
            }
        }
        DeployHelper.orderByDateDesc(deploys);
        return deploys;
    }
    
    public Configuration getConfiguration() {
        return Jenkins.getInstance().getPlugin(PromoteDeployPlugin.class).getConfiguration();
    }

}
