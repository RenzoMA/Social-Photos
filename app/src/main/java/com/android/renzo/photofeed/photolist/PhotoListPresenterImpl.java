package com.android.renzo.photofeed.photolist;

import com.android.renzo.photofeed.entities.Photo;
import com.android.renzo.photofeed.libs.base.EventBus;
import com.android.renzo.photofeed.photolist.events.PhotoListEvent;
import com.android.renzo.photofeed.photolist.ui.PhotoListView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by HOME on 03/07/2016.
 */
public class PhotoListPresenterImpl implements PhotoListPresenter {

    private static final String EMPTY_LIST = "Listado vacio";
    private EventBus eventBus;
    private PhotoListView view;
    private PhotoListInteractor interactor;

    public PhotoListPresenterImpl(EventBus eventBus, PhotoListView view, PhotoListInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        eventBus.unregister(this);
    }

    @Override
    public void subscribe() {
        if(view != null){
            view.hideList();
            view.showProgress();
        }
        interactor.subscribe();
    }

    @Override
    public void unsubscribe() {
        interactor.unsubscribe();
    }

    @Override
    public void removePhoto(Photo photo) {
        interactor.removePhoto(photo);

    }

    @Override
    @Subscribe
    public void onEventMainThread(PhotoListEvent event) {
        if(view != null){
            view.hideProgress();
            view.showList();
        }
        String error = event.getError();
        if(error != null){
            if(error.isEmpty()){
                view.onPhotoError(EMPTY_LIST);
            }else{
                view.onPhotoError(error);
            }
        }else{
            if(event.getType() == PhotoListEvent.READ_EVENT){
                view.addPhoto(event.getPhoto());
            }else if(event.getType() == PhotoListEvent.DELETE_EVENT){
                view.removePhoto(event.getPhoto());
            }
        }
    }
}
