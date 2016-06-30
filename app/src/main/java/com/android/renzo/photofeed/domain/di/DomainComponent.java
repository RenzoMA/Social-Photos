package com.android.renzo.photofeed.domain.di;

import com.android.renzo.photofeed.PhotoFeedAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by HOME on 29/06/2016.
 */
@Singleton
@Component(modules = {DomainModule.class, PhotoFeedAppModule.class})
public interface DomainComponent {
}
