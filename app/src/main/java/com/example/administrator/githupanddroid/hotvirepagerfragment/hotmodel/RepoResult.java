package com.example.administrator.githupanddroid.hotvirepagerfragment.hotmodel;

import java.util.List;

/**
 * Created by Administrator on 2016-08-01.
 */

public class RepoResult {


    /**
     * total_count : 2074901
     * incomplete_results : false
     * items : [{"id":29028775,"name":"react-native","full_name":"facebook/react-native","owner":{"login":"facebook","id":69631,"avatar_url":"https://avatars.githubusercontent.com/u/69631?v=3"},"description":"A framework for building native apps with React.","stargazers_count":33961,"forks_count":7122},"......"]
     */

    private int total_count;
    private boolean incomplete_results;
    private List<ItemsBean> items;

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

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "RepoResult{" +
                "total_count=" + total_count +
                ", incomplete_results=" + incomplete_results +
                ", items=" + items +
                '}';
    }
}
