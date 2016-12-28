package com.example.colorblocks;

/**
 * Created by RainVon on 2015/4/21.
 * 求点到线段的距离
 */
public class PointToLine {
    public static double getPointToLine(int x1, int y1, int x2,int y2, float x0, float y0)
    {
        double space = 0;
        double a, b, c;
        a = distance(x1, y1, x2, y2);// 线段的长度
        b = distance(x1, y1, x0, y0);// (x1,y1)到点的距离
        c = distance(x2, y2, x0, y0);// (x2,y2)到点的距离
        if (c + b == a) {             //点在线段上
            space = 0;
            return space;
        }
        if (a <= 0.000001) {           //不是线段，是一个点
            space = b;
            return space;
        }
        if (c * c >= a * a + b * b) {   //组成直角三角形或钝角三角形，x1,y1为直角或钝角
            space = b;
            return space;
        }
        if (b * b >= a * a + c * c) {   //组成直角三角形或钝角三角形，x2,y2为直角或钝角
            space = c;
            return space;
        }
        double p = (a + b + c) / 2;// 半周长
        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));// 海伦公式求面积
        space = 2 * s / a;// 返回点到线的距离（利用三角形面积公式求高）
        return space;
    }
    public static double distance(int x1, int y1, float x2, float y2) {
        double lineLength = 0;
        lineLength = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2)
                * (y1 - y2));
        return lineLength;
    }
}
