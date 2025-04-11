package com.halawystory.askandanswer.mvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LevelsOpenEntityDataBase(
    @PrimaryKey(autoGenerate = true)
    var idLevel : Int = 0,
    var state : StateLevel,
    var difficult : Difficulty,
    var Level : Int,
    var solveQuestions : Int,
    var totalQuestions : Int,
)
enum class StateLevel{
    Closed , OpenProcess  , OpenDone
}
