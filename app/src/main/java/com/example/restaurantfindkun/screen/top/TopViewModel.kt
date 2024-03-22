package com.example.restaurantfindkun.screen.top

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.restaurantfindkun.data.api.RestaurantService
import com.example.restaurantfindkun.data.api.response.RestaurantItem
import com.example.restaurantfindkun.data.api.response.RestaurantItemListResponse
import com.example.restaurantfindkun.data.api.response.Shop
import com.example.restaurantfindkun.screen.base.BaseViewModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Inject

@HiltViewModel
class TopViewModel @Inject constructor() : BaseViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    companion object {
        private const val TAG = "MainActivity"
        private const val BASE_URL = "https://webservice.recruit.co.jp/hotpepper/"
    }

    private val _apiList = MutableStateFlow<List<Shop>>(emptyList())
    val apiList: StateFlow<List<Shop>> = _apiList

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(SimpleXmlConverterFactory.create()) // XML converterを追加
        .build()

    private val service = retrofit.create(RestaurantService::class.java)

    fun executeReposLoad() {
        val call = service.loadRepos()
        val requestUrl = call.request().url.toString() // リクエストのURLを取得

        Log.d(TAG, "Request URL: $requestUrl") // リクエストのURLをログに表示

        call.enqueue(object : Callback<RestaurantItemListResponse> {
            override fun onResponse(
                call: Call<RestaurantItemListResponse>,
                response: Response<RestaurantItemListResponse>
            ) {
                if (response.isSuccessful) {
                    val restaurantItemListResponse = response.body()
                    // レスポンスを適切に処理する
                    restaurantItemListResponse?.let { response ->
                        val shops = response.shops
                        shops?.forEach { shop ->
                            // ここで取得したデータを適切に処理する
                            println("Shop Name: ${shop.name}")
                            println("Shop Address: ${shop.address}")
                            // 必要に応じて他のデータも処理する
                        }
                        println("Total Shops: ${shops?.size}")
                    }
                } else {
                    val msg = "HTTP error. HTTP status code: ${response.code()}"
                    println("$TAG: $msg")
                }
            }

            override fun onFailure(
                call: Call<RestaurantItemListResponse>,
                t: Throwable
            ) {
                println("$TAG: Failed to fetch data")
                t.printStackTrace()
            }
        })
    }

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