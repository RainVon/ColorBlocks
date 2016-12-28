package com.example.colorblocks;

/**
 * Created by RainVon on 2015/4/21.
 * ��㵽�߶εľ���
 */
public class PointToLine {
    public static double getPointToLine(int x1, int y1, int x2,int y2, float x0, float y0)
    {
        double space = 0;
        double a, b, c;
        a = distance(x1, y1, x2, y2);// �߶εĳ���
        b = distance(x1, y1, x0, y0);// (x1,y1)����ľ���
        c = distance(x2, y2, x0, y0);// (x2,y2)����ľ���
        if (c + b == a) {             //�����߶���
            space = 0;
            return space;
        }
        if (a <= 0.000001) {           //�����߶Σ���һ����
            space = b;
            return space;
        }
        if (c * c >= a * a + b * b) {   //���ֱ�������λ�۽������Σ�x1,y1Ϊֱ�ǻ�۽�
            space = b;
            return space;
        }
        if (b * b >= a * a + c * c) {   //���ֱ�������λ�۽������Σ�x2,y2Ϊֱ�ǻ�۽�
            space = c;
            return space;
        }
        double p = (a + b + c) / 2;// ���ܳ�
        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));// ���׹�ʽ�����
        space = 2 * s / a;// ���ص㵽�ߵľ��루���������������ʽ��ߣ�
        return space;
    }
    public static double distance(int x1, int y1, float x2, float y2) {
        double lineLength = 0;
        lineLength = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2)
                * (y1 - y2));
        return lineLength;
    }
}
