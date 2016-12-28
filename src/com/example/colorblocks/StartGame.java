package com.example.colorblocks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StartGame extends Activity {
	private TextView tv;
	//适应各尺寸屏幕变量值
	private SoundPool sp;//声明一个SoundPool   
    private int music;
	public int dpw50 = (int) (50 * Constant.displayWidth / 480);
	public int dp15 = (int) (15 * Constant.displayWidth / 480);
	public int dpw100 = (int) (100 * Constant.displayWidth / 480);
	public int dph50 = (int) (50 * Constant.displayHeight / 800);
	public int dph100 = (int) (100 * Constant.displayHeight / 800);
	int t = 0;    //定时器计数值
	int tt = 0;   //定时器计数值
	int cc ;      //检测碰撞常数
	int speed = (int) (5 * Constant.displayHeight / 800);//速度控制常数
	int c;        //小球上次的颜色
	public static int score;
	public static String s;
	public int [] colors = {0xffff5563, 0xff1ea071, 0xfff5742c, Color.WHITE, 0xff404040};
	public int s1 = 0, s2 = 0, s3 = 0, s4 = 0;
 	public int y1 = (int) (- (dph50 + Math.random() * dph100) + s1);
 	public int y2 = (int) (- (dph50 + Math.random() * dph100) + s2);
 	public int y3 = (int) (- (dph50 + Math.random() * dph100) + s3);
 	public int y4 = (int) (- (dph50 + Math.random() * dph100) + s4);
 	//public int y5 = (int) (- (50 + Math.random() * 300) + s5);
    Handler handler;
	Runnable runnable;
	
	
	@Override
	    protected void onCreate(Bundle savedInstanceState) {
		
		//dm = new DisplayMetrics();      //获取屏幕尺寸
		//getWindowManager().getDefaultDisplay().getMetrics(dm);    
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_start_game);
	    tv = (TextView) this.findViewById(R.id.tvscore);
	    sp = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);   
        music = sp.load(this, R.raw.game_over, 1);
        SharedPreferences pref1= getSharedPreferences("data", MODE_PRIVATE);
        VibrationControl.m = pref1.getInt("MUSIC", 1);
       // if( VibrationControl.m == 1 )   mp.start(); 

	        LinearLayout root = (LinearLayout) findViewById(R.id.root);
	        //创建一个自己继承于View的对象
	        final DrawView drawView = new DrawView(this);
	        drawView.setOnTouchListener(new View.OnTouchListener() {
	            public boolean onTouch(View v, MotionEvent event) {
	               
	                switch(event.getAction()) {  
	                case MotionEvent.ACTION_DOWN:     
	                	//drawView.invalidate();    
	                    break;    
	                case MotionEvent.ACTION_UP:     
	                	//drawView.currentX = event.getX();
	 	                //drawView.currentY = event.getY() - 120;
	                	//drawView.invalidate(); 
	                    break;    
	                case MotionEvent.ACTION_MOVE:    
	                	drawView.currentX = event.getX();
	 	                drawView.currentY = event.getY() - (int) (120 * Constant.displayHeight / 800);;
	                	drawView.invalidate();    
	                    break;    
	            }  
	              
	            return true; 
	            }
	        });
	        root.addView(drawView);
	        
	        //定时器
	        handler = new Handler( ); 
	        runnable = new Runnable( ) {
	        public void run ( ) {
	        	t++;
	        	tt++;
	        	if( tt >= 1000) tt = 0;
	        	score = t / 100;
	        	s = String.valueOf(score); 
	        	tv.setText(s);
	        	if( t >= 1000) speed = (int) (6 * Constant.displayHeight / 800);
	        	if( t >= 2000) speed = (int) (8 * Constant.displayHeight / 800);
	        	if( t >= 3000) speed = (int) (10 * Constant.displayHeight / 800);
	        	if( t >= 4000) speed = (int) (13 * Constant.displayHeight / 800);
	        	if( t >= 5000) speed = (int) (15 * Constant.displayHeight / 800);
	        	if( t >= 100) { y1 += speed; s1 +=speed; }
	        	if( t >= 200) { y2 += speed; s2 +=speed; }
	        	if( t >= 300) { y3 += speed; s3 +=speed; }
	        	if( t >= 400) { y4 += speed; s4 +=speed; }
	        	//if( t >= 500) { y5 += speed; s5 +=speed; }
	        	drawView.invalidate();
	        handler.postDelayed(this,10); 
	        //postDelayed(this,1000)方法安排一个Runnable对象到主线程队列中
	        }
	        };

	        handler.postDelayed(runnable,50);    
	    }

	    public class DrawView extends View {
	    	
	    	public int width = Constant.displayWidth;  // 屏幕宽度（像素）
	        public int height = Constant.displayHeight; //屏幕高度
	    	public float currentX = width / 2 - dp15;
	        public float currentY = height / 3 * 2;
	        public int x1 = (int) (Math.random() * width - dpw100);      //矩形障碍物1初始坐标
	        public int x2 = (int) (Math.random() * width - dpw100);
	        public int x3 = (int) (Math.random() * width - dpw100);
	        public int x4 = (int) (Math.random() * width - dpw100);
	        //public int x5 = (int) (Math.random() * width - 100);
	        public int z1 = (int) (dpw50 + Math.random() * dpw100 + x1);
	        public int z2 = (int) (dpw50 + Math.random() * dpw100 + x2);
	        public int z3 = (int) (dpw50 + Math.random() * dpw100 + x3);
	        public int z4 = (int) (dpw50 + Math.random() * dpw100 + x4);
	        //public int z5 = (int) (50 + Math.random() * 100 + x5);
	        public int color0 = Color.WHITE;
	        public int color1 = colors[(int) (Math.random() *3)];
	        public int color2 = colors[(int) (Math.random() *3)];
	        public int color3 = colors[(int) (Math.random() *3)];
	        public int color4 = colors[(int) (Math.random() *3)];
	        //public int color5 = colors[(int) (Math.random() *3)];

	        //必须重写一个构造方法
	        public DrawView(Context context) {
	            super(context);
	        }

	        //重写onDraw方法通过Canvas绘画
	        @Override

	        protected void onDraw(Canvas canvas) {
	            super.onDraw(canvas);

	            //创建画笔
	            Paint paint = new Paint();
	            Paint paint1 = new Paint();
	            Paint paint2 = new Paint();
	            Paint paint3 = new Paint();
	            Paint paint4 = new Paint();
	            //Paint paint5 = new Paint();
	            
	            paint.setColor(color0);
	            paint1.setColor(color1);
	            paint2.setColor(color2);
	            paint3.setColor(color3);
	            paint4.setColor(color4);
	            paint.setAntiAlias(true);
	            //paint5.setColor(color5);

	            //小球变色
	            if( tt == 410 )  {c = color0;  color0 = 0xff030303;}
	            if( tt == 415 )  color0 = c;
	            if( tt == 425 )  color0 = 0xff030303;
	            if( tt == 430 )  color0 = c;
	            if( tt == 440 )  color0 = 0xff030303;
	            if( tt == 445 )  color0 = c;
	            if( tt == 455 )  color0 = 0xff030303;
	            if( tt == 460 )  color0 = c;
	            if( tt == 470 )  color0 = 0xff030303;
	            if( tt == 475 )  color0 = c;
	            if( tt == 485 )  color0 = 0xff030303;
	            if( tt == 490 )  color0 = c;
	            if( tt == 500 )  color0 = colors[(int) (Math.random() *5)];
	            
	            //矩形超过边界则重画
	            if( y1 > (height - dph50)) { s1 = 0; y1 = (int) (- (dph50 + Math.random() * dph100) + s1);
	            x1 = (int) (Math.random() * width - dpw100); z1 = (int) (dpw50 + Math.random() * dpw100 + x1); color1 = colors[(int) (Math.random() *3)];}
	            
	            if( y2 > (height - dph50)) { s2 = 0; y2 = (int) (- (dph50 + Math.random() * dph100) + s2);
	            x2 = (int) (Math.random() * width - dpw100); z2 = (int) (dpw50 + Math.random() * dpw100 + x2); color2 = colors[(int) (Math.random() *3)];}
	            
	            if( y3 > (height - dph50)) { s3 = 0; y3 = (int) (- (dph50 + Math.random() * dph100) + s3);
	            x3 = (int) (Math.random() * width - dpw100); z3 = (int) (dpw50 + Math.random() * dpw100 + x3); color3 = colors[(int) (Math.random() *3)];}
	            
	            if( y4 > (height - dph50)) { s4 = 0; y4 = (int) (- (dph50 + Math.random() * dph100) + s4);
	            x4 = (int) (Math.random() * width - dpw100); z4 = (int) (dpw50 + Math.random() * dpw100 + x4); color4 = colors[(int) (Math.random() *3)];}
	            
	            //if( y5 > (height - 50)) { s5 = 0; y5 = (int) (- (50 + Math.random() * 100) + s5);
	            //x5 = (int) (Math.random() * width - 100); z5 = (int) (50 + Math.random() * 100 + x5); color5 = colors[(int) (Math.random() *3)];}
	            
	            //检测碰撞
	            cc = CheckCrash.CheckCrash(currentX, currentY, dp15, color0, x1, y1, z1, s1, color1, x2, y2, z2, s2, color2, x3, y3, z3, s3, color3, x4, y4, z4, s4, color4);
	            if (cc == 0)   { handler.removeCallbacks(runnable);   gameover();}
	            
	          //绘制一个小圆
	            canvas.drawCircle(currentX, currentY, dp15, paint);
	            //s1 = s1 + y1;     s2 = s2 + y2;
	            //s3 = s3 + y3;     s4 = s4 + y4;    s5 = s5 + y5;
	            //依次画矩形
	            if( t >= 100) canvas.drawRect(x1, y1, z1, s1, paint1);
	            if( t >= 200) canvas.drawRect(x2, y2, z2, s2, paint2);
	            if( t >= 300) canvas.drawRect(x3, y3, z3, s3, paint3);
	            if( t >= 400) canvas.drawRect(x4, y4, z4, s4, paint4);
	            //if( t >= 500) canvas.drawRect(x5, y5, z5, s5, paint5);
	       }
	    }
	    
	    private void delay(int ms){  
	        try {  
	            Thread.currentThread();  
	            Thread.sleep(ms);  
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        }   
	     }    
	    
	    public void gameover()
	    {
	    	SharedPreferences pref1= getSharedPreferences("data", MODE_PRIVATE);
	        VibrationControl.v = pref1.getInt("VIBRATION", 1);
	        VibrationControl.s = pref1.getInt("SOUND", 1);
	    	if( VibrationControl.s == 1 )  sp.play(music, 1, 1, 0, 0, 1);
	    	if( VibrationControl.v == 1 )  VibrationControl.Vibrate(StartGame.this, 300);
	    	delay(1500);
	    	Intent intent = new Intent(this, GameOver.class);
            startActivity(intent);
            StartGame.this.finish();
	    }
	    
	    @Override   // 使返回键无效
	    public boolean onKeyDown(int keyCode, KeyEvent event) {  
	    // TODO Auto-generated method stub  
	    if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){  
	        //需要处理  
	    }  
	        return false;  
	    }  
	    
	}

