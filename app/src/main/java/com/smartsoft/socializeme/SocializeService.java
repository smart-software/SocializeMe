package com.smartsoft.socializeme;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;

import com.parse.Parse;

public class SocializeService extends Service {
    // Parse keys
    private final String PARSE_APPID = "F13jhzTNsPglWJ3rSXIFjPlKhcvPVuUmzqhkdsxd";
    private final String PARSE_CLIENTID = "vHGFSAN2uaoKpPPFsn19Jm3WjaBW7iBFD7asCnqv";

    public SocializeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Parse
        Parse.initialize(this, this.PARSE_APPID, this.PARSE_CLIENTID);

        // Initialize LocationManager and LocationListener
        /*
            Для того, чтобы предусмотреть универсальное решение, предлагается сделать кастомный интерфейс LocationManager,
            который инкапсулирует работу либо со стандартным LocationManager, либо с GoogleMapsAPI.
            Получать сервис предлагается с помощь ServiceLocator'а // на первом этапе можно обойтись
         */
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Start check location

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
