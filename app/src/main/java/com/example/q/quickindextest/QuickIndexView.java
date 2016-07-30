package com.example.q.quickindextest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by YQ on 2016/7/30.
 */
public class QuickIndexView extends View {
    private Paint mPaint;
    private int mHeight;
    private int cellWidth;
    private int touchIndex=-1;
    private static final String[] letters = new String[]{
            "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"};
    private float cellHeight;

    public QuickIndexView(Context context) {
        this(context, null);
    }

    public QuickIndexView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickIndexView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(20);
        mPaint.setAntiAlias(true);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
    }


    //注意精度问题
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //计算坐标
        for (int i=0;i<letters.length;i++){
            String text=letters[i];
            int x= (int) (cellWidth/2.0f-mPaint.measureText(text)/2.0f);//画笔可以测量text的长度
            Rect bounds=new Rect();
            mPaint.getTextBounds(text,0,text.length(),bounds);//填充bounds
            int textHeight=bounds.height();
            int y= (int) (cellHeight/2.0f+textHeight/2.0f+i*cellHeight);
            canvas.drawText(text,x,y,mPaint);//x，y分别为字母左下角距离单个cell的左上角的距离
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //最好在这里获得单元格的宽和高
        mHeight=getMeasuredHeight();
        cellWidth=getMeasuredWidth();
        cellHeight=mHeight*1.0f/letters.length;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index=-1;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                index= (int) (event.getY()/cellHeight);
                if (index>=0&&index<letters.length){
                    if (index!=touchIndex){//判断是否跟上一次触摸一样
                        touchIndex=index;
                        MyToast.showToast(getContext(),letters[index]);
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                index= (int) (event.getY()/cellHeight);
                if (index>=0&&index<letters.length){
                    if (index!=touchIndex){
                        touchIndex=index;
                        MyToast.showToast(getContext(),letters[index]);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                touchIndex=-1;
                break;
        }
        return true;//返回tru之后才可以接受剩下的事件
    }
}
