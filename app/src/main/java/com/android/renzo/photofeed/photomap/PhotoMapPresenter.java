package com.android.renzo.photofeed.photomap;

import com.android.renzo.photofeed.photomap.events.PhotoMapEvent;

/**
 * Created by HOME on 03/07/2016.
 */
public interface PhotoMapPresenter {
    void onCreate();
    void onDestroy();
    void subscribe();
    void unsubscribe();

    void onEventMainThread(PhotoMapEvent event);
}
