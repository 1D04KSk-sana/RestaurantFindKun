package com.example.restaurantfindkun.screen.top

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.restaurantfindkun.data.api.RestaurantService
import com.example.restaurantfindkun.screen.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class TopViewModel @Inject constructor() : BaseViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    companion object {
        private const val TAG = "MainActivity"
        private const val BASE_URL = "http://webservice.recruit.co.jp/hotpepper/gourmet/v1/"
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .build()

    private val service = retrofit.create(RestaurantService::class.java)

    private val loadRepos = service.loadRepos("tatsuroappdev", "query")

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

    fun executeReposLoad() {
        loadRepos.clone().enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    response.body()?.string()?.let { json ->
                        Log.d(TAG, json)
                    }
                } else {
                    val msg = "HTTP error. HTTP status code: ${response.code()}"
                    Log.e(TAG, msg)
                }
            }

            override fun onFailure(
                call: Call<ResponseBody>,
                t: Throwable
            ) {
                Log.e(TAG, t.toString())
            }
        })
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