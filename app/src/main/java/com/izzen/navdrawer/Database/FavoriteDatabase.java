package com.izzen.navdrawer.Database;

/*
 * Nama          : M Izzudin Wijaya
 * NIM           : 10117152
 * Latest Update : 15 July 2020, 11:19:00 WIB
 * Modified      : 15 July 2020, 11:19:00 WIB
 * */

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.izzen.navdrawer.Model.FavoriteList;
import com.izzen.navdrawer.Interface.FavoriteDao;

@Database(entities={FavoriteList.class},version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {

    public abstract FavoriteDao favoriteDao();


}