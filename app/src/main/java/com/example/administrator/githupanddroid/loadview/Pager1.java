package com.example.administrator.githupanddroid.loadview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.example.administrator.githupanddroid.R;

/**
 * Created by Administrator on 2016-07-26.
 */

public class Pager1 extends FrameLayout {
    public Pager1(Context context) {
        super(context);
        init(context);
    }

    public Pager1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Pager1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.content_pager_0,this,true);
    }
}
