package com.android.renzo.photofeed.libs.base;

/**
 * Created by HOME on 29/06/2016.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
