package com.jesualex.autocompletelocation.sample

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.jesualex.autocompletelocation.AutocompleteLocation
import com.jesualex.autocompletelocation.AutocompleteLocationListener
import com.jesualex.autocompletelocation.OnPlaceLoadListener
import com.jesualex.autocompletelocation.OnSearchListener
import com.jesualex.autocompletelocation.PlaceUtils

class MainActivity : FragmentActivity(),
        OnMapReadyCallback,
        AutocompleteLocationListener,
        OnSearchListener,
        OnPlaceLoadListener
{
    private var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val bounds = RectangularBounds.newInstance(
                LatLng(-33.880490, 151.184363),
                LatLng(-33.858754, 151.229596))

        val autoCompleteLocation = findViewById<AutocompleteLocation>(R.id.autocomplete_location)
        autoCompleteLocation.setAutoCompleteTextListener(this)
        autoCompleteLocation.setOnSearchListener(this)
        autoCompleteLocation.setCountry("Au")
        autoCompleteLocation.setLocationBias(bounds)

        //Set placeListener to auto calculate Place object when a AutocompletePrediction has selected.
        autoCompleteLocation.setPlaceListener(this)

        //Set placeFields to receive only the fields what you need. By default PlaceUtil.getDefaultFields() is call.
        autoCompleteLocation.setPlaceFields(PlaceUtils.defaultFields)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val madrid = LatLng(40.4167754, -3.7037902)
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(madrid, 16f))
    }

    override fun onTextClear() {
        mMap!!.clear()
    }

    override fun onItemSelected(selectedPlace: AutocompletePrediction?) {
        Log.i(javaClass.simpleName, "A autocomplete has selected: ")
        selectedPlace?.let { logAutocomplete(it) }
    }

    override fun onSearch(address: String, predictions: List<AutocompletePrediction>) {
        for (prediction in predictions) {
            logAutocomplete(prediction)
        }
    }

    override fun onPlaceLoad(place: Place) {
        addMapMarker(place.latLng)
    }

    private fun logAutocomplete(selectedPrediction: AutocompletePrediction) {
        Log.i(javaClass.simpleName, selectedPrediction.placeId)
        Log.i(javaClass.simpleName, selectedPrediction.getPrimaryText(null).toString())
    }

    private fun addMapMarker(latLng: LatLng?) {
        mMap!!.clear()
        mMap!!.addMarker(MarkerOptions().position(latLng!!))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
    }
}
