package com.halawystory.askandanswer.mvvm.view.component

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.halawystory.askandanswer.R
import com.halawystory.askandanswer.mvvm.model.Difficulty
import com.halawystory.askandanswer.mvvm.model.StateAds
import com.halawystory.askandanswer.mvvm.repository.StateDecrementCoin
import com.halawystory.askandanswer.mvvm.viewmodel.QuestionsViewModel
import com.halawystory.askandanswer.ui.theme.gray
import com.halawystory.askandanswer.ui.theme.ibmBold
import com.halawystory.askandanswer.ui.theme.yallowButton

@OptIn(UnstableApi::class)
@SuppressLint("SuspiciousIndentation", "CoroutineCreationDuringComposition",
    "StateFlowValueCalledInComposition"
)
@Composable
fun DialogPriceLevel(context: Context , difficulty: Difficulty , minsCoin : Int , viewModel: QuestionsViewModel){
    val processPushCoin  = remember {
        mutableStateOf(false)
    }
    val isClickButton  = remember {
        mutableStateOf(false)
    }
    val processButton = remember {
        mutableStateOf(ProcessButton.Noon)
    }

    Log.d("Dpended" ,"${difficulty}")


    var counterLoadAds = 0



    val rewardeAds by viewModel.reward.collectAsState()

    val rewardeAdsIsLoaded = viewModel.isRewardedLoaded.value


    if (rewardeAdsIsLoaded==true){
        counterLoadAds++
        Log.d("Counter" , "${counterLoadAds}")
    }else{

        counterLoadAds++
        Log.d("Counter" , "${counterLoadAds}")

    }



    val stateAds by viewModel.stateAds.collectAsState()

        Log.d("StateAds", "$stateAds  //// ${processButton.value}")


         when (processButton.value) {

             // زر شراء العملة (Push Price)
             ProcessButton.PushPrice -> {

                 if (processPushCoin.value) {
                     val decrementState = viewModel.decrementCoin(context, minsCoin)
                     when (decrementState) {
                         StateDecrementCoin.SUCCESS -> {
                             viewModel.getCoin(context)
                             viewModel.setDialogPriceLevelDicrementCoin()
                             viewModel.visbleLoadingAndEnterRoomGame(difficulty)
                         }
                         StateDecrementCoin.DONTHAVEENOUGHMONEY -> {
                             Toast.makeText(context, "ليس لدي عملة كافية", Toast.LENGTH_SHORT).show()
                         }
                         else -> {
                             Toast.makeText(context, "حدث خطأ ما", Toast.LENGTH_SHORT).show()
                         }
                     }

                     isClickButton.value = false
                     processPushCoin.value = false
                     processButton.value = ProcessButton.Noon
                 }
             }

             // زر مشاهدة الإعلانات (Ads)
             ProcessButton.Ads -> {

                 if (stateAds == StateAds.ENDSHOWADS) {

                     Handler(Looper.getMainLooper()).postDelayed({
                         if (stateAds == StateAds.ENDSHOWADS) {
                             viewModel.setStateAds(StateAds.NONClickADS)

                             Log.d("FuckerADS" ,"${stateAds}")
                             viewModel.setDialogPriceLevelDicrementCoin()
                             viewModel.visbleLoadingAndEnterRoomGame(difficulty)
                             isClickButton.value = false
                             viewModel.setProcessButton(ProcessButton.Noon)

                         }
                     }, 1000) // Delay 1 second


                 }
             }

             // زر البدء من جديد (Start Again)
             ProcessButton.StartAgain -> {
                 viewModel.deleteAllMyQuestionByDifficultyAndEnterGameRoom(difficulty ,{state ->
                     if (state==true){
                         viewModel.setStateDiaolog(false)
                         viewModel.visbleLoadingAndEnterRoomGame(difficulty)
                     }

                 })

                 Handler(Looper.getMainLooper()).postDelayed({
                     isClickButton.value = false
                     processButton.value = ProcessButton.Noon

                 }, 1000) // Delay 1 second


             }

             else -> Unit
         }








    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.8f))
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
            .clickable(
                interactionSource = remember { MutableInteractionSource() }, // يمنع تأثيرات الضغط
                indication = null, // يمنع ظهور أي تأثير عند الضغط
                enabled = true
            ) {},
        contentAlignment = Alignment.Center,

        )
    {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .background(
                    Color(0xFFDCD8D0),
                    shape = RoundedCornerShape(10.dp)
                ),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally

            )
        {

            Row(
                modifier = Modifier
                    .background(
                        color = gray,
                        shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)
                    )
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {

                if (isClickButton.value == false)
                    IconButton(
                        onClick = {
                            if (viewModel.soundLiveData.value==true)
                                    MediaPlayer.create(context , R.raw.back).start()

                            viewModel.setDialogPriceLevelDicrementCoin()
                        }
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
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .border(1.5.dp, Color.Black)

                        )
                    }

                Text(text = "استكمال التحدي" ,
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        fontFamily = ibmBold,

                        shadow = Shadow(
                            color = Color.Black.copy(alpha =1f),  // Shadow color
                            offset = Offset(2f, 2f), // X, Y offset
//                                            blurRadius = 5f // Blur effect
                        ),

                        ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )


            }

            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .padding(vertical = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {



                //شاهد (اعلان) لاستكمال التحدي
                if (isClickButton.value==false&&rewardeAdsIsLoaded!=null&&counterLoadAds==1)
                    Card(
                        shape = RoundedCornerShape(20),
                        colors = CardDefaults.cardColors(
                            containerColor = yallowButton
                        ),
                        border = BorderStroke(2.dp , Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(enabled = !processPushCoin.value)

                            {
                                if (viewModel.soundLiveData.value==true)
                                    MediaPlayer.create(context , R.raw.click).start()

                                isClickButton.value = true

                                processButton.value = ProcessButton.Ads

                                rewardeAds?.showRewardedAd()


                            }


                    ) {
//                    if (processPushCoin.value==false){
                        Row {
                            // الصف العلوي للعملات
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {

                                Image(
                                    painter = painterResource(id = R.drawable.video),
                                    contentDescription = "videoIocnAds" ,
                                    modifier = Modifier.size(40.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))

                                Text(
                                    text = "شاهد اعلان",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 25.sp,
                                        fontFamily = ibmBold,

                                        shadow = Shadow(
                                            color = Color.Black.copy(alpha =1f),  // Shadow color
                                            offset = Offset(3f, 3f), // X, Y offset
//                                            blurRadius = 5f // Blur effect
                                        ),

                                    ),

                                )
                            }
                            // الصف الع
                        }
//                    }else{
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(15.dp),
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.Center
//                        ) {
//                            CircularProgressIndicator()
//                        }
//                    }

                    }


                if (isClickButton.value==false&&rewardeAdsIsLoaded!=null&&counterLoadAds==1)
                    Spacer(modifier = Modifier.height(10.dp))


                if (isClickButton.value==false)
                //ادفع لاستكمال التحدي
                    Card(
                        shape = RoundedCornerShape(20),
                        colors = CardDefaults.cardColors(
                            containerColor = yallowButton
                        ),
                        border = BorderStroke(2.dp , Color.Black),

//                        colors = CardDefaults.cardColors(
//                            contentColor = Color.White,
//                            containerColor = Color.White.copy(alpha = 0.2f)
//                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(enabled = !processPushCoin.value)

                            {
                                if (viewModel.soundLiveData.value==true)
                                    MediaPlayer.create(context , R.raw.click).start()

                                processPushCoin.value = true
                                isClickButton.value = true
                                processButton.value = ProcessButton.PushPrice

                            }

                    ) {
                        // الصف العلوي للعملات
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
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
                                    fontSize = 25.sp,
                                    fontFamily = ibmBold,
                                    shadow = Shadow(
                                        color = Color.Black.copy(alpha = 1f),  // Shadow color
                                        offset = Offset(3f, 3f), // X, Y offset
//                                            blurRadius = 5f // Blur effect
                                    ),

                                )
                            )
                        }
                    }

                Spacer(modifier = Modifier.height(10.dp))




                if (isClickButton.value==false)
                //حذف جميع التحديات الخاصة بك
                    Card(
                        shape = RoundedCornerShape(20),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Red.copy(alpha = 0.6f),
                        ),
                        border = BorderStroke(2.dp , Color.Black),

                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                if (viewModel.soundLiveData.value==true)
                                    MediaPlayer.create(context , R.raw.click).start()

                                isClickButton.value = true
                                processButton.value = ProcessButton.StartAgain
                            }

                    ) {
//                    if (processPushCoin.value==false){
                        Row {
                            // الصف العلوي للعملات
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                ,
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "بدأ التحدي من جديد",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 25.sp,
                                        fontFamily = ibmBold,
                                        shadow = Shadow(
                                            color = Color.Black.copy(alpha =1f),  // Shadow color
                                            offset = Offset(5f, 5f), // X, Y offset
//                                            blurRadius = 5f // Blur effect
                                        ),

                                    )
                                )
                            }
                            // الصف الع
                        }
//                    }else{
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(15.dp),
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.Center
//                        ) {
//                            CircularProgressIndicator()
//                        }
//                    }
                    }



                //////////////////////////////////////////////////////////////////////////////////////////


                if (processButton.value == ProcessButton.Ads&&isClickButton.value==true)
                //شاهد (اعلان) لاستكمال التحدي
                Card(
                    shape = RoundedCornerShape(20),
                    colors = CardDefaults.cardColors(
                        contentColor = Color.White,
                        containerColor = Color.White.copy(alpha = 0.2f)
                    )) {
//                    if (processPushCoin.value==false){
                            // الصف العلوي للعملات
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator(
                                    color = Color.White
                                )
                            }
                }





                if (processButton.value == ProcessButton.PushPrice&&isClickButton.value==true)
                //ادفع لاستكمال التحدي
                Card(
                    shape = RoundedCornerShape(20),
                    colors = CardDefaults.cardColors(
                        contentColor = Color.White,
                        containerColor = Color.White.copy(alpha = 0.2f)
                    )) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(
                                color = Color.White
                            )
                        }
                }



                if (processButton.value == ProcessButton.StartAgain&&isClickButton.value==true)
                //حذف جميع التحديات الخاصة بك
                Card(
                    shape = RoundedCornerShape(20),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Red.copy(alpha = 0.6f),
                        contentColor = Color.White
                    )) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                ,
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator(
                                    color = Color.White
                                )
                            }
                }

            }

            ///////////////////////////////////////////////



        }


    }
}

enum class ProcessButton{
    Ads,
    PushPrice,
    StartAgain,
    Noon
}