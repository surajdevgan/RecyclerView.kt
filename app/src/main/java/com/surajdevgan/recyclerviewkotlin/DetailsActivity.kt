package com.surajdevgan.recyclerviewkotlin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {

    lateinit var userName: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

          val userData = intent.getSerializableExtra("currentUserData") as UserModel?


        if(userData!=null)
        {
            userName = findViewById(R.id.uname)
            userName.setText(userData.first_name + userData.last_name)


        }
    }
}