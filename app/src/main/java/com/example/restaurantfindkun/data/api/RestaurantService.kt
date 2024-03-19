package com.example.restaurantfindkun.data.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantService {
    @GET("users/{username}/repos")
    fun loadRepos(
        @Path("username") username: String, @Query("sort") sort: String
    ): Call<ResponseBody>
}