package com.example.administrator.githupanddroid.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.githupanddroid.R;
import com.example.administrator.githupanddroid.adpater.HotVviewPagAdapter;
import com.example.administrator.githupanddroid.hotvirepagerfragment.LangugeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-07-27.
 */

public class HotReponFragment extends Fragment {
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    private HotVviewPagAdapter adapter;
    private List<Fragment> fragments;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_repo, container, false);
        ButterKnife.bind(this, view);
        initview();
        return view;
    }

    private void initview() {
        fragments=new ArrayList<>();
        fragments.add(new LangugeFragment());
        fragments.add(new LangugeFragment());
        fragments.add(new LangugeFragment());
        fragments.add(new LangugeFragment());
        fragments.add(new LangugeFragment());
        adapter=new HotVviewPagAdapter(getFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
