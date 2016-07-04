package com.android.renzo.photofeed.libs;

import android.widget.ImageView;

import com.android.renzo.photofeed.libs.base.ImageLoader;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;

/**
 * Created by HOME on 26/06/2016.
 */
public class GlideImageLoader implements ImageLoader {

    private RequestManager glidRequestManager;
    private RequestListener onFinishedLoadingListener;

    public GlideImageLoader(RequestManager glidRequestManager) {
        this.glidRequestManager = glidRequestManager;
    }

    @Override
    public void load(ImageView imageView, String URL) {
        if(onFinishedLoadingListener != null){
            glidRequestManager.load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .listener(onFinishedLoadingListener)
                    .into(imageView);

        }else{
            glidRequestManager.load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(700,700)
                    .centerCrop()
                    .into(imageView);
        }
    }

    @Override
    public void setOnFinishedImageLoadingListener(Object listener) {
        if(listener instanceof  RequestListener) {
            this.onFinishedLoadingListener = (RequestListener) listener;
        }
    }
}
