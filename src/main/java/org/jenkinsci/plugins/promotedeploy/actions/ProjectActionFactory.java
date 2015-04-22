package org.jenkinsci.plugins.promotedeploy.actions;

import hudson.Extension;
import hudson.model.Action;
import hudson.model.TransientProjectActionFactory;
import hudson.model.AbstractProject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Extension
public class ProjectActionFactory extends TransientProjectActionFactory {

    @Override
    public Collection<? extends Action> createFor(AbstractProject project) {
        List<ProjectAction> actions = new ArrayList<ProjectAction>();
        actions.add(new ProjectAction(project));
        return actions;
    }

}
