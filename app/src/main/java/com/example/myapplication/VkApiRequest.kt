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

        val fields = "contacts,bdate"
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
        var list_keys = mutableListOf<String>(
            "bdate",
            "mobile_phone",
            "home_phone"
        )


        if (jsonUsers != null){
            for(i in 0 until jsonUsers.size()){
                val jsonObject = jsonUsers[i].asJsonObject
                for (i in 0 until list_keys.size){
                    if (jsonObject.get(list_keys[i]) != null){
                        list_keys[i] = jsonObject.get(list_keys[i]).asString
                    }
                    else{
                        list_keys[i] = "not found"
                    }
                }
                list_users.add(
                    User(
                        id = jsonObject.get("id").asString,
                        last_name = jsonObject.get("last_name").asString,
                        first_name = jsonObject.get("first_name").asString,
                        bdate = list_keys[0],
                        mobile_phone = list_keys[1],
                        home_phone = list_keys[2]
                    )
                )
            }
        }
        return  list_users
    }
}