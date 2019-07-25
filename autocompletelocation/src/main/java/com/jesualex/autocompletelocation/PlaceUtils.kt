package com.jesualex.autocompletelocation

import android.content.Context
import android.util.Log

import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient

/**
 * Created by jesualex on 01-03-19.
 */
object PlaceUtils {
    val defaultFields: List<Place.Field>
        get() = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)

    internal fun getPlacesClient(context: Context): PlacesClient {
        if (!Places.isInitialized()) {
            Places.initialize(context, context.resources.getString(R.string.google_places_key))
        }

        return Places.createClient(context)
    }

    fun getPlace(
            context: Context, placeId: String, listener: OnPlaceLoadListener
    ) {
        getPlace(context, placeId, defaultFields, listener)
    }

    fun getPlace(
            context: Context, placeId: String,
            placeFields: List<Place.Field>, listener: OnPlaceLoadListener
    ) {
        getPlace(getPlacesClient(context), placeId, placeFields, listener)
    }

    internal fun getPlace(
            client: PlacesClient, placeId: String,
            placeFields: List<Place.Field>, listener: OnPlaceLoadListener
    ) {
        val request = FetchPlaceRequest.builder(placeId, placeFields).build()

        client.fetchPlace(request)
                .addOnSuccessListener { fetchPlaceResponse ->
                    listener.onPlaceLoad(fetchPlaceResponse.place)
                }.addOnFailureListener { e ->
                    Log.e(AutocompleteLocation::class.java.simpleName, e.message)
                }
    }
}
