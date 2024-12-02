package com.example.yukawawebseitebe.models.user;

public enum UserRole {
    ADMIN("admin"),
    USER("user"),
    GUEST("guest"),
    BANDMEMBER("bandmember");

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
}
