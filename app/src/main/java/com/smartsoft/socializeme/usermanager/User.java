package com.smartsoft.socializeme.usermanager;

import android.location.Location;

import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.smartsoft.socializeme.locationmanager.ISaveCallback;

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
        ParseGeoPoint point = new ParseGeoPoint(position.getLatitude(), position.getLongitude());
        m_parseUser.put("location", point);
    }

    @Override
    public void save(final ISaveCallback saveCallback) {
        m_parseUser.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                saveCallback.done();
            }
        });
    }
}
