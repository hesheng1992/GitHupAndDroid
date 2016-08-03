package com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.githupanddroid.R;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.model.CoderItemsBean;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.model.UsersInfo;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.presenter.CoderAPI;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.presenter.HotCoderPre;
import com.example.administrator.githupanddroid.hotvirepagerfragment.viewfragment.FooterView;
import com.mugen.Mugen;
import com.mugen.MugenCallbacks;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by Administrator on 2016-08-02.
 */

public class HotCoderFragment extends Fragment implements CoderAPI{
    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;
    @Bind(R.id.ptr)
    PtrClassicFrameLayout ptr;

    private RecyAdapter adapter;
    private List<UsersInfo> list;
    private FooterView footerView;
    private HotCoderPre pre;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_coder,container,false);
        ButterKnife.bind(this,view);
        initdata();
        return view;
    }

    private void initdata() {
        footerView=new FooterView(getContext());
        pre=new HotCoderPre(this);
        list=new ArrayList<>();
        adapter=new RecyAdapter(list,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        // 使用当前对象做为key，来记录上一次的刷新时间,如果两次下拉太近，将不会触发新刷新
        ptr.setLastUpdateTimeRelateObject(this);
        // 关闭header所用时长
        ptr.setDurationToCloseHeader(2000);
        ptr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pre.refreching();
            }
        });

        //下拉刷新的设置封装类
        StoreHouseHeader houseHeader=new StoreHouseHeader(getContext());
        //修改下拉刷新的样式
        ptr.setHeaderView(houseHeader);
        ptr.addPtrUIHandler(houseHeader);
        ptr.setBackgroundResource(R.color.colorRefresh);
        houseHeader.initWithString("HOT CODER ");
        houseHeader.setPadding(0,50,0,50);

        //上拉加载
        Mugen.with(recyclerView, new MugenCallbacks() {
            @Override
            public void onLoadMore() {


            }

            @Override
            public boolean isLoading() {

                return false;
            }

            @Override
            public boolean hasLoadedAllItems() {
                return false;
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });


    }


    @Override
    public void loadData(List<UsersInfo> list2) {
        if (list2!=null){
            adapter=new RecyAdapter(list2,getContext());
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void showFootview() {

    }

    @Override
    public void removeFootview() {

    }


    @Override
    public void refrechData(List<UsersInfo> list1) {
        if (list1!=null){
            adapter=new RecyAdapter(list1,getContext());
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void stopRefrech() {
        ptr.refreshComplete();
    }

    @Override
    public void showeeror(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

    }
}
