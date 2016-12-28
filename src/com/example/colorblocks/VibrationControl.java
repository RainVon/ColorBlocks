package com.example.colorblocks;

import android.app.Activity;
import android.app.Service;
import android.os.Vibrator;

public class VibrationControl {

	public static int s = 1;
    public static int v = 1;
    public static int m = 1;
	/** 
     * final Activity activity  �����ø÷�����Activityʵ�� 
     * long milliseconds ���𶯵�ʱ������λ�Ǻ��� 
     * long[] pattern  ���Զ�����ģʽ �����������ֵĺ���������[��ֹʱ������ʱ������ֹʱ������ʱ��������]ʱ���ĵ�λ�Ǻ��� 
     * boolean isRepeat �� �Ƿ񷴸��𶯣������true�������𶯣������false��ֻ��һ�� 
     */  
      
     public static void Vibrate(final Activity activity, long milliseconds) {   
            Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);   
            vib.vibrate(milliseconds);   
     }   
     public static void Vibrate(final Activity activity, long[] pattern,boolean isRepeat) {   
            Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);   
            vib.vibrate(pattern, isRepeat ? 1 : -1);   
     }   
}