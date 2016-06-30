package com.android.renzo.photofeed.libs.base;

import android.widget.ImageView;

/**
 * Created by HOME on 29/06/2016.
 */
public interface ImageLoader {
    void load(ImageView imageView, String URL);
    void setOnFinishedImageLoadingListener(Object listener);
}
