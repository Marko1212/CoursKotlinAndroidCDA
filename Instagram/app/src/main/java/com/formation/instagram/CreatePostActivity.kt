package com.formation.instagram

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CreatePostActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var sp: SharedPreferences
    lateinit var bottomNavigationView: BottomNavigationView

    lateinit var nickname: TextView
    lateinit var description: EditText
    lateinit var descriptionCount: TextView
    lateinit var btnOpenGallery: Button
    lateinit var photoBlur: ImageView
    lateinit var photo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        bottomNavigationViewAction()
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener(this)

        sp = getSharedPreferences("User", Context.MODE_PRIVATE)

        nickname = findViewById(R.id.nickname)
        description = findViewById(R.id.description)
        descriptionCount = findViewById(R.id.description_count)
        btnOpenGallery = findViewById(R.id.btn_open_gallery)
        photoBlur = findViewById(R.id.photo_blur)
        photo = findViewById(R.id.photo)

        nickname.text = sp.getString("nickname", "")
        getDescriptionForTextLength()



    }

    override fun onClick(v: View) {

    }

    private fun getDescriptionForTextLength() {

        descriptionCount.text = getString(R.string.description_text_length, 0)
        description.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            descriptionCount.text = getString(R.string.description_text_length, s?.length)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun bottomNavigationViewAction(){
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)

        bottomNavigationView
            .menu
            .findItem(R.id.action_void).isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.action_home -> {
                    val intent = Intent(this, PostListActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.action_user -> {
                    val intent = Intent(this, UserActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }

        }
    }

}