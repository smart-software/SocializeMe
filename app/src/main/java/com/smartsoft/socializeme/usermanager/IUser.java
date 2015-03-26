package com.smartsoft.socializeme.usermanager;

import android.location.Location;

import com.smartsoft.socializeme.locationmanager.ISaveCallback;

/**
 * Created by Sidorov_S on 25.03.2015.
 */
public interface IUser {
    void setPosition(Location position);
    void save(ISaveCallback saveCallback);
}
