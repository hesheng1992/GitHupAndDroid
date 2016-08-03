package com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.model;

import java.util.List;

/**
 * Created by Administrator on 2016-08-02.
 */

public class UsersUpToal {
    private int total_count;
    private boolean incomplete_results;
    private List<UsersUp> items;

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

    public List<UsersUp> getItems() {
        return items;
    }

    public void setItems(List<UsersUp> items) {
        this.items = items;
    }
}
