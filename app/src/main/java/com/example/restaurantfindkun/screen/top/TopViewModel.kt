package com.example.restaurantfindkun.screen.top

import androidx.lifecycle.viewModelScope
import com.example.restaurantfindkun.screen.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopViewModel @Inject constructor() : BaseViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: SearchBarEvent) {
        when (event) {
            //テキストが変更されたときのイベント
            is SearchBarEvent.QueryChange -> {
                var isQuerying = false
                val SearchList = mutableListOf<String>()
                viewModelScope.launch {
                    if (event.query.isNotEmpty()) {
                        isQuerying = true
//                        SearchList.addAll(
//                            _storeList.filter {
//                                it.name.startsWith(
//                                    prefix = event.query,
//                                    ignoreCase = true
//                                ) || it.nameOfKana.startsWith(
//                                    prefix = event.query,
//                                    ignoreCase = true
//                                )
//                            }
//                        )
                    } else {
                        SearchList.addAll(_storeList)
                    }

                    _uiState.value =
                        _uiState.value.copy(
                            query = event.query,
                            isQuerying = isQuerying,
                            historyTestList = SearchList
                        )
                }
            }

            //selectイベントまだ作ってないのでまた作る

            //検索バーを非アクティブにするときのイベント
            is SearchBarEvent.Back -> {
                _uiState.value = _uiState.value.copy(
                    selected = null
                )
            }

            //検索条件をクリアしたときのイベント
            is SearchBarEvent.Cancel -> {
                _uiState.value =
                    _uiState.value.copy(
                        query = "",
                        isQuerying = false,
                        selected = null,
                        historyTestList = _history
                    )
            }
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
    val selected: String? = null,    //一旦StringにしてるからDataset作ってから
    val historyTestList: List<String> = _history,
    val storeList: List<String> = _storeList,    //一旦仮データでおいてる
)

private val _history = listOf(
    "履歴1",
    "履歴2",
    "履歴3",
    "履歴4"
)

private val _storeList = listOf(
    "検索1",
    "検索2",
    "検索3",
    "検索4"
)