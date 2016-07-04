package com.android.renzo.photofeed.photolist.di;

import com.android.renzo.photofeed.PhotoFeedAppModule;
import com.android.renzo.photofeed.domain.di.DomainModule;
import com.android.renzo.photofeed.libs.di.LibsModule;
import com.android.renzo.photofeed.photolist.ui.PhotoListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by HOME on 03/07/2016.
 */
@Singleton
@Component(modules = {PhotoListModule.class, DomainModule.class, LibsModule.class, PhotoFeedAppModule.class})
public interface PhotoListComponent {
    void inject(PhotoListFragment fragment);
}
