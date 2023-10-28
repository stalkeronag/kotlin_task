package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

@Suppress("DEPRECATION")
class SecondActivity : AppCompatActivity() {

    private lateinit var textId:TextView

    private lateinit var textFirstName:TextView

    private lateinit var textLastName:TextView

    private lateinit var textBirthdate:TextView

    private lateinit var textHomePhone:TextView

    private lateinit var textMobilePhone:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Init()
        val user = intent.getSerializableExtra("user") as User
        textId.text = user.id
        textFirstName.text = user.first_name
        textLastName.text = user.last_name
        textBirthdate.text = user.bdate
        textHomePhone.text = user.home_phone
        textMobilePhone.text = user.mobile_phone
    }

    private fun Init(){
        textId = findViewById(R.id.id_raw)
        textFirstName = findViewById(R.id.first_name_raw)
        textLastName = findViewById(R.id.last_name_raw)
        textBirthdate = findViewById(R.id.bdate_raw)
        textMobilePhone = findViewById(R.id.mobile_phone_raw)
        textHomePhone = findViewById(R.id.home_phone_raw)
    }
}