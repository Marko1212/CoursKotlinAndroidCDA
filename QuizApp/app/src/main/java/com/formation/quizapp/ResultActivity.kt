package com.formation.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)


        // START
        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val userName = intent.getStringExtra(Constants.USER_NAME)
        findViewById<TextView>(R.id.tv_name).text = userName

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        findViewById<TextView>(R.id.tv_score).text = "Your Score is $correctAnswers out of $totalQuestions."

        findViewById<Button>(R.id.btn_finish).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        // END
    }
}