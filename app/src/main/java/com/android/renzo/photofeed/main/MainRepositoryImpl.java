package com.android.renzo.photofeed.main;

import android.location.Location;

import com.android.renzo.photofeed.domain.FirebaseAPI;
import com.android.renzo.photofeed.entities.Photo;
import com.android.renzo.photofeed.libs.base.EventBus;
import com.android.renzo.photofeed.libs.base.ImageStorage;
import com.android.renzo.photofeed.libs.base.ImageStorageFinishedListener;
import com.android.renzo.photofeed.main.events.MainEvent;

import java.io.File;

/**
 * Created by HOME on 03/07/2016.
 */
public class MainRepositoryImpl implements MainRepository {

    private EventBus eventBus;
    private FirebaseAPI firebaseAPI;
    private ImageStorage imageStorage;

    public MainRepositoryImpl(EventBus eventBus, FirebaseAPI firebaseAPI, ImageStorage imageStorage) {
        this.eventBus = eventBus;
        this.firebaseAPI = firebaseAPI;
        this.imageStorage = imageStorage;
    }

    @Override
    public void logout() {
        firebaseAPI.logout();
    }

    @Override
    public void uploadPhoto(Location location, String path) {
        final String newPhotoId = firebaseAPI.create();
        final Photo photo = new Photo();
        photo.setId(newPhotoId);
        photo.setEmail(firebaseAPI.getAuthEmail());
        if(location != null){
            photo.setLatitude(location.getLatitude());
            photo.setLongitude(location.getLongitude());
        }
        post(MainEvent.UPLOAD_INIT);
        ImageStorageFinishedListener listener = new ImageStorageFinishedListener() {
            @Override
            public void onSuccess() {
                String url = imageStorage.getImageUrl(newPhotoId);
                photo.setUrl(url);
                firebaseAPI.update(photo);
                post(MainEvent.UPLOAD_COMPLETE);
            }

            @Override
            public void onError(String error) {
                post(MainEvent.UPLOAD_ERROR, error);
            }
        };
        imageStorage.upload(new File(path), newPhotoId, listener);
    }

    private void post(int type) {
        post(type,null);
    }
    private void post(int type,String error){
        MainEvent event = new MainEvent();
        event.setError(error);
        event.setType(type);
        eventBus.post(event);
    }
}
