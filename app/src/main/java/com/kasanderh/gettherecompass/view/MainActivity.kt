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
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        onClickListenerSetup()

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        Compass(this, sensorManager)
        presenter.requestLocationPermission(this)

    }


    private fun onClickListenerSetup() {
        binding.buttonAddDestination.setOnClickListener {
            presenter.showDialog(this)
        }
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
        Toast.makeText(this, getString(R.string.location_permission_granted), Toast.LENGTH_SHORT)
            .show()
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


    override fun showCompassRotation(anim: RotateAnimation) {
        this.binding.imageViewCompass.startAnimation(anim)
    }

    override fun showArrowRotation(anim: RotateAnimation) {
        this.binding.imageViewArrow.startAnimation(anim)
    }

    override fun onGpsLocationChanged(latitude: String, longitude: String) {
        this.binding.textViewYourLocationCoordinates.text = "$latitude., $longitude"
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
        Toast.makeText(this, "There was an error retrieving the location.", Toast.LENGTH_LONG)
            .show()
        this.binding.textViewYourLocationCoordinates.text = getString(R.string.error)

    }
}


