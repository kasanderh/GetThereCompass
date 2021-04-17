package com.kasanderh.gettherecompass.presenter

import android.app.Activity
import android.content.Context
import android.view.animation.RotateAnimation
import com.kasanderh.gettherecompass.view.MainActivity

abstract class MainActivityContract {

    interface View {

        fun requestLocationPermission()
        fun onLocationPermissionGranted()
        fun onLocationPermissionDenied()
//        fun startGps(coordinatesUser: Array<String>)
        fun startGps()
        fun dialogError()
//        fun showPermissionError()
        fun showCompassRotation(anim: RotateAnimation)
        fun showArrowRotation(anim: RotateAnimation)
        fun onGpsLocationChanged(latitude: String, longitude: String)
        fun onDestinationChanged(latitude: String, longitude: String)
        fun onPermissionResult(requestCode: Int,permissions: Array<String>, grantResults: IntArray)


    }

    interface Presenter {
        //        public fun startCoordinateDialog(context: Context)
        fun getLocation(context: Context)
        fun requestLocationPermission(activity: Activity)
        fun onPermissionResult(
            requestCode: Int,
            permissions: Array<String>, grantResults: IntArray
        )

        fun showDialog(context: Context)
        fun closeDialog(context: Context)

        fun rotationCompass(fromPosition: Double, toPosition: Double)
        fun rotationArrow(fromPosition: Double, toPosition: Double)

        fun locationChanged(latitude: String, longitude: String)

    }

}