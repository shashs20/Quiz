package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.quiz.databinding.ActivityPlayQuizBinding

class PlayQuiz : AppCompatActivity() {
    private var binding:ActivityPlayQuizBinding? = null

    private var position = 0
    private var timer:CountDownTimer? = null
    private var timeGiven = 0
    private var score = 0
    private var questionDataList = ArrayList<Question>(10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayQuizBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val questionType = intent.getStringExtra("questionType")
        questionDataList = QList(questionType).getQuestionList()

        setGivenTime(questionType)
        updateQuestion()
        updateOption()
        updateHProgressBar()
        startTimer()

        binding?.btnChoice1?.setOnClickListener { onSelectOption(binding?.btnChoice1?.text.toString()) }
        binding?.btnChoice2?.setOnClickListener { onSelectOption(binding?.btnChoice2?.text.toString()) }
        binding?.btnChoice3?.setOnClickListener { onSelectOption(binding?.btnChoice3?.text.toString()) }
        binding?.btnChoice4?.setOnClickListener { onSelectOption(binding?.btnChoice4?.text.toString()) }
    }

    private fun updateQuestion()
    {
        binding?.questionView?.text = questionDataList[position].problem
    }

    private fun updateOption()
    {
        binding?.btnChoice1?.text = questionDataList[position].choice1
        binding?.btnChoice2?.text = questionDataList[position].choice2
        binding?.btnChoice3?.text = questionDataList[position].choice3
        binding?.btnChoice4?.text = questionDataList[position].choice4
    }

    private fun updateHProgressBar()
    {
        binding?.hProgressBar?.incrementProgressBy(1)
    }

    private fun setGivenTime(level:String?)
    {
        timeGiven = when(level)
        {
            "easy"-> 10000
            "medium"-> 12000
            else-> 15000
        }
    }

    private fun startTimer()
    {
        var count = timeGiven/1000
        binding?.circleTimer?.progress = timeGiven/1000
        binding?.circleTimer?.max = timeGiven/1000

        timer = object : CountDownTimer(timeGiven.toLong(), 1000)
        {
            override fun onTick(p0: Long) {
                binding?.circleTimer?.incrementProgressBy(-1)
                count--
                binding?.circleTimeDown?.text = count.toString()
            }

            override fun onFinish()
            {
                setNextRound()
            }
        }.start()
    }

    private fun onSelectOption(option:String)
    {
        if (option==questionDataList[position].answer)
            score++
        questionDataList[position].selectedChoice = option
        setNextRound()
    }

    private fun setNextRound()
    {
        if (position<9)
        {
            position++
            timer?.cancel()
            timer = null
            updateHProgressBar()
            updateQuestion()
            updateOption()
            startTimer()
        }
        else
            endGame ()
    }

    private fun endGame()
    {
        val intent = Intent(this, FinishQuiz::class.java)
        intent.putExtra("score", score)
        intent.putExtra("dataSet", questionDataList)
        startActivity(intent)
        finish()
        timer?.cancel()
        timer = null
        binding = null
    }

}