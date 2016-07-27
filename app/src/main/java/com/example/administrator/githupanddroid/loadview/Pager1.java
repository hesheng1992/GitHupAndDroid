package com.example.administrator.githupanddroid.loadview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.administrator.githupanddroid.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-07-26.
 */

public class Pager1 extends FrameLayout {
    @Bind(R.id.ivTablet)
    ImageView ivTablet;
    @Bind(R.id.ivDesktop)
    ImageView ivDesktop;


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
        LayoutInflater.from(context).inflate(R.layout.content_pager_0, this, true);
        ButterKnife.bind(this);

    }

    public void showAnimotion(){

            postDelayed(new Runnable() {
                @Override
                public void run() {

                    YoYo.with(Techniques.BounceInLeft).duration(3000).playOn(ivDesktop);
                }
            },0);
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    YoYo.with(Techniques.BounceInRight).duration(3000).playOn(ivTablet);
                }
            },0);
        }

}
