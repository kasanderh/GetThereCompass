package com.kasanderh.gettherecompass.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.kasanderh.gettherecompass.R
import com.kasanderh.gettherecompass.databinding.ActivityMainBinding
import com.kasanderh.gettherecompass.databinding.DialogInputCoordinatesBinding
import com.kasanderh.gettherecompass.model.CoordinateInputDialog
import com.kasanderh.gettherecompass.presenter.MainActivityContract
import com.kasanderh.gettherecompass.presenter.MainActivityPresenter

class MainActivity : AppCompatActivity(), MainActivityContract.View {

    private val presenter = MainActivityPresenter()

    private lateinit var compass: Compass

    private lateinit var binding: ActivityMainBinding

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
        var userLocation = presenter.getLocation(this)
        Toast.makeText(this, "Your location is: $userLocation", Toast.LENGTH_SHORT).show()
    }

    private fun startCompass() {
        compass.setImageViewCompass(imageViewCompass = binding.imageViewCompass)
    }

    private fun onClickListenerSetup() {
        //onclick for "SET DESTINATION"
        binding.buttonAddDestination.setOnClickListener {
            CoordinateInputDialog(this).show()
//            startCustomDialog()

        }



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


