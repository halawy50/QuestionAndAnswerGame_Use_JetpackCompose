package com.halawystory.askandanswer.mvvm.view.room_game_screen_LevelDone

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.UnstableApi
import com.halawystory.askandanswer.R
import com.halawystory.askandanswer.mvvm.model.ResumeGameWithAds
import com.halawystory.askandanswer.mvvm.repository.StateDecrementCoin
import com.halawystory.askandanswer.mvvm.viewmodel.QuestionsViewModel
import com.halawystory.askandanswer.ui.theme.backgroundExitRoom
import com.halawystory.askandanswer.ui.theme.ibmBold
import com.halawystory.askandanswer.ui.theme.textDialogExitRoom
import com.halawystory.askandanswer.ui.theme.yallowButton

@OptIn(UnstableApi::class)
@SuppressLint("SuspiciousIndentation")
@Composable
fun DialogWrongAndswerLevelDone(
    onExitClick: (Boolean) -> Unit,
    viewModel: QuestionsViewModel
){

    val context = LocalContext.current
    val rewardeAds by viewModel.reward.collectAsState()

    val isRewardedLoaded by viewModel.isRewardedLoaded.collectAsState()

    val isWrong by viewModel.checkWrongLevelEnd.collectAsState()

    val userLoseGameButCanResumeUseAds by viewModel.userLoseGameButCanResumeUseAds.collectAsState()


    // عندما تكون الإجابة خاطئة ويكون المستخدم قد أنهى الإعلان، يتم استئناف السؤال
    if (isWrong && userLoseGameButCanResumeUseAds == ResumeGameWithAds.UserClickAndEndShowAds) {
        Toast.makeText(context, "Return Question", Toast.LENGTH_SHORT).show()
        viewModel.restartLevelDoneQuestionFronDialogWrongAnswer()
        viewModel.setStateAdsWhenUserLoseGame(ResumeGameWithAds.Non)
    }






    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black.copy(alpha = 0.7f)),
        contentAlignment = Alignment.Center
    ){

      Card(
          modifier =
          Modifier
              .fillMaxWidth()
              .padding(10.dp),
          colors = CardDefaults.cardColors(
              containerColor = backgroundExitRoom
          ),

      ){
          Column(
              modifier = Modifier
                  .fillMaxWidth()
                  .background(backgroundExitRoom)
                  .padding(10.dp),
              horizontalAlignment = Alignment.CenterHorizontally,
              verticalArrangement = Arrangement.Center
          ) {

              Box(modifier = Modifier.fillMaxWidth(),
                  contentAlignment = Alignment.TopCenter

              ){

                  IconButton(
                      onClick = {
                          if (viewModel.soundLiveData.value==true) {
                              MediaPlayer.create(context, R.raw.back).start()
                          }

                          viewModel.exitRoomLevelEnd()
                      },
                      modifier = Modifier.align(Alignment.TopEnd)
                  ) {
                      Icon(
                          imageVector = Icons.Default.Clear,
                          contentDescription = "cancel",

                          tint = Color.White,
                          modifier = Modifier
                              .size(60.dp)
                              .padding(5.dp)
                              .background(
                                  color = Color(0xFFFC5C26),
                                  shape = RoundedCornerShape(5.dp)
                              )
                              .border(1.5.dp, Color.Black, shape = RoundedCornerShape(5.dp))

                      )
                  }

                  Text(text = "استكمل التحدي", style = TextStyle(
                      fontFamily = ibmBold,
                      color = textDialogExitRoom,
                      fontSize = 25.sp
                  ),
                      modifier = Modifier
                          .fillMaxWidth()
                          .align(Alignment.Center),
                      textAlign = TextAlign.Center
                  )
              }



              Spacer(modifier = Modifier.height(30.dp))


              Row(
                  modifier = Modifier
                      .fillMaxWidth()
                      .height(60.dp)
              ){


                  Card(
                      shape = RoundedCornerShape(20),
                      colors = CardDefaults.cardColors(
                          containerColor = yallowButton
                      ),
                      border = BorderStroke(2.dp , Color.Black),
                      modifier = Modifier
                          .weight(0.5f)
                          .fillMaxHeight(),

                      onClick = {
                          if (viewModel.soundLiveData.value==true)
                                MediaPlayer.create(context , R.raw.click).start()

                          val decrementState = viewModel.decrementCoin(context, 200)
                              when (decrementState) {
                                  StateDecrementCoin.SUCCESS -> {
                                      viewModel.getCoin(context)
                                      viewModel.restartLevelDoneQuestionFronDialogWrongAnswer()
                                  }
                                  StateDecrementCoin.DONTHAVEENOUGHMONEY -> {
                                      Toast.makeText(context, "ليس لدي عملة كافية", Toast.LENGTH_SHORT).show()
                                  }
                                  else -> {
                                      Toast.makeText(context, "حدث خطأ ما", Toast.LENGTH_SHORT).show()
                                  }
                              }

                      }

                  ) {
                      Row(
                          modifier = Modifier
                              .fillMaxSize()
                              .padding(10.dp),
                          verticalAlignment = Alignment.CenterVertically,
                          horizontalArrangement = Arrangement.Center
                      ) {
                          Image(
                              painter = painterResource(id = R.drawable.coin),
                              contentDescription = "coin",
                              modifier = Modifier
                                  .height(30.dp)
                                  .width(30.dp)
                          )
                          Text(
                              text = " x${200}",
                              style = TextStyle(
                                  color = Color.White,
                                  fontWeight = FontWeight.Bold,
                                  fontSize = 22.sp,
                                  fontFamily = ibmBold,
                                  shadow = Shadow(
                                      color = Color.Black.copy(alpha = 1f),  // Shadow color
                                      offset = Offset(3f, 3f), // X, Y offset
                                  ),

                                  )
                          )
                      }
                  }



                  if (isRewardedLoaded==true)
                  Spacer(modifier = Modifier.width(10.dp))


                  //Continue By Show Ads
                  if (isRewardedLoaded==true)
                  Card(
                      shape = RoundedCornerShape(20),
                      colors = CardDefaults.cardColors(
                          containerColor = yallowButton
                      ),
                      border = BorderStroke(2.dp , Color.Black),
                      modifier = Modifier
                          .weight(0.5f)
                          .fillMaxHeight()
                          .clickable {
                              if (viewModel.soundLiveData.value==true)
                                    MediaPlayer.create(context , R.raw.click).start()


                              viewModel.setStateAdsWhenUserLoseGame(ResumeGameWithAds.UserClickAds)
                              rewardeAds?.showRewardedAd()
                              viewModel.setSkipQuestion(true)


                          },
                  ) {
                          Row(
                              verticalAlignment = Alignment.CenterVertically,
                              horizontalArrangement = Arrangement.SpaceBetween,
                              modifier = Modifier
                                  .fillMaxSize()
                                  .padding(10.dp)

                          ) {

                              Image(
                                  painter = painterResource(id = R.drawable.video),
                                  contentDescription = "videoIocnAds" ,
                                  modifier = Modifier
                                      .weight(0.5f)
                                      .size(30.dp)
                              )
                              Spacer(modifier = Modifier.width(10.dp))

                              Text(
                                  modifier = Modifier
                                      .weight(0.5f)
                                      .fillMaxHeight()
                                      .padding(top = 3.dp),
                                  text = "مجانا",
                                  style = TextStyle(
                                      color = Color.White,
                                      fontWeight = FontWeight.Bold,
                                      fontSize = 22.sp,
                                      fontFamily = ibmBold,
                                      shadow = Shadow(
                                          color = Color.Black.copy(alpha =1f),  // Shadow color
                                          offset = Offset(3f, 3f), // X, Y offset
                                      ),

                                      ),
                                  textAlign = TextAlign.Center

                                  )
                          }

                  }//end card Ads




              }
          }
      }
  }
}