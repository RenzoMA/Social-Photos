package com.android.renzo.photofeed.photolist;

import com.android.renzo.photofeed.entities.Photo;
import com.android.renzo.photofeed.photolist.events.PhotoListEvent;

/**
 * Created by HOME on 03/07/2016.
 */
public interface PhotoListPresenter {
    void onCreate();
    void onDestroy();
    void subscribe();
    void onsubscribe();
    void removePhoto(Photo photo);
    void onEventMainThread(PhotoListEvent event);
}
