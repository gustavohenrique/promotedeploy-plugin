package org.jenkinsci.plugins.promotedeploy.actions;

import hudson.Extension;
import hudson.Plugin;
import hudson.model.ModelObject;
import hudson.model.Result;
import hudson.model.TaskListener;
import hudson.model.Descriptor.FormException;
import hudson.model.Run;
import hudson.model.listeners.RunListener;
import hudson.security.Permission;
import hudson.security.PermissionGroup;

import java.io.IOException;

import javax.servlet.ServletException;

import net.sf.json.JSONObject;

import org.jenkinsci.plugins.promotedeploy.helpers.BuildHelper;
import org.jenkinsci.plugins.promotedeploy.models.Configuration;
import org.jenkinsci.plugins.promotedeploy.models.Environment;
import org.kohsuke.stapler.StaplerRequest;

public class PromoteDeployPlugin extends Plugin implements ModelObject {

    public static final PermissionGroup GROUP = new PermissionGroup(PromoteDeployPlugin.class, null);
    public static final Permission READ = new Permission(GROUP, "PromoteDeployRead", null, PromoteDeployPlugin.READ);
    public static final Permission WRITE = new Permission(GROUP, "PromoteDeployWrite", null, PromoteDeployPlugin.WRITE);

    private Configuration configuration;
    
    @Override
    public void start() throws Exception {
        load();
    }

    @Override
    public void configure(StaplerRequest request, JSONObject formData) throws IOException, ServletException, FormException {
        configuration = new Configuration();
        configuration.setLabelId(formData.getString("labelId"));
        configuration.setLabelDesc(formData.getString("labelDesc"));
        configuration.setEnvironments(request.bindJSONToList(Environment.class, formData.get("environments")));
        save();
    }
    
    public Configuration getConfiguration() {
        return configuration;
    }

    @Extension
    public static class PromoteDeployRunListener extends RunListener<Run> {
        
        public PromoteDeployRunListener() {
            super(Run.class);
        }

        @Override
        public void onCompleted(Run build, TaskListener listener) {
            Result buildResult = build.getResult();
            
            if (BuildHelper.isRelease(build)) {
                if (isSuccessful(buildResult)) {
                    build.addAction(new BuildAction(build));
                }
            }
        }

        private boolean isSuccessful(Result res) {
            return res != Result.FAILURE && res != Result.ABORTED;
        }
    }

    public String getDisplayName() {
        return "Release x Ambiente";
    }

}
