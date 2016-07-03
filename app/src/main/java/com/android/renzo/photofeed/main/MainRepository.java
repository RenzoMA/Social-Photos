package com.android.renzo.photofeed.main;

import android.location.Location;

/**
 * Created by HOME on 03/07/2016.
 */
public interface MainRepository {
    void logout();
    void uploadPhoto(Location location, String path);
}
