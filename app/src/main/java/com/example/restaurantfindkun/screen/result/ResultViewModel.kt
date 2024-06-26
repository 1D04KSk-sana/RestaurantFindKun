package com.example.restaurantfindkun.screen.result

import android.util.Log
import com.example.restaurantfindkun.BuildConfig
import com.example.restaurantfindkun.data.api.RestaurantService
import com.example.restaurantfindkun.data.api.response.RestaurantItemListResponse
import com.example.restaurantfindkun.data.api.response.Shop
import com.example.restaurantfindkun.screen.base.BaseViewModel
import com.example.restaurantfindkun.screen.component.CompanionObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Inject

//
//検索結果画面
//
@HiltViewModel
class ResultViewModel @Inject constructor() : BaseViewModel() {
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

    fun executeReposLoad(
        id: String?,
        latitude: String?,
        longitude: String?,
        range: String?
    ) {
        Log.d("Test result", CompanionObject.positionLatitude)
        val call = service.loadRepos(BuildConfig.API_KEY, id, null, latitude, longitude, range)
        val requestUrl = call.request().url.toString() // リクエストのURLを取得

        Log.d(TAG, "Request URL: $requestUrl") // リクエストのURLをログに表示

        call.enqueue(object : Callback<RestaurantItemListResponse> {
            override fun onResponse(
                call: Call<RestaurantItemListResponse>,
                response: Response<RestaurantItemListResponse>
            ) {
                if (response.isSuccessful) {
                    val restaurantItemListResponse = response.body()
                    val shopList = mutableListOf<Shop>()
                    // レスポンスを適切に処理する
                    restaurantItemListResponse?.let { response ->
                        val shops = response.shops
                        shops?.forEach { shop ->
                            // ここで取得したデータを適切に処理する
                            // 必要に応じて他のデータも処理する
                            shopList.add(shop)
                        }
                        println("Total Shops: ${shops?.size}")
                        _apiList.value = shopList
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
}