package com.example.colorblocks;

/**
 * Created by RainVon on 2015/4/22.
 * 检测圆与矩形是否发生碰撞
 */
public class CheckCrash {
	public StartGame sg;
	public static int ChenckCrashOne(float xc, float yc,int radius, int xr, int yr, int zr, int sr){
		double check1, check2, check3, check4;
		check1 = PointToLine.getPointToLine(xr, yr, xr, sr, xc, yc) - radius;
		check2 = PointToLine.getPointToLine(xr, yr, zr, yr, xc, yc) - radius;
		check3 = PointToLine.getPointToLine(zr, yr, zr, sr, xc, yc) - radius;
		check4 = PointToLine.getPointToLine(xr, sr, zr, sr, xc, yc) - radius;
		
		if( (check1 > 0) && (check2 > 0) && (check3 > 0) && (check4 > 0) )
		return 1;           //1:圆与矩形未碰撞
		else return 0;      //0:圆与矩形碰撞
	
	}
	
	public static int CheckCrash(float xc0, float yc0, int radius, int cc0, int xr1, int yr1, int zr1, int sr1, int cc1, int xr2, int yr2, int zr2, int sr2, int cc2, int xr3, int yr3, int zr3, int sr3,int cc3, int xr4, int yr4, int zr4, int sr4, int cc4){
		int c1, c2, c3, c4;
		if ( (cc0 == 0xff030303) || (cc0 == 0xff404040) )   return 1;
		else{
		
			if (cc0 == cc1)  c1 = 1;
		else c1 = CheckCrash.ChenckCrashOne(xc0, yc0, radius, xr1, yr1, zr1, sr1);
		
		    if( cc0 == cc2)  c2 = 1;
		else c2 = CheckCrash.ChenckCrashOne(xc0, yc0, radius, xr2, yr2, zr2, sr2);
		
		    if( cc0 == cc3)  c3 = 1;
		else c3 = CheckCrash.ChenckCrashOne(xc0, yc0, radius, xr3, yr3, zr3, sr3);
		
		    if( cc0 == cc4)  c4 = 1;
		else c4 = CheckCrash.ChenckCrashOne(xc0, yc0, radius, xr4, yr4, zr4, sr4);
		//c5 = CheckCrash.ChenckCrashOne(xc0, yc0, xr5, yr5, zr5, sr5);
		
		if( (c1 > 0) && (c2 > 0) && (c3 > 0) && (c4 > 0) )
		return 1;          //1:圆与5个矩形都未碰撞
		else return 0;     //0:发生碰撞
		}
	
	}
	
	
}
