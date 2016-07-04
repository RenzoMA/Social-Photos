package com.android.renzo.photofeed.main.di;

import com.android.renzo.photofeed.PhotoFeedAppModule;
import com.android.renzo.photofeed.domain.di.DomainModule;
import com.android.renzo.photofeed.libs.di.LibsModule;
import com.android.renzo.photofeed.main.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by HOME on 03/07/2016.
 */
@Singleton
@Component(modules = {MainModule.class, DomainModule.class, LibsModule.class, PhotoFeedAppModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
