package com.android.renzo.photofeed;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HOME on 29/06/2016.
 */
@Module
public class PhotoFeedAppModule {
    PhotoFeedApp app;

    public PhotoFeedAppModule(PhotoFeedApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application providesApplication(){
        return this.app;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedpreferences(Application application){
        return application.getSharedPreferences(app.getSharedPreferencesName(), Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    Context provideApplicationContext(){
        return app.getApplicationContext();
    }
}
