package com.android.renzo.photofeed.photomap;

import com.android.renzo.photofeed.domain.FirebaseAPI;
import com.android.renzo.photofeed.domain.FirebaseEventListenerCallback;
import com.android.renzo.photofeed.entities.Photo;
import com.android.renzo.photofeed.libs.base.EventBus;
import com.android.renzo.photofeed.photomap.events.PhotoMapEvent;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

/**
 * Created by HOME on 03/07/2016.
 */
public class PhotoMapRepositoryImpl implements  PhotoMapRepository {
    private EventBus eventBus;
    private FirebaseAPI firebaseAPI;

    public PhotoMapRepositoryImpl(EventBus eventBus, FirebaseAPI fIrebaseAPI) {
        this.eventBus = eventBus;
        this.firebaseAPI = fIrebaseAPI;
    }

    @Override
    public void subscribe() {
        firebaseAPI.subscribe(new FirebaseEventListenerCallback() {
            @Override
            public void onChilAdded(DataSnapshot snapshot) {
                Photo photo = snapshot.getValue(Photo.class);
                photo.setId(snapshot.getKey());

                String email = firebaseAPI.getAuthEmail();
                boolean puglishedByMe = photo.getEmail().equals(email);
                photo.setPublishedByMe(puglishedByMe);
                post(PhotoMapEvent.READ_EVENT,photo);
            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {
                Photo photo = snapshot.getValue(Photo.class);
                photo.setId(snapshot.getKey());

                post(PhotoMapEvent.DELETE_EVENT, photo);
            }

            @Override
            public void onCancelled(FirebaseError error) {
                post(error.getMessage());
            }
        });

    }

    @Override
    public void unsubscribe() {
        firebaseAPI.unsubscribe();
    }
    private void post(int type, Photo photo){
        post(type, photo, null);
    }

    private void post(String error){
        post(0, null, error);
    }

    private void post(int type, Photo photo, String error){
        PhotoMapEvent event = new PhotoMapEvent();
        event.setType(type);
        event.setError(error);
        event.setPhoto(photo);
        eventBus.post(event);
    }
}
