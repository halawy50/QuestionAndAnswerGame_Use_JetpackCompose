package com.halawystory.askandanswer.mvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SolveQuestionsEntityDataBase (
    @PrimaryKey(autoGenerate = true)  var idSolveQuestions : Int = 0,
    var idQuestion : Int,
    var question : String,
    var difficulty: Difficulty,

)