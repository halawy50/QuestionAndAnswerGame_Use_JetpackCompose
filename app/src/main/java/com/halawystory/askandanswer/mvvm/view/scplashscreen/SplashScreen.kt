package com.halawystory.askandanswer.mvvm.view.scplashscreen

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import com.halawystory.askandanswer.Routes
import com.halawystory.askandanswer.mvvm.service.Quotes
import com.halawystory.askandanswer.ui.theme.ibmBold
import com.halawystory.askandanswer.ui.theme.ibmLight
import kotlinx.coroutines.delay
import kotlin.random.Random


@OptIn(UnstableApi::class)
@Composable
fun SplashScreen(navController: NavController) {
    val currentNavController by rememberUpdatedState(navController)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoadingBar(durationInSeconds = 10, progressColor = Color(0xFFFFAC0D), backgroundColor = Color.LightGray)

            Text(
                text = "سؤال وجواب",
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = ibmBold,
                    fontSize = 50.sp
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            val randomQuote by remember {
                mutableStateOf(Quotes.quotes.getOrNull(Random.nextInt(Quotes.quotes.size)) ?: "اقتباس غير متوفر")
            }

            Log.e("EBGSD", "SplashScreen recomposed")

            Text(
                textAlign = TextAlign.Center,
                text = randomQuote,
                style = TextStyle(
                    color = Color.White,
                    fontFamily = ibmLight,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                ),
            )

            Spacer(modifier = Modifier.height(150.dp))

            // التنقل إلى الشاشة الرئيسية بعد انتهاء التحميل
            LaunchedEffect(Unit) {
                delay(5000) // تأخير 5 ثوانٍ
                currentNavController.navigate(Routes.homeScreen)
            }
        }
    }
}

@Composable
fun LoadingBar(
    durationInSeconds: Int, // مدة التحميل
    progressColor: Color = Color.Blue, // لون خط التحميل
    backgroundColor: Color = Color.Gray.copy(alpha = 0.3f) // لون الخلفية
) {
    var progress by remember { mutableStateOf(0f) }

    LaunchedEffect(Unit) {
        val step = 1f / (durationInSeconds * 10) // تقسيم الوقت إلى أجزاء صغيرة
        while (progress < 1f) {
            delay(100) // تحديث كل 100ms
            progress = (progress + step).coerceAtMost(1f) // ضمان عدم تجاوز 1f
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        // شريط التحميل المستدير
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(10.dp)
                .clip(RoundedCornerShape(50)), // مستدير
            color = progressColor,
            trackColor = backgroundColor
        )
    }
}
