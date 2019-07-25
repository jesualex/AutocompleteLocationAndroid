package com.jesualex.autocompletelocation;

import com.google.android.libraries.places.api.model.AutocompletePrediction;

import java.util.List;

/**
 * Created by jesualex on 01-03-19.
 */
public interface OnSearchListener {
    void onSearch(String address, List<AutocompletePrediction> predictions);
}
