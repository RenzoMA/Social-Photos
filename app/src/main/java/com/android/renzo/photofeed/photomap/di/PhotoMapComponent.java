package com.android.renzo.photofeed.photomap.di;

import com.android.renzo.photofeed.PhotoFeedAppModule;
import com.android.renzo.photofeed.domain.di.DomainModule;
import com.android.renzo.photofeed.libs.di.LibsModule;
import com.android.renzo.photofeed.photomap.ui.PhotoMapFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by HOME on 03/07/2016.
 */
@Singleton
@Component(modules = {PhotoMapModule.class, DomainModule.class, LibsModule.class, PhotoFeedAppModule.class})
public interface PhotoMapComponent {
    void inject(PhotoMapFragment fragment);
}
