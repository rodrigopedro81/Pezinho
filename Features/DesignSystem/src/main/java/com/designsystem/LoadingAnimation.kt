package com.designsystem

import android.os.CountDownTimer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants.IterateForever
import com.airbnb.lottie.compose.rememberLottieComposition

private const val DEFAULT_DURATION = 2000L

@Composable
fun LoadingAnimation(
    duration: Long = DEFAULT_DURATION,
    onFinish: () -> Unit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    LottieAnimation(
        composition,
        iterations = IterateForever,
    )
    object: CountDownTimer(duration, 500) {
        override fun onTick(remaining: Long) {
//            remainingTime = remaining
        }
        override fun onFinish() {
            onFinish.invoke()
        }
    }.start()
}