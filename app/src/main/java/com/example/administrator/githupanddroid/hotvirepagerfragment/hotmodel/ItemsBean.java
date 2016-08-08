package com.example.administrator.githupanddroid.hotvirepagerfragment.hotmodel;

import android.os.Bundle;
import android.os.Parcelable;

import com.example.administrator.githupanddroid.login.model.User;


import java.io.Serializable;

/**
 * Created by Administrator on 2016-08-01.
 */

public class ItemsBean implements Serializable{
    private int id;
    private String name;
    private String full_name;
    private User owner;
    private String description;
    private int stargazers_count;
    private int forks_count;//仓库的fork数量

    public ItemsBean(int id, String name, String full_name, User owner, String description, int stargazers_count, int forks_count) {
        this.id = id;
        this.name = name;
        this.full_name = full_name;
        this.owner = owner;
        this.description = description;
        this.stargazers_count = stargazers_count;
        this.forks_count = forks_count;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public int getForks_count() {
        return forks_count;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }

    @Override
    public String toString() {
        return "ItemsBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", full_name='" + full_name + '\'' +
                ", owner=" + owner +
                ", description='" + description + '\'' +
                ", stargazers_count=" + stargazers_count +
                ", forks_count=" + forks_count +
                '}';
    }
}
