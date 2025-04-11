package com.halawystory.askandanswer.mvvm.view.room_game_screen_LevelDone

import com.halawystory.askandanswer.mvvm.view.room_game_screen.DialogExitRoomGame

import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.halawystory.askandanswer.MusicPlayer
import com.halawystory.askandanswer.MusicPlayerRoom
import com.halawystory.askandanswer.R
import com.halawystory.askandanswer.mvvm.model.CovertDifficulty
import com.halawystory.askandanswer.mvvm.model.Question
import com.halawystory.askandanswer.mvvm.model.ResumeGameWithAds

import com.halawystory.askandanswer.mvvm.viewmodel.QuestionsViewModel
import com.halawystory.askandanswer.ui.theme.green
import com.halawystory.askandanswer.ui.theme.ibmBold
import com.halawystory.askandanswer.ui.theme.ibmLight
import com.halawystory.askandanswer.ui.theme.ibmReguler
import com.halawystory.askandanswer.ui.theme.orange
import com.halawystory.askandanswer.ui.theme.yallow



@Composable
fun RoomGameScreenLevelEnd(navController: NavController , context : Context , viewModel: QuestionsViewModel){


    val isWrong by viewModel.checkWrongLevelEnd.collectAsState()

    val levelName by viewModel.levelNameLevelEnd.collectAsState()
    val totalQuestion by viewModel.numberTotalQuestionLevelEnd.collectAsState()
    val numberAnswer by viewModel.numberAnswerQuestionLevelEnd.collectAsState()


    val coin by viewModel.coinLiveData.observeAsState()

    if (viewModel.musicLiveData.value==true)
        MusicPlayerRoom.start(context)




    var isExit = remember {
        mutableStateOf(true)
    }

    var isDialogExit = remember {
        mutableStateOf(false)
    }

    if (isWrong==true){
        if (viewModel.soundLiveData.value==true)
        MediaPlayer.create(context , R.raw.wrong).start()
    }

    Box(modifier = Modifier
        .fillMaxSize()


    ){
        Image(painter = painterResource(id = R.drawable.backgroundlevelgame),
            contentDescription ="background levels",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)
        Spacer(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E1E1E).copy(alpha = 0.8f)))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column( //column
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
            ) { //start column


                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                )
                { //First Row

                    IconButton(//Setting

                        onClick = {

                            isDialogExit.value = true

                        }) {    //start icon exit
                        Icon(
                            painter = painterResource(
                                id = R.drawable.exit
                            ),
                            contentDescription = "",
                            tint = orange,
                            modifier = Modifier.size(40.dp)
                        )
                    }//end exit



                    Row( //row coin
                        modifier = Modifier.padding(10.dp),

                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) { //start row coin

                        //coin
                        Image(painter = painterResource(id = R.drawable.coin),
                            contentDescription = "coin" ,
                            modifier = Modifier.size(30.dp),
                        )

                        Spacer(modifier = Modifier.width(10.dp))


                        Text(text = "${coin?:0}" ,
                            style = TextStyle(
                                color = yallow,
                                fontSize = 20.sp,
                                fontFamily = ibmBold,

                                ))

                    } //end row coin

                } //end First Row

                Spacer(modifier = Modifier.height(10.dp))


                Row( // second Row
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) { //start second Row

                    Column( // Column Levels and label Level
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top
                    )
                    { //start Column (Levels and label Level)



                        Text( //level name
                            text = "${CovertDifficulty.convertDifficulty(levelName!!)}",
                            style = TextStyle(
                                color = Color.White,
                                fontFamily = ibmLight,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,

                                )
                        )//emd level name

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(   //label level name
                            text = "مستوي",
                            style = TextStyle(
                                color = Color.White,
                                fontFamily = ibmLight,
                                fontSize = 15.sp
                            )
                        ) //end label level name

                    } //end //start Column (Levels and label Level)


                    Column( //Column Coin and Challenges
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Top
                    ) { //Start Column (Coin and Challenges)

                        Text( //challenges
                            text = "${numberAnswer}/${totalQuestion}",
                            style = TextStyle(
                                color = Color.White,
                                fontFamily = ibmLight,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,

                                )
                        )//end challenges

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(   //label challenges name
                            text = "تحدي",
                            style = TextStyle(
                                color = Color.White,
                                fontFamily = ibmLight,
                                fontSize = 15.sp
                            )
                        ) //end label challenges name

                    }//end column coin and challenges

                } //end second Row

            }//end column

            Spacer(modifier = Modifier.height(20.dp))

            generateQuestion(navController = navController ,context , viewModel)

        }


        //if i wanna Exit
        if (isDialogExit.value == true) {
            if (viewModel.soundLiveData.value==true)
                MediaPlayer.create(context , R.raw.pop).start()
            DialogExitRoomGame(
                onExitClicked = {
                    try {
                        viewModel.exitRoomLevelEnd()
                        // الرجوع إلى الشاشة السابقة
                        navController.popBackStack()
                    }catch (e:Exception){

                    }

                },
                onStayClicked = {
                    if (viewModel.soundLiveData.value==true)
                        MediaPlayer.create(context , R.raw.click).start()

                    // إغلاق مربع الحوار عند الضغط على "لا"
                    isDialogExit.value = false
                }
            )
        }


        //if User Wrong
        if (isWrong)
            DialogWrongAndswerLevelDone(

                onExitClick = { exit ->
                    if (exit==true){
                        viewModel.restartLevelDoneQuestionFronDialogWrongAnswer()
                        viewModel.setStateAdsWhenUserLoseGame(ResumeGameWithAds.Non)

                        MusicPlayerRoom.releaseMusic()

                        if (viewModel.musicLiveData.value==true)
                            MusicPlayer.highVolume()

                        Handler(Looper.getMainLooper()).postDelayed(
                            {
                                navController.popBackStack()

                            }
                            ,100)
                    }
                },
                viewModel
            )


    }



    // استماع للرجوع عبر زر الرجوع الفعلي
    BackHandler(enabled = isExit.value) {
        if (!isWrong)
            isDialogExit.value = true  // يظهر مربع الحوار عند الرجوع للخلف
    }


}


@Composable
fun generateQuestion(navController: NavController, context: Context, viewModel: QuestionsViewModel) {
    val randomQuestionViewModel by viewModel.randomQuestionLevelEnd.collectAsState()
    val stateQuestion by viewModel.animationStateLevelEnd.collectAsState()

    randomQuestionViewModel?.let { questionData ->
        AnimatedVisibility(
            visible = stateQuestion,
            enter = expandVertically(tween(1000)),
            exit = shrinkVertically(tween(1000)),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CustomShape {
                    Text(
                        text = questionData.question ?: "", // احتياط
                        style = TextStyle(
                            color = Color.White,
                            fontFamily = ibmReguler,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        lineHeight = 40.sp
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // تأكد من أن options ليست null ولا فاضية
                questionData.options?.takeIf { it.isNotEmpty() }?.let { optionsList ->
                    LazyColumn {
                        items(optionsList) { option ->
                            Option(
                                navController = navController,
                                context = context,
                                option = option,
                                question = questionData,
                                correctAnswer = questionData.correctAnswer ?: "", // احتياط
                                viewModel = viewModel
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun Option(navController: NavController,context: Context , option: String, question: Question ,correctAnswer : String ,viewModel: QuestionsViewModel) {


    val clickAllOptionLevelEnd by viewModel.clickAllOptionLevelEnd.collectAsState()
    val isAnswerIsWrong by viewModel.checkWrongLevelEnd.collectAsState()
    val optionClickedName by viewModel.optionClikedName.collectAsState()
    val checkAnswerLevelEnd by viewModel.checkAnswerLevelEnd.collectAsState()
    val restartAllColorLevelEnd by viewModel.restartAllColorLevelEnd.collectAsState()


    val clickOption = remember {
        mutableStateOf(true)
    }

    var colorOption = remember {
        mutableStateOf(ColorOption.DEFUILTCOLOR)
    }

    if (option==question.correctAnswer && checkAnswerLevelEnd){
        colorOption.value = ColorOption.CORRECTANSWERCOLOR
    }
    if (isAnswerIsWrong && clickOption.value==false){
        colorOption.value = ColorOption.WRONGCOLOR
    }

    if (restartAllColorLevelEnd==true){
        colorOption.value = ColorOption.DEFUILTCOLOR
    }

    val color by animateColorAsState(
        targetValue = when (colorOption.value) {
            ColorOption.DEFUILTCOLOR -> Color.DarkGray
            ColorOption.CLICKANDCHECKCOLOR -> orange
            ColorOption.CORRECTANSWERCOLOR -> green
            ColorOption.WRONGCOLOR -> Color.Red
            else -> Color.DarkGray
        },
        animationSpec = tween(durationMillis = 500)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = (clickOption.value && clickAllOptionLevelEnd) ) {
                colorOption.value = ColorOption.CLICKANDCHECKCOLOR
                clickOption.value = false
                viewModel.checkAnswerLevelEnd(context , option = option , question = question)

            },
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            text = option,
            style = TextStyle(color = Color.White, fontFamily = ibmReguler , fontWeight = FontWeight.Bold , fontSize = 22.sp)
            , textAlign = TextAlign.Center
        )
    }
}


enum class ColorOption{
    DEFUILTCOLOR,
    CLICKANDCHECKCOLOR,
    CORRECTANSWERCOLOR,
    WRONGCOLOR,

}

@Composable
fun CustomShape(content : @Composable ()->Unit){

    val gradientBrush = Brush.linearGradient(
        colors = listOf(orange, yallow)
    )

    AnimatedVisibility(
        visible = true,
        enter = expandHorizontally(tween(2000))
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .border(3.dp, gradientBrush, RoundedCornerShape(10.dp))
            .padding(horizontal = 10.dp, vertical = 20.dp))
        {
            content()
        }
    }
}

