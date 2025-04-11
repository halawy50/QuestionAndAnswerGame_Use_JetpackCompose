package com.halawystory.askandanswer.mvvm.service.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.halawystory.askandanswer.mvvm.model.LevelsOpenEntityDataBase
import com.halawystory.askandanswer.mvvm.model.SolveQuestionsEntityDataBase


@Database(entities = [
    SolveQuestionsEntityDataBase::class ,
    LevelsOpenEntityDataBase::class
] , version = 1)
@TypeConverters(Converters::class)

abstract class QuesionDataBase :  RoomDatabase(){

    companion object{
        const val NAME = "QUESTOINSDN"
    }
    abstract  fun  getQuestionsDao(): QuestionsDao

}