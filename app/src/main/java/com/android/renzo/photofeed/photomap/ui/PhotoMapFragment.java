package com.android.renzo.photofeed.photomap.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.android.renzo.photofeed.PhotoFeedApp;
import com.android.renzo.photofeed.R;
import com.android.renzo.photofeed.entities.Photo;
import com.android.renzo.photofeed.photomap.PhotoMapPresenter;
import com.google.android.gms.maps.SupportMapFragment;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoMapFragment extends Fragment implements PhotoMapView {

    @Bind(R.id.container)
    FrameLayout container;

    @Inject
    PhotoMapPresenter presenter;

    public PhotoMapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupInjection();
        presenter.onCreate();
    }

    private void setupInjection() {
        PhotoFeedApp app = (PhotoFeedApp)getActivity().getApplication();

    }

    @Override
    public void onDestroy() {
        presenter.unsubscribe();
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photo_map, container, false);
        ButterKnife.bind(this, view);
        presenter.subscribe();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void addPhoto(Photo photo) {

    }

    @Override
    public void removePhoto(Photo photo) {

    }

    @Override
    public void onPhotosError(String error) {

    }


}
