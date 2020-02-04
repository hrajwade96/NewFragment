package com.tatadigital.tcp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.contentValuesOf
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult

import com.google.android.gms.location.LocationServices


//Created by Ankit Wala on 16/01/2020

/**
 * Helper class to get User's device location updates.
 * @param context Required to access permissions and get instance of FusedLocationProviderClient
 * @param callback LocationHelperCallback needs to be implemented to update the caller about the
 *        states of the location requests made
 * TODO: Currently only Last Known Location update request is implemented, later continuous location updates needs to be implemented
 *
 */
class UserLocationHelper(private val context: Context, private val callback: LocationHelperCallback) {


    private val TAG = "UserLocationHelper"

    private val fusedLocationClient: FusedLocationProviderClient
            = LocationServices.getFusedLocationProviderClient(context)

    enum class LocationUpdateFailureReason{
        /* Last Known Location request was successful, but the location returned was null. */
        LAST_KNOWN_LOCATION_NULL
    }


    /**
     * Call this method to request Last Known Location of the user.
     * It also checks for location permissions in the device.
     * If location request is successful, the method onLocationUpdated is called from the LocationHelperCallback.
     */

//    public fun requestLastKnownLocation(){
//
//        //Checking if the app is having any one of the location permissions atleast.
//        if (context.isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION)
//            ||
//                    context.isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)
//            ){
//
//            fusedLocationClient.lastLocation
//                .addOnSuccessListener{location ->
//                    if(location !=null) {
//
//                     //   callback.onLocationUpdated(location)
//                    }
//                    else{
//
//                       // callback.onLocationUpdateFailure(LocationUpdateFailureReason.LAST_KNOWN_LOCATION_NULL)
//                    }
//                }
//                .addOnFailureListener { exception ->
//                    Log.d(TAG, "Exception accessing Location = ${exception.message}")
//                    if (exception is SecurityException) {
//                        //If it is a Security Exception the it is because of the permission not granted.
//                    //    callback.onLocationPermissionNeeded()
//                    }
//                }
//
//        }
//       // else(
//            //    callback.onLocationPermissionNeeded()
//              //  )
//
//    }

    fun requestContinuosLocation()
    {


        val permissionAccessCoarseLocationApproved = ActivityCompat
            .checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED

        if (permissionAccessCoarseLocationApproved) {
            val backgroundLocationPermissionApproved = ActivityCompat
                .checkSelfPermission(context, Manifest.permission.ACCESS_BACKGROUND_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED

            if (backgroundLocationPermissionApproved) {
                // App can access location both in the foreground and in the background.
                // Start your service that doesn't have a foreground service type
                // defined.

            } else {
                lateinit var locationCallback: LocationCallback


                locationCallback = object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult?) {
                        locationResult ?: return
                        for (location in locationResult.locations){
                            // Update UI with location data
                            // ...
                            Log.i("Latitude",""+location.latitude)
                            Log.i("Longitude",""+location.longitude)

                        }
                    }
                }
                // App can only access location in the foreground. Display a dialog
                // warning the user that your app must have all-the-time access to
                // location in order to function properly. Then, request background
                // location.
                    //uncommment below code
//                ActivityCompat.requestPermissions(context,
//                    arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION),
//                    your- Manifest.permission -request-code
//                )
            }
        } else {
            // App doesn't have access to the device's location at all. Make full request
            // for permission.

            //uncommment below code
//            ActivityCompat.requestPermissions(this,
//                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
//                    Manifest.permission.ACCESS_BACKGROUND_LOCATION),
//                your- Manifest.permission -request-code
//            )
        }
    }
    /**
     * Interface to provide certain update statuses to the caller regarding the location request
     */
    interface LocationHelperCallback{

        /**
         * This method is called when the permissions needed to access the location is not granted.
         */
        fun onLocationPermissionNeeded()

        /**
         * Called when the requested location update is available and not null.
         */
        fun onLocationUpdated(location: Location)
        {

        }

        /**
         * Current Implementation: The request was successful but the location received was null and hence failed
         * Possible reasons : User has turned off Location Services.
         *                  : Brand new device and location was never updated
         *                  : Google Play services are restarted.
         *        @param failureReason : Enum signifying what was the reason for failure
         * TODO: Solution: Implement Settings Client method to prompt user to take appropriate action based on the reason
         * TODO: Example: Prompt user to Enable Location services in the device when location is null, and if enabled, enable periodic location updates.
         */
        fun onLocationUpdateFailure(failureReason: LocationUpdateFailureReason)

    }
}