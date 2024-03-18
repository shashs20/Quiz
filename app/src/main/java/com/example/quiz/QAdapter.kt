package com.example.quiz

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QAdapter(private val dataSet: ArrayList<Question>):
  RecyclerView.Adapter<QAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val problemTV: TextView = view.findViewById(R.id.problemTV)
        val choice1: TextView = view.findViewById(R.id.resultChoice1)
        val choice2: TextView = view.findViewById(R.id.resultChoice2)
        val choice3: TextView = view.findViewById(R.id.resultChoice3)
        val choice4: TextView = view.findViewById(R.id.resultChoice4)
        val givenAnswer: TextView = view.findViewById(R.id.givenAnswer)
        val correctAnswer: TextView = view.findViewById(R.id.correctAnswer)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.results_questions, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.problemTV.text = dataSet[position].problem
        holder.choice1.text = dataSet[position].choice1
        holder.choice2.text = dataSet[position].choice2
        holder.choice3.text = dataSet[position].choice3
        holder.choice4.text = dataSet[position].choice4
        holder.givenAnswer.text = "Your Answer: ${dataSet[position].selectedChoice}"
        holder.correctAnswer.text = "Correct Answer: ${dataSet[position].answer}"

        if (position%2 !=0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#000000"))
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#3c3f41"))
        }
    }
}



