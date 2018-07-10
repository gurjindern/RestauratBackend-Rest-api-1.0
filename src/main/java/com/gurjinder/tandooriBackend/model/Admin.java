package com.gurjinder.tandooriBackend.model;

public class Admin extends User {
    private boolean admin;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
