package org.jenkinsci.plugins.promotedeploy.helpers;

import org.apache.commons.lang.StringUtils;

import hudson.model.ParameterValue;
import hudson.model.ParametersAction;
import hudson.model.Run;
import hudson.model.StringParameterValue;

public class BuildHelper {

    public static String getReleaseVersion(Run build) {
        String releaseVersion = "";
        ParametersAction parametersAction = build.getAction(ParametersAction.class);

        if (parametersAction != null) {
            for (ParameterValue param : parametersAction.getParameters()) {
                if (param instanceof StringParameterValue && "RELEASE_VERSION".equals(((StringParameterValue) param).getName())) {
                    releaseVersion = ((StringParameterValue) param).value;
                    break;
                }
            }
        }
        return releaseVersion;
    }

    public static boolean isRelease(Run build) {
        return StringUtils.isNotBlank(getReleaseVersion(build));
    }

}
