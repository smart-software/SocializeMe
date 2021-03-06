package com.smartsoft.socializeme.locationmanager;

import android.location.Location;

/**
 * Created by SERGant on 20.03.2015.
 */
public interface ILocationManager {
    Location getLastKnownLocation();
    void setLocationListener(ILocationListener listener);
    void startLocationUpdates(long minTime, float minDistance);
}
