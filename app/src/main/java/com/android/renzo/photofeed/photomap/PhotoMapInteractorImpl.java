package com.android.renzo.photofeed.photomap;

/**
 * Created by HOME on 03/07/2016.
 */
public class PhotoMapInteractorImpl implements PhotoMapInteractor {

    PhotoMapRepository repository;

    public PhotoMapInteractorImpl(PhotoMapRepository repository) {
        this.repository = repository;
    }

    @Override
    public void subscribe() {
        repository.subscribe();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribe();
    }
}
