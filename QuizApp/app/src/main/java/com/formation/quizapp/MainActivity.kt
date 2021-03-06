package com.formation.quizapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatEditText

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        val btnStart = findViewById<Button>(R.id.btn_start)

        val etName = findViewById<AppCompatEditText>(R.id.et_name)

        btnStart.setOnClickListener{
                if (etName.text.toString().isEmpty()) {
                    Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(this, QuizQuestionsActivity::class.java)
                    intent.putExtra(Constants.USER_NAME, etName.text.toString())
                    startActivity(intent)
                    finish()
                }
        }

    }
}