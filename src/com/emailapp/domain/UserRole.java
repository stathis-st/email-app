package com.emailapp.domain;


public class UserRole extends Entity {

    private long id;
    private long usersId;
    private long rolesId;

    public UserRole() {
    }

    public UserRole(long usersId, long rolesId) {
        this.usersId = usersId;
        this.rolesId = rolesId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getUsersId() {
        return usersId;
    }

    public void setUsersId(long usersId) {
        this.usersId = usersId;
    }


    public long getRolesId() {
        return rolesId;
    }

    public void setRolesId(long rolesId) {
        this.rolesId = rolesId;
    }

}
