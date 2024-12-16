package com.thedomain.koindemonstration.koin;


import com.thedomain.koindemonstration.DataManager;

interface UserRepo {
    User getUser();
    void addUser(User aUser);
    void clearUser();
}
public class UserRepoImpl implements UserRepo {

    @Override
    public User getUser() {
        return DataManager.getInstance().getCurrentUser();
    }

    @Override
    public void addUser(User aUser) {
        DataManager.getInstance().setCurrentUser(aUser);
    }

    @Override
    public void clearUser() {
        DataManager.getInstance().clearUser();
    }
}
