package com.android.renzo.photofeed.login;

import com.android.renzo.photofeed.domain.FirebaseAPI;
import com.android.renzo.photofeed.domain.FirebaseActionListenerCallback;
import com.android.renzo.photofeed.libs.base.EventBus;
import com.android.renzo.photofeed.login.events.LoginEvent;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

/**
 * Created by HOME on 11/06/2016.
 */
public class LoginRepositoryImpl implements LoginRepository {

    private EventBus eventBus;
    private FirebaseAPI fIrebaseAPI;

    public LoginRepositoryImpl( FirebaseAPI fIrebaseAPI,EventBus eventBus) {
        this.eventBus = eventBus;
        this.fIrebaseAPI = fIrebaseAPI;
    }

    @Override
    public void signUp(final String email, final String password) {
        fIrebaseAPI.signup(email, password, new FirebaseActionListenerCallback() {
            @Override
            public void onSuccess() {
                postEvent(LoginEvent.onSignUpSuccess);
                signIn(email, password);
            }

            @Override
            public void onError(FirebaseError error) {
                postEvent(LoginEvent.onSignUpError, error.getMessage(), null);
            }
        });
    }

    @Override
    public void signIn(String email, String password) {
        if(email != null && password != null){
            fIrebaseAPI.login(email, password, new FirebaseActionListenerCallback() {
                @Override
                public void onSuccess() {
                    String email = fIrebaseAPI.getAuthEmail();
                    postEvent(LoginEvent.onSignInSuccess, email);
                }

                @Override
                public void onError(FirebaseError error) {
                    postEvent(LoginEvent.onSignInError, error.getMessage(),null);
                }
            });
        }else{
            fIrebaseAPI.checkForSession(new FirebaseActionListenerCallback() {
                @Override
                public void onSuccess() {
                    String email = fIrebaseAPI.getAuthEmail();
                    postEvent(LoginEvent.onSignInSuccess, email);
                }

                @Override
                public void onError(FirebaseError error) {
                    postEvent(LoginEvent.onFailedToRecoverSession);
                }
            });
        }

    }

    private void postEvent(int type,String errorMessage,String currentUserEmail){
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        loginEvent.setErrorMessage(errorMessage);
        loginEvent.setCurrentUserEmail(currentUserEmail);

        eventBus.post(loginEvent);
    }
    private void postEvent(int type){
        postEvent(type,null,null);
    }
    private void postEvent(int type, String currentUserEmail){
        postEvent(type,null,currentUserEmail);
    }
}
