package com.example.elina.weatherapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.elina.weatherapp.pojoClasses.Info;

@Database(entities = {Info.class}, version = 1)
public abstract class AppDb extends RoomDatabase {
    public abstract Dao dao();
}
