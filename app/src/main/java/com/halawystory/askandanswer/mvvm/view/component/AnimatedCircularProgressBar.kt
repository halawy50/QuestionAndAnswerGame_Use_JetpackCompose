package com.halawystory.askandanswer.mvvm.view.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.text.BasicText
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import com.halawystory.askandanswer.ui.theme.orange
import com.halawystory.askandanswer.ui.theme.yallow
import kotlinx.coroutines.launch

enum class State {
    Process, Restart, Stop
}

@Composable
fun AnimatedCircularProgressBar(
    state: State,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 5.dp,
    onValueChange: (Int) -> Unit
) {
    val animatedProgress = remember { Animatable(1f) } // يبدأ من 100%
    val scale = remember { Animatable(1f) } // للتحكم في تأثير النبض

    LaunchedEffect(state) {
        when (state) {
            State.Process -> {
                animatedProgress.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = 10000, easing = LinearEasing)
                )
            }
            State.Restart -> {
                animatedProgress.snapTo(1f)
                animatedProgress.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = 10000, easing = LinearEasing)
                )
            }
            State.Stop -> {
                animatedProgress.stop()
            }
        }
    }

    val currentProgress = animatedProgress.value * 100
    val displayValue = when {
        currentProgress >= 70 -> 15
        currentProgress >= 30 -> 10
        else -> 5
    }

    LaunchedEffect(displayValue) {
        onValueChange(displayValue)

        // تشغيل تأثير النبض عند تغيير القيمة
        launch {
            while (true) {
                scale.animateTo(1.2f, animationSpec = tween(300, easing = FastOutSlowInEasing))
                scale.animateTo(1f, animationSpec = tween(300, easing = FastOutSlowInEasing))
            }
        }
    }

    val progressColor = when {
        currentProgress >= 70 -> Color.Green
        currentProgress >= 30 -> orange
        else -> yallow
    }

    Box(modifier = modifier.size(70.dp), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val size = size.minDimension
            val stroke = strokeWidth.toPx()
            val radius = (size - stroke) / 2
            val center = Offset(size / 2, size / 2)
            val startAngle = -90f
            val sweepAngle = animatedProgress.value * 360

            drawArc(
                color = progressColor,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
                style = Stroke(stroke, cap = StrokeCap.Round)
            )
        }

        BasicText(
            text = "+$displayValue",
            style = TextStyle(
                color = progressColor,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.scale(scale.value) // تطبيق تأثير النبض
        )
    }
}

