package com.formation.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition:Int = 1

    private var mQuestionsList: ArrayList<Question>? = null

    private var mSelectedOptionPosition: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mQuestionsList = Constants.getQuestions()

        setQuestion()

        val tvOptionOne = findViewById<TextView>(R.id.tv_option_one)
        tvOptionOne.setOnClickListener(this)

        val tvOptionTwo = findViewById<TextView>(R.id.tv_option_two)
        tvOptionTwo.setOnClickListener(this)

        val tvOptionThree = findViewById<TextView>(R.id.tv_option_three)
        tvOptionThree.setOnClickListener(this)

        val tvOptionFour = findViewById<TextView>(R.id.tv_option_four)
        tvOptionFour.setOnClickListener(this)




    }

    private fun setQuestion() {

        mCurrentPosition = 1
        val question = mQuestionsList!![mCurrentPosition - 1]

        defaultOptionsView()

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        progressBar.progress = mCurrentPosition

        val tvProgress = findViewById<TextView>(R.id.tv_progress)

        tvProgress.text = "$mCurrentPosition" + "/" + progressBar.max

        val tvQuestion = findViewById<TextView>(R.id.tv_question)

        tvQuestion.text = question!!.question

        val ivImage = findViewById<ImageView>(R.id.iv_image)

        ivImage.setImageResource(question.image)

        val tvOptionOne = findViewById<TextView>(R.id.tv_option_one)
        tvOptionOne.text = question.optionOne

        val tvOptionTwo = findViewById<TextView>(R.id.tv_option_two)
        tvOptionTwo.text = question.optionTwo

        val tvOptionThree = findViewById<TextView>(R.id.tv_option_three)
        tvOptionThree.text = question.optionThree

        val tvOptionFour = findViewById<TextView>(R.id.tv_option_four)
        tvOptionFour.text = question.optionFour


    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()

        val tvOptionOne = findViewById<TextView>(R.id.tv_option_one)
        val tvOptionTwo = findViewById<TextView>(R.id.tv_option_two)
        val tvOptionThree = findViewById<TextView>(R.id.tv_option_three)
        val tvOptionFour = findViewById<TextView>(R.id.tv_option_four)

        options.add(0, tvOptionOne)
        options.add(1, tvOptionTwo)
        options.add(2, tvOptionThree)
        options.add(3, tvOptionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)

        }

    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}