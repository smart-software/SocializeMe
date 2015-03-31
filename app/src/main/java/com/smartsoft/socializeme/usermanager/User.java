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
    public String getID() {
        return m_parseUser.getObjectId();
    }

    @Override
    public void setPosition(Location position) {
        ParseGeoPoint point = new ParseGeoPoint(position.getLatitude(), position.getLongitude());
        m_parseUser.put("location", point);
    }

    @Override
    public Location getPosition() {
        ParseGeoPoint point = (ParseGeoPoint) m_parseUser.get("location");
        if(point != null) {
            Location userPosition = new Location("dummyprovider");
            userPosition.setLatitude(point.getLatitude());
            userPosition.setLongitude(point.getLongitude());

            return userPosition;
        }
        else {
            return null;
        }
    }

    @Override
    public void save(final ISaveCallback saveCallback) {
        m_parseUser.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                ISaveCallback.SaveResult saveResult = ISaveCallback.SaveResult.FAILED;
                if(e == null) saveResult = ISaveCallback.SaveResult.SUCCESS;

                saveCallback.done(saveResult);
            }
        });
    }
}
