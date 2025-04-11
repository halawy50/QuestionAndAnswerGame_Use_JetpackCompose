package com.halawystory.askandanswer.mvvm.model

import com.halawystory.askandanswer.mvvm.model.Difficulty.*

enum class Difficulty { EASY, MEDIUM, HARD, EXPERT }

object  CovertDifficulty{
    fun convertDifficulty(difficulty: Difficulty): String {
        return when (difficulty) {
            EASY -> "مبتدئ"
            MEDIUM -> "متوسط"
            HARD -> "صعب"
            EXPERT -> "خبير"
        }
    }

}
