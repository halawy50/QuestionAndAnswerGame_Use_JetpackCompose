package com.halawystory.askandanswer

import android.content.Context
import android.media.MediaPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object MusicPlayer {
    private var mediaPlayer: MediaPlayer? = null

    fun playMusic(context: Context, isMusicOn: Boolean) {
        if (isMusicOn) {
            startMusic(context)
        } else {
            pauseMusic()
        }
    }

    private fun startMusic(context: Context) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.backgroundmusic).apply {
                isLooping = true
                start()
                setVolume(1f , 1f)
            }
        } else if (!mediaPlayer!!.isPlaying) {
            mediaPlayer?.start()  // استئناف التشغيل فقط
        }
    }

     fun pauseMusic() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause() // إيقاف مؤقت
            }
        }
    }

    fun lowVolume() {
        mediaPlayer?.let { player ->
            if (player.isPlaying) {
                val steps = 20 // عدد الخطوات لخفض الصوت
                val delayMillis = 100L // الزمن بين كل خفض للصوت

                CoroutineScope(Dispatchers.Main).launch {
                    for (i in steps downTo 0) {
                        val volume = i / steps.toFloat()
                        player.setVolume(volume, volume)
                        delay(delayMillis)
                    }
                    player.setVolume(0.0f, 0.0f) // تأكيد وصول الصوت للصفر
                }
            }
        }
    }

    fun highVolume() {
        mediaPlayer?.let { player ->
            if (player.isPlaying) {
                    player.start()
                    player.setVolume(1f,1f)
            }
        }
    }

    fun releaseMusic() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
