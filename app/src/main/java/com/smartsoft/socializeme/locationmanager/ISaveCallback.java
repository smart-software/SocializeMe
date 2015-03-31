package com.smartsoft.socializeme.locationmanager;

/**
 * Created by SERGant on 27.03.2015.
 */
public interface ISaveCallback {
    public enum SaveResult {
        SUCCESS,
        FAILED
    }

    void done(SaveResult saveResult);
}
