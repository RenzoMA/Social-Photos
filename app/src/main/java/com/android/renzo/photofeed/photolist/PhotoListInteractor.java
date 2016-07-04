package com.android.renzo.photofeed.photolist;

import com.android.renzo.photofeed.entities.Photo;

/**
 * Created by HOME on 03/07/2016.
 */
public interface PhotoListInteractor {
    void subscribe();
    void unsubscribe();
    void removePhoto(Photo photo);
}
