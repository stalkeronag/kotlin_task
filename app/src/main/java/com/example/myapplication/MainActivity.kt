package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private lateinit var commandInfo:TextView

    private lateinit var commandText:EditText

    private lateinit var buttonAddUser:Button

    private lateinit var buttonDeleteUser:Button

    private lateinit var buttonShowUser:Button

    private lateinit var buttonFindUser:Button

    private lateinit var userList:RecyclerView

    private lateinit var userAdapter: UserAdapter

    private lateinit var users:MutableList<User>

    private lateinit var vkService:VkApiRequest

    private lateinit var userCrud: UserCrud

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Init()
        val versioApi = getResources().getString(R.string.api_version);
        val token = getResources().getString(R.string.token_api_vk);
        val userIds = "218973646,goophi,idpotcan,brinst,274417249"
        val layoutManager = LinearLayoutManager(this)

        userList.layoutManager = layoutManager
        vkService = VkApiRequest(versioApi,token)
        GlobalScope.launch(Dispatchers.IO){
            val jsonUser = async { vkService.GetUserInfoById(userIds)}
            users = vkService.ConvertJsonToUser(jsonUser.await())
            userCrud = UserCrud(users)
        }
        buttonShowUser.setOnClickListener {
            commandInfo.text = "list users"
            userAdapter = UserAdapter(userCrud.GetUsers())
            userList.adapter = userAdapter
        }

        buttonDeleteUser.setOnClickListener {
            commandInfo.text = "command remove"
            userCrud.DeleteUser(commandText.text.toString())
            userAdapter = UserAdapter(userCrud.GetUsers())
            userList.adapter = userAdapter
        }

        buttonAddUser.setOnClickListener {
            commandInfo.text = "command add"
            GlobalScope.launch(Dispatchers.IO){
                val jsonUser = async { vkService.GetUserInfoById(commandText.text.toString())}
                var result = vkService.ConvertJsonToUser(jsonUser.await())
                userAdapter = UserAdapter(userCrud.AddUsers(result))
            }
            Thread.sleep(1000)
            userList.adapter = userAdapter
        }

        buttonFindUser.setOnClickListener {
            commandInfo.text = "find user"
            userAdapter = UserAdapter(userCrud.FindUsers(commandText.text.toString()))
            userList.adapter = userAdapter
        }
    }
    fun Init(){
        commandText = findViewById(R.id.et_command)
        commandInfo = findViewById(R.id.tv_list_users)
        buttonAddUser = findViewById(R.id.b_add_users)
        buttonShowUser = findViewById(R.id.b_show_users)
        buttonDeleteUser = findViewById(R.id.b_delete_users)
        buttonFindUser = findViewById(R.id.b_find_users)
        userList = findViewById(R.id.rv_user)
    }
}