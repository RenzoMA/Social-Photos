package com.android.renzo.photofeed;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.renzo.photofeed.domain.di.DomainModule;
import com.android.renzo.photofeed.libs.di.LibsModule;
import com.android.renzo.photofeed.login.di.DaggerLoginComponent;
import com.android.renzo.photofeed.login.di.LoginComponent;
import com.android.renzo.photofeed.login.di.LoginModule;
import com.android.renzo.photofeed.login.ui.LoginView;
import com.android.renzo.photofeed.main.di.DaggerMainComponent;
import com.android.renzo.photofeed.main.di.MainComponent;
import com.android.renzo.photofeed.main.di.MainModule;
import com.android.renzo.photofeed.main.ui.MainView;
import com.firebase.client.Firebase;

/**
 * Created by HOME on 29/06/2016.
 */
public class PhotoFeedApp extends Application {
    private final static String EMAIL_KEY = "email";
    private final static String SHARED_PREFERENCES_NAME = "UserPrefs";
    private final static String FIREBASE_URL = "https://android-chat-exa.firebaseIO.com/";
    private PhotoFeedAppModule photoFeedAppModule;
    private DomainModule domainModule;

    @Override
    public void onCreate() {
        super.onCreate();
        initFirebase();
        initModules();
    }

    private void initModules() {
        photoFeedAppModule = new PhotoFeedAppModule(this);
        domainModule = new DomainModule(FIREBASE_URL);

    }

    private void initFirebase() {
        Firebase.setAndroidContext(this);
    }

    public  String getEmailKey() {
        return EMAIL_KEY;
    }

    public  String getSharedPreferencesName() {
        return SHARED_PREFERENCES_NAME;
    }

    public LoginComponent getLoginComponent(LoginView view){
        return DaggerLoginComponent
                .builder()
                .photoFeedAppModule(photoFeedAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(null))
                .loginModule(new LoginModule(view))
                .build();


    }

    public MainComponent getMainComponent(MainView view, FragmentManager manager, Fragment[] fragments, String[] titles){
        return DaggerMainComponent
                .builder()
                .photoFeedAppModule(photoFeedAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(null))
                .mainModule(new MainModule(view,titles,fragments,manager))
                .build();
    }

}
