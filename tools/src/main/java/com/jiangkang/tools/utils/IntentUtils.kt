package com.jiangkang.tools.utils

import android.content.Context
import android.content.Intent
import android.provider.AlarmClock
import android.provider.MediaStore

/**
 * Created by jiangkang on 2018/2/17.
 * description：常用Intent 工具类
 */

object IntentUtils {


    /*
    * 拍照Intent
    * */
    val cameraIntent: Intent
        get() {
            return Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        }

    /*
    * 拍视频Intent
    * */
    val takeVideoIntent: Intent
        get() {
            return Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        }


    /*
    * 创建闹铃
    * 需要<uses-permission android:name="com.android.alarm.permission.SET_ALARM" />
    * */
    fun createAlarm(context: Context, msg: String, hour: Int, min: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM)
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, msg)
        intent.putExtra(AlarmClock.EXTRA_HOUR, hour)
        intent.putExtra(AlarmClock.EXTRA_MINUTES, min)
        intent.resolveActivity(context.packageManager)?.let {
            context.startActivity(intent)
        }
    }


    /*
    * 创建定时器
    * 需要<uses-permission android:name="com.android.alarm.permission.SET_ALARM" />
    * */
    fun startTimer(context:Context, msg:String, seconds:Int){
        val intent = Intent(AlarmClock.ACTION_SET_TIMER)
        intent.putExtra(AlarmClock.EXTRA_MESSAGE,msg)
        intent.putExtra(AlarmClock.EXTRA_LENGTH,seconds)
        intent.putExtra(AlarmClock.EXTRA_SKIP_UI,true)//设置定时器时跳过UI
        intent.resolveActivity(context.packageManager)?.let {
            context.startActivity(intent)
        }
    }





}