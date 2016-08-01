package com.example.administrator.githupanddroid.hotvirepagerfragment.hotpresenter;

import com.example.administrator.githupanddroid.hotvirepagerfragment.hotmodel.ItemsBean;

import java.util.List;

/**
 * Created by Administrator on 2016-07-28.
 */

public interface AddLoadMore {
    void hintloadMore();
    void showData(List<ItemsBean> list);
    void showError(String meesge);
    void loading();

}
