package org.jenkinsci.plugins.promotedeploy.actions;

import hudson.Extension;
import hudson.model.Action;
import hudson.model.TransientProjectActionFactory;
import hudson.model.AbstractProject;

import java.util.Arrays;
import java.util.Collection;

@Extension
public class ProjectActionFactory extends TransientProjectActionFactory {

    @Override
    public Collection<? extends Action> createFor(AbstractProject project) {
        return Arrays.asList(new ProjectAction(project));
    }

}
