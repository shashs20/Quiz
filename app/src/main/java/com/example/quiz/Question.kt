package com.example.quiz

import java.io.Serializable

data class Question(
    val problem:String,
    val answer:String,
    val choice1:String,
    val choice2:String,
    val choice3:String,
    val choice4:String,
    var selectedChoice:String,
):Serializable
