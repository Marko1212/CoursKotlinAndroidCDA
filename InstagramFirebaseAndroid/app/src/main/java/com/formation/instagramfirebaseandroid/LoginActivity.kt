package com.formation.instagramfirebaseandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            if(email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Email/mot de passe doivent être renseignés!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Vérification de l'authentification Firebase


        }
    }
}