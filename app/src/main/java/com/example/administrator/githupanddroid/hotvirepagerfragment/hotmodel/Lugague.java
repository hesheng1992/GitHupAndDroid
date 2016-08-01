package com.example.administrator.githupanddroid.hotvirepagerfragment.hotmodel;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * Created by Administrator on 2016-08-01.
 */

//这个类是拿到不同的语言的类
public class Lugague implements Serializable{
    private String path;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private static List<Lugague> list;

    public static List<Lugague> getInstanse(Context context){
        if (list!=null){
            return list;
        }
        try {
            InputStream inputStream = context.getAssets().open("langs.json");
            //将输入流直接转换为字符串
            String s = IOUtils.toString(inputStream);
            //在将它转换为gson
            Gson gson=new Gson();
            list = gson.fromJson(s, new TypeToken<List<Lugague>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;

    }


}
