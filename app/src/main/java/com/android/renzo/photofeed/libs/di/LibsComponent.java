package com.android.renzo.photofeed.libs.di;

import com.android.renzo.photofeed.PhotoFeedAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by HOME on 29/06/2016.
 */
@Singleton
@Component(modules = {LibsModule.class, PhotoFeedAppModule.class})
public class LibsComponent {

}
