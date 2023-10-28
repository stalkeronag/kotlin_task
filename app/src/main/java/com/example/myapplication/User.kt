package com.example.myapplication

import java.io.Serializable

data class User(
    val id:String,
    val first_name: String,
    val last_name: String,
    val bdate:String,
    val home_phone:String,
    val mobile_phone:String) : Serializable