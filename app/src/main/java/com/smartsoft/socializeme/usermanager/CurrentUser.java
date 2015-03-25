package com.smartsoft.socializeme.usermanager;

import android.location.Location;

import com.parse.ParseUser;

/**
 * Created by Sidorov_S on 25.03.2015.
 */
public class CurrentUser extends User implements ICurrentUser {

    public CurrentUser(ParseUser parseUser) {
        super(parseUser);
    }
}
