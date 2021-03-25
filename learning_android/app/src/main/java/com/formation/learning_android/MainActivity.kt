package com.formation.learning_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnOpenActivityGreen: Button = findViewById(R.id.btn_open_activity_green)

        btnOpenActivityGreen.setOnClickListener{println("Start green activity")}


    }

    }