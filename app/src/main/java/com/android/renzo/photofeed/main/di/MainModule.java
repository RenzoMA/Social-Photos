package com.android.renzo.photofeed.main.di;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.renzo.photofeed.domain.FirebaseAPI;
import com.android.renzo.photofeed.libs.base.EventBus;
import com.android.renzo.photofeed.libs.base.ImageStorage;
import com.android.renzo.photofeed.main.MainPresenter;
import com.android.renzo.photofeed.main.MainPresenterImpl;
import com.android.renzo.photofeed.main.MainRepository;
import com.android.renzo.photofeed.main.MainRepositoryImpl;
import com.android.renzo.photofeed.main.SessionInteractor;
import com.android.renzo.photofeed.main.SessionInteractorImpl;
import com.android.renzo.photofeed.main.UploadInteractor;
import com.android.renzo.photofeed.main.UploadInteractorImpl;
import com.android.renzo.photofeed.main.adapters.MainSectionsPagerAdapter;
import com.android.renzo.photofeed.main.ui.MainView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HOME on 03/07/2016.
 */
@Module
public class MainModule {
    private MainView view;
    private String[] titles;
    private Fragment[] fragments;
    private FragmentManager fragmentManager;

    public MainModule(MainView view, String[] titles, Fragment[] fragments, FragmentManager fragmentManager) {
        this.view = view;
        this.titles = titles;
        this.fragments = fragments;
        this.fragmentManager = fragmentManager;
    }
    @Provides
    @Singleton
    MainView providesMainView() {
        return this.view;
    }

    @Provides @Singleton
    MainPresenter providesMainPresenter(MainView view, EventBus eventBus, UploadInteractor uploadInteractor, SessionInteractor sessionInteractor) {
        return new MainPresenterImpl(view, eventBus, uploadInteractor, sessionInteractor);
    }

    @Provides @Singleton
    UploadInteractor providesUploadInteractor(MainRepository repository) {
        return new UploadInteractorImpl(repository);
    }

    @Provides @Singleton
    SessionInteractor providesSessionInteractor(MainRepository repository) {
        return new SessionInteractorImpl(repository);
    }

    @Provides @Singleton
    MainRepository providesMainRepository(EventBus eventBus, FirebaseAPI firebase, ImageStorage imageStorage) {
        return new MainRepositoryImpl(eventBus, firebase, imageStorage);
    }

    @Provides @Singleton
    MainSectionsPagerAdapter providesAdapter(FragmentManager fm, String[] titles,  Fragment[] fragments){
        return new MainSectionsPagerAdapter(fm, titles,fragments);
    }

    @Provides @Singleton
    FragmentManager providesAdapterFragmentManager(){
        return this.fragmentManager;
    }

    @Provides @Singleton
    Fragment[] providesFragmentArrayForAdapter(){
        return this.fragments;
    }

    @Provides @Singleton
    String[] providesStringArrayForAdapter(){
        return this.titles;
    }

}
