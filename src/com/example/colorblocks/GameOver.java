package com.example.colorblocks;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GameOver extends Activity {
    private long exitTime = 0;
	private TextView text;
	private TextView tt;
	private String bs;
	private int bestscore;
	private SoundPool sp;//声明一个SoundPool   
    private int music;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_over);
		sp= new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);   
        music = sp.load(this, R.raw.try_again, 1); 
        
        SharedPreferences.Editor editor= getSharedPreferences("best_score", MODE_PRIVATE).edit();
        SharedPreferences pref= getSharedPreferences("best_score", MODE_PRIVATE);
        //存入数据
        bs = pref.getString("BEST_SCORE", "0");
        bestscore = Integer.valueOf(bs).intValue();
		if( bestscore < StartGame.score)   bs = StartGame.s;
		editor.putString("BEST_SCORE", bs);
		editor.commit();
		text = (TextView) this.findViewById(R.id.text_score);
		text.setText("Score: " + StartGame.s);
		tt = (TextView) this.findViewById(R.id.text_best_score);
		tt.setText("Best Score: " + bs);
	}
	
	public void try_again(View view){
		SharedPreferences pref1= getSharedPreferences("data", MODE_PRIVATE);
    	VibrationControl.s = pref1.getInt("SOUND", 1);
		if( VibrationControl.s ==1 )  sp.play(music, 1, 1, 0, 0, 1);
		Intent intent = new Intent(this, StartGame.class);
        startActivity(intent);
		GameOver.this.finish();
		overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);  
	}
	
	public void exit_to_home(View view){
		SharedPreferences pref1= getSharedPreferences("data", MODE_PRIVATE);
    	VibrationControl.s = pref1.getInt("SOUND", 1);
		if( VibrationControl.s ==1 )  sp.play(music, 1, 1, 0, 0, 1);
		GameOver.this.finish();
		Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
	        if((System.currentTimeMillis()-exitTime) > 2000){  
	            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();                                
	            exitTime = System.currentTimeMillis();   
	        } else {
	        	android.os.Process.killProcess(android.os.Process.myPid());
	            System.exit(0);
	        }
	        return true;   
	    }
	    return super.onKeyDown(keyCode, event);
	}

}
