package com.example.myapplication

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserCrud {


    private var users:MutableList<User>

    constructor(listUser:MutableList<User>){
        users = listUser

    }

    fun GetUsers(): MutableList<User> {
        return users
    }

    fun AddUsers(addUsers:MutableList<User>) : MutableList<User>{
        users.addAll(addUsers)
        return users
    }

    fun DeleteUser(id:String) : MutableList<User> {
        var lengthUser = users.size
        var i = 0
        while (i < lengthUser){
            if (users[i].id == id){
                users.removeAt(i)
                i--
                lengthUser--
            }
            i++
        }

        return users
    }

    fun FindUsers(id:String) : MutableList<User> {
        var result = mutableListOf<User>()

        for (i in 0 until users.size){
            if (users[i].id.equals(id)){
                result.add(users[i])
            }
        }

        return  result
    }

}