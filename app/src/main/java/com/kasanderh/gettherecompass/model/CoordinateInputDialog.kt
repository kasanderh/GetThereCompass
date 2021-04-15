package com.kasanderh.gettherecompass.model

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.kasanderh.gettherecompass.R

class CoordinateInputDialog(context: Context): Dialog(context) {

    init {
        setCancelable(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_input_coordinates)

        setupOnClickListeners()

    }

    private fun setupOnClickListeners() {
        //Add onClickListeners for OK and CANCEL
    // TODO("Not yet implemented")
    }


}