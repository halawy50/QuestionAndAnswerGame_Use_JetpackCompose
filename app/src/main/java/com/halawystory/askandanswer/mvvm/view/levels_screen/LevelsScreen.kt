package com.halawystory.askandanswer.mvvm.view.levels_screen

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.halawystory.askandanswer.MusicPlayer
import com.halawystory.askandanswer.R

import com.halawystory.askandanswer.Routes
import com.halawystory.askandanswer.mvvm.model.Difficulty
import com.halawystory.askandanswer.mvvm.model.LevelsModels
import com.halawystory.askandanswer.mvvm.model.StateLevel
import com.halawystory.askandanswer.mvvm.view.component.DialogPriceLevel
import com.halawystory.askandanswer.mvvm.viewmodel.QuestionsViewModel
import com.halawystory.askandanswer.ui.theme.green
import com.halawystory.askandanswer.ui.theme.ibmBold
import com.halawystory.askandanswer.ui.theme.orange
import com.halawystory.shopapp.mvvm.view.HomeScreen.GifFromDrawable


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LevelsScreen(context: Context , navController: NavController , viewModel: QuestionsViewModel) {



    val allLevels by viewModel.allLevelsOpenLiveData.observeAsState()
    val stateSound by viewModel.soundLiveData.observeAsState(initial = false)
    val visableLoading by viewModel.clickLevel.collectAsState()
    val uniqueQuestons by viewModel.uniqueQuestion.collectAsState()

    val isDialogPriceLevel by viewModel.isDialogPriceLevel.collectAsState()

    val myCoin by viewModel.coinLiveData.observeAsState()

    val visableLoadingLevelDone = remember {
        mutableStateOf(false)
    }


    val difficultyClick = remember {
        mutableStateOf(Difficulty.EASY)
    }
    if (uniqueQuestons==1){
//        defuiltClick(context , viewModel.soundLiveData.observeAsState().value?:false)
        navController.navigate(Routes.roomGameScreen)
    }

    LaunchedEffect(Unit) {
        viewModel.fetchAllLevels()
    }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center // يضع كل المحتويات في المنتصف
    ){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.8f)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
                , horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                IconButton(
                    onClick = {
                        if (stateSound==true){
                            if (viewModel.soundLiveData.value==true)
                                    MediaPlayer.create(context , R.raw.back).apply {
                                setVolume(5.0f , 5.0f)
                                start()
                            }
                        }
                        navController.popBackStack()

                    },
                    modifier = Modifier.size(50.dp)

                ) {

                    Image(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "back",
                    )

                }

                Text(text = "المستويات",
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = ibmBold,
                        fontSize = 20.sp
                    )
                )

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
                        text = "${myCoin?:0}",
                        style = TextStyle(
                            color = Color(0xFFF2FF00),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )
                }
                // الصف الع

            }


            Box (
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
            ){
                LazyColumn {
                    allLevels?.let { listLevels ->
                        items(listLevels){ level->
                            CardLevels(
                               navController= navController ,
                                levelsData = level ,
                                context =  context ,
                                viewModel =  viewModel ,
                                visableLoadingLevelDone = visableLoadingLevelDone ,
                                clickCardDifficulity = { difficulty ->
                                   difficultyClick.value = difficulty
                            })
                            Spacer(modifier = Modifier.height(20.dp))
                        }

                    }

                }

            }

        }


        if (visableLoading==true || visableLoadingLevelDone.value == true)
            Box(modifier = Modifier
                .fillMaxSize()
                .clickable(enabled = true) {}
            ) {
                Spacer(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.8f)))

               Column(
                   modifier = Modifier.fillMaxSize(),
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally
               ) {
                   Box(
                       modifier = Modifier
                           .size(200.dp)
                           .clip(RoundedCornerShape(20.dp))
                   ) {
                       GifFromDrawable(context , R.drawable.relax)
                   }
                   Spacer(modifier = Modifier.height(10.dp))
                   Text(text = "...برجاء الانتظار",
                       style = TextStyle(
                           color = Color.White,
                           fontFamily = ibmBold,
                           fontSize = 20.sp
                       )
                   )

               }

            }

        if (isDialogPriceLevel==true ){

            if (viewModel.soundLiveData.value==true)
                MediaPlayer.create(context , R.raw.pop).start()
            DialogPriceLevel(
                context = context ,
                viewModel = viewModel,
                minsCoin = 200,
                difficulty = difficultyClick.value
            )
        }





    }






}


@Composable
fun CardLevels(navController: NavController
               , levelsData : LevelsModels
               , context: Context
               , viewModel: QuestionsViewModel
               , visableLoadingLevelDone : MutableState<Boolean>
                , clickCardDifficulity : (Difficulty) -> Unit){
    val stateSound by viewModel.soundLiveData.observeAsState(initial = true)

    ScaleClickItem(
        onClick = {
            if (stateSound == true&&levelsData.solveQuestions<=0) {
                        MediaPlayer
                            .create(
                                context,
                                if (levelsData.state == StateLevel.OpenDone || (levelsData.state == StateLevel.OpenProcess&&levelsData.solveQuestions==0)) {
                                    R.raw.opendoor
                                } else {
                                    R.raw.closedore
                                }
                            )
                            .apply {
                                setVolume(5.0f, 5.0f)
                                start()
                            }
            }

            if(levelsData.state == StateLevel.OpenDone ){
               if (viewModel.getAllQuestionLevelEnd(difficulty = levelsData.difficult)){

                   visableLoadingLevelDone.value = true

                   if(visableLoadingLevelDone.value == true){
                       ///////////////////////////////////////////
                       if (viewModel.soundLiveData.value==true)
                           MediaPlayer
                               .create(
                                   context,
                                   if (levelsData.state == StateLevel.OpenDone || (levelsData.state == StateLevel.OpenProcess&&levelsData.solveQuestions==0)) {
                                       R.raw.opendoor
                                   } else {
                                       R.raw.closedore
                                   }
                               )
                               .apply {
                                   setVolume(5.0f, 5.0f)
                                   start()
                               }
                       /////////////////////////////////
                        Handler(Looper.getMainLooper()).postDelayed(
                            {
                                if (viewModel.musicLiveData.value==true)
                                    MusicPlayer.lowVolume()
                                navController.navigate(Routes.roomGameLevelDoneScreen)
//                                visableLoadingLevelDone.value = false

                            }
                        ,1000)
                   }

               }
            }
             else if(levelsData.state == StateLevel.OpenProcess) {


                clickCardDifficulity(levelsData.difficult)
                viewModel.setIsWrong(false)
                viewModel.restartProcessPones()

               viewModel.fetchUnsolvedQuestions(levelsData.difficult)


            } else {
                Toast.makeText(context, "مستوي مغلق, يجب ان تتخطي المستوي الذي يسبقه", Toast.LENGTH_SHORT).show()
            }

        }
    )
    {
        Card(modifier = Modifier

        ) {
            Column {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(orange)

                ){
                    Image(
                        painter = painterResource(id = levelsData.imageLvel),
                        contentDescription = "image Level",
                        contentScale = ContentScale.Crop
                        ,modifier = Modifier
                            .fillMaxSize()
                    )
                    if (levelsData.state.equals(StateLevel.Closed))
                        Spacer(modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f)) )

                    if (levelsData.state.equals(StateLevel.OpenDone))
                       Icon(painter = painterResource(id = R.drawable.correct),
                           contentDescription = "" ,
                           tint = green,
                           modifier = Modifier.fillMaxSize()
                               .align(Alignment.Center)
                       )
                    

                    Row(modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically) {

                        if (levelsData.state.equals(StateLevel.Closed))
                            Image(painter = painterResource(
                                id = R.drawable.lockclose
                            ),
                                contentDescription = "Lock",
                                modifier = Modifier.size(60.dp))

                    }

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFB0865C))
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Text(text = "(${levelsData.solveQuestions}/${levelsData.totalQuestions})"+" ${levelsData.nameLevel}"
                        ,  style = TextStyle(
                            color = Color.White,
                            fontFamily = ibmBold,
                            fontSize = 20.sp
                        ),
                    )



                }
            }
        }

    }

}

@Composable
fun ScaleClickItem(
    onClick: () -> Unit = {},
    content: @Composable () -> Unit
) {

    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,  // عند الضغط يصغر، عند الإفراج يعود للحجم الطبيعي
        animationSpec = tween(
            durationMillis = 100,  // مدة أقصر للاستجابة الفورية
            easing = FastOutSlowInEasing
        )
    )

    Box(
        modifier = Modifier
            .scale(scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        // تفعيل التأثير عند الضغط
                        isPressed = true

                        // انتظار الإفراج عن الضغط
                        val released = tryAwaitRelease()

                        // إعادة الحجم للوضع الطبيعي
                        isPressed = false

                        // تنفيذ الإجراء فقط إذا تم الإفراج بنجاح (ضغطة كاملة)
                        if (released) {
                            onClick()
                        }
                    },
                    // إلغاء معالجات اللمس الأخرى
                    onTap = null,
                    onDoubleTap = null,
                    onLongPress = null
                )
            }
    ) {
        content()
    }
}