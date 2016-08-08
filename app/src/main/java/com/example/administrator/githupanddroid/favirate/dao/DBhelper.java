package com.example.administrator.githupanddroid.favirate.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.githupanddroid.favirate.model.LocalRepo;
import com.example.administrator.githupanddroid.favirate.model.RepoGroup;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016-08-04.
 */

public class DBhelper extends OrmLiteSqliteOpenHelper{
    private static DBhelper dBhelper;
    private Context context;

    private DBhelper(Context context) {
        super(context, "repo_favorite.db", null, 3);
        this.context=context;
    }

    public static synchronized DBhelper getdBhelper(Context context){
        if (dBhelper==null){
            dBhelper=new DBhelper(context.getApplicationContext());
        }
        return dBhelper;
    }
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource,RepoGroup.class);
            TableUtils.createTableIfNotExists(connectionSource, LocalRepo.class);
            new RepoGroupDao(dBhelper).createOrUpdate(RepoGroup.getRepoGroups(context));
            new LocalRepoDao(dBhelper).createOrUpdate(LocalRepo.getDefaultLocalRepos(context));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,RepoGroup.class,true);
            TableUtils.dropTable(connectionSource,LocalRepo.class,true);
            onCreate(database,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
