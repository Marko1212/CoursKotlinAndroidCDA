package com.formation.learning_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

   // private val TAG = MainActivity::class.java.simpleName

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnOpenActivityGreen: Button = findViewById(R.id.btn_open_activity_green)

        btnOpenActivityGreen.setOnClickListener{
            val intent = Intent(this, GreenActivity::class.java)
            intent.action = Intent.ACTION_VIEW
            intent.addCategory("UserViewer")

            intent.putExtra("name", "Marko")
            intent.putExtra("age", 47)

            startActivity(intent)
            println("Start green activity")

        }

        Log.v(TAG, "verbose log")
        Log.d(TAG, "debug log")
        Log.i(TAG, "info log")
        Log.w(TAG, "warning log")
        Log.e(TAG, "error log")
        Log.println(Log.ASSERT, TAG, "assert log")

    }


     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_start_activity_user_details).setOnClickListener{
            val intent = Intent(this, UserDetailsActivity::class.java)

            intent.putExtra("user", UserModel("Marko", 47))

            startActivity(intent)
        }
    }

    }