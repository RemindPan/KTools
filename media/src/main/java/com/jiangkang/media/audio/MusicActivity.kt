package com.jiangkang.media.audio

import android.content.ComponentName
import android.content.Context
import android.media.AudioManager
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v7.app.AppCompatActivity
import com.jiangkang.media.R

class MusicActivity : AppCompatActivity() {

    private lateinit var mMediaBrowser: MediaBrowserCompat

    private lateinit var mMusicControllerCallback: MusicControllerCallback

    private lateinit var mMusicConnectionCallback: MusicConnectionCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        mMusicControllerCallback = MusicControllerCallback()
        mMusicConnectionCallback = MusicConnectionCallback(this, mMediaBrowser)

        mMediaBrowser = MediaBrowserCompat(
                this,
                ComponentName(this, MusicService::class.java),
                mMusicConnectionCallback,
                null
        )
    }

    override fun onStart() {
        super.onStart()
        mMediaBrowser.connect()
    }

    override fun onResume() {
        super.onResume()
        volumeControlStream = AudioManager.STREAM_MUSIC
    }

    override fun onStop() {
        super.onStop()
        MediaControllerCompat
                .getMediaController(this)
                ?.unregisterCallback(mMusicControllerCallback)
        mMediaBrowser.disconnect()
    }

}


class MusicConnectionCallback(var context: Context, var browser: MediaBrowserCompat) : MediaBrowserCompat.ConnectionCallback() {

    override fun onConnected() {
        browser.sessionToken.also { token ->
            val mediaController = MediaControllerCompat(context, token)
            MediaControllerCompat.setMediaController(context as MusicActivity, mediaController)
        }
        buildTransportControls()
    }

    private fun buildTransportControls() {

    }

}


class MusicControllerCallback : MediaControllerCompat.Callback() {}