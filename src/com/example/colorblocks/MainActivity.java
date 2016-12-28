package com.example.colorblocks;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

public class MainActivity extends Activity {
	private SoundPool sp;
    private int music;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Constant.displayWidth = displayMetrics.widthPixels;
        Constant.displayHeight = displayMetrics.heightPixels;
        sp= new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);   
        music = sp.load(this, R.raw.key_sound, 1);
    }


    public void start (View view)
    {
    	SharedPreferences pref1= getSharedPreferences("data", MODE_PRIVATE);
    	VibrationControl.s = pref1.getInt("SOUND", 1);
    	if( VibrationControl.s ==1 )  sp.play(music, 1, 1, 0, 0, 1);
    	Intent intent = new Intent(this, StartGame.class);
        startActivity(intent);
    }

    public void setting(View view)
    {
    	SharedPreferences pref1= getSharedPreferences("data", MODE_PRIVATE);
    	VibrationControl.s = pref1.getInt("SOUND", 1);
    	if( VibrationControl.s ==1 )  sp.play(music, 1, 1, 0, 0, 1);
    	Intent intent = new Intent(this, SettingGame.class);
        startActivity(intent);
        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }

    public void about(View view)
    {
    	SharedPreferences pref1= getSharedPreferences("data", MODE_PRIVATE);
    	VibrationControl.s = pref1.getInt("SOUND", 1);
    	if( VibrationControl.s ==1 )  sp.play(music, 1, 1, 0, 0, 1);
    	Intent intent = new Intent (this, AboutGame.class);
        startActivity(intent);
        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }
    
    
}
