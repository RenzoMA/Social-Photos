package com.android.renzo.photofeed.login.di;

import com.android.renzo.photofeed.domain.FirebaseAPI;
import com.android.renzo.photofeed.libs.base.EventBus;
import com.android.renzo.photofeed.login.LoginInteractor;
import com.android.renzo.photofeed.login.LoginInteractorImpl;
import com.android.renzo.photofeed.login.LoginPresenter;
import com.android.renzo.photofeed.login.LoginPresenterImpl;
import com.android.renzo.photofeed.login.LoginRepository;
import com.android.renzo.photofeed.login.LoginRepositoryImpl;
import com.android.renzo.photofeed.login.ui.LoginView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class LoginModule {
    LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    LoginView providesLoginView() {
        return this.view;
    }

    @Provides @Singleton
    LoginPresenter providesLoginPresenter(EventBus eventBus, LoginView loginView, LoginInteractor loginInteractor) {
        return new LoginPresenterImpl(eventBus, loginView, loginInteractor);
    }

    @Provides @Singleton
    LoginInteractor providesLoginInteractor(LoginRepository repository) {
        return new LoginInteractorImpl(repository);
    }


    @Provides @Singleton
    LoginRepository providesLoginRepository(FirebaseAPI firebase, EventBus eventBus) {
        return new LoginRepositoryImpl(firebase, eventBus);
    }
}
