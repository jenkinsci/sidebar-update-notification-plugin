package com.exxeta.jenkins.plugins.sidebar.updatenotification;

import static com.exxeta.jenkins.plugins.sidebar.updatenotification.JenkinsPermissionValidator.userHasNoAdministerPermission;
import hudson.Extension;
import hudson.model.RootAction;
import hudson.model.UpdateCenter;
import jenkins.model.Jenkins;

/**
 *
 * @author Benjamin Asbach, 2014
 */
@Extension(ordinal = 2)
public class JenkinsUpdateAvailableAction implements RootAction {

    private UpdateCenter.CoreUpdateMonitor coreUpdateMonitor;

    public JenkinsUpdateAvailableAction() {
        this.coreUpdateMonitor = (UpdateCenter.CoreUpdateMonitor) Jenkins
                .getInstance()
                .getAdministrativeMonitor(UpdateCenter.CoreUpdateMonitor.class.getName());
    }

    @Override
    public String getIconFileName() {
        if (!coreUpdateMonitor.isActivated() || userHasNoAdministerPermission()) {
            return null;
        }

        return "refresh.png";
    }

    @Override
    public String getDisplayName() {
        return String.format(Messages.JenkinsUpdateAvailable(), extractJenkinsUpdateersion());
    }

    public String getUrlName() {
        return "/manage";
    }

    private String extractJenkinsUpdateersion() {
        return coreUpdateMonitor.getData().core.version;
    }
}
