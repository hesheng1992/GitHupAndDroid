package com.example.administrator.githupanddroid.adpater;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.githupanddroid.hotvirepagerfragment.hotmodel.Lugague;
import com.example.administrator.githupanddroid.hotvirepagerfragment.viewfragment.LangugeFragment;

import java.util.List;

/**
 * Created by Administrator on 2016-07-27.
 */

public class HotVviewPagAdapter extends FragmentPagerAdapter {
    private List<Lugague> list;

    public HotVviewPagAdapter(FragmentManager fm, Context context) {
        super(fm);
        //得到本地的json数据用gson解析返回的实体集合
        list=Lugague.getInstanse(context);
    }

    @Override
    public Fragment getItem(int position) {
        return LangugeFragment.getInstance(list.get(position));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getName();
    }
}
