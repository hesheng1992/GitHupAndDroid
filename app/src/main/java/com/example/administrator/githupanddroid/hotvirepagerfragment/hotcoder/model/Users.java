package com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.model;

import com.example.administrator.githupanddroid.hotvirepagerfragment.hotmodel.ItemsBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016-08-02.
 */

public class Users implements Serializable{
    private int total_count;
    private boolean incomplete_results;

    private List<CoderItemsBean> items;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<CoderItemsBean> getItems() {
        return items;
    }

    public void setItems(List<CoderItemsBean> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Users{" +
                "total_count=" + total_count +
                ", incomplete_results=" + incomplete_results +
                ", items=" + items +
                '}';
    }
}
