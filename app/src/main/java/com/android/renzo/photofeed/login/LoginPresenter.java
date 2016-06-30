package com.android.renzo.photofeed.login;


import com.android.renzo.photofeed.login.events.LoginEvent;

/**
 * Created by HOME on 11/06/2016.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
    void onEventMainThread(LoginEvent event);
}
