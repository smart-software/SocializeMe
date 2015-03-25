package com.smartsoft.socializeme.usermanager;

import com.parse.ParseUser;

/**
 * Created by Sidorov_S on 25.03.2015.
 */
public class UserManager implements IUserManager {

    public UserManager() {
        ParseUser.enableAutomaticUser();
    }

    @Override
    public ICurrentUser getCurrentUser() {
        ICurrentUser currentUser = null;
        ParseUser parseUser = ParseUser.getCurrentUser();

        if (parseUser != null) {
            currentUser = new CurrentUser(parseUser);
        }

        return currentUser;
    }

    @Override
    public void countNearUsers(float distance, ICountNearUsersCallback callback) {

    }
}
