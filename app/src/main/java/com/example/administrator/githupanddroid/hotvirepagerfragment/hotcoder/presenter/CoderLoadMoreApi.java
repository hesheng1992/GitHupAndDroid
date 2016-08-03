package com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.presenter;

import com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.model.CoderItemsBean;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.model.UsersInfo;

import java.util.List;

/**
 * Created by Administrator on 2016-08-02.
 */

public interface CoderLoadMoreApi {
    void loadData(List<UsersInfo> list);
    void showFootview();
    void removeFootview();
}
