package com.example.administrator.githupanddroid.favirate.dao;

import com.example.administrator.githupanddroid.favirate.model.LocalRepo;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016-08-04.
 */

public class LocalRepoDao {

    private Dao<LocalRepo,Long> dao;

    public LocalRepoDao(DBhelper dBhelper){
        try {
            dao=dBhelper.getDao(LocalRepo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   public void  createOrUpdate(LocalRepo localRepo){
       try {
           dao.createOrUpdate(localRepo);
       } catch (SQLException e) {
           e.printStackTrace();
       }

   }

    public void createOrUpdate(List<LocalRepo> localRepos){
        for (LocalRepo localRepo :localRepos){
            try {
                dao.create(localRepo);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //删除本地数据库
    public void delete(LocalRepo localRepo){
        try {
            dao.delete(localRepo);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //查询本地仓库(图像处理的，架构的...,能查到全部或未分类的？？？)
    public List<LocalRepo>  queryForGroupId(int id) throws SQLException {

        return dao.queryForEq(LocalRepo.COLUMN_GROUP_ID,id);
    }

    //查询未分类

    public List<LocalRepo> queryForNoGroup() throws SQLException {
        return dao.queryBuilder().where().isNotNull(LocalRepo.COLUMN_GROUP_ID).query();
    }

    //查询本地仓库
    public List<LocalRepo> queryForAll() throws SQLException {

        return dao.queryForAll();
    }

}
