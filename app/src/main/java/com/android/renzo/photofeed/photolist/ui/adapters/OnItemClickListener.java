package com.android.renzo.photofeed.photolist.ui.adapters;

import android.widget.ImageView;

import com.android.renzo.photofeed.entities.Photo;

/**
 * Created by HOME on 03/07/2016.
 */
public interface OnItemClickListener {
    void onPlaceClick(Photo photo);
    void onShareClick(Photo photo,ImageView imageView);
    void onDeleteClick(Photo photo);
}
