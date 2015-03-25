package com.smartsoft.socializeme.locationmanager;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by SERGant on 26.03.2015.
 */
public class GLocationManager implements ILocationManager {
    // Private fields
    private ILocationListener m_locationListener = null;
    private GoogleApiClient m_googleApiClient;

    public GLocationManager(Context context) {
        GoogleApiClient.Builder googleApiClientBuilder = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(new ConnectionCallbacks())
                .addApi(LocationServices.API);
        m_googleApiClient = googleApiClientBuilder.build();
        m_googleApiClient.connect();
    }

    @Override
    public Location getLastKnownLocation() {
        return LocationServices.FusedLocationApi.getLastLocation(m_googleApiClient);
    }

    @Override
    public void setLocationListener(ILocationListener listener) {
        m_locationListener = listener;
    }

    @Override
    public void startLocationUpdates(long minTime, float minDistance) {
        LocationServices.FusedLocationApi.requestLocationUpdates(
                m_googleApiClient,
                createLocationRequest(minTime, minDistance),
                new com.google.android.gms.location.LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        // Called when a new location is found by the provider.
                        if(m_locationListener != null) m_locationListener.onLocationChange(location);
                    }
                }
        );
    }

    private LocationRequest createLocationRequest(long minTime, float minDistance) {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(minTime); // release:1000*60*7 debug: 10*1000
        locationRequest.setFastestInterval(1000L * 60 * 10); //
        locationRequest.setSmallestDisplacement(minDistance);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        return locationRequest;
    }

    private class ConnectionCallbacks implements GoogleApiClient.ConnectionCallbacks {
        @Override
        public void onConnected(Bundle bundle) {
        }

        @Override
        public void onConnectionSuspended(int i) {
        }
    }
}
