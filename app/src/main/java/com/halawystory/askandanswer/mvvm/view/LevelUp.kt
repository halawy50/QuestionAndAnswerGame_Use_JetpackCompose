package com.halawystory.askandanswer.mvvm.view

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.halawystory.askandanswer.MusicPlayer
import com.halawystory.askandanswer.MusicPlayerRoom
import com.halawystory.askandanswer.R
import com.halawystory.askandanswer.mvvm.model.CovertDifficulty.convertDifficulty
import com.halawystory.askandanswer.mvvm.model.LevelsOpenEntityDataBase
import com.halawystory.askandanswer.mvvm.service.LevelsList
import com.halawystory.askandanswer.mvvm.viewmodel.QuestionsViewModel
import com.halawystory.askandanswer.ui.theme.ibmBold
import com.halawystory.askandanswer.ui.theme.yallowButton
import com.halawystory.shopapp.mvvm.view.HomeScreen.GifFromDrawable

@Composable
fun LevelUpDialog(levelNewOpen : LevelsOpenEntityDataBase , viewModel: QuestionsViewModel){
    val reward by viewModel.reward.collectAsState()
    val checkAds = remember {
        mutableStateOf(false)
    }
    if (checkAds.value==false){
        reward?.showRewardedAd()
        checkAds.value = true

    }

    val context = LocalContext.current

    MusicPlayerRoom.releaseMusic()
    if (viewModel.musicLiveData.value==true)
        MusicPlayer.highVolume()

    if (viewModel.musicLiveData.value==true)
            MediaPlayer.create(context , R.raw.levelup).start()

    Box(modifier = Modifier
        .clickable(
            interactionSource = remember { MutableInteractionSource() }, // يمنع تأثيرات الضغط
            indication = null, // يمنع ظهور أي تأثير عند الضغط
            enabled = true
        ) {}
        .fillMaxSize(),
        contentAlignment = Alignment.Center

    ){

       GifFromDrawable(context , R.drawable.party)

        Box(
            modifier =
            Modifier
                .padding(20.dp)
                .fillMaxWidth()

            ){


            Column(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .background(
                        color = Color(0xFFFFEEA4),
                        shape = RoundedCornerShape(16.dp),
                    )
                    .border(10.dp, yallowButton, shape = RoundedCornerShape(16.dp))
                    .padding(20.dp)
                    .padding(top = 20.dp)
                    .align(Alignment.BottomCenter),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ){



                LevelsList.livelsList.find {
                    it.difficult == levelNewOpen.difficult
                }?.let { painterResource(id = it.imageLvel) }
                    ?.let {
                        Image(painter = it,
                        contentDescription = "Image Difficult",
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(16.dp))
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop)
                    }

                Spacer(modifier = Modifier.height(20.dp))


                Text(text = "مستوى ${convertDifficulty(levelNewOpen.difficult)}",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontFamily = ibmBold,
                        color = Color(0xFF945C2C),
//                        shadow = Shadow(
//                            color = Color.Black.copy(alpha =1f),  // Shadow color
//                            offset = Offset(2f, 2f), // X, Y offset
////                                            blurRadius = 5f // Blur effect
//                        ),
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(30.dp))


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp))
//                        .border(width = 5.dp, color = Color.Blue, shape = RoundedCornerShape(16.dp)) // ✅ خلي الـ shape نفس الـ shadow
                        .background(color = yallowButton, shape = RoundedCornerShape(16.dp))
                        .padding(horizontal = 32.dp, vertical = 16.dp)
                        .clickable(enabled = true) {
                            viewModel.setStateFinishLevel(false)
                            if (viewModel.musicLiveData.value == true)
                                MusicPlayer.highVolume()

//                            Toast.makeText(context , "استمر" , Toast.LENGTH_SHORT).show()
                        }
                ) {
                    Text(
                        text = "استمر",
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontFamily = ibmBold,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))


            }
            val topRoundedShape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = 16.dp,
                bottomEnd = 16.dp
            )

            Box(
                modifier = Modifier
                    .absoluteOffset(y = (-40).dp)
                    .shadow(elevation = 8.dp, shape = topRoundedShape)
//                    .border(width = 1.dp, color = Color(0xFF4A7B00), shape = topRoundedShape)
                    .background(color = Color(0xFF79B225), shape = topRoundedShape)
                    .padding(horizontal = 32.dp, vertical = 16.dp)
                    .align(Alignment.TopCenter)
            )
            {
                Text(
                    text = "رفع المستوي",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontFamily = ibmBold,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }



        }

    }

}


//                    shadow = Shadow(
//                        color = Color.Black.copy(alpha =1f),  // Shadow color
//                        offset = Offset(2f, 2f), // X, Y offset
////                                            blurRadius = 5f // Blur effect
//                    ),