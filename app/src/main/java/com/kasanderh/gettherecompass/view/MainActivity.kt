package com.kasanderh.gettherecompass.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kasanderh.gettherecompass.databinding.ActivityMainBinding
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

        startCompass()
    }

    private fun startCompass() {
        compass.setImageViewCompass(imageViewCompass = binding.imageViewCompass)
    }

    private fun onClickListenerSetup() {
        TODO("Not yet implemented")
    }



}