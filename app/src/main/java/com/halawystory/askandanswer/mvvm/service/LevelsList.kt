package com.halawystory.askandanswer.mvvm.service

import com.halawystory.askandanswer.R
import com.halawystory.askandanswer.mvvm.model.CovertDifficulty
import com.halawystory.askandanswer.mvvm.model.Difficulty
import com.halawystory.askandanswer.mvvm.model.LevelsModels
import com.halawystory.askandanswer.mvvm.model.StateLevel

object LevelsList {
    val livelsList = listOf(
        LevelsModels(
            idLevel = 0
            , nameLevel = "${CovertDifficulty.convertDifficulty(Difficulty.EASY)}"
            , imageLvel = R.drawable.oldhouse
            , difficult = Difficulty.EASY
            , state = StateLevel.OpenProcess
            , solveQuestions = 1
            , totalQuestions = 40
        ),
        LevelsModels(
            idLevel = 1
            , nameLevel = "${CovertDifficulty.convertDifficulty(Difficulty.MEDIUM)}"
            , imageLvel = R.drawable.meduim
            , difficult = Difficulty.MEDIUM
            , state = StateLevel.Closed
            , solveQuestions = 0
            , totalQuestions = 40
        ),
        LevelsModels(
            idLevel = 2
            , nameLevel = "${CovertDifficulty.convertDifficulty(Difficulty.HARD)}"
            , imageLvel = R.drawable.high
            , difficult = Difficulty.HARD
            , state = StateLevel.Closed
            , solveQuestions = 0
            , totalQuestions = 40
        ),
        LevelsModels(
            idLevel = 3
            , nameLevel = "${CovertDifficulty.convertDifficulty(Difficulty.EXPERT)}"
            , imageLvel = R.drawable.expirt
            , difficult = Difficulty.EXPERT
            , state = StateLevel.Closed
            , solveQuestions = 0
            , totalQuestions = 40
        ),
    )
}