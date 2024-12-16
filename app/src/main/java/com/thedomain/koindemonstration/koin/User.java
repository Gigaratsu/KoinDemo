package com.thedomain.koindemonstration.koin;

public class User {
    private String displayName;
    private String firstName;
    private int userId;

    public User() {
    }

    public User(String displayName, String firstName, int userId) {
        this.displayName = displayName;
        this.firstName = firstName;
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
