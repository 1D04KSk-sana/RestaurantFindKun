package com.example.restaurantfindkun.screen

import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.restaurantfindkun.navigation.FindKunDestination
import com.example.restaurantfindkun.navigation.FindKunNavHost
import com.example.restaurantfindkun.screen.top.TopScreen
import com.example.restaurantfindkun.ui.theme.FindKunComposeTheme
import com.example.restaurantfindkun.ui.theme.KariColor
import com.example.restaurantfindkun.ui.theme.White

class MainActivity : AppCompatActivity(), LocationListener {
    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var locationManager: LocationManager

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                locationStart()
            } else {
                Toast.makeText(this, "これ以上何もできません", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val currentDestination by mainViewModel.currentDestination.collectAsStateWithLifecycle()

            FindKunApp(
                currentDestinations = currentDestination,
                onChangeDestination = { nextDestination ->
                    mainViewModel.setCurrentDestination(nextDestination)
                }
            )
        }

        // ActionBarを非表示にする
        supportActionBar?.hide()

        if (androidx.core.content.ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            locationStart()
        }
    }

    private fun locationStart() {
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            val settingsIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(settingsIntent)
        }

        if (androidx.core.content.ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1000
            )
            return
        }

        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            1000,
            50f,
            this
        )
    }

    override fun onLocationChanged(location: Location) {
        val latitude = location.latitude.toString()
        val longitude = location.longitude.toString()
        Log.d("Location", "Latitude: $latitude, Longitude: $longitude")
    }

    override fun onProviderEnabled(provider: String) {}

    override fun onProviderDisabled(provider: String) {}
}

//
//表示画面（共通）
//
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindKunApp(
    currentDestinations: FindKunDestination,
    onChangeDestination: (FindKunDestination) -> Unit
) {
    FindKunComposeTheme {
        val navController = rememberNavController()

        Scaffold(
            //タイトルバー
            topBar = {
                currentDestinations.topBarTitle?.let {
                    TopAppBar(
                        navigationIcon = {
                            Icon(
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(start = 5.dp)
                                    .fillMaxSize(),
                                imageVector = Icons.Default.RestaurantMenu,
                                contentDescription = "アイコン",
                                tint = White,
                            )
                        },
                        title = {
                            Text(
                                text = stringResource(id = it),
                                color = White,
                                fontSize = 25.sp
                            )
                        },
                        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = KariColor)
                    )
                }
            },
        ) {
            FindKunNavHost(
                navController = navController,
                onChangeDestination,
                modifier = Modifier.padding(it)
            )
        }
    }
}
