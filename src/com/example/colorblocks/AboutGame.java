package com.example.colorblocks;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class AboutGame extends Activity {
	private SoundPool sp;//ÉùÃ÷Ò»¸öSoundPool   
    private int music;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_about_game);
		sp= new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);   
        music = sp.load(this, R.raw.key_sound, 1);
	}

	public void back_to_main2(View view){
		SharedPreferences pref1= getSharedPreferences("data", MODE_PRIVATE);
    	VibrationControl.s = pref1.getInt("SOUND", 1);
		if( VibrationControl.s ==1 )  sp.play(music, 1, 1, 0, 0, 1);
		AboutGame.this.finish();
		overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);  
	}

}
