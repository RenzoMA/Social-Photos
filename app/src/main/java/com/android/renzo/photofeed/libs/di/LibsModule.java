package com.android.renzo.photofeed.libs.di;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;


import com.android.renzo.photofeed.libs.CloudinaryImageStorage;
import com.android.renzo.photofeed.libs.GlideImageLoader;
import com.android.renzo.photofeed.libs.GreenRobotEventBus;
import com.android.renzo.photofeed.libs.base.EventBus;
import com.android.renzo.photofeed.libs.base.ImageLoader;
import com.android.renzo.photofeed.libs.base.ImageStorage;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.cloudinary.Cloudinary;
import com.cloudinary.android.Utils;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HOME on 26/06/2016.
 */
@Module
public class LibsModule {
    private Fragment fragment;

    public LibsModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager){
        return new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    ImageStorage providesImageStorage(Cloudinary cloudinary){
        return new CloudinaryImageStorage(cloudinary);
    }

    @Provides
    @Singleton
    Cloudinary providesCloudinary(Context context){
        return new Cloudinary(Utils.cloudinaryUrlFromContext(context));
    }


    @Provides
    @Singleton
    RequestManager providesRequestManager(Activity activity){
        return  Glide.with(activity);
    }

    @Provides
    @Singleton
    Fragment providesFragment(){
        return this.fragment;
    }

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }
}
