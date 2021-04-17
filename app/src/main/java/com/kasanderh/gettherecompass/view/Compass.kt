package com.kasanderh.gettherecompass.view

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import com.kasanderh.gettherecompass.model.DataLocation
import kotlin.math.roundToInt

class Compass(private val listener: SensorListener, sensorManager: SensorManager): SensorEventListener {

//    var locationData: LocationData = LocationData()
    private val gravity: FloatArray = FloatArray(3)
    private val geomagnetic: FloatArray = FloatArray(3)
    private val alpha = 0.97f
    private var currentCompassPosition= 0f
    private var lastCompassPosition= 0f

    init {
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME)
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_GAME)

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //  "Not yet implemented"
    }

    override fun onSensorChanged(event: SensorEvent?) {
        when(event!!.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                gravity[0] = alpha * gravity[0] + (1-alpha) * event.values[0]
                gravity[1] = alpha * gravity[1] + (1-alpha) * event.values[1]
                gravity[2] = alpha * gravity[2] + (1-alpha) * event.values[2]
            }

            Sensor.TYPE_MAGNETIC_FIELD -> {
                geomagnetic[0] = alpha * geomagnetic[0] + (1-alpha) * event.values[0]
                geomagnetic[1] = alpha * geomagnetic[1] + (1-alpha) * event.values[1]
                geomagnetic[2] = alpha * geomagnetic[2] + (1-alpha) * event.values[2]
            }
        }

        updateCompassDirection()
    }

    private fun updateCompassDirection() {
        val R = FloatArray(9)
        val I = FloatArray(9)

        val success = SensorManager.getRotationMatrix(R, I, gravity, geomagnetic)
        if(success) {
            val orientation = FloatArray(3)
            SensorManager.getOrientation(R, orientation)
            currentCompassPosition = Math.toDegrees(orientation[0].toDouble()).toFloat()
            currentCompassPosition = (currentCompassPosition + 360) % 360
            listener.onCompassSensorChanged(DataLocation.ROTATE_COMPASS, currentCompassPosition, lastCompassPosition)
            lastCompassPosition = currentCompassPosition
        }
    }

    interface SensorListener {
        fun onCompassSensorChanged(updateType: Int, fromPosition: Float, toPosition: Float)

    }

    //    private var currentDegree = 0f
//
//    private var mySensorManager: SensorManager? = null
//
//    private var imageViewCompass: ImageView? = null
//
//    init {
//        mySensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
//    }
//
//    fun setImageViewCompass (imageViewCompass: ImageView?) {
//        this.imageViewCompass = imageViewCompass
//    }
//
//    override fun onSensorChanged(event: SensorEvent?) {
//        val degree = event!!.values[0].roundToInt().toFloat()
//
//        // Rotation Animation
//
//        val rotationAnimation = RotateAnimation(currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
//
//        rotationAnimation.duration = 210
//
//        rotationAnimation.fillAfter = true
//
//        imageViewCompass!!.startAnimation(rotationAnimation)
//        currentDegree = -degree
//
//    }
//
//    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//
//    }
}