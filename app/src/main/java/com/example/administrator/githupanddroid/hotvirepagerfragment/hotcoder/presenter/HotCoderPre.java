package com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.presenter;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.model.CoderItemsBean;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.model.Users;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.model.UsersInfo;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.model.UsersUp;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.model.UsersUpToal;
import com.example.administrator.githupanddroid.httputils.GitHupApi;
import com.example.administrator.githupanddroid.httputils.OkhttpUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2016-08-02.
 */

public class HotCoderPre{
    private CoderAPI coderAPI;
    private int page;
    private Call<UsersUpToal> callrefrech;
    private Call<UsersUpToal> callLoad;

    private Call<UsersInfo> callinfo;
    private static List<UsersInfo> list=new ArrayList<>();
    private static List<UsersInfo> loadlist=new ArrayList<>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==100){
                Log.e("TAG","handler");
                coderAPI.refrechData(list);
            }else if (msg.what==200){
                coderAPI.removeFootview();
                coderAPI.loadData(loadlist);
            }
        }
    };

    public HotCoderPre(CoderAPI coderAPI){
        this.coderAPI=coderAPI;
    }
    //帅新
    public void refreching(){
        page=1;
        callrefrech= OkhttpUtils.getOkhttpUtils().searchCoder("followers:>0",page,"desc");
        callrefrech.enqueue(reCallback);

    }
    //加载更多
    public void loadMoreData(){
        coderAPI.showFootview();
        callLoad=OkhttpUtils.getOkhttpUtils().searchCoder("followers:>0",page,"desc");
        callLoad.enqueue(callloadmore);

    }
    private Callback<UsersUpToal> reCallback=new Callback<UsersUpToal>() {
        @Override
        public void onResponse(Call<UsersUpToal> call, Response<UsersUpToal> response) {
            UsersUpToal body = response.body();
            List<UsersUp> items = body.getItems();
            list.clear();
            for (UsersUp usersUp:items){
                String login = usersUp.getLogin();
                Log.e("TAG","logian"+login);
                callinfo=OkhttpUtils.getOkhttpUtils().getUserinfom(login);
                Callback<UsersInfo> infoCallback=new Callback<UsersInfo>() {
                    @Override
                    public void onResponse(Call<UsersInfo> call, Response<UsersInfo> response) {
                        Log.e("TAG","进入获取用户信息");
                        coderAPI.stopRefrech();
                        UsersInfo body1 = response.body();
                        list.add(body1);
                        handler.sendEmptyMessage(100);

                    }
                    @Override
                    public void onFailure(Call<UsersInfo> call, Throwable t) {
                        coderAPI.stopRefrech();
                        coderAPI.showeeror(t.getMessage());
                    }
                };
                callinfo.enqueue(infoCallback);
            }
        }
        @Override
        public void onFailure(Call<UsersUpToal> call, Throwable t) {
            coderAPI.stopRefrech();
            coderAPI.showeeror(t.getMessage());
        }
    };

    private Callback<UsersUpToal> callloadmore=new Callback<UsersUpToal>() {
        @Override
        public void onResponse(Call<UsersUpToal> call, Response<UsersUpToal> response) {
            UsersUpToal body = response.body();
            List<UsersUp> items = body.getItems();

            for (UsersUp usersUp:items){
                String login = usersUp.getLogin();
                Log.e("TAG","logian"+login);
                callinfo=OkhttpUtils.getOkhttpUtils().getUserinfom(login);
                Callback<UsersInfo> infoCallback=new Callback<UsersInfo>() {
                    @Override
                    public void onResponse(Call<UsersInfo> call, Response<UsersInfo> response) {
                        Log.e("TAG","进入获取用户信息");
                        coderAPI.stopRefrech();
                        UsersInfo body1 = response.body();
                        loadlist.add(body1);
                        handler.sendEmptyMessage(200);

                    }
                    @Override
                    public void onFailure(Call<UsersInfo> call, Throwable t) {
                        coderAPI.stopRefrech();
                        coderAPI.showeeror(t.getMessage());
                    }
                };
                callinfo.enqueue(infoCallback);
            }
            page++;
        }

        @Override
        public void onFailure(Call<UsersUpToal> call, Throwable t) {

        }
    };



}
