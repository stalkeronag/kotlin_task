package com.example.myapplication

import com.google.gson.JsonArray
import java.io.File
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VkApiRequest {

    private val apiVersion:String

    private val accessToken:String

    constructor(versionApi: String, token:String){
        apiVersion = versionApi
        accessToken = token
    }

    fun GetUserInfoById(users:String): JsonArray {

        val fields = "education,contacts,city"
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.vk.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val vkService = retrofit.create(VKService::class.java)
        val call = vkService.getUsersInfo(users, fields, accessToken, apiVersion)
        val response = call.clone().execute()
        var jsonResponse = response.body()?.asJsonObject?.get("response")?.asJsonArray
        return jsonResponse!!
    }

    fun ConvertJsonToUser(jsonUsers:JsonArray):MutableList<User> {
        var list_users = mutableListOf<User>()
        if (jsonUsers != null){
            for(i in 0 until jsonUsers.size()){
                val jsonObject = jsonUsers[i].asJsonObject
                jsonObject.get("last_name")
                list_users.add(
                    User(
                        id = jsonObject.get("id").asString,
                        last_name = jsonObject.get("last_name").asString,
                        first_name = jsonObject.get("first_name").asString
                    )
                )
            }
        }
        return  list_users
    }
}