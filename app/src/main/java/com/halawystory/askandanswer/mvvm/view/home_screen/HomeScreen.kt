package com.halawystory.askandanswer.mvvm.view.home_screen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.halawystory.askandanswer.MusicPlayer
import com.halawystory.askandanswer.R
import com.halawystory.askandanswer.Routes
import com.halawystory.askandanswer.mvvm.model.CovertDifficulty
import com.halawystory.askandanswer.mvvm.viewmodel.QuestionsViewModel
import com.halawystory.askandanswer.ui.theme.ibmBold
import kotlinx.coroutines.delay

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(context: Context ,
               navController : NavController ,
               viewModelQuestion : QuestionsViewModel) {

    var isVisible by rememberSaveable { mutableStateOf(false) }

    val stateSound by viewModelQuestion.soundLiveData.observeAsState(initial = false)
    val myLevel by viewModelQuestion.myLevelLiveData.observeAsState()
    val coin by viewModelQuestion.coinLiveData.observeAsState()

    Log.d("Hukerrrrr", "Fucker First1 ${myLevel}")


    // تشغيل الأنيميشن بعد 3 ثوانٍ
    LaunchedEffect(Unit) {
        delay(100) // تأخير لمدة 3 ثوانٍ
        isVisible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {


        // طبقة شفافة فوق الخلفية
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
        )

        Column(
            modifier = Modifier.fillMaxSize(),

        ) {

                AnimatedVisibility(
                    visible = isVisible,
                    enter = expandHorizontally(tween(1000)),
                    exit = scaleOut(tween(50000)),
                    ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 15.dp, vertical = 10.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // الصف العلوي للعملات
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.coin),
                                    contentDescription = "coin",
                                    modifier = Modifier
                                        .height(30.dp)
                                        .width(30.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "${coin?:0}",
                                    style = TextStyle(
                                        color = Color(0xFFF2FF00),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                )
                            }
                            // الصف العلوي للعملات
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Text(
                                    text = myLevel?.let { "مستوي : ${CovertDifficulty.convertDifficulty(it.difficult)}" } ?: "مستوي غير معروف",
                                    style = TextStyle(
                                        color = Color(0xFFF2FF00),
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = ibmBold,
                                        fontSize = 20.sp
                                    )
                                )
                            }
//                            // الصف العلوي للعملات
//                            Row(
//                                verticalAlignment = Alignment.CenterVertically
//                            ) {
//                                Spacer(modifier = Modifier.width(10.dp))
//                                Text(
//                                    text = "ADS",
//                                    style = TextStyle(
//                                        color = Color.Red,
//                                        fontWeight = FontWeight.Bold,
//                                        fontSize = 20.sp,
//                                        textDecoration = TextDecoration.LineThrough // خط يتوسط النص
//
//
//
//                                    )
//                                )
//                            }
                        }
                        Column(
                            modifier = Modifier
                                .padding(vertical = 20.dp)
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState()),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

//                    InfiniteRotatingLogo()

                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(top = 30.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                //Music Icon
                                MusicPlayerControl(context , viewModelQuestion)

                                // Sound Button
                                IconButton(
                                    onClick = {
                                        viewModelQuestion.controlSound(context)
                                        soundControl(context , stateSound==false)
                                    },
                                ) {
                                    Icon(
                                        painter = painterResource(
                                            id =if(stateSound==true) R.drawable.sound else R.drawable.mutesound
                                        ),
                                        contentDescription = "",
                                        tint = Color(0xFFFFFFFF),
                                        modifier = Modifier.size(50.dp)
                                    )
                                }

////                            Setting
//                                IconButton(onClick = {
//                                    soundControl(context , stateSound)
//
//                                }) {
//                                    Icon(painter = painterResource(id = R.drawable.setting),
//                                        contentDescription = "",
//                                        tint = Color(0xFFFFFFFF),
//                                        modifier = Modifier.size(50.dp))
//                                }//end setting


                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Column(
                                verticalArrangement = Arrangement.Center
                            ) {
                                //Button Start Game
                                AnimatedScalingStartGame(context, navController , stateSound)

//
//                                //Buy Coin
//                                Box(
//                                    modifier = Modifier
//                                        .padding(top = 20.dp)
//                                        .fillMaxWidth()
//                                        .height(60.dp)
//                                        .clip(RoundedCornerShape(10.dp)) // تدوير الحواف
//                                        .background(Color(0xFFFFAC0D)) // لون بنفسجي غامق
//                                        .clickable {
//                                            soundControl(context, stateSound)
//
//                                        },
//                                    contentAlignment = Alignment.Center
//                                ) {
//                                    Text(
//                                        text = "اشتري عملات",
//                                        color = Color.White, // تغيير اللون ليكون واضحًا مع الخلفية
//                                        fontSize = 18.sp,
//                                        fontWeight = FontWeight.Bold
//                                    )
//                                } // end buy coin
//

//                                //review
//                                Box(
//                                    modifier = Modifier
//                                        .padding(top = 20.dp)
//                                        .fillMaxWidth()
//                                        .height(60.dp)
//                                        .clip(RoundedCornerShape(10.dp)) // تدوير الحواف
//                                        .background(Color(0xFFFFAC0D)) // لون بنفسجي غامق
//                                        .clickable {
//                                            soundControl(context, stateSound)
//
//                                        },
//                                    contentAlignment = Alignment.Center
//                                ) {
//                                    Text(
//                                        text = "قيم اللعبة",
//                                        color = Color.White, // تغيير اللون ليكون واضحًا مع الخلفية
//                                        fontSize = 18.sp,
//                                        fontWeight = FontWeight.Bold
//                                    )
//                                } // end review

                            }


                            Spacer(modifier = Modifier.height(50.dp))

//                            Text(text = "Created By\nAhmed Halawy" , style = TextStyle(
//                                color = Color.White,
//                                fontWeight = FontWeight.Normal,
//                                fontFamily = ibmLight,
//                                textAlign = TextAlign.Center
//                            ),
//                                modifier = Modifier.fillMaxWidth()
//                            )

                        }
                    }

                }
//            }



        }
    }
    BackHandler {
        (context as? Activity)?.finish()
    }
}


@Composable
fun AnimatedScalingStartGame(context: Context, navController: NavController, stateSound: Boolean) {
    val scale = remember { Animatable(1f) }

    LaunchedEffect(Unit) {
        while (true) {
            scale.animateTo(1f, animationSpec = tween(1000)) // تكبير
            scale.animateTo(0.95f, animationSpec = tween(1000))   // تصغير
        }
    }

    //start Game
    Box(
        modifier = Modifier
            .padding(top = 20.dp).padding(horizontal = 10.dp)
            .fillMaxWidth()
            .height(120.dp)
            .scale(scale.value)

            .clip(RoundedCornerShape(10.dp)) // تدوير الحواف
            .background(Color(0xFFFFAC0D)) // لون بنفسجي غامق
            .clickable {
                soundControl(context, stateSound)
                navController.navigate(Routes.levelsScreen)

            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "ابدأ اللعب",
            color = Color.White, // تغيير اللون ليكون واضحًا مع الخلفية
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold
        )
    }//end box StartGame()



//    Button(
//        onClick = { /* أي حدث عند النقر */ },
//        modifier = Modifier
//            .scale(scale.value)
//            .padding(16.dp),
//        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
//    ) {
//        Text(text = "Click Me", color = Color.White, fontSize = 16.sp)
//    }
}


@Composable
fun MusicPlayerControl(context: Context , viewModel: QuestionsViewModel) {
    val stateMusic = viewModel.musicLiveData.observeAsState().value?:false
    val iconRes = remember {
        mutableStateOf(0)
    }
    val isClickMusic = remember {
        mutableStateOf(stateMusic)
    }
    IconButton(
        onClick = {
            viewModel.controlMusic(context) // يتم التبديل مرة واحدة فقط بعد تنفيذ العملية المناسبة
            isClickMusic.value =  !stateMusic
             MusicPlayer.playMusic(context, isClickMusic.value)

        }
    ) {
        iconRes.value = if (isClickMusic.value) R.drawable.music else R.drawable.mutemusic
        val description = if (isClickMusic.value) "إيقاف الموسيقى" else "تشغيل الموسيقى"

        Icon(
            painter = painterResource(id = iconRes.value),
            contentDescription = description,
            tint = Color.White,
            modifier = Modifier.size(50.dp)
        )
    }
}


fun soundControl(context: Context , stateSound : Boolean){
    if (stateSound){

        MediaPlayer.create(context , R.raw.click).apply {
            setVolume(1.0f , 1.0f)
            start()
        }
    }

}