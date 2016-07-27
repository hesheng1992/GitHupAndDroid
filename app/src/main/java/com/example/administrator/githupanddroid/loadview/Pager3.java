package com.example.administrator.githupanddroid.loadview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.administrator.githupanddroid.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-07-26.
 */

public class Pager3 extends FrameLayout {
    @Bind(R.id.ivBubble1)
    ImageView ivBubble1;
    @Bind(R.id.ivBubble2)
    ImageView ivBubble2;
    @Bind(R.id.ivBubble3)
    ImageView ivBubble3;

    public Pager3(Context context) {
        super(context);
        init(context);
    }

    public Pager3(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Pager3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.content_pager_2, this, true);
        ButterKnife.bind(this);


    }
    public void showAnimotion(){

        postDelayed(new Runnable() {
            @Override
            public void run() {

                YoYo.with(Techniques.RotateInDownRight).duration(3000).playOn(ivBubble1);
            }
        },50);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.RotateInDownRight).duration(3000).playOn(ivBubble2);
            }
        },100);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.RotateInDownRight).duration(3000).playOn(ivBubble3);

            }
        },150);
    }



}
