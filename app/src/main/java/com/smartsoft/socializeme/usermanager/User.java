package com.smartsoft.socializeme.usermanager;

import android.location.Location;

import com.parse.ParseUser;

/**
 * Created by Sidorov_S on 25.03.2015.
 */
public class User implements IUser {
    private ParseUser m_parseUser = null;

    public User(ParseUser parseUser) {
        m_parseUser = parseUser;
    }

    @Override
    public void setPosition(Location position) {
        // TODO: set position to ParseUser
    }

    @Override
    public void save() {
        m_parseUser.saveInBackground();
    }
}
