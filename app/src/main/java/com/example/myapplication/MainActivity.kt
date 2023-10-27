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


class MainActivity : AppCompatActivity() {

    private lateinit var commandInfo:TextView

    private lateinit var commandText:EditText

    private lateinit var buttonAddUser:Button

    private lateinit var buttonDeleteUser:Button

    private lateinit var buttonShowUser:Button

    private lateinit var userList:RecyclerView

    private lateinit var userAdapter: UserAdapter

    private lateinit var users:MutableList<User>

    private lateinit var vkService:VkApiRequest

    private lateinit var userCrud: UserCrud

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Init()
        val versioApi = "5.154"
        val token = "vk1.a.zXR5PTs691yUSKduLPp30iSQfGLSaE-1SbS1XhMkmt32clyB64UVOh4-CtHDmnGIFQ9IJF3cIK52pTnKbF-bdAXrZ6ophRqSRxS-9VdtdO62OPdH5T06hLyI8iydApBsjbPorHgV4k-hFm9N4Zop2XyBqpT8v-0nKG1WVsC5ISumhbou6UlCfUfdWQXtVCfS3ZegSyktGWLyQmbKXbdYbQ"
        val userIds = "goophi,idpotcan,id218973646,brinst,id274417249"
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

    }

    fun Init(){
        commandText = findViewById(R.id.et_command)
        commandInfo = findViewById(R.id.tv_list_users)
        buttonAddUser = findViewById(R.id.b_add_users)
        buttonShowUser = findViewById(R.id.b_show_users)
        buttonDeleteUser = findViewById(R.id.b_delete_users)
        userList = findViewById(R.id.rv_user)
    }
}