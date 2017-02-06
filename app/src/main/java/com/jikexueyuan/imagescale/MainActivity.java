package com.jikexueyuan.imagescale;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private float tempX,tempY,touch1X,touch1Y,touch2X,touch2Y,percentX,percentY,saveX,saveY;
    ImageView ivScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        绑定对应的事件
        ivScale = (ImageView) findViewById(R.id.ivScale);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        当两根手指触控时触发
        if (MotionEventCompat.getPointerCount(event) == 2){
//            分别获取两根手指的x,y轴坐标
            touch1X = MotionEventCompat.getX(event,0);
            touch1Y = MotionEventCompat.getY(event,0);
            touch2X = MotionEventCompat.getX(event,1);
            touch2Y = MotionEventCompat.getY(event,1);
        }
//        根据不同触摸事件响应
        switch (event.getActionMasked()){
            case 0:
//                x = Math.abs(touch2X - touch1X);
//                y = Math.abs(touch2Y - touch1Y);

                break;
//            当手指滑动时
            case 2:
                /*通过计算滑动占点击时对应xy坐标比例进行缩放但效果不是很好体验性比较差
                tempX = Math.abs(touch2X - touch1X);
                tempY = Math.abs(touch2Y - touch1Y);
                if (tempX <=100){
                    tempX = 100;
                }else if (tempX >=500){
                    tempX = 500;
                }
                if (tempY <= 100){
                    tempY = 100;
                }else if (tempY >= 500){
                    tempY = 500;
                }
                if(x == 0 || y == 0) {
                    ivScale.setScaleX(tempX / 300);
                    ivScale.setScaleY(tempY / 300);
                }else{
                    ivScale.setScaleX(tempX / x);
                    ivScale.setScaleY(tempY / y);
                }*/

//                获取两指x，y轴坐标差
                tempX = touch2X - touch1X;
                tempY = touch2Y - touch1Y;

//                对缩放比例进行一些限制避免过度缩放
                if (saveX == 0 || saveY == 0){
                    percentX = 1 +(tempX / 500);
                    percentY = 1 + (tempY / 500);
                } else{
                    percentX = saveX +(tempX / 1000);
                    percentY = saveY + (tempY / 1000);
                }

                if (percentX >= 1.6){
                    percentX = (float) 1.6;
                }else if (percentX <= 0.2){
                    percentX = (float) 0.2;
                }

                if (percentY >= 1.6){
                    percentY = (float) 1.6;
                }else if (percentY <= 0.2){
                    percentY = (float) 0.2;
                }

//                按比例缩放
                ivScale.setScaleX(percentX);
                ivScale.setScaleY(percentY);
                break;
//            当手指离开时记录上次的缩放比例
            case 1:
                saveX = percentX;
                saveY = percentY;
        }
        return super.onTouchEvent(event);
    }

}
