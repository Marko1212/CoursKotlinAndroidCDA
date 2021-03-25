package com.formation.learning_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class UserDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        val user = intent.getParcelableExtra<UserModel>("user")

        findViewById<TextView>(R.id.user_name).text = "User name : ${user?.name}"
        findViewById<TextView>(R.id.user_age).text = "User age : ${user?.age}"
    }
}