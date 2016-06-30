package com.android.renzo.photofeed.libs.base;

import java.io.File;

/**
 * Created by HOME on 29/06/2016.
 */
public interface ImageStorage {
    String getImageUrl(String id);
    void upload(File file, String id, ImageStorageFinishedListener listener);
}
