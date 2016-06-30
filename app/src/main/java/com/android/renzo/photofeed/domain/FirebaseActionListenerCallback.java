package com.android.renzo.photofeed.domain;

import com.firebase.client.FirebaseError;

/**
 * Created by HOME on 29/06/2016.
 */
public interface FirebaseActionListenerCallback {
    void onSuccess();
    void onError(FirebaseError error);
}
