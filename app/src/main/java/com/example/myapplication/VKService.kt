package com.example.myapplication

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface VKService {
    @GET("method/users.get")
    fun getUsersInfo(
        @Query("user_ids") user_ids: String,
        @Query("fields") fields: String,
        @Query("access_token") accessToken: String,
        @Query("v") apiVersion: String
    ): Call<JsonElement>
}