package com.kasanderh.gettherecompass.view

import android.app.Activity
import android.content.Context
import android.hardware.SensorManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.kasanderh.gettherecompass.R
import com.kasanderh.gettherecompass.databinding.ActivityMainBinding
import com.kasanderh.gettherecompass.model.CoordinateInputDialog
import com.kasanderh.gettherecompass.model.DataLocation
import com.kasanderh.gettherecompass.model.GpsLocation
import com.kasanderh.gettherecompass.presenter.MainActivityContract
import com.kasanderh.gettherecompass.presenter.MainActivityPresenter

class MainActivity : AppCompatActivity(), MainActivityContract.View, GpsLocation.LocationListener,
    Compass.SensorListener {

    private val presenter = MainActivityPresenter(this)

//    private lateinit var compass: Compass

    private lateinit var binding: ActivityMainBinding

//    private var locationData: LocationData = LocationData()

//    private lateinit var fragmentManager: FragmentManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        onClickListenerSetup()

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        Compass(this, sensorManager)


//        compass = Compass(this)
//        startCompass()
//        showLocation()
        // method for asking permission
        presenter.requestLocationPermission(this)

    }

//    private fun showLocation() {
//        var userLocation = presenter.getLocation(binding.imageViewCompass.context)
//        Toast.makeText(this, "Your location is: $userLocation", Toast.LENGTH_SHORT).show()
//    }

//    private fun startCompass() {
//
////        compass.setImageViewCompass(imageViewCompass = binding.imageViewCompass)
//
//    }

    private fun onClickListenerSetup() {
        //onclick for "SET DESTINATION"
        binding.buttonAddDestination.setOnClickListener {
            presenter.showDialog(this)
//            CoordinateInputDialog(this, this).show()
//            startCustomDialog()

        }

//    fun closeDialog() {
//        presenter.closeDialog(this)
////        CoordinateInputDialog(this, fragmentManager).hide()
//    }


        //onclick for "CANCEL"
        //onclick for "OK"

    }

    override fun requestLocationPermission() {
        presenter.requestLocationPermission(this)
    }

    override fun onPermissionResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        presenter.onPermissionResult(requestCode, permissions, grantResults)
    }

    override fun onLocationPermissionGranted() {
        Toast.makeText(this, getString(R.string.location_permission_granted), Toast.LENGTH_SHORT).show()
    }

    override fun onLocationPermissionDenied() {
        Toast.makeText(this, getString(R.string.location_permission_denied), Toast.LENGTH_SHORT)
            .show()
    }

    override fun startGps() {
        val locationManger = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        GpsLocation(this, locationManger)
    }

    override fun dialogError() {
        Toast.makeText(this, "Please enter coordinates", Toast.LENGTH_SHORT).show()
    }

//    fun getView(): Context {
//        return this
//    }

    override fun showCompassRotation(anim: RotateAnimation) {
        this.binding.imageViewCompass.startAnimation(anim)
    }

    override fun showArrowRotation(anim: RotateAnimation) {
        this.binding.imageViewArrow.startAnimation(anim)
    }

    override fun onGpsLocationChanged(latitude: String, longitude: String) {
        this.binding.textViewYourLocationCoordinates.text = "$latitude., $longitude"
//        Toast.makeText(this, "Your location is " + latitude + longitude, Toast.LENGTH_LONG).show()
        presenter.locationChanged(latitude, longitude)
    }

    override fun onCompassSensorChanged(
        updateType: Int,
        fromPosition: Float,
        toPosition: Float
    ) {
        when (updateType) {
            DataLocation.ROTATE_COMPASS ->
                presenter.rotationCompass(fromPosition.toDouble(), toPosition.toDouble())
            DataLocation.ROTATE_ARROW ->
                presenter.rotationArrow(fromPosition.toDouble(), toPosition.toDouble())
        }
    }

    override fun onDestinationChanged(latitude: String, longitude: String) {
        this.binding.textViewDestinationCoordinates.text = "$latitude, $longitude"
    }

    override fun onGpsLocationError() {
        Toast.makeText(this, "There was an error retrieving the location.", Toast.LENGTH_LONG).show()
        this.binding.textViewYourLocationCoordinates.text = getString(R.string.error)

    }

    //    override fun getActivity(): Activity {
//        return this
//    }

    //    private fun startCustomDialog() {
//        CoordinateInputDialog(this).show()

//        MainActivityPresenter.startCoordinateDialog(this)
//        val rootView = window.decorView.rootView
//        val dialogView: View = LayoutInflater.from(this).inflate(R.layout.dialog_input_coordinates, rootView, false)
//        var alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
//        alertDialogBuilder.setView(dialogView)
//        var alertDialog: AlertDialog = alertDialogBuilder.create()
//        alertDialog.show()
//    }
}


