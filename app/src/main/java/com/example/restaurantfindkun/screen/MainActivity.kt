package com.example.restaurantfindkun.screen

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.restaurantfindkun.R
import com.example.restaurantfindkun.navigation.FindKunDestination
import com.example.restaurantfindkun.navigation.FindKunNavHost
import com.example.restaurantfindkun.screen.base.BaseViewModel
import com.example.restaurantfindkun.ui.theme.FindKunComposeTheme
import com.example.restaurantfindkun.ui.theme.KariColor
import com.example.restaurantfindkun.ui.theme.White
import dagger.hilt.android.AndroidEntryPoint

//
//メイン画面　デザイン
//

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val currentDestination by mainViewModel.currentDestination.collectAsStateWithLifecycle()

            RequestPermission(
                modifier = Modifier,
                this,
                this
            )
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
//許可表示
//
@Composable
fun RequestPermission(
    modifier: Modifier = Modifier,
    context: Context,
    activity: Activity
) {
    //権限がすでに拒否されているか確認
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        //権限が現在付与されているか確認　GRANTED＝許可
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.e("testPermission", "true : 付与されてる")
        } else {
            Log.e("testPermission", "false : 付与されてない")
        }
    } else if (ActivityCompat.shouldShowRequestPermissionRationale(
            activity,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    ) {

    }

}

//
//表示画面
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
            topBar = {
                currentDestinations.topBarTitle?.let {
                    TopAppBar(
                        navigationIcon = {
//                            Icon(
//                                painter = painterResource(R.drawable.icn_arrow_back),
//                                contentDescription = "戻るボタン",
//                                tint = White,
//                            )
                        },
                        title = {
                            Text(
                                text = stringResource(id = it),
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