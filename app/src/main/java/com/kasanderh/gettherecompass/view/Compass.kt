package com.kasanderh.gettherecompass.view

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView

class Compass(private val context: Context): SensorEventListener {

    private var currentDegree = 0f

    private var mySensorManager: SensorManager? = null

    private var imageViewCompass: ImageView? = null

    init {
        mySensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    fun setImageViewCompass (imageViewCompass: ImageView?) {
        this.imageViewCompass = imageViewCompass
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val degree = Math.round(event!!.values[0]).toFloat()

        // Rotation Animation

        val rotationAnimation = RotateAnimation(currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)

        rotationAnimation.duration = 210

        rotationAnimation.fillAfter = true

        imageViewCompass!!.startAnimation(rotationAnimation)
        currentDegree = -degree

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}