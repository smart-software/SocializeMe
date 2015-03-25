package com.smartsoft.socializeme;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;

import com.parse.Parse;

import com.smartsoft.socializeme.locationmanager.ILocationListener;
import com.smartsoft.socializeme.locationmanager.ILocationManager;
import com.smartsoft.socializeme.servicelocator.ObjectFactory;
import com.smartsoft.socializeme.usermanager.ICountNearUsersCallback;
import com.smartsoft.socializeme.usermanager.ICurrentUser;
import com.smartsoft.socializeme.usermanager.IUser;
import com.smartsoft.socializeme.usermanager.IUserManager;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SocializeService extends Service implements ILocationListener {
    // Parse keys
    private final String PARSE_APPID = "F13jhzTNsPglWJ3rSXIFjPlKhcvPVuUmzqhkdsxd";
    private final String PARSE_CLIENTID = "vHGFSAN2uaoKpPPFsn19Jm3WjaBW7iBFD7asCnqv";
    private final float MAX_DISTANCE = 5;
    private final float MAX_USER_DISTANCE = 0.1f;
    private final int MAX_NEAR_USERS = 0;

    // Private fields
    private Location m_lastKnownLocation;
    private Timer m_checkNearUsersTimer;

    public SocializeService() {
        m_lastKnownLocation = null;
        m_checkNearUsersTimer = null;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, this.PARSE_APPID, this.PARSE_CLIENTID);
        ObjectFactory.createObjectFactory(this.getApplicationContext());

        ILocationManager locationManager = ObjectFactory.getLocationManager();
        locationManager.setLocationListener(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Start receive location updates
        ILocationManager locationManager = ObjectFactory.getLocationManager();
        m_lastKnownLocation = locationManager.getLastKnownLocation();
        locationManager.startLocationUpdates(1000L * 60, this.MAX_DISTANCE); // Redo this

        m_checkNearUsersTimer = new Timer(true);
        m_checkNearUsersTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Check if are there some users near current location
                checkNearUsers();
            }
        }, 1000L, 60L * 1000 * 10);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        m_checkNearUsersTimer.cancel();
        super.onDestroy();
    }

    @Override
    public void onLocationChange(Location newLocation) {
        // Check if current user's location changed
        if((m_lastKnownLocation == null) || (m_lastKnownLocation.distanceTo(newLocation) > this.MAX_DISTANCE)) {
            m_lastKnownLocation = newLocation;

            // Send current user's new location to the cloud
            ICurrentUser currentUser = ObjectFactory.getUserManager().getCurrentUser();
            currentUser.setPosition(m_lastKnownLocation);
            currentUser.save();
        }
    }

    private void checkNearUsers() {
        IUserManager um = ObjectFactory.getUserManager();
        um.countNearUsers(this.MAX_USER_DISTANCE, new ICountNearUsersCallback() {
            @Override
            public void done(int count, List<IUser> userList) {
                if(count > MAX_NEAR_USERS) {
                    // Start to get application statistic on separate thread
                }
            }
        });
    }
}
