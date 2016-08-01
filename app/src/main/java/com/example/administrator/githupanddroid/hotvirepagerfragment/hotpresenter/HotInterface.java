package com.example.administrator.githupanddroid.hotvirepagerfragment.hotpresenter;

import com.example.administrator.githupanddroid.hotvirepagerfragment.hotmodel.ItemsBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-07-28.
 */

public interface HotInterface {
    public void showContentView();
    public void showErrorView(String errorMsg);
    public void showEmptyView();
    //当数据刷新，停止刷新的方法
    public void stopRefresh();
    //当在p层分线程处理完后得到的数据，传递给这个页面的适配器加载数据
    public void getdata(ArrayList<ItemsBean> data);


}
