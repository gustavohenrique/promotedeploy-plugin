package org.jenkinsci.plugins.promotedeploy.helpers;

import java.util.Collections;
import java.util.List;

import org.jenkinsci.plugins.promotedeploy.models.Deploy;
import org.jenkinsci.plugins.promotedeploy.models.Environment;

public class DeployHelper {

    public static List<Deploy> orderByDateDesc(List<Deploy> deploys) {
        Collections.sort(deploys);
        Collections.reverse(deploys);
        return deploys;
    }
    
    public static List<Deploy> orderByDateAsc(List<Deploy> deploys) {
        Collections.sort(deploys);
        return deploys;
    }

    public static Environment findByName(String name, List<Environment> environments) {
        for (Environment environment : environments) {
            if (name.equals(environment.getName())) {
                return environment;
            }
        }
        return new Environment();
    }
    
}
