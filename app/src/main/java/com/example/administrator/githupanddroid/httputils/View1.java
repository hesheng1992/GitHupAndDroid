package com.example.administrator.githupanddroid.httputils;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016-07-30.
 *
 * 自定义view类，需要先建立个类，继承自view类，重写构造器，重写onmesure（）和ondraw（）方法
 * 然后在xml布局里可以添加这个视图
 */

public class View1 extends View {
    public View1(Context context) {
        super(context);
    }

    public View1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public View1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
