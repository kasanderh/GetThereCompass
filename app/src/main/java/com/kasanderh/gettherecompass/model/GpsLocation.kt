package com.kasanderh.gettherecompass.model

import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle

class GpsLocation(private val listener: LocationListener, private val locationManager: LocationManager): LocationListener {

    init {
        initializeLocalization()
    }

    @SuppressLint("MissingPermission")
    private fun initializeLocalization() {
        val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0f, this)
        onLocationChanged(location)
    }

    override fun onLocationChanged(location: Location?) {
        val latitude = location!!.latitude.toString()
        val longitude = location!!.longitude.toString()
        listener.onGpsLocationChanged(latitude, longitude)
    }


    interface LocationListener {
        fun onGpsLocationChanged(latitude: String, longitude: String)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
       // TODO("Not yet implemented")
    }

    override fun onProviderEnabled(provider: String?) {
        //TODO("Not yet implemented")
    }

    override fun onProviderDisabled(provider: String?) {
        //TODO("Not yet implemented")
    }
}