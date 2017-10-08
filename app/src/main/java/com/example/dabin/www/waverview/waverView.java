package com.example.dabin.www.waverview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by Dabin on 2017/9/29.
 * <p>
 * content: 做一个动态的VIew
 */

public class waverView extends View {
    private Paint paint;
    private float rx = 0;
    private Thread thread;
    private RectF rectF = new RectF(0, 60, 100, 160);
    int sweepAngle = 0;

    public waverView(Context context) {
        super(context);
    }

    public waverView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        paint = new Paint();
        paint.setTextSize(30);
        canvas.drawText("Dabinai", rx, 30, paint);
        canvas.drawArc(rectF, 0, sweepAngle, true, paint);
        if (thread == null) {
            thread = new Mythead();
            thread.start();
        }
        super.onDraw(canvas);
    }

    @Override
    protected void onDetachedFromWindow() {
        running = false;
        super.onDetachedFromWindow();
    }

    private boolean running = true;
    class Mythead extends Thread {
        Random rand = new Random();

        @Override
        public void run() {
            super.run();
            while (running) {
                rx += 3;
                if (rx > getWidth()) {
                    rx = 0 - paint.measureText("Dabinai"); // measureText()  获取文字的宽度
                }
                sweepAngle++;
                if (sweepAngle > 360) {
                    sweepAngle = 0;
                }
                int r = rand.nextInt(256);
                int g = rand.nextInt(256);
                int b = rand.nextInt(256);
                paint.setARGB(255, r, g, b);
                postInvalidate(); // 在线程中更新绘制的方法
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
