package com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-08-02.
 */

public class CoderItemsBean implements Serializable{
    private String login;
    private int id;
    private String avatar_url;
    private String type;
    private double score;

    public CoderItemsBean(String login, int id, String avatar_url, String type, double score) {
        this.login = login;
        this.id = id;
        this.avatar_url = avatar_url;
        this.type = type;
        this.score = score;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "CoderItemsBean{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", avatar_url='" + avatar_url + '\'' +
                ", type='" + type + '\'' +
                ", score=" + score +
                '}';
    }
}
