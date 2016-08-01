package com.example.administrator.githupanddroid.hotvirepagerfragment.hotpresenter;

import android.os.AsyncTask;
import android.util.Log;

import com.example.administrator.githupanddroid.hotvirepagerfragment.hotmodel.ItemsBean;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotmodel.Lugague;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotmodel.RepoResult;
import com.example.administrator.githupanddroid.hotvirepagerfragment.viewfragment.LangugeFragment;
import com.example.administrator.githupanddroid.httputils.OkhttpUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016-07-27.
 */

public class TestP {
//这个类是用来做主页面的视图更新和业务逻辑处理

    //初始化上拉加载和下拉刷新的公共接口子类
    private PublicInterFace interFace;
//    private HotInterface hotInterface;
    private int page=1;
    private Lugague lugague;
    private Call<RepoResult> repoResultCall;

    public TestP(PublicInterFace interFace,Lugague lugague){
      this.interFace=interFace;
        this.lugague=lugague;
    }
    //提供方法，以便在ui界面下拉时调用这个方法，在这个界面处理加载数据的操作
    public void refresh() {
//        interFace.hintloadMore();
        interFace.showContentView();
        repoResultCall = OkhttpUtils.getOkhttpUtils().searchRepos("lugague:" + lugague.getPath(), page);
        repoResultCall.enqueue(repoCallback);
    }

    public void begianloadMore(){
        interFace.loading();
        repoResultCall=OkhttpUtils.getOkhttpUtils().searchRepos("lugague:" + lugague.getPath(), page);
        repoResultCall.enqueue(loadmorecall);

    }

    //下拉刷新联网获取最新数据
    private Callback<RepoResult> repoCallback=new Callback<RepoResult>() {
        @Override
        public void onResponse(Call<RepoResult> call, Response<RepoResult> response) {
            interFace.stopRefresh();
            RepoResult body = response.body();
            Log.e("TAG","body"+body.toString());
            if (body==null){
                interFace.showErrorView("结果为空");
                return;
            }
            if(body.getTotal_count()<=0){
                interFace.showEmptyView();
                interFace.getdata(null);
                return;
            }
            List<ItemsBean> items = body.getItems();
            interFace.getdata((ArrayList<ItemsBean>) items);
            page=2;

        }

        @Override
        public void onFailure(Call<RepoResult> call, Throwable t) {
            // 视图停止刷新
            interFace.stopRefresh();
            interFace.showError("repoCallback onFailure" + t.getMessage());

        }

    };


    //加载更多联网获取数据
    private Callback<RepoResult> loadmorecall=new Callback<RepoResult>() {
        @Override
        public void onResponse(Call<RepoResult> call, Response<RepoResult> response) {
            interFace.hintloadMore();
            RepoResult body = response.body();
            if (body==null){
                interFace.showErrorView("结果为空");
            }
            List<ItemsBean> items = body.getItems();
            interFace.showData(items);
            page++;

        }

        @Override
        public void onFailure(Call<RepoResult> call, Throwable t) {
            interFace.hintloadMore();
            interFace.showErrorView("repoCallback onFailure" + t.getMessage());
        }
    };


}
