package com.example.restaurantfindkun.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint

//メイン画面　デザイン

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RequestPermission()
        }
    }
}

@Composable
private fun RequestPermission() {

}


