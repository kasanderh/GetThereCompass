package com.kasanderh.gettherecompass.model;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class LocationData {
    private FusedLocationProviderClient fusedLocationProviderClient;
    private double latitude;
    private double longitude;

    public final int REQUEST_RECORD_CODE = 1;


    @SuppressLint("MissingPermission")
    public String getLocation(Context context) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);


        fusedLocationProviderClient.getLastLocation().addOnSuccessListener((Activity) context, location -> {
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                System.out.println("The latitude is in the classy: " + latitude);
                System.out.println("The longitude is in the classy: " + longitude);

            }
        });

        System.out.println("The latitude is: " + latitude);
        System.out.println("The longitude is: " + longitude);
        return latitude + "," + longitude;


    }
}


