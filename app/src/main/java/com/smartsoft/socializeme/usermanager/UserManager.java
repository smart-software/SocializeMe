package com.smartsoft.socializeme.usermanager;

import android.location.Location;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by Sidorov_S on 25.03.2015.
 */
public class UserManager implements IUserManager {

    public UserManager() {
        ParseUser.enableAutomaticUser();
    }

    @Override
    public ICurrentUser getCurrentUser() {
        ICurrentUser currentUser = null;
        ParseUser parseUser = ParseUser.getCurrentUser();

        if (parseUser != null) {
            currentUser = new CurrentUser(parseUser);
        }

        return currentUser;
    }

    @Override
    public void countNearUsers(float distance, final ICountNearUsersCallback callback) {
        ICurrentUser currentUser = getCurrentUser();
        Location userLocation = currentUser.getPosition();
        ParseGeoPoint point = new ParseGeoPoint(userLocation.getLatitude(), userLocation.getLongitude());

        ParseQuery<ParseUser> query = ParseQuery.getQuery(ParseUser.class);
        query.whereWithinKilometers("location", point, 0.1);
        query.whereNotEqualTo("objectId", currentUser.getID()); // Not including yourself
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> parseUsers, ParseException e) {
                if(e == null) {
                    callback.done(parseUsers.size(), null); // TODO:
                }
                else {
                    callback.done(-1, null);
                }
            }
        });
    }
}
