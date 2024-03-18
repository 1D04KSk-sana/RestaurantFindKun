package com.example.restaurantfindkun.screen.top

import com.example.restaurantfindkun.screen.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TopViewModel @Inject constructor() : BaseViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: SearchBarEvent) {
        when (event) {
            is SearchBarEvent.QueryChange -> {}

            is SearchBarEvent.Back -> {}

            is SearchBarEvent.Cancel -> {}

        }
    }
}

sealed class SearchBarEvent {
    data class QueryChange(val query: String) : SearchBarEvent()
    object Back : SearchBarEvent()
    object Cancel : SearchBarEvent()
}

data class UiState(
    val query: String = "",
    val isQuerying: Boolean = false,
)