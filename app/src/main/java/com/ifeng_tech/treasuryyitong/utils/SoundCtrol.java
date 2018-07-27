package com.ifeng_tech.treasuryyitong.utils;

import android.app.Service;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;

/**
 * Created by zzt on 2018/7/3.
 */

public class SoundCtrol {

    private static Vibrator mVibrator;
    private static int MUTE = 0; //静音
    private static int VIBRATE = 1;//振动
    private static int SOUND = 2;//响玲

    public static void playSound(Context context, MediaPlayer player) {
        //创建震动服务对象
        mVibrator=(Vibrator)context.getSystemService(Service.VIBRATOR_SERVICE);

        AudioManager am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        //ringerMode为手机的系统声音设置的状态值，0位静音，1为震动，2为响铃
        final int ringerMode = am.getRingerMode();
        LogUtils.i("jiba","ringerMode=="+ringerMode);
        if (ringerMode == MUTE) {
            //do nothing
        }else if (ringerMode == VIBRATE) {
            //设置震动周期，数组表示时间：等待+执行，单位是毫秒，下面操作代表:等待100，执行100，等待100，执行1000，
            //后面的数字如果为-1代表不重复，之执行一次，其他代表会重复，0代表从数组的第0个位置开始
            mVibrator.vibrate(new long[]{100,500,100,500},-1);
        } else if (ringerMode == SOUND) {
            //播放声音
            player.start();
        }
    }
}
