package com.android.renzo.photofeed.main;

import android.location.Location;

import com.android.renzo.photofeed.main.events.MainEvent;

/**
 * Created by HOME on 03/07/2016.
 */
public interface MainPresenter {
    void onCreate();
    void onDestroy();

    void logout();
    void uploadPhoto(Location location, String path);
    void onEVentMainThread(MainEvent event);
}
