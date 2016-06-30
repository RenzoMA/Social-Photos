package com.android.renzo.photofeed.domain;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

/**
 * Created by HOME on 29/06/2016.
 */
public interface FirebaseEventListenerCallback {
    void onChilAdded(DataSnapshot snapshot);
    void onChildRemoved(DataSnapshot snapshot);
    void onCancelled(FirebaseError error);
}
