package com.example.restaurantfindkun.screen

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    onChangeDestination: (FindKunDestination) -> Unit,
) {
    val navController = rememberNavController()

    val styledText = buildAnnotatedString {
        append("Powered by ")

        // ここから先の処理でappendされたテキストにAnnotationを追加
        // pop()が呼ばれるまでが対象
        pushStringAnnotation(
            tag = "ホットペッパーグルメ Webサービス",
            annotation = "http://webservice.recruit.co.jp/"
        )

        withStyle(SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)) {
            append("ホットペッパーグルメ Webサービス")
        }

        pop()
    }

    val uriHandler = LocalUriHandler.current

    FindKunComposeTheme {
        Scaffold(
            //タイトルバー
            topBar = {
                currentDestinations.topBarTitle?.let {
                    TopAppBar(
                        navigationIcon = {
                            // ArrowBackIosNewアイコンが表示されている場合のみClickableコンポーネントを有効にする
                            if (currentDestinations.isShowPreviewButton) {
                                Icon(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(start = 5.dp)
                                        .fillMaxSize()
                                        .clickable { navController.navigateUp() },
                                    imageVector = Icons.Default.ArrowBackIosNew,
                                    contentDescription = "アイコン",
                                    tint = White,
                                )
                            } else {
                                Icon(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(start = 5.dp)
                                        .fillMaxSize(),
                                    imageVector = Icons.Default.RestaurantMenu,
                                    contentDescription = "アイコン",
                                    tint = White,
                                )
                            }
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
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier
                        .height(70.dp)
                        .clip(RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp)),
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        ClickableText(
                            text = styledText,
                            onClick = { pos ->
                                // クリックされた箇所からAnnotationを取得
                                val annotation =
                                    styledText.getStringAnnotations(start = pos, end = pos)
                                        .firstOrNull()
                                annotation?.let { range ->
                                    // クリックされた箇所のURLを開く
                                    // pushStringAnnotationで設定した情報が取得できる
                                    uriHandler.openUri(range.item) // UriHandlerを使ってブラウザを開く
                                }
                            }
                        )
                    }

                }
            }
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
//    FindKunApp(FindKunDestination.Top, {}, Context)
}

