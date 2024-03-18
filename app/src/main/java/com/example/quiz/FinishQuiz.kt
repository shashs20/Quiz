package com.example.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FinishQuiz : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_quiz)

        val score = intent.getIntExtra("score", 0)
        val data:ArrayList<Question> =  intent.getSerializableExtra("dataSet") as ArrayList<Question>

        val scoreTV:TextView = findViewById(R.id.score_tv)
        scoreTV.text = "Your Score \n$score/10"

        setAdapterRecyclerView(data)

        val btnHome : Button = findViewById(R.id.btnHome)
        btnHome.setOnClickListener { finish() }
    }

    private fun setAdapterRecyclerView(data:ArrayList<Question>){
        val recyclerView: RecyclerView = findViewById(R.id.qView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = QAdapter(data)
        recyclerView.adapter = adapter
    }
}