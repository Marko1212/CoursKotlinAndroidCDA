package com.formation.instagram

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserActivity : AppCompatActivity() {

    lateinit var userIntro: TextView
    lateinit var userEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

      //  val bottomNavigationView =  findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
       // val actionUser = bottomNavigationView.menu.getItem(2)
       // actionUser.isEnabled=true

        userIntro = findViewById(R.id.user_intro)
        userEditText = findViewById(R.id.edit_user_nickname)

        userIntro.text = getString(R.string.user_intro, "")

        userEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s?.length!! < 2) {
                    userIntro.setTextColor(Color.RED)
                } else {
                    userIntro.setTextColor(Color.GREEN)
                }

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                userIntro.text = getString(R.string.user_intro, s)

                Log.i("UserActivity", "Dans textChanged: $s")
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
}