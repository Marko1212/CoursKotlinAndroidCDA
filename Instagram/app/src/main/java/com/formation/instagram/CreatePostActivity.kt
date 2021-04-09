package com.formation.instagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CreatePostActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        bottomNavigationViewAction()
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener(this)
    }

    override fun onClick(v: View?) {

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