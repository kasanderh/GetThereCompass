package com.kasanderh.gettherecompass.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
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


