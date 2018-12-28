package com.emailapp.domain;


public class Role extends Entity {

    public static final String ADMIN_TYPE = "ADMIN";
    public static final String USER_TYPE = "USER";

    private String roleType;
    private String description;

    public Role() {
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
