package com.smartsoft.socializeme.servicelocator;

import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.smartsoft.socializeme.locationmanager.BaseLocationManager;
import com.smartsoft.socializeme.locationmanager.GLocationManager;
import com.smartsoft.socializeme.locationmanager.ILocationManager;
import com.smartsoft.socializeme.usermanager.IUserManager;
import com.smartsoft.socializeme.usermanager.UserManager;

/**
 * Created by Sidorov_S on 24.03.2015.
 */
public final class ObjectFactory {
    private static ObjectFactory Obj;

    private Context m_context = null;
    private ILocationManager m_locationManager = null;
    private IUserManager m_userManager = null;

    private ObjectFactory(Context context) {
        m_context = context;
    }

    public static void createObjectFactory(Context context) {
        Obj = new ObjectFactory(context);
    }

    public static ILocationManager getLocationManager() {
        if(Obj.m_locationManager == null) {
            // Check if Google MAP API Service available, then use this
            if(Obj.isGooglePlayServicesAvailable()) Obj.m_locationManager = new GLocationManager(Obj.m_context);
            else Obj.m_locationManager = new BaseLocationManager(Obj.m_context);
        }

        return Obj.m_locationManager;
    }

    public static IUserManager getUserManager() {
        if(Obj.m_userManager == null) Obj.m_userManager = new UserManager();

        return Obj.m_userManager;
    }

    private boolean isGooglePlayServicesAvailable() {
        //Check if Google play Services is avaliable
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(m_context);

        return (resultCode == ConnectionResult.SUCCESS);
    }
}
