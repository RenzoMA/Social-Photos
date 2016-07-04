package com.android.renzo.photofeed.photomap.di;

import android.app.Activity;

import com.android.renzo.photofeed.domain.FirebaseAPI;
import com.android.renzo.photofeed.libs.base.EventBus;
import com.android.renzo.photofeed.photomap.PhotoMapInteractor;
import com.android.renzo.photofeed.photomap.PhotoMapInteractorImpl;
import com.android.renzo.photofeed.photomap.PhotoMapPresenter;
import com.android.renzo.photofeed.photomap.PhotoMapPresenterImpl;
import com.android.renzo.photofeed.photomap.PhotoMapRepository;
import com.android.renzo.photofeed.photomap.PhotoMapRepositoryImpl;
import com.android.renzo.photofeed.photomap.ui.PhotoMapView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HOME on 03/07/2016.
 */
@Module
public class PhotoMapModule {
    private PhotoMapView view;
    private Activity activity;

    public PhotoMapModule(PhotoMapView view, Activity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Provides
    @Singleton
    PhotoMapView providesPhotoMapView(){
        return this.view;
    }

    @Provides
    @Singleton
    PhotoMapPresenter providesPhotoMapPresenter(EventBus eventBus, PhotoMapView view, PhotoMapInteractor interactor){
        return new PhotoMapPresenterImpl(eventBus,view,interactor);
    }

    @Provides
    @Singleton
    PhotoMapInteractor providesPhotoMapInteractor(PhotoMapRepository photoMapRepository){
        return new PhotoMapInteractorImpl(photoMapRepository);
    }

    @Provides
    @Singleton
    PhotoMapRepository providesPhotoMapRepository(EventBus eventBus, FirebaseAPI firebaseAPI){
        return new PhotoMapRepositoryImpl(eventBus, firebaseAPI);
    }

    @Provides
    @Singleton
    Activity providesActivity(){
        return this.activity;
    }

}
