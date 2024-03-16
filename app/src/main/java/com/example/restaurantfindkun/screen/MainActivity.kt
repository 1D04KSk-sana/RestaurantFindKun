package com.example.restaurantfindkun.screen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.restaurantfindkun.navigation.FindKunDestination
import com.example.restaurantfindkun.navigation.FindKunNavHost
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

            //RequestPermission()
            FindKunApp(
                currentDestinations = currentDestination,
                onChangeDestination = {
                        nextDestination -> mainViewModel.setCurrentDestination(nextDestination)
                }
            )
        }
    }
}

//
//許可表示
//
@Composable
private fun RequestPermission() {
    // LocalComposition より提供される Context を取得する
    val context = LocalContext.current

    // LocalComposition より提供される Lifecycle を取得する
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    // Permission Request を実行する Permission を定義する
    val permission = Manifest.permission.READ_EXTERNAL_STORAGE

    // Permission Request を実行するための Launcher を作成する
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted -> Log.v("TEST", "PERMISSION REQUEST RESULT ${isGranted}") }
    )

    // もし Permission が許可されていなければ、 Activity が onStart に遷移したとき、
    // Launcher を利用して Permission Request を実行する LifecycleObserver を作成する。
    val lifecycleObserver = remember {
        LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                if (!permission.isGrantedPermission(context)) {
                    launcher.launch(permission)
                }
            }
        }
    }

    // lifecycle または lifecycleObserver が変化した、また破棄されたら呼び出される
    DisposableEffect(lifecycle, lifecycleObserver) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }
}

private fun String.isGrantedPermission(context: Context): Boolean {
    // checkSelfPermission は PERMISSION_GRANTED or PERMISSION_DENIED のどちらかを返す
    // そのため checkSelfPermission の戻り値が PERMISSION_GRANTED であれば許可済みになる。
    return context.checkSelfPermission(this) == PackageManager.PERMISSION_GRANTED
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