package com.epam.newsmanager.entity;

import java.io.Serializable;

/**
 * User role in news management system
 */
public class Role implements Serializable {

    public static final long serialVersionUID = 1;

    /**
     * Unique identifier
     */
    private long userId;

    /**
     * Role name
     */
    private String roleName;

    public Role() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "userId=" + userId +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Role role = (Role) o;

        if (userId != role.userId) {
            return false;
        }
        return !(roleName != null ? !roleName.equals(role.roleName) : role.roleName != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }
}
