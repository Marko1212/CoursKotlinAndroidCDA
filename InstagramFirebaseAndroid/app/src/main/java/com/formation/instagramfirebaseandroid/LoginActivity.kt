package com.formation.instagramfirebaseandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

private const val TAG = "LoginActivity"

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            goPostsActivity()
        }
        btnLogin.setOnClickListener {
            btnLogin.isEnabled = false
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            if(email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Email/mot de passe doivent être renseignés!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Vérification de l'authentification Firebase
            auth.signInWithEmailAndPassword(email, password).
                    addOnCompleteListener {
                        task ->
                        btnLogin.isEnabled = true
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Succès!", Toast.LENGTH_SHORT).show()
                            goPostsActivity()
                    } else {
                        Log.i(TAG, "L'identification avec l'email et le mot de passe a échoué", task.exception)
                        Toast.makeText(this, "L'authentification a échoué", Toast.LENGTH_SHORT).show()
                    }
                    }
        }
    }

    private fun goPostsActivity() {
        Log.i(TAG, "goPostsActivity")
        val intent = Intent(this, PostsActivity::class.java)
        startActivity(intent)
        finish()
    }
}