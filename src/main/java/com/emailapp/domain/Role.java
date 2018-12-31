package com.emailapp.domain;


public class Role extends Entity {

    public static final String ADMINISTRATOR = "ADMINISTRATOR";
    public static final String R_MODERATOR = "R_MODERATOR";
    public static final String RU_MODERATOR = "RU_MODERATOR";
    public static final String RUD_MODERATOR = "RUD_MODERATOR";
    public static final String USER = "USER";

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
