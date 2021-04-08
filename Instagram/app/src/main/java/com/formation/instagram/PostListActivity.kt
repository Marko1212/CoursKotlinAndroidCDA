package com.formation.instagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.formation.instagram.adapters.PostAdapter
import com.formation.instagram.models.PostModel
import com.formation.instagram.models.UserModel

class PostListActivity : AppCompatActivity() {

    lateinit var posts: MutableList<PostModel>

    lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        posts = mutableListOf()

        posts.add(
                PostModel(1L, UserModel("marko", null), null, listOf("marko","toto","titi"), "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque, consequatur dele")
        )
        posts.add(
                PostModel(2L, UserModel("marko askovic", null), null, listOf("marko","toto","titi", "y", "u", "o", "i"), "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque, consequatur dele")
        )
    }
}