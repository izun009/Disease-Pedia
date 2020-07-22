package com.izzen.navdrawer.Interface;

/*
 * Nama          : M Izzudin Wijaya
 * NIM           : 10117152
 * Latest Update : 15 July 2020, 11:19:00 WIB
 * Modified      : 15 July 2020, 11:19:00 WIB
 * */


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.izzen.navdrawer.Model.FavoriteList;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Insert
    public void addData(FavoriteList favoriteList);

    @Query("select * from favoritelist")
    public List<FavoriteList> getFavoriteData();

    @Query("SELECT EXISTS (SELECT 1 FROM favoritelist WHERE id=:id)")
    public int isFavorite(int id);

    @Delete
    public void delete(FavoriteList favoriteList);

    @Query("select * from favoritelist where id=:id")
    public List<FavoriteList> getSpesifikData(int id);


}