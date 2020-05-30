package io.bsu.mmf.helpme.featuremain.fragment.train.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import com.google.android.gms.location.*
import java.util.*


@SuppressLint("MissingPermission")
class CurrentLocationListener private constructor(context: Context) : LiveData<Location>() {

    private var currentTime: Int = 0
    private var prevTime: Int = 0
    private var delta = 0
    private var distance = 0f
    private var currentSpeed = 0f
    private var currentLocation: Location? = null
    private var startLocation: Location? = null
    private var prevLocation: Location? = null
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var mLocationRequest: LocationRequest? = null
    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult?) {
            super.onLocationResult(result)

            //init startLocation
            if (startLocation == null && result?.lastLocation != null) {
                startLocation = result.lastLocation
            }

            //init current Location and time in second
            value = result?.lastLocation
            currentTime = Calendar.getInstance().get(Calendar.SECOND)
            currentLocation = result?.lastLocation



            if (prevTime != 0) {
                delta = currentTime - prevTime
                prevTime = currentTime
                distance = startLocation?.distanceTo(currentLocation) ?: 0f

                currentSpeed = prevLocation?.distanceTo(currentLocation)?.div(delta) ?: 0f

            }
            prevTime = currentTime
            prevLocation = currentLocation


        }
    }

    init {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        mFusedLocationClient?.lastLocation?.addOnSuccessListener { value = it }
        createLocationRequest()
    }

    private fun createLocationRequest() {
        mLocationRequest = LocationRequest().apply {
            interval = 3500
            fastestInterval = 1500
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        mFusedLocationClient?.requestLocationUpdates(mLocationRequest, mLocationCallback, null)
    }

    override fun onInactive() {
        super.onInactive()
        mFusedLocationClient?.removeLocationUpdates(mLocationCallback)
    }


    companion object {

        @Volatile
        private var INSTANCE: CurrentLocationListener? = null

        fun getInstance(context: Context): CurrentLocationListener =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildLocationUpdateListener(context).also { INSTANCE = it }
            }

        private fun buildLocationUpdateListener(context: Context) =
            CurrentLocationListener(context)
    }
}