package com.exxeta.jenkins.plugins.sidebar.updatenotification;

import jenkins.model.Jenkins;

/**
 *
 * @author Benjamin Asbach, 2014
 */
public class JenkinsPermissionValidator {

    private JenkinsPermissionValidator() {
    }

    public static boolean userHasNoAdministerPermission() {
        return !Jenkins.getInstance().hasPermission(Jenkins.ADMINISTER);
    }
}
