package com.kasanderh.gettherecompass.view

import android.content.Context
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
import com.kasanderh.gettherecompass.model.GpsLocation
import com.kasanderh.gettherecompass.presenter.MainActivityContract
import com.kasanderh.gettherecompass.presenter.MainActivityPresenter

class MainActivity : AppCompatActivity(), MainActivityContract.View, GpsLocation.LocationListener {

    private val presenter = MainActivityPresenter(this)

    private lateinit var compass: Compass

    private lateinit var binding: ActivityMainBinding

//    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        onClickListenerSetup()

        compass = Compass(this)
        startCompass()
        showLocation()

    }

    private fun showLocation() {
        var userLocation = presenter.getLocation(binding.imageViewCompass.context)
        Toast.makeText(this, "Your location is: $userLocation", Toast.LENGTH_SHORT).show()
    }

    private fun startCompass() {
        compass.setImageViewCompass(imageViewCompass = binding.imageViewCompass)
    }

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

    override fun onLocationPermissionGranted() {
        Toast.makeText(this,getString(R.string.location_permission_granted), Toast.LENGTH_SHORT).show()
    }

    override fun onLocationPermissionDenied() {
        Toast.makeText(this,getString(R.string.location_permission_denied), Toast.LENGTH_SHORT).show()
    }

    override fun startGps(coordinatesUser: Array<String>) {
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
        //TODO("Not yet implemented")
    }

    override fun onGpsLocationChanged(latitude: String, longitude: String) {
        this.binding.textViewYourLocation.text = "Your current location is: $latitude, $longitude"
    }
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


