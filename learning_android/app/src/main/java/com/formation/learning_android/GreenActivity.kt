package com.formation.learning_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class GreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_green)

        val action = intent.action
        val isUserViewer = intent.hasCategory("UserViewer")

        val extras: Bundle? = intent.extras
        val name = extras?.getString("name")
        val age = extras?.getInt("age")

        Log.i("GreenActivity", "action : $action, isUserViewer: $isUserViewer, name: $name, age: $age")

    }
}