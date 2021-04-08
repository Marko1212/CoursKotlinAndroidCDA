package com.formation.instagram

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserActivity : AppCompatActivity() {

    lateinit var userIntro : TextView
    lateinit var userEditText: EditText

    private lateinit var sharedPreferences: SharedPreferences

    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        bottomNavigationViewAction()

        sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val actionUser = bottomNavigationView.menu.findItem(R.id.action_user)
        actionUser.isChecked = true

        userIntro = findViewById(R.id.user_intro)
        userEditText = findViewById(R.id.user_edit_nickname)

        userIntro.text = getString(R.string.user_intro, getNicknameSharedPreference())
        userEditText.hint = getNicknameSharedPreference()

        userEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if(s?.length!! < 2) {
                    userIntro.setTextColor(Color.RED)
                } else {
                    userIntro.setTextColor(Color.GREEN)
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                userIntro.text = getString(R.string.user_intro, s.toString())
                setNicknameSharedPreference(s.toString())
                Log.i("UserActivity", "Dans textChanged: ${getNicknameSharedPreference()}")
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun getNicknameSharedPreference(): String? {
        return sharedPreferences.getString("nickname", "")
    }

    private fun setNicknameSharedPreference(nickname: String) {
        val editor = sharedPreferences.edit()
        editor.putString("nickname", nickname)
        editor.apply()
    }

    private fun bottomNavigationViewAction(){
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.action_home -> {
//                    val intent = Intent(this, PostListActivity::class.java)
//                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}