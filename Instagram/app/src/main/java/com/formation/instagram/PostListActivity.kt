package com.formation.instagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.formation.instagram.adapters.PostAdapter
import com.formation.instagram.models.PostModel
import com.formation.instagram.models.UserModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PostListActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var posts: MutableList<PostModel>

    lateinit var adapter: PostAdapter

    lateinit var bottomNavigationView: BottomNavigationView

    lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        bottomNavigationViewAction()
        fab = findViewById(R.id.fab)
        fab.setOnClickListener(this)

        fillPostList()

        adapter = PostAdapter(posts)
        val rv = findViewById<RecyclerView>(R.id.recycler_view)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter


    }

    override fun onClick(v: View) {

        if (v.id == R.id.fab) {
            val intent = Intent(this, CreatePostActivity::class.java)
            startActivity(intent)
        }

    }

    private fun bottomNavigationViewAction(){
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)

        bottomNavigationView
                .menu
                .findItem(R.id.action_home).isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            Log.i("PostListActivity", "click sur action_user")
            when (item.itemId) {
                R.id.action_home -> {
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

    private fun fillPostList() {
        posts = mutableListOf()

        posts.add(
            PostModel(1L, UserModel("marko", null), null, listOf("marko","toto","titi"), "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque, consequatur dele")
        )
        posts.add(
            PostModel(2L, UserModel("marko askovic", null),
                null, listOf("marko","toto","titi", "y", "u", "o", "i"),
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque, consequatur dele")
        )

    }
}