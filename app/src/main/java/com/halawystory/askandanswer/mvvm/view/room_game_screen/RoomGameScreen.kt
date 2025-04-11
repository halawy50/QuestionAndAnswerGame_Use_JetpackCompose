package com.halawystory.askandanswer.mvvm.view.room_game_screen

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
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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
import androidx.compose.runtime.setValue
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
import com.halawystory.askandanswer.mvvm.view.component.AnimatedCircularProgressBar

import com.halawystory.askandanswer.mvvm.viewmodel.QuestionsViewModel
import com.halawystory.askandanswer.ui.theme.green
import com.halawystory.askandanswer.ui.theme.ibmBold
import com.halawystory.askandanswer.ui.theme.ibmLight
import com.halawystory.askandanswer.ui.theme.ibmReguler
import com.halawystory.askandanswer.ui.theme.orange
import com.halawystory.askandanswer.ui.theme.yallow



@Composable
fun RoomGameScreen(navController: NavController , context : Context , viewModel: QuestionsViewModel){

    val singleLevelLiveData by viewModel.singleLevelLiveData.observeAsState()
    val levelRoom by viewModel.difficultyClick.collectAsState()

    val isAddedPones by viewModel.isAddedPones.collectAsState()


    val processPones by viewModel.stateProsessPones.collectAsState()

    val valuePones by viewModel.valuePones.collectAsState()

    val isWrong by viewModel.isWrong.collectAsState()

    val coin by viewModel.coinLiveData.observeAsState()

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

                    AnimatedVisibility(
                        visible = isAddedPones,
                        enter = scaleIn(tween(500)),
                        exit = scaleOut(tween(500))
                    ) {
                        Text(
                            text = "${valuePones}+  ",
                            style = TextStyle(
                                color = yallow,
                                fontSize = 20.sp,
                                fontFamily = ibmBold,
                            )
                        )
                    }


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
                        text = "${CovertDifficulty.convertDifficulty(levelRoom)}",
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
                        text = "${singleLevelLiveData?.solveQuestions}/${singleLevelLiveData?.totalQuestions}",
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

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            , verticalAlignment = Alignment.Top ,
            horizontalArrangement = Arrangement.Center) {
            AnimatedCircularProgressBar(
                state = processPones,
                onValueChange = { newValue -> viewModel.setPones(newValue)}
            )
        }

        //if i wanna Exit
        if (isDialogExit.value == true) {
            if (viewModel.soundLiveData.value==true)
                 MediaPlayer.create(context , R.raw.pop).start()
            DialogExitRoomGame(
                onExitClicked = {
                    if (viewModel.soundLiveData.value==true)
                        MediaPlayer.create(context , R.raw.click).start()

                    viewModel.fetchAllLevels()  // جلب المستويات من ViewModel
                    MusicPlayerRoom.releaseMusic()  // إيقاف الموسيقى
                    MusicPlayer.highVolume()  // إعادة تشغيل الموسيقى بمستوى عالي (إذا كانت هذه هي الوظيفة المطلوبة)


                    // الرجوع إلى الشاشة السابقة
                    navController.popBackStack()

//                    // تنفيذ الإجراءات عند الضغط على "نعم"
//                    isExit.value = false


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
            DialogWrongAnswer(

                onExitClick = { exit ->
                    if (exit==true){
                        viewModel.fetchAllLevels()
                        viewModel.restartClickOption()
                        viewModel.restartWrongAnswer()
                        viewModel.setStateAdsWhenUserLoseGame(ResumeGameWithAds.Non)
                        viewModel.setSkipQuestion(true)

                        MusicPlayerRoom.releaseMusic()

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
fun generateQuestion(navController: NavController , context: Context , viewModel : QuestionsViewModel){
    val questionsUnique by viewModel.uniqueQuestionsLiveData.observeAsState()
    val randomQuestionViewModel by viewModel.randomQuestion.observeAsState()
    val stateQuestion = viewModel.stateQuestion.collectAsState().value



    var randomQuestion = randomQuestionViewModel
    if (randomQuestionViewModel!=null){

    }else {
        randomQuestion = questionsUnique?.random()
        if (randomQuestion != null) {
            viewModel.setRandomQuesion(randomQuestion)
        }

    }
        if (randomQuestion != null) {
            AnimatedVisibility(
                visible = stateQuestion,
                enter = expandVertically(tween(1000)),
                exit = shrinkVertically(tween(1000)),

            ) {
                Column(modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {

                    CustomShape{
                        Text(text = "${randomQuestion.question}" , style = TextStyle(
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

                    LazyColumn {

                        items(randomQuestion.options){ option ->

                            Option(navController = navController, context =  context , option, question = randomQuestion ,randomQuestion.correctAnswer , viewModel = viewModel)

                            Spacer(modifier = Modifier.height(10.dp))

                        }
                    }

                }
            }



        }

}

@Composable
fun Option(navController: NavController,context: Context , option: String, question: Question ,correctAnswer : String ,viewModel: QuestionsViewModel) {
    val clickOption by viewModel.clickOption.collectAsState()
    val wrongAnswer by viewModel.wrongAnswer.collectAsState()

    val skipQuestion by viewModel.skipQuestion.collectAsState()

    var colorOption by remember {
        mutableStateOf(ColorOption.DEFUILTCOLOR)
    }


    if (skipQuestion==true){
        colorOption = ColorOption.DEFUILTCOLOR
        viewModel.setSkipQuestion(false)

    }

    //بيضع لون اخضر علي الاختيار الصحيح اذا كان اختيار المستخدم خاطئ
    if (correctAnswer==option && wrongAnswer == true){
        colorOption = ColorOption.CORRECTANSWERCOLOR

        viewModel.setIsWrong(true)


    }

    val color by animateColorAsState(
        targetValue = when (colorOption) {
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
            .clickable(enabled = (clickOption == false)) {
                viewModel.setClickOption(
                    navController = navController,
                    context = context,
                    true,
                    question = question,
                    option
                ) { colorSelected ->
                    colorOption = colorSelected
//
                }
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

