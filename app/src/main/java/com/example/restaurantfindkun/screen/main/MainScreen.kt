package com.example.pecoricompose.screen.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

//
//メイン画面
//
@Composable
fun MainScreen() {
    Text(text = "プロフィール")
}

//
//プレビュー：メイン画面
//
@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}