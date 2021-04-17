package com.kasanderh.gettherecompass.model;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.jetbrains.annotations.NotNull;

public class LocationData {
    private FusedLocationProviderClient fusedLocationProviderClient;
    private double latitude;
    private double longitude;
    private static double inputDirectionLatitude;
    private static double inputDirectionLongitude;

    public final int REQUEST_RECORD_CODE = 1;
    public final int ROTATE_COMPASS = 1;
    public final int ROTATE_ARROW = 2;



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

    public static void setCoordinates(@NotNull String latitude, @NotNull String longitude) {
        inputDirectionLatitude = Double.parseDouble(latitude);
        inputDirectionLongitude = Double.parseDouble(longitude);
    }
}


