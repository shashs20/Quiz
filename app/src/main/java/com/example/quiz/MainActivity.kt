package com.example.quiz

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.easyBtn?.setOnClickListener { startGame("easy") }
        binding?.mediumBtn?.setOnClickListener { startGame("medium") }
        binding?.hardBtn?.setOnClickListener { startGame("hard") }

        val mainGrad = findViewById<ConstraintLayout>(R.id.mainGrad)
        val animationDrawable = mainGrad.background as AnimationDrawable

        animationDrawable.apply {
            setEnterFadeDuration(1000)
            setExitFadeDuration(2000)
            start()
        }
    }

    private fun startGame(questionType:String)
    {
        val intent = Intent(this, PlayQuiz::class.java)
        intent.putExtra("questionType", questionType)
        startActivity(intent)
    }
}