package com.smartsoft.socializeme.usermanager;

import android.location.Location;

import com.smartsoft.socializeme.locationmanager.ISaveCallback;

/**
 * Created by Sidorov_S on 25.03.2015.
 */
public interface IUser {
    String getID();
    void setPosition(Location position);
    Location getPosition();

    void save(ISaveCallback saveCallback);
}
