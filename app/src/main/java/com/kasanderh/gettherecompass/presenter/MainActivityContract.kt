package com.kasanderh.gettherecompass.presenter

import android.app.Activity
import android.content.Context
import com.kasanderh.gettherecompass.view.MainActivity

abstract class MainActivityContract {

    interface View {

        fun requestLocationPermission()
        fun onLocationPermissionGranted()
        fun onLocationPermissionDenied()
//        fun showPermissionError()


    }

    interface Presenter {
//        public fun startCoordinateDialog(context: Context)
        fun getLocation(context: Context)
        fun requestLocationPermission(activity: Activity)
        fun onPermissionResult(requestCode: Int,
        permissions: Array<String>, grantResults: IntArray)

    }

}