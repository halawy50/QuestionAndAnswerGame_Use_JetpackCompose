package com.halawystory.askandanswer

import android.content.Context
import android.media.MediaPlayer

object MusicPlayerRoom {
    private var mediaPlayer: MediaPlayer? = null
    private var checkIsMusicOn: Boolean = false

    fun playMusic(isMusicOn: Boolean) {
        checkIsMusicOn = isMusicOn
    }

    fun start(context: Context) {
        if (checkIsMusicOn) {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(context, R.raw.musicroomgame).apply {
                    isLooping = true
                    setVolume(1f, 1f)
                    start()
                }
            } else if (!(mediaPlayer?.isPlaying ?: false)) {
                mediaPlayer?.start()
            }
        }
    }

    fun pauseMusic() {
        if (checkIsMusicOn) {
            mediaPlayer?.takeIf { it.isPlaying }?.pause()
        }
    }

    fun returnMusic() {
        if (checkIsMusicOn) {
            mediaPlayer?.apply {
                pause()
                seekTo(0)
            }
        }
    }

    fun lowVolume() {
        mediaPlayer?.setVolume(0.0f, 0.0f)
    }

    fun highVolume() {
        mediaPlayer?.setVolume(1f, 1f)
    }

    fun releaseMusic() : Boolean{
        mediaPlayer?.release()
        mediaPlayer = null
        if (mediaPlayer==null)
            return true
        else return false
    }
}
