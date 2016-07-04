package com.android.renzo.photofeed.photolist.di;

import android.app.Activity;

import com.android.renzo.photofeed.domain.FirebaseAPI;
import com.android.renzo.photofeed.domain.Util;
import com.android.renzo.photofeed.entities.Photo;
import com.android.renzo.photofeed.libs.base.EventBus;
import com.android.renzo.photofeed.libs.base.ImageLoader;
import com.android.renzo.photofeed.photolist.PhotoListInteractor;
import com.android.renzo.photofeed.photolist.PhotoListInteractorImpl;
import com.android.renzo.photofeed.photolist.PhotoListPresenter;
import com.android.renzo.photofeed.photolist.PhotoListPresenterImpl;
import com.android.renzo.photofeed.photolist.PhotoListRepository;
import com.android.renzo.photofeed.photolist.PhotoListRepositoryImpl;
import com.android.renzo.photofeed.photolist.ui.PhotoListView;
import com.android.renzo.photofeed.photolist.ui.adapters.OnItemClickListener;
import com.android.renzo.photofeed.photolist.ui.adapters.PhotoListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HOME on 03/07/2016.
 */
@Module
public class PhotoListModule {
    PhotoListView view;
    OnItemClickListener onItemClickListener;
    Activity activity;

    public PhotoListModule(PhotoListView view, OnItemClickListener onItemClickListener, Activity activity) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;
        this.activity = activity;
    }

    @Provides
    @Singleton
    PhotoListView providesPhotoContentView() {
        return this.view;
    }

    @Provides @Singleton
    PhotoListPresenter providesPhotoListPresenter(EventBus eventBus, PhotoListView view, PhotoListInteractor listInteractor) {
        return new PhotoListPresenterImpl(eventBus, view, listInteractor);
    }

    @Provides @Singleton
    PhotoListInteractor providesPhotoListInteractor(PhotoListRepository repository) {
        return new PhotoListInteractorImpl(repository);
    }
    @Provides @Singleton
    PhotoListRepository providesPhotoListRepository(FirebaseAPI firebase, EventBus eventBus) {
        return new PhotoListRepositoryImpl(eventBus, firebase );
    }
    @Provides @Singleton
    PhotoListAdapter providesPhotosAdapter(Util utils, List<Photo> photoList, ImageLoader imageLoader, OnItemClickListener onItemClickListener) {
        return new PhotoListAdapter(utils, photoList, imageLoader, onItemClickListener);
    }

    @Provides @Singleton
    List<Photo> providesPhotosList() {
        return new ArrayList<Photo>();
    }
    @Provides
    @Singleton
    Activity providesActivity(){
        return this.activity;
    }

    @Provides @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return this.onItemClickListener;
    }

}
