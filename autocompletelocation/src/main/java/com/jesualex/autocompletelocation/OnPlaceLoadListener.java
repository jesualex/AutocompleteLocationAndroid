package com.jesualex.autocompletelocation;

import android.support.annotation.NonNull;

import com.google.android.libraries.places.api.model.Place;

/**
 * Created by jesualex on 01-03-19.
 */
public interface OnPlaceLoadListener {
    void onPlaceLoad(@NonNull Place place);
}
