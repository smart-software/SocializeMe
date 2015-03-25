package com.smartsoft.socializeme.locationmanager;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by Sidorov_S on 24.03.2015.
 */
public class BaseLocationManager implements ILocationManager {
    private LocationManager m_locationManager = null;
    private ILocationListener m_locationListener = null;

    private BaseLocationManager() {
    }

    public BaseLocationManager(Context context) {
        m_locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public Location getLastKnownLocation() {
        Criteria criteria = new Criteria();
        String provider = m_locationManager.getBestProvider(criteria, false);

        return m_locationManager.getLastKnownLocation(provider);
    }

    @Override
    public void setLocationListener(ILocationListener listener) {
        m_locationListener = listener;
    }

    @Override
    public void startLocationUpdates(long minTime, float minDistance) {
        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the provider.
                if(m_locationListener != null) m_locationListener.onLocationChange(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        Criteria criteria = new Criteria();
        String provider = m_locationManager.getBestProvider(criteria, false);

        // Register the listener with the Location Manager to receive location updates
        m_locationManager.requestLocationUpdates(provider, minTime, minDistance, locationListener);
    }
}
