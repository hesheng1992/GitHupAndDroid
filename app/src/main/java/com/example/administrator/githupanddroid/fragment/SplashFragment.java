package com.example.administrator.githupanddroid.fragment;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.administrator.githupanddroid.MainActivity;
import com.example.administrator.githupanddroid.R;
import com.example.administrator.githupanddroid.adpater.SplashViewPager;
import com.example.administrator.githupanddroid.loadview.Pager1;
import com.example.administrator.githupanddroid.loadview.Pager2;
import com.example.administrator.githupanddroid.loadview.Pager3;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Administrator on 2016-07-26.
 */

public class SplashFragment extends Fragment {


    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.indicator)
    CircleIndicator indicator;
    @Bind(R.id.ivPhoneBlank)
    ImageView ivPhoneBlank;
    @Bind(R.id.ivPhoneFont)
    ImageView ivPhoneFont;
    @Bind(R.id.layoutPhone)
    FrameLayout layoutPhone;
    @Bind(R.id.content)
    FrameLayout content;
    private SplashViewPager adapter;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_pager, container, false);

        ButterKnife.bind(this, view);
        adapter=new SplashViewPager(getContext());
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(PagerColorListener);
        viewPager.addOnPageChangeListener(PhoneViewListener);

        return view;
    }


//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ButterKnife.unbind(this);
//    }

    @OnClick({R.id.ivPhoneBlank, R.id.ivPhoneFont, R.id.layoutPhone, R.id.content})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivPhoneBlank:
                break;
            case R.id.ivPhoneFont:
                break;
            case R.id.layoutPhone:
                break;
            case R.id.content:
                break;
        }
    }

    //切换页面颜色的改变
    private ViewPager.OnPageChangeListener PagerColorListener=new ViewPager.OnPageChangeListener() {
        //颜色渐变，从一个颜色渐变到另一个颜色
        final ArgbEvaluator argbEvaluator=new ArgbEvaluator();
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (position==0){
                int evaluate = (Integer) argbEvaluator.evaluate(positionOffset, getResources().getColor(R.color.colorGreen), getResources().getColor(R.color.colorRed));
                content.setBackgroundColor(evaluate);
            }else if (position==1){
                int evaluate = (Integer) argbEvaluator.evaluate(positionOffset, getResources().getColor(R.color.colorRed), getResources().getColor(R.color.colorYellow));
                content.setBackgroundColor(evaluate);
            }
        }

        @Override
        public void onPageSelected(int position) {
//            if (position==2){
//                Intent intent=new Intent(getActivity(), MainActivity.class);
//                getActivity().startActivity(intent);
//                getActivity().finish();
            if (position==0){
                Pager1 view = (Pager1) adapter.getView(position);
                view.showAnimotion();
            }else if (position==2){
                Pager3 view = (Pager3) adapter.getView(position);
                view.showAnimotion();
            }



        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    //切换手机视图
    private ViewPager.OnPageChangeListener PhoneViewListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if(position==1){
                layoutPhone.setTranslationX(-positionOffsetPixels);
            }
            if (position==0){
//                ivPhoneFont.setScaleX();
//                ivPhoneFont.setScaleY();
                ivPhoneFont.setAlpha(positionOffset);
                float scale=0.3f+positionOffset*0.7f;
                float scaley=0.3f+positionOffset*0.7f;
                layoutPhone.setScaleY(scale);
                layoutPhone.setScaleY(scaley);

                //手机平移
                layoutPhone.setTranslationX((positionOffset-1)*200);

            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
