package com.smartsoft.socializeme.usermanager;

/**
 * Created by Sidorov_S on 25.03.2015.
 */
public interface IUserManager {
    ICurrentUser getCurrentUser();
    void countNearUsers(float distance, ICountNearUsersCallback callback);
}
