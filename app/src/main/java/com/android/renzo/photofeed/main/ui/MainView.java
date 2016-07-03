package com.android.renzo.photofeed.main.ui;

/**
 * Created by HOME on 03/07/2016.
 */
public interface MainView {
    void onUploadInit();
    void onUploadComplete();
    void onUploadError(String error);

}
