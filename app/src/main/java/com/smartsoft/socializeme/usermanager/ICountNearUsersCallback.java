package com.smartsoft.socializeme.usermanager;

import java.util.List;

/**
 * Created by Sidorov_S on 25.03.2015.
 */
public interface ICountNearUsersCallback {
    void done(int count, List<IUser> userList);
}
