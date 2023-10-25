package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {

    private lateinit var listUsers:TextView

    private lateinit var buttonAddUser:Button

    private lateinit var userList:RecyclerView

    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listUsers = findViewById(R.id.tv_list_users)
        buttonAddUser = findViewById(R.id.b_add_users)
        userList = findViewById(R.id.rv_user)

        val layoutManager:LinearLayoutManager = LinearLayoutManager(this)
        userList.layoutManager = layoutManager
        userList.setHasFixedSize(true)

        buttonAddUser.setOnClickListener {
            listUsers.text = "hello"
        }

        var users:MutableList<User> = arrayListOf(
            User("Kostya", "ovovouovryms@gmail.com"),
            User("Nikita", "dogcatcupoftea@gmail.com"),
            User("Kostya", "ovovouovryms@gmail.com"),
            User("Nikita", "dogcatcupoftea@gmail.com"),
            User("Kostya", "ovovouovryms@gmail.com"),
            User("Nikita", "dogcatcupoftea@gmail.com"),
            User("Kostya", "ovovouovryms@gmail.com"),
            User("Nikita", "dogcatcupoftea@gmail.com"),
            User("Kostya", "ovovouovryms@gmail.com"),
            User("Nikita", "dogcatcupoftea@gmail.com"),
            User("Kostya", "ovovouovryms@gmail.com"),
            User("Nikita", "dogcatcupoftea@gmail.com"),
            User("Kostya", "ovovouovryms@gmail.com"),
            User("Nikita", "dogcatcupoftea@gmail.com"),
            User("Kostya", "ovovouovryms@gmail.com"),
            User("Nikita", "dogcatcupoftea@gmail.com"),
            User("Kostya", "ovovouovryms@gmail.com"),
            User("Nikita", "dogcatcupoftea@gmail.com")
        )
        userAdapter = UserAdapter(users)
        userList.adapter = userAdapter
    }
    }