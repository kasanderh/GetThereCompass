package com.kasanderh.gettherecompass.presenter

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.contentValuesOf
import com.kasanderh.gettherecompass.model.CoordinateInputDialog
import com.kasanderh.gettherecompass.model.DataLocation
import com.kasanderh.gettherecompass.model.GpsLocation
import com.kasanderh.gettherecompass.view.MainActivity

class MainActivityPresenter(view: MainActivityContract.View): MainActivityContract.Presenter, CoordinateInputDialog.InputDialogResults{

    var view: MainActivityContract.View? = view
    lateinit var inputDialog: CoordinateInputDialog


//    private var locationData: LocationData = LocationData()


    //    override fun startCoordinateDialog(context: Context) {
//        CoordinateInputDialog(context).show()
//    }
    override fun getLocation(context: Context) {
//        locationData.getLocation(context)

    }

    override fun requestLocationPermission(activity: Activity) {
//        ActivityCompat.requestPermissions(activity)
        val fineLocation = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
        val coarseLocation = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)


        if(fineLocation != PackageManager.PERMISSION_GRANTED || coarseLocation != PackageManager.PERMISSION_GRANTED) {

            //permission not granted
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), DataLocation.REQUEST_RECORD_CODE)


//            if(ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
//                // show message why needed
//            } else {
//                ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), locationData.REQUEST_RECORD_CODE)
//
//            }

        } else {
            //permission is already granted, ready to start GPS service
            view?.onLocationPermissionGranted()

        }
    }

    override fun onPermissionResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            DataLocation.REQUEST_RECORD_CODE -> {
                if(grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                    //ERROR, view.onLocationError()
                } else {
                    // granted
                    view?.startGps()
                }
            }

//            DataLocation.REQUEST_RECORD_CODE -> {
//                if((grantResults.isNotEmpty() &&
//                            grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//                    // permission granted
//                    view?.onLocationPermissionGranted()
//                    view?.startGps()
//                } else {
//                    view?.onLocationPermissionDenied()
//                }
//                return
//            }
        }
    }


    override fun onInputDialogConfirmed(latitude: String, longitude: String) {
        DataLocation.inputLatitude = latitude.toFloat()
        DataLocation.inputLongitude = longitude.toFloat()
//        LocationData.setCoordinates(latitude, longitude)
        view?.onDestinationChanged(latitude,longitude)
//        view?.onGpsLocationChanged(latitude, longitude)
        inputDialog.hide()
    }

    override fun onInputDialogCancelled() {
        inputDialog.hide()
    }

    override fun showDialog(context: Context) {
        inputDialog = CoordinateInputDialog(context, this)
        inputDialog.show()
    }

    override fun closeDialog(context: Context) {
        inputDialog = CoordinateInputDialog(context, this)
        inputDialog.hide()
    }

    override fun onInputDialogError() {
        view?.dialogError()
    }

    override fun rotationCompass(fromPosition: Double, toPosition: Double) {
        val anim = RotateAnimation(-(fromPosition.toFloat()), -(toPosition.toFloat()), Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        anim.duration = 500
        anim.repeatCount = 0
        anim.fillAfter = true
        view?.showCompassRotation(anim)
    }

    override fun rotationArrow(fromPosition: Double, toPosition: Double) {
        val anim = RotateAnimation(-(fromPosition.toFloat()), -(toPosition.toFloat()), Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        anim.duration = 500
        anim.repeatCount = 0
        anim.fillAfter = true
        view?.showArrowRotation(anim)
    }

    override fun locationChanged(latitude: String, longitude: String) {
        DataLocation.currentLatitude = latitude.toFloat()
        DataLocation.currentLongitude = longitude.toFloat()
//        view?.onGpsLocationChanged(latitude, longitude)
    }
}