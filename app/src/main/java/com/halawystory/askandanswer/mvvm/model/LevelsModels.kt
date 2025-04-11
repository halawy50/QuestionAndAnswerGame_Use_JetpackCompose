package com.halawystory.askandanswer.mvvm.model


data class LevelsModels (
    var idLevel : Int,
    var nameLevel : String,
    var imageLvel : Int,
    var state : StateLevel,
    var difficult : Difficulty,
    var solveQuestions : Int,
    var totalQuestions : Int,


    )