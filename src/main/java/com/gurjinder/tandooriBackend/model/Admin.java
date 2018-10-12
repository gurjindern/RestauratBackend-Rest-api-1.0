package com.gurjinder.tandooriBackend.model;

public class Admin extends User {

    private String adminUsername;

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUserName) {
        this.adminUsername = adminUserName;
    }
}
