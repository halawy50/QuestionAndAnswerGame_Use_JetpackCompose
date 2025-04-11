package com.halawystory.askandanswer.mvvm.model

data class Question(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctAnswer: String,
    val category: Category,
    val difficulty: Difficulty
)
