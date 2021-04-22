package com.formation.instagramfirebaseandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.formation.instagramfirebaseandroid.models.Post
import com.formation.instagramfirebaseandroid.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_posts.*

private const val TAG = "PostsActivity"
private const val EXTRA_USERNAME = "EXTRA_USERNAME"

open class PostsActivity : AppCompatActivity() {

    private var signedInUser: User? = null
    private lateinit var firestoreDb: FirebaseFirestore
    private lateinit var posts: MutableList<Post>
    private lateinit var adapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        // Créer un fichier layout pour représenter un post - OK
        // Créer une data source - OK
        posts = mutableListOf()
        // Créer l'adaptateur - OK
        adapter = PostsAdapter(this, posts)
        // Lier l'adaptateur avec le layout manager pour le Recycler View - OK
        rvPosts.adapter = adapter
        rvPosts.layoutManager = LinearLayoutManager(this)
        firestoreDb = FirebaseFirestore.getInstance()

        firestoreDb.collection("users")
                .document(FirebaseAuth.getInstance().currentUser?.uid as String)
                .get()
                .addOnSuccessListener { userSnapshot ->
                    signedInUser = userSnapshot.toObject(User::class.java)
                    Log.i(TAG, "utilisateur connecté : $signedInUser")
                }
                .addOnFailureListener { exception ->
                    Log.i(TAG, "Echec de la récupération de l'utilisateur connecté", exception)
                }



        var postsReference = firestoreDb.collection("posts")
            .limit(20)
            .orderBy("creation_time_ms", Query.Direction.DESCENDING)

        val username = intent.getStringExtra(EXTRA_USERNAME)

        if (username != null) {
            supportActionBar?.title = username
            postsReference = postsReference.whereEqualTo("user.username", username)
        }

        postsReference.addSnapshotListener{ snapshot, exception ->
            if (exception !=null || snapshot == null) {
                Log.e(TAG, "Une erreur est survenue lors de la requête des posts", exception)
                return@addSnapshotListener
            }
            val postList = snapshot.toObjects(Post::class.java)
            posts.clear()
            posts.addAll(postList)
            adapter.notifyDataSetChanged()
            for (post in postList) {
                Log.i(TAG, "Post ${post}")
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_posts, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_profile) {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra(EXTRA_USERNAME, signedInUser?.username)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }

}