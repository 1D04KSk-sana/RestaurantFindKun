package com.example.restaurantfindkun.screen

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationRequest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.restaurantfindkun.navigation.FindKunDestination
import com.example.restaurantfindkun.navigation.FindKunNavHost
import com.example.restaurantfindkun.ui.theme.FindKunComposeTheme
import com.example.restaurantfindkun.ui.theme.KariColor
import com.example.restaurantfindkun.ui.theme.TitleText
import com.example.restaurantfindkun.ui.theme.White
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

//
//画面共通部分
//

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    //権限リクエストするための変数
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        //⑦ ユーザーが権限を許可したか
        if (isGranted) {
            Log.d("Test", "Launcher：許可")
        } else {
            Log.d("Test", "Launcher：拒否")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // FusedLocationProviderClientのインスタンスを取得して初期化
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        //前回が許可済みか拒否されたか確認
        if (ActivityCompat.checkSelfPermission(
                this,
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
            requestPermissionLauncher.launch(
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        }

        setContent {
            val currentDestination by mainViewModel.currentDestination.collectAsStateWithLifecycle()

            FindKunApp(
                currentDestinations = currentDestination,
                onChangeDestination = { nextDestination ->
                    mainViewModel.setCurrentDestination(nextDestination)
                }
            )
        }
    }

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
                                style = TitleText,
                                color = White
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

//
//プレビュー：表示画面（共通）
//
@Preview(showBackground = true)
@Composable
fun FindKunAppPreview() {
    FindKunApp(FindKunDestination.Top, {})
}

