package com.halawystory.askandanswer.mvvm.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.*
import androidx.navigation.NavController
import com.halawystory.askandanswer.Ads.RewardeAds
import com.halawystory.askandanswer.MusicPlayer
import com.halawystory.askandanswer.MusicPlayerRoom
import com.halawystory.askandanswer.R
import com.halawystory.askandanswer.mvvm.model.*
import com.halawystory.askandanswer.mvvm.repository.QuestionsRepository
import com.halawystory.askandanswer.mvvm.repository.StateDecrementCoin
import com.halawystory.askandanswer.mvvm.service.Questions
import com.halawystory.askandanswer.mvvm.view.component.ProcessButton
import com.halawystory.askandanswer.mvvm.view.component.State
import com.halawystory.askandanswer.mvvm.view.room_game_screen.ColorOption
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


enum class StateQuestion{
    PROCESS,
    CORRECT
}

class QuestionsViewModel(
    private val questionsRepository: QuestionsRepository
) : ViewModel() {

    init {
        getMyLevel()
    }





    private val _processButton = MutableStateFlow(ProcessButton.Noon)
    val processButton : StateFlow<ProcessButton> = _processButton

    fun setProcessButton(processButton : ProcessButton){
        _processButton.value = processButton
    }


    private val _isFinishLevel = MutableStateFlow(false)
    val isFinishLevel : StateFlow<Boolean> = _isFinishLevel


    fun setStateFinishLevel(state : Boolean){
        _isFinishLevel.value = state
    }

    private val _skipQuestion = MutableStateFlow(false)
    val skipQuestion : StateFlow<Boolean> = _skipQuestion

    fun setSkipQuestion(isSkipQuestion : Boolean){
        _skipQuestion.value = isSkipQuestion
    }

    private val _isWrong = MutableStateFlow(false)
    val isWrong : StateFlow<Boolean> = _isWrong

    fun setIsWrong(isWrong : Boolean) {
        _isWrong.value = isWrong
    }

    private val _userLoseGameButCanResumeUseAds = MutableStateFlow(ResumeGameWithAds.Non)
    val userLoseGameButCanResumeUseAds : StateFlow<ResumeGameWithAds> = _userLoseGameButCanResumeUseAds

    fun setStateAdsWhenUserLoseGame(stateAds : ResumeGameWithAds){
        _userLoseGameButCanResumeUseAds.value = stateAds
    }

    fun getStateAdsWhenUserLoseGame(): ResumeGameWithAds{
        return userLoseGameButCanResumeUseAds.value
    }

    private val _initAdmobAds = MutableStateFlow(false)
    val initAdmobAds : StateFlow<Boolean> = _initAdmobAds

    fun setAdmobAds(admobMobile: Boolean){
        _initAdmobAds.value = admobMobile
    }


    //Ads Rewards Class
    private val _rewardMutableStateFlow = MutableStateFlow<RewardeAds?>(null)
    val reward: StateFlow<RewardeAds?> = _rewardMutableStateFlow

    fun setRewardAds(rewardAds: RewardeAds) {
        _rewardMutableStateFlow.value = rewardAds
    }


    //Ads Rewards Class
    private val _isRewardedLoaded = MutableStateFlow(false)
    val isRewardedLoaded: StateFlow<Boolean?> = _isRewardedLoaded

    fun setRewardAdsInit(isRewardedLoaded: Boolean) {
        _isRewardedLoaded.value = isRewardedLoaded
    }


    //Ads Rewards Class
    private val _stateAds = MutableStateFlow<StateAds?>(StateAds.NONClickADS)
    val stateAds: StateFlow<StateAds?> = _stateAds

    fun setStateAds(stateAds: StateAds) {
        _stateAds.value = stateAds
        Log.d("wwwwwwwwwwwwww" , "${_stateAds.value}")
    }



    val myLevelLiveData: LiveData<LevelsOpenEntityDataBase> = questionsRepository.myLevelLiveData

    val allLevelsOpenLiveData: LiveData<List<LevelsModels>> =
        questionsRepository.allLevelsOpenLiveData

    val coinLiveData: LiveData<Int> = questionsRepository.coinLiveData
    val musicLiveData: LiveData<Boolean> = questionsRepository.musicLiveData
    val soundLiveData: LiveData<Boolean> = questionsRepository.soundLiveData
    val uniqueQuestionsLiveData: LiveData<List<Question>> = questionsRepository.uniqueQuestionsLiveData
    val singleLevelLiveData: LiveData<LevelsOpenEntityDataBase> = questionsRepository.singleLevelLiveData

    private val _stateQuestion = MutableStateFlow(false)
    val stateQuestion : StateFlow<Boolean> = _stateQuestion


    private val _randomQuestion = MutableLiveData<Question?>()
    val randomQuestion = _randomQuestion

    private val _clickOption = MutableStateFlow(false)
     val clickOption = _clickOption

    fun restartClickOption(){
        _clickOption.value = false
    }

    private var _clickLevel = MutableStateFlow(false)
    var clickLevel : StateFlow<Boolean> = _clickLevel


    private val _uniqueQuestion = MutableStateFlow(-2)
    val uniqueQuestion = _uniqueQuestion


    private val _difficultyClick = MutableStateFlow(Difficulty.EASY)
    val difficultyClick: StateFlow<Difficulty> = _difficultyClick


//    private var _animationColor  =  MutableStateFlow(ColorOption.DEFUILTCOLOR)
//    val animationColor : StateFlow<ColorOption> =  _animationColor

    private val _wrongAnswer = MutableStateFlow(false)
     val wrongAnswer : StateFlow<Boolean> = _wrongAnswer

    fun restartWrongAnswer(){
        _wrongAnswer.value = false
    }

    private val _valuePones = MutableStateFlow(15)
    val valuePones : StateFlow<Int> = _valuePones


    private val _stateProsessPones = MutableStateFlow(State.Process)
    val stateProsessPones : StateFlow<State> = _stateProsessPones

    fun restartProcessPones(){
        _stateProsessPones.value = State.Restart
    }
    private val _isAddedPones = MutableStateFlow(false)
    val isAddedPones : StateFlow<Boolean> = _isAddedPones

    private val _isFinishQuestionLevel = MutableStateFlow(false)
    val isFinishQuestionLevel : StateFlow<Boolean> = _isFinishQuestionLevel


    private val _isDialogPriceLevel = MutableStateFlow(false)
    val isDialogPriceLevel : StateFlow<Boolean> = _isDialogPriceLevel


    fun setPones(pones : Int){
        _valuePones.value = pones
    }





    fun setRandomQuesion(randomQuestion : Question){
        _randomQuestion.value = randomQuestion
    }


    suspend fun getDataLevel(difficulty: Difficulty) : LiveData<LevelsOpenEntityDataBase> {
        return questionsRepository.getDataLeve(difficulty)
    }
    val _levelsOpenEntityDataBase = MutableStateFlow<LevelsOpenEntityDataBase?>(null)
    val levelsOpenEntityDataBase : StateFlow<LevelsOpenEntityDataBase?> = _levelsOpenEntityDataBase

    @SuppressLint("SuspiciousIndentation")
    fun setClickOption(navController: NavController
                       , context: Context, clickOption : Boolean,
                       question: Question, option : String
                       , onColor : (ColorOption) -> Unit){


        CoroutineScope(Dispatchers.Main).launch {



            _stateProsessPones.value = State.Stop

            _clickOption.value = clickOption

            onColor(ColorOption.CLICKANDCHECKCOLOR)
            delay(1000)

            var checkCorrect = _randomQuestion.value?.correctAnswer == option

            if (checkCorrect==true){

                _isAddedPones.value =true

                onColor(ColorOption.CORRECTANSWERCOLOR)

                if (soundLiveData.value == true){
                    MediaPlayer.create(context, R.raw.correct3).apply {
                        setVolume(1f, 1f)  // تعيين الحد الأقصى للصوت
                        start()
                    }

                }

                delay(700)

                _stateQuestion.value=  false

                delay(500)


                addSolvedQuestion(question)

                delay(500)

                val isFinishLevel = isLevelQuestionFinish(difficulty = question.difficulty)
                Log.d("StateLevel" , "isFinishLevel  ---- ${isFinishLevel}")


                if (isFinishLevel){
                    val isUpdateLevel = withContext(Dispatchers.IO){ questionsRepository.updateLevelToDone(difficulty = question.difficulty) }

                    //////////////
                    //////////////
                    //////////
                    /////////

                    if (isUpdateLevel!= null){

                        _levelsOpenEntityDataBase.value = isUpdateLevel

                            Log.d("StateLevel" , "Done")
                        fetchAllLevels()
                        restartClickOption()
                        restartWrongAnswer()
                        setStateAdsWhenUserLoseGame(ResumeGameWithAds.Non)
                        setSkipQuestion(true)
                        _isAddedPones.value = false


                        _isFinishLevel.value = true
                        _uniqueQuestion.value = 0

                        delay(200)


                        delay(500)

                        navController.popBackStack()

                        _randomQuestion.value = null
                        return@launch
                    }else{
                        navController.popBackStack()
                        return@launch
                    }

                }


                if (singleLevelLiveData.value!!.state.equals(StateLevel.OpenProcess)){
                    increamentCoin(context , _valuePones.value)
                }
                else if (singleLevelLiveData.value!!.state.equals(StateLevel.OpenDone)){
                    increamentCoin(context , 1)

                }
                _isAddedPones.value =false



                delay(150)

                uniqueQuestionsLiveData.value?.let { setRandomQuesion(it.random()) }

                _clickOption.value = false
                onColor(ColorOption.DEFUILTCOLOR)

                delay(700)

                _stateQuestion.value=  true

                _stateProsessPones.value = State.Restart

            }

            else if(checkCorrect==false){
                onColor(ColorOption.WRONGCOLOR)
                _stateProsessPones.value = State.Stop
                _wrongAnswer.value = true
            }
        }
    }

    ////////////Level Done =====================


    private val _clickAllOptionLevelEnd = MutableStateFlow(true)
    val clickAllOptionLevelEnd : StateFlow<Boolean> = _clickAllOptionLevelEnd

    private val _restartAllColorLevelEnd = MutableStateFlow(false)
    val restartAllColorLevelEnd : StateFlow<Boolean> = _restartAllColorLevelEnd

    private val _animationStateLevelEnd = MutableStateFlow(true)
    val animationStateLevelEnd : StateFlow<Boolean> = _animationStateLevelEnd

    private val _numberTotalQuestionLevelEnd = MutableStateFlow(0)
    val numberTotalQuestionLevelEnd : StateFlow<Int> = _numberTotalQuestionLevelEnd

    private val _numberAnswerQuestionLevelEnd = MutableStateFlow(0)
    val numberAnswerQuestionLevelEnd : StateFlow<Int> = _numberAnswerQuestionLevelEnd

    private var _allQuestionsLevelEnd : List<Question?> = listOf()

    private val _randomQuestionLevelEnd = MutableStateFlow<Question?>(null)
    val randomQuestionLevelEnd : StateFlow<Question?> = _randomQuestionLevelEnd

    private val _levelNameLevelEnd = MutableStateFlow<Difficulty?>(null)
    val levelNameLevelEnd : StateFlow<Difficulty?> = _levelNameLevelEnd

    private val _checkWrongLevelEnd = MutableStateFlow<Boolean>(false)
    val checkWrongLevelEnd : StateFlow<Boolean> = _checkWrongLevelEnd

    private val _optionClikedName = MutableStateFlow("")
    val optionClikedName : StateFlow<String> = _optionClikedName

    private val _checkAnswerLevelEnd = MutableStateFlow(false) // not end check
    val checkAnswerLevelEnd : StateFlow<Boolean> = _checkAnswerLevelEnd


    private val _isCorrectLevelEnd = MutableStateFlow(false) // not end check
    val isCorrectLevelEnd : StateFlow<Boolean> = _isCorrectLevelEnd


    fun traggerClickAllOption(state : Boolean){
        _clickAllOptionLevelEnd.value = state
    }

    fun restartAllColorLevelEnd(state : Boolean){
        _restartAllColorLevelEnd.value = state
    }

    fun traggerAnimationStateLevelEnd(state : Boolean){
        _animationStateLevelEnd.value = state
    }

    fun restartLevelDoneQuestionFronDialogWrongAnswer(){
            viewModelScope.launch {
                _clickAllOptionLevelEnd.value = true
                _isCorrectLevelEnd.value = false
                _checkWrongLevelEnd.value = false
                _checkAnswerLevelEnd.value = false
                _restartAllColorLevelEnd.value = true
                delay(500)
                _restartAllColorLevelEnd.value = false

                return@launch
        }

    }

    fun exitRoomLevelEnd(){
        viewModelScope.launch {
            MusicPlayerRoom.releaseMusic()
            _clickAllOptionLevelEnd.value = true
            _isCorrectLevelEnd.value = false
            _checkWrongLevelEnd.value = false
            _checkAnswerLevelEnd.value = false
            _restartAllColorLevelEnd.value = true
            delay(500)
            if (musicLiveData.value==true)
                MusicPlayer.highVolume()
            _restartAllColorLevelEnd.value = false
            _animationStateLevelEnd.value = true
            _randomQuestionLevelEnd.value = null
            return@launch
        }

    }



    fun getAllQuestionLevelEnd(difficulty: Difficulty):Boolean{
        var allQuestions = Questions.questionsList.filter { it.difficulty == difficulty }
        _allQuestionsLevelEnd = allQuestions
        _levelNameLevelEnd.value = difficulty
        if (!_allQuestionsLevelEnd.isNullOrEmpty()){
            _numberTotalQuestionLevelEnd.value = allQuestions.size
            _numberAnswerQuestionLevelEnd.value = 1

            _randomQuestionLevelEnd.value = _allQuestionsLevelEnd.random()
            return true
        }
        else return false
    }

    fun generateRandomQuestion(question: Question): Boolean {
        _allQuestionsLevelEnd.filter { it != question }
       var newRandom =  _allQuestionsLevelEnd.random()
        if (newRandom!=question){
            _numberAnswerQuestionLevelEnd.value += 1

            _randomQuestionLevelEnd.value = newRandom
            return true
        }else return false

    }


    fun checkAnswerLevelEnd(context: Context , option: String , question: Question ){

        viewModelScope.launch {
            _clickAllOptionLevelEnd.value = false
            delay(700)
            if (option == question.correctAnswer){
                _isCorrectLevelEnd.value = true
                delay(700)
                _checkAnswerLevelEnd.value = true
            }else{
                _checkWrongLevelEnd.value = true
                _checkAnswerLevelEnd.value = true
                MediaPlayer.create(context , R.raw.wrong).start()


                return@launch
            }
            if (soundLiveData.value==true)
                MediaPlayer.create(context , R.raw.correct3).start()

            delay(700)
            if (_isCorrectLevelEnd.value==true){

                _animationStateLevelEnd.value = false
                delay(500)

                _restartAllColorLevelEnd.value = true

                delay(700)
                if(generateRandomQuestion(question)){
                    _restartAllColorLevelEnd.value = false
                    _clickAllOptionLevelEnd.value = true
                    _checkAnswerLevelEnd.value = false

                    delay(700)
                    _animationStateLevelEnd.value = true
                }
            }



        }



    }

    fun fetchUnsolvedQuestions(difficulty: Difficulty) {
        CoroutineScope(Dispatchers.IO).launch {
            var isLevelDataIsFetching = allLevelsOpenLiveData.value?.filter { it.difficult == difficulty }?.firstOrNull()

            if (isLevelDataIsFetching != null) {
                levelGameNaw(isLevelDataIsFetching)
                // Proceed with your logic if the data is found
                if (isLevelDataIsFetching.solveQuestions==0){
                    visbleLoadingAndEnterRoomGame(difficulty)
                }
                else
                    _isDialogPriceLevel.value = true

                Log.e("HanyRamzy", "$isLevelDataIsFetching")

            } else {
                // Handle the case where no data is found
                Log.e("Error", "No level data found for difficulty: $difficulty")
            }
        }
    }

    fun levelGameNaw(levelGame : LevelsModels){
        viewModelScope.launch {
            questionsRepository.setLevelGameNow(levelGame)
        }
    }

    fun setStateDiaolog(state: Boolean){
        _isDialogPriceLevel.value = state
    }
    fun deleteAllMyQuestionByDifficultyAndEnterGameRoom(difficulty: Difficulty , state : (Boolean) -> Unit)  {
        viewModelScope.launch(Dispatchers.IO) {
            val isDeleted = questionsRepository.deleteAllMyQuestionByDifficulty(difficulty)
            delay(100)  // Ensure the operation has completed, but this isn't ideal, consider using LiveData or StateFlow observation.
            if (isDeleted) {
                Log.d("QuestionsViewModel", "updateOpenLevel result: $difficulty")

                questionsRepository.fetchUnsolvedQuestions(difficulty)

                delay(200)  // Ensure the operation has completed, but this isn't ideal, consider using LiveData or StateFlow observation.

                val size = uniqueQuestionsLiveData.value?.size ?: 0  // Default to 0 if value is null
                if (size==40){
                    state(true)
                    return@launch

                }
            }
        }
    }


    ///////////////////////////////////////////////////////////////////



    fun setDialogPriceLevelDicrementCoin(){
        _isDialogPriceLevel.value = false
    }

    fun unVisableProcess(){
        _isDialogPriceLevel.value = false
    }

    fun decrementCoin(context: Context , minsCoin : Int) : StateDecrementCoin{
           return questionsRepository.decrementCoin(context , minsCoin)
    }


    fun visbleLoadingAndEnterRoomGame(difficulty: Difficulty){

        viewModelScope.launch(Dispatchers.IO){
            _uniqueQuestion.value = -1
            _clickLevel.value = true
            delay(3000)
            getDataLevel(difficulty)
            _difficultyClick.value = difficulty
            questionsRepository.fetchUnsolvedQuestions(difficulty)
            if (musicLiveData.value==true)
                 MusicPlayer.lowVolume()
            delay(1000)
            _uniqueQuestion.value = 1
            _stateQuestion.value=  true
            delay(100)
            _uniqueQuestion.value = -2
            unvisableLoading()

        }


    }
    fun unvisableLoading(){
        _clickLevel.value = false

    }

    fun controlSound(context: Context){
        viewModelScope.launch {
            questionsRepository.controlSound(context )
        }
    }
    fun controlMusic(context: Context){
        viewModelScope.launch {
            questionsRepository.controlMusic(context)
        }
    }

    fun getSound(context: Context){
        viewModelScope.launch {
            questionsRepository.getSound(context)
        }
    }

    fun getMusic(context: Context){
        viewModelScope.launch {
            questionsRepository.getMusic(context)
        }
    }



    fun addNewOpenLevel(level: LevelsOpenEntityDataBase) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = questionsRepository.addNewOpenLevel(level)
            Log.d("QuestionsViewModel", "addNewOpenLevel result: $result")
        }
    }


    fun fetchAllLevels() {
        viewModelScope.launch(Dispatchers.IO) {
            questionsRepository.fetchAllLevels()
        }
    }

    fun addSolvedQuestion(question: Question) {

        var solveQuestion = SolveQuestionsEntityDataBase(
            idQuestion = question.id,
            difficulty = question.difficulty,
            question = question.question
        )
        viewModelScope.launch(Dispatchers.IO) {
            val result = questionsRepository.addSolvedQuestion(solveQuestion)
            Log.d("QuestionsViewModel", "addSolvedQuestion result: $result")
        }
    }


    suspend fun isLevelQuestionFinish(difficulty: Difficulty):Boolean{

        val levelData = getDataLevel(difficulty = difficulty).value
        Log.d("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG" , "${levelData}")
        val solveQuestion : Int = levelData?.solveQuestions ?:0
        val solveQuestionPluseOne  = solveQuestion+1
        if (solveQuestionPluseOne == levelData?.totalQuestions
            && levelData?.state == StateLevel.OpenProcess
            ){
            Log.d("StateLevel" , "isLevelQuestionFinish  ---- ${true}")

            return true
        }
        Log.d("StateLevel" , "isLevelQuestionFinish  ---- ${false}")

        return false
    }

    fun getMyLevel() {

        viewModelScope.launch(Dispatchers.IO) {
            questionsRepository.getMyLevel()
        }
    }

    fun firstOpenApp(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            questionsRepository.firstOpenApp(context)
        }
    }

    fun getCoin(context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            questionsRepository.getCoin(context)
        }
    }
    fun increamentCoin(context: Context  ,pones : Int){
        viewModelScope.launch(Dispatchers.IO) {
            questionsRepository.increamentCoin(context , pones)
        }

    }
}
