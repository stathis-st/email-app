package com.emailapp.domain;


public class Role extends Entity {


    private String roleType;
    private String description;

    public Role() {
    }

    public Role(String roleType, String description) {
        this.roleType = roleType;
        this.description = description;
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
