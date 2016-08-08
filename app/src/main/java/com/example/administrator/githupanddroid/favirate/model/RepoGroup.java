package com.example.administrator.githupanddroid.favirate.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 2016-08-04.
 */

@DatabaseTable(tableName = "repostiory_group")
public class RepoGroup {
    @DatabaseField(id = true)
    @SerializedName("id")
    private int id;
    @DatabaseField(columnName = "NAME")
    @SerializedName("name")
    private String name;

    public RepoGroup(int id, String name) {
        this.id = id;
        this.name = name;
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

    private static List<RepoGroup> repoGroups;

    public static List<RepoGroup> getRepoGroups(Context context){
        if(repoGroups != null)return repoGroups;
        try {
            InputStream open = context.getAssets().open("repogroup.json");
            String s = IOUtils.toString(open);
            Gson gson=new Gson();
             repoGroups = gson.fromJson(s, new TypeToken<List<RepoGroup>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return repoGroups;
    }

}
