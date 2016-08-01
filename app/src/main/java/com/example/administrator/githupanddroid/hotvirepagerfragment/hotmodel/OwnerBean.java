package com.example.administrator.githupanddroid.hotvirepagerfragment.hotmodel;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-08-01.
 */

public class OwnerBean implements Serializable{
    private String login;
    private int id;
    private String avatar_url;

    public OwnerBean(String login, int id, String avatar_url) {
        this.login = login;
        this.id = id;
        this.avatar_url = avatar_url;
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

    @Override
    public String toString() {
        return "OwnerBean{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", avatar_url='" + avatar_url + '\'' +
                '}';
    }
}
