package com.android.renzo.photofeed.photomap.ui;

import com.android.renzo.photofeed.entities.Photo;

/**
 * Created by HOME on 03/07/2016.
 */
public interface PhotoMapView {
    void addPhoto(Photo photo);
    void removePhoto(Photo photo);
    void onPhotosError(String error);
}
