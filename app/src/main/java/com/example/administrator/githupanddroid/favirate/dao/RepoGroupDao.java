package com.example.administrator.githupanddroid.favirate.dao;

import com.example.administrator.githupanddroid.favirate.model.RepoGroup;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016-08-04.
 */

public class RepoGroupDao {
    private Dao<RepoGroup,Long> dao;


    public RepoGroupDao(DBhelper dBhelper){
        try {
            dao=dBhelper.getDao(RepoGroup.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //创建或更新，仓库类别表
    public void createOrUpdate(RepoGroup group){
        try {
            dao.create(group);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void createOrUpdate(List<RepoGroup> list){
        for (RepoGroup group:list){
            createOrUpdate(group);
        }
    }
    //查询指定ID 仓库类别
    public RepoGroup queryForId(long id) throws SQLException {


        return dao.queryForId(id);


    }

    public List<RepoGroup> queryForAll() throws SQLException {
        return dao.queryForAll();

    }




}