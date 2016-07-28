package com.example.administrator.githupanddroid;

import android.os.AsyncTask;

import com.example.administrator.githupanddroid.hotvirepagerfragment.LangugeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-07-27.
 */

public class TestP {
//这个类是用来做主页面的视图更新和业务逻辑处理

    private LangugeFragment fragment;

    int count=0;
    public TestP(LangugeFragment fragment){
        this.fragment=fragment;
    }
    public void refresh(){
        //同一个异步栈线程只启动一次，不能被多次启动，所以需要匿名对象来启动。
        new Mytask().execute();
    }
    class Mytask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            List<String> data=new ArrayList<String>();

            for (int i=0;i<20;i++){
                data.add("这是第"+(count++));
            }
            fragment.stopRefresh();
            fragment.getdata((ArrayList<String>) data);
            fragment.showContentView();
        }
    }











}
