package com.example.restaurantfindkun.navigation

import androidx.annotation.StringRes
import com.example.restaurantfindkun.R

sealed class FindKunDestination(
    var route: String,
    @StringRes val topBarTitle: Int?,
    //検索バーを出すか否かを判断するBool値
    val isShowSearchBar: Boolean
) {
    //Splash画面
    object Splash : FindKunDestination("splash", null, false)

    //Main画面
    object Top : FindKunDestination("top", R.string.restaurantFindKun, true)

    //検索結果画面
    object  Result : FindKunDestination("result", R.string.resultFindKum, false)

    //レストラン詳細画面
    object  Detail : FindKunDestination("detail", R.string.detailFindKum, false)
}