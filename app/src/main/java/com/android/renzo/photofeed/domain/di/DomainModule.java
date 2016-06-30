package com.android.renzo.photofeed.domain.di;

import android.content.Context;
import android.location.Geocoder;

import com.android.renzo.photofeed.domain.FirebaseAPI;
import com.android.renzo.photofeed.domain.Util;
import com.firebase.client.Firebase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HOME on 29/06/2016.
 */
@Module
public class DomainModule {

    String firebaseURL;

    public DomainModule(String firebaseURL) {
        this.firebaseURL = firebaseURL;
    }

    @Provides
    @Singleton
    FirebaseAPI providesFirebaseAPI(Firebase firebase){
        return new FirebaseAPI(firebase);
    }

    @Provides
    @Singleton
    Firebase providesFirebase(String firebaseURL){
        return new Firebase(firebaseURL);
    }

    @Provides
    @Singleton
    String providesFirebaseURL(){
        return this.firebaseURL;
    }

    @Provides
    @Singleton
    Util providesUtil(Geocoder geocoder){
        return new Util(geocoder);
    }

    @Provides
    @Singleton
    Geocoder providesGeocoder(Context context){
        return new Geocoder(context);
    }
}
