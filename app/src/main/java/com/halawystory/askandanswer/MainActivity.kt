package com.halawystory.askandanswer

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.halawystory.askandanswer.Ads.RewardeAds
import com.halawystory.askandanswer.mvvm.repository.QuestionsRepository
import com.halawystory.askandanswer.mvvm.view.LevelUpDialog
import com.halawystory.askandanswer.mvvm.view.home_screen.HomeScreen
import com.halawystory.askandanswer.mvvm.view.levels_screen.LevelsScreen
import com.halawystory.askandanswer.mvvm.view.room_game_screen.RoomGameScreen
import com.halawystory.askandanswer.mvvm.view.room_game_screen_LevelDone.RoomGameScreenLevelEnd
import com.halawystory.askandanswer.mvvm.view.scplashscreen.SplashScreen
import com.halawystory.askandanswer.mvvm.viewmodel.QuestionsViewModel
import com.halawystory.askandanswer.mvvm.viewmodel.QuestionsViewModelFactory
import com.halawystory.askandanswer.ui.theme.AiTheme
import java.util.Locale

class MainActivity : ComponentActivity() {

    private lateinit var viewmodelQuestion: QuestionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLocale("en") // تعيين اللغة العربية


        // جعل شريط الحالة شفاف
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        window.navigationBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isStatusBarContrastEnforced = false
            window.isNavigationBarContrastEnforced = false
        }
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false // استخدم true للخلفيات الفاتحة

        val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView)

        windowInsetsController?.hide(WindowInsetsCompat.Type.navigationBars())
        windowInsetsController?.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE


        val repository = QuestionsRepository()
        val factory = QuestionsViewModelFactory(repository)
        viewmodelQuestion = ViewModelProvider(this, factory).get(QuestionsViewModel::class.java)


        viewmodelQuestion.firstOpenApp(this@MainActivity)
        viewmodelQuestion.getCoin(this@MainActivity)
        viewmodelQuestion.getSound(this@MainActivity)
       viewmodelQuestion.getMusic(this@MainActivity)



        setContent {
                val navController = rememberNavController()
                val levelsOpenEntityDataBase by viewmodelQuestion.levelsOpenEntityDataBase.collectAsState()
                val isFinishLevel by viewmodelQuestion.isFinishLevel.collectAsState()
                val stateMusic = viewmodelQuestion.musicLiveData.observeAsState().value?:false
                MusicPlayer.playMusic(this@MainActivity, stateMusic)
                MusicPlayerRoom.playMusic( stateMusic)
                val rewardAds by viewmodelQuestion.reward.collectAsState()


            // بعد التأكد من تهيئة AdMob، تحقق مما إذا كان `rewardAds` غير مهيأ ثم قم بتحميله
            if (rewardAds == null) {
                val newRewardAds = RewardeAds(this@MainActivity , viewModel = viewmodelQuestion)
                viewmodelQuestion.setRewardAds(newRewardAds)

                newRewardAds.loadRewardedAd()
            }


            viewmodelQuestion.getMyLevel()

            AiTheme {

                // استخدام insets بشكل صحيح في Compose
                val statusBarHeight = with(LocalDensity.current) {
                    WindowInsets.statusBars.getTop(this).toDp()
                }


                    Box(modifier = Modifier.fillMaxSize()) {

                        Image(
                            painter = painterResource(id = R.drawable.background),
                            contentDescription = "Background Image",
                            modifier = Modifier.fillMaxSize().fillMaxHeight(),
                            contentScale = ContentScale.Crop
                        )

                            Box(
                                modifier = Modifier
                                    .padding(top = statusBarHeight)
                                    .fillMaxSize()

                            ) {


                                NavHost(
                                    navController = navController,
                                    startDestination = Routes.splashScreen
                                ) {
                                    composable(Routes.splashScreen) { SplashScreen(navController) }
                                    composable(Routes.homeScreen) {
                                        HomeScreen(
                                            this@MainActivity,
                                            navController,
                                            viewmodelQuestion
                                        )
                                    }
                                    composable(Routes.levelsScreen) {
                                        LevelsScreen(
                                            this@MainActivity,
                                            navController,
                                            viewmodelQuestion
                                        )
                                    }
                                    composable(Routes.roomGameScreen) {
                                        RoomGameScreen(
                                            navController,
                                            this@MainActivity,
                                            viewmodelQuestion
                                        )
                                    }

                                    composable(Routes.roomGameLevelDoneScreen) {
                                        RoomGameScreenLevelEnd(
                                            navController,
                                            this@MainActivity,
                                            viewmodelQuestion
                                        )
                                    }

                                }
                                if (isFinishLevel) {
                                    MusicPlayerRoom.releaseMusic()

                                    levelsOpenEntityDataBase?.let {
                                        LevelUpDialog(
                                            it,
                                            viewModel = viewmodelQuestion
                                        )
                                    }
                                }
                        }
                }

            }
        }
    }

    private fun handleMusicLifecycle() {
        if (viewmodelQuestion.musicLiveData.value==true)
            MusicPlayer.playMusic(this, viewmodelQuestion.musicLiveData.value?:false)
    }

    override fun onPause() {
        super.onPause()
        MusicPlayer.pauseMusic() // فرضًا لديك دالة لإيقاف الموسيقى
        MusicPlayerRoom.pauseMusic()
    }

    override fun onRestart() {
        super.onRestart()
        handleMusicLifecycle()
//        MusicPlayerRoom.start()

    }

    override fun onResume() {
        super.onResume()
        handleMusicLifecycle()
//        MusicPlayerRoom.start()

    }

    override fun onStart() {
        super.onStart()
        handleMusicLifecycle()

//        MusicPlayerRoom.start()

    }

    private fun setLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)

        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )
    }

}
