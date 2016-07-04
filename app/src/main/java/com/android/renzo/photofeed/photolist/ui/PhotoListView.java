package com.android.renzo.photofeed.photolist.ui;

import com.android.renzo.photofeed.entities.Photo;

/**
 * Created by HOME on 03/07/2016.
 */
public interface PhotoListView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void addPhoto(Photo photo);
    void removePhoto(Photo photo);
    void onPhotoError(String error);
}
