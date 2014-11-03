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
@Extension(ordinal = 1)
public class PluginUpdateAvailableAction implements RootAction {

    private UpdateCenter updateCenter = Jenkins.getInstance().getUpdateCenter();

    public String getIconFileName() {
        if (updateCenter.getUpdates().isEmpty() || userHasNoAdministerPermission()) {
            return null;
        }

        return "refresh.png";
    }

    public String getDisplayName() {
        int availableUpdateCount = updateCenter.getUpdates().size();
        if (availableUpdateCount == 1) {
            return Messages.JenkinsPluginSingleUpdateAvailable();
        } else {
            return String.format(Messages.JenkinsPluginMultipleUpdatesAvailable(), availableUpdateCount);
        }
    }

    public String getUrlName() {
        return "/pluginManager";
    }
}
