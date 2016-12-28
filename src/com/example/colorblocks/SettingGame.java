package com.example.colorblocks;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class SettingGame extends Activity {
    private TextView tv;
    private TextView tt;
    private TextView tt2;
    private SoundPool sp;//ÉùÃ÷Ò»¸öSoundPool   
    private int music;
    //public static int s = 1;
    //public static int v = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting_game);
		sp= new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);  
        music = sp.load(this, R.raw.key_sound, 1); 
		tt = (TextView) this.findViewById(R.id.sound);
		tv = (TextView) this.findViewById(R.id.vibration);
		tt2 = (TextView) this.findViewById(R.id.music);
		SharedPreferences.Editor editor1= getSharedPreferences("data", MODE_PRIVATE).edit();
        SharedPreferences pref1= getSharedPreferences("data", MODE_PRIVATE);
        VibrationControl.v = pref1.getInt("VIBRATION", 1);
        VibrationControl.s = pref1.getInt("SOUND", 1);
        VibrationControl.m = pref1.getInt("MUSIC", 1);
        if (VibrationControl.v == 1)  { tv.setText("Vibration Off"); tv.setTextColor(0xffff515a); }
        if (VibrationControl.v == -1) { tv.setText("Vibration On");  tv.setTextColor(0xff1ea071); }
        if (VibrationControl.s == 1)  { tt.setText("Sound Off"); tt.setTextColor(0xffff515a); }
        if (VibrationControl.s == -1) { tt.setText("Sound On");  tt.setTextColor(0xff1ea071); }
        if (VibrationControl.m == 1)  { tt2.setText("Music Off"); tt2.setTextColor(0xffff515a); }
        if (VibrationControl.m == -1) { tt2.setText("Music On");  tt2.setTextColor(0xff1ea071); }
	}

	public void back_to_main(View view){
		SharedPreferences pref1= getSharedPreferences("data", MODE_PRIVATE);
    	VibrationControl.s = pref1.getInt("SOUND", 1);
		if( VibrationControl.s ==1 )  sp.play(music, 1, 1, 0, 0, 1);
		SettingGame.this.finish();
		overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);  
	}
	
	public void sound_control(View view){
		SharedPreferences pref1= getSharedPreferences("data", MODE_PRIVATE);
    	VibrationControl.s = pref1.getInt("SOUND", 1);
		if( VibrationControl.s ==1 )  sp.play(music, 1, 1, 0, 0, 1);
		tv = (TextView) this.findViewById(R.id.sound);
		VibrationControl.s = -VibrationControl.s;
		SharedPreferences.Editor editor1= getSharedPreferences("data", MODE_PRIVATE).edit();
		editor1.putInt("SOUND", VibrationControl.s);
		editor1.commit();
        if (VibrationControl.s == 1)  { tv.setText("Sound Off"); tv.setTextColor(0xffff515a); }
        if (VibrationControl.s == -1) { tv.setText("Sound On");  tv.setTextColor(0xff1ea071); }
	}
	
	public void vibration_control(View view){
		SharedPreferences pref1= getSharedPreferences("data", MODE_PRIVATE);
    	VibrationControl.s = pref1.getInt("SOUND", 1);
		if( VibrationControl.s ==1 )  sp.play(music, 1, 1, 0, 0, 1);
		tv = (TextView) this.findViewById(R.id.vibration);
		VibrationControl.v = -VibrationControl.v;
		SharedPreferences.Editor editor1= getSharedPreferences("data", MODE_PRIVATE).edit();
		editor1.putInt("VIBRATION", VibrationControl.v);
		editor1.commit();
        if (VibrationControl.v == 1)  { tv.setText("Vibration Off"); tv.setTextColor(0xffff515a); }
        if (VibrationControl.v == -1) { tv.setText("Vibration On");  tv.setTextColor(0xff1ea071); }
	}
	
	public void music_control(View view){
		SharedPreferences pref1= getSharedPreferences("data", MODE_PRIVATE);
    	VibrationControl.s = pref1.getInt("SOUND", 1);
		if( VibrationControl.s ==1 )  sp.play(music, 1, 1, 0, 0, 1);
		tv = (TextView) this.findViewById(R.id.music);
		VibrationControl.m = -VibrationControl.m;
		SharedPreferences.Editor editor1= getSharedPreferences("data", MODE_PRIVATE).edit();
		editor1.putInt("MUSIC", VibrationControl.m);
		editor1.commit();
        if (VibrationControl.m == 1)  { tv.setText("Music Off"); tv.setTextColor(0xffff515a); }
        if (VibrationControl.m == -1) { tv.setText("Music On");  tv.setTextColor(0xff1ea071); }
	}
}
