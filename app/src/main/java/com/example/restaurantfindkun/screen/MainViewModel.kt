package com.example.restaurantfindkun.screen

import com.example.restaurantfindkun.navigation.FindKunDestination
import com.example.restaurantfindkun.screen.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

//メイン画面

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {
    //あとでFindKunDestination.Splashに変える
    private val _currentDestination = MutableStateFlow<FindKunDestination>(FindKunDestination.Top)
    val currentDestination: StateFlow<FindKunDestination> = _currentDestination

    fun setCurrentDestination(destination: FindKunDestination) {
        _currentDestination.value = destination
    }
}