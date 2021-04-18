package com.kasanderh.gettherecompass.model

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.kasanderh.gettherecompass.databinding.DialogInputCoordinatesBinding

class CoordinateInputDialog(context: Context, private val returnResults: InputDialogResults) :
    Dialog(context) {

    private lateinit var bindingDialog: DialogInputCoordinatesBinding
    private lateinit var latitude: TextInputEditText
    private lateinit var longitude: TextInputEditText
    private lateinit var buttonOK: Button
    private lateinit var buttonCancel: Button
    private lateinit var longitudeInput: String
    private lateinit var latitudeInput: String

    init {
        setCancelable(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        bindingDialog = DialogInputCoordinatesBinding.inflate(layoutInflater)
        val view = bindingDialog.root
        setContentView(view)

        setView()
        setupOnClickListeners()

    }

    private fun setView() {
        latitude = bindingDialog.inputLatitude
        longitude = bindingDialog.inputLongitude
        buttonOK = bindingDialog.buttonOk
        buttonCancel = bindingDialog.buttonCancel

    }

    private fun setupOnClickListeners() {

        bindingDialog.buttonOk.setOnClickListener {
            if (bindingDialog.inputLongitude.text.toString()
                    .isNotEmpty() && bindingDialog.inputLatitude.text.toString()
                    .isNotEmpty()
            ) {
                longitudeInput = bindingDialog.inputLongitude.text.toString()
                latitudeInput = bindingDialog.inputLatitude.text.toString()
                returnResults.onInputDialogConfirmed(latitudeInput, longitudeInput)
            } else {
                returnResults.onInputDialogError()
            }
        }

        bindingDialog.buttonCancel.setOnClickListener {
            returnResults.onInputDialogCancelled()
        }
    }


    interface InputDialogResults {
        fun onInputDialogConfirmed(latitude: String, longitude: String)
        fun onInputDialogCancelled()
        fun onInputDialogError()
    }
}