package com.halawystory.askandanswer

import android.app.Application
import androidx.room.Room
import com.halawystory.askandanswer.mvvm.service.db.QuesionDataBase

class AppQuestion:Application() {

    companion object{
        lateinit var questionsDataBase : QuesionDataBase
    }

    override fun onCreate() {
        super.onCreate()
        questionsDataBase = Room.databaseBuilder(
            applicationContext,
            QuesionDataBase::class.java,
            QuesionDataBase.NAME
        ).build()



    }
}