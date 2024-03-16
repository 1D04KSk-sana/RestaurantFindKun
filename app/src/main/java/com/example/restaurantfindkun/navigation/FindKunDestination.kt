package com.example.restaurantfindkun.navigation

import androidx.annotation.StringRes

sealed class FindKunDestination(
    var route: String,
    @StringRes val topBarTitle: Int?,
    //検索バーを出すか否かを判断するBool値
    val isShowSearchBar: Boolean
) {
    object Splash : FindKunDestination("splash", null, false)
}