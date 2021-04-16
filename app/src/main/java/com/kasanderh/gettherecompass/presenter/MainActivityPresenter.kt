package com.kasanderh.gettherecompass.presenter

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.kasanderh.gettherecompass.model.CoordinateInputDialog
import com.kasanderh.gettherecompass.model.LocationData
import com.kasanderh.gettherecompass.view.MainActivity

class MainActivityPresenter: MainActivityContract.Presenter {

    lateinit var view: MainActivityContract.View


    private var locationData: LocationData = LocationData()


    //    override fun startCoordinateDialog(context: Context) {
//        CoordinateInputDialog(context).show()
//    }
    override fun getLocation(context: Context) {
        locationData.getLocation(context)
    }

    override fun requestLocationPermission(activity: Activity) {
//        ActivityCompat.requestPermissions(activity)
        val fineLocation = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
        val coarseLocation = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)


        if(fineLocation != PackageManager.PERMISSION_GRANTED || coarseLocation != PackageManager.PERMISSION_GRANTED) {

            //permission not granted
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), locationData.REQUEST_RECORD_CODE)


//            if(ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
//                // show message why needed
//            } else {
//                ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), locationData.REQUEST_RECORD_CODE)
//
//            }

        } else {
            //permission is already granted, ready to start GPS service
            view.onLocationPermissionGranted()

        }
    }

    override fun onPermissionResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            locationData.REQUEST_RECORD_CODE -> {
                if((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission granted
                    view.onLocationPermissionGranted()
                } else {
                    view.onLocationPermissionDenied()
                }
                return
            }
        }
    }
}