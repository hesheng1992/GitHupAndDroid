package com.example.administrator.githupanddroid.adpater;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.githupanddroid.loadview.Pager1;
import com.example.administrator.githupanddroid.loadview.Pager2;
import com.example.administrator.githupanddroid.loadview.Pager3;

/**
 * Created by Administrator on 2016-07-26.
 */

public class SplashViewPager extends PagerAdapter {
    private View[] views=new View[3];

    public SplashViewPager(Context context){
        views[0]=new Pager1(context);
        views[1]=new Pager2(context);
        views[2]=new Pager3(context);
    }

    public View getView(int position){

        return views[position];
    }


    @Override
    public int getCount() {
        return views.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views[position],0);

        return views[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView(views[position]);

    }
}
