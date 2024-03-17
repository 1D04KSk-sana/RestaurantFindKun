package com.example.restaurantfindkun.data.location

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class PositionGet(private val context: Context) {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    fun getLocation() {
        // FusedLocationProviderClientのインスタンスを取得して初期化
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        //前回が許可済みか拒否されたか確認
        if (checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("Test", "許可済み")
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                // Got last known location. In some rare situations this can be null.
                var latitude = 0.0
                var longitude = 0.0

                if (location != null) {
                    latitude = location.latitude
                    longitude = location.longitude
                }

                Log.d("Test", "$latitude, $longitude")
            }
        } else {
            // 位置情報のパーミッションをリクエスト
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_REQUEST_CODE
        )
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 100
    }
}