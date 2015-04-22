package org.jenkinsci.plugins.promotedeploy.actions;

import hudson.model.BuildBadgeAction;
import hudson.model.Job;
import hudson.model.Run;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;

import jenkins.model.Jenkins;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.jenkinsci.plugins.promotedeploy.helpers.BuildHelper;
import org.jenkinsci.plugins.promotedeploy.helpers.DeployHelper;
import org.jenkinsci.plugins.promotedeploy.models.Configuration;
import org.jenkinsci.plugins.promotedeploy.models.Deploy;
import org.jenkinsci.plugins.promotedeploy.models.Environment;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

public class BuildAction implements BuildBadgeAction {

    private Run build;
    
    private List<Deploy> deploys;

    public BuildAction(Run build) {
        this.build = build;
    }

    public String getIconFileName() {
        return null;
    }

    public String getDisplayName() {
        return "";
    }

    public String getUrlName() {
        return "promotedeploy";
    }
    
    public String getUsername() {
        String username = Jenkins.getAuthentication().getName();
        if (StringUtils.isBlank(username)) {
            username = "anonymous";
        }
        return username;
    }
    
    public boolean hasWritePermission() {
        if (StringUtils.isNotBlank(getUsername())) {
            return Jenkins.getInstance().getACL().hasPermission(Jenkins.getAuthentication(), PromoteDeployPlugin.WRITE);
        }
        return false;
    }
    
    public boolean hasReadPermission() {
        if (StringUtils.isNotBlank(getUsername())) {
            return Jenkins.getInstance().getACL().hasPermission(Jenkins.getAuthentication(), PromoteDeployPlugin.READ);
        }
        return false;
    }
    
    public Configuration getConfiguration() {
        return Jenkins.getInstance().getPlugin(PromoteDeployPlugin.class).getConfiguration();
    }
    
    public List<Deploy> getDeploys() {
        if (deploys == null) {
            deploys = new ArrayList<Deploy>();
        }
        return deploys;
    }
    
    public List<Deploy> getDeploysOrderedByDateDesc() {
        return DeployHelper.orderByDateDesc(getDeploys());
    }
    
    public Deploy getLastDeploy() {
        if (getDeploys().size() > 0) {
            return getDeploysOrderedByDateDesc().get(0);
        }
        return new Deploy();
    }
    
    public void doSaveData(StaplerRequest request, StaplerResponse response) throws IOException, ServletException {
        request.findAncestorObject(Job.class).checkPermission(PromoteDeployPlugin.WRITE);
        JSONObject form = request.getSubmittedForm();
        List<Environment> environments = getConfiguration().getEnvironments();
        
        Deploy deploy = new Deploy();
        Environment environment = DeployHelper.findByName(form.getString("environment"), environments);
        deploy.setEnvironment(environment);
        deploy.setVersion(BuildHelper.getReleaseVersion(build));
        deploy.setTimeInMillis(Calendar.getInstance().getTimeInMillis());
        deploy.setUsername(getUsername());
        deploy.setId(form.getString("id"));
        deploy.setDesc(form.getString("desc"));
        
        getDeploys().add(deploy);
        
        build.save();

        request.findAncestorObject(Run.class).keepLog(true);

        response.forwardToPreviousPage(request);
    }

}
