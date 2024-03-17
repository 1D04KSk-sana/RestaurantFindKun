package com.example.restaurantfindkun.screen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import com.example.restaurantfindkun.ui.theme.White
import dagger.hilt.android.AndroidEntryPoint

//
//画面共通部分
//

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

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

        //前回が許可済みか拒否されたか確認
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Log.d("Test", "許可済み")
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

//
//プレビュー：表示画面（共通）
//
@Preview(showBackground = true)
@Composable
fun FindKunAppPreview() {
    FindKunApp(FindKunDestination.Top, {})
}

