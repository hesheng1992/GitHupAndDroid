package com.example.administrator.githupanddroid.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016-07-27.
 */

public class HotVviewPagAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public HotVviewPagAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.list=list;
    }
    private String []str={
      "java","javaScipt","HTML","GO","Sfit"
    };


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }
}
