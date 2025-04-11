package com.halawystory.askandanswer.mvvm.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.halawystory.askandanswer.AppQuestion
import com.halawystory.askandanswer.mvvm.model.*
import com.halawystory.askandanswer.mvvm.service.LevelsList
import com.halawystory.askandanswer.mvvm.service.Questions
import com.halawystory.askandanswer.mvvm.service.db.QuestionsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class QuestionsRepository {
    private val questionsDao: QuestionsDao = AppQuestion.questionsDataBase.getQuestionsDao()

    private val _allLevelsOpenLiveData = MutableLiveData<List<LevelsModels>>()
    val allLevelsOpenLiveData: LiveData<List<LevelsModels>> get() = _allLevelsOpenLiveData

    private val _singleLevelLiveData = MutableLiveData<LevelsOpenEntityDataBase>()
    val singleLevelLiveData: LiveData<LevelsOpenEntityDataBase> get() = _singleLevelLiveData

    private val _uniqueQuestionsLiveData = MutableLiveData<List<Question>>()
    val uniqueQuestionsLiveData: LiveData<List<Question>> get() = _uniqueQuestionsLiveData

    private val _myLevelLiveData = MutableLiveData<LevelsOpenEntityDataBase>()
    val myLevelLiveData: LiveData<LevelsOpenEntityDataBase> get() = _myLevelLiveData

    private val _coinLiveData = MutableLiveData<Int>()
    val coinLiveData: LiveData<Int> get() = _coinLiveData

    private val _soundLiveData = MutableLiveData<Boolean>()
    val soundLiveData: LiveData<Boolean> get() = _soundLiveData

    private val _musicLiveData = MutableLiveData<Boolean>()
    val musicLiveData: LiveData<Boolean> get() = _musicLiveData


    private val _levelGameNaw = MutableLiveData<LevelsModels>()


    fun setLevelGameNow(levelNaw : LevelsModels){
        _levelGameNaw.value = levelNaw
    }
    fun controlSound(context: Context){
        val sharedPreferences = context.getSharedPreferences("MyPrefs" , Context.MODE_PRIVATE)
        val isSound = !sharedPreferences.getBoolean("sound" , true)
        sharedPreferences.edit().putBoolean("sound" , isSound).apply()
        _soundLiveData.postValue(isSound)
    }

    fun getSound(context: Context){
        val sharedPreferences = context.getSharedPreferences("MyPrefs" , Context.MODE_PRIVATE)
        val isSound = sharedPreferences.getBoolean("sound" , true)
        _soundLiveData.postValue(isSound)
    }

    fun getMusic(context: Context){
        val sharedPreferences = context.getSharedPreferences("MyPrefs" , Context.MODE_PRIVATE)
        val isMusic = sharedPreferences.getBoolean("music" , true)
        _musicLiveData.postValue(isMusic)

    }

    fun controlMusic(context: Context ){
        val sharedPreferences = context.getSharedPreferences("MyPrefs" , Context.MODE_PRIVATE)
        val isMusic = sharedPreferences.getBoolean("music" , false)

        if (isMusic==true){
            sharedPreferences.edit().putBoolean("music" , false).apply()
            val isSound = sharedPreferences.getBoolean("music" , false)
            _musicLiveData.postValue(isSound)
            Log.d("HalawyFuckenTRUE" , "${isMusic}")
        }else{
            sharedPreferences.edit().putBoolean("music" , true).apply()
            val isSound = sharedPreferences.getBoolean("music" , true)
            _musicLiveData.postValue(isSound)
            Log.d("HalawyFuckenFALSE" , "${isMusic}")

        }
    }





    suspend fun addNewOpenLevel(level: LevelsOpenEntityDataBase): Result<Boolean> {
        return try {
            val result = questionsDao.addOpenLevel(level)
            Result.success(result > 0)
        } catch (e: Exception) {
            Log.e("QuestionsRepository", "Error adding new level", e)
            Result.failure(e)
        }
    }

    suspend fun getDataLeve(difficulty: Difficulty) : LiveData<LevelsOpenEntityDataBase>{
        val dataLevel = withContext(Dispatchers.IO) {
            questionsDao.getSingleLevel(difficulty)
        }
        _singleLevelLiveData.postValue(dataLevel)
        return _singleLevelLiveData
    }


    suspend fun fetchAllLevels() {
        try {
            val openLevels = questionsDao.retriveAllMyLevels() ?: emptyList()
            LevelsList.livelsList.forEach { level ->
                openLevels.find { it.difficult == level.difficult }?.let {
                    level.state = it.state
                    level.solveQuestions = it.solveQuestions
                    level.totalQuestions = it.totalQuestions

                }
            }
            _allLevelsOpenLiveData.postValue(LevelsList.livelsList)
        } catch (e: Exception) {
            Log.e("QuestionsRepository", "Error fetching levels", e)
        }
    }


    suspend fun updateLevelToDone(difficulty: Difficulty):LevelsOpenEntityDataBase?{
        val isUpdate = questionsDao.updateLevel(difficulty = difficulty , stateLevel = StateLevel.OpenDone)
        Log.d("StateLevel" , "updateLevelToDone  ---- ${isUpdate}")

        if(isUpdate>0){
            Log.d("StateLevel" , "isUpdate  ---- ${isUpdate}")

            when(difficulty) {
                Difficulty.EASY -> {
                    _uniqueQuestionsLiveData.postValue(emptyList()) // ✅ حل المشكلة

                    _singleLevelLiveData
                    val newLevelMEDIUM = LevelsOpenEntityDataBase(
                        state = StateLevel.OpenProcess,
                        difficult = Difficulty.MEDIUM,
                        Level = 20,
                        solveQuestions = 0,
                        totalQuestions = 40,
                    )
                    Log.d("StateLevel" , "newLevelMEDIUM")

                    val isAddNewLevel = questionsDao.addOpenLevel(newLevelMEDIUM)
                    if (isAddNewLevel > 0) {
                        return newLevelMEDIUM
                    }
                }

                Difficulty.MEDIUM -> {
                    _uniqueQuestionsLiveData.postValue(emptyList()) // ✅ حل المشكلة

                    val newLevelHARD = LevelsOpenEntityDataBase(
                        state = StateLevel.OpenProcess,
                        difficult = Difficulty.HARD,
                        Level = 30,
                        solveQuestions = 0,
                        totalQuestions = 40,
                    )
                    val isAddNewLevel = questionsDao.addOpenLevel(newLevelHARD)
                    if (isAddNewLevel > 0) {
                        return newLevelHARD
                    }
                }

                Difficulty.HARD -> {
                    _uniqueQuestionsLiveData.postValue(emptyList()) // ✅ حل المشكلة

                    val newLevelHARD = LevelsOpenEntityDataBase(
                        state = StateLevel.OpenProcess,
                        difficult = Difficulty.EXPERT,
                        Level = 20,
                        solveQuestions = 0,
                        totalQuestions = 40,
                    )
                    val isAddNewLevel = questionsDao.addOpenLevel(newLevelHARD)

                    if (isAddNewLevel > 0) {
                        return newLevelHARD
                    }
                }
                else -> {

                    return null
                }
            }
            return null
        }
        else return null
    }






    suspend fun updateOpenLevel(difficulty: Difficulty , stateLevel : StateLevel): Result<Boolean> {
        return try {
            if (stateLevel == StateLevel.OpenDone){
                Result.success(true)
            }
            else if (stateLevel == StateLevel.OpenProcess){
                Log.d("HukerZZZZZZZ" , "1")
                val updatedRows = questionsDao.incrementSolveQuestions(difficulty)
                Log.d("HukerZZZZZZZ" , "2")

                delay(500)
                if (updatedRows > 0) {
                    Log.d("HukerZZZZZZZ" , "3")

                    val letLevel = questionsDao.getSingleLevel(difficulty)
                    Log.d("HukerZZZZZZZ" , "4")
                    delay(500)

                    _singleLevelLiveData.postValue(letLevel)
                    Log.d("HukerZZZZZZZ" , "5")

                    delay(500)

                    if (_singleLevelLiveData.value!=null){
                        Log.d("HukerZZZZZZZ" , "6")

                        Log.d("HukerZZZZZZZ" , "${_singleLevelLiveData.value}")
                        Result.success(true)
                    }

                }

            }
            Log.d("HukerZZZZZZZ" , "7")

            Result.success(false)

        } catch (e: Exception) {
            Log.d("HukerZZZZZZZ" , "8")

            Log.e("QuestionsRepository", "Error updating level", e)
            Result.failure(e)
        }
    }

    suspend fun addSolvedQuestion(solvedQuestion: SolveQuestionsEntityDataBase): Result<Boolean> {
        return try {
            Log.d("HalawyAhmed", "1")

            // Safely check for null values
            val myLevelValue = _levelGameNaw.value
            if (myLevelValue == null) {
                Log.d("HalawyAhmed", "myLevelValue is null")
                return Result.success(false)
            }

            if (myLevelValue.state == StateLevel.OpenDone) {
                Log.d("HalawyAhmed", "2")


                _uniqueQuestionsLiveData.postValue(
                    _uniqueQuestionsLiveData.value?.filterNot { it.id == solvedQuestion.idQuestion } ?: emptyList()
                )
                Log.d("HalawyAhmed", "3")

                return Result.success(true)
            }

            if (myLevelValue.state == StateLevel.OpenProcess) {
                Log.d("HalawyAhmed", "4")

                val result = questionsDao.addSolveQuestion(solvedQuestion)
                Log.d("HalawyAhmed", "5")

                getDataLeve(myLevelValue.difficult)
                // Safely check singleLevelLiveData
                val singleLevelValue = _singleLevelLiveData.value
                if (singleLevelValue == null) {
                    Log.d("HalawyAhmed", "singleLevelValue is null")
                    return Result.success(false)
                }

                if (result != -1L && myLevelValue.solveQuestions <= 40) {

                    val checkUpdate = updateOpenLevel(solvedQuestion.difficulty , myLevelValue.state)

                    if (checkUpdate.isSuccess) {
                        Log.d("HalawyAhmed", "7")


                        Log.d("HalawyAhmed SolveQuestion ", "${_singleLevelLiveData.value}")

                        _uniqueQuestionsLiveData.postValue(
                            _uniqueQuestionsLiveData.value?.filterNot { it.id == solvedQuestion.idQuestion } ?: emptyList()
                        )
                        Log.d("HalawyAhmed", "8")

                        return Result.success(true)
                    }else {
                        val checkUpdate = updateOpenLevel(solvedQuestion.difficulty , myLevelValue.state)
                        if (checkUpdate.isSuccess) {
                            Log.d("HalawyAhmed", "7")

                            _uniqueQuestionsLiveData.postValue(
                                _uniqueQuestionsLiveData.value?.filterNot { it.id == solvedQuestion.idQuestion } ?: emptyList()
                            )
                            Log.d("HalawyAhmed", "8")

                            return Result.success(true)
                        }
                    }
                }
            }

            Result.success(false)
        } catch (e: Exception) {
            Log.d("HalawyAhmed", "9")
            Log.e("QuestionsRepository", "Error adding solved question", e)
            Result.failure(e)
        }
    }

    fun getQuestionIfLevelEnd(difficulty: Difficulty) : List<Question>{
        val listQuestion = Questions.questionsList.filter { it.difficulty==difficulty }
        return listQuestion
    }



    suspend fun isQuestionsSolvingFoundedInDataBase(difficulty: Difficulty) : Boolean {
        return  try {
            val solvedQuestions = questionsDao.getAllMySolveQuestion(difficulty)
            if (solvedQuestions.isNullOrEmpty()){
                val allQuestionByDifficulty = Questions.questionsList.filter { question ->
                    question.difficulty == difficulty
                }
                _uniqueQuestionsLiveData.postValue(allQuestionByDifficulty)
                return  false
            }
            else{
                return  true
            }

        } catch (e: Exception) {
            return  true
        }
    }




    suspend fun fetchUnsolvedQuestions(difficulty: Difficulty) : Boolean {
        return  try {
            _uniqueQuestionsLiveData.postValue(emptyList())

            val solvedQuestions = questionsDao.getAllMySolveQuestion(difficulty)

            val solvedSet = solvedQuestions.map { it.idQuestion }.toSet()

            val unsolvedQuestions = Questions.questionsList.filter { question ->
                question.difficulty == difficulty && question.id !in solvedSet
            }
            _uniqueQuestionsLiveData.postValue(unsolvedQuestions)
            Log.e("QuestionsRepository", "${difficulty}")

            return true
        } catch (e: Exception) {
            Log.e("QuestionsRepository", "Error fetching unsolved questions", e)
            return  false
        }
    }

    suspend fun deleteAllMyQuestionByDifficulty(difficulty: Difficulty):Boolean{
        return try {
            val isDeleted = questionsDao.clearQuestionsAndResetSolveCount(difficulty)
            if (isDeleted==true){
                 true
            }else{
                 false
            }
        }catch (e:Exception){
             false
        }
    }

    suspend fun getMyLevel() {
        try {
            _myLevelLiveData.postValue(questionsDao.getMyLevel())
            Log.d("Fuken",  "${questionsDao.getMyLevel()}")

        } catch (e: Exception) {
            Log.e("QuestionsRepository", "Error fetching my level", e)
        }
    }

    suspend fun getCoin(context: Context){
        val sharedPreferences = context.getSharedPreferences("MyPrefs" , Context.MODE_PRIVATE)
        val coin = sharedPreferences.getInt("coin" , 0)
        _coinLiveData.postValue(coin)
    }

    suspend fun increamentCoin(context: Context , ponesCoin : Int){
        val sharedPreferences = context.getSharedPreferences("MyPrefs" , Context.MODE_PRIVATE)
        var coin = sharedPreferences.getInt("coin" , 0)
        coin += ponesCoin
        sharedPreferences.edit().putInt("coin" , coin).apply()

        val updateCoin = sharedPreferences.getInt("coin" , 0)

        _coinLiveData.postValue(updateCoin)
    }

    suspend fun firstOpenApp(context: Context){
        Log.d("firstOpenApp",  "1")

        val sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        Log.d("firstOpenApp",  "2")

        val isFirstOpenApp = sharedPref.getBoolean("isFirstOpenApp", true)
        Log.d("firstOpenApp",  "3")

        if (isFirstOpenApp) {
            Log.d("firstOpenApp",  "4")

            val checkAdd = addNewOpenLevel(
                LevelsOpenEntityDataBase(
                    state = StateLevel.OpenProcess,
                    totalQuestions = 40,
                    difficult = Difficulty.EASY,
                    solveQuestions = 0,
                    Level = 10,
                )
            )
            Log.d("firstOpenApp",  "5")

            if (checkAdd.isFailure){
                Log.d("firstOpenApp",  "6")

                 addNewOpenLevel(
                    LevelsOpenEntityDataBase(
                        state = StateLevel.OpenProcess,
                        totalQuestions = 40,
                        difficult = Difficulty.EASY,
                        solveQuestions = 0,
                        Level = 10,
                    )
                )
                Log.d("firstOpenApp",  "7")

                Log.d("NotAdded","${checkAdd}")

            }
            Log.d("firstOpenApp",  "8")

            sharedPref.edit().putInt("coin", 50).apply()
            Log.d("firstOpenApp",  "9")

            sharedPref.edit().putBoolean("isFirstOpenApp", false).apply()

            Log.d("firstOpenApp",  "10")

            getCoin(context)
            Log.d("firstOpenApp",  "11")

            Log.d("GIFI","${checkAdd}")
            Log.d("firstOpenApp",  "12")

        }else if(myLevelLiveData==null){
            Log.d("firstOpenApp",  "13")

            val checkAdd = addNewOpenLevel(
                LevelsOpenEntityDataBase(
                    state = StateLevel.OpenProcess,
                    totalQuestions = 40,
                    difficult = Difficulty.EASY,
                    solveQuestions = 0,
                    Level = 10,
                )
            )
            Log.d("firstOpenApp",  "14")

            Log.d("GIFI 2","${checkAdd}")

            sharedPref.edit().putInt("coin", 50).apply()
            sharedPref.edit().putBoolean("isFirstOpenApp", false).apply()
            Log.d("firstOpenApp",  "15")

        }

    }

    fun decrementCoin(context: Context , minsCoin : Int) : StateDecrementCoin {
        val sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        var allCoin = sharedPref.getInt("coin", 0)
        if (allCoin >= minsCoin ){
            allCoin -= minsCoin
            sharedPref.edit().putInt("coin", allCoin).apply()
            return StateDecrementCoin.SUCCESS
        }else if (allCoin < minsCoin){
            return StateDecrementCoin.DONTHAVEENOUGHMONEY
        }else{
            return StateDecrementCoin.FAILED
        }

    }
}

enum class StateDecrementCoin{
    SUCCESS,
    DONTHAVEENOUGHMONEY,
    FAILED
}