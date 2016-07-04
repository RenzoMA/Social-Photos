package com.android.renzo.photofeed.main;

/**
 * Created by HOME on 03/07/2016.
 */
public class SessionInteractorImpl implements SessionInteractor {

    MainRepository repository;

    public SessionInteractorImpl(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void logout() {
        repository.logout();
    }
}
